package sumdu.edu.ua.lab11;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseXML {

    public List<Student> parseStudents(String fileName){

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("src/resources/" + fileName + ".xml"));

            NodeList studentElements = document.getDocumentElement().getElementsByTagName("student");

            List<Student> studentList = new ArrayList<>();

            for (int i = 0; i < studentElements.getLength(); i++) {
                Element student = (Element) studentElements.item(i);

                String firstName = student.getAttribute("firstname");
                String lastName = student.getAttribute("lastname");
                int groupNumber = Integer.parseInt(student.getAttribute("groupnumber"));

                studentList.add(new Student(firstName, lastName, groupNumber,
                        parseSubjects(student.getElementsByTagName("subject")),
                        parseAverage(student.getElementsByTagName("average"))));
            }
            return studentList;
        } catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
            System.out.println("ERROR: Check input file name");
        }
        return null;
    }

    private List<Subject> parseSubjects(NodeList subjects){

        List<Subject> subjectList = new ArrayList<>();

        for (int i = 0; i < subjects.getLength(); i++) {
            Node subject = subjects.item(i);

            NamedNodeMap attributes = subject.getAttributes();

            String title = attributes.getNamedItem("title").getNodeValue();
            int mark = Integer.parseInt(attributes.getNamedItem("mark").getNodeValue());

            subjectList.add(new Subject(title, mark));
        }
        return subjectList;
    }

    private float parseAverage(NodeList averages){
        float averageNum = 0;

        for (int i = 0; i < averages.getLength(); i++) {
            Node average = averages.item(i);

            averageNum = Float.parseFloat(average.getTextContent());
        }
        return averageNum;
    }
}
