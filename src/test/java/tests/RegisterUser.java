package tests;

import models.lombok.*;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.RequestSpec.*;
import static specs.ResponseSpec.responseSpec;


public class RegisterUser extends TestBase{

    @Test
    void successfulRegisterUserTest() {
        RegisterBodyLombokModel regData = new RegisterBodyLombokModel();
        regData.setEmail("eve.holt@reqres.in");
        regData.setPassword("pistol");

        RegisterResponseLombokModel response = step("Make request", () ->
                given(requestSpec)
                        .body(regData)
                .when()
                        .post("/register")
                .then()
                        .spec(responseSpec(200))
                        .extract()
                        .as(RegisterResponseLombokModel.class)
        );

        step("Check response", () -> {
            assertEquals(4, response.getId());
            assertNotNull(response.getToken(), "Token is null");
        });
    }

    @Test
    void unsuccessfulLoginTest() {
        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setEmail("sydney@fife");

        UnsuccessfulLoginResponseLobmokModel response = step("Make request", () ->
                given(requestSpec)
                        .body(authData)
                .when()
                        .post("/register")
                .then()
                        .spec(responseSpec(400))
                        .extract()
                        .as(UnsuccessfulLoginResponseLobmokModel.class)
        );

        step("Check response", () -> {
            assertEquals("Missing password", response.getError());
        });
    }
}
