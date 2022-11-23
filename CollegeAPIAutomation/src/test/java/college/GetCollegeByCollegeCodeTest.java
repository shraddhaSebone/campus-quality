package college;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetCollegeByCollegeCodeTest {
    /*
     * @autherName- varsha rane
     * @className- GetCollegeByCollegeCodeTest
     * @Objective- to automate the testcases for the GetCollegeByCollegeCode api.
     */

    private SetupForCollege url;

    //@MethodObjective- url to execute before class.
    @BeforeClass
    public void url() {

        url = new SetupForCollege();
    }
    //@MethodObjective- to verify the status code is "200" and response .
    @Test(priority=1)
    public void verifyCorrectCodeResponse() {
        //object created of getApiResponse and pass in method code
        GetApiResponse getApiResponse = url.setUpForGetCollegeCode("119");
        //status code is 200 passed
        int statusCode =  getApiResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        // Get the status line from the Response in a variable called statusLine
        String statusLine = getApiResponse.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 200 ");

        //creating object of response and storing from the method response
        Response response = getApiResponse.getResponse();
        // JsonPath object created for response for geting JSON representation from response body
        JsonPath jsonPathEvauator = response.jsonPath();
        // message response validate for expected values
        String collegeCode = jsonPathEvauator.getString("collegeCode");
       //validating code is 119 for whom we passed to get details
        Assert.assertTrue(collegeCode.contains("119"));
        //validate other fields
        String collegeName =jsonPathEvauator.getString("collegeName");
        Assert.assertEquals(collegeName,"Imperial College Of Engineering");
        String type =jsonPathEvauator.getString("type");
        Assert.assertTrue(type.equals("PRIVATE"));
        String approvedBy =jsonPathEvauator.getString("approvedBy");
        System.out.println(approvedBy);
        Assert.assertEquals(approvedBy, null);
        String city =jsonPathEvauator.getString("city");
        Assert.assertEquals(city,null);
        String overview =jsonPathEvauator.getString("overview");
        Assert.assertEquals(overview ,null);
        String awards =jsonPathEvauator.getString("awards");
        Assert.assertEquals(awards,null);
        String established =jsonPathEvauator.getString("established");
        Assert.assertEquals(established,null);
        String ranking =jsonPathEvauator.getString("ranking");
        Assert.assertEquals(ranking,"0");
        String contactNumber =jsonPathEvauator.getString("contactNumber");
        Assert.assertTrue(contactNumber.equals("8989098099"));
        String email =jsonPathEvauator.getString("email");
        Assert.assertTrue(email.equals("mohit88@gmail.com"));
        String mapLocation =jsonPathEvauator.getString("mapLocation");
        Assert.assertEquals(mapLocation ,null);
        String responseBody =response.getBody().prettyPrint();
        Assert.assertTrue(responseBody.contains("address"));
        String verified =jsonPathEvauator.getString("verified");
        Assert.assertEquals(verified,null);
        String facilities =jsonPathEvauator.getString("facilities");
        Assert.assertEquals(facilities,null);
        String noOfStudents =jsonPathEvauator.getString("noOfStudents");
        Assert.assertEquals(noOfStudents,"0");
        String courses =jsonPathEvauator.getString("courses");
        Assert.assertEquals(courses,null);
        String images =jsonPathEvauator.getString("images");
        Assert.assertEquals(images,null);
        String videos =jsonPathEvauator.getString("videos");
        Assert.assertEquals(videos,null);
        String placements =jsonPathEvauator.getString("placements");
        Assert.assertEquals(placements,null);
        String examsAccepted =jsonPathEvauator.getString("examsAccepted");
        Assert.assertEquals(examsAccepted,null);
        String collegeStatus =jsonPathEvauator.getString("collegeStatus");
        Assert.assertEquals(collegeStatus,null);
        String website =jsonPathEvauator.getString("website");
        Assert.assertEquals(website,null);
        String profileImage =jsonPathEvauator.getString("profileImage");
        Assert.assertEquals(profileImage,null);
        String placedStudents =jsonPathEvauator.getString("placedStudents");
        Assert.assertEquals(placedStudents,"0");

    }

    //@MethodObjective-Check the content -type is application json or not.
    @Test(priority=2)
    public void checkContentType(){
        GetApiResponse getApiResponse = url.setUpForGetCollegeCode("119");
        Response response = getApiResponse.getResponse();
        String contentType = response.header("Content-Type");
        // assertion for contentType
        Assert.assertEquals(contentType, "application/json");
    }

   //@MethodObjective= Response time of the api is less then 1000 ms.
    @Test(priority=3)
    public void checkResponseTime(){
        GetApiResponse response = url.setUpForGetCollegeCode("1111");
        // obtain ValidatableResponse type
        ValidatableResponse resType = response.getResponse().then();
        // verify response time lesser than 1000 milliseconds
        resType.time(Matchers.lessThan(1000L));
    }

    //@MethodObjective- Check that on adding college by using addCollege that
    //college is displayed or not for passing that invalid college code.
   @Test(priority=4)
    public void checkInvalidCodeResponse(){
     GetApiResponse apiResponse = url.setUpForGetCollegeCode("11");
     int statusCode = apiResponse.getStatusCode();
     Assert.assertEquals(statusCode,404);
       // Get the status line from the Response in a variable called statusLine
     String statusLine = apiResponse.getStatusLine();
     Assert.assertEquals(statusLine,"HTTP/1.1 404 ");
       //creating object of response and storing from the method response
       Response response = apiResponse.getResponse();
       // JsonPath object created for response for geting JSON representation from response body
       JsonPath jsonPathEvauator = response.jsonPath();
       // message response validate for expected values
       String errorCode = jsonPathEvauator.getString("errorCode");
       Assert.assertEquals(errorCode,"NOT_FOUND");
       String errorMessage = jsonPathEvauator.getString("errorMessage");
       Assert.assertEquals(errorMessage,"Op's something went wrong, Don't worry we are figuring out");
       String detailedMessage = jsonPathEvauator.getString("detailedMessage");
       Assert.assertEquals(detailedMessage,"No value present");
   }

    //@MethodObjective- to verify the status code is "200" and statusline is "HTTP/1.1 200 "
    // for the correct college code after adding college and then get it with getcollegeByCollegeCode for same college code.
    @Test(priority=5)
    public void validateResponseBody() {
        //college added for college code
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        //college code is unique so it need to be change everytime
        addCollegeDo.setCollegeCode("1127");
        GetApiResponse response = url.setUpForAddCollege(addCollegeDo);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 200 ");

        //geting the college details for the college added for checking data added is correct or not
        //object created of getApiResponse and pass in method id
        GetApiResponse getApiResponse = url.setUpForGetCollegeCode("1127");
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
        //validating code is 1127 for whom we passed to get details
        Assert.assertTrue(collegeCode.contains("1127"));

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

    //@MethodObjective -Check that on passing the same college code in alphabetic form whats the response.
    @Test(priority=6)
    public void validateAlphabeticCode(){
        GetApiResponse apiResponse = url.setUpForGetCollegeCode("test11");
        int statusCode = apiResponse.getStatusCode();
        Assert.assertEquals(statusCode,404);
        // Get the status line from the Response in a variable called statusLine
        String statusLine = apiResponse.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 404 ");
        //creating object of response and storing from the method response
        Response response = apiResponse.getResponse();
        // JsonPath object created for response for geting JSON representation from response body
        JsonPath jsonPathEvauator = response.jsonPath();
        // message response validate for expected values
        String errorCode = jsonPathEvauator.getString("errorCode");
        Assert.assertEquals(errorCode,"NOT_FOUND");
        String errorMessage = jsonPathEvauator.getString("errorMessage");
        Assert.assertEquals(errorMessage,"Op's something went wrong, Don't worry we are figuring out");
        String detailedMessage = jsonPathEvauator.getString("detailedMessage");
        Assert.assertEquals(detailedMessage,"No value present");
    }

    //@MethodObjective-Check on passing null college code whats the response.
    @Test(priority=7)
    public void checkNullCodeResponse(){
        GetApiResponse apiResponse = url.setUpForGetCollegeCode("null");
        int statusCode = apiResponse.getStatusCode();
        Assert.assertEquals(statusCode,404);
        // Get the status line from the Response in a variable called statusLine
        String statusLine = apiResponse.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 404 ");
        //creating object of response and storing from the method response
        Response response = apiResponse.getResponse();
        // JsonPath object created for response for geting JSON representation from response body
        JsonPath jsonPathEvauator = response.jsonPath();
        // message response validate for expected values
        String errorCode = jsonPathEvauator.getString("errorCode");
        Assert.assertEquals(errorCode,"NOT_FOUND");
        String errorMessage = jsonPathEvauator.getString("errorMessage");
        Assert.assertEquals(errorMessage,"Op's something went wrong, Don't worry we are figuring out");
        String detailedMessage = jsonPathEvauator.getString("detailedMessage");
        Assert.assertEquals(detailedMessage,"No value present");
    }

    //@MethodObjective-Check on passing college code in "" whats the response.
    @Test(priority=8)
    public void checkDoubleQuotesCodePassedResponse(){
        GetApiResponse apiResponse = url.setUpForGetCollegeCode("%22119%22");
        int statusCode = apiResponse.getStatusCode();
        Assert.assertEquals(statusCode,404);
        // Get the status line from the Response in a variable called statusLine
        String statusLine = apiResponse.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 404 ");
        //creating object of response and storing from the method response
        Response response = apiResponse.getResponse();
        // JsonPath object created for response for geting JSON representation from response body
        JsonPath jsonPathEvauator = response.jsonPath();
        // message response validate for expected values
        String errorCode = jsonPathEvauator.getString("errorCode");
        Assert.assertEquals(errorCode,"NOT_FOUND");
        String errorMessage = jsonPathEvauator.getString("errorMessage");
        Assert.assertEquals(errorMessage,"Op's something went wrong, Don't worry we are figuring out");
        String detailedMessage = jsonPathEvauator.getString("detailedMessage");
        Assert.assertEquals(detailedMessage,"No value present");
    }

    //@MethodObjective- Check on passing college code with special character then whats the response.
    @Test(priority=8)
    public void checkSpecialCharCodeResponse(){
        GetApiResponse apiResponse = url.setUpForGetCollegeCode("&^112");
        int statusCode = apiResponse.getStatusCode();
        Assert.assertEquals(statusCode,404);
        // Get the status line from the Response in a variable called statusLine
        String statusLine = apiResponse.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 404 ");
        //creating object of response and storing from the method response
        Response response = apiResponse.getResponse();
        // JsonPath object created for response for geting JSON representation from response body
        JsonPath jsonPathEvauator = response.jsonPath();
        // message response validate for expected values
        String errorCode = jsonPathEvauator.getString("errorCode");
        Assert.assertEquals(errorCode,"NOT_FOUND");
        String errorMessage = jsonPathEvauator.getString("errorMessage");
        Assert.assertEquals(errorMessage,"Op's something went wrong, Don't worry we are figuring out");
        String detailedMessage = jsonPathEvauator.getString("detailedMessage");
        Assert.assertEquals(detailedMessage,"No value present");
    }

    //@MethodObjective-Check if there is change in end point of the url in uppercase
    // then correct error message is showing or not(intead of api  use API)
    @Test(priority=9)
    public void checkUrlChangeResponse(){
        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/API/v1/campus/college/collegeCode";
        RequestSpecification httprequest = RestAssured.given();
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.GET,"125");
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode,404);
        // Get the status line from the Response in a variable called statusLine
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 404 ");
        // JsonPath object created for response for geting JSON representation from response body
        JsonPath jsonPathEvauator = response.jsonPath();
        // message response validate for expected values
        String error = jsonPathEvauator.getString("error");
        Assert.assertEquals(error,"Not Found");
        String errorMessage = jsonPathEvauator.getString("status");
        Assert.assertEquals(errorMessage,"404");
        String path = jsonPathEvauator.getString("path");
        Assert.assertEquals(path,"/API/v1/campus/college/collegeCode/125");
    }

    //@MethodObjective-Change the method type from get to post and check if status code is correct or not.
    @Test(priority=10)
    public void checkInvalidUrlResponse(){
        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/test/v1/campus/college/collegeCode";
        RequestSpecification httprequest = RestAssured.given();
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.GET,"125");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,404);
        // Get the status line from the Response in a variable called statusLine
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 404 ");
        // JsonPath object created for response for geting JSON representation from response body
        JsonPath jsonPathEvauator = response.jsonPath();
        // message response validate for expected values
        String error = jsonPathEvauator.getString("error");
        Assert.assertEquals(error,"Not Found");
        String errorMessage = jsonPathEvauator.getString("status");
        Assert.assertEquals(errorMessage,"404");
        String path = jsonPathEvauator.getString("path");
        Assert.assertEquals(path,"/test/v1/campus/college/collegeCode/125");
    }

    //@MethodObjective- Verify that change the method type from get to post and check if status code is correct or not.
    @Test(priority=11)
    public void changeInMethodResponse(){
        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/api/v1/campus/college/collegeCode";
        RequestSpecification httprequest = RestAssured.given();
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.POST,"125");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,405);
        // Get the status line from the Response in a variable called statusLine
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 405 ");
        // JsonPath object created for response for geting JSON representation from response body
        JsonPath jsonPathEvauator = response.jsonPath();
        // message response validate for expected values
        String error = jsonPathEvauator.getString("error");
        Assert.assertEquals(error,"Method Not Allowed");
        String errorMessage = jsonPathEvauator.getString("status");
        Assert.assertEquals(errorMessage,"405");
        String path = jsonPathEvauator.getString("path");
        Assert.assertEquals(path,"/api/v1/campus/college/collegeCode/125");
    }
}
