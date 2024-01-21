package booksProject.shelves.repository;

import booksProject.shelves.entity.BookShelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {

    @Query("SELECT bs.id " +
            "FROM BookShelf as bs " +
            "JOIN bs.user as user " +
            "WHERE user.login = :idUserLogin ")
    List<Long> getIdBookShelfByUser(@Param("idUserLogin") String login);
}
