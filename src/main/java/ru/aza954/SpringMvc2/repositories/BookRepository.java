package ru.aza954.SpringMvc2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aza954.SpringMvc2.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByName(String name);
}
