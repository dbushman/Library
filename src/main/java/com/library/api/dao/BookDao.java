package com.library.api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.api.model.Book;

@Repository
public class BookDao {
    private final static String RETRIEVE_ALL_BOOKS = "select * from books";
    private final static String FIND_BOOK_BY_ID = "select * from books where id = :id";
    private final static String ADD_BOOK = "insert into books (title, author, dewey_decimal, copyright_year), values (:title, :author, :dewey_dec, cr_year)";
    private final static String UPDATE_TITLE = "update books set title = :title where id = :id";
    private final static String UPDATE_AUTHOR = "update books set author = :author where id = :id";
    private final static String UPDATE_DEWEY = "update books set dewey_decimal = :dewey_dec where id = :id";
    private final static String UPDATE_YEAR = "update books set copyright_year = :cr_year where id = :id";
    private final static String DELETE_BOOK = "delete from books where id = :id";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Book> getAllBooks(){
        return jdbcTemplate.query(RETRIEVE_ALL_BOOKS, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBook(String id){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.queryForObject(FIND_BOOK_BY_ID, params, new BeanPropertyRowMapper<>(Book.class));
    }

    public int addBook(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("author", book.getAuthor());
        params.addValue("dewey_dec", book.getDeweyDecimal());
        params.addValue("cr_year", book.getCopyrightYear());
        return jdbcTemplate.update(ADD_BOOK, params);
    }

    public int updateTitle(String id, String title) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("title", title);

        return jdbcTemplate.update(UPDATE_TITLE, params);
    }

    public int updateAuthor(String id, String author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("author", author);

        return jdbcTemplate.update(UPDATE_AUTHOR, params);
    }

    public int updateDewey(String id, String dewey) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("dewey_dec", dewey);

        return jdbcTemplate.update(UPDATE_DEWEY, params);
    }

    public int updateYear(String id, String year) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("cr_year", year);

        return jdbcTemplate.update(UPDATE_YEAR, params);
    }

    public int deleteBook(String id){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbcTemplate.update(DELETE_BOOK, params);    }
}
