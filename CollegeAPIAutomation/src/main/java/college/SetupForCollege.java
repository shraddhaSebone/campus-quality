package college;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
        //passing method and url param in request and storing it in response
        Response response = httprequest.request(Method.PUT);
        //returning response, statuscode, statusline
        return new GetApiResponse(response,response.getStatusCode(),response.statusLine()) ;
    }

    public GetApiResponse setUpForUpdatePartialCollege(UpdateCollegeDetailsPartialDo updateCollegeDetailsPartialDo,String collegeCode){
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
        //passing method and url param in request and storing it in response.
        Response response = httprequest.request(Method.PATCH,collegeCode);
        //returning response, statuscode,statusline
        return new GetApiResponse(response,response.getStatusCode(),response.getStatusLine());
    }
}
