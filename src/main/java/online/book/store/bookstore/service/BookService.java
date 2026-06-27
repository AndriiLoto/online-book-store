package online.book.store.bookstore.service;

import java.util.List;
import online.book.store.bookstore.dto.book.BookDto;
import online.book.store.bookstore.dto.book.BookSearchParameters;
import online.book.store.bookstore.dto.book.CreateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    Page<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    void deleteById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto requestDto);

    public List<BookDto> search(BookSearchParameters bookSearchParameters);
}
