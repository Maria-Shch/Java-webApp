package ru.shcherbatykh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shcherbatykh.codesjava.Config;
import ru.shcherbatykh.dao.PersonRepo;
import ru.shcherbatykh.models.Person;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonRepo personRepo;

    @Autowired
    public PeopleController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personRepo.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personRepo.findById(id).get());
        return "show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        personRepo.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personRepo.findById(id).get());
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
            @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "edit";
        }
        personRepo.save(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personRepo.deleteById(id);
        return "redirect:/people";
    }

}
