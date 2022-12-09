package college;

import org.json.simple.JSONObject;

public class RequestParamForPartialUpdate {
    /*
     * @ClassName- RequestParamForPartialUpdate
     * @autherName- Varsha Rane
     * @Objective- passing the entities in the request parameters
     */

    public JSONObject getRequestParamForPartialUpdate(UpdateCollegeDetailsPartialDo updateCollegeDetailsPartialDo){
        /*
         * @methodName-getRequestParamForPartialUpdate
         *
         * @MethodObjective- passing the UpdateCollegeDetailsPartialDo in the request params
         *
         * @returnType- JSONObject
         */

        // creating jsonobject named requestParams and passing respective values in it.
        JSONObject requestParamForPartialUpdate = new JSONObject();
        requestParamForPartialUpdate.put("collegeCode",updateCollegeDetailsPartialDo.getCollegeCode());
        requestParamForPartialUpdate.put("collegeName",updateCollegeDetailsPartialDo.getCollegeName());
        requestParamForPartialUpdate.put("city",updateCollegeDetailsPartialDo.getCity());
        requestParamForPartialUpdate.put("ranking",updateCollegeDetailsPartialDo.getRanking());
        requestParamForPartialUpdate.put("contactNumber",updateCollegeDetailsPartialDo.getContactNumber());
        requestParamForPartialUpdate.put("email",updateCollegeDetailsPartialDo.getEmail());
        requestParamForPartialUpdate.put("noOfStudents",updateCollegeDetailsPartialDo.getNoOfStudents());
        requestParamForPartialUpdate.put("placedStudents",updateCollegeDetailsPartialDo.getPlacedStudents());
        requestParamForPartialUpdate.put("overview",updateCollegeDetailsPartialDo.getOverview());
        requestParamForPartialUpdate.put("awards",updateCollegeDetailsPartialDo.getAwards());
        requestParamForPartialUpdate.put("website",updateCollegeDetailsPartialDo.getWebsite());

        return requestParamForPartialUpdate;
    }
}
