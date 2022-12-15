package com.sebone.main.data;

public class PlacementsDo {
    private String branch;
    private String passingYear;
    private String course;
    private String companyName;
    private String noOfStudents;

    //getter setter for the placement

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(String passingYear) {
        this.passingYear = passingYear;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(String noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    //constructers for the placement
    public PlacementsDo(String branch, String passingYear, String course, String companyName, String noOfStudents) {
        this.branch = branch;
        this.passingYear = passingYear;
        this.course = course;
        this.companyName = companyName;
        this.noOfStudents = noOfStudents;
    }

    //tostring method override
    @Override
    public String toString() {
        return "placements{" +
                "branch='" + branch + '\'' +
                ", passingYear='" + passingYear + '\'' +
                ", course='" + course + '\'' +
                ", companyName='" + companyName + '\'' +
                ", noOfStudents='" + noOfStudents + '\'' +
                '}';
    }
}
