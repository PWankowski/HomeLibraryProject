package booksProject.books.service;

import booksProject.books.NoBookFoundException;
import booksProject.books.dto.BookDto;
import booksProject.books.dto.BookForm;
import booksProject.books.entity.BookEntity;
import booksProject.books.entity.BookTagEntity;
import booksProject.books.mappers.BookDetailsMapper;
import booksProject.books.mappers.BookMapper;
import booksProject.books.repository.BookTagRepository;
import booksProject.books.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
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
    public List<BookDto> findAllByAuthor(String author) {

        if(author != null) {
            Optional<List<BookEntity>> result =  bookRepository.findAllByAuthor(author);
            if(result.isPresent()){
               return result.get().stream()
                       .map(bookEntity -> BookMapper.map(bookEntity))
                       .collect(Collectors.toList());
            }
            return bookRepository.findAll().stream()
                    .map(bookEntity -> BookMapper.map(bookEntity))
                    .collect(Collectors.toList());
        }
        return bookRepository.findAll().stream()
                .map(bookEntity -> BookMapper.map(bookEntity))
                .collect(Collectors.toList());
    }
    @Override
    public BookDto findByUuid(String uuid) throws NoBookFoundException{

        BookEntity book = bookRepository.findByUuid(uuid).orElse(null);
        if(book != null){
           return BookMapper.map(book);
        }
        throw new NoBookFoundException(uuid);
    }
    @Override
    public BookDto create(BookForm form) {
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
    public boolean delete(String uuid) throws NoBookFoundException{

        Optional<BookEntity> result = bookRepository.findByUuid(uuid);
        if(result.isPresent()){
          BookEntity book =  result.get();
          List<BookTagEntity> tags =  book.getTags().stream().collect(Collectors.toList());
          for(int i=0; i<tags.size(); i++) {
              book.removeBookTag(tags.get(i));
          }
          bookRepository.delete(book);
          return true;
        }
        throw new NoBookFoundException(uuid);
    }

    @Override
    public BookDto update(String uuid, BookForm form) {

        Optional<BookEntity> bookOpt = bookRepository.findByUuid(uuid);
        if (bookOpt.isPresent()) {
            BookEntity updatedBookEntity = bookOpt.get();
            updatedBookEntity.setTitle(form.getTitle());
            updatedBookEntity.setAuthor(form.getAuthor());
            updatedBookEntity.setDetails(BookDetailsMapper.map(form.getDetails()));
            Set<String> bookFormTags = form.getTags();
            updateTags(bookFormTags, updatedBookEntity);

            return BookMapper.map(bookRepository.saveAndFlush(updatedBookEntity));
        }
        return new BookDto();
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
}
