package ru.aza954.SpringMvc2.Controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aza954.SpringMvc2.dao.PersonDAO;
import ru.aza954.SpringMvc2.models.Person;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people",personDAO.index());
        return "Person/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model){
        model.addAttribute("person",personDAO.show(id));
        model.addAttribute("books",personDAO.getBooks(id));
        return "Person/show";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("person",new Person());
        return "Person/new";
    }
    @PostMapping
    public String createPerson(@Valid @ModelAttribute Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "Person/new";
        }
        personDAO.newPerson(person);

        return "redirect:/people";
    }


    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String toedit(@PathVariable("id") int id,Model model){
        model.addAttribute("person",personDAO.show(id));
        return "Person/edit";
    }
    @PatchMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,@ModelAttribute Person person){
        personDAO.update(id,person);
        return "redirect:/people";
    }
}
