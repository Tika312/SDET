package com.digitalnomads.demo;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
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

}
