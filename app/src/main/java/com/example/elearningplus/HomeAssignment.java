package com.example.elearningplus;

public class HomeAssignment {
    private String courseName;
    private String courseAssignment;

    public HomeAssignment(String courseName, String courseAssignment) {
        this.courseName = courseName;
        this.courseAssignment = courseAssignment;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseAssignment() {
        return courseAssignment;
    }

    public void setCourseAssignment(String courseAssignment) {
        this.courseAssignment = courseAssignment;
    }
}
