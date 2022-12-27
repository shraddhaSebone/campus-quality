package com.sebone.main;

import com.sebone.main.college.SetupForCollege;
import com.sebone.main.data.AddCollegeDo;
import com.sebone.main.data.UpdatePlacementDo;
import com.sebone.main.requestparams.RequestParamForUpdatePlacement;
import com.sebone.main.response.GetApiResponse;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class UpdatePlacementAutomationTest {
    /*
     * @autherName- varsha rane
     * @className- UpdatePlacementAutomationTest
     * @Objective- to automate the testcases for the UpdatePlacementDetails api.
     */

    private SetupForCollege collegeCode;

    //@MethodObjective- url to execute before class.
    @BeforeClass
    public void url() {

        collegeCode = new SetupForCollege();
    }
    //@MethodObjective- to verify the status code is "200" and response for creating new placement details with id.
    @Test(priority = 1)
    public void checkNewPlacementDetailsResponse(){
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setBranch("CSE");
        updatePlacementDo.setCourse("Mtech");
        updatePlacementDo.setCompanyName("Beckman coulter");
        GetApiResponse getApiResponse = collegeCode.setUpForUpdatePlacement(updatePlacementDo,"111");
        int statusCode= getApiResponse.getResponse().statusCode();
        Assert.assertEquals(200,statusCode);

    }

    //@MethodObjective- to verify the status code is "200" and response for creating new placement details with id.
    @Test(priority = 2)
    public void validatePlacementDetailsResponse(){
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setBranch("IT");
        updatePlacementDo.setCourse("MSC");
        updatePlacementDo.setCompanyName("Asian Paints");
        updatePlacementDo.setPlacementId(450);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo,"111");
        Assert.assertEquals(200,response.getStatusCode());
        //convert JSON Response array to List
        Map<String,List<LinkedHashMap>> jsonResponse = response.getResponse().jsonPath().getMap("$");
        //System.out.println(jsonResponse.toString());
        List<LinkedHashMap> placementData = jsonResponse.get("placements");
        List<UpdatePlacementDo> list = new ArrayList<UpdatePlacementDo>();
        for (int i = 0; i < placementData.size(); i++){
            LinkedHashMap  tempLinkedHashMapObj = new LinkedHashMap();
            tempLinkedHashMapObj = placementData.get(i);
            if(Integer.parseInt(tempLinkedHashMapObj.get("placementId").toString()) == 450) {
                Assert.assertEquals(tempLinkedHashMapObj.get("course").toString(), "MSC");
                Assert.assertEquals(tempLinkedHashMapObj.get("companyName").toString(), "Asian Paints");
                Assert.assertEquals(tempLinkedHashMapObj.get("branch").toString(), "IT");
            }
        }
    }

