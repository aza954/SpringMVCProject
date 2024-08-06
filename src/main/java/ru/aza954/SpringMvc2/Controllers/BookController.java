package ru.aza954.SpringMvc2.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aza954.SpringMvc2.dao.BookDAO;
import ru.aza954.SpringMvc2.dao.PersonDAO;
import ru.aza954.SpringMvc2.models.Book;
import ru.aza954.SpringMvc2.models.Person;
import ru.aza954.SpringMvc2.services.BookService;
import ru.aza954.SpringMvc2.services.PeopleService;

@Controller
@RequestMapping("/books")
public class BookController {
//    private final BookDAO bookDAO;
//    private final PersonDAO personDAO;
    private final PeopleService peopleService;
    private final BookService bookService;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO, PeopleService peopleService, BookService bookService) {
//        this.bookDAO = bookDAO;
//        this.personDAO = personDAO;
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books",bookService.findAll());
        bookService.findByBookName("Кровь Эльфов");
        return "Book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model){
        model.addAttribute("book",bookService.findOne(id));
        model.addAttribute("people",peopleService.findAll());
        model.addAttribute("person", new Person());
        model.addAttribute("checkbook",bookService.checkBook(id));
        model.addAttribute("owner",bookService.getPerson(id));
        return "Book/show";

    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("book",new Book());
        return "Book/new";
    }
    @PostMapping
    public String createBook(@ModelAttribute Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "Book/new";
        }
        bookService.save(book);

        return "redirect:/books";
    }
    @PatchMapping("/{id}")
    public String setBook(@ModelAttribute Person person,@PathVariable("id") int id){
        bookService.setBook(id,person.getUser_id());

        return "redirect:/books/"+id;
    }

    @PostMapping("/{id}")
    public String takeBook(@PathVariable("id") int id){
        bookService.takeBook(id);


        return "redirect:/books/"+ id;
    }
    @GetMapping("/{id}/edit")
    public String edie(@PathVariable("id") int id,Model model){
        model.addAttribute("book",bookService.findOne(id));
        return "Book/edit";
    }
    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id")int id,@ModelAttribute Book book){
        bookService.update(id,book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id")int id){
        bookService.delete(id);
        return "redirect:/books";
    }



}
