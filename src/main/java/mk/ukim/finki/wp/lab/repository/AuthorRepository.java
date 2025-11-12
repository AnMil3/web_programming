package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository {

    public List<Author> findAll() {
        return DataHolder.authors;
    }

}
