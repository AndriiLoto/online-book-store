package online.book.store.bookstore.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import online.book.store.bookstore.dto.BookDto;
import online.book.store.bookstore.dto.CreateBookRequestDto;
import online.book.store.bookstore.mapper.BookMapper;
import online.book.store.bookstore.model.Book;
import online.book.store.bookstore.repository.BookRepository;
import online.book.store.bookstore.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toBookDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book book = getBookById(id);
        return bookMapper.toBookDto(book);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public BookDto updateById(Long id, CreateBookRequestDto requestDto) {
        Book book = getBookById(id);
        bookMapper.updateBookFromDto(requestDto,book);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toBookDto(updatedBook);
    }

    private Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book not found with id: " + id)
        );
    }
}
