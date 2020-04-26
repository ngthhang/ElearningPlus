package com.example.elearningplus;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseScreen_Assignment {

    private String name;
    private String due;
    private String isOpen;
    private String late;

    public CourseScreen_Assignment(String name, String due, String isOpen) {
        this.name = name;
        this.due = due;
        this.isOpen = isOpen;
        Date currentDate = new Date();
        try {
            Date convertDate = new SimpleDateFormat( "dd/MM/yyyy" ).parse( due );
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }
}
