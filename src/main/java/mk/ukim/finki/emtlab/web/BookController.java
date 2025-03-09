package mk.ukim.finki.emtlab.web;

import mk.ukim.finki.emtlab.model.Book;
import mk.ukim.finki.emtlab.model.dtos.BookDto;
import mk.ukim.finki.emtlab.model.enums.Category;
import mk.ukim.finki.emtlab.service.BookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class BookController {
    
    private final BookService bookService;
    
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping("/api/books/")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/api/books/categories")
    public List<String> getAllCategories() {
        Category[] categories = Category.values();
        List<String> categoryNames = new ArrayList<>();

        for (Category category : categories) {
            categoryNames.add(category.name());
        }

        return categoryNames;
    }

    @GetMapping("/api/books/paged")
    public List<Book> getBooksByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return bookService.getAllBooksByPage(PageRequest.of(page, size));
    }
    
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }
    
    @PostMapping("/api/books/create")
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
        Book book = bookService.save(bookDto);
        if (book == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(book);
    }
    
    @PutMapping("/api/books/{id}/update")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        Book book = bookService.update(id, bookDto);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }
    
    @DeleteMapping("/api/books/{id}/delete")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/api/books/{id}/mark-taken")
    public ResponseEntity<Void> markBookAsTaken(@PathVariable Long id) {
        bookService.markBookAsTaken(id);
        return ResponseEntity.ok().build();
    }
}
