package online.book.store.bookstore.service;

import java.util.List;
import online.book.store.bookstore.dto.BookDto;
import online.book.store.bookstore.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto findById(long id);
}
