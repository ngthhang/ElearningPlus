package com.example.elearningplus;

public class CourseScreen_Course {

    private String nChapter;
    private String nIntro;

    public CourseScreen_Course(String nChapter, String nIntro) {
        this.nChapter = nChapter;
        this.nIntro = nIntro;
    }

    public String getnChapter() {
        return nChapter;
    }

    public void setnChapter(String nChapter) {
        this.nChapter = nChapter;
    }

    public String getnIntro() {
        return nIntro;
    }

    public void setnIntro(String nIntro) {
        this.nIntro = nIntro;
    }
}