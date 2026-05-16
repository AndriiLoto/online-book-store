package online.book.store.bookstore.repository;

import java.util.List;
import java.util.Optional;
import online.book.store.bookstore.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findById(long id);
}
