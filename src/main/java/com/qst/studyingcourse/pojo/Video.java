package com.qst.studyingcourse.pojo;

public class Video {
    private int id;
    private String videodesc;
    private int courseid;
    private String videodescribe;
    private String coverimg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideodesc() {
        return videodesc;
    }

    public void setVideodesc(String videodesc) {
        this.videodesc = videodesc;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getVideodescribe() {
        return videodescribe;
    }

    public void setVideodescribe(String videodescribe) {
        this.videodescribe = videodescribe;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", videodesc='" + videodesc + '\'' +
                ", courseid=" + courseid +
                ", videodescribe='" + videodescribe + '\'' +
                '}';
    }
}
