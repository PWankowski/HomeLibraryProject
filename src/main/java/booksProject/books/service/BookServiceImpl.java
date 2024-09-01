package booksProject.books.service;

import booksProject.books.BookExistException;
import booksProject.books.NoBookFoundException;
import booksProject.books.dto.BookDto;
import booksProject.books.dto.BookForm;
import booksProject.books.entity.BookEntity;
import booksProject.books.entity.BookTagEntity;
import booksProject.books.mappers.BookDetailsMapper;
import booksProject.books.mappers.BookMapper;
import booksProject.books.repository.BookTagRepository;
import booksProject.books.repository.BooksRepository;
import booksProject.shelves.entity.BookShelf;
import booksProject.user.NoUserFoundException;
import booksProject.user.entity.UserEntity;
import booksProject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {


    private final BooksRepository bookRepository;
    private final BookTagRepository bookTagRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAll(String userLogin) throws NoUserFoundException, NoBookFoundException {

        UserEntity user = userRepository.findByLogin(userLogin).orElseThrow(() -> new NoUserFoundException(userLogin));
        List<BookEntity> books = bookRepository.findAllByUser(user).orElseThrow(() -> new NoBookFoundException(String.format("No Books for user: %s found!", user.getLogin())));
        return books.stream()
                    .map(bookEntity -> BookMapper.map(bookEntity))
                    .collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAllByAuthor(String author, String userLogin) throws NoBookFoundException, NoUserFoundException {

        if(author != null) {
            UserEntity user = userRepository.findByLogin(userLogin).orElseThrow(() -> new NoUserFoundException(userLogin));
            List<BookEntity> books = bookRepository.findAllByUser(user).orElseThrow(() -> new NoBookFoundException(String.format("No Books for user: %s found!", user.getLogin())));

            List<BookEntity> result =  books.stream()
                    .filter(book -> book.getAuthor().equals(author))
                    .collect(Collectors.toList());

            if(!result.isEmpty()){
               return result.stream()
                       .map(bookEntity -> BookMapper.map(bookEntity))
                       .collect(Collectors.toList());
            }
             throw new NoBookFoundException(String.format("No Books for author: %s found!", author));
        }
        return new ArrayList<>();
    }
    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "findByUUID", key = "#uuid")
    public BookDto findByUuid(String uuid) throws NoBookFoundException {

        BookEntity book = bookRepository.findByUuid(uuid).orElseThrow(() -> new NoBookFoundException(String.format("No Book with uuid: %s found!", uuid)));
        return BookMapper.map(book);
    }
    @Override
    @Transactional
    public BookDto create(BookForm form, String userLogin) throws BookExistException, NoUserFoundException {

        UserEntity user = userRepository.findByLogin(userLogin).orElseThrow(() -> new NoUserFoundException(userLogin));
        Boolean isBookPresent = validateBook(form, user);
        if(isBookPresent){
            throw new BookExistException("Book with this Author and Title exist!");
        }
        BookEntity book = BookMapper.map(form);
        Set<String> bookTagsString = form.getTags();
        book.addUser(user);

        if(bookTagsString == null || bookTagsString.size() == 0) {
           return BookMapper.map(bookRepository.save(book));
        }
        Set<BookTagEntity> bookTags = mapStringToTagEntity(bookTagsString);
        for(BookTagEntity tag : bookTags) {
            book.addBookTag(tag);
        }
        return BookMapper.map(bookRepository.save(book));
    }
    @Override
    @Transactional
    public boolean delete(String uuid, String userLogin) throws NoBookFoundException, NoUserFoundException {

        UserEntity user = userRepository.findByLogin(userLogin).orElseThrow(() -> new NoUserFoundException(userLogin));
        BookEntity book = bookRepository.findByUuid(uuid).orElseThrow(() -> new NoBookFoundException(String.format("No Book with uuid: %s found!", uuid)));

        if(!book.getUser().equals(user)) {
            return false;
        }
        List<BookTagEntity> tags =  book.getTags().stream().toList();
        for(int i=0; i<tags.size(); i++) {
             book.removeBookTag(tags.get(i));
        }
        List<BookShelf> bookShelves = book.getBookShelves().stream().toList();
        for(int i=0; i<bookShelves.size(); i++) {
            book.removeBookShelf(bookShelves.get(i));
        }
        book.removeRelatedUserFromBook(user);
        bookRepository.delete(book);
        return true;
    }

    @Override
    @Transactional
    @CachePut(cacheNames = "findByUUID", key = "#result.uuid")
    public BookDto update(String uuid, BookForm form) throws NoBookFoundException {

        BookEntity book = bookRepository.findByUuid(uuid).orElseThrow(() -> new NoBookFoundException(String.format("No Book with uuid: %s found!", uuid)));
        book.setTitle(form.getTitle());
        book.setAuthor(form.getAuthor());
        book.setDetails(BookDetailsMapper.map(form.getDetails()));
        Set<String> bookFormTags = form.getTags();
        updateTags(bookFormTags, book);
        return BookMapper.map(bookRepository.saveAndFlush(book));
    }

    private void updateTags(Set<String> tagsForm, BookEntity book) {

        List<BookTagEntity> bookTagsFiltered = book.getTags().stream()
                .filter(tag -> !tagsForm.contains(tag.getTagValue()))
                .collect(Collectors.toList());

        for(int i=0; i<bookTagsFiltered.size(); i++) {
            book.removeBookTag(bookTagsFiltered.get(i));
        }

        Set<String> formTagsFiltered = tagsForm.stream()
                .filter(tagForm -> !book.getTags().contains(tagForm))
                .collect(Collectors.toSet());
        Set<BookTagEntity> bookTags = mapStringToTagEntity(formTagsFiltered);
        for(BookTagEntity tag : bookTags) {
            book.addBookTag(tag);
        }
    }

    private Set<BookTagEntity> mapStringToTagEntity(Set<String> bookTagsValues) {

        return bookTagsValues.stream()
                .map(tag -> {
                    Optional<BookTagEntity> bookTag = bookTagRepository.findByTagValue(tag);
                    if(bookTag.isPresent()) {
                        return bookTag.get();
                    }
                    BookTagEntity bookTagEntity = new BookTagEntity();
                    bookTagEntity.setTagValue(tag);
                    return bookTagEntity;
                })
                .collect(Collectors.toSet());
    }

    boolean validateBook(BookForm form,UserEntity user) throws NoUserFoundException {

      String author =  form.getAuthor();
      String title = form.getTitle().toLowerCase().replace(" ", "");
      long result;

      if(user.getBooks().isEmpty()) {
          return false;
      }
      List<BookEntity> booksByAuthor = user.getBooks().stream()
                                                      .filter(book -> book.getAuthor().equals(author))
                                                      .collect(Collectors.toList());

      if(booksByAuthor.isEmpty()) {
          return false;
      } else {
          result = booksByAuthor.stream()
                  .map(book -> book.getTitle().toLowerCase().replace(" ", ""))
                  .filter(bookTitle -> bookTitle.equals(title))
                  .count();
      }

      if(result != 0) {
          return true;
      }
      return false;
    }
}
