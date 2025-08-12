package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static tests.TestBase.apiKey;

public class LoginSpec {
    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .header("x-api-key", apiKey)
            .contentType(JSON);

    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification unsuccessfulLoginResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification deleteUsersResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(STATUS)
            .log(BODY)
            .build();
}