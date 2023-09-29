package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class RegistrationSpec {

    public static RequestSpecification registrationRequestSpec = with()
            .baseUri("https://demoqa.com")
            .basePath("/Account/v1/User")
            .contentType(JSON)
            .log().method()
            .log().uri()
            .log().body();

    public static ResponseSpecification registrationResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .build();

}
