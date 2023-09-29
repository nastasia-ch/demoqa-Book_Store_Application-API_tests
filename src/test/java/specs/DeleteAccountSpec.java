package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class DeleteAccountSpec {

    public static RequestSpecification deleteAccountRequestSpec = with()
            .baseUri("https://demoqa.com")
            .basePath("/Account/v1/User/{userID}")
            .contentType(JSON)
            .log().method()
            .log().uri()
            .log().body();

    public static ResponseSpecification deleteAccountResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(HEADERS)
            .log(BODY)
            .build();

}
