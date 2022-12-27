package com.sebone.main.requestparams;

import com.sebone.main.data.UpdateCourseDo;
import com.sebone.main.data.UpdatePlacementDo;
import org.json.simple.JSONObject;

public class RequestParamForUpdateCourse {
    /*
     * @ClassName- RequestParamForUpdateCourse
     * @autherName- Varsha Rane
     * @Objective- passing the entities in the request parameters
     */

    public JSONObject getRequestParamForUpdateCourse(UpdateCourseDo updateCourseDo){
        /*
         * @methodName-getRequestParamForUpdatePlacement
         *
         * @MethodObjective- passing the updateBranchDo in the request params
         *
         * @returnType- JSONObject
         */

        // creating jsonobject named requestParams and passing respective values in it.
        JSONObject requestParamForUpdateCourse = new JSONObject();
        requestParamForUpdateCourse.put("courseName",updateCourseDo.getCourseName());
        requestParamForUpdateCourse.put("branchName",updateCourseDo.getBranchName());
        requestParamForUpdateCourse.put("placedStudents",updateCourseDo.getPlacedStudents());
        requestParamForUpdateCourse.put("totalStudents",updateCourseDo.getTotalStudents());
        requestParamForUpdateCourse.put("courseId",updateCourseDo.getCourseId());

        //returning jsonObject
        return requestParamForUpdateCourse;
    }
}
