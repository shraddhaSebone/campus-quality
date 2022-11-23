package college;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AddCollegeAutomationTest {
    /* @className- AddCollegeAutomationTest
     * @autherName- varsha Rane
     * @Objective - to automate the testcases for the college api
     */
     private SetupForCollege url;

    //@MethodObjective- to execute before class
    @BeforeClass
    public void url() {
        url = new SetupForCollege();
    }

    // @MethodObjective- the objective of this method is to test the status code is 200 and statusline for the successfully adding the college.
    @Test(priority = 1)
    public void verifyAddCollege(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        //college code is unique so it need to be change everytime
        addCollegeDo.setCollegeCode("125");
        GetApiResponse response = url.setUpForAddCollege(addCollegeDo);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 200 ");
    }

    //@MethodObjective- Check if the content -type is application json or not.
    @Test(priority = 2)
    public void verifyContentType(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setCollegeCode("127");
        GetApiResponse response = url.setUpForAddCollege(addCollegeDo);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        //after checking api is 200 response validate that for the correct request its content -type passed was application-json
        // access header and validate it
        String contentType = response.getResponse().header("Content-Type");
        // assertion for contentType
        Assert.assertEquals(contentType, "application/json");
    }

    // @MethodObjective= testing the response time of the College api which should be less than 1 sec.
    @Test(priority = 3)
    public void verifyResponseTime() {
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        GetApiResponse getApiResponse = url.setUpForAddCollege(addCollegeDo);
        // obtain ValidatableResponse type
        ValidatableResponse resType = getApiResponse.getResponse().then();
        // verify response time lesser than 1000 milliseconds
        resType.time(Matchers.lessThan(1000L));
    }

    //@MethodObjective-Check if the response is showing the correct data of the college added successfully or not.
    @Test(priority = 4)
    public void verifyAddCollegeResponseBody(){
        //creating object of the DO class
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setCollegeCode("135");
        //creating object created for response
        GetApiResponse getApiResponse = url.setUpForAddCollege(addCollegeDo);
        // JsonPath object created for response
        Response response = getApiResponse.getResponse();
        String responseBody =response.getBody().prettyPrint();
        Assert.assertTrue(responseBody.contains("collegeName"));
        Assert.assertTrue(responseBody.contains("type"));
        Assert.assertTrue(responseBody.contains("email"));
        Assert.assertTrue(responseBody.contains("address"));
        Assert.assertTrue(responseBody.contains("collegeCode"));
        Assert.assertTrue(responseBody.contains("email"));
    }

    //@MethodObjective- Check if on adding the already added collage code then  its showing error message and status code.
    @Test(priority = 5)
    public void checkAlreadyAddedResponse() {
        //creating object of the Do class
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setCollegeCode("138");
        //creating object of getApi response class for passing url and object of Do
        GetApiResponse getApiResponse = url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(200,statusCode);

        //again adding college with same college code
        addCollegeDo.setCollegeCode("138");
        //creating object of getApi response class for passing url and object of Do
        GetApiResponse alreadyAddedResponse = url.setUpForAddCollege(addCollegeDo);
        int statusCode2 = alreadyAddedResponse.getStatusCode();
        Assert.assertEquals(500,statusCode2);
        //errorcode, errormessage and detailedMessage assertion for the messages
        JsonPath jsonPathEvauator = alreadyAddedResponse.getResponse().jsonPath();
        String errorCode = jsonPathEvauator.getString("errorCode");
        Assert.assertTrue(errorCode.equals("INTERNAL_SERVER_ERROR"));
        String errorMessage = jsonPathEvauator.getString("errorMessage");
        Assert.assertTrue(errorMessage.equals("Op's Something went wrong!"));
        String detailedMessage = jsonPathEvauator.getString("detailedMessage");
        Assert.assertTrue(detailedMessage.equals("This College already exists!"));
    }

    //@MethodObjective- Check if on passing empty collegeName what is the error message and status code.
    @Test(priority=6)
    public void checkEmptyCollegeNameValidation(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setCollegeName("");
        GetApiResponse getApiResponse = url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String collegeName =  jsonPathEvaluator.getString("collegeName");
        Assert.assertTrue(collegeName.equals("College Name Should Be Valid!"));
    }

    //@MethodObjective- Check if on passing null collegeName what is the error message and status code.
    @Test(priority=6)
    public void checkNullCollegeNameValidation(){

        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setCollegeName(null);
        GetApiResponse getApiResponse = url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String collegeName =  jsonPathEvaluator.getString("collegeName");
        Assert.assertTrue(collegeName.equals("College Name Should Be Valid!"));
    }

    //@MethodObjective-Check if "collegeName" is accepting correct alphabetic characters.
    @Test(priority=7)
    public void validateCollegeName(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setCollegeName("Imperial677%");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String collegeName =  jsonPathEvaluator.getString("collegeName");
        Assert.assertTrue(collegeName.equals("College Name Should Be Valid!"));
    }

    //Check if "collegeName" is not having specific length in characters. example 32
    @Test(priority=8)
    public void validateCollegeLength(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setCollegeName("Ihhgfvbnnjjkjhhhjjjnfgthgtgrrgfdgvfrfbfbgfbgfbfgbgfbvfvb  bbv b njnhbgtfnhhyggyyhhjnygnhnghuknhkuhghjhbhjhkjkjkjkhkhihuihuikjljklnklkkkjjjjjjjjjjjlkkluihuihuikiujhjhjkuyyyiufvtrdfgjgykuijghnyuhj");
        addCollegeDo.setCollegeCode("026");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(200,statusCode);
        // JsonPath object created for response
        Response response = getApiResponse.getResponse();
        String responseBody =response.getBody().prettyPrint();
        Assert.assertTrue(responseBody.contains("collegeName"));
        Assert.assertTrue(responseBody.contains("type"));
        Assert.assertTrue(responseBody.contains("email"));
        Assert.assertTrue(responseBody.contains("address"));
        Assert.assertTrue(responseBody.contains("collegeCode"));
        Assert.assertTrue(responseBody.contains("email"));
    }

    //@MethodObjective-Check if the collegeCode is not accepting any special characters in it.
    @Test(priority=9)
    public void checkCollegeCodeWithSpecialCharacter(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setCollegeCode("@$#");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String collegeName =  jsonPathEvaluator.getString("collegeName");
        Assert.assertTrue(collegeName.equals("College Name Should Be Valid!"));
    }

    //@MethodObjective- Check if the collegeCode is not accepting any alphabets in it.
    @Test(priority = 10)
    public void checkCollegeCodeWithAlphabets(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setCollegeCode("jit");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String collegeCode =  jsonPathEvaluator.getString("collegeCode");
        Assert.assertTrue(collegeCode.equals("CollegeCode Should Be Valid!"));
    }

    //@MethodObjective- Check if type is enum and accepting only private and govt.
    @Test(priority=11)
    public void checkTypeIsPrivate(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setType(Type.PRIVATE);
        addCollegeDo.setCollegeCode("157");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(200,statusCode);
        // JsonPath object created for response
        Response response = getApiResponse.getResponse();
        String responseBody =response.getBody().prettyPrint();
        Assert.assertTrue(responseBody.contains("collegeName"));
        Assert.assertTrue(responseBody.contains("type"));
        Assert.assertTrue(responseBody.contains("email"));
        Assert.assertTrue(responseBody.contains("address"));
        Assert.assertTrue(responseBody.contains("collegeCode"));
        Assert.assertTrue(responseBody.contains("email"));

    }

    //@MethodObjective- Check if type is enum and accepting only private and govt.
    @Test(priority=11)
    public void checkTypeAsGovt(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setType(Type.GOVERNMENT);
        addCollegeDo.setCollegeCode("156");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(200,statusCode);
        // JsonPath object created for response
        Response response = getApiResponse.getResponse();
        String responseBody =response.getBody().prettyPrint();
        Assert.assertTrue(responseBody.contains("collegeName"));
        Assert.assertTrue(responseBody.contains("type"));
        Assert.assertTrue(responseBody.contains("email"));
        Assert.assertTrue(responseBody.contains("address"));
        Assert.assertTrue(responseBody.contains("collegeCode"));
        Assert.assertTrue(responseBody.contains("email"));
    }

    //@MethodObjective-Check if the type is passed as null or empty then whats the response.
    @Test(priority=11)
    public void checkTypeAsNull() {
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setType(Type.EMPTY);
        GetApiResponse getApiResponse = url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400, statusCode);
    }
    //@MethodObjective- Check if the email is not passed then whats the error response.
     @Test(priority=12)
    public void checkEmptyEmailPassed(){
         AddCollegeDo addCollegeDo = new AddCollegeDo();
         addCollegeDo.setEmail("");
        // addCollegeDo.setCollegeCode("179");
         GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
         int statusCode = getApiResponse.getStatusCode();
         Assert.assertEquals(400,statusCode);
         JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
         String email =  jsonPathEvaluator.getString("email");
         Assert.assertTrue(email.equals("Not a valid email!"));
    }

     //@MethodObjective- Check if the email is passed without special character then whats the error response.
     @Test(priority=13)
     public void checkEmailWithoutSpecialChar(){
         AddCollegeDo addCollegeDo = new AddCollegeDo();
         addCollegeDo.setEmail("Mohit99gmail.com");
         GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
         int statusCode = getApiResponse.getStatusCode();
         Assert.assertEquals(400,statusCode);
         JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
         String email =  jsonPathEvaluator.getString("email");
         Assert.assertTrue(email.equals("Not a valid email"));
    }

    //@MethodObjective:-Check if the email is not passed in valid form then whats the error response.
    @Test(priority=13)
    public void checkInvalidEmailPassed(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setEmail("Mohit99@");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String email =  jsonPathEvaluator.getString("email");
        Assert.assertTrue(email.equals("Not a valid email"));
    }

    //@MethodObjective- Check if the state is not accepting other values than null
    @Test(priority =14)
    public void checkStateAsNull() {
        //object of addCollege is created
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        //obj of address is created for passing all its value in addCollegeDo
        AddressDo addressDo = new AddressDo("barud phatha","magriya","khargone",State.EMPTY);
        addCollegeDo.setAddressDoObj(addressDo);
        GetApiResponse getApiResponse = url.setUpForAddCollege(addCollegeDo);
        System.out.println(getApiResponse.getResponse().prettyPrint());
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400, statusCode);
    }

    //@MethodObjective- Check if the state is accepting all the enum defined for the state.
    @Test(priority =15)
    public void checkCorrectState() {
        //object of addCollege is created
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setCollegeCode("201");
        //obj of address is created for passing all its value in addCollegeDo
        AddressDo addressDo = new AddressDo("barud phatha","magriya","khargone",State.ARUNACHAL_PRADESH);
        addCollegeDo.setAddressDoObj(addressDo);
        GetApiResponse getApiResponse = url.setUpForAddCollege(addCollegeDo);
        System.out.println(getApiResponse.getResponse().prettyPrint());
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(200, statusCode);
        // JsonPath object created for response
        Response response = getApiResponse.getResponse();
        String responseBody =response.getBody().prettyPrint();
        Assert.assertTrue(responseBody.contains("collegeName"));
        Assert.assertTrue(responseBody.contains("type"));
        Assert.assertTrue(responseBody.contains("email"));
        Assert.assertTrue(responseBody.contains("address"));
        Assert.assertTrue(responseBody.contains("collegeCode"));
        Assert.assertTrue(responseBody.contains("email"));
    }

   //@methodObjective- Check if in the "address" ,"city" is only accepting alphabets (special characters too).
   @Test(priority =16)
   public void validationForAddressObj() {
       //object of addCollege is created
       AddCollegeDo addCollegeDo = new AddCollegeDo();
       addCollegeDo.setCollegeCode("199");
       //obj of address is created for passing all its value in addCollegeDo
       AddressDo addressDo = new AddressDo("$barud ^phatha","56magr*iya","%khar-gon67e",State.ARUNACHAL_PRADESH);
       addCollegeDo.setAddressDoObj(addressDo);
       GetApiResponse getApiResponse = url.setUpForAddCollege(addCollegeDo);
       //System.out.println(getApiResponse.getResponse().prettyPrint());
       int statusCode = getApiResponse.getStatusCode();
       Assert.assertEquals(200, statusCode);
       // JsonPath object created for response
       Response response = getApiResponse.getResponse();
       String responseBody =response.getBody().prettyPrint();
       Assert.assertTrue(responseBody.contains("collegeName"));
       Assert.assertTrue(responseBody.contains("type"));
       Assert.assertTrue(responseBody.contains("email"));
       Assert.assertTrue(responseBody.contains("address"));
       Assert.assertTrue(responseBody.contains("collegeCode"));
       Assert.assertTrue(responseBody.contains("email"));
   }

   //@MethodObjective-Check if the "contactNumber " is passed invalid then whats the error message.
   @Test(priority=17)
   public void checkEmptyContactNoPassed(){
       AddCollegeDo addCollegeDo = new AddCollegeDo();
       addCollegeDo.setContactNumber("");
       GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
       int statusCode = getApiResponse.getStatusCode();
       Assert.assertEquals(400,statusCode);
       JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
       String contactNumber =  jsonPathEvaluator.getString("contactNumber");
       Assert.assertTrue(contactNumber.equals("Phone Number Should Be Valid!"));
   }

    //@MethodObjective-Check if the "contactNumber " is passed invalid then whats the error message.
    @Test(priority=18)
    public void checkInvalidContactNoValidation(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setContactNumber("0098900002");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String contactNumber =  jsonPathEvaluator.getString("contactNumber");
        Assert.assertTrue(contactNumber.equals("Phone Number Should Be Valid!"));
    }

    //@MethodObjective-Check if the contactNumber is not accepting non-numeric digits.
    @Test(priority=19)
    public void checkStringContactNoValidation(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setContactNumber("hhyuhjj");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String contactNumber =  jsonPathEvaluator.getString("contactNumber");
        Assert.assertTrue(contactNumber.equals("Phone Number Should Be Valid!"));
    }

    //@MethodObjective-Check if the contactNumber is passed more then 10 digits.
    @Test(priority=20)
    public void check10DigitContactNoValidation(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setContactNumber("678989887646778");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String contactNumber =  jsonPathEvaluator.getString("contactNumber");
        Assert.assertTrue(contactNumber.equals("Phone Number Should Be Valid!"));
    }

    //@MethodObjective- Check if the contact number(min) 0000000000000000 is not accepted .
    @Test(priority=21)
    public void checkMinContactNoValidation(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setContactNumber("000000000");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String contactNumber =  jsonPathEvaluator.getString("contactNumber");
        Assert.assertTrue(contactNumber.equals("Phone Number Should Be Valid!"));
    }

    //@MethodObjective- Check if the 11111111111111 contact number(max) not accepted.
    @Test(priority=22)
    public void checkMaxContactNoValidation(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setContactNumber("111111111");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String contactNumber =  jsonPathEvaluator.getString("contactNumber");
        Assert.assertTrue(contactNumber.equals("Phone Number Should Be Valid!"));
    }

    //@MethodObjective- Check if the  contact number starting from 6,7,8,9 are only accepted rest other not.
    @Test(priority=22)
    public void ContactNoValidation(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setContactNumber("4567890900");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String contactNumber =  jsonPathEvaluator.getString("contactNumber");
        Assert.assertTrue(contactNumber.equals("Phone Number Should Be Valid!"));
    }

    //@MethodObjective- Check if the password is accepting alphanumeric and special characters .
    @Test(priority=23)
    public void checkEmptyPasswordValidation(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setPassword("");
        // addCollegeDo.setCollegeCode("179");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String password =  jsonPathEvaluator.getString("password");
        Assert.assertTrue(password.equals("Password Should Be Valid!"));
    }

    //@MethodObjective- Check if the password is accepting alphanumeric and special characters .
    @Test(priority=24)
    public void checkPasswordValidation(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setPassword("Test@123%&*");
        // addCollegeDo.setCollegeCode("179");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String password =  jsonPathEvaluator.getString("password");
        Assert.assertTrue(password.equals("Password Should Be Valid!"));
    }

    //@MethodObjective- Check if the password is not accepting space .
    @Test(priority=25)
    public void checkPasswordValidation2(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setPassword("Test@123  %&*");
        // addCollegeDo.setCollegeCode("179");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String password =  jsonPathEvaluator.getString("password");
        Assert.assertTrue(password.equals("Password Should Be Valid!"));
    }

    //@MethodObjective- Check if the password is not accepting less than 8 characters .
    @Test(priority=26)
    public void checkPasswordMinlength(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setPassword("Test@12");
        // addCollegeDo.setCollegeCode("179");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String password =  jsonPathEvaluator.getString("password");
        Assert.assertTrue(password.equals("Password Should Be Valid!"));
    }

    //@MethodObjective- Check if the password is  accepting upto 10 digits of characters.
    @Test(priority=27)
    public void checkPasswordMaxlength(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setPassword("Test@12787");
         addCollegeDo.setCollegeCode("1112");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(200,statusCode);
        // JsonPath object created for response
        Response response = getApiResponse.getResponse();
        String responseBody =response.getBody().prettyPrint();
        Assert.assertTrue(responseBody.contains("collegeName"));
        Assert.assertTrue(responseBody.contains("type"));
        Assert.assertTrue(responseBody.contains("email"));
        Assert.assertTrue(responseBody.contains("address"));
        Assert.assertTrue(responseBody.contains("collegeCode"));
        Assert.assertTrue(responseBody.contains("email"));
    }

    //@MethodObjective-Check if the password is not displayed in response.
    @Test(priority=28)
    public void checkPasswordDisplayedInResponse(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setCollegeCode("1114");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(200,statusCode);
        // JsonPath object created for response
        Response response = getApiResponse.getResponse();
        String responseBody =response.getBody().prettyPrint();
        Assert.assertFalse(responseBody.contains("password"));
    }

    //@MethodObjective- Check if the password is not accepting less than 8 characters .
    @Test(priority=29)
    public void checkPasswordWithSpace(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setPassword("Test@12 78");
        // addCollegeDo.setCollegeCode("179");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String password =  jsonPathEvaluator.getString("password");
        Assert.assertTrue(password.equals("Password Should Be Valid!"));
    }

    //@MethodObjective-Check if null college code is passed then error message is displayed or not.Error message shouldn't change after sending request again and again.
    @Test(priority=30)
    public void checkNullCollegeCode(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        addCollegeDo.setCollegeCode("jit");
        GetApiResponse getApiResponse =url.setUpForAddCollege(addCollegeDo);
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = getApiResponse.getResponse().jsonPath();
        String collegeCode =  jsonPathEvaluator.getString("collegeCode");
        Assert.assertTrue(collegeCode.equals("CollegeCode Should Be Valid!"));
        //sending request multiple times
        addCollegeDo.setCollegeCode("jit");
        GetApiResponse getApiResponse2 =url.setUpForAddCollege(addCollegeDo);
        int statusCode2 = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode2);
        JsonPath jsonPathEvaluator2 = getApiResponse.getResponse().jsonPath();
        String collegeCode2 =  jsonPathEvaluator.getString("collegeCode");
        Assert.assertTrue(collegeCode2.equals("CollegeCode Should Be Valid!"));

        addCollegeDo.setCollegeCode("&*");
        GetApiResponse getApiResponse3 =url.setUpForAddCollege(addCollegeDo);
        int statusCode3 = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode3);
        JsonPath jsonPathEvaluator3 = getApiResponse.getResponse().jsonPath();
        String collegeCode3 =  jsonPathEvaluator3.getString("collegeCode");
        Assert.assertTrue(collegeCode3.equals("CollegeCode Should Be Valid!"));

        addCollegeDo.setCollegeCode("test");
        GetApiResponse getApiResponse4 =url.setUpForAddCollege(addCollegeDo);
        int statusCode4 = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode4);
        JsonPath jsonPathEvaluator4 = getApiResponse.getResponse().jsonPath();
        String collegeCode4 =  jsonPathEvaluator.getString("collegeCode");
        Assert.assertTrue(collegeCode4.equals("CollegeCode Should Be Valid!"));

        addCollegeDo.setCollegeCode("verified");
        GetApiResponse getApiResponse5 =url.setUpForAddCollege(addCollegeDo);
        int statusCode5 = getApiResponse.getStatusCode();
        Assert.assertEquals(400,statusCode5);
        JsonPath jsonPathEvaluator5 = getApiResponse.getResponse().jsonPath();
        String collegeCode5 =  jsonPathEvaluator5.getString("collegeCode");
        Assert.assertTrue(collegeCode5.equals("CollegeCode Should Be Valid!"));
    }

    //@MethodObjective-Changes in the IP address in the url should not allow to send request
    @Test(priority=31)
    public void checkUrlChangeValidation(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/test/v1/campus/college";
        RequestSpecification httprequest = RestAssured.given();
        RequestParam RequestParamsObj = new RequestParam();
        JSONObject requestParam = RequestParamsObj.getRequestParamForAddCollege(addCollegeDo);
        // to convert in string from json
        httprequest.body(requestParam.toJSONString());
        // passing content type in header
        httprequest.header("Content-Type", "application/json");
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.POST);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(404,statusCode);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String error =  jsonPathEvaluator.getString("error");
        Assert.assertTrue(error.equals("Not Found"));
    }

    //@MethodObjective-Changes in the IP address in the url from api to API which should not allow to send request
    @Test(priority=32)
    public void checkUrlChangeValidation2(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/API/v1/campus/college";
        RequestSpecification httprequest = RestAssured.given();
        RequestParam RequestParamsObj = new RequestParam();
        JSONObject requestParam = RequestParamsObj.getRequestParamForAddCollege(addCollegeDo);
        // to convert in string from json
        httprequest.body(requestParam.toJSONString());
        // passing content type in header
        httprequest.header("Content-Type", "application/json");
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.POST);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(404,statusCode);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String error =  jsonPathEvaluator.getString("error");
        Assert.assertTrue(error.equals("Not Found"));
    }


    //@MethodObjective-Changes in the IP address in the url from api to API which should not allow to send request
    @Test(priority=33)
    public void checkMethodChangeValidation(){
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/api/v1/campus/college";
        RequestSpecification httprequest = RestAssured.given();
        RequestParam RequestParamsObj = new RequestParam();
        JSONObject requestParam = RequestParamsObj.getRequestParamForAddCollege(addCollegeDo);
        // to convert in string from json
        httprequest.body(requestParam.toJSONString());
        // passing content type in header
        httprequest.header("Content-Type", "application/json");
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.PATCH);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(405,statusCode);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String error =  jsonPathEvaluator.getString("error");
        Assert.assertTrue(error.equals("Method Not Allowed"));
    }

    //@MethodObjective- Validate that on adding the college its detais are added
    // successfully and on geting the id with same college code its same as data added.
    @Test(priority=34)
    public void validateResponseBody() {
           //college added for college code
            AddCollegeDo addCollegeDo = new AddCollegeDo();
            //college code is unique so it need to be change everytime
            addCollegeDo.setCollegeCode("1125");
            GetApiResponse response = url.setUpForAddCollege(addCollegeDo);
            int statusCode = response.getStatusCode();
            Assert.assertEquals(statusCode,200);
            String statusLine= response.getStatusLine();
            Assert.assertEquals(statusLine,"HTTP/1.1 200 ");

        //geting the college details for the college added for checking data added is correct or not
        //object created of getApiResponse and pass in method id
        GetApiResponse getApiResponse = url.setUpForGetCollegeCode("1125");
        //status code is 200 passed
        int statusCode2 = getApiResponse.getStatusCode();
        Assert.assertEquals(statusCode2, 200);
        // Get the status line from the Response in a variable called statusLine
        String statusLine2 = getApiResponse.getStatusLine();
        Assert.assertEquals(statusLine2, "HTTP/1.1 200 ");

        //creating object of response and storing from the method response
        Response response2 = getApiResponse.getResponse();
        // JsonPath object created for response for geting JSON representation from response body
        JsonPath jsonPathEvauator = response2.jsonPath();
        // message response validate for expected values
        String collegeCode = jsonPathEvauator.getString("collegeCode");
        //validating code is 119 for whom we passed to get details
        Assert.assertTrue(collegeCode.contains("1125"));

        //validate other fields
        String collegeName =jsonPathEvauator.getString("collegeName");
        Assert.assertEquals(collegeName,"Imperial College Of Engineering");
        String type = jsonPathEvauator.getString("type");
        Assert.assertTrue(type.equals("PRIVATE"));
        String approvedBy = jsonPathEvauator.getString("approvedBy");
        System.out.println(approvedBy);
        Assert.assertEquals(approvedBy, null);
        String city = jsonPathEvauator.getString("city");
        Assert.assertEquals(city, null);
        String overview = jsonPathEvauator.getString("overview");
        Assert.assertEquals(overview, null);
        String awards = jsonPathEvauator.getString("awards");
        Assert.assertEquals(awards, null);
        String established = jsonPathEvauator.getString("established");
        Assert.assertEquals(established, null);
        String ranking = jsonPathEvauator.getString("ranking");
        Assert.assertEquals(ranking, "0");
        String contactNumber = jsonPathEvauator.getString("contactNumber");
        Assert.assertTrue(contactNumber.equals("8989098099"));
        String email = jsonPathEvauator.getString("email");
        Assert.assertTrue(email.equals("mohit88@gmail.com"));
        String mapLocation = jsonPathEvauator.getString("mapLocation");
        Assert.assertEquals(mapLocation, null);
        String responseBody = response2.getBody().prettyPrint();
        Assert.assertTrue(responseBody.contains("address"));
        String verified = jsonPathEvauator.getString("verified");
        Assert.assertEquals(verified, null);
        String facilities = jsonPathEvauator.getString("facilities");
        Assert.assertEquals(facilities, null);
        String noOfStudents = jsonPathEvauator.getString("noOfStudents");
        Assert.assertEquals(noOfStudents, "0");
        String courses = jsonPathEvauator.getString("courses");
        Assert.assertEquals(courses, null);
        String images = jsonPathEvauator.getString("images");
        Assert.assertEquals(images, null);
        String videos = jsonPathEvauator.getString("videos");
        Assert.assertEquals(videos, null);
        String placements = jsonPathEvauator.getString("placements");
        Assert.assertEquals(placements, null);
        String examsAccepted = jsonPathEvauator.getString("examsAccepted");
        Assert.assertEquals(examsAccepted, null);
        String collegeStatus = jsonPathEvauator.getString("collegeStatus");
        Assert.assertEquals(collegeStatus, null);
        String website = jsonPathEvauator.getString("website");
        Assert.assertEquals(website, null);
        String profileImage = jsonPathEvauator.getString("profileImage");
        Assert.assertEquals(profileImage, null);
        String placedStudents = jsonPathEvauator.getString("placedStudents");
        Assert.assertEquals(placedStudents, "0");
    }
}