//    //@MethodObjective- to print whole hashmap on console and can assert whole response.
//    @Test(priority = 3)
//    public void validateWholePlacementResponse() {
//        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
//        updatePlacementDo.setBranch("IT");
//        updatePlacementDo.setCourse("MSC");
//        updatePlacementDo.setCompanyName("Asian Paints");
//        updatePlacementDo.setPlacementId(450);
//
//        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
//        Assert.assertEquals(200, response.getStatusCode());
//        //convert JSON Response array to List
//      //  JsonPath jsonPath = JsonPath.from(String.valueOf(response));
//
//        //map taking placement as key and values as arraylist and
//        Map<String, List<LinkedHashMap>> jsonResponse = response.getResponse().jsonPath().getMap("$");
//        //System.out.println(jsonResponse.toString());
//        List<LinkedHashMap> placementData = jsonResponse.get("placements");
//        List<UpdatePlacementDo> list = new ArrayList<UpdatePlacementDo>();
//        for (int i = 0; i < placementData.size(); i++) {
//            LinkedHashMap tempLinkedHashMapObj = new LinkedHashMap();
//            tempLinkedHashMapObj = placementData.get(i);
////            if (Integer.parseInt(tempLinkedHashMapObj.get("placementId").toString()) == 450) {
////                Assert.assertEquals(tempLinkedHashMapObj.get("course").toString(), "MSC");
////                Assert.assertEquals(tempLinkedHashMapObj.get("companyName").toString(), "Asian Paints");
////                Assert.assertEquals(tempLinkedHashMapObj.get("branch").toString(), "IT");
////            }
//             //get the arraylists from the response(hashmap obj) and set using getter setter of class updatePlacementDo then add that obj in list .
//            UpdatePlacementDo tempPlacementDoObj = new UpdatePlacementDo();
//            tempPlacementDoObj.setCourse(tempLinkedHashMapObj.get("course").toString());
//            tempPlacementDoObj.setPlacementId(Integer.parseInt(tempLinkedHashMapObj.get("placementId").toString()));
//            tempPlacementDoObj.setCompanyName(tempLinkedHashMapObj.get("companyName").toString());
//            tempPlacementDoObj.setNoOfStudents(tempLinkedHashMapObj.get("noOfStudents").toString());
//            tempPlacementDoObj.setBranch(tempLinkedHashMapObj.get("branch").toString());
//            tempPlacementDoObj.setPassingYear(tempLinkedHashMapObj.get("passingYear").toString());
//            list.add(tempPlacementDoObj);
//        }
//      //  System.out.println(list);
//Object courses = jsonPathEvauator.getJsonObject("courses");
  //      Assert.assertEquals(courses.toString(), "{courses=[{courseName=B.tech , branchName=civil, placedStudents=120, totalStudents=160}]}");
