package tests;

import apiSteps.APISteps;
import models.BookListResponseModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tests.testData.TestDataBooks;

import java.io.IOException;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class Bookstore {

    APISteps apiSteps = new APISteps();
    TestDataBooks data = new TestDataBooks();

    @ValueSource(strings = {
            "Git Pocket Guide", "Learning JavaScript Design Patterns"
    })
    @ParameterizedTest
    void findBookInList(String bookName) throws IOException {

        BookListResponseModel response = step("Выполняем api-запрос на " +
                "открытие списка книг в Bookstore", () ->
                apiSteps
                        .openBookList()
                        .statusCode(200)
                        .extract().response().as(BookListResponseModel.class));

        step("Проверяем наличие в списке книги '" + bookName + "' и " +
                "корретность отображения в ответе данных по ней", () -> {
            assertThat(response.getBooks().get(data.getBookNumberInList(bookName))
                    .getIsbn()).isEqualTo(data.getIsbn(bookName));
            assertThat(response.getBooks().get(data.getBookNumberInList(bookName))
                    .getTitle()).isEqualTo(data.getTitle(bookName));
            assertThat(response.getBooks().get(data.getBookNumberInList(bookName))
                    .getSubTitle()).isEqualTo(data.getSubTitle(bookName));
            assertThat(response.getBooks().get(data.getBookNumberInList(bookName))
                    .getAuthor()).isEqualTo(data.getAuthor(bookName));
            assertThat(response.getBooks().get(data.getBookNumberInList(bookName))
                    .getPublish_date()).isEqualTo(data.getPublish_date(bookName));
            assertThat(response.getBooks().get(data.getBookNumberInList(bookName))
                    .getPublisher()).isEqualTo(data.getPublisher(bookName));
            assertThat(response.getBooks().get(data.getBookNumberInList(bookName))
                    .getPages()).isEqualTo(data.getPages(bookName));
            assertThat(response.getBooks().get(data.getBookNumberInList(bookName))
                    .getDescription()).isEqualTo(data.getDescription(bookName));
            assertThat(response.getBooks().get(data.getBookNumberInList(bookName))
                    .getWebsite()).isEqualTo(data.getWebsite(bookName));
        });
    }

}
