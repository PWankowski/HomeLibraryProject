package booksProject.shelves.service;

import booksProject.books.NoBookFoundException;
import booksProject.books.entity.BookEntity;
import booksProject.books.repository.BooksRepository;
import booksProject.shelves.BookShelfExistException;
import booksProject.shelves.NoBookShelfExistException;
import booksProject.shelves.entity.BookShelf;
import booksProject.shelves.entity.BookShelfDto;
import booksProject.shelves.entity.BookShelfForm;
import booksProject.shelves.mapper.BookShelfMapper;
import booksProject.shelves.repository.BookShelfRepository;
import booksProject.user.NoUserFoundException;
import booksProject.user.entity.UserEntity;
import booksProject.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookShelfServiceImpl implements BookShelfService {

    private final BookShelfRepository bookShelfRepository;

    private final BooksRepository booksRepository;

    private final UserRepository userRepository;


    @Autowired
    public BookShelfServiceImpl(BookShelfRepository bookShelfRepository, BooksRepository booksRepository, UserRepository userRepository) {
        this.bookShelfRepository = bookShelfRepository;
        this.booksRepository = booksRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookShelfDto createBookShelf(String login, BookShelfForm bookShelfForm) throws BookShelfExistException, NoUserFoundException {

        UserEntity user = userRepository.findByLogin(login).orElse(null);
        if(user == null) {
            throw new NoUserFoundException(login);
        }
        BookShelf bookShelf = new BookShelf();
        if(user.getShelves() == null){
            bookShelf = BookShelfMapper.mapBookShelfFormToBookShelf(bookShelfForm);
            user.getShelves().add(bookShelf);
        } else {
            long result = user.getShelves().stream().filter(shelve -> shelve.equals(bookShelfForm.getName())).count();
            if(result != 0) {
                throw new BookShelfExistException();
            }
            bookShelf = BookShelfMapper.mapBookShelfFormToBookShelf(bookShelfForm);
            user.getShelves().add(bookShelf);
        }
        bookShelf.setUser(user);
        return BookShelfMapper.mapBookShelfToBookShelfDto(bookShelfRepository.saveAndFlush(bookShelf));
    }

    @Override
    public void deleteBookShelf(Long id) throws NoBookShelfExistException {

        BookShelf bookShelf = validateBookShelfExistAndReturnValueOrThrowException(id);
        UserEntity user = bookShelf.getUser();
        bookShelf.removeUser(user);

        List<BookEntity> items = bookShelf.getItems().stream().toList();
        for(int i=0; i<items.size(); i++) {
            bookShelf.removeItem(items.get(i));
        }
        bookShelfRepository.save(bookShelf);
        bookShelfRepository.delete(bookShelf);
    }
    @Override
    public List<BookShelfDto> getAllByUser(String userLogin) {

        List<BookShelfDto> bookShelvesByUser = new ArrayList<>();
        List<Long> result = bookShelfRepository.getIdBookShelfByUser(userLogin);

        for(Long idShelve : result) {
           BookShelf bookShelf =  bookShelfRepository.findById(idShelve).get();
           BookShelfDto bookShelfDto = BookShelfMapper.mapBookShelfToBookShelfDto(bookShelf);
           bookShelvesByUser.add(bookShelfDto);
        }
        return bookShelvesByUser;
    }

    @Override
    public BookShelfDto getBookShelf(Long id) throws NoBookShelfExistException {
        BookShelf bookShelf = validateBookShelfExistAndReturnValueOrThrowException(id);
        return BookShelfMapper.mapBookShelfToBookShelfDto(bookShelf);
    }

    @Override
    public BookShelfDto updateBookShelf(Long id, String name) throws NoBookShelfExistException {

        BookShelf bookShelf = validateBookShelfExistAndReturnValueOrThrowException(id);
        bookShelf.setName(name);
        return BookShelfMapper.mapBookShelfToBookShelfDto(bookShelfRepository.save(bookShelf));
    }

    @Override
    public BookShelfDto addBooksToBookShelf(Long idBookShelf, List<String> booksUUIDs) throws NoBookShelfExistException, NoBookFoundException {

        BookShelf bookShelf = validateBookShelfExistAndReturnValueOrThrowException(idBookShelf);
        List<BookEntity> items = bookShelf.getItems();
        if(items.isEmpty()) {
            for(String uuid : booksUUIDs) {
              BookEntity book =  booksRepository.findByUuid(uuid).orElseThrow(() ->  new NoBookFoundException(String.format("No Book with uuid: %s found!", uuid)));
              bookShelf.setItems(book);
            }
        } else {
            for(String uuid : booksUUIDs) {
                BookEntity book =  booksRepository.findByUuid(uuid).get();
                long result = items.stream().filter(item -> item.equals(book)).count();
                if(result != 0){
                    continue;
                }
                bookShelf.setItems(book);
            }
        }
        return BookShelfMapper.mapBookShelfToBookShelfDto(bookShelfRepository.save(bookShelf));
    }

    @Override
    public void deleteBookFromBookShelf(Long idBookShelf, String bookUUID) throws NoBookShelfExistException {

        BookShelf bookShelf = validateBookShelfExistAndReturnValueOrThrowException(idBookShelf);
        BookEntity book =  booksRepository.findByUuid(bookUUID).get();
        bookShelf.removeItem(book);
        bookShelfRepository.save(bookShelf);
    }

    private BookShelf validateBookShelfExistAndReturnValueOrThrowException(Long idBookShelf) throws NoBookShelfExistException {

        Optional<BookShelf> bookShelfOpt = bookShelfRepository.findById(idBookShelf);
        if(!bookShelfOpt.isPresent()){
            throw new NoBookShelfExistException();
        }
        return bookShelfOpt.get();
    }
}
