package tests;

import apiSteps.APISteps;
import models.RegistrationResponseModel;
import org.junit.jupiter.api.Test;
import tests.testData.TestDataExistedUser;
import tests.testData.TestDataNewUser;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

public class Registration {

    APISteps apiSteps = new APISteps();
    TestDataNewUser newUser = new TestDataNewUser();
    TestDataExistedUser existedUser = new TestDataExistedUser();

    @Test
    void registrationExistedUser() {

        step("Выполняем api-запрос на " +
                "создание нового пользователя с существующим userName", () ->
                apiSteps
                        .registration(existedUser.userName,existedUser.password)
                        .statusCode(406)
                        .body("code", is("1204"))
                        .body("message", is("User exists!")));
    }

    @Test
    void registrationNewUser() {

        RegistrationResponseModel response = step("Выполняем api-запрос на " +
                "создание нового пользователя с несуществующим userName", () ->
                apiSteps
                        .registration(newUser.userName, newUser.password)
                        .statusCode(201)
                        .extract().response().as(RegistrationResponseModel.class));

        try {
            step("Проверяем корректность данных в ответе", () -> {
                assertThat(response.getUsername()).isEqualTo(newUser.userName);
                assertThat(response.getUserID()).isNotNull();
            });
        }

        finally {
            step("Ощищаем данные после теста: выполняем api-запрос на " +
                    "удаление созданного пользователя", () -> {
                String userID = response.getUserID();

                String token = apiSteps
                        .auth(newUser.userName, newUser.password)
                        .statusCode(200)
                        .extract().path("token");

                apiSteps.deleteAccount(userID, token);
            });
        }

    }

}
