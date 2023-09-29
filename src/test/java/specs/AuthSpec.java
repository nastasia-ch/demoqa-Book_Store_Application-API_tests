package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class AuthSpec {

    public static RequestSpecification authRequestSpec = with()
            .baseUri("https://demoqa.com")
            .basePath("/Account/v1/GenerateToken")
            .contentType(JSON)
            .log().method()
            .log().uri()
            .log().body();

    public static ResponseSpecification authResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .build();

}
