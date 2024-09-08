package booksProject.books.service;

import booksProject.books.dto.BookDto;
import booksProject.books.repository.BookTagRepository;
import booksProject.books.repository.BooksRepository;
import booksProject.user.entity.UserEntity;
import booksProject.user.repository.UserRepository;
import booksProject.util.BookUtil;
import booksProject.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BookServiceTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BooksRepository booksRepository;
    @Mock
    private BookTagRepository bookTagRepository;
    @Spy
    @InjectMocks
    private BookServiceImpl bookService;
    private static String userLogin;
    private static String author;
    private static UserEntity user;

    @BeforeAll
    static void initData() {
        userLogin = "Test";
        author = "Paul Robertson";
        user = UserUtil.getUser();
    }
    @Test
    void shouldReturnAllBooksFromFindAll() {

        //Given
        Mockito.when(userRepository.findByLogin(userLogin)).thenReturn(Optional.of(user));
        Mockito.when(booksRepository.findAllByUser(user)).thenReturn(Optional.of(List.of(BookUtil.getBook())));

        //When
        final List<BookDto> result = bookService.findAll(userLogin);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("1234-1234-1234-1234", result.get(0).getUuid());
    }
}
