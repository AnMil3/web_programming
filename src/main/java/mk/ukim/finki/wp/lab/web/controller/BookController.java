package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    public final BookService bookService;
    public final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false)String error,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(required = false) String rating,
                               Model model) {

        List<Book> books;

        if ((keyword != null && !keyword.isEmpty()) || (rating != null && !rating.isEmpty())) {
            double r = Double.parseDouble(rating);
            books = bookService.searchBooks(keyword, r);
        }
        else
            books = bookService.listAll();

        model.addAttribute("books", books);
        return "listBooks";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.save(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @GetMapping("/book-form")
    public String getAddBookPage(Model model) {
//        model.addAttribute("book", new Book()); // empty book used by form
        model.addAttribute("authors", authorService.findAll());
        return "bookForm";
    }

    @GetMapping("/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.listAll().stream().filter(book1 -> book1.getId().equals(id)).toList().get(0);
        if (book == null) {
            return "redirect:/books?error=BookNotFound";
        }
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        return "bookForm";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String genre,
                           @RequestParam(required = false) Double averageRating,
                           @RequestParam(required = false) Long authorId,
                           Model model) {
        bookService.edit(bookId, title, genre, averageRating, authorId);
        model.addAttribute("books", bookService.listAll());
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

}
