package online.book.store.bookstore.mapper;

import online.book.store.bookstore.config.MapperConfig;
import online.book.store.bookstore.dto.BookDto;
import online.book.store.bookstore.dto.CreateBookRequestDto;
import online.book.store.bookstore.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toBookDto(Book book);

    Book toModel(CreateBookRequestDto bookRequestDtoDto);
}
