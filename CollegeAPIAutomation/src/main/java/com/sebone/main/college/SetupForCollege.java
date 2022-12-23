package com.sebone.main.college;
import com.sebone.main.data.*;
import com.sebone.main.requestparams.*;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.sebone.main.response.GetApiResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class SetupForCollege {
    /*
     * @autherName- varsha Rane
     * @ClassName- SetupForCollege
     * @objective- to pass the entities that are required for the setup of college api post, put and get
     */

    public GetApiResponse setUpForAddCollege(AddCollegeDo addCollegeDo) {
        /*
         * @MethodName- setUpForAddCollege
         * @objective- to pass the entities that are required for the setup of add college api
         * @para- String,object
         * @returnType-object,int,string
         */

        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/api/v1/campus/college";
        RequestSpecification httprequest = RestAssured.given();
        RequestParamForAddCollege requestParamsObjForAddCollege = new RequestParamForAddCollege();
        JSONObject requestParam = requestParamsObjForAddCollege.getRequestParamForAddCollege(addCollegeDo);
        // to convert in string from json
        httprequest.body(requestParam.toJSONString());
        // passing content type in header
        httprequest.header("Content-Type", "application/json");
        httprequest.header("Authorization","\"eyJraWQiOiJEWmdxUElCV3RSQWNhTzFIZ1g1eVRXbDJSdVNQOGlIYmtBVDFMR2lLVVwvYz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIyMjg3YWRmYi00YjRmLTQzYWYtYmVlMy1kOGVlM2M3Y2FhZjIiLCJjb2duaXRvOmdyb3VwcyI6WyJjb2xsZWdlIl0sImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfejFORFhnU1pxIiwiY2xpZW50X2lkIjoiNTQ4ZzZoN3N1MWhzdTBpZTVzbWo4b28xMDEiLCJvcmlnaW5fanRpIjoiMWM2MjZmNWEtZjczNS00NTMzLWJiNmEtOTU3YTgwZWZiMWZhIiwiZXZlbnRfaWQiOiIyMDVhMTVkZS1lN2QyLTRmODMtYjZkMC01NWMyZjNiMmY3ZjMiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjcxNzkzMjgyLCJleHAiOjE2NzE3OTY4ODIsImlhdCI6MTY3MTc5MzI4MiwianRpIjoiMGY3YzY4YjgtNmRlMy00Yzc5LWJmMWUtNmQ0MjU5NGNkOTNlIiwidXNlcm5hbWUiOiJtb2hpdHJhanB1dDE0MTJAZ21haWwuY29tIn0.uRljKxRUeKL4cmrWaCH2tYoswdhWtINE28Lhi5Tfh5vHay3tgLFt5pF4KoPvmHCkgAuct-GNaqCxMDgKdq6E7Nt-E9Fy6SsvdshLPzrGlcF_tzDNX9nfAHNh5wKVQMdbxz2CUW7O1Yr3hRCD_OX9GifDVO1-MWB-uQcd7_EQ3Gxs618OvvJ7Ku64bLqsP5Z5Sh6P-fTKR-qSyjJzDns549ekV7Tj4YhwpzI3JRDlwzIG8B89YyrMh1vIcc4nKUtaAyVz311iKG5Gnk98Ppz6vzXPu0Q9BmDAsctt1Lpjj2B5qU-DyYLxyp6VwUkNiWXpP4HpeXyDNfpjSm2mKpFmjg\"");
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.POST);
        //returning response, statuscode, statusline
        return new GetApiResponse(response,response.getStatusCode(),response.statusLine()) ;
    }

    public GetApiResponse setUpForGetCollegeCode(String collegeCode) {
        /*
         * @MethodName- setUpForGetCollegeCode
         * @objective- to pass the entities that are required for the setup of getCollegeBycollegeCode api
         * @para- String,object
         * @returnType-object,int,string
         */

        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/api/v1/campus/college/collegeCode";
        RequestSpecification httprequest = RestAssured.given();
        httprequest.header("Authorization","\"eyJraWQiOiJEWmdxUElCV3RSQWNhTzFIZ1g1eVRXbDJSdVNQOGlIYmtBVDFMR2lLVVwvYz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIyMjg3YWRmYi00YjRmLTQzYWYtYmVlMy1kOGVlM2M3Y2FhZjIiLCJjb2duaXRvOmdyb3VwcyI6WyJjb2xsZWdlIl0sImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfejFORFhnU1pxIiwiY2xpZW50X2lkIjoiNTQ4ZzZoN3N1MWhzdTBpZTVzbWo4b28xMDEiLCJvcmlnaW5fanRpIjoiMWM2MjZmNWEtZjczNS00NTMzLWJiNmEtOTU3YTgwZWZiMWZhIiwiZXZlbnRfaWQiOiIyMDVhMTVkZS1lN2QyLTRmODMtYjZkMC01NWMyZjNiMmY3ZjMiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjcxNzkzMjgyLCJleHAiOjE2NzE3OTY4ODIsImlhdCI6MTY3MTc5MzI4MiwianRpIjoiMGY3YzY4YjgtNmRlMy00Yzc5LWJmMWUtNmQ0MjU5NGNkOTNlIiwidXNlcm5hbWUiOiJtb2hpdHJhanB1dDE0MTJAZ21haWwuY29tIn0.uRljKxRUeKL4cmrWaCH2tYoswdhWtINE28Lhi5Tfh5vHay3tgLFt5pF4KoPvmHCkgAuct-GNaqCxMDgKdq6E7Nt-E9Fy6SsvdshLPzrGlcF_tzDNX9nfAHNh5wKVQMdbxz2CUW7O1Yr3hRCD_OX9GifDVO1-MWB-uQcd7_EQ3Gxs618OvvJ7Ku64bLqsP5Z5Sh6P-fTKR-qSyjJzDns549ekV7Tj4YhwpzI3JRDlwzIG8B89YyrMh1vIcc4nKUtaAyVz311iKG5Gnk98Ppz6vzXPu0Q9BmDAsctt1Lpjj2B5qU-DyYLxyp6VwUkNiWXpP4HpeXyDNfpjSm2mKpFmjg\"");
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.GET,collegeCode);

        //returning response, statuscode, statusline
        return new GetApiResponse(response,response.getStatusCode(),response.statusLine()) ;
    }

    public GetApiResponse setUpForGetCollegeList() {
        /*
         * @MethodName- setUpForGetCollegeCode
         * @objective- to pass the entities that are required for the setup of getCollegeBycollegeCode api
         * @para- String,object
         * @returnType-object,int,string
         */

        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/api/v1/campus/college";
        RequestSpecification httprequest = RestAssured.given();
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.GET);
        httprequest.header("Authorization","\"eyJraWQiOiJEWmdxUElCV3RSQWNhTzFIZ1g1eVRXbDJSdVNQOGlIYmtBVDFMR2lLVVwvYz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIyMjg3YWRmYi00YjRmLTQzYWYtYmVlMy1kOGVlM2M3Y2FhZjIiLCJjb2duaXRvOmdyb3VwcyI6WyJjb2xsZWdlIl0sImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfejFORFhnU1pxIiwiY2xpZW50X2lkIjoiNTQ4ZzZoN3N1MWhzdTBpZTVzbWo4b28xMDEiLCJvcmlnaW5fanRpIjoiZDgxOTVmMDgtYmVlYi00MGNkLThlMWItYjlmNTYwNmZlZWNjIiwiZXZlbnRfaWQiOiI3YjQ2YTAwNi1kOThjLTRlNDUtYTBjMS1kYmJiZDU0NGJhYjMiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjcxNzk1MDQzLCJleHAiOjE2NzE3OTg2NDMsImlhdCI6MTY3MTc5NTA0MywianRpIjoiYWU3NGFhNjctYWNhMy00YjVhLWEwMTEtNmE0ZmMzOWQ2OTA2IiwidXNlcm5hbWUiOiJtb2hpdHJhanB1dDE0MTJAZ21haWwuY29tIn0.rp86_85wK7bP6hgOhY1IS0VhU9naZRfUwtqeUyjD3FnJ88iYM8m2RS5jR94UVwwtdtE9az1QNGhpjNG4_MZQ9P8kBuHt9yY021uD6iVWatmFv2qrBRIkpyQR9BN2CQxSWHMa-ghWPQAE00FuRFLXn4677wqOupNnxywCs8WeAZMWTuC88DIZOStl9BhbyWmFPBSJwdLgt_XmCqP9y6_ztFuDhBjj_5Biqg0lgkzJ3SUY4tz-h6V-BEJBgVuc8igj4ZCLeIPAAKFcQbNk77RZ8ad-txufsiCJsgnzk2BluG_ubl_K-n4rj4WwaXZpTA7tKMceb86XAvbunXA-JnkFRA\"");
        //returning response, statuscode, statusline
        return new GetApiResponse(response,response.getStatusCode(),response.statusLine()) ;
    }

    public GetApiResponse setUpForUpdateCollege(UpdateCollegeDo updateCollegeDo){
        /*
         * @MethodName- setUpForUpdateCollege
         * @objective- to pass the entities that are required for the setup of UpdateCollege api
         * @para- String,object
         * @returnType-object,int,string
         */

        //Specify the url
        RestAssured.baseURI="http://13.232.186.165:8080/api/v1/campus/college";
        RequestSpecification httprequest = RestAssured.given();
        RequestParamForUpdateCollege requestParamsObjForUpdateCollege = new RequestParamForUpdateCollege();
        JSONObject requestParam = requestParamsObjForUpdateCollege.getRequestParamForUpdateCollege(updateCollegeDo);
        // to convert in string from json
        httprequest.body(requestParam.toJSONString());
        // passing content type in header
        httprequest.header("Content-Type", "application/json");
        httprequest.header("Authorization","\"eyJraWQiOiJEWmdxUElCV3RSQWNhTzFIZ1g1eVRXbDJSdVNQOGlIYmtBVDFMR2lLVVwvYz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIyMjg3YWRmYi00YjRmLTQzYWYtYmVlMy1kOGVlM2M3Y2FhZjIiLCJjb2duaXRvOmdyb3VwcyI6WyJjb2xsZWdlIl0sImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfejFORFhnU1pxIiwiY2xpZW50X2lkIjoiNTQ4ZzZoN3N1MWhzdTBpZTVzbWo4b28xMDEiLCJvcmlnaW5fanRpIjoiMWM2MjZmNWEtZjczNS00NTMzLWJiNmEtOTU3YTgwZWZiMWZhIiwiZXZlbnRfaWQiOiIyMDVhMTVkZS1lN2QyLTRmODMtYjZkMC01NWMyZjNiMmY3ZjMiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjcxNzkzMjgyLCJleHAiOjE2NzE3OTY4ODIsImlhdCI6MTY3MTc5MzI4MiwianRpIjoiMGY3YzY4YjgtNmRlMy00Yzc5LWJmMWUtNmQ0MjU5NGNkOTNlIiwidXNlcm5hbWUiOiJtb2hpdHJhanB1dDE0MTJAZ21haWwuY29tIn0.uRljKxRUeKL4cmrWaCH2tYoswdhWtINE28Lhi5Tfh5vHay3tgLFt5pF4KoPvmHCkgAuct-GNaqCxMDgKdq6E7Nt-E9Fy6SsvdshLPzrGlcF_tzDNX9nfAHNh5wKVQMdbxz2CUW7O1Yr3hRCD_OX9GifDVO1-MWB-uQcd7_EQ3Gxs618OvvJ7Ku64bLqsP5Z5Sh6P-fTKR-qSyjJzDns549ekV7Tj4YhwpzI3JRDlwzIG8B89YyrMh1vIcc4nKUtaAyVz311iKG5Gnk98Ppz6vzXPu0Q9BmDAsctt1Lpjj2B5qU-DyYLxyp6VwUkNiWXpP4HpeXyDNfpjSm2mKpFmjg\"");
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.PUT);
        //returning response, statuscode, statusline
        return new GetApiResponse(response,response.getStatusCode(),response.statusLine()) ;
    }

    public GetApiResponse setUpForUpdatePartialCollege(UpdateCollegeDetailsPartialDo updateCollegeDetailsPartialDo, String collegeCode){
        /*
         * @MethodName- setUpForUpdatePartialCollege
         * @objective- to pass the entities that are required for the setup of UpdatePartialCollege api
         * @para- String,object
         * @returnType-object,int,string
         */

        //Specify the url
        RestAssured.baseURI ="http://13.232.186.165:8080/api/v1/campus/college/update/collegeCode";
        RequestSpecification httprequest = RestAssured.given();
        RequestParamForPartialUpdate requestParamForPartialUpdate = new RequestParamForPartialUpdate();
        JSONObject requestParamObj = requestParamForPartialUpdate.getRequestParamForPartialUpdate(updateCollegeDetailsPartialDo);
        //to convert in string from json
        httprequest.body(requestParamObj.toJSONString());
        //passing content-type  in header
        httprequest.header("Content-Type","application/json");
        httprequest.header("Authorization","\"eyJraWQiOiJEWmdxUElCV3RSQWNhTzFIZ1g1eVRXbDJSdVNQOGlIYmtBVDFMR2lLVVwvYz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIyMjg3YWRmYi00YjRmLTQzYWYtYmVlMy1kOGVlM2M3Y2FhZjIiLCJjb2duaXRvOmdyb3VwcyI6WyJjb2xsZWdlIl0sImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfejFORFhnU1pxIiwiY2xpZW50X2lkIjoiNTQ4ZzZoN3N1MWhzdTBpZTVzbWo4b28xMDEiLCJvcmlnaW5fanRpIjoiMWM2MjZmNWEtZjczNS00NTMzLWJiNmEtOTU3YTgwZWZiMWZhIiwiZXZlbnRfaWQiOiIyMDVhMTVkZS1lN2QyLTRmODMtYjZkMC01NWMyZjNiMmY3ZjMiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjcxNzkzMjgyLCJleHAiOjE2NzE3OTY4ODIsImlhdCI6MTY3MTc5MzI4MiwianRpIjoiMGY3YzY4YjgtNmRlMy00Yzc5LWJmMWUtNmQ0MjU5NGNkOTNlIiwidXNlcm5hbWUiOiJtb2hpdHJhanB1dDE0MTJAZ21haWwuY29tIn0.uRljKxRUeKL4cmrWaCH2tYoswdhWtINE28Lhi5Tfh5vHay3tgLFt5pF4KoPvmHCkgAuct-GNaqCxMDgKdq6E7Nt-E9Fy6SsvdshLPzrGlcF_tzDNX9nfAHNh5wKVQMdbxz2CUW7O1Yr3hRCD_OX9GifDVO1-MWB-uQcd7_EQ3Gxs618OvvJ7Ku64bLqsP5Z5Sh6P-fTKR-qSyjJzDns549ekV7Tj4YhwpzI3JRDlwzIG8B89YyrMh1vIcc4nKUtaAyVz311iKG5Gnk98Ppz6vzXPu0Q9BmDAsctt1Lpjj2B5qU-DyYLxyp6VwUkNiWXpP4HpeXyDNfpjSm2mKpFmjg\"");
        //passing method and url param in request and storing it in response.
        Response response = httprequest.request(Method.PATCH,collegeCode);
        //returning response, statuscode,statusline
        return new GetApiResponse(response,response.getStatusCode(),response.getStatusLine());
    }

    public GetApiResponse setUpForAddContactInfo(AddContactDo addContactDo, String collegeCode){
        /*
         * @MethodName- setUpForAddContactInfo
         * @objective- to pass the entities that are required for the setup of AddContactInfo api
         * @para- String,object
         * @returnType-object,int,string
         */

        //Specify the url
        RestAssured.baseURI ="http://13.232.186.165:8080/api/v1/campus/college/addContactInfo";
        RequestSpecification httprequest = RestAssured.given();
        RequestParamForAddContactInfo requestParamForAddContactInfo = new RequestParamForAddContactInfo();
        JSONObject requestParamObj = requestParamForAddContactInfo.getRequestParamForAddContactInfo(addContactDo);
        //to convert in string from json
        httprequest.body(requestParamObj.toJSONString());
        //passing content-type  in header
        httprequest.header("Content-Type","application/json");
        httprequest.header("Authorization","\"eyJraWQiOiJEWmdxUElCV3RSQWNhTzFIZ1g1eVRXbDJSdVNQOGlIYmtBVDFMR2lLVVwvYz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIyMjg3YWRmYi00YjRmLTQzYWYtYmVlMy1kOGVlM2M3Y2FhZjIiLCJjb2duaXRvOmdyb3VwcyI6WyJjb2xsZWdlIl0sImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfejFORFhnU1pxIiwiY2xpZW50X2lkIjoiNTQ4ZzZoN3N1MWhzdTBpZTVzbWo4b28xMDEiLCJvcmlnaW5fanRpIjoiMWM2MjZmNWEtZjczNS00NTMzLWJiNmEtOTU3YTgwZWZiMWZhIiwiZXZlbnRfaWQiOiIyMDVhMTVkZS1lN2QyLTRmODMtYjZkMC01NWMyZjNiMmY3ZjMiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjcxNzkzMjgyLCJleHAiOjE2NzE3OTY4ODIsImlhdCI6MTY3MTc5MzI4MiwianRpIjoiMGY3YzY4YjgtNmRlMy00Yzc5LWJmMWUtNmQ0MjU5NGNkOTNlIiwidXNlcm5hbWUiOiJtb2hpdHJhanB1dDE0MTJAZ21haWwuY29tIn0.uRljKxRUeKL4cmrWaCH2tYoswdhWtINE28Lhi5Tfh5vHay3tgLFt5pF4KoPvmHCkgAuct-GNaqCxMDgKdq6E7Nt-E9Fy6SsvdshLPzrGlcF_tzDNX9nfAHNh5wKVQMdbxz2CUW7O1Yr3hRCD_OX9GifDVO1-MWB-uQcd7_EQ3Gxs618OvvJ7Ku64bLqsP5Z5Sh6P-fTKR-qSyjJzDns549ekV7Tj4YhwpzI3JRDlwzIG8B89YyrMh1vIcc4nKUtaAyVz311iKG5Gnk98Ppz6vzXPu0Q9BmDAsctt1Lpjj2B5qU-DyYLxyp6VwUkNiWXpP4HpeXyDNfpjSm2mKpFmjg\"");
        //passing method and url param in request and storing it in response.
        Response response = httprequest.request(Method.POST,collegeCode);
        //returning response, statuscode,statusline
        return new GetApiResponse(response,response.getStatusCode(),response.getStatusLine());
    }

    public GetApiResponse setUpForImageUpload(Map<String, Object> imageMap, String urlPath,File imageFile){
        /*
         * @MethodName- setUpForImageUpload
         * @objective- to pass the entities that are required for the setUp of the image upload
         * @returnType-object,int,string
         */

        //Specify the url
        RestAssured.baseURI ="http://13.232.186.165:8080/api/v1/campus/college/upload/images";
        Response response=(Response) given().log().all().config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
                .header("Content-Type", "multipart/form-data")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive")
                .body(imageMap.toString())
                .multiPart("image", imageFile)
                .when()
                .post(urlPath);

        //returning response, statuscode,statusline
        return new GetApiResponse(response,response.getStatusCode(),response.getStatusLine());
    }

    public GetApiResponse setUpForVedioUpload(Map<String, Object> imageMap, String urlPath, File videoFile){
        /*
         * @MethodName- setUpForVedioUpload
         * @objective- to pass the entities that are required for the setUp of the vedio upload
         * @returnType-object,int,string
         */

        //Specify the url
        RestAssured.baseURI ="http://13.232.186.165:8080/api/v1/campus/college/upload/video";
        Response response=(Response) given().log().all().config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
                .header("Content-Type", "multipart/form-data")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive")
                .body(imageMap.toString())
                .multiPart("video", videoFile)
                .when()
                .post(urlPath);
        //returning response, statuscode,statusline
        return new GetApiResponse(response,response.getStatusCode(),response.getStatusLine());
    }

    public GetApiResponse setUpForProfileImageUpload(Map<String, Object> imageMap, String urlPath,File profileImageFile){
        /*
         * @MethodName- setUpForImageUpload
         * @objective- to pass the entities that are required for the setUp of the image upload
         * @returnType-object,int,string
         */

        //Specify the url
        RestAssured.baseURI ="http://13.232.186.165:8080/api/v1/campus/college/upload/profile";
        Response response=(Response) given().log().all().config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
                .header("Content-Type", "multipart/form-data")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Connection", "keep-alive")
                .body(imageMap.toString())
                .multiPart("profile", profileImageFile)
                .when()
                .post(urlPath);

        //returning response, statuscode,statusline
        return new GetApiResponse(response,response.getStatusCode(),response.getStatusLine());
    }

    public GetApiResponse setUpForUpdateAddress(AddressDo addressDo,String collegeCode){
        /*
         * @MethodName- setUpForUpdateAddress
         * @objective- to pass the entities that are required for the setUp of the updateAddress
         * @returnType-object,int,string
         */

        //Specify the url
        RestAssured.baseURI ="http://13.232.186.165:8080/api/v1/campus/college/updateAddress";
        RequestSpecification httprequest = RestAssured.given();
         RequestParamForUpdateAddress requestParamForUpdateAddress = new RequestParamForUpdateAddress();
        JSONObject requestParamObj = requestParamForUpdateAddress.getRequestParamForUpdateAddress(addressDo);
        //to convert in string from json
        httprequest.body(requestParamObj.toJSONString());
        //passing content-type  in header
        httprequest.header("Content-Type","application/json");
        //passing auth token in header
        httprequest.header("Authorization","\"eyJraWQiOiJEWmdxUElCV3RSQWNhTzFIZ1g1eVRXbDJSdVNQOGlIYmtBVDFMR2lLVVwvYz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIyMjg3YWRmYi00YjRmLTQzYWYtYmVlMy1kOGVlM2M3Y2FhZjIiLCJjb2duaXRvOmdyb3VwcyI6WyJjb2xsZWdlIl0sImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfejFORFhnU1pxIiwiY2xpZW50X2lkIjoiNTQ4ZzZoN3N1MWhzdTBpZTVzbWo4b28xMDEiLCJvcmlnaW5fanRpIjoiMWM2MjZmNWEtZjczNS00NTMzLWJiNmEtOTU3YTgwZWZiMWZhIiwiZXZlbnRfaWQiOiIyMDVhMTVkZS1lN2QyLTRmODMtYjZkMC01NWMyZjNiMmY3ZjMiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6ImF3cy5jb2duaXRvLnNpZ25pbi51c2VyLmFkbWluIiwiYXV0aF90aW1lIjoxNjcxNzkzMjgyLCJleHAiOjE2NzE3OTY4ODIsImlhdCI6MTY3MTc5MzI4MiwianRpIjoiMGY3YzY4YjgtNmRlMy00Yzc5LWJmMWUtNmQ0MjU5NGNkOTNlIiwidXNlcm5hbWUiOiJtb2hpdHJhanB1dDE0MTJAZ21haWwuY29tIn0.uRljKxRUeKL4cmrWaCH2tYoswdhWtINE28Lhi5Tfh5vHay3tgLFt5pF4KoPvmHCkgAuct-GNaqCxMDgKdq6E7Nt-E9Fy6SsvdshLPzrGlcF_tzDNX9nfAHNh5wKVQMdbxz2CUW7O1Yr3hRCD_OX9GifDVO1-MWB-uQcd7_EQ3Gxs618OvvJ7Ku64bLqsP5Z5Sh6P-fTKR-qSyjJzDns549ekV7Tj4YhwpzI3JRDlwzIG8B89YyrMh1vIcc4nKUtaAyVz311iKG5Gnk98Ppz6vzXPu0Q9BmDAsctt1Lpjj2B5qU-DyYLxyp6VwUkNiWXpP4HpeXyDNfpjSm2mKpFmjg\"");
        //passing method and url param in request and storing it in response.
        Response response = httprequest.request(Method.POST,collegeCode);
        //returning response, statuscode,statusline
        return new GetApiResponse(response,response.getStatusCode(),response.getStatusLine());
    }

}
