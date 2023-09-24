package booksProject.books;

public class BookFormDetails {

    private String isbn;
    private String lang;
    private String publisher;

    public String getIsbn() {
        return isbn;
    }

    public BookFormDetails setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public BookFormDetails setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public BookFormDetails setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }
}
