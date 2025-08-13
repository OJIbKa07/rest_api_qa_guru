package tests;

import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;

public class DeleteUsers extends TestBase{

    @Test
    void successfulDeleteUserTest() {
         step("Make request", ()->
            given(requestSpec)
            .when()
                .delete("/users/2")
            .then()
                .spec(responseSpec(204))
        );

    }
}
