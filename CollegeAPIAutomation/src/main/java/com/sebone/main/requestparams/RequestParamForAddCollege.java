package com.sebone.main.requestparams;
import com.sebone.main.data.AddCollegeDo;
import com.sebone.main.data.AddressDo;
import org.json.simple.JSONObject;

public class RequestParamForAddCollege {
    /*
     * @ClassName- RequestParamForAddCollege
     * @autherName- Varsha Rane
     * @Objective- passing the entities in the request parameters
     */

    public JSONObject getRequestParamForAddCollege(AddCollegeDo addCollegeDo){
        /*
         * @methodName-getRequestParams
         *
         * @MethodObjective- passing the addCollegeDo in the request params
         *
         * @returnType- JSONObject
         */

        // creating jsonobject named requestParams and passing respective values in it.
        JSONObject requestParamForAddCollege = new JSONObject();
        requestParamForAddCollege.put("collegeName",addCollegeDo.getCollegeName());
        requestParamForAddCollege.put("type",addCollegeDo.getType().toString());
        requestParamForAddCollege.put("email",addCollegeDo.getEmail());
        requestParamForAddCollege.put("address",getAddress(addCollegeDo.getAddressDoObj()));
        requestParamForAddCollege.put("contactNumber",addCollegeDo.getContactNumber());
        requestParamForAddCollege.put("password",addCollegeDo.getPassword());
        requestParamForAddCollege.put("collegeCode",addCollegeDo.getCollegeCode());
        //returning requestparam for addcollege
        return requestParamForAddCollege;
    }

    /*
     * @methodName-address
     *
     * @MethodObjective- putting the address in the request params by creating arraylist of type address
     *
     * @returnType- JSONObject arraylist
     */
   public JSONObject getAddress(AddressDo addressDo){
       JSONObject addressObj = new JSONObject();
       addressObj.put("addressLine",addressDo.getAddressLine());
       addressObj.put("landmark",addressDo.getLandmark());
       addressObj.put("city",addressDo.getCity());
       addressObj.put("state",addressDo.getState().toString());
       return addressObj;
   }

}
