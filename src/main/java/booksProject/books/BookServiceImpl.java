package booksProject.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{


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
            List<BookEntity> result =  bookRepository.findAllByAuthor(author).get();
            if(!result.isEmpty()){
               return result.stream()
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
    public BookDto findByUuid(String uuid) {

        BookEntity book = bookRepository.findByUuid(uuid).orElse(null);
        if(book != null){
           return BookMapper.map(book);
        }
        return new BookDto();
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
    public void delete(String uuid) {

        Optional<BookEntity> result = bookRepository.findByUuid(uuid);
        if(result.isPresent()){
          BookEntity book =  result.get();
          List<BookTagEntity> tags =  book.getTags().stream().collect(Collectors.toList());
          for(int i=0; i<tags.size(); i++) {
              book.removeBookTag(tags.get(i));
          }
          bookRepository.delete(book);
        }
    }

    @Override
    public BookDto update(String uuid, BookForm form) {

        Optional<BookEntity> bookOpt = bookRepository.findByUuid(uuid);
        if (bookOpt.isPresent()) {
            BookEntity updatedBookEntity = bookOpt.get()
                    .setTitle(form.getTitle())
                    .setAuthor(form.getAuthor())
                    .setDetails(BookDetailsMapper.map(form.getDetails()));
            Set<String> bookFormTags = form.getTags();
            updateTags(bookFormTags, updatedBookEntity);

            return BookMapper.map(bookRepository.saveAndFlush(updatedBookEntity));
        }
        return new BookDto();
    }

    private void updateTags(Set<String> tagsForm, BookEntity book){
        BookTagMapper BookTagMapper = new BookTagMapper();
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
                    return new BookTagEntity().setTagValue(tag);
                })
                .collect(Collectors.toSet());
    }


}
