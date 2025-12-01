package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getAuthorsPage(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "listAuthors";
    }

    @GetMapping("/author-form")
    public String getAddAuthorPage(Model model) {
        return "authorForm";
    }

    @GetMapping("/author-form/{id}")
    public String getEditAuthorPage(@PathVariable Long id, Model model) {
        Author author = authorService.findById(id);
        if (author == null) {
            return "redirect:/authors?error=AuthorNotFound";
        }
        model.addAttribute("author", author);
        return "authorForm";
    }

    @PostMapping("/add")
    public String saveAuthor(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography) {
        authorService.save(name, surname, country, biography);
        return "redirect:/authors";
    }

    @PostMapping("/edit/{id}")
    public String editAuthor(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography) {
        authorService.edit(id, name, surname, country, biography);
        return "redirect:/authors";
    }

    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}
