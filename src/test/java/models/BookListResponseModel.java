package models;

import lombok.Data;

import java.util.List;

@Data
public class BookListResponseModel {

    private List<Book> books;

    @Data
    public static class Book {
       private String author;
       private String description;
       private String isbn;
       private String pages;
       private String publish_date;
       private String publisher;
       private String subTitle;
       private String title;
       private String website;

    }

}
