package com.sebone.main;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import io.restassured.response.Response;
import com.sebone.main.college.SetupForCollege;
import com.sebone.main.response.GetApiResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class UploadImageApiAutomation {

    /* @className- UploadImageApiAutomation
     * @autherName- varsha Rane
     * @Objective - to automate the testcases for the uploadImage api
     */
    private SetupForCollege url;

    //@MethodObjective- to execute before class
    @BeforeClass
    public void url() {

        url = new SetupForCollege();
    }

    // @MethodObjective- the objective of this method is to test the status code is 201 and response that its returning
    // the path of the image uploaded on the s3  for the successfully uploading image.
    @Test(priority=1)
    public void uploadImageTest() {
        Map<String, Object> imageMap = new HashMap<>();
        imageMap.put("image", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        GetApiResponse response = url.setUpForImageUpload(imageMap,"111/Sports",new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(response.getResponse().contentType(),"application/json");
        String jsonStringResponse = response.getResponse().asString();
     // Assert.assertTrue(jsonStringResponse.equals("{\"lab\": \"https://s3.ap-south-1.amazonaws.com/campus-data-sebone/jit/Profile/lab/50d153aa-0bb5-40b8-b65e-6f05d2fed55b.jpg\"}"));
    }

    // @MethodObjective- the objective of this method is to test the status code is 201 and response that its returning
    // the path of the image uploaded on the s3  for the successfully uploading image on uploading multiple times same file again and again for same category and code.
    @Test(priority=2)
    public void uploadDuplicateImageResponse() {
        //upload the same image for same college code and same category
        Map<String, Object> imageMap = new HashMap<>();
        imageMap.put("image", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        GetApiResponse response = url.setUpForImageUpload(imageMap,"112/Sports",new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(response.getResponse().contentType(),"application/json");
        String jsonStringResponse = response.getResponse().asString();
        Assert.assertTrue(jsonStringResponse.equals("{\"Sports\":[\"https://s3.ap-south-1.amazonaws.com/campus-data-sebone/rgpv/Image/sports/bug%20login1.png\"]}"));

        //upload imageUrl
        Map<String, Object> imageMap2 = new HashMap<>();
        imageMap2.put("image", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        GetApiResponse response2 = url.setUpForImageUpload(imageMap,"112/Sports",new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(response2.getResponse().contentType(),"application/json");
        String jsonStringResponse2 = response2.getResponse().asString();
        Assert.assertTrue(jsonStringResponse2.equals("{\"Sports\":[\"https://s3.ap-south-1.amazonaws.com/campus-data-sebone/rgpv/Image/sports/bug%20login1.png\"]}"));
    }

    //@MethodObjective-Check whether its accepting image in the body or not in pdf format or not.
    @Test(priority=3)
    public void pdfImagesUploadUrlResponse() {
        Map<String, Object> imageMap = new HashMap<>();
        imageMap.put("image", new File("\"C:\\Users\\Varsha Rane\\OneDrive\\Documents\\BestPractices.pptx\""));
        GetApiResponse response = url.setUpForImageUpload(imageMap, "111/Sports", new File("C:\\Users\\Varsha Rane\\OneDrive\\Documents\\BestPractices.pptx"));
        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals(response.getResponse().contentType(), "application/json");
        String jsonStringResponse = response.getResponse().asString();
        Assert.assertTrue(jsonStringResponse.equals("{\"Sports\":[\"https://s3.ap-south-1.amazonaws.com/campus-data-sebone/jit/Image/sports/BestPractices.pptx\"]}"));
    }

    // @MethodObjective- the objective of this method is to test if the response time of the api is less then 2 sec.
    @Test(priority=4)
    public void ImageUploadResponseTime() {
        Map<String, Object> imageMap = new HashMap<>();
        imageMap.put("image", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        RestAssured.baseURI ="http://13.232.186.165:8080/api/v1/campus/college";
        given().log().all().config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
                .header("Content-Type", "multipart/form-data")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive")
                        .body(imageMap.toString())
                .multiPart("image", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"))
                .when()
                .post("/upload/images/111/Sports").then().time(Matchers.lessThan(2000L));
    }


   //@MethodObjective- Check if the "image" name passed in body is passed invalid or in uppercase for the valid image file then whats the error message.
    @Test(priority=5)
    public void ImagesNameInvalidResponse() {
       Map<String, Object> imageMap = new HashMap<>();
        imageMap.put("image", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        RestAssured.baseURI ="http://13.232.186.165:8080/api/v1/campus/college";
              Response response= (Response) given().log().all().config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
                .header("Content-Type", "multipart/form-data")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive").body(imageMap.toString())
                .multiPart("Image", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"))
                .when()
                .post("/upload/images/111/Sports");
              Assert.assertEquals(400,response.getStatusCode());
    }
}
