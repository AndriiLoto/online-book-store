package online.book.store.bookstore.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import online.book.store.bookstore.dto.book.BookDto;
import online.book.store.bookstore.dto.book.BookSearchParameters;
import online.book.store.bookstore.dto.book.CreateBookRequestDto;
import online.book.store.bookstore.exception.EntityNotFoundException;
import online.book.store.bookstore.mapper.BookMapper;
import online.book.store.bookstore.model.Book;
import online.book.store.bookstore.repository.book.BookRepository;
import online.book.store.bookstore.repository.book.BookSpecificationBuilder;
import online.book.store.bookstore.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    @Transactional
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toBookDto(bookRepository.save(book));
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        return bookRepository
                .findAll(pageable)
                .map(bookMapper::toBookDto);
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

    @Override
    public List<BookDto> search(BookSearchParameters bookSearchParameters) {
        Specification<Book> bookSpecification =
                bookSpecificationBuilder.build(bookSearchParameters);
        return bookRepository.findAll(bookSpecification)
                .stream()
                .map(bookMapper::toBookDto)
                .toList();
    }
}
