import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class DeleteUsers extends TestBase{

    @Test
    void successfulDeleteUserTest() {
        given()
                .header("x-api-key", apiKey)
                .contentType(JSON)
                .log().uri()
        .when()
                .delete(baseURI + basePath + "/users/2")
        .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
