package booksProject.books.service;

import booksProject.books.dto.BookDto;
import booksProject.books.dto.BookForm;
import booksProject.books.entity.BookEntity;
import booksProject.books.mappers.BookMapper;
import booksProject.books.repository.BookTagRepository;
import booksProject.books.repository.BooksRepository;
import booksProject.user.entity.UserEntity;
import booksProject.user.repository.UserRepository;
import booksProject.util.BookFormUtil;
import booksProject.util.BookUtil;
import booksProject.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class BookServiceTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BooksRepository booksRepository;
    @Mock
    private BookTagRepository bookTagRepository;
    @InjectMocks
    private BookServiceImpl bookService;
    private static String userLogin;

    @BeforeAll
    static void initData() {
        userLogin = "Test";
    }
    @Test
    void shouldReturnAllBooksFromFindAllMethod() {

        //Given
        UserEntity user = UserUtil.getUser();
        Mockito.when(userRepository.findByLogin(userLogin)).thenReturn(Optional.of(user));
        Mockito.when(booksRepository.findAllByUser(any(UserEntity.class))).thenReturn(Optional.of(user.getBooks().stream().toList()));

        //When
        final List<BookDto> result = bookService.findAll(userLogin);
        for (BookDto book : result) {
            System.out.println(book.toString());
        }

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("1234-1234-1234-1234", result.get(0).getUuid());
        Assertions.assertEquals("1000-1000-1111-1111", result.get(1).getUuid());
        Assertions.assertEquals("1000-0000-2222-3333", result.get(2).getUuid());
    }

    @Test
    void shouldReturnAllBooksByAuthorFromFindAllByAuthorMethod() {

        //Given
        UserEntity user = UserUtil.getUser();
        String author = "Json Hrejterzy";
        Mockito.when(userRepository.findByLogin(userLogin)).thenReturn(Optional.of(user));
        Mockito.when(booksRepository.findAllByUser(user)).thenReturn(Optional.of(user.getBooks().stream().toList()));

        //When
        final List<BookDto> result = bookService.findAllByAuthor(author, userLogin);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(author, result.get(0).getAuthor());
        Assertions.assertEquals(author, result.get(1).getAuthor());
        Assertions.assertEquals("1000-1000-1111-1111", result.get(0).getUuid());
        Assertions.assertEquals("1000-0000-2222-3333", result.get(1).getUuid());
    }

    @Test
    void shouldCreateBookFromCreateMethod() {

        //Given
        UserEntity user = UserUtil.getUser();
        BookForm form = BookFormUtil.getBookForm();
        BookEntity entity = BookMapper.map(form);
        Mockito.when(userRepository.findByLogin(userLogin)).thenReturn(Optional.of(user));
        Mockito.when(booksRepository.save(any())).thenReturn(entity);

        //When
        final BookDto result = bookService.create(form, userLogin);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(form.getAuthor(), result.getAuthor());
        Assertions.assertEquals(form.getTitle(), result.getTitle());
        Assertions.assertEquals(form.getDetails().getIsbn(), result.getDetails().getIsbn());
        Assertions.assertEquals(form.getDetails().getLang(), result.getDetails().getLang());
        Assertions.assertEquals(form.getDetails().getPublisher(), result.getDetails().getPublisher());
        Assertions.assertEquals(form.getDetails().getDescription(), result.getDetails().getDescription());
    }

    @Test
    void shouldDeleteBookFromDeleteMethod() {

        //Given
        UserEntity user = UserUtil.getUser();
        String uuid = "1234-1234-1234-1234";
        Mockito.when(userRepository.findByLogin(userLogin)).thenReturn(Optional.of(user));
        Mockito.when(booksRepository.findByUuid(uuid)).thenReturn(Optional.of(BookUtil.getBook()));

        //When
        final Boolean result = bookService.delete(uuid, userLogin);

        //Then
        Assertions.assertEquals(true, result);
    }

    @Test
    void shouldUpdateBookFromUpdateMethod() {

        //Given
        String uuid = "1234-1234-1234-1234";
        BookEntity existingBook = BookUtil.getBook2();
        BookEntity updatedBook = BookUtil.getUpdatedBook();
        BookForm bookForm = BookFormUtil.getBookForm();
        Mockito.when(booksRepository.findByUuid(uuid)).thenReturn(Optional.of(existingBook));
        Mockito.when(booksRepository.saveAndFlush(any(BookEntity.class))).thenReturn(updatedBook);

        //When
        BookDto result = bookService.update(uuid, bookForm);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(bookForm.getAuthor(), result.getAuthor());
        Assertions.assertEquals(bookForm.getTitle(), result.getTitle());
        Assertions.assertEquals(bookForm.getTags().size(), result.getTags().size());
    }
}
