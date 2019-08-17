package com.qst.studyingcourse.pojo;

public class Course {
    private int id;
    private String coursename;
    private String coursedesc;
    private String courseimg;
    private String courseteacher;
    private String coursetype;
    private int secondid;
    private String upID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursedesc() {
        return coursedesc;
    }

    public void setCoursedesc(String coursedesc) {
        this.coursedesc = coursedesc;
    }

    public String getCourseimg() {
        return courseimg;
    }

    public void setCourseimg(String courseimg) {
        this.courseimg = courseimg;
    }

    public String getCourseteacher() {
        return courseteacher;
    }

    public void setCourseteacher(String courseteacher) {
        this.courseteacher = courseteacher;
    }

    public String getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype;
    }

    public int getSecondid() {
        return secondid;
    }

    public void setSecondid(int secondid) {
        this.secondid = secondid;
    }

    public String getUpID() {
        return upID;
    }

    public void setUpID(String upID) {
        this.upID = upID;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", coursename='" + coursename + '\'' +
                ", coursedesc='" + coursedesc + '\'' +
                ", courseimg='" + courseimg + '\'' +
                ", courseteacher='" + courseteacher + '\'' +
                ", coursetype='" + coursetype + '\'' +
                ", secondid=" + secondid +
                '}';
    }
}
