package com.sebone.main;
import java.io.*;
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

public class UploadVedioApiAutomation {
    /* @className- UploadVedioApiAutomation
     * @autherName- varsha Rane
     * @Objective - to automate the testcases for the uploadVideo api
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
    public void uploadVideoTest() {
        Map<String, Object> videoMap = new HashMap<>();
        videoMap.put("video", new File("C:/Users/Varsha Rane/Downloads/big_buck_bunny_720p_1mb.mp4"));
        //C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png
        //"C:\Users\Varsha Rane\Downloads\big_buck_bunny_720p_1mb.mp4"
        GetApiResponse response = url.setUpForVedioUpload(videoMap,"111/music",new File("C:/Users/Varsha Rane/Downloads/big_buck_bunny_720p_1mb.mp4"));
        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(response.getResponse().contentType(),"application/json");
        String jsonStringResponse = response.getResponse().asString();
        Assert.assertTrue(jsonStringResponse.equals("{\"music\":[\"https://s3.ap-south-1.amazonaws.com/campus-data-sebone/jit/Videos/music/big_buck_bunny_720p_1mb.mp4\"]}"));
    }

    // @MethodObjective- the objective of this method is to test the status code is 201 and response that its returning
    // the path of the vedio uploaded on the s3  for the successfully uploading video on uploading multiple times same file again and again for same category and code.
    @Test(priority=2)
    public void uploadDuplicateVideoResponse() {
        Map<String, Object> videoMap = new HashMap<>();
        videoMap.put("video", new File("C:/Users/Varsha Rane/Downloads/big_buck_bunny_720p_1mb.mp4"));
        //C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png
        //"C:\Users\Varsha Rane\Downloads\big_buck_bunny_720p_1mb.mp4"
        GetApiResponse response = url.setUpForVedioUpload(videoMap,"111/music",new File("C:/Users/Varsha Rane/Downloads/big_buck_bunny_720p_1mb.mp4"));
        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(response.getResponse().contentType(),"application/json");
        String jsonStringResponse = response.getResponse().asString();
        Assert.assertTrue(jsonStringResponse.equals("{\"music\":[\"https://s3.ap-south-1.amazonaws.com/campus-data-sebone/jit/Videos/music/big_buck_bunny_720p_1mb.mp4\"]}"));

        //upload imageUrl
        Map<String, Object> videoMap2 = new HashMap<>();
        videoMap2.put("video", new File("C:/Users/Varsha Rane/Downloads/big_buck_bunny_720p_1mb.mp4"));
        GetApiResponse response2 = url.setUpForVedioUpload(videoMap2,"111/music",new File("C:/Users/Varsha Rane/Downloads/big_buck_bunny_720p_1mb.mp4"));
        Assert.assertEquals(201,response2.getStatusCode());
        Assert.assertEquals(response2.getResponse().contentType(),"application/json");
        String jsonStringResponse2 = response2.getResponse().asString();
        Assert.assertTrue(jsonStringResponse2.equals("{\"music\":[\"https://s3.ap-south-1.amazonaws.com/campus-data-sebone/jit/Videos/music/big_buck_bunny_720p_1mb.mp4\"]}"));
    }

    //@MethodObjective-Check whether its accepting video in the body or not in pdf format or not.
    @Test(priority=3)
    public void pdfImagesUploadUrlResponse() {
        Map<String, Object> videoMap = new HashMap<>();
        videoMap.put("video", new File("C:/Users/Varsha Rane/Downloads/Sebone-Template.potx"));
        GetApiResponse response = url.setUpForVedioUpload(videoMap,"111/music",new File("C:/Users/Varsha Rane/Downloads/Sebone-Template.potx"));
        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(response.getResponse().contentType(),"application/json");
        String jsonStringResponse = response.getResponse().asString();
        Assert.assertTrue(jsonStringResponse.equals("{\"music\":[\"https://s3.ap-south-1.amazonaws.com/campus-data-sebone/jit/Videos/music/Sebone-Template.potx\"]}"));
    }

    // @MethodObjective- the objective of this method is to test if the response time of the api is less then 3 sec.
    @Test(priority=4)
    public void ImageUploadResponseTime() {
        Map<String, Object> videoMap = new HashMap<>();
        videoMap.put("video", new File("C:/Users/Varsha Rane/Downloads/video (2).mp4"));
        RestAssured.baseURI ="http://13.232.186.165:8080/api/v1/campus/college";
        given().log().all().config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
                .header("Content-Type", "multipart/form-data")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive")
                .body(videoMap.toString())
                .multiPart("image", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"))
                .when()
                .post("/upload/images/111/music").then().time(Matchers.lessThan(3000L));
    }


    //@MethodObjective- Check if the "video" name passed in body as "Video" invalid or in uppercase for the valid video file then whats the error message.
    @Test(priority=5)
    public void videoNameInvalidResponse() {
        Map<String, Object> videoMap = new HashMap<>();
        videoMap.put("video", new File("C:/Users/Varsha Rane/Downloads/video (2).mp4"));
        RestAssured.baseURI ="http://13.232.186.165:8080/api/v1/campus/college";
        Response response= (Response) given().log().all().config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
                .header("Content-Type", "multipart/form-data")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive").body(videoMap.toString())
                .multiPart("Video", new File("C:/Users/Varsha Rane/Downloads/video (2).mp4"))
                .when()
                .post("upload/video/111/Sports");
        Assert.assertEquals(400,response.getStatusCode());
    }

    //@MethodObjective- check if the image is upload ablein video or not using video upload api.
    @Test(priority=6)
    public void uploadImageInVideoTest() {
        Map<String, Object> videoMap = new HashMap<>();
        videoMap.put("video", new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        GetApiResponse response = url.setUpForVedioUpload(videoMap,"111/music",new File("C:/Users/Varsha Rane/OneDrive/Pictures/bug login1.png"));
        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(response.getResponse().contentType(),"application/json");
        String jsonStringResponse = response.getResponse().asString();
        Assert.assertTrue(jsonStringResponse.equals("{\"music\":[\"https://s3.ap-south-1.amazonaws.com/campus-data-sebone/jit/Videos/music/bug%20login1.png\"]}"));
    }
}
