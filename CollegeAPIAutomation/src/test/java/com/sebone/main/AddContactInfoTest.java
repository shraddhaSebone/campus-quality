package com.sebone.main;

import com.sebone.main.college.SetupForCollege;
import com.sebone.main.data.AddCollegeDo;
import com.sebone.main.data.AddContactDo;
import com.sebone.main.requestparams.RequestParamForAddContactInfo;
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

import java.io.Serializable;

import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class AddContactInfoTest implements Serializable {
    /* @className- AddContactInfoTest
     * @autherName- varsha Rane
     * @Objective - to automate the testcases for the addContactInfo api
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
    public void verifyAddContact(){
        AddContactDo addContactDo = new AddContactDo();
        //college code is unique so it need to be change everytime
        addContactDo.setFirstName("Shreya");
        addContactDo.setEmail("varrane99@gmail.com");
        addContactDo.setLastName("Paliwal");
        addContactDo.setContactNumber("8425970700");
        addContactDo.setMessage("For Khandwa #$% 123 college");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
        System.out.println(response.getResponse().prettyPrint());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 200 ");
    }

    //@MethodObjective- the objective of this method is to test that content-type is application-json
    @Test(priority = 2)
    public void verifyContentType(){
        AddContactDo addContactDo = new AddContactDo();
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
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
        AddContactDo addContactDo = new AddContactDo();
        GetApiResponse getApiResponse = url.setUpForAddContactInfo(addContactDo,"111");
        // obtain ValidatableResponse type
        ValidatableResponse resType = getApiResponse.getResponse().then();
        // verify response time lesser than 1000 milliseconds
        resType.time(Matchers.lessThan(1000L));
    }

    //@MethodObjective-Changes in  url should not allow to send request
    @Test(priority=4)
    public void checkUrlChangeValidation(){
        AddContactDo addContactDo = new AddContactDo();
        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/API/v1/campus/college/addContactInfo/111";
        RequestSpecification httprequest = RestAssured.given();
        RequestParamForAddContactInfo requestParamForAddContactInfo = new RequestParamForAddContactInfo();
        JSONObject requestParam = requestParamForAddContactInfo.getRequestParamForAddContactInfo(addContactDo);
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
    @Test(priority=5)
    public void checkUrlChangeValidation2(){
        AddContactDo addContactDo = new AddContactDo();
        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/API/v1/campus/college/addContactInfo/111";
        RequestSpecification httprequest = RestAssured.given();
        RequestParamForAddContactInfo requestParamForAddContactInfo = new RequestParamForAddContactInfo();
        JSONObject requestParam = requestParamForAddContactInfo.getRequestParamForAddContactInfo(addContactDo);
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

    //@MethodObjective-Changes in the url from its orignal method to different method so it will not allow to send request
    @Test(priority=6)
    public void checkMethodChangeValidation(){
        AddContactDo addContactDo = new AddContactDo();
        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/api/v1/campus/college/addContactInfo/111";
        RequestSpecification httprequest = RestAssured.given();
        RequestParamForAddContactInfo requestParamForAddContactInfo = new RequestParamForAddContactInfo();
        JSONObject requestParam = requestParamForAddContactInfo.getRequestParamForAddContactInfo(addContactDo);
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

    //@MethodObjective-Check by empty the fields i.e. firstname, lastname,contactNumber
    @Test(priority=7)
    public void checkEmptyFieldsResponse(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setFirstName("");
        addContactDo.setLastName("");
        addContactDo.setContactNumber("");
        GetApiResponse getApiResponse = url.setUpForAddContactInfo(addContactDo,"111");
        int statusCode =getApiResponse.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String EmptyFieldsValidation  = "{\"contactInfo[0].contactNumber\":\"Phone Number Should Be Valid!\",\"contactInfo[0].firstName\":\"must not be empty\",\"contactInfo[0].lastName\":\"must not be empty\"}".toString();
        Assert.assertEquals(getApiResponse.getResponse().asString(), EmptyFieldsValidation);
    }

    //@MethodObjective-Check by null the fields i.e. firstname, lastname,contactNumber
    @Test(priority=7)
    public void checkNullFieldsResponse(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setFirstName(null);
        addContactDo.setLastName(null);
        addContactDo.setContactNumber(null);
        GetApiResponse getApiResponse = url.setUpForAddContactInfo(addContactDo,"111");
        int statusCode =getApiResponse.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String nullFieldsValidation1  = "{\"contactInfo[0].contactNumber\":\"must not be null\",\"contactInfo[0].firstName\":\"must not be null\",\"contactInfo[0].lastName\":\"must not be null\"}".toString();
        String nullFieldsValidation2  = "{\"contactInfo[0].contactNumber\":\"must not be null\",\"contactInfo[0].firstName\":\"must not be empty\",\"contactInfo[0].lastName\":\"must not be empty\"}".toString();
        String nullFieldsValidation3  = "{\"contactInfo[0].contactNumber\":\"must not be null\",\"contactInfo[0].firstName\":\"must not be null\",\"contactInfo[0].lastName\":\"must not be empty\"}".toString();
        String nullFieldsValidation4  = "{\"contactInfo[0].contactNumber\":\"must not be null\",\"contactInfo[0].firstName\":\"must not be empty\",\"contactInfo[0].lastName\":\"must not be null\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((getApiResponse.getResponse().asString()).equals(nullFieldsValidation1) || (getApiResponse.getResponse().asString()).equals(nullFieldsValidation2) ||
                (getApiResponse.getResponse().asString()).equals(nullFieldsValidation3) || (getApiResponse.getResponse().asString()).equals(nullFieldsValidation4));
    }

    //@MethodObjective-Check that lastName, contactNumber and firstname should not accept numeric and contact number should not accept alphabets
    @Test(priority = 8)
    public void verifyInvalidValuesPassedResponse(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setFirstName("677HUHBHHbjhghbhbhh");
        addContactDo.setLastName("HUHBHHbjhghbhbcscscsdcsdhh");
        addContactDo.setContactNumber("gfyfggcgc");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"112");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
        //response check
        String invalidValuesPassedResponse  = "{\"contactInfo[0].contactNumber\":\"Phone Number Should Be Valid!\",\"contactInfo[0].firstName\":\"Name Should Be Valid!\",\"contactInfo[0].lastName\":\"Name Should Be Valid!\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));

    }

    //@MethodObjective-Check that lastName, contactNumber and firstname should not accept special charcters.
    @Test(priority = 9)
    public void verifySpecialCharactersPassedResponse(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setFirstName("%$#(())(*&");
        addContactDo.setLastName("^^^^&#$$##@##");
        addContactDo.setContactNumber("$$%$%^&&*&**(");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"112");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
        //response check
        String invalidValuesPassedResponse  = "{\"contactInfo[0].contactNumber\":\"Phone Number Should Be Valid!\",\"contactInfo[0].firstName\":\"Name Should Be Valid!\",\"contactInfo[0].lastName\":\"Name Should Be Valid!\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));
    }

    //@MethodObjective-Check that last name, first name ,message fields name are accepting space in them.
    @Test(priority = 10)
    public void checkSpaceAcceptingInResponse(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setFirstName("Shreya Kumari");
        addContactDo.setLastName("Paliwal Rane");
        addContactDo.setMessage("For JIT Khargone");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"112");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 200 ");
    }

    //@MethodObjective-Check that email, contact number fields name are accepting space in them.
    @Test(priority = 11)
    public void checkSpaceNotAcceptingInResponse(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setContactNumber("7890 78900");
        addContactDo.setEmail("var@99 gmail.com");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"112");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
        //response check
        String invalidValuesPassedResponse  = "{\"contactInfo[0].contactNumber\":\"Phone Number Should Be Valid!\",\"contactInfo[0].email\":\"must be a well-formed email address\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));
    }


    //@MethodObjective-Check that message is not having limitation for the length.
    @Test(priority = 12)
    public void checkLimitationOfMessage(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setMessage("For Khandwa #$% 123 college gugghvgh  bvvghbj n   ghnmn   hjbjnkj  jknbkj.n     hjbh  hjhjh ghjygg ffgggh ghkjh hjjhj  hjhb hjhj");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 200 ");
    }

    //@MethodObjective-Check that contactNumber passed as 00000000000 .
    @Test(priority = 13)
    public void checkMinContactNo(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setContactNumber("00000000000");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
        String invalidValuesPassedResponse  = "{\"contactInfo[0].contactNumber\":\"Phone Number Should Be Valid!\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));

    }

    //@MethodObjective-Check that contactNumber passed as 99999999991 .
    @Test(priority = 14)
    public void checkMaxContactNo(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setContactNumber("99999999991");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
        String invalidValuesPassedResponse  = "{\"contactInfo[0].contactNumber\":\"Phone Number Should Be Valid!\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));

    }

    //@MethodObjective- Check that contactNumber is not accepting other then
    //6,7,8,9 digits range of numbers.
    @Test(priority = 14)
    public void checkContactNoValidation1(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setContactNumber("1111111111");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
        String invalidValuesPassedResponse  = "{\"contactInfo[0].contactNumber\":\"Phone Number Should Be Valid!\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));

    }

    //@MethodObjective- Check that contactNumber is not accepting other then
    //6,7,8,9 digits range of numbers.
    @Test(priority = 15)
    public void checkContactNoValidation2(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setContactNumber("2222222222");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
        String invalidValuesPassedResponse  = "{\"contactInfo[0].contactNumber\":\"Phone Number Should Be Valid!\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));

    }

    //@MethodObjective- Check that contactNumber is not accepting other then
    //6,7,8,9 digits range of numbers.
    @Test(priority = 16)
    public void checkContactNoValidation3(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setContactNumber("3333333333");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
        String invalidValuesPassedResponse  = "{\"contactInfo[0].contactNumber\":\"Phone Number Should Be Valid!\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));

    }

    //@MethodObjective- Check that contactNumber is not accepting other then
    //6,7,8,9 digits range of numbers.
    @Test(priority = 17)
    public void checkContactNoValidation4(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setContactNumber("4444444444");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
        String invalidValuesPassedResponse  = "{\"contactInfo[0].contactNumber\":\"Phone Number Should Be Valid!\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));

    }

    //@MethodObjective- Check that contactNumber is not accepting other then
    //6,7,8,9 digits range of numbers.
    @Test(priority = 18)
    public void checkContactNoValidation5(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setContactNumber("5555555555");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
        String invalidValuesPassedResponse  = "{\"contactInfo[0].contactNumber\":\"Phone Number Should Be Valid!\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));

    }

    //@MethodObjective- Check that contactNumber is not accepting less than 10 digits.
    //6,7,8,9 digits range of numbers.
    @Test(priority = 19)
    public void checkContactNoValidation6(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setContactNumber("787878989");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
        String invalidValuesPassedResponse  = "{\"contactInfo[0].contactNumber\":\"Phone Number Should Be Valid!\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));

    }

    //@MethodObjective- Check that contactNumber is not accepting more than 10 digits.
    @Test(priority = 20)
    public void checkContactNoValidation7(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setContactNumber("78787898912");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
        String invalidValuesPassedResponse  = "{\"contactInfo[0].contactNumber\":\"Phone Number Should Be Valid!\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));
    }

    //@MethodObjective-Check that email is accepting only well formed email.
    @Test(priority = 21)
    public void checkEmailValidation1(){
       AddContactDo addContactDo = new AddContactDo();
       addContactDo.setEmail("var99gmail.com");
       GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
       int statusCode = response.getStatusCode();
       Assert.assertEquals(statusCode,400);
       String statusLine= response.getStatusLine();
       Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
       String invalidValuesPassedResponse  = "{\"contactInfo[0].email\":\"must be a well-formed email address\"}".toString();
       //error message is changing sometimes for null or empty so this can be assert using or condition.
       Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));

   }


    //@MethodObjective-Check that email is accepting only well formed email.
    @Test(priority = 21)
    public void checkEmailValidation2(){
        AddContactDo addContactDo = new AddContactDo();
        addContactDo.setEmail("vargmailcom");
        GetApiResponse response = url.setUpForAddContactInfo(addContactDo,"111");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,400);
        String statusLine= response.getStatusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 400 ");
        String invalidValuesPassedResponse  = "{\"contactInfo[0].email\":\"must be a well-formed email address\"}".toString();
        //error message is changing sometimes for null or empty so this can be assert using or condition.
        Assert.assertTrue((response.getResponse().asString()).equals(invalidValuesPassedResponse));

    }
    //@MethodObjective-Check that new college is added then its contact info is
    // updated then its details are get from
    //getCollegeByCollegeCode api check the response has data
    //updated properly or not.
    @Test(priority = 22)
    public void endToEndCheckForApis(){
        //college added
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        //college code is unique so it need to be change everytime
        addCollegeDo.setCollegeCode("6121");
        addCollegeDo.setEmail("varsha@992gmail.com");
        GetApiResponse response = url.setUpForAddCollege(addCollegeDo);
        int statusCode1 = response.getStatusCode();
        Assert.assertEquals(statusCode1,200);

        //contact added for that college code and email
        AddContactDo addContactDo = new AddContactDo();
        //college code is unique so it need to be change everytime
        addContactDo.setFirstName("Shreya");
        addContactDo.setEmail("var@99gmail.com");
        addContactDo.setLastName("Paliwal");
        addContactDo.setContactNumber("8425970700");
        addContactDo.setMessage("For Khandwa college");
        GetApiResponse response2 = url.setUpForAddContactInfo(addContactDo,"6121");
        int statusCode2 = response2.getStatusCode();
        Assert.assertEquals(statusCode2,200);

        //object created of getApiResponse and pass in method code
        GetApiResponse getApiResponse3 = url.setUpForGetCollegeCode("6121");
        //status code is 200 passed
        int statusCode3 =  getApiResponse3.getStatusCode();
        Assert.assertEquals(statusCode3, 200);
    }

}