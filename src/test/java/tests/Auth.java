package tests;

import apiSteps.APISteps;
import models.AuthResponseModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tests.testData.TestDataExistedUser;

import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class Auth {

    APISteps apiSteps = new APISteps();
    static TestDataExistedUser userData = new TestDataExistedUser();

    @Test
    void authWithValidData() {

        AuthResponseModel response = step("Выполняем api-запрос на " +
                "авторизацию пользователя с валидными userName и password", () ->
                apiSteps
                    .auth(userData.userName, userData.password)
                    .statusCode(200)
                    .extract().response().as(AuthResponseModel.class));

        step("Проверяем корректность данных в ответе", () -> {
            assertThat(response.getToken()).isNotNull();
            assertThat(response.getExpires()).isNotNull();
            assertThat(response.getStatus()).isEqualTo("Success");
            assertThat(response.getResult()).isEqualTo("User authorized successfully.");
        });
    }

    static Stream<Arguments> authWithInvalidData() {
        return Stream.of(
                Arguments.of("валидным", userData.userName,
                        "невалидным",userData.invalidPassword),
                Arguments.of("невалидным", userData.invalidUserName,
                        "валидным",userData.password)
        );
    }
    @MethodSource()
    @ParameterizedTest
    void authWithInvalidData(String userNameType, String userName,
                             String passwordType, String password) {

        AuthResponseModel response = step("Выполняем api-запрос на " +
                "авторизацию пользователя с " + userNameType + " userName и " +
                passwordType + " password", () ->
                apiSteps
                    .auth(userName, password)
                    .statusCode(200)
                    .extract().response().as(AuthResponseModel.class));

        step("Проверяем корректность данных в ответе", () -> {
            assertThat(response.getToken()).isNull();
            assertThat(response.getExpires()).isNull();
            assertThat(response.getStatus()).isEqualTo("Failed");
            assertThat(response.getResult()).isEqualTo("User authorization failed.");
        });
    }

}
