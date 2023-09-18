package com.digitalnomads.api.controllers;

import com.digitalnomads.ApiRequest;
import com.digitalnomads.api.entities.Group;
import com.digitalnomads.api.utils.MockData;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.digitalnomads.api.application.TalentLMSBaseEndPoints.*;
@Slf4j
public class GroupController extends ApiRequest {
    public GroupController(String url) {
        super(url, API_KEY);
    }

    public Response getAllGroups(){
        return this.response = super.get(getEndPoint(API,V1,GROUPS));
    }

    public int getNumberOfAllGroups(){
        Group[]groups = getAllGroups().as(Group[].class);
        int numberOfGroups = groups.length;
        return numberOfGroups;
    }

    public Response getGroupById(String groupId){
        return this.response = super.getWithParams(getEndPoint(API,V1,GROUPS),generateParams("id",groupId));
    }

    public List<String> getListOfUsersInThisGroup(String groupId){
        List<String>listOfUsers = getGroupById(groupId).jsonPath()
                .getList("users");
        log.info("The list of Users in this group: {}", listOfUsers);
        return listOfUsers;
    }
    public int getNumberOfUsersInGroup(String groupId){
        int numberOfUsers = getListOfUsersInThisGroup(groupId).size();
        log.info("Number of Users in this group: {}", numberOfUsers);
        return numberOfUsers;
    }

    public List<String> getListOfCoursesInGroup(String groupId){
        List<String> listOfCourses = getGroupById(groupId).jsonPath()
                .getList("courses");
        log.info("The list of Courses in this group: {}", listOfCourses);
        return listOfCourses;
    }

    public int getNumberOfCoursesInGroup(String groupId){
        int numberOfCourses = getListOfCoursesInGroup(groupId).size();
        log.info("Number of Courses in this group: {}", numberOfCourses);
        return numberOfCourses;
    }

//    public Group createGroup(Group group){
//        String jsonGroup = JacksonUtils.fromObjectToJson(group);
//       return super.post(getEndPoint(API,V1,CREATE_GROUP),jsonGroup).as(Group.class);
//    }

    public Response createGroup(String groupName, String groupDescription){
        Map<String,String> params = new LinkedHashMap<>();
        params.put("name", groupName);
        params.put("description", groupDescription);
        params.put("key", MockData.generateGroupKey());
        return super.postWithParams(getEndPoint(API,V1,CREATE_GROUP),params);
    }

    public String deleteGroup(String groupId){
        Map<String,String>params = new LinkedHashMap<>();
        params.put("group_id", groupId);
        params.put("delete_by_user_id", "1");
        String message = super.postWithParams(getEndPoint(API,V1,DELETE_GROUP),params)
                .jsonPath().getString("message");
        return message;
    }
    public boolean checkGroupLimit(){
        int numberOfGroups = getNumberOfAllGroups();
        if (numberOfGroups==20){
            return true;
        }else {
            return false;
        }
    }
    public Response addUserToGroup(String userId,String groupKey){
        Map<String,String>params = new LinkedHashMap<>();
        params.put("user_id", userId);
        params.put("group_key",groupKey);
        return super.getWithParams(getEndPoint(API,V1,ADD_USER_TO_GROUP),params);
    }

    public Response removeUserFromGroup(String userId,String groupId){
        Map<String,String>params = new LinkedHashMap<>();
        params.put("user_id",userId);
        params.put("group_id",groupId);
        return super.getWithParams(getEndPoint(API,V1,REMOVE_USER_FROM_GROUP),params);
    }

    public Response addCourseToGroup(String courseId,String groupId){
        Map<String,String>params = new LinkedHashMap<>();
        params.put("course_id", courseId);
        params.put("group_id",groupId);
        return super.getWithParams(getEndPoint(API,V1,ADD_COURSE_TO_GROUP),params);
    }
}
