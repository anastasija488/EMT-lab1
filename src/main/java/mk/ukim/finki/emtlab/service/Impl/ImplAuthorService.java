package mk.ukim.finki.emtlab.service.Impl;

import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.dtos.AuthorDto;
import mk.ukim.finki.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import mk.ukim.finki.emtlab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplAuthorService implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public ImplAuthorService(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author save(AuthorDto author) {
        Author a = new Author();
        return saveAuthor(author, a);
    }

    @Override
    public Author update(Long id, AuthorDto author) {
        Author a = authorRepository.findById(id).orElse(null);

        if (a == null) {
            return null;
        }

        return saveAuthor(author, a);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    private Author saveAuthor(AuthorDto author, Author a) {
        Country c = countryRepository.findById(author.getCountry()).orElse(null);

        if (c == null) {
            return null;
        }

        a.setName(author.getName());
        a.setSurname(author.getSurname());
        a.setCountry(c);

        return authorRepository.save(a);
    }


}