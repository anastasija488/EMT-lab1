package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}