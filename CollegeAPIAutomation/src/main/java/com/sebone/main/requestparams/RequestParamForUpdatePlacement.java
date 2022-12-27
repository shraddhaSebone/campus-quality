package com.sebone.main.requestparams;

import com.sebone.main.data.UpdatePlacementDo;
import org.json.simple.JSONObject;

public class RequestParamForUpdatePlacement {
    /*
     * @ClassName- RequestParamForUpdatePlacement
     * @autherName- Varsha Rane
     * @Objective- passing the entities in the request parameters
     */

    public JSONObject getRequestParamForUpdatePlacement( UpdatePlacementDo updatePlacementDo){
        /*
         * @methodName-getRequestParamForUpdatePlacement
         *
         * @MethodObjective- passing the updatePlacementDo in the request params
         *
         * @returnType- JSONObject
         */

        // creating jsonobject named requestParams and passing respective values in it.
        JSONObject requestParamForUpdatePlacement = new JSONObject();
        requestParamForUpdatePlacement.put("branch",updatePlacementDo.getBranch());
        requestParamForUpdatePlacement.put("passingYear",updatePlacementDo.getPassingYear());
        requestParamForUpdatePlacement.put("course",updatePlacementDo.getCourse());
        requestParamForUpdatePlacement.put("companyName",updatePlacementDo.getCompanyName());
        requestParamForUpdatePlacement.put("noOfStudents",updatePlacementDo.getNoOfStudents());
        requestParamForUpdatePlacement.put("placementId",updatePlacementDo.getPlacementId());

        //returning jsonObject
        return requestParamForUpdatePlacement;
    }
}
