package booksProject.books;

public class BookDetailsDto {

    private String isbn;
    private String lang;
    private String publisher;

    public String getIsbn() {
        return isbn;
    }

    public BookDetailsDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public BookDetailsDto setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public BookDetailsDto setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }
}
