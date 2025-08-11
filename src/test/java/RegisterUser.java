import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;


public class RegisterUser extends TestBase{

    @Test
    void successfulRegisterUserTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";

        given()
                .header("x-api-key", apiKey)
                .body(authData)
                .contentType(JSON)
                .log().uri()
        .when()
                .post("/register")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", not(isEmptyOrNullString()));
    }

    @Test
    void unsuccessfulLoginTest() {
        String authData = "{\"email\": \"sydney@fife\"}";

        given()
                .header("x-api-key", apiKey)
                .body(authData)
                .contentType(JSON)
                .log().uri()
        .when()
                .post("/register")
        .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
