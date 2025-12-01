package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorServiceImpl implements AuthorService {

    public final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findAll().stream().filter(a -> Objects.equals(a.getId(), id)).toList().stream().findFirst().orElse(null);
    }

    @Override
    public Author save(String name, String surname, String country, String biography) {
        Author author = new Author(name, surname, country, biography);
        return authorRepository.save(author);
    }

    @Override
    public Author edit(Long id, String name, String surname, String country, String biography) {
        Author author = authorRepository.findAll().stream().filter(a -> Objects.equals(a.getId(), id)).toList().get(0);
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        author.setBiography(biography);
        return authorRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
