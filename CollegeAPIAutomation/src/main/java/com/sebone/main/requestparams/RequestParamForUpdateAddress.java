package com.sebone.main.requestparams;
import com.sebone.main.data.AddressDo;
import org.json.simple.JSONObject;

public class RequestParamForUpdateAddress {
    /*
     * @ClassName- RequestParamForUpdateAddress
     * @autherName- Varsha Rane
     * @Objective- passing the entities in the request parameters
     */

    public JSONObject getRequestParamForUpdateAddress(AddressDo addressDo){
        /*
         * @methodName-getRequestParamForUpdateAddress
         *
         * @MethodObjective- passing the addressDo in the request params
         *
         * @returnType- JSONObject
         */

        // creating jsonobject named requestParams and passing respective values in it.
        JSONObject requestParamForUpdateAddress = new JSONObject();
        requestParamForUpdateAddress.put("addressLine",addressDo.getAddressLine());
        requestParamForUpdateAddress.put("landmark",addressDo.getLandmark());
        requestParamForUpdateAddress.put("city",addressDo.getCity());
        requestParamForUpdateAddress.put("state",addressDo.getState().toString());

        //returning jsonObject
        return requestParamForUpdateAddress;
    }

}
