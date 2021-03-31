package com.library.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.library.api.dao.BookDao;
import com.library.api.model.Book;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }

    public Book getBook(String id){
        return bookDao.getBook(id);
    }

    public boolean addBook(Book book) {
        return bookDao.addBook(book) == 1;
    }

    public boolean updateBook(Book book) {
        int cnt = 0;
        // only try to update the values that are not null from the client
        if(StringUtils.hasText(book.getTitle()))
            cnt += bookDao.updateTitle(book.getId(), book.getTitle());
        if(StringUtils.hasText(book.getAuthor()))
            cnt += bookDao.updateAuthor(book.getId(), book.getAuthor());
        if(StringUtils.hasText(book.getDeweyDecimal()))
            cnt += bookDao.updateDewey(book.getId(), book.getDeweyDecimal());
        if(StringUtils.hasText(book.getCopyrightYear()))
            cnt += bookDao.updateYear(book.getId(), book.getCopyrightYear());

        return cnt > 1;
    }

    public boolean deleteBook(String id){
        return bookDao.deleteBook(id) == 1;
    }
}
