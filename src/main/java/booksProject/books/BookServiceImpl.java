package booksProject.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BooksRepository bookRepository;

    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }
    @Override
    public List<BookEntity> findAll(String author) {

        if(author != null) {
            List<BookEntity> result =  bookRepository.findAllByAuthor(author).get();
            if(!result.isEmpty()){
               return result;
            }
            return bookRepository.findAll();
        }
        return bookRepository.findAll();
    }
    @Override
    public BookEntity findByUuid(String uuid) {

        return bookRepository.findByUuid(uuid).orElse(null);
    }
    @Override
    public BookEntity create(BookForm form) {
        return bookRepository.saveAndFlush(BookMapper.map(form));
    }
    @Override
    public void delete(String uuid) {

        bookRepository.findByUuid(uuid)
                .ifPresent(book -> bookRepository.delete(book));
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
        List<BookTagEntity> bookTagsFiltered = book.getTags().stream()
                .filter(tag -> !tagsForm.contains(tag.getTagValue()))
                .collect(Collectors.toList());

        bookTagsFiltered.stream()
                .forEach(tagValue -> book.removeBookTag(tagValue));

        Set<String> formTagsFiltered = tagsForm.stream()
                .filter(tagForm -> !book.getTags().contains(tagForm))
                .collect(Collectors.toSet());

        BookTagMapper.mapToEntity(formTagsFiltered).stream()
                .forEach(bookTag ->  book.addBookTag(bookTag));
    }
}
