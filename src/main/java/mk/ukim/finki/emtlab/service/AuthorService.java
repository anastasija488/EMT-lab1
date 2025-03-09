package mk.ukim.finki.emtlab.service;

import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.dtos.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getAuthorById(Long id);

    Author save(AuthorDto author);

    Author update(Long id, AuthorDto author);

    void deleteAuthor(Long id);
}