package com.digitalnomads.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Data;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
@Data
public class WireMockTest {


    private WireMockServer wireMockServer;
    public Response response;

    @BeforeClass
    public void setUpWireMock() {
        // Define the desired port and host for WireMock
        int port = 8080;
        String host = "localhost"; //якобы наш url адрес

        // Create and start the WireMock server with the specified configuration
        wireMockServer = new WireMockServer(port);
        wireMockServer.start();

        // Configure stubs for your tests
        stubFor(get(urlEqualTo("/api/users")) //наши mock endpoint
                .willReturn(aResponse() // должен возращать
                        .withStatus(200) // статус код 200
                        .withHeader("Content-Type", "application/json") // в header должны быть такие параметры
                        .withBody("{\n" +
                                "    \"page\": 2,\n" +
                                "    \"per_page\": 6,\n" +
                                "    \"total\": 12,\n" +
                                "    \"total_pages\": 2,\n" +
                                "    \"data\": [\n" +
                                "        {\n" +
                                "            \"id\": 7,\n" +
                                "            \"email\": \"michael.lawson@reqres.in\",\n" +
                                "            \"first_name\": \"Michael\",\n" +
                                "            \"last_name\": \"Lawson\",\n" +
                                "            \"avatar\": \"https://reqres.in/img/faces/7-image.jpg\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"id\": 8,\n" +
                                "            \"email\": \"lindsay.ferguson@reqres.in\",\n" +
                                "            \"first_name\": \"Lindsay\",\n" +
                                "            \"last_name\": \"Ferguson\",\n" +
                                "            \"avatar\": \"https://reqres.in/img/faces/8-image.jpg\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"id\": 9,\n" +
                                "            \"email\": \"tobias.funke@reqres.in\",\n" +
                                "            \"first_name\": \"Tobias\",\n" +
                                "            \"last_name\": \"Funke\",\n" +
                                "            \"avatar\": \"https://reqres.in/img/faces/9-image.jpg\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"id\": 10,\n" +
                                "            \"email\": \"byron.fields@reqres.in\",\n" +
                                "            \"first_name\": \"Byron\",\n" +
                                "            \"last_name\": \"Fields\",\n" +
                                "            \"avatar\": \"https://reqres.in/img/faces/10-image.jpg\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"id\": 11,\n" +
                                "            \"email\": \"george.edwards@reqres.in\",\n" +
                                "            \"first_name\": \"George\",\n" +
                                "            \"last_name\": \"Edwards\",\n" +
                                "            \"avatar\": \"https://reqres.in/img/faces/11-image.jpg\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"id\": 12,\n" +
                                "            \"email\": \"rachel.howell@reqres.in\",\n" +
                                "            \"first_name\": \"Rachel\",\n" +
                                "            \"last_name\": \"Howell\",\n" +
                                "            \"avatar\": \"https://reqres.in/img/faces/12-image.jpg\"\n" +
                                "        }\n" +
                                "    ],\n" +
                                "    \"support\": {\n" +
                                "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                                "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                                "    }\n" +
                                "}") // в body якобы должен быть такой ответ
                )
        );
    }

    @Test
    public void testWireMock() {
        // Your test code that interacts with the WireMock server
    // Проверям наш mock метод
        response = RestAssured.given()
                .baseUri("http://localhost:8080")
                .get("/api/users");
        System.out.println(response.getStatusCode());
//        response.prettyPrint();
    }

    @AfterClass
    public void tearDownWireMock() {
        // Stop the WireMock server after your tests are complete
        wireMockServer.stop();
    }
}
