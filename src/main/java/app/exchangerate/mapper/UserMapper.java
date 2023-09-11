package app.exchangerate.mapper;

import app.exchangerate.config.MapperConfig;
import app.exchangerate.dto.user.UserRegistrationRequestDto;
import app.exchangerate.dto.user.UserResponseDto;
import app.exchangerate.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);
}
