package booksProject.books.service;

import booksProject.books.BookExistException;
import booksProject.books.NoBookFoundException;
import booksProject.books.dto.BookForm;
import booksProject.books.entity.BookEntity;
import booksProject.books.repository.BookTagRepository;
import booksProject.books.repository.BooksRepository;
import booksProject.user.NoUserFoundException;
import booksProject.user.entity.UserEntity;
import booksProject.user.repository.UserRepository;
import booksProject.util.BookFormUtil;
import booksProject.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
class BookServiceExceptionsTests {

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
		author = "John Simple";
		user = UserUtil.getUser();
	}
	@Test
	void shouldReturnNoUserFoundExceptionFromFindAll() {

		//Given

		//When
		final var result = Assertions.assertThrows(NoUserFoundException.class, () -> bookService.findAll(userLogin));

		//Then
		Assertions.assertEquals(result.getClass(), NoUserFoundException.class);
		Assertions.assertEquals(String.format("No User  %s found!", userLogin), result.getMessage());
	}

	@Test
	void shouldReturnNoBookFoundExceptionFromFindAll() {

		//Given
		Mockito.when(userRepository.findByLogin(userLogin)).thenReturn(Optional.of(user));

		//When
		final var result = Assertions.assertThrows(NoBookFoundException.class, () -> bookService.findAll(userLogin));

		//Then
		Assertions.assertEquals(result.getClass(), NoBookFoundException.class);
		Assertions.assertEquals(String.format("No Books for user: %s found!", userLogin), result.getMessage());
	}

	@Test
	void shouldReturnNoUserFoundExceptionFromFindAllByAuthor() {

		//Given

		//When
		final var result = Assertions.assertThrows(NoUserFoundException.class, () -> bookService.findAllByAuthor(author, userLogin));

		//Then
		Assertions.assertEquals(result.getClass(), NoUserFoundException.class);
		Assertions.assertEquals(String.format("No User  %s found!", userLogin), result.getMessage());
	}

	@Test
	void shouldReturnNoBookFoundExceptionFromFindAllByAuthor() {

		//Given
		Mockito.when(userRepository.findByLogin(userLogin)).thenReturn(Optional.of(user));

		//When
		final var result = Assertions.assertThrows(NoBookFoundException.class, () -> bookService.findAllByAuthor(author, userLogin));

		//Then
		Assertions.assertEquals(result.getClass(), NoBookFoundException.class);
		Assertions.assertEquals(String.format("No Books for user: %s found!", userLogin), result.getMessage());
	}

	@Test
	void shouldReturnNoBookFoundExceptionForAuthorFromFindAllByAuthor() {

		//Given
		Mockito.when(userRepository.findByLogin(userLogin)).thenReturn(Optional.of(user));
		Mockito.when(booksRepository.findAllByUser(any())).thenReturn(Optional.of(getBooksByUser(userLogin)));

		//When
		final var result = Assertions.assertThrows(NoBookFoundException.class, () -> bookService.findAllByAuthor(author, userLogin));

		//Then
		System.out.println(result.getMessage());
		Assertions.assertEquals(result.getClass(), NoBookFoundException.class);
		Assertions.assertEquals(String.format("No Books for author: %s found!", author), result.getMessage());
	}

	@Test
	void shouldReturnNoBookFoundExceptionFromFindByUuid() {

		//Given
		String uuid = "1234-1234-1234";
		//When
		final var result = Assertions.assertThrows(NoBookFoundException.class, () -> bookService.findByUuid(uuid));

		//Then
		Assertions.assertEquals(result.getClass(), NoBookFoundException.class);
		Assertions.assertEquals(String.format("No Book with uuid: %s found!", uuid), result.getMessage());
	}

	@Test
	void shouldReturnBookExistExceptionFromCreate() {

		//Given
		BookForm form = BookFormUtil.getBookForm();
		Mockito.when(userRepository.findByLogin(userLogin)).thenReturn(Optional.of(user));
		Mockito.doReturn(true).when(bookService).validateBook(form, user);
		//When
		final var result = Assertions.assertThrows(BookExistException.class, () -> bookService.create(form, userLogin));

		//Then
		Assertions.assertEquals(result.getClass(), BookExistException.class);
		Assertions.assertEquals("Book with this Author and Title exist!", result.getMessage());
	}

	@Test
	void shouldReturnNoUserFoundExceptionFromCreate() {

		//Given
		BookForm form = BookFormUtil.getBookForm();
		//When
		final var result = Assertions.assertThrows(NoUserFoundException.class, () -> bookService.create(form, userLogin));

		//Then
		Assertions.assertEquals(result.getClass(), NoUserFoundException.class);
		Assertions.assertEquals(String.format("No User  %s found!", userLogin), result.getMessage());
	}

	@Test
	void shouldReturnNoUserFoundExceptionFromDelete() {

		//Given
		String uuid = "1234-1234-1234";
		//When
		final var result = Assertions.assertThrows(NoUserFoundException.class, () -> bookService.delete(uuid, userLogin));

		//Then
		Assertions.assertEquals(result.getClass(), NoUserFoundException.class);
		Assertions.assertEquals(String.format("No User  %s found!", userLogin), result.getMessage());
	}

	@Test
	void shouldReturnNoBookFoundExceptionFromDelete() {

		//Given
		String uuid = "1234-1234-1234";
		//When
		Mockito.when(userRepository.findByLogin(userLogin)).thenReturn(Optional.of(user));
		final var result = Assertions.assertThrows(NoBookFoundException.class, () -> bookService.delete(uuid, userLogin));

		//Then
		Assertions.assertEquals(result.getClass(), NoBookFoundException.class);
		Assertions.assertEquals(String.format("No Book with uuid: %s found!", uuid), result.getMessage());
	}

	@Test
	void shouldReturnNoBookFoundExceptionFromUpdate() {

		//Given
		String uuid = "1234-1234-1234";
		BookForm form = BookFormUtil.getBookForm();
		//When
		final var result = Assertions.assertThrows(NoBookFoundException.class, () -> bookService.update(uuid, form));

		//Then
		Assertions.assertEquals(result.getClass(), NoBookFoundException.class);
		Assertions.assertEquals(String.format("No Book with uuid: %s found!", uuid), result.getMessage());
	}

	private List<BookEntity> getBooksByUser(String login) {

		List<BookEntity> result = new ArrayList<>();
		BookEntity testEntity = new BookEntity();
		testEntity.setAuthor("Paul Robertson");
		testEntity.setTitle("Testing");
		testEntity.setUuid("1234-1234-1234-1234");
		result.add(testEntity);

		return  result;
	}


}
