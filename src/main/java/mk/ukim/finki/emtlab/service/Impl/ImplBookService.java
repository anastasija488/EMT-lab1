package mk.ukim.finki.emtlab.service.Impl;

import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.Book;
import mk.ukim.finki.emtlab.model.dtos.BookDto;
import mk.ukim.finki.emtlab.repository.BookRepository;
import mk.ukim.finki.emtlab.service.AuthorService;
import mk.ukim.finki.emtlab.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplBookService implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public ImplBookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book save(BookDto book) {
        Book b = new Book();
        return saveBook(book, b);
    }

    @Override
    public Book update(Long id, BookDto book) {
        Book b = bookRepository.findById(id).orElse(null);

        if (b == null) {
            return null;
        }

        return saveBook(book, b);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void markBookAsTaken(Long id) {
        Book b = bookRepository.findById(id).orElse(null);

        if (b == null) {
            return;
        }

        b.setAvailableCopies(b.getAvailableCopies() == 0 ? 0 : b.getAvailableCopies() - 1);

        bookRepository.save(b);
    }

    @Override
    public List<Book> getAllBooksByPage(Pageable withPage) {
        return bookRepository.findAll(withPage).getContent();
    }

    private Book saveBook(BookDto book, Book b) {
        Author a = authorService.getAuthorById(book.getAuthor());

        if (a == null) {
            return null;
        }

        b.setName(book.getName().isEmpty() ? "Unnamed Book" : book.getName());
        b.setCategory(book.getCategory());
        b.setAuthor(a);
        b.setAvailableCopies(book.getAvailableCopies() >= 0 ? book.getAvailableCopies() : 0);

        return bookRepository.save(b);
    }
}
