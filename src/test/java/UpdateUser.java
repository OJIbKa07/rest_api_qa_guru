import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

public class UpdateUser extends TestBase {


    @Test
    void successfulUpdateUserTest() {
        String
                authData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}",
                currentDate = LocalDate.now().toString();

        given()
                .header("x-api-key", apiKey)
                .body(authData)
                .contentType(JSON)
                .log().uri()
        .when()
                .put(baseURI + basePath + "/users/2")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", startsWith(currentDate));
    }
}
