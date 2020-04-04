package com.example.elearningplus;

public class HomeCourse {
    private String name;
    private String courseId;

    public HomeCourse(String name, String courseId ) {
        this.name = name;
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
