package app.exchangerate.service.impl;

import app.exchangerate.dto.user.UserRegistrationRequestDto;
import app.exchangerate.dto.user.UserResponseDto;
import app.exchangerate.exeption.RegistrationException;
import app.exchangerate.mapper.UserMapper;
import app.exchangerate.model.Role;
import app.exchangerate.model.User;
import app.exchangerate.repository.RoleRepository;
import app.exchangerate.repository.UserRepository;
import app.exchangerate.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        Role userRole = roleRepository.findRoleByRoleName(Role.RoleName.USER)
                .orElseThrow(() -> new RegistrationException("Can't find role by name"));
        user.setRoles(Set.of(userRole));
        User saveUser = userRepository.save(user);
        return userMapper.toDto(saveUser);
    }
}
