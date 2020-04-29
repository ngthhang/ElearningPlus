package com.example.elearningplus;

public class LogUp_ChooseLesson_Data {

    private boolean Checked;
    private String Name;

    public LogUp_ChooseLesson_Data(String name) {
        Checked = false;
        Name = name;
    }

    public LogUp_ChooseLesson_Data(boolean checked, String name) {
        Checked = checked;
        Name = name;
    }

    public boolean isChecked() {
        return Checked;
    }

    public void setChecked(boolean checked) {
        Checked = checked;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
