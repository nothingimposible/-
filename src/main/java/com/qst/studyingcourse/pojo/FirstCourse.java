package com.qst.studyingcourse.pojo;

public class FirstCourse {
    private int ID;
    private String NAME;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    @Override
    public String toString() {
        return "FirstCourse{" +
                "ID=" + ID +
                ", NAME='" + NAME + '\'' +
                '}';
    }
}
