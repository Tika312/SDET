package com.digitalnomads.api.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class Intro {

    @Test
    void getAllUsers(){

        RestAssured.baseURI = "https://reqres.in"; // основной url

        RequestSpecification requestSpecification =   // передаем виды параметра:path param,query param,header,body
                given()                               // все эти параметры передаем в requestSpecification
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON);
        Response response = requestSpecification     // response хранится тело ответа, requestSpecification хранится все параметры которые мы передали
                .request(Method.GET,"/api/users");//с помощью request делаем запрос GET
        response.prettyPrint();// результат этого запроса хранится в response который распечатываем

        JsonPath jsonPath = response.jsonPath();
        String firstName = jsonPath.getString("data[0].first_name"); // получаем имя в массиве data у 0 индекса firstname
        System.out.println(firstName);
        String email = jsonPath.getString("data[5].email");//// получаем email в массиве data у 5 индекса email
        System.out.println(email);

    }

    @Test
    void getSingleUserById(){
        RestAssured.baseURI = "https://reqres.in";

        RequestSpecification requestSpecification =
                given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON);

        Response response = requestSpecification
                .request(Method.GET, "/api/users/2"); // делаем GET request что получить одного user
        response.prettyPrint();// у кого id = 2
        Assert.assertEquals(response.getStatusCode(), 200);
        // сравниваем статус код от сервера и наш ожидаемый код
        response.body();//Получаем тело ответа
        response.statusCode();//статус код от сервера
        response.contentType();//формат данных которые мы получили
        response.cookies();// cookies

    }

    @Test
    void createANewUser(){
        RestAssured.baseURI = "https://reqres.in";

        String requestBody = " {\n" +
                "    \"name\": \"Asman\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        RequestSpecification requestSpecification =
                given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestBody);

        Response response = requestSpecification
                .request(Method.POST, "/api/users");


        User asman = response.as(User.class);
        System.out.println(asman);

//        JsonPath jsonPath = response.jsonPath();
//
//        String name = jsonPath.getString("name");
//        String job = jsonPath.getString("job");
//        System.out.println(name);
//        System.out.println(job);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(asman.getName(), "Asman");
        Assert.assertEquals(asman.getJob(), "leader");
    }

    @Test
    void updateUserDetailsByPutMethod(){
        String requestBody = "{\n" +
                "    \"name\": \"Neo\",\n" +
                "    \"job\": \"The Chosen\"\n" +
                "}";

        RequestSpecification requestSpecification =
                given()
                        .baseUri("https://reqres.in/api")
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(requestBody);
        Response response = requestSpecification
                .request(Method.PUT, "/users/2");

        User neo = response.as(User.class);
        Assert.assertEquals(neo.getName(), "Neo");
        Assert.assertEquals(neo.getJob(), "The Chosen");
        Assert.assertEquals(response.getStatusCode(), 200);
        response.prettyPrint();
        System.out.println(neo);
    }

    @Test
    void deleteUserById(){
        RequestSpecification requestSpecification =
                given()
                        .baseUri("https://reqres.in/");
        Response response = requestSpecification
                .request(Method.DELETE, "api/users/2");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 204);
    }




}
