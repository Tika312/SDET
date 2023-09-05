package com.digitalnomads.api.controllers;

import com.digitalnomads.ApiRequest;
import com.digitalnomads.api.entities.Course;
import com.digitalnomads.api.entities.User;
import com.digitalnomads.api.utils.JacksonUtils;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.digitalnomads.api.application.TalentLMSBaseEndPoints.*;
@Slf4j
public class UserController extends ApiRequest {



    public UserController(String url) {
        super(url, API_KEY);
    }

    public Response getAllUsers(){
        return this.response = super.get(getEndPoint(API,V1,USERS));
    }

    public Response getUserByEmail(String email){
        return this.response = super.getWithParams(getEndPoint(API,V1,USERS)
                ,generateParams(EMAIL, email));
    }

    public Response getUserById(String userId){
        return this.response = super.getWithParams(getEndPoint(API,V1,USERS), generateParams("id", userId));
    }

    public boolean isUserOnline(String userId){
        boolean result = super.getWithParams(getEndPoint(API,V1,IS_USER_ONLINE),generateParams("user_id", userId))
                .jsonPath().get("online");
        return result;
    }

    public User createNewUser(User newUser){
        String jsonUser = JacksonUtils.fromObjectToJson(newUser);
        return super.post(getEndPoint(API,V1,SIGN_UP), jsonUser).as(User.class);
    }

    public String deleteUser(String userId){
        Map<String,String> params = new LinkedHashMap<>();
        params.put("user_id", userId);
        params.put("deleted_by_user_id", "1");
        String result = super.postWithParams(getEndPoint(API,V1,DELETE_USER), params)
                .jsonPath().get("message");
        return result;
    }

    public int getNumberOfUsers(){
        User[]allUsers = getAllUsers().as(User[].class);
        int numberOfUsers =allUsers.length;
        log.info("Number of Courses are: {}", numberOfUsers);
        return numberOfUsers;
    }

    public boolean checkUserLimit(){
        int numberOfUsers = getNumberOfUsers();
        if (numberOfUsers==5){
            return true;
        }else {
            return false;
        }
    }




}
