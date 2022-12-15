package com.sebone.main.data;

public class CoursesDo {
    private String courseName;
    private String branchName;
    private String placedStudents;
    private String totalStudents;

    public String getCourseName() {
        return courseName;
    }

    //getter setter for the variables of course
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPlacedStudents() {
        return placedStudents;
    }

    public void setPlacedStudents(String placedStudents) {
        this.placedStudents = placedStudents;
    }

    public String getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(String totalStudents) {
        this.totalStudents = totalStudents;
    }

    //constructers for the course
    public CoursesDo(String courseName, String branchName, String placedStudents, String totalStudents) {
        this.courseName = courseName;
        this.branchName = branchName;
        this.placedStudents = placedStudents;
        this.totalStudents = totalStudents;
    }

    //to print the object's value tostring method called otherwise hashcode is displayed
    @Override
    public String toString() {
        return "{" +
                "courseName='" + courseName + '\'' +
                ", branchName='" + branchName + '\'' +
                ", placedStudents='" + placedStudents + '\'' +
                ", totalStudents='" + totalStudents + '\'' +
                '}';
    }
}
