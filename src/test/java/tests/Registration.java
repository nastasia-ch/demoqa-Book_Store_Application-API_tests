package tests;

import models.RegistrationModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static specs.RegistrationSpec.registrationRequestSpec;
import static specs.RegistrationSpec.registrationResponseSpec;

public class Registration {

    @Test
    void registrationExistedUser() {

        RegistrationModel requestBody = new RegistrationModel();
        requestBody.setUserName("Qwer0987!");
        requestBody.setPassword("Qwer0987!");

        given()
                .spec(registrationRequestSpec)
                .body(requestBody)
        .when()
                .post()
        .then()
                .spec(registrationResponseSpec)
                .statusCode(406)
                .body("code",is("1204"))
                .body("message",is("User exists!"));
    }

    @Test
    void registrationNewUser() {

        RegistrationModel requestBody = new RegistrationModel();
        requestBody.setUserName("Zxcv1234!");
        requestBody.setPassword("Zxcv1234!");

        given()
                .spec(registrationRequestSpec)
                .body(requestBody)
        .when()
                .post()
        .then()
                .spec(registrationResponseSpec)
                .statusCode(201)
                .body("username",is("Zxcv1234!"));


        String token = given()
                .contentType(JSON)
                .log().method()
                .log().uri()
                .log().body()
                .body(requestBody)
        .when()
                .post("https://demoqa.com/Account/v1/GenerateToken")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("token");

        String userId = given()
                .contentType(JSON)
                .log().method()
                .log().uri()
                .log().body()
                .body(requestBody)
                .when()
                .post("https://demoqa.com/Account/v1/Login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("userId");

        given()
                .contentType(JSON)
                .log().method()
                .log().uri()
                .pathParam("userId", userId)
                .header("Authorization", "Bearer "+token)
        .when()
                .delete("https://demoqa.com/Account/v1/User/{userId}")
        .then()
                .log().status()
                .log().headers()
                .log().body()
                .statusCode(204);

    }




}
