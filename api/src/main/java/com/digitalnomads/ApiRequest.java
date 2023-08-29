package com.digitalnomads;


import com.digitalnomads.api.entities.BaseEntity;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j

public abstract class ApiRequest {

    protected String url;
    protected RequestSpecification requestSpecification;
    protected String apiKey;
    protected Response response;


    public ApiRequest(String url, String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
        BasicAuthScheme auth = new BasicAuthScheme();
        auth.setUserName(this.apiKey);
        auth.setPassword("");
        this.requestSpecification = new RequestSpecBuilder()
                .setAuth(auth)
                .setBaseUri(this.url)
                .build();
    }

    public static String getEndPoint(String... args){
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(args).forEach(
                a -> stringBuilder.append(a).append("/")
        );
        return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();
    }

    public static Map<String,String> generateParams(String key, String value){
        return new HashMap<>(){{
            put(key,value);
        }};
    }

    private void logResponse(){
        log.warn("Response is: ");
        log.warn(this.response.getBody().asString());
        log.warn("Status code is: {}", String.valueOf(this.response.getStatusCode()));
    }

    public Response get(String endPoint){
        log.info("Performed GET request {}", endPoint);
        this.response = given()
                .spec(requestSpecification)
                .get(endPoint);
        logResponse();
        return this.response;
    }

    public Response getWithParams(String endPoint, Map<String, String> params){
        log.info("Performed GET request {}", endPoint);
        this.response = given()
                .spec(requestSpecification)
                .params(params)
                .get(endPoint);
        logResponse();
        return this.response;
    }

    public Response post(String endPoint, String body){
        log.info("Performed POST request {}", endPoint);
        log.info("Body is {}", body);
        this.response = given()
                .spec(requestSpecification)
                .body(body)
                .post(endPoint);
        logResponse();
        return this.response;
    }

    public <T extends BaseEntity> BaseEntity extractObjectFromResponse(Class<T> tClass){
       try {
           return this.response
                   .then()
                   .extract()
                   .body()
                   .as(tClass);
       }catch (Exception e){
           log.error("can not parse response {}", e);
           return null;
       }
    }
}
