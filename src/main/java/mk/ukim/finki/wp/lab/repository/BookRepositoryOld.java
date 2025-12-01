package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookRepositoryOld {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
    Book save(Book book);
    void deleteById(Long id);
}
