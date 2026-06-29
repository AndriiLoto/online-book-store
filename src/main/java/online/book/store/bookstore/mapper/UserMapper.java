package online.book.store.bookstore.mapper;

import online.book.store.bookstore.config.MapperConfig;
import online.book.store.bookstore.dto.user.UserRegistrationRequestDto;
import online.book.store.bookstore.dto.user.UserResponseDto;
import online.book.store.bookstore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(config = MapperConfig.class,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    User toUser(UserRegistrationRequestDto dto);

    UserResponseDto toUserResponseDto(User user);
}
