package booksProject.books;

public class BookDetailsMapper {

    public static BookDetailsDto map(BookDetailsEntity bookDetails){

        if(bookDetails == null) {
            return new BookDetailsDto();
        }
        return new BookDetailsDto()
                .setIsbn(bookDetails.getIsbn())
                .setPublisher(bookDetails.getPublisher())
                .setLang(bookDetails.getLang());
    }
}
