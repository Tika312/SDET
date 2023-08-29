package com.digitalnomads.api.controllers;

import com.digitalnomads.ApiRequest;
import io.restassured.response.Response;

import static com.digitalnomads.api.application.TalentLMSBaseEndPoints.*;

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
}
