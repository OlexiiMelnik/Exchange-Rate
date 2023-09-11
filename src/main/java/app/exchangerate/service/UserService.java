package app.exchangerate.service;

import app.exchangerate.dto.user.UserRegistrationRequestDto;
import app.exchangerate.dto.user.UserResponseDto;
import app.exchangerate.exeption.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException;
}
