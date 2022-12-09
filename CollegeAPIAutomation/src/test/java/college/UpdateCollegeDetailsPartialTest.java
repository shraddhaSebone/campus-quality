package college;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.ObjectUtils;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UpdateCollegeDetailsPartialTest {
    /*
     * @authorName- Varsha Rane
     * @className- UpdateCollegeDetailsPartialTest
     * @Objective- to automate the testcases for the updateCollegeDetailsPartial api.
     */

    private SetupForCollege collegeCode;

    //@MethodObjective- to execute before class
    @BeforeClass
    public void url() {

        collegeCode = new SetupForCollege();
    }

    // @MethodObjective- the objective of this method is to test the status code is 200 and statusline for the successfully partial updating college details.
    @Test(priority = 1)
    public void verifyPartialUpdateColllegeDetailsResponse() {
        UpdateCollegeDetailsPartialDo updateCollegeDetailsPartialDo = new UpdateCollegeDetailsPartialDo();
        updateCollegeDetailsPartialDo.setCollegeCode("112");
        updateCollegeDetailsPartialDo.setCollegeName("RGPV");
        updateCollegeDetailsPartialDo.setCity("Bhopal");
        updateCollegeDetailsPartialDo.setAwards("You should designate which type of award you received, though (i.e., Commended Student, Semifinalist, Finalist, or Scholar). However, if the award is particular to your school, or is something that may not be self-explanatory, you may need to describe it a bit more.");
        updateCollegeDetailsPartialDo.setOverview("A college is an educational institution for engineering.");
        updateCollegeDetailsPartialDo.setWebsite("www.rgpv.com");
        updateCollegeDetailsPartialDo.setNoOfStudents(120);
        updateCollegeDetailsPartialDo.setPlacedStudents(10);
        updateCollegeDetailsPartialDo.setRanking(3);
        updateCollegeDetailsPartialDo.setEmail("rgpv87@gmail.com");
        GetApiResponse getApiResponse = collegeCode.setUpForUpdatePartialCollege(updateCollegeDetailsPartialDo, "111");
        getApiResponse.getResponse().prettyPrint();
        int statusCode = getApiResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        String statusLine = getApiResponse.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 ");

        // JsonPath object created for response
        Response response = getApiResponse.getResponse();
        // JsonPath object created for response for geting JSON representation from response body
        JsonPath jsonPathEvauator = getApiResponse.getResponse().jsonPath();
        // message response validate for expected values
        String collegeCode = jsonPathEvauator.getString("collegeCode");
        //validating details
        Assert.assertTrue(collegeCode.contains("112"));
        String collegeName = jsonPathEvauator.getString("collegeName");
        Assert.assertEquals(collegeName, "RGPV");
        String city = jsonPathEvauator.getString("city");
        Assert.assertEquals(city, "Bhopal");
        int ranking = jsonPathEvauator.getInt("ranking");
        Assert.assertEquals(ranking, 3);
        String contactNumber = jsonPathEvauator.getString("contactNumber");
        Assert.assertEquals(contactNumber, "8976789899");
        String email = jsonPathEvauator.getString("email");
        Assert.assertEquals(email, "rgpv87@gmail.com");
        int noOfStudents = jsonPathEvauator.getInt("noOfStudents");
        Assert.assertEquals(noOfStudents, 120);
        int placedStudents = jsonPathEvauator.getInt("placedStudents");
        Assert.assertEquals(placedStudents, 10);
        String overview = jsonPathEvauator.getString("overview");
        Assert.assertEquals(overview, "A college is an educational institution for engineering.");
        String awards = jsonPathEvauator.getString("awards");
        Assert.assertEquals(awards, "You should designate which type of award you received, though (i.e., Commended Student, Semifinalist, Finalist, or Scholar). However, if the award is particular to your school, or is something that may not be self-explanatory, you may need to describe it a bit more.");
        String website = jsonPathEvauator.getString("website");
        Assert.assertEquals(website, "www.rgpv.com");
    }

    //@MethodObjective-Changes in the IP address in the url should not allow to send request
    @Test(priority = 2)
    public void checkUrlChangeValidation() {
        UpdateCollegeDetailsPartialDo updateCollegeDetailsPartialDo = new UpdateCollegeDetailsPartialDo();
        //Specify the url
        RestAssured.baseURI = "http://13.232.186.165:8080/test/v1/campus/college/update/collegeCode/111";
        RequestSpecification httprequest = RestAssured.given();
        RequestParamForPartialUpdate requestParamForPartialUpdate = new RequestParamForPartialUpdate();
        JSONObject requestParam = requestParamForPartialUpdate.getRequestParamForPartialUpdate(updateCollegeDetailsPartialDo);
        // to convert in string from json
        httprequest.body(requestParam.toJSONString());
        // passing content type in header
        httprequest.header("Content-Type", "application/json");
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.PATCH);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(404, statusCode);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String error = jsonPathEvaluator.getString("error");
        Assert.assertTrue(error.equals("Not Found"));
    }

    //@MethodObjective-Changes in the IP address in the url from api to API which should not allow to send request
    @Test(priority = 3)
    public void checkUrlChangeValidation2() {
        UpdateCollegeDetailsPartialDo updateCollegeDetailsPartialDo = new UpdateCollegeDetailsPartialDo();
        //Specify the url
        RestAssured.baseURI = "http://13.232.186.165:8080/API/v1/campus/college/update/collegeCode/111";
        RequestSpecification httprequest = RestAssured.given();
        RequestParamForPartialUpdate requestParamForPartialUpdate = new RequestParamForPartialUpdate();
        JSONObject requestParam = requestParamForPartialUpdate.getRequestParamForPartialUpdate(updateCollegeDetailsPartialDo);
        // to convert in string from json
        httprequest.body(requestParam.toJSONString());
        // passing content type in header
        httprequest.header("Content-Type", "application/json");
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.PATCH);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(404, statusCode);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String error = jsonPathEvaluator.getString("error");
        Assert.assertTrue(error.equals("Not Found"));
    }

    //@MethodObjective-Changes in the IP address in the url from api to API which should not allow to send request
    @Test(priority = 4)
    public void checkMethodChangeValidation() {
        UpdateCollegeDetailsPartialDo updateCollegeDetailsPartialDo = new UpdateCollegeDetailsPartialDo();
        //Specify the url
        RestAssured.baseURI = "http://13.232.186.165:8080/api/v1/campus/college/update/collegeCode/111";
        RequestSpecification httprequest = RestAssured.given();
        RequestParamForPartialUpdate requestParamForPartialUpdate = new RequestParamForPartialUpdate();
        JSONObject requestParam = requestParamForPartialUpdate.getRequestParamForPartialUpdate(updateCollegeDetailsPartialDo);
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
        UpdateCollegeDetailsPartialDo updateCollegeDetailsPartialDo = new UpdateCollegeDetailsPartialDo();
        updateCollegeDetailsPartialDo.setCollegeCode("111");
        GetApiResponse response = collegeCode.setUpForUpdatePartialCollege(updateCollegeDetailsPartialDo,"111");
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
        UpdateCollegeDetailsPartialDo updateCollegeDetailsPartialDo = new UpdateCollegeDetailsPartialDo();
        updateCollegeDetailsPartialDo.setCollegeCode("127");
        GetApiResponse response = collegeCode.setUpForUpdatePartialCollege(updateCollegeDetailsPartialDo,"111");
        // obtain ValidatableResponse type
        ValidatableResponse resType = response.getResponse().then();
        // verify response time lesser than 1000 milliseconds
        resType.time(Matchers.lessThan(1000L));
    }
}

