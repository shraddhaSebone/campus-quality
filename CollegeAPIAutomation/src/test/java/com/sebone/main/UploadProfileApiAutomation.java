package com.sebone.main;

import com.sebone.main.college.SetupForCollege;
import com.sebone.main.response.GetApiResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class UploadProfileApiAutomation {
    /* @className- UploadProfileApiAutomation
     * @autherName- varsha Rane
     * @Objective - to automate the testcases for the uploadProfile api
     */
    private SetupForCollege url;

    //@MethodObjective- to execute before class
    @BeforeClass
    public void url() {

        url = new SetupForCollege();
    }

    // @MethodObjective- the objective of this method is to test the status code is 201 and response that its returning
    // the path of the image uploaded on the s3  for the successfully uploading profile image.
    @Test(priority=1)
    public void uploadProfileImageTest() {
        Map<String, Object> profileImageMap = new HashMap<>();
        profileImageMap.put("profile", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        GetApiResponse response = url.setUpForProfileImageUpload(profileImageMap,"111/sports",new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(response.getResponse().contentType(),"application/json");
    }

    // @MethodObjective- the objective of this method is to test the status code is 201 and response that its returning
    // the path of the profile image uploaded on the s3  for the successfully uploading image on uploading multiple times same file again and again for same category and code.
    @Test(priority=2)
    public void uploadDuplicateImageResponse() {
        //upload the same image for same college code and same category
        Map<String, Object> profileImageMap = new HashMap<>();
        profileImageMap.put("profile", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        GetApiResponse response = url.setUpForProfileImageUpload(profileImageMap,"111/lab",new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(response.getResponse().contentType(),"application/json");

        //upload imageUrl
        Map<String, Object> imageMap2 = new HashMap<>();
        imageMap2.put("profile", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        GetApiResponse response2 = url.setUpForProfileImageUpload(profileImageMap,"111/lab",new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(response2.getResponse().contentType(),"application/json");
    }

    //@MethodObjective-Check whether its accepting profile image in the body or not in pdf format or not.
    @Test(priority=3)
    public void pdfImagesUploadUrlResponse() {
        Map<String, Object> profileImageMap = new HashMap<>();
        profileImageMap.put("profile", new File("\"C:\\Users\\Varsha Rane\\OneDrive\\Documents\\BestPractices.pptx\""));
        GetApiResponse response = url.setUpForProfileImageUpload(profileImageMap, "111/lab", new File("C:\\Users\\Varsha Rane\\OneDrive\\Documents\\BestPractices.pptx"));
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(response.getResponse().contentType(), "application/json");
    }

    // @MethodObjective- the objective of this method is to test if the response time of the api is less then 2 sec.
    @Test(priority=4)
    public void ImageUploadResponseTime() {
        Map<String, Object> profileImageMap = new HashMap<>();
        profileImageMap.put("image", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        RestAssured.baseURI ="http://13.232.186.165:8080/api/v1/campus/college/upload/profile/111/lab";
        given().log().all().config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
                .header("Content-Type", "multipart/form-data")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive")
                .body(profileImageMap.toString())
                .multiPart("image", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"))
                .when()
                .post("upload/profile/111/lab").then().time(Matchers.lessThan(3000L));
    }


    //@MethodObjective- Check if the "profile" name passed in body is passed invalid or in uppercase for the valid Profile file then whats the error message.
    @Test(priority=5)
    public void profileNameInvalidResponse() {
        Map<String, Object> profileImageMap = new HashMap<>();
        profileImageMap.put("image", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        RestAssured.baseURI ="http://13.232.186.165:8080/api/v1/campus/college";
        Response response= (Response) given().log().all().config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
                .header("Content-Type", "multipart/form-data")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive").body(profileImageMap.toString())
                .multiPart("Profile", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"))
                .when()
                .post("upload/profile/111/lab");
        Assert.assertEquals(400,response.getStatusCode());
    }
}
