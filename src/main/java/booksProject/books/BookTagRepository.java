package booksProject.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookTagRepository extends JpaRepository<BookTagEntity, Long> {

    Optional<BookTagEntity> findByTagValue(String tagValue);
}
