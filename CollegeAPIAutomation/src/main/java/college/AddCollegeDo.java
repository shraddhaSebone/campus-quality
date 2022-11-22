package college;

public class AddCollegeDo {
    /*
     * @className-AddCollegeDo
     * @ClassObjective- creating getter setter for the adding college first time i.e.college api post method and inserting data for enities.
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

    //getter setter for the variables
    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
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

    public void setAddressDoObj(AddressDo addressDoArrayList) {
        this.addressDoObj = addressDoArrayList;
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

    //constructor
    public AddCollegeDo(String collegeName, Type type, String email, AddressDo addressDoObj, String contactNumber, String password, String collegeCode) {
        this.collegeName = collegeName;
        this.type = type;
        this.email = email;
        this.addressDoObj = addressDoObj;
        this.contactNumber = contactNumber;
        this.password = password;
        this.collegeCode = collegeCode;
    }

    public  AddCollegeDo(){
        //inserting values in entities.
        collegeName = "Imperial College Of Engineering";
        type = Type.PRIVATE;
        email = "mohit88@gmail.com";
        contactNumber ="8989098099";
        password="Test@123";
        collegeCode="113";
        addressDoObj = new AddressDo("Borawan","Ravindra nagar","Khargone",State.ARUNACHAL_PRADESH);
    }
}
