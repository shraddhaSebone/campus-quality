package college;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class RequestParamForUpdateCollege {
    /*
     * @ClassName- RequestParamForUpdateCollege
     * @autherName- Varsha Rane
     * @Objective- passing the entities in the request parameters
     */

    public JSONObject getRequestParamForUpdateCollege(UpdateCollegeDo updateCollegeDo){
        /*
         * @methodName-getRequestParamForUpdateCollege
         *
         * @MethodObjective- passing the updateCollegeDo in the request params
         *
         * @returnType- JSONObject
         */

        // creating jsonobject named requestParams and passing respective values in it.
        JSONObject requestParamForUpdateCollege = new JSONObject();
        requestParamForUpdateCollege.put("collegeName",updateCollegeDo.getCollegeName());
        requestParamForUpdateCollege.put("type",updateCollegeDo.getType().toString());
        requestParamForUpdateCollege.put("email",updateCollegeDo.getEmail());
        requestParamForUpdateCollege.put("address",getAddress(updateCollegeDo.getAddressDoObj()));
        requestParamForUpdateCollege.put("contactNumber",updateCollegeDo.getContactNumber());
        requestParamForUpdateCollege.put("password",updateCollegeDo.getPassword());
        requestParamForUpdateCollege.put("collegeCode",updateCollegeDo.getCollegeCode());
        requestParamForUpdateCollege.put("approvedBy",updateCollegeDo.getApprovedBy());
        requestParamForUpdateCollege.put("city",updateCollegeDo.getCity());
        requestParamForUpdateCollege.put("overview",updateCollegeDo.getOverview());
        requestParamForUpdateCollege.put("awards",updateCollegeDo.getAwards());
        requestParamForUpdateCollege.put("established",updateCollegeDo.getEstablished());
        requestParamForUpdateCollege.put("ranking",updateCollegeDo.getRanking());
        requestParamForUpdateCollege.put("mapLocation",updateCollegeDo.getMapLocation());
        requestParamForUpdateCollege.put("verified",updateCollegeDo.getVerified());
        requestParamForUpdateCollege.put("facilities",updateCollegeDo.getFacilities());
        requestParamForUpdateCollege.put("noOfStudents",updateCollegeDo.getNoOfStudents());
        requestParamForUpdateCollege.put("profileImage",updateCollegeDo.getProfileImage());
        requestParamForUpdateCollege.put("images",updateCollegeDo.getImages());
        requestParamForUpdateCollege.put("videos",updateCollegeDo.getVideos());
        requestParamForUpdateCollege.put("examsAccepted",updateCollegeDo.getExamsAccepted());
        requestParamForUpdateCollege.put("collegeStatus",updateCollegeDo.getCollegeStatus());
        requestParamForUpdateCollege.put("website",updateCollegeDo.getWebsite());
        requestParamForUpdateCollege.put("placedStudents",updateCollegeDo.getPlacedStudents());
        requestParamForUpdateCollege.put("courses",getCourseList(updateCollegeDo.getCoursesArrayList()));
        requestParamForUpdateCollege.put("placements",getPlacementList(updateCollegeDo.getPlacementsDoArrayList()));
        //returning requestparam for updateCollege
        return requestParamForUpdateCollege;
    }

    public JSONObject getAddress(AddressDo addressDo){
        /*
         * @methodName-address
         *
         * @MethodObjective- putting the address in the request params by creating arraylist of type address
         *
         * @returnType- JSONObject arraylist
         */

        JSONObject addressObj = new JSONObject();
        addressObj.put("addressLine",addressDo.getAddressLine());
        addressObj.put("landmark",addressDo.getLandmark());
        addressObj.put("city",addressDo.getCity());
        addressObj.put("state",addressDo.getState().toString());

        return addressObj;
    }

    public JSONObject getCourseList(ArrayList<CoursesDo> coursesArrayList){
        /*
         * @methodName-courseList
         *
         * @MethodObjective- putting the course in the request params by creating arraylist of type address
         *
         * @returnType- JSONObject arraylist
         */

        ArrayList<JSONObject> courseList = new ArrayList<JSONObject>();
        JSONObject courses = new JSONObject();
        courses.put("courseName",coursesArrayList.get(0).getCourseName());
        courses.put("branchName",coursesArrayList.get(0).getBranchName());
        courses.put("placedStudents",coursesArrayList.get(0).getPlacedStudents());
        courses.put("totalStudents",coursesArrayList.get(0).getTotalStudents());
        //json obj created  and json obj stored in it
        courseList.add(courses);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("courses",courseList);
        return jsonObject1;
    }

    public JSONObject getPlacementList(ArrayList<PlacementsDo> placementArrayList) {
        /*
         * @methodName-placementList
         *
         * @MethodObjective- putting the placement in the request params by creating arraylist of type address
         *
         * @returnType- JSONObject arraylist
         */
        ArrayList<JSONObject> placementList = new ArrayList<JSONObject>();
        JSONObject placements = new JSONObject();
        placements.put("branch", placementArrayList.get(0).getBranch());
        placements.put("passingYear", placementArrayList.get(0).getPassingYear());
        placements.put("course", placementArrayList.get(0).getCourse());
        placements.put("companyName", placementArrayList.get(0).getCompanyName());
        placements.put("noOfStudents", placementArrayList.get(0).getNoOfStudents());
        //added json obj in arrraylist
        placementList.add(placements);
        //json obj created and added in it json obj
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("placements",placementList);
        return jsonObject1;
    }

}
