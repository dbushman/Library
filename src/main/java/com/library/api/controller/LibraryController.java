package com.library.api.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.library.api.exception.BadRequestException;
import com.library.api.model.Book;
import com.library.api.service.BookService;

@RestController
public class LibraryController {
    private Logger logger = LoggerFactory.getLogger(LibraryController.class);
    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public List<Book> getAllBooks() {
        logger.info("*** Retrieving list of all books");
        List<Book> books = bookService.getAllBooks();
        logger.info("*** Returning {} books", books.size());

        return books;
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable String id) {
        logger.info("*** Retrieving book with id: {}", id);
        Book book = bookService.getBook(id);
        logger.info("*** Returning " + book);

        return book;
    }

    @PostMapping("/book/new")
    public boolean addNewBook(@RequestBody Book book) {
        boolean success;
        logger.info("*** Adding {} to library", book.getTitle());
        try{
            success = bookService.addBook(book);
        } catch(Exception e) {
            logger.error("*** Could not add book to library. " + e.getMessage());
            e.printStackTrace();
            throw new BadRequestException("Could not add book.");
        }
        logger.info("*** Added {} successfully", book.getTitle());

        return success;
    }

    @PutMapping("/book/update")
    public boolean updateBook(@RequestBody Book book) {
        boolean success = false;
        logger.info("*** Updating " + book.getTitle());
        try{
            success = bookService.updateBook(book);
        } catch(Exception e) {
            logger.error("*** Could not update book. " + e.getMessage());
            e.printStackTrace();
            throw new BadRequestException("Could not update book.");
        }
        logger.info("*** Updated {} successfully", book.getTitle());

        return success;
    }

    @DeleteMapping("/book/delete/{id}")
    public boolean deleteBook(@PathVariable String id) {
        logger.info("*** Deleting book with id: " + id);
        if(bookService.deleteBook(id)) {
            logger.info("*** Deletion successful");
            return true;
        }

        logger.info("*** Deletion failed");
        throw new BadRequestException("Could not delete book " + id);
    }

}