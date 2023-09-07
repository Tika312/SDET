package com.digitalnomads.api.controllers;

import com.digitalnomads.ApiRequest;
import com.digitalnomads.api.asserts.ApiAssert;
import com.digitalnomads.api.entities.Course;
import com.digitalnomads.api.entities.User;
import com.digitalnomads.api.utils.EntityManager;
import com.digitalnomads.api.utils.JacksonUtils;
import com.digitalnomads.wiremock.UserFromRegress;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public UserFromRegress createNewUserFromReg(UserFromRegress newUser){
        String jsonUser = JacksonUtils.fromObjectToJson(newUser);
        return super.post(getEndPoint(API,V1,SIGN_UP), jsonUser).as(UserFromRegress.class);
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

    public void createNewUserOrDelete(){
        if (checkUserLimit()){
            User[]totalUser = getAllUsers().as(User[].class);
            String actualResult = deleteUser(totalUser[0].getId());
            String expectedResult = "Operation completed successfully";
            Assert.assertEquals(actualResult,expectedResult);
            log.info("Successfully deleted this User");
        }else {
            User newUser = EntityManager.generateUser();
            User actualUser = createNewUser(newUser);
            ApiAssert.assertThat(getResponse())
                    .isCorrectStatusCode(200)
                    .assertUser()
                    .isIdNotNull()
                    .isEqual(newUser);
            log.info("Successfully created a new User: {}", actualUser.toString());
        }
    }

//    public List<String> checkUsersCourses(String userId){
//        List<String>listOfCourses =  getUserById(userId).jsonPath()
//                .getList("courses");
//        log.info("User {}, has this courses {}", userId, listOfCourses);
//        return listOfCourses;
//    }

    public String checkUsersCourses(String userId){
        String actual =  getUserById(userId).jsonPath()
                .getString("courses");
        log.info("User {}, has this courses {}", userId, actual);
        return actual;
    }

    public Course converJsonToCourse(String json){
        Gson gson = new Gson();
        Course course = gson.fromJson(json,Course.class);
        return course;
    }



    public  String convertToJSON(String keyValueString) {
        keyValueString = keyValueString.replace("[[", "{");
        keyValueString  = keyValueString.replace("]]", "}");
        // Remove curly braces from the start and end, as it's not valid JSON
        keyValueString = keyValueString.substring(1, keyValueString.length() - 1);

        // Split the string into key-value pairs
        String[] keyValuePairs = keyValueString.split(", ");

        // Create a JSON formatted string
        StringBuilder jsonBuilder = new StringBuilder("{");
        for (String keyValue : keyValuePairs) {
            String[] parts = keyValue.split(":");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();

                if (jsonBuilder.length() > 1) {
                    jsonBuilder.append(", ");
                }
                jsonBuilder.append("\"").append(key).append("\": ").append("\"").append(value).append("\"");
            }
        }
        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }


    public Course[] getUserCourses(String userId){
        User user = getUserById(userId).as(User.class);
        return user.getCourses();
    }

    public  String convertToJSONNew(String dataString) {
        List<JsonObject> jsonObjectList = new ArrayList<>();

        // Используем регулярное выражение для извлечения данных внутри скобок "[...]"
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(dataString);

        while (matcher.find()) {
            String dataElement = matcher.group(1);
            String[] keyValuePairs = dataElement.split(", ");
            JsonObject jsonObject = new JsonObject();

            for (String keyValue : keyValuePairs) {
                String[] parts = keyValue.split(":");
                String key = parts[0].trim();
                String value = parts[1].trim();
                jsonObject.addProperty(key, value);
            }

            jsonObjectList.add(jsonObject);
        }

        Gson gson = new Gson();
        return gson.toJson(jsonObjectList);
    }
}
