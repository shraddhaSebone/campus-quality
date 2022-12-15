package com.sebone.main.data;

public class AddContactDo {
    /*
     * @className-AddContactDo
     * @ClassObjective- creating getter setter for the adding contact first time.
     * @autherName- Varsha Rane
     */

    //variables declaration
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String message;

    //getter and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //contructor

    public AddContactDo() {
        this.firstName = "Mohit";
        this.lastName = "Rane";
        this.email = "mohit99@gmail.com";
        this.contactNumber = "8909876900";
        this.message = "For JIT college";
    }
}
