package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
    Book save(String title, String genre, Double averageRating, Long authorId);
    Book edit(Long bookId, String title, String genre, Double averageRating, Long authorId);
    void deleteById(Long id);
}
