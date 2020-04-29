package com.example.elearningplus;

public class CourseResultDetail {
    private int final_test;
    private int mid_test;
    private int progress_1;
    private int progress_2;

    public CourseResultDetail() {
        this.final_test = 0;
        this.mid_test = 0;
        this.progress_1 = 0;
        this.progress_2 = 0;
    }

    public int getFinal_test() {
        return final_test;
    }

    public void setFinal_test(int final_test) {
        this.final_test = final_test;
    }

    public int getMid_test() {
        return mid_test;
    }

    public void setMid_test(int mid_test) {
        this.mid_test = mid_test;
    }

    public int getProgress_1() {
        return progress_1;
    }

    public void setProgress_1(int progress_1) {
        this.progress_1 = progress_1;
    }

    public int getProgress_2() {
        return progress_2;
    }

    public void setProgress_2(int progress_2) {
        this.progress_2 = progress_2;
    }
}
