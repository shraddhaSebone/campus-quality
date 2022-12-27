package com.sebone.main.data;

public class UpdateCourseDo {
    //variables declared
    private String courseName;
    private String branchName;
    private String placedStudents;
    private String totalStudents;
    private int courseId;

    public UpdateCourseDo() {

    }

    //course getter setters

    public String getCourseName() {
        return courseName;
    }

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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    //constructor
    public UpdateCourseDo(String courseName, String branchName, String placedStudents, String totalStudents) {
        this.courseName = "Ploytechnic Diploma";
        this.branchName = "CSE";
        this.placedStudents = "11";
        this.totalStudents = "48";
    }
}
