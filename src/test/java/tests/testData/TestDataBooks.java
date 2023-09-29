package tests.testData;

import com.codeborne.xlstest.XLS;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TestDataBooks {

    public String getAuthor(String bookName) throws IOException {
        return getData(bookName, "author");
    }

    public String getDescription(String bookName) throws IOException {
        return getData(bookName, "description");
    }

    public String getIsbn(String bookName) throws IOException {
        return getData(bookName, "isbn");
    }

    public String getPages(String bookName) throws IOException {
        return getData(bookName, "pages");
    }

    public String getPublish_date(String bookName) throws IOException {
        return getData(bookName, "publish_date");
    }

    public String getPublisher(String bookName) throws IOException {
        return getData(bookName, "publisher");
    }

    public String getSubTitle(String bookName) throws IOException {
        return getData(bookName, "subTitle");
    }

    public String getTitle(String bookName) throws IOException {
        return getData(bookName, "title");
    }

    public String getWebsite(String bookName) throws IOException {
        return getData(bookName, "website");
    }

    public int getBookNumberInList(String bookName) {
        return books.get(bookName);
    }

    private final Map<String, Integer> books = new HashMap<>() {{
        put("Git Pocket Guide", 0);
        put("Learning JavaScript Design Patterns", 1);
    }};

    private final Map<String, Integer> bookDescription = new HashMap<>() {{
        put("isbn", 0);
        put("title", 1);
        put("subTitle", 2);
        put("author", 3);
        put("publish_date", 4);
        put("publisher", 5);
        put("pages", 6);
        put("description", 7);
        put("website", 8);
    }};

    private String getData(String bookName, String bookParam) throws IOException {

        ClassLoader cl = TestDataBooks.class.getClassLoader();
        try (InputStream is = cl.getResourceAsStream("books_data.xlsx")) {
            XLS xls = new XLS(is);
            return xls.excel.getSheetAt(0).getRow(bookDescription.get(bookParam))
                    .getCell(books.get(bookName) + 1).toString();

        }

    }


}
