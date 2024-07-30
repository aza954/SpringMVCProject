package ru.aza954.SpringMvc2.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.aza954.SpringMvc2.models.Book;
import ru.aza954.SpringMvc2.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));

    }
    public Person show(int id){
        if (jdbcTemplate.query("SELECT * FROM person WHERE user_id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).isEmpty()){
            return null;
        }
        else return jdbcTemplate.query("SELECT * FROM person WHERE user_id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).get(0);


    }
    public void newPerson(Person person){
        jdbcTemplate.update("INSERT INTO person(fullName, yearOfbirth) values (?,?)",person.getFullname(),person.getYearofbirth());
    }
    public List<Book> getBooks(int id){
        return jdbcTemplate.query("SELECT book_id,Book.user_id,author,name,year from book join person on person.user_id = book.user_id and Person.user_id =?",new BeanPropertyRowMapper<>(Book.class),id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person where user_id=?",id);

    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE person set fullName=?,yearOfbirth=? where user_id=?",updatedPerson.getFullname(),updatedPerson.getYearofbirth(),id);


    }

}
