package ru.aza954.SpringMvc2.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aza954.SpringMvc2.dao.BookDAO;
import ru.aza954.SpringMvc2.dao.PersonDAO;
import ru.aza954.SpringMvc2.models.Book;
import ru.aza954.SpringMvc2.models.Person;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books",bookDAO.index());
        return "Book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model){
        model.addAttribute("book",bookDAO.show(id));
        model.addAttribute("people",personDAO.index());
        model.addAttribute("person", new Person());
        model.addAttribute("checkbook",bookDAO.checkBook(id));
        model.addAttribute("owner",bookDAO.getPerson(id));
        return "Book/show";

    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("book",new Book());
        return "Book/new";
    }
    @PostMapping
    public String createBook(@ModelAttribute Book book){
        bookDAO.newBook(book);

        return "redirect:/books";
    }
    @PatchMapping("/{id}")
    public String setBook(@ModelAttribute Person person,@PathVariable("id") int id){
        bookDAO.setBook(id,person.getUser_id());

        return "redirect:/books/"+id;
    }

    @PostMapping("/{id}")
    public String takeBook(@PathVariable("id") int id){
        bookDAO.takeBook(id);


        return "redirect:/books/"+ id;
    }
    @GetMapping("/{id}/edit")
    public String edie(@PathVariable("id") int id,Model model){
        model.addAttribute("book",bookDAO.show(id));
        return "Book/edit";
    }
    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id")int id,@ModelAttribute Book book){
        bookDAO.updateBook(book,id);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id")int id){
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }



}
