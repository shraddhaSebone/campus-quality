package com.sebone.main;

import com.sebone.main.college.SetupForCollege;
import com.sebone.main.data.*;
import com.sebone.main.requestparams.RequestParamForUpdateAddress;
import com.sebone.main.response.GetApiResponse;
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

public class UpdateAddressAutomationTest {
    /* @className- UpdateAddressAutomationTest
     * @autherName- varsha Rane
     * @Objective - to automate the testcases for the updateAddress api
     */
    private SetupForCollege url;

    //@MethodObjective- to execute before class
    @BeforeClass
    public void url() {

        url = new SetupForCollege();
    }

    // @MethodObjective- the objective of this method is to test the status code is 200 and statusline for the successfully
    // adding the contact info on passing correct values and message is accepting alphanumeric and special characters.
    @Test(priority = 1)
    public void checkUpdateAddress(){
        AddressDo addressDo = new AddressDo();
        addressDo.setAddressLine("Borawan");
        addressDo.setLandmark("jawahar marg");
        addressDo.setCity("Khargone");
        addressDo.setState(State.HIMACHAL_PRADESH);
        GetApiResponse response = url.setUpForUpdateAddress(addressDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 200 ");
        JsonPath jsonpathEvaluator  = response.getResponse().jsonPath();
        String addressLine = jsonpathEvaluator.getString("addressLine");
        Assert.assertEquals(addressLine,"Borawan");
        String landmark = jsonpathEvaluator.getString("landmark");
        Assert.assertEquals(landmark,"jawahar marg");
        String city = jsonpathEvaluator.getString("city");
        Assert.assertEquals(city,"Khargone");
        String state = jsonpathEvaluator.getString("state");
        Assert.assertEquals(state,"HIMACHAL_PRADESH");

    }

    //@MethodObjective-Changes in the IP address in the url should not allow to send request
    @Test(priority = 2)
    public void checkUrlChangeValidation() {
        AddressDo addressDo = new AddressDo();
        //Specify the url
        RestAssured.baseURI = "http://13.232.186.165:8080/Test/v1/campus/college/updateAddress/111";
        RequestSpecification httprequest = RestAssured.given();
        RequestParamForUpdateAddress requestParamForUpdateAddress = new RequestParamForUpdateAddress();
        JSONObject requestParam = requestParamForUpdateAddress.getRequestParamForUpdateAddress(addressDo);
        // to convert in string from json
        httprequest.body(requestParam.toJSONString());
        // passing content type in header
        httprequest.header("Content-Type", "application/json");
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.POST);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(404, statusCode);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String error = jsonPathEvaluator.getString("error");
        Assert.assertTrue(error.equals("Not Found"));
    }

