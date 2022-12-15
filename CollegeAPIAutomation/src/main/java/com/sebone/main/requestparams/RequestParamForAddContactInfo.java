package com.sebone.main.requestparams;

import com.sebone.main.data.AddCollegeDo;
import com.sebone.main.data.AddContactDo;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class RequestParamForAddContactInfo {
    /*
     * @ClassName- RequestParamForAddContactInfo
     * @autherName- Varsha Rane
     * @Objective- passing the entities in the request parameters
     */

    public JSONObject getRequestParamForAddContactInfo(AddContactDo addContactDo){
        /*
         * @methodName-getRequestParamForAddContactInfo
         *
         * @MethodObjective- passing the addContactDo in the request params
         *
         * @returnType- JSONObject
         */

        // creating jsonobject named requestParams and passing respective values in it.
        JSONObject requestParamForAddContactInfo = new JSONObject();
        requestParamForAddContactInfo.put("firstName",addContactDo.getFirstName());
        requestParamForAddContactInfo.put("lastName",addContactDo.getLastName());
        requestParamForAddContactInfo.put("email",addContactDo.getEmail());
        requestParamForAddContactInfo.put("contactNumber",addContactDo.getContactNumber());
        requestParamForAddContactInfo.put("message",addContactDo.getMessage());
        ArrayList<JSONObject> contactInfoList = new ArrayList<>();
        //adding in arraylist
        contactInfoList.add(requestParamForAddContactInfo);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contactInfo",contactInfoList);
       //returning jsonObject
       return jsonObject;
    }

}
