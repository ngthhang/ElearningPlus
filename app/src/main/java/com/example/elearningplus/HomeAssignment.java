package com.example.elearningplus;

public class HomeAssignment {
    private String courseName;
    private String courseAssignment;
    private String dueDate;

    public HomeAssignment(String courseName, String courseAssignment, String dueDate) {
        this.courseName = courseName;
        this.courseAssignment = courseAssignment;
        this.dueDate = dueDate;
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }


}
