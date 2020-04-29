package com.example.elearningplus;

public class LogUp_ChooseLesson_Data {

    private boolean checked;
    private String name;
    private String id;

    public LogUp_ChooseLesson_Data(String id, String name, Boolean checked) {
        this.id = id;
        this.name = name;
        this.checked = checked;
    }

    public LogUp_ChooseLesson_Data(String id, String name){
        this.id = id;
        this.name = name;
        this.checked = false;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
