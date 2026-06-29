package online.book.store.bookstore.mapper;

import online.book.store.bookstore.config.MapperConfig;
import online.book.store.bookstore.dto.book.BookDto;
import online.book.store.bookstore.dto.book.CreateBookRequestDto;
import online.book.store.bookstore.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toBookDto(Book book);

    Book toModel(CreateBookRequestDto bookRequestDtoDto);

    void updateBookFromDto(CreateBookRequestDto bookRequestDto, @MappingTarget Book book);
}