//
//    }

    //@MethodObjective- to verify the status code is "200" and response for creating new placement details with id.
    @Test(priority =3)
    public void validatePlacementResponseForNewId(){
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setBranch("Civil");
        updatePlacementDo.setCourse("Btech");
        updatePlacementDo.setCompanyName("Zehra pvt.ltd");
        //validate response
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo,"111");
        Assert.assertEquals(200,response.getStatusCode());
        //convert JSON Response array to List
        Map<String,List<LinkedHashMap>> jsonResponse = response.getResponse().jsonPath().getMap("$");
        List<LinkedHashMap> placementData = jsonResponse.get("placements");
        for (int i = 0; i < placementData.size(); i++){
            LinkedHashMap  tempLinkedHashMapObj = new LinkedHashMap();
            tempLinkedHashMapObj = placementData.get(i);
            if(tempLinkedHashMapObj.get("branch").toString().equals("Civil") && tempLinkedHashMapObj.get("course").toString().equals("Btech") && tempLinkedHashMapObj.get("companyName").toString().equals("Zehra pvt.ltd") ) {
                Assert.assertEquals(tempLinkedHashMapObj.get("course").toString(), "Btech");
                Assert.assertEquals(tempLinkedHashMapObj.get("companyName").toString(), "Zehra pvt.ltd");
                Assert.assertEquals(tempLinkedHashMapObj.get("branch").toString(), "Civil");
            }
        }
    }

    //@MethodObjective- Check the content-type is application json or not.
    @Test(priority =4)
    public void validateContentTypeResponse() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setBranch("Civil");
        updatePlacementDo.setCourse("Btech");
        updatePlacementDo.setCompanyName("Zehra pvt.ltd");
        //validate response
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
        Assert.assertEquals(200, response.getStatusCode());
        String contentType = response.getResponse().header("Content-Type");
        Assert.assertEquals( contentType,"application/json");
    }

    //@MethodObjective- Check the response time is application json or not.
    @Test(priority=5)
    public void checkResponseTime() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        //validate response
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
        ValidatableResponse resType = response.getResponse().then();
        // verify response time lesser than 1000 milliseconds
        resType.time(Matchers.lessThan(1000L));
    }

    //@MethodObjective- Check that chnge in url can give correct error response or not.
    @Test(priority=6)
    public void changeInUrlResponse2(){
        //Specify the url
        RestAssured.baseURI ="http://13.232.186.165:8080/Test/v1/campus/college/updatePlacementDetails";
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        //passing in requestParam
        RequestParamForUpdatePlacement requestParamForUpdatePlacement = new RequestParamForUpdatePlacement();
        JSONObject requestParamObj = requestParamForUpdatePlacement.getRequestParamForUpdatePlacement(updatePlacementDo);
        Response response=
                given().urlEncodingEnabled(false).log().all().config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
                .header("Content-Type", "application/json")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Authorization","\"eyJraWQiOiJEWmdxUElCV3RSQWNhTzFIZ1g1eVRXbDJSdVNQOGlIYmtBVDFMR2lLVVwvYz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIyMjg3YWRmYi00YjRmLTQzYWYtYmVlMy1kOGVlM2M3Y2FhZjIiLCJjb2duaXRvOmdyb3VwcyI6WyJjb2xsZWdlIiwiU1RVREVOVF9DT0xMRUdFX0FQUFJPVkVEIiwiU1RVREVOVCJdLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGgtMS5hbWF6b25hd3MuY29tXC9hcC1zb3V0aC0xX3oxTkRYZ1NacSIsImNsaWVudF9pZCI6IjU0OGc2aDdzdTFoc3UwaWU1c21qOG9vMTAxIiwib3JpZ2luX2p0aSI6IjQ4ODc5NDNiLTljODUtNDRlNS05YTM0LWU1YTUzMTI1OTQzNCIsImV2ZW50X2lkIjoiNjJjOTA4MjktOGEyNy00MDdhLWJjZDUtOWU0ZWQzYjg3MTUwIiwidG9rZW5fdXNlIjoiYWNjZXNzIiwic2NvcGUiOiJhd3MuY29nbml0by5zaWduaW4udXNlci5hZG1pbiIsImF1dGhfdGltZSI6MTY3MTcxMzQ5OSwiZXhwIjoxNjcxNzE3MDk5LCJpYXQiOjE2NzE3MTM0OTksImp0aSI6IjJjNDQyYTdlLTBiYzUtNGQ0Yy1hZDkxLTBmNWFmNzNjN2JkZSIsInVzZXJuYW1lIjoibW9oaXRyYWpwdXQxNDEyQGdtYWlsLmNvbSJ9.jIg8ssBciW6jUTPo7EqRW0NNZk14-eXZBVPk761LtIMNhIDMKpRytAH8rMThPYnJRlOxZaClWvINYNsokWp7CciJ2x3Sk0PgNLUoU4S7mksb80Xm09RFRas3emdFpyaINPFal-LC7P8Vi1MTzBuQ2kLuzVhbTflypooVeRxeaW2Iao2b3L15Mh2UZGZCI0YM_-hn7oBByCqV2nBgjUUxaVL72gIawdRPOf7x9uh4ffa9wh9lOO3KC7TJ5jXaQre71agrMz_LGB5jqRyQxygfmg0JPXKaqIgXu5XxW8NNUiP5AynzV9RJawtRwcjVlDSOPmIV4Rax8G-cyQUjfpDK5w\"")
                .body(requestParamObj.toJSONString())
                .when()
                .post("111");
        Assert.assertEquals(response.getStatusCode(),404);
    }

    //@MethodObjective- Check that api is chnged to API then whats the error response.
    @Test(priority=7)
    public void changeInUrlResponse(){
        //Specify the url
        RestAssured.baseURI ="http://13.232.186.165:8080/API/v1/campus/college/updatePlacementDetails";
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        //passing in requestParam
        RequestParamForUpdatePlacement requestParamForUpdatePlacement = new RequestParamForUpdatePlacement();
        JSONObject requestParamObj = requestParamForUpdatePlacement.getRequestParamForUpdatePlacement(updatePlacementDo);
        Response response=
                given().urlEncodingEnabled(false).log().all().config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
                        .header("Content-Type", "application/json")
                        .header("Accept-Encoding","gzip, deflate, br")
                        .header("Connection", "keep-alive")
                        .header("Authorization","\"eyJraWQiOiJEWmdxUElCV3RSQWNhTzFIZ1g1eVRXbDJSdVNQOGlIYmtBVDFMR2lLVVwvYz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIyMjg3YWRmYi00YjRmLTQzYWYtYmVlMy1kOGVlM2M3Y2FhZjIiLCJjb2duaXRvOmdyb3VwcyI6WyJjb2xsZWdlIiwiU1RVREVOVF9DT0xMRUdFX0FQUFJPVkVEIiwiU1RVREVOVCJdLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGgtMS5hbWF6b25hd3MuY29tXC9hcC1zb3V0aC0xX3oxTkRYZ1NacSIsImNsaWVudF9pZCI6IjU0OGc2aDdzdTFoc3UwaWU1c21qOG9vMTAxIiwib3JpZ2luX2p0aSI6IjQ4ODc5NDNiLTljODUtNDRlNS05YTM0LWU1YTUzMTI1OTQzNCIsImV2ZW50X2lkIjoiNjJjOTA4MjktOGEyNy00MDdhLWJjZDUtOWU0ZWQzYjg3MTUwIiwidG9rZW5fdXNlIjoiYWNjZXNzIiwic2NvcGUiOiJhd3MuY29nbml0by5zaWduaW4udXNlci5hZG1pbiIsImF1dGhfdGltZSI6MTY3MTcxMzQ5OSwiZXhwIjoxNjcxNzE3MDk5LCJpYXQiOjE2NzE3MTM0OTksImp0aSI6IjJjNDQyYTdlLTBiYzUtNGQ0Yy1hZDkxLTBmNWFmNzNjN2JkZSIsInVzZXJuYW1lIjoibW9oaXRyYWpwdXQxNDEyQGdtYWlsLmNvbSJ9.jIg8ssBciW6jUTPo7EqRW0NNZk14-eXZBVPk761LtIMNhIDMKpRytAH8rMThPYnJRlOxZaClWvINYNsokWp7CciJ2x3Sk0PgNLUoU4S7mksb80Xm09RFRas3emdFpyaINPFal-LC7P8Vi1MTzBuQ2kLuzVhbTflypooVeRxeaW2Iao2b3L15Mh2UZGZCI0YM_-hn7oBByCqV2nBgjUUxaVL72gIawdRPOf7x9uh4ffa9wh9lOO3KC7TJ5jXaQre71agrMz_LGB5jqRyQxygfmg0JPXKaqIgXu5XxW8NNUiP5AynzV9RJawtRwcjVlDSOPmIV4Rax8G-cyQUjfpDK5w\"")
                        .body(requestParamObj.toJSONString())
                        .when()
                        .post("111");
        Assert.assertEquals(response.getStatusCode(),404);
    }

    //@MethodObjective-Change in request of the api should give correct error message.
    @Test(priority=8)
    public void changeInMethodResponse(){
        //Specify the url
        RestAssured.baseURI ="http://13.232.186.165:8080/api/v1/campus/college/updatePlacementDetails";
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        //passing in requestParam
        RequestParamForUpdatePlacement requestParamForUpdatePlacement = new RequestParamForUpdatePlacement();
        JSONObject requestParamObj = requestParamForUpdatePlacement.getRequestParamForUpdatePlacement(updatePlacementDo);
        Response response=
                given().urlEncodingEnabled(false).log().all().config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
                        .header("Content-Type", "application/json")
                        .header("Accept-Encoding","gzip, deflate, br")
                        .header("Connection", "keep-alive")
                        .header("Authorization","\"eyJraWQiOiJEWmdxUElCV3RSQWNhTzFIZ1g1eVRXbDJSdVNQOGlIYmtBVDFMR2lLVVwvYz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIyMjg3YWRmYi00YjRmLTQzYWYtYmVlMy1kOGVlM2M3Y2FhZjIiLCJjb2duaXRvOmdyb3VwcyI6WyJjb2xsZWdlIiwiU1RVREVOVF9DT0xMRUdFX0FQUFJPVkVEIiwiU1RVREVOVCJdLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGgtMS5hbWF6b25hd3MuY29tXC9hcC1zb3V0aC0xX3oxTkRYZ1NacSIsImNsaWVudF9pZCI6IjU0OGc2aDdzdTFoc3UwaWU1c21qOG9vMTAxIiwib3JpZ2luX2p0aSI6IjQ4ODc5NDNiLTljODUtNDRlNS05YTM0LWU1YTUzMTI1OTQzNCIsImV2ZW50X2lkIjoiNjJjOTA4MjktOGEyNy00MDdhLWJjZDUtOWU0ZWQzYjg3MTUwIiwidG9rZW5fdXNlIjoiYWNjZXNzIiwic2NvcGUiOiJhd3MuY29nbml0by5zaWduaW4udXNlci5hZG1pbiIsImF1dGhfdGltZSI6MTY3MTcxMzQ5OSwiZXhwIjoxNjcxNzE3MDk5LCJpYXQiOjE2NzE3MTM0OTksImp0aSI6IjJjNDQyYTdlLTBiYzUtNGQ0Yy1hZDkxLTBmNWFmNzNjN2JkZSIsInVzZXJuYW1lIjoibW9oaXRyYWpwdXQxNDEyQGdtYWlsLmNvbSJ9.jIg8ssBciW6jUTPo7EqRW0NNZk14-eXZBVPk761LtIMNhIDMKpRytAH8rMThPYnJRlOxZaClWvINYNsokWp7CciJ2x3Sk0PgNLUoU4S7mksb80Xm09RFRas3emdFpyaINPFal-LC7P8Vi1MTzBuQ2kLuzVhbTflypooVeRxeaW2Iao2b3L15Mh2UZGZCI0YM_-hn7oBByCqV2nBgjUUxaVL72gIawdRPOf7x9uh4ffa9wh9lOO3KC7TJ5jXaQre71agrMz_LGB5jqRyQxygfmg0JPXKaqIgXu5XxW8NNUiP5AynzV9RJawtRwcjVlDSOPmIV4Rax8G-cyQUjfpDK5w\"")
                        .body(requestParamObj.toJSONString())
                        .when()
                        .put("111");
        Assert.assertEquals(response.getStatusCode(),405);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String error =  jsonPathEvaluator.getString("error");
        Assert.assertTrue(error.equals("Method Not Allowed"));
    }

    //@MethodObjective- Check that branch, passingYear,course, companyName,noOfStudents
    // field has validation and cannot put it empty .
    @Test(priority = 9)
    public void validateEmptyResponse() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setBranch("");
        updatePlacementDo.setCourse("");
        updatePlacementDo.setCompanyName("");
        updatePlacementDo.setNoOfStudents("");
        updatePlacementDo.setPassingYear("");
        updatePlacementDo.setPlacementId(451);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
        Assert.assertEquals(response.getStatusCode(),400);
        JsonPath jsonPathEvaluator = response.getResponse().jsonPath();
        String passingYear =  jsonPathEvaluator.getString("passingYear");
        Assert.assertTrue(passingYear.equals("Passing Year Should Be Valid!"));
        String companyName =  jsonPathEvaluator.getString("companyName");
        Assert.assertTrue(companyName.equals("must not be empty"));
        String course =  jsonPathEvaluator.getString("course");
        Assert.assertTrue(course.equals("must not be empty"));
        String branch =  jsonPathEvaluator.getString("branch");
        Assert.assertTrue(branch.equals("must not be empty"));
    }

  //@MethodObjective- Check that branch, passingYear,course, companyName,noOfStudents
    // field has validation and cannot put it empty .
  @Test(priority =10)
  public void validateNullResponse() {
      UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
      updatePlacementDo.setBranch(null);
      updatePlacementDo.setCourse(null);
      updatePlacementDo.setCompanyName(null);
      updatePlacementDo.setNoOfStudents(null);
      updatePlacementDo.setPassingYear(null);
      updatePlacementDo.setPlacementId(451);
      GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
      Assert.assertEquals(response.getStatusCode(),400);
      JsonPath jsonPathEvaluator = response.getResponse().jsonPath();
      String passingYear =  jsonPathEvaluator.getString("passingYear");
      Assert.assertTrue((passingYear.equals("must not be null"))||((passingYear.equals("Passing Year Should Be Valid!"))));
      String companyName =  jsonPathEvaluator.getString("companyName");
      Assert.assertTrue((companyName.equals("must not be null"))||((companyName.equals("must not be empty"))));
      String course =  jsonPathEvaluator.getString("course");
      Assert.assertTrue((course.equals("must not be null"))||((course.equals("must not be empty"))));
      String branch =  jsonPathEvaluator.getString("branch");
      Assert.assertTrue((branch.equals("must not be null"))||((branch.equals("must not be empty"))));
  }

    //@MethodObjective- Check that branch is accepting the alphanumeric characters and special characters.
    //,passing year and noOfStudents is accepting only numeric digits,course is appecting alphanumeric digits and special charcaters
    // companyName is  accepting alphanumeric and special characters and accepting uperlowercase also.
    @Test(priority =11)
    public void validateResponse() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setBranch("Test");
        updatePlacementDo.setCourse("ShreGHHya$%^^$");
        updatePlacementDo.setCompanyName("GTTGsdfgvbnm456yhgfbhty&&*&");
        updatePlacementDo.setNoOfStudents("45");
        updatePlacementDo.setPassingYear("2022");
        updatePlacementDo.setPlacementId(451);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
        Assert.assertEquals(response.getStatusCode(),200);
        Map<String,List<LinkedHashMap>> jsonResponse = response.getResponse().jsonPath().getMap("$");
        List<LinkedHashMap> placementData = jsonResponse.get("placements");
        for (int i = 0; i < placementData.size(); i++){
            LinkedHashMap  tempLinkedHashMapObj = new LinkedHashMap();
            tempLinkedHashMapObj = placementData.get(i);
            if(Integer.parseInt(tempLinkedHashMapObj.get("placementId").toString()) == 451) {
                Assert.assertEquals(tempLinkedHashMapObj.get("course").toString(), "Shreya$%^^$");
                Assert.assertEquals(tempLinkedHashMapObj.get("companyName").toString(), "sdfgvbnm456yhgfbhty&&*&");
                Assert.assertEquals(tempLinkedHashMapObj.get("branch").toString(), "Test");
                Assert.assertEquals(tempLinkedHashMapObj.get("passingYear"),"2022");
                Assert.assertEquals(tempLinkedHashMapObj.get("noOfStudents"),45);
            }
        }
    }

    //@MethodObjective- Check that branch is not accepting non-alphabetic char.
    @Test(priority = 12)
    public void checkResponseForInvalidBranchPassed() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setBranch("CSE#");
        updatePlacementDo.setPlacementId(451);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
        Assert.assertEquals(response.getStatusCode(),400);
        JsonPath jsonPathEvaluator = response.getResponse().jsonPath();
        String branch =  jsonPathEvaluator.getString("branch");
        Assert.assertTrue(branch.equals("Name Should Be Valid!"));
    }


    //@MethodObjective- Check that passingyear is not accepting non-numeric digits "noOfStudents" field
    // is not accepting non-numeric fields,
    @Test(priority = 13)
    public void checkResponseForStringNoOfStudents() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
          updatePlacementDo.setNoOfStudents("fdbf");
        updatePlacementDo.setPlacementId(451);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
        Assert.assertEquals(response.getStatusCode(),400);
    }

    //@MethodObjective- Check that passingyear is not accepting non-numeric digits "noOfStudents" field
    // is not accepting non-numeric fields,
    @Test(priority = 14)
    public void checkResponseForStringPassingYear() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setPassingYear("ccdc");
        updatePlacementDo.setPlacementId(451);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
        Assert.assertEquals(response.getStatusCode(),400);
    }

    //@MethodObjective- //Check that "passingYear" field is not accepting less than 4 and greater than 4 digits
    @Test(priority =15)
    public void checksSmallerPassingyearRes() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setPassingYear("34");
        updatePlacementDo.setPlacementId(451);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
        Assert.assertEquals(response.getStatusCode(),400);
        JsonPath jsonPathEvaluator = response.getResponse().jsonPath();
        String passingYear =  jsonPathEvaluator.getString("passingYear");
        Assert.assertTrue(passingYear.equals("Passing Year Should Be Valid!"));
    }

    //@MethodObjective- //Check that "passingYear" field is not accepting greater than 4 digits
    @Test(priority =16)
    public void checksGreaterPassingyearRes() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setPassingYear("34565");
        updatePlacementDo.setPlacementId(451);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
        Assert.assertEquals(response.getStatusCode(),400);
        JsonPath jsonPathEvaluator = response.getResponse().jsonPath();
        String passingYear =  jsonPathEvaluator.getString("passingYear");
        Assert.assertTrue(passingYear.equals("Passing Year Should Be Valid!"));
    }

    //@MethodObjective- //Check that "passing year " field is not accepting 0
    @Test(priority =17)
    public void checksZeroPassingYearRes() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setPassingYear("0");
        updatePlacementDo.setPlacementId(451);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
        Assert.assertEquals(response.getStatusCode(),400);
        JsonPath jsonPathEvaluator = response.getResponse().jsonPath();
        String passingYear =  jsonPathEvaluator.getString("passingYear");
        Assert.assertTrue(passingYear.equals("Passing Year Should Be Valid!"));
    }

    //@MethodObjective- Check that "passing year " field is not accepting less than 0 digits.
    @Test(priority = 18)
    public void checksLessThanZeroPassingYearRes() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setPassingYear("-1");
        updatePlacementDo.setPlacementId(451);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
        Assert.assertEquals(response.getStatusCode(),400);
        JsonPath jsonPathEvaluator = response.getResponse().jsonPath();
        String passingYear =  jsonPathEvaluator.getString("passingYear");
        Assert.assertTrue(passingYear.equals("Passing Year Should Be Valid!"));
    }

    //@MethodObjective-Check that on placementId is not creating on manually passing.
    @Test(priority =19)
    public void checkManualIdpassedResponse() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setPassingYear("2022");
        updatePlacementDo.setPlacementId(56);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "111");
        Assert.assertEquals(response.getStatusCode(),404);
        JsonPath jsonPathEvaluator = response.getResponse().jsonPath();
        String errorCode =  jsonPathEvaluator.getString("errorCode");
        Assert.assertTrue(errorCode.equals("NOT_FOUND"));

    }

    //@MethodObjective-Check that duplicate  placementId is not created everytime new placementid is created.
    @Test(priority = 20)
    public void checkDuplicateIdInResponse(){
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setBranch("IT");
        updatePlacementDo.setCourse("MSC");
        updatePlacementDo.setCompanyName("Asian Paints");
       // updatePlacementDo.setPlacementId(450);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo,"111");
        Assert.assertEquals(200,response.getStatusCode());
        //convert JSON Response array to List
        Map<String,List<LinkedHashMap>> jsonResponse = response.getResponse().jsonPath().getMap("$");
        //System.out.println(jsonResponse.toString());
        List<LinkedHashMap> placementData = jsonResponse.get("placements");
        List<UpdatePlacementDo> list = new ArrayList<UpdatePlacementDo>();

        for (int i = 0; i < placementData.size(); i++){
            for(int j=i+1 ;j<placementData.size();j++) {
                LinkedHashMap tempLinkedHashMapObj = new LinkedHashMap();
                tempLinkedHashMapObj = placementData.get(i);
                if (Integer.parseInt(tempLinkedHashMapObj.get("placementId").toString()) == Integer.parseInt(placementData.get(j).get("placementId").toString())) {
                    Assert.assertTrue(false);
                    break;
                }
            }
        }
    }

    //@MethodObjective- Check that on passing invalid collegeCode error mesage is correct.
    @Test(priority = 21)
    public void checkInvalidCollegeCodeResponse() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setPassingYear("2022");
        updatePlacementDo.setPlacementId(56);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "8");
        Assert.assertEquals(response.getStatusCode(),404);
        JsonPath jsonPathEvaluator = response.getResponse().jsonPath();
        String errorCode =  jsonPathEvaluator.getString("errorCode");
        Assert.assertTrue(errorCode.equals("NOT_FOUND"));
        String errorMessage =  jsonPathEvaluator.getString("errorMessage");
        Assert.assertTrue(errorMessage.equals("Op's something went wrong, Don't worry we are figuring out"));
        String detailedMessage =  jsonPathEvaluator.getString("detailedMessage");
        Assert.assertTrue(detailedMessage.equals("No value present"));
    }
    //@MethodObjective- Check that on passing string passed in  collegeCode error mesage is correct.
    @Test(priority = 22)
    public void checkStringCollegeCodeResponse() {
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setPassingYear("2022");
        updatePlacementDo.setPlacementId(56);
        GetApiResponse response = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "vgbj");
        Assert.assertEquals(response.getStatusCode(),404);
        JsonPath jsonPathEvaluator = response.getResponse().jsonPath();
        String errorCode =  jsonPathEvaluator.getString("errorCode");
        Assert.assertTrue(errorCode.equals("NOT_FOUND"));
        String errorMessage =  jsonPathEvaluator.getString("errorMessage");
        Assert.assertTrue(errorMessage.equals("Op's something went wrong, Don't worry we are figuring out"));
        String detailedMessage =  jsonPathEvaluator.getString("detailedMessage");
        Assert.assertTrue(detailedMessage.equals("No value present"));
    }


    //@MethodObjective-Check on adding new college then updating its placement details:-
    // then get it by getCollegeByCollegeCode and verify it that
    //details are updated in response.
    @Test(priority = 23)
    public void EndToEndTestApi() {
        //college added
        AddCollegeDo addCollegeDo = new AddCollegeDo();
        //college code is unique so it need to be change everytime
        addCollegeDo.setCollegeCode("2001");
        addCollegeDo.setEmail("ritika89@gmail.com");
        GetApiResponse response = collegeCode.setUpForAddCollege(addCollegeDo);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        //update its placement details
        UpdatePlacementDo updatePlacementDo = new UpdatePlacementDo();
        updatePlacementDo.setBranch("IT");
        updatePlacementDo.setCourse("MSC");
        updatePlacementDo.setCompanyName("Asian Paints");
        GetApiResponse updateResponse = collegeCode.setUpForUpdatePlacement(updatePlacementDo, "2001");
        Assert.assertEquals(200, updateResponse.getStatusCode());
        //convert JSON Response array to List
        Map<String, List<LinkedHashMap>> jsonResponse = updateResponse.getResponse().jsonPath().getMap("$");
        //System.out.println(jsonResponse.toString());
        List<LinkedHashMap> placementData = jsonResponse.get("placements");
        List<UpdatePlacementDo> list = new ArrayList<UpdatePlacementDo>();
        for (int i = 0; i < placementData.size(); i++) {
            LinkedHashMap tempLinkedHashMapObj = new LinkedHashMap();
            tempLinkedHashMapObj = placementData.get(i);
            if (Integer.parseInt(tempLinkedHashMapObj.get("placementId").toString()) == updatePlacementDo.getPlacementId()) {
                Assert.assertEquals(tempLinkedHashMapObj.get("course").toString(), "MSC");
                Assert.assertEquals(tempLinkedHashMapObj.get("companyName").toString(), "Asian Paints");
                Assert.assertEquals(tempLinkedHashMapObj.get("branch").toString(), "IT");
            }
        }
        //get it in the list using get college code api
        GetApiResponse getApiResponse = collegeCode.setUpForGetCollegeCode("2001");
        //status code is 200 passed
        Assert.assertEquals(getApiResponse.getStatusCode(), 200);
        Map<String, List<LinkedHashMap>> jsonResponse2 = updateResponse.getResponse().jsonPath().getMap("$");
        //System.out.println(jsonResponse.toString());
        List<LinkedHashMap> placementData2 = jsonResponse2.get("placements");
        List<UpdatePlacementDo> list2 = new ArrayList<UpdatePlacementDo>();
        for (int i = 0; i < placementData2.size(); i++) {
            LinkedHashMap tempLinkedHashMapObj2 = new LinkedHashMap();
            tempLinkedHashMapObj2 = placementData.get(i);
            if (Integer.parseInt(tempLinkedHashMapObj2.get("placementId").toString()) == updatePlacementDo.getPlacementId()) {
                Assert.assertEquals(tempLinkedHashMapObj2.get("course").toString(), "MSC");
                Assert.assertEquals(tempLinkedHashMapObj2.get("companyName").toString(), "Asian Paints");
                Assert.assertEquals(tempLinkedHashMapObj2.get("branch").toString(), "IT");
            }

        }

    }
}



















