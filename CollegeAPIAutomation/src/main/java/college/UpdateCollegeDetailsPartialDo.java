package college;

public class UpdateCollegeDetailsPartialDo {
    /*
     * @className-UpdateCollegeDetailsPartialDo
     * @ClassObjective- creating getter setter for the updating college  i.e.college api put method and inserting data for enities.
     * @autherName- Varsha Rane
     */

    //variables declarations
    private String collegeCode;
    private String collegeName;
    private String city;
    private Integer ranking;
    private String contactNumber;
    private String email;
    private int noOfStudents;
    private int placedStudents;
    private String overview;
    private String awards;
    private String website;

    //getter setter for variables
    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRanking() {
        return ranking.intValue();
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public int getPlacedStudents() {
        return placedStudents;
    }

    public void setPlacedStudents(int placedStudents) {
        this.placedStudents = placedStudents;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    //constructor
    public UpdateCollegeDetailsPartialDo(String collegeCode, String collegeName, String city, Integer ranking, String contactNumber, String email, int noOfStudents, int placedStudents, String overview, String awards, String website) {
        this.collegeCode = collegeCode;
        this.collegeName = collegeName;
        this.city = city;
        this.ranking = ranking.intValue();
        this.contactNumber = contactNumber;
        this.email = email;
        this.noOfStudents = noOfStudents;
        this.placedStudents = placedStudents;
        this.overview = overview;
        this.awards = awards;
        this.website = website;
    }

    //constructor with initialized values
    public UpdateCollegeDetailsPartialDo() {
        this.collegeCode = "111";
        this.collegeName = "JIT";
        this.city = "khargone";
        this.ranking = 5;
        this.contactNumber = "8976789899";
        this.email = "mohit99@gmail.com";
        this.noOfStudents = 100;
        this.placedStudents = 60;
        this.overview = "A college (Latin: collegium) is an educational institution.overview";
        this.awards = "best college in asia  recived in thailand";
        this.website = "www.google.com";
    }
}
