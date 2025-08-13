package tests;

import models.lombok.UsersResponseLombokModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RequestSpec.*;
import static specs.ResponseSpec.responseSpec;

public class GetUsers extends TestBase {

    String
            supportUrl = "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral",
            supportText = "Tired of writing endless social media content? Let Content Caddy generate it for you.";

    @Test
    void successfulSingleUserTest() {
        UsersResponseLombokModel response = step("Make request", ()->
                given(requestSpec)
                        .get("/users/2")
                        .then()
                        .spec(responseSpec(200))
                        .extract()
                        .as(UsersResponseLombokModel.class));

        step("Check response", () -> {
            assertEquals(2, response.getData().getId());
            assertEquals("janet.weaver@reqres.in", response.getData().getEmail());
            assertEquals("Janet", response.getData().getFirst_name());
            assertEquals("Weaver", response.getData().getLast_name());
            assertEquals(baseURI + "/img/faces/2-image.jpg", response.getData().getAvatar());
            assertEquals(supportUrl, response.getSupport().getUrl());
            assertEquals(supportText, response.getSupport().getText());
        });
    }

}