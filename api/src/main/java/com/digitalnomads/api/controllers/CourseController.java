package com.digitalnomads.api.controllers;

import com.digitalnomads.ApiRequest;
import com.digitalnomads.api.entities.Course;
import com.digitalnomads.api.utils.JacksonUtils;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.digitalnomads.api.application.TalentLMSBaseEndPoints.*;
@Slf4j
public class CourseController extends ApiRequest {
    public CourseController(String url) {
        super(url, API_KEY);
    }

    public Response getAllCourses(){
        return
        this.response = super.get(getEndPoint(API,V1,COURSES));
    }

    public int getNumberOfCourses(){
        Course[]allCourses = getAllCourses().as(Course[].class);
        int numberOfCourses =allCourses.length;
        log.info("Number of Courses are: {}", numberOfCourses);
        return numberOfCourses;
    }

    public Response getCourseById(String courseId){
        return this.response = super.getWithParams(getEndPoint(API,V1,COURSES), generateParams("id",courseId));
    }

    public List<String> getListOfUsers(String courseId){
        List<String> list = getCourseById(courseId).jsonPath().getList("users");
        log.info("The list of Users in this Group: {}", list);
        return list;
    }
    public int getNumberOfUsers(String courseId){
        int numberOfUsers = getListOfUsers(courseId).size();
        log.info("The number of Users in this Group: {}", numberOfUsers);
        return numberOfUsers;
    }

    public Response getUserStatusCourse(String courseId,String userId){
        Map<String,String> params = new LinkedHashMap<>();
        params.put("course_id", courseId);
        params.put("user_id", userId);
        return super.getWithParams(getEndPoint(API,V1,USER_STATUS), params);
    }

    public Course createCourse(Course course){
        String jsonCourse = JacksonUtils.fromObjectToJson(course);
        return super.post(getEndPoint(API,V1,CREATE_COURSE), jsonCourse).as(Course.class);
    }

    public String deleteCourse(String courseId){
        Map<String,String> params = new LinkedHashMap<>();
        params.put("course_id", courseId);
        params.put("deleted_by_user_id", "1");
        String message = super.postWithParams(getEndPoint(API,V1,DELETE_COURSE),params)
                .jsonPath().getString("message");
        return message;
    }

    public boolean checkCoursesLimit(){
        int totalNumberOfCourses = getNumberOfCourses();
        if (totalNumberOfCourses == 10){
            return true;
        }else {
            return false;
        }
    }

    public Response addUserToCourse(String userId, String courseId){
        Map<String,String> params = new LinkedHashMap<>();
        params.put("user_id", userId);
        params.put("course_id", courseId);
        return super.postWithParams(getEndPoint(API,V1,ADD_USER_TO_COURSE),params);
    }

}
