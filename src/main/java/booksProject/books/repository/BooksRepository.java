package booksProject.books.repository;

import booksProject.books.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<BookEntity,Long> {

     Optional<BookEntity> findByUuid(String uuid);

     Optional<List<BookEntity>> findAllByAuthor(String author);
}
