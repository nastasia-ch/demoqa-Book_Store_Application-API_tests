package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class OpenBookListSpec {

    public static RequestSpecification openBookListRequestSpec = with()
            .baseUri("https://demoqa.com")
            .basePath("/BookStore/v1/Books")
            .log().method()
            .log().uri()
            .log().body();

    public static ResponseSpecification openBookListResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .build();

}
