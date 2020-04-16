package com.example.elearningplus;

public class HomeCourse {
    private String name;
    private String courseId;
    private String courseKey;

    public HomeCourse(String name, String courseId, String courseKey ) {
        this.name = name;
        this.courseId = courseId;
        this.courseKey = courseKey;
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

    public String getCourseKey() {
        return courseKey;
    }

    public void setCourseKey(String courseKey) {
        this.courseKey = courseKey;
    }
}
