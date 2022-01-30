package sumdu.edu.ua.lab11.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Student {

    private String firstName;
    private String lastName;
    private int groupNumber;
    private List<Subject> subjectList;
    private float average;

    public Student(String firstName, String lastName, int groupNumber, List<Subject> subjectList, float average) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupNumber = groupNumber;
        this.subjectList = subjectList;
        setAverage(average);
    }

    public float calculateAverage(){
        float trueAverage = 0;

        for (int i = 0; i < subjectList.size(); i++) {
            trueAverage += subjectList.get(i).getMark();
        }

        trueAverage = trueAverage/subjectList.size();

        if (Float.toString(trueAverage).length() > 3) {
            return new BigDecimal(trueAverage).setScale(1, BigDecimal.ROUND_DOWN).floatValue();
        }
        return trueAverage;
    }

    public void setAverage(float average) {
        float calculatedAverage = calculateAverage();

        if (Float.toString(average).length() > 3) {
            average = new BigDecimal(average).setScale(1, RoundingMode.DOWN).floatValue();
        }
        if (average != calculatedAverage){
            this.average = calculatedAverage;
        } else {
            this.average = average;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public float getAverage() {
        return average;
    }
}
