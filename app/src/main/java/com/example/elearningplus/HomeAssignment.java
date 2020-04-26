package com.example.elearningplus;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeAssignment {
    private String courseName;
    private String courseAssignment;
    private String dueDate;
    private String late;
    private String courseKey;
    private String isOpen;

    public HomeAssignment(String courseKey,String courseName, String courseAssignment, String dueDate, String isOpen) {
        this.courseKey = courseKey;
        this.courseName = courseName;
        this.courseAssignment = courseAssignment;
        this.dueDate = dueDate;
        this.isOpen = isOpen;
        String asssignmentDueText = dueDate;
        Date currentDate = new Date();
        try {
            Date convertDate = new SimpleDateFormat( "dd/MM/yyyy" ).parse( asssignmentDueText );
            if (convertDate.compareTo( currentDate ) < 0) {
                this.late = "true";
            }
            else if (convertDate.compareTo( currentDate ) > 0){
                this.late = "false";
            }
        }
        catch(ParseException e){
            Log.e("Fail to convert Date", toString());
        }
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

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }

    public String getCourseKey() {
        return courseKey;
    }

    public void setCourseKey(String courseKey) {
        this.courseKey = courseKey;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }
}
