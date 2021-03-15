package net.courseproject.alex.veterinary.manager.impl;

import lombok.AllArgsConstructor;
import net.courseproject.alex.veterinary.domain.Status;
import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.dto.UserDto;
import net.courseproject.alex.veterinary.dto.request.AuthenticationRequest;
import net.courseproject.alex.veterinary.dto.request.UserRegisterRequest;
import net.courseproject.alex.veterinary.dto.response.LoginResponse;
import net.courseproject.alex.veterinary.dto.response.UserRegisterResponse;
import net.courseproject.alex.veterinary.dto.transformer.impl.UserRegisterRequestTransformerProviderImpl;
import net.courseproject.alex.veterinary.dto.transformer.impl.UserTransformerProvider;
import net.courseproject.alex.veterinary.manager.AuthenticationManager;
import net.courseproject.alex.veterinary.repository.RoleRepository;
import net.courseproject.alex.veterinary.repository.UserRepository;
import net.courseproject.alex.veterinary.security.jwt.JwtTokenProvider;
import net.courseproject.alex.veterinary.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
@AllArgsConstructor
public class AuthenticationManagerImpl implements AuthenticationManager {
    private static final String USER_ROLE = "USER";
    private final UserService userService;
    private final org.springframework.security.authentication.AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRegisterRequestTransformerProviderImpl guestTransformerProvider;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserTransformerProvider userTransformerProvider;

    @Override
    public LoginResponse login(AuthenticationRequest requestDto) {
        String username = requestDto.getEmail();
        User user = userService.findByName(username);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        String token = jwtTokenProvider.createToken(username, user.getRoles());
        return new LoginResponse(username, token, user.getRoles());
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        guestTransformerProvider.transformDtoToDomain(userRegisterRequest, user);
        user.setRoles(Collections.singletonList(roleRepository.findByName(USER_ROLE)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setCreated(LocalDateTime.now());
        user.setLastVisit(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        UserDto userDto = new UserDto();
        userTransformerProvider.transformDomainToDto(userRepository.save(user), userDto);
        return new UserRegisterResponse(userDto);
    }
}
