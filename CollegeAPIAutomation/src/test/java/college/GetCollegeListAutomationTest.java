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

public class GetCollegeListAutomationTest {
    /*
     * @autherName- varsha rane
     * @className- GetCollegeListAutomationTest
     * @Objective- to automate the testcases for the getCollegeList api.
     */

    private SetupForCollege url;

    //@MethodObjective- url to execute before class.
    @BeforeClass
    public void url() {

        url = new SetupForCollege();
    }

    // @MethodObjective- verify the status code and response
    @Test(priority=1)
    public void verifyStateCode(){
      GetApiResponse getApiResponse =url.setUpForGetCollegeList();
      int statusCode = getApiResponse.getStatusCode();
      // status code is 200 passed
      Assert.assertEquals(statusCode,"200");
      // Get the status line from the Response in a variable called statusLine
      String statusLine = getApiResponse.getStatusLine();
      Assert.assertEquals(statusLine,"HTTP/1.1 200 ");
      // to check if the response body is not empty.
      String res = getApiResponse.getResponse().asString();
      Assert.assertFalse(res.isEmpty());
    }

    //@MethodObjective-Check the content -type is application json or not.
    @Test(priority = 3)
    public void validateContentType() {
        GetApiResponse getApiResponse = url.setUpForGetCollegeList();
        // access header and validate it
        String contentType = getApiResponse.getResponse().header("Content-Type");
        // assertion for contentType
        Assert.assertEquals(contentType, "application/json");
    }

    // @MethodOPbjective- response time verification
    @Test(priority = 4)
    public void ResponseTimeValidation() {
        // object created of getApiResponse and pass in method id
        GetApiResponse getApiResponse = url.setUpForGetCollegeList();
        // obtain ValidatableResponse type
        ValidatableResponse resType = getApiResponse.getResponse().then();
        // verify response time lesser than 1000 milliseconds
        resType.time(Matchers.lessThan(1000L));
    }

    //@MethodObjective- Check that on adding college by using addCollege that college
    // is displayed or not for passing that college code.
    @Test(priority=5)
    public void validateChangeInUrlResponse(){
        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/test/v1/campus/college";
        RequestSpecification httprequest = RestAssured.given();
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.GET);
        int statusCode2 = response.getStatusCode();
        // status code is 200 passed
        Assert.assertEquals(statusCode2,404);
        JsonPath jsonPathEvauator = response.jsonPath();
        // message response validate for expected values
        String error = jsonPathEvauator.getString("error");
        Assert.assertEquals(error,"Not Found");

    }

    //@MethodObjective-Check if there is change in end point of the url in uppercase
    // then correct error message is showing or not (intead of api  use API)
    @Test(priority=6)
    public void validateInvalidUrlResponse(){
        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/API/v1/campus/college";
        RequestSpecification httprequest = RestAssured.given();
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.GET);
        int statusCode2 = response.getStatusCode();
        // status code is 200 passed
        Assert.assertEquals(statusCode2,404);
        JsonPath jsonPathEvauator = response.jsonPath();
        // message response validate for expected values
        String error = jsonPathEvauator.getString("error");
        Assert.assertEquals(error,"Not Found");

    }

    //@MethodObjective-Check if change the method type from get to post or put
    //and check if status code 405 is coming as reponse or not.
    //(because url is same for get , post, put )
    @Test(priority=7)
    public void validateChangeInMethodResponse(){
        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/api/v1/campus/college";
        RequestSpecification httprequest = RestAssured.given();
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.PATCH);
        int statusCode2 = response.getStatusCode();
        // status code is 200 passed
        Assert.assertEquals(statusCode2,405);
        JsonPath jsonPathEvauator = response.jsonPath();
        // message response validate for expected values
        String error = jsonPathEvauator.getString("error");
        Assert.assertEquals(error,"Method Not Allowed");
    }


}
