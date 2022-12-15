package com.sebone.main.data;
import java.util.ArrayList;

public class UpdateCollegeDo {
    /*
     * @className-UpdateCollegeDo
     * @ClassObjective- creating getter setter for the updating college  i.e.college api put method and inserting data for enities.
     * @autherName- Varsha Rane
     */

    //arraylists and variables declarations
    private String collegeName;
    private Type type;
    private String email;
    private AddressDo addressDoObj;
    private String contactNumber;
    private String password;
    private String collegeCode;
    private String approvedBy;
    private String city;
    private String overview;
    private String awards;
    private String established;
    private String ranking;
    private String mapLocation;
    private String verified;
    private String facilities;
    private String noOfStudents;
    private ArrayList<CoursesDo> coursesArrayList;
    private ArrayList<PlacementsDo> placementsDoArrayList;
    private String examsAccepted;
    private String collegeStatus;
    private String website;
    private String profileImage;
    private String placedStudents;
    private String images;
    private String videos;



    //getter setter for variables and arraylist
    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDo getAddressDoObj() {
        return addressDoObj;
    }

    public void setAddressDoObj(AddressDo addressDoObj) {
        this.addressDoObj = addressDoObj;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(ApprovedBy approvedBy) {
        this.approvedBy = String.valueOf(approvedBy);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getEstablished() {
        return established;
    }

    public void setEstablished(String established) {
        this.established = established;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(String mapLocation) {
        this.mapLocation = mapLocation;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(String noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public ArrayList<CoursesDo> getCoursesArrayList() {
        return coursesArrayList;
    }

    public void setCoursesArrayList(ArrayList<CoursesDo> coursesArrayList) {
        this.coursesArrayList = coursesArrayList;
    }

    public ArrayList<PlacementsDo> getPlacementsDoArrayList() {
        return placementsDoArrayList;
    }

    public void setPlacementsDoArrayList(ArrayList<PlacementsDo> placementsDoArrayList) {
        this.placementsDoArrayList = placementsDoArrayList;
    }

    public String getExamsAccepted() {
        return examsAccepted;
    }

    public void setExamsAccepted(String examsAccepted) {
        this.examsAccepted = examsAccepted;
    }

    public String getCollegeStatus() {
        return collegeStatus;
    }

    public void setCollegeStatus(String collegeStatus) {
        this.collegeStatus = collegeStatus;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getPlacedStudents() {
        return placedStudents;
    }

    public void setPlacedStudents(String placedStudents) {
        this.placedStudents = placedStudents;
    }

    //constructers for variable and arraylist
    public UpdateCollegeDo(String collegeName, Type type, String email, AddressDo addressDoObj, String contactNumber, String password, String collegeCode, String approvedBy, String city, String overview, String awards, String established, String ranking, String mapLocation, String verified, String facilities, String noOfStudents, ArrayList<CoursesDo> coursesArrayList, ArrayList<PlacementsDo> placementsDoArrayList, String examsAccepted, String collegeStatus, String website, String profileImage, String placedStudents, String images, String videos) {
        this.collegeName = collegeName;
        this.type = type;
        this.email = email;
        this.addressDoObj = addressDoObj;
        this.contactNumber = contactNumber;
        this.password = password;
        this.collegeCode = collegeCode;
        this.approvedBy = approvedBy;
        this.city = city;
        this.overview = overview;
        this.awards = awards;
        this.established = established;
        this.ranking = ranking;
        this.mapLocation = mapLocation;
        this.verified = verified;
        this.facilities = facilities;
        this.noOfStudents = noOfStudents;
        this.coursesArrayList = coursesArrayList;
        this.placementsDoArrayList = placementsDoArrayList;
        this.examsAccepted = examsAccepted;
        this.collegeStatus = collegeStatus;
        this.website = website;
        this.profileImage = profileImage;
        this.placedStudents = placedStudents;
        this.images = images;
        this.videos = videos;
    }

    //putting values in the updateCollegeDo ctor
    public UpdateCollegeDo(){
        //inserting values in entities.
        collegeName = "Imperial College Of Engineering";
        type = Type.PRIVATE;
        email = "mohit88@gmail.com";
        contactNumber ="8989098099";
        approvedBy = ApprovedBy.AICTE.name();
        collegeCode="113";
        addressDoObj = new AddressDo("Borawan","Ravindra nagar","Khargone", State.ARUNACHAL_PRADESH);
        city = "banglore";
        overview = null;
        awards = null;
        established = null;
        ranking = "1";
        mapLocation =null;
        verified =null;
        facilities =null;
        noOfStudents  ="1";
        //adding values in courseArraylist
        coursesArrayList = new ArrayList<CoursesDo>();
        CoursesDo coursesDo = new CoursesDo("B.tech ","civil","120","150");
        coursesArrayList.add(coursesDo);
        //adding values in placementArraylist
        placementsDoArrayList = new ArrayList<PlacementsDo>();
        PlacementsDo placementsDo = new PlacementsDo("Cs","2022","MCA","Sebone","1");
        placementsDoArrayList.add(placementsDo);
        images=null;
        videos = null;
    }

}