    //@MethodObjective-Changes in the IP address in the url from api to API which should not allow to send request
    @Test(priority = 3)
    public void checkUrlChangeValidation2() {
        AddressDo addressDo = new AddressDo();
        //Specify the url
        RestAssured.baseURI = "http://13.232.186.165:8080/API/v1/campus/college/updateAddress/111";
        RequestSpecification httprequest = RestAssured.given();
        RequestParamForUpdateAddress requestParamForUpdateAddress = new RequestParamForUpdateAddress();
        JSONObject requestParam = requestParamForUpdateAddress.getRequestParamForUpdateAddress(addressDo);
        // to convert in string from json
        httprequest.body(requestParam.toJSONString());
        // passing content type in header
        httprequest.header("Content-Type", "application/json");
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.POST);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(404, statusCode);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String error = jsonPathEvaluator.getString("error");
        Assert.assertTrue(error.equals("Not Found"));
    }

    //@MethodObjective-Changes in the method which will not allow to send request and give 405 error message.
    @Test(priority = 4)
    public void checkMethodChangeValidation() {
        AddressDo addressDo = new AddressDo();
        //Specify the url
        RestAssured.baseURI = "http://13.232.186.165:8080/api/v1/campus/college/updateAddress/111";
        RequestSpecification httprequest = RestAssured.given();
        RequestParamForUpdateAddress requestParamForUpdateAddress = new RequestParamForUpdateAddress();
        JSONObject requestParam = requestParamForUpdateAddress.getRequestParamForUpdateAddress(addressDo);
        // to convert in string from json
        httprequest.body(requestParam.toJSONString());
        // passing content type in header
        httprequest.header("Content-Type", "application/json");
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.PUT);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(405, statusCode);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String error = jsonPathEvaluator.getString("error");
        Assert.assertTrue(error.equals("Method Not Allowed"));
    }

    //@MethodObjective- Check if the content -type is application json or not.
    @Test(priority = 5)
    public void verifyContentType(){
        AddressDo addressDo = new AddressDo();
        GetApiResponse response = url.setUpForUpdateAddress(addressDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        //after checking api is 200 response validate that for the correct request its content -type passed was application-json
        // access header and validate it
        String contentType = response.getResponse().header("Content-Type");
        // assertion for contentType
        Assert.assertEquals(contentType, "application/json");
    }

    // @MethodObjective= testing the response time of the College api which should be less than 1 sec.
    @Test(priority = 6)
    public void verifyResponseTime() {
        AddressDo addressDo = new AddressDo();
        GetApiResponse response = url.setUpForUpdateAddress(addressDo,"111");
        // obtain ValidatableResponse type
        ValidatableResponse resType = response.getResponse().then();
        // verify response time lesser than 1000 milliseconds
        resType.time(Matchers.lessThan(1000L));
    }

    //@MethodObjective- Check that on passing empty addressLine error message is displayed correctly or not.
    @Test(priority=7)
    public  void checkValidationForAddressline(){
        AddressDo addressDo = new AddressDo();
        addressDo.setAddressLine("");
        GetApiResponse response =url.setUpForUpdateAddress(addressDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(400,statusCode);
        JsonPath jsonPathEvaluator = response.getResponse().jsonPath();
        String addressLine = jsonPathEvaluator.getString("addressLine");
        Assert.assertEquals(addressLine,"must not be empty");
    }

    //@MethodObjective-Check that addressLine, landmark, city is accepting alphanumeric charcters and special characters.
    @Test(priority =8)
    public  void checkAddresslineAcceptsAllChars() {
        AddressDo addressDo = new AddressDo();
        addressDo.setAddressLine("Shri hari vitthal mandir ,#3rd cross block B");
        addressDo.setCity("khargone 5$#0");
        addressDo.setLandmark("infront of gurukul school ,3rd buliding infront of cahmundi anaxe.");
        GetApiResponse response = url.setUpForUpdateAddress(addressDo, "111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
    }

    //@MethodObjective- Check that city ,landmark is not having validation and can add empty landmark successfully or not.
    @Test(priority =9)
    public  void checkEmptyfieldsAddableOrNot() {
        AddressDo addressDo = new AddressDo();
        addressDo.setCity("");
        addressDo.setLandmark("");
        GetApiResponse response = url.setUpForUpdateAddress(addressDo, "111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
    }

    //@MethodObjective-Check if the city, addressline and landmark is accepting alphanumeric char
    // or not with lowercase upercase or camal case.
    @Test(priority =10)
    public  void checkCamelCaseAcceptance() {
        AddressDo addressDo = new AddressDo();
        addressDo.setCity("Khargone MP");
        addressDo.setLandmark("Shri hari vitthal mandir");
        addressDo.setAddressLine("RamanNand stop");
        GetApiResponse response = url.setUpForUpdateAddress(addressDo, "111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
    }

    //@MethodObjective-Check that in address  all fields are updated on updating and displayed in response.
    @Test(priority =11)
    public  void checkAddressUpdated() {
        AddressDo addressDo = new AddressDo();
        //added address assertion
        addressDo.setAddressLine("Borawan");
        addressDo.setLandmark("jawahar marg");
        addressDo.setCity("Khargone");
        addressDo.setState(State.HIMACHAL_PRADESH);
        GetApiResponse response = url.setUpForUpdateAddress(addressDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 200 ");
        JsonPath jsonpathEvaluator  = response.getResponse().jsonPath();
        String addressLine = jsonpathEvaluator.getString("addressLine");
        Assert.assertEquals(addressLine,"Borawan");
        String landmark = jsonpathEvaluator.getString("landmark");
        Assert.assertEquals(landmark,"jawahar marg");
        String city = jsonpathEvaluator.getString("city");
        Assert.assertEquals(city,"Khargone");
        String state = jsonpathEvaluator.getString("state");
        Assert.assertEquals(state,"HIMACHAL_PRADESH");

        //assertion for the updated address
        //added address assertion
        addressDo.setAddressLine("near mega more store");
        addressDo.setLandmark("katra");
        addressDo.setCity("Jalandar");
        addressDo.setState(State.PUNJAB);
        GetApiResponse response2 = url.setUpForUpdateAddress(addressDo,"111");
        System.out.println(response2.getResponse().prettyPrint());
        int statusCode2 = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        String statusLine2= response2.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 200 ");
        JsonPath jsonpathEvaluator2  = response2.getResponse().jsonPath();
        String addressLine2 = jsonpathEvaluator2.getString("addressLine");
        Assert.assertEquals(addressLine2,"near mega more store");
        String landmark2 = jsonpathEvaluator2.getString("landmark");
        Assert.assertEquals(landmark2,"katra");
        String city2 = jsonpathEvaluator2.getString("city");
        Assert.assertEquals(city2,"Jalandar");
        String state2 = jsonpathEvaluator2.getString("state");
        Assert.assertEquals(state2,"PUNJAB");
    }

    //@MethodObjective-Check on adding new college then updating its address details
    // then get it by getCollegeByCollegeCode and verify it that details are updated in response.
    @Test(priority = 12)
    public void checkEndToEndTesting(){
        //college is added
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        //college code is unique so it need to be change everytime
        addCollegeDo.setCollegeCode("6601");
        addCollegeDo.setEmail("Shraddha99@gmail.com");
        GetApiResponse response = url.setUpForAddCollege(addCollegeDo);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 200 ");

        AddressDo addressDo = new AddressDo();
        addressDo.setAddressLine("Borawan");
        addressDo.setLandmark("jawahar marg");
        addressDo.setCity("Khargone");
        addressDo.setState(State.HIMACHAL_PRADESH);
        GetApiResponse response2 = url.setUpForUpdateAddress(addressDo,"111");
        int statusCode2 = response2.getStatusCode();
        Assert.assertEquals(statusCode2,200);
        String statusLine2= response.getStatusLine();
        Assert.assertEquals(statusLine2,"HTTP/1.1 200 ");

        //update address for the added college
        addressDo.setAddressLine("Borawan");
        addressDo.setLandmark("jawahar marg");
        addressDo.setCity("Khargone");
        addressDo.setState(State.HIMACHAL_PRADESH);
        JsonPath jsonpathEvaluator2  = response2.getResponse().jsonPath();
        String addressLine2 = jsonpathEvaluator2.getString("addressLine");
        Assert.assertEquals(addressLine2,"Borawan");
        String landmark2 = jsonpathEvaluator2.getString("landmark");
        Assert.assertEquals(landmark2,"jawahar marg");
        String city2 = jsonpathEvaluator2.getString("city");
        Assert.assertEquals(city2,"Khargone");
        String state2 = jsonpathEvaluator2.getString("state");
        Assert.assertEquals(state2,"HIMACHAL_PRADESH");
    }

    //@MethodObjective- check on passing invalid collegeCode ewhats the error response
    @Test(priority = 13)
    public void checkInvalidCodePassedResponse(){
        AddressDo addressDo = new AddressDo();
        GetApiResponse response = url.setUpForUpdateAddress(addressDo,"90");
        System.out.println(response.getResponse().prettyPrint());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,404);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 404 ");
    }

    //@MethodObjective- check on passing String collegeCode whats the error response
    @Test(priority =14)
    public void checkStringCodePassedResponse() {
        AddressDo addressDo = new AddressDo();
        GetApiResponse response = url.setUpForUpdateAddress(addressDo, "Test");
        System.out.println(response.getResponse().prettyPrint());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 404 ");
    }
}
