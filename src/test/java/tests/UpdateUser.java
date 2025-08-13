package tests;

import models.lombok.UpdateDateResponseLombokModel;
import models.lombok.UpdateUserBodyLombokModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.RequestSpec.*;
import static specs.ResponseSpec.responseSpec;

public class UpdateUser extends TestBase {


    @Test
    void successfulUpdateUserTest() {
        UpdateUserBodyLombokModel updateData = new UpdateUserBodyLombokModel();
        updateData.setName("morpheus");
        updateData.setJob("zion resident");
        updateData.setUpdatedAt(LocalDate.now().toString());

        UpdateDateResponseLombokModel response = step("Make request", () ->
                given(requestSpec)
                        .body(updateData)
                .when()
                        .put("/users/2")
                .then()
                        .spec(responseSpec(200))
                        .extract()
                        .as(UpdateDateResponseLombokModel.class)
        );

        step("Check response", () -> {
            assertEquals(updateData.getName(), response.getName());
            assertEquals(updateData.getJob(), response.getJob());
            assertThat(response.getUpdatedAt(), startsWith(updateData.getUpdatedAt()));
        });
    }
}
