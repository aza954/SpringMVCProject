package ru.aza954.SpringMvc2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aza954.SpringMvc2.dao.PersonDAO;
import ru.aza954.SpringMvc2.models.Person;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {
    List<Person> findByYearofbirthGreaterThanOrderByYearofbirth(int year);
}
