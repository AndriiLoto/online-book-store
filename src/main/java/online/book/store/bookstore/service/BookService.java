package online.book.store.bookstore.service;

import java.util.List;
import online.book.store.bookstore.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
