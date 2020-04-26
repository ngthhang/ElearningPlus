package com.example.elearningplus;

public class CourseScreen_Grade {

    private String NameGrade;
    private float Grade;

    public CourseScreen_Grade(String nameGrade,float grade) {
        NameGrade = nameGrade;
        Grade = grade;
    }

    public String getNameGrade() {
        return NameGrade;
    }

    public void setNameGrade(String nameGrade) {
        NameGrade = nameGrade;
    }

    public float getGrade() {
        return Grade;
    }

    public void setGrade(float grade) {
        Grade = grade;
    }
}
