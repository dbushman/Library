package com.library.api;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.library.api.model.Book;
import com.library.api.service.BookService;

@SpringBootTest
@Transactional
class BookServiceTest {
    @Autowired
    public BookService bookService;

	@Test
	void canGetAllBooks() {
	    List<Book> books = bookService.getAllBooks();
        assertThat(books.size(), greaterThan(0));
	}

	@Test
	void canGetBook() {
	    String id = "1001";
	    Book book = bookService.getBook(id);
        assertEquals(book.getTitle(), "First Book");
	}

	@Test
	void canAddBook() {
        Book book = new Book();
        book.setTitle("Good Book");
        book.setAuthor("John Doe");
        book.setDeweyDecimal("56y.7gb");
        book.setCopyrightYear("2021");

        assertTrue(bookService.addBook(book));
	}

	@Test
	void canUpdateBook() {
	    Book book = new Book();
	    book.setId("1001");
	    book.setAuthor("John Doh");

        assertTrue(bookService.addBook(book));

        Book book2 = bookService.getBook("1001");
        assertEquals(book.getAuthor(), book2.getAuthor());
	}

	@Test
	void canDeleteBook() {
	    String id = "1001";
	    boolean del = bookService.deleteBook(id);
        assertTrue(del);
	}
}
