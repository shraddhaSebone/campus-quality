package com.sebone.main.data;

public class UpdatePlacementDo {
    private String branch;
    private String passingYear;
    private String course;
    private String companyName;
    private String noOfStudents;
    private int placementId;

    //getter setter for the placement


    public int getPlacementId() {
        return placementId;
    }

    public void setPlacementId(int placementId) {
        this.placementId = placementId;
    }

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
    public UpdatePlacementDo() {
        this.branch = "ME";
        this.passingYear = "2022";
        this.course = "Btech";
        this.companyName = "Sebone technologies pvt.ltd";
        this.noOfStudents = "120";
    }

    //constructers for the placement

    //tostring method override
    @Override
    public String toString() {
        return "placements{" +
                "branch='" + branch + '\'' +
                ", passingYear='" + passingYear + '\'' +
                ", course='" + course + '\'' +
                ", companyName='" + companyName + '\'' +
                ", noOfStudents='" + noOfStudents + '\'' +
                ",placementId='" + placementId + '\''+
                '}';
    }
}
