package mk.ukim.finki.emtlab.service;

import mk.ukim.finki.emtlab.model.Book;
import mk.ukim.finki.emtlab.model.dtos.BookDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book save(BookDto book);

    Book update(Long id, BookDto book);

    void deleteBook(Long id);

    void markBookAsTaken(Long id);

    List<Book> getAllBooksByPage(Pageable withPage);
}