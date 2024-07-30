package ru.aza954.SpringMvc2.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.aza954.SpringMvc2.models.Book;
import ru.aza954.SpringMvc2.models.Person;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));

    }
    public Book show(int id){
        if (jdbcTemplate.query("SELECT * FROM book WHERE book_id=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class)).isEmpty()){
            return null;
        }
        else return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class)).get(0);


    }
    public void newBook(Book book){
        jdbcTemplate.update("insert into book(author, year,name)  values(?, ?,?);",book.getAuthor(),book.getYear(),book.getName());
    }

    public List<Book> checkBook(int id){
        return jdbcTemplate.query("SELECT * from Book where book_id=? and user_id IS NULL", new BeanPropertyRowMapper<>(Book.class),id);
    }
    public void setBook(int bookId,int personId){
        jdbcTemplate.update("UPDATE book set user_id=? where book_id=?",personId,bookId);
    }

    public List<Person> getPerson(int id){
        return jdbcTemplate.query("SELECT Person.user_id,fullName,yearOfbirth from person join book on person.user_id = book.user_id and book_id=?",new BeanPropertyRowMapper<>(Person.class),id);
    }
    public void takeBook(int id){
        jdbcTemplate.update("UPDATE book set user_id= null where book_id=?",id);
    }
    public void updateBook(Book book,int id){
        jdbcTemplate.update("UPDATE book set name=?,author=?,year=? where book_id =?",book.getName(),book.getAuthor(),book.getYear(),id);


    }
    public void deleteBook(int id){
        jdbcTemplate.update("DELETE from book where book_id=?",id);
    }


}
