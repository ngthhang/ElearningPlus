package com.example.elearningplus;

public class CourseScreen_Assignment {

    private String nLab;
    private String nText;
    private String nDay;

    public CourseScreen_Assignment(String nLab, String nText, String nDay) {
        this.nLab = nLab;
        this.nText = nText;
        this.nDay = nDay;
    }

    public String getnLab() {
        return nLab;
    }

    public void setnLab(String nLab) {
        this.nLab = nLab;
    }

    public String getnText() {
        return nText;
    }

    public void setnText(String nText) {
        this.nText = nText;
    }

    public String getnDay() {
        return nDay;
    }

    public void setnDay(String nDay) {
        this.nDay = nDay;
    }
}
