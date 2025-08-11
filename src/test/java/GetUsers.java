import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class GetUsers extends TestBase{

    String
            supportUrl = "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral",
            supportText = "Tired of writing endless social media content? Let Content Caddy generate it for you.";

    @Test
    void successfulSingleUserTest() {
        given()
                .log().uri()
                .get(baseURI + basePath + "/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"))
                .body("data.avatar",  is(baseURI + "/img/faces/2-image.jpg"))
                .body("support.url", is(supportUrl))
                .body("support.text", is(supportText));
    }

}
