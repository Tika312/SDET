package com.digitalnomads.api.controllers;

import com.digitalnomads.ApiRequest;
import com.digitalnomads.api.entities.Category;
import com.digitalnomads.api.entities.Course;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.digitalnomads.api.application.TalentLMSBaseEndPoints.*;
@Slf4j
public class CategoryController extends ApiRequest {
    public CategoryController(String url) {
        super(url, API_KEY);
    }

    public Response getAllCategories(){
        return this.response = super.get(getEndPoint(API,V1,CATEGORIES));
    }

    public int getNumberOfCategories(){
        Category[]categories = getAllCategories().as(Category[].class);
        int numberOfCategories = categories.length;
        return numberOfCategories;
    }

    public Response getCategoryById(String categoryId){
        return this.response = super.getWithParams(getEndPoint(API,V1,CATEGORIES), generateParams("id", categoryId));
    }

    public List<String>getListOfCourses(String categoryId){
        List<String> listOfCourses = getCategoryById(categoryId).jsonPath()
                .getList("courses");
        log.info("These are all courses: {}, under this categoryId: {}", listOfCourses, categoryId);
        return listOfCourses;
    }

    public int getNumberOfCoursesInCategory(String categoryId){
        int numberOfCourses = getListOfCourses(categoryId).size();
        log.info("Total number of Courses in this category: {}", numberOfCourses);
        return numberOfCourses;
    }

}
