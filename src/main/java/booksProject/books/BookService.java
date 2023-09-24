package booksProject.books;

import java.util.List;

public interface BookService {

    public List<BookEntity> findAll(String author);

    public BookEntity findByUuid(String uuid);

    public BookEntity create(BookForm form);

    public void delete(String uuid);

    public BookDto update(String uuid, BookForm form);
}
