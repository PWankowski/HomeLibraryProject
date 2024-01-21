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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {


    private final BooksRepository bookRepository;
    private final BookTagRepository bookTagRepository;

    @Autowired
    public BookServiceImpl(BooksRepository bookRepository, BookTagRepository bookTagRepository) {
        this.bookRepository = bookRepository;
        this.bookTagRepository = bookTagRepository;
    }

    @Override
    public List<BookDto> findAll() {
        List<BookEntity> books = bookRepository.findAll();
        return books.stream()
                    .map(bookEntity -> BookMapper.map(bookEntity))
                    .collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAllByAuthor(String author) throws NoBookFoundException {

        if(author != null) {
            Optional<List<BookEntity>> result =  bookRepository.findAllByAuthor(author);
            if(!result.get().isEmpty()){
               return result.get().stream()
                       .map(bookEntity -> BookMapper.map(bookEntity))
                       .collect(Collectors.toList());
            }
             throw new NoBookFoundException(String.format("No Books for author: %s found!", author));
        }
        return bookRepository.findAll().stream()
                .map(bookEntity -> BookMapper.map(bookEntity))
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    @Cacheable(cacheNames = "findByUUID", key = "#uuid")
    public BookDto findByUuid(String uuid) throws NoBookFoundException {

        BookEntity book = bookRepository.findByUuid(uuid).orElseThrow(() -> new NoBookFoundException(String.format("No Book with uuid: %s found!", uuid)));
        return BookMapper.map(book);
    }
    @Override
    public BookDto create(BookForm form) throws BookExistException {
        Boolean isBookPresent = validateBook(form);
        if(isBookPresent){
            throw new BookExistException("Book with this Author and Title exist!");
        }
        BookEntity book = BookMapper.map(form);
        Set<String> bookTagsString = form.getTags();

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
    public boolean delete(String uuid) throws NoBookFoundException {

        Optional<BookEntity> result = bookRepository.findByUuid(uuid);
        if(result.isPresent()){
          BookEntity book =  result.get();
          List<BookTagEntity> tags =  book.getTags().stream().toList();
          for(int i=0; i<tags.size(); i++) {
              book.removeBookTag(tags.get(i));
          }
          List<BookShelf> bookShelves = book.getBookShelves().stream().toList();
          for(int i=0; i<bookShelves.size(); i++) {
              book.removeBookShelf(bookShelves.get(i));
          }
          bookRepository.delete(book);
          return true;
        }
        throw new NoBookFoundException(String.format("No Book with uuid: %s found!", uuid));
    }

    @Override
    @CachePut(cacheNames = "findByUUID", key = "#result.uuid")
    public BookDto update(String uuid, BookForm form) {

        Optional<BookEntity> bookOpt = bookRepository.findByUuid(uuid);
        BookEntity updatedBookEntity = bookOpt.get();
        updatedBookEntity.setTitle(form.getTitle());
        updatedBookEntity.setAuthor(form.getAuthor());
        updatedBookEntity.setDetails(BookDetailsMapper.map(form.getDetails()));
        Set<String> bookFormTags = form.getTags();
        updateTags(bookFormTags, updatedBookEntity);

        return BookMapper.map(bookRepository.saveAndFlush(updatedBookEntity));
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

    private boolean validateBook(BookForm form) {

      String author =  form.getAuthor();
      String title = form.getTitle().toLowerCase().replace(" ", "");

      List<BookEntity> booksByAuthor = bookRepository.findAllByAuthor(author).get();

      if(booksByAuthor.isEmpty()){
          return false;
      }
     long result = booksByAuthor.stream()
              .map(book -> book.getTitle().toLowerCase().replace(" ", ""))
              .filter(bookTitle -> bookTitle.equals(title))
              .count();

      if(result != 0){
          return true;
      }
      return false;
    }
}
