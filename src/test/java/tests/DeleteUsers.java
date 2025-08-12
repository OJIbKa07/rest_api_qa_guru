package tests;

import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static specs.LoginSpec.deleteUsersResponseSpec;
import static specs.LoginSpec.loginRequestSpec;

public class DeleteUsers extends TestBase{

    @Test
    void successfulDeleteUserTest() {
         step("Make request", ()->
            given(loginRequestSpec)
            .when()
                .delete("/users/2")
            .then()
                .spec(deleteUsersResponseSpec)
        );

    }
}
