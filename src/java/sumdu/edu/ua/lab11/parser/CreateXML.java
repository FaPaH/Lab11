package sumdu.edu.ua.lab11.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sumdu.edu.ua.lab11.model.Student;
import sumdu.edu.ua.lab11.model.Subject;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CreateXML {

    public void create(List<Student> studentList, String fileName){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element element = document.createElement("group");
            document.appendChild(element);

            for (int i = 0; i < studentList.size(); i++) {
                Element student = document.createElement("student");
                student.setAttribute("firstname", studentList.get(i).getFirstName());
                student.setAttribute("lastname", studentList.get(i).getLastName());
                student.setAttribute("groupnumber", String.valueOf(studentList.get(i).getGroupNumber()));
                element.appendChild(student);
                ArrayList<Subject> subjectArrayList = (ArrayList<Subject>) studentList.get(i).getSubjectList();
                for (int j = 0; j < subjectArrayList.size(); j++) {
                    Element subject = document.createElement("subject");
                    subject.setAttribute("mark", Integer.toString(subjectArrayList.get(j).getMark()));
                    subject.setAttribute("title", subjectArrayList.get(j).getTitle());
                    student.appendChild(subject);
                }
                Element average = document.createElement("average");
                average.setTextContent(Float.toString(studentList.get(i).getAverage()));
                student.appendChild(average);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/resources/" + fileName + ".xml")));
            System.out.println("DONE!");
        } catch (ParserConfigurationException | TransformerException | FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR: Check input file name");
        }
    }
}
