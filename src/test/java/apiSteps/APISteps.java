package apiSteps;

import io.restassured.response.ValidatableResponse;
import models.AuthRequestModel;
import models.RegistrationRequestModel;

import static io.restassured.RestAssured.given;
import static specs.AuthSpec.authRequestSpec;
import static specs.AuthSpec.authResponseSpec;
import static specs.DeleteAccountSpec.deleteAccountRequestSpec;
import static specs.DeleteAccountSpec.deleteAccountResponseSpec;
import static specs.RegistrationSpec.registrationRequestSpec;
import static specs.RegistrationSpec.registrationResponseSpec;

public class APISteps {

    public ValidatableResponse registration(String userName, String password) {

        RegistrationRequestModel registrationRequestBody = new RegistrationRequestModel();
        registrationRequestBody.setUserName(userName);
        registrationRequestBody.setPassword(password);

        return given()
                .spec(registrationRequestSpec)
                .body(registrationRequestBody)
                .when()
                .post()
                .then()
                .spec(registrationResponseSpec);

    }

    public ValidatableResponse auth(String userName, String password) {

        AuthRequestModel authRequestBody = new AuthRequestModel();
        authRequestBody.setUserName(userName);
        authRequestBody.setPassword(password);

        return given()
                .spec(authRequestSpec)
                .body(authRequestBody)
                .when()
                .post()
                .then()
                .spec(authResponseSpec);

    }

    public ValidatableResponse deleteAccount(String userID, String token) {

        return given()
                .spec(deleteAccountRequestSpec)
                .pathParam("userID", userID)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete()
                .then()
                .spec(deleteAccountResponseSpec);

    }


}
