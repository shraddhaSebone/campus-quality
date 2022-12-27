package com.sebone.main;

import com.sebone.main.college.SetupForCollege;
import com.sebone.main.data.UpdateCourseDo;
import com.sebone.main.data.UpdatePlacementDo;
import com.sebone.main.response.GetApiResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class UpdateCourseAutomationTest {
    /*
     * @autherName- varsha rane
     * @className- UpdateCourseAutomationTest
     * @Objective- to automate the testcases for the UpdateCourseDetails api.
     */

    private SetupForCollege collegeCode;

    //@MethodObjective- url to execute before class.
    @BeforeClass
    public void url() {

        collegeCode = new SetupForCollege();
    }

    //@MethodObjective- to verify the status code is "200" and response .
//    public void checkNewCourseDetailsResponse(){
//        UpdateCourseDo updateCourseDo = new UpdateCourseDo();
//        updateCourseDo.setCourseName("CSE");
//        updateCourseDo.setBranchName("Mtech");
//        updateCourseDo.setPlacedStudents("15");
//        updateCourseDo.setTotalStudents("22");
//        GetApiResponse getApiResponse = collegeCode.setUpForUpdateCourse(updateCourseDo,"111");
//        int statusCode= getApiResponse.getResponse().statusCode();
//        Assert.assertEquals(200,statusCode);
//
//    }
}
