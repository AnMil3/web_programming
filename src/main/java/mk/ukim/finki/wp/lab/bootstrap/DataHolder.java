package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    @PostConstruct
    public void init() {
        books.add(new Book("Book 1", "roman", 4.4));
        books.add(new Book("Book 2", "roman", 4.5));
        books.add(new Book("Book 3", "roman", 4.6));
        books.add(new Book("Book 4", "roman", 4.7));
        books.add(new Book("Book 5", "roman", 4.8));
    }
}
