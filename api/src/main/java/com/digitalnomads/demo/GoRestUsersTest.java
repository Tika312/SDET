package com.digitalnomads.demo;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GoRestUsersTest {




        @Test
        void getUsers() {
            RequestSpecification requestSpecification =
                    given()
                            .baseUri("https://gorest.co.in/public/v2")
                            .header("Authorization", "Bearer 54f71521a469c24f2bd1573d0faf9f5e5f24d5a6d31b44b8d757c22f5ea5b1ba")
                            .contentType(ContentType.JSON)
                            .accept(ContentType.JSON);
            Response response = requestSpecification
                    .request(Method.GET, "/users");
            response.prettyPrint();
        }

        @Test
        void createANewUser(){
            String requestBody = "{\n" +
                    "        \"name\": \"Kylian Mbappe\",\n" +
                    "        \"email\": \"Mbape@gmail.com\",\n" +
                    "        \"gender\": \"male\",\n" +
                    "        \"status\": \"Active\"\n" +
                    "    }";

            RequestSpecification requestSpecification =
                    given()
                            .baseUri("https://gorest.co.in/public/v2")
                            .header("Authorization", "Bearer 54f71521a469c24f2bd1573d0faf9f5e5f24d5a6d31b44b8d757c22f5ea5b1ba")
                            .contentType(ContentType.JSON)
                            .accept(ContentType.JSON)
                            .body(requestBody);

            Response response = requestSpecification
                    .request(Method.POST, "/users");
            response.prettyPrint();
            Assert.assertEquals(response.getStatusCode(), 201);
        }

        @Test
    void updateAboveRegisteredUsersEmail(){
            String newEmail = "{\n" +
                    "        \"email\": \"Mbappe10@gmail.com\"\n" +
                    "    }";
            RequestSpecification requestSpecification =
                    given()
                            .baseUri("https://gorest.co.in/public/v2")
                            .body(newEmail)
                            .header("Authorization", "Bearer 54f71521a469c24f2bd1573d0faf9f5e5f24d5a6d31b44b8d757c22f5ea5b1ba")
                            .contentType(ContentType.JSON)
                            .accept(ContentType.JSON);

            Response response = requestSpecification.request(Method.PUT, "/users/4697559");
            JsonPath jsonPath = response.jsonPath();
            response.prettyPrint();
            String resultEmail = jsonPath.getString("email");
            Assert.assertEquals(resultEmail, "Mbappe10@gmail.com");
            Assert.assertEquals(response.getStatusCode(), 200);
        }

        @Test
    void changeTheAboveStatusOfUser(){
            String newStatus = "{\n" +
                    "        \"status\": \"inactive\"\n" +
                    "    }";
            RequestSpecification requestSpecification =
                    given()
                            .baseUri("https://gorest.co.in/public/v2/users/")
                            .header("Authorization", "Bearer 54f71521a469c24f2bd1573d0faf9f5e5f24d5a6d31b44b8d757c22f5ea5b1ba")
                            .header("Connection", "keep-alive")
                            .contentType(ContentType.JSON)
                            .accept(ContentType.JSON)
                            .body(newStatus);

            Response response = requestSpecification.request(Method.PATCH, "4697559");
            JsonPath jsonPath = response.jsonPath();
            String actualStatus = jsonPath.getString("status");
            Assert.assertEquals(actualStatus, "inactive");
            Assert.assertEquals(response.getStatusCode(), 200);
            response.prettyPrint();
        }

        @Test
    void deleteAboveRegisteredUser(){
            RequestSpecification requestSpecification =
                    given()
                            .baseUri("https://gorest.co.in/public/v2/users/")
                            .header("Authorization", "Bearer 54f71521a469c24f2bd1573d0faf9f5e5f24d5a6d31b44b8d757c22f5ea5b1ba")
                            .contentType(ContentType.JSON)
                            .accept(ContentType.JSON);
            Response response =
                    requestSpecification.request(Method.DELETE, "4697559");
            Assert.assertEquals(response.getStatusCode(), 204);
            response.prettyPrint();
        }

}
