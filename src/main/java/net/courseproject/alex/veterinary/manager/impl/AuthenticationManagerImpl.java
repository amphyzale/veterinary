package net.courseproject.alex.veterinary.manager.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.domain.Role;
import net.courseproject.alex.veterinary.domain.Status;
import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.dto.UserDto;
import net.courseproject.alex.veterinary.dto.request.AuthenticationRequest;
import net.courseproject.alex.veterinary.dto.request.UserRegisterRequest;
import net.courseproject.alex.veterinary.dto.response.LoginResponse;
import net.courseproject.alex.veterinary.dto.response.UserRegisterResponse;
import net.courseproject.alex.veterinary.dto.transformer.impl.UserRegisterRequestTransformerProviderImpl;
import net.courseproject.alex.veterinary.dto.transformer.impl.UserTransformerProvider;
import net.courseproject.alex.veterinary.exception.UserAlreadyExistsException;
import net.courseproject.alex.veterinary.manager.IAuthenticationManager;
import net.courseproject.alex.veterinary.repository.RoleRepository;
import net.courseproject.alex.veterinary.repository.UserRepository;
import net.courseproject.alex.veterinary.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthenticationManagerImpl implements IAuthenticationManager {
    private static final String USER_ROLE = "USER";
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRegisterRequestTransformerProviderImpl guestTransformerProvider;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserTransformerProvider userTransformerProvider;

    @Override
    public LoginResponse login(AuthenticationRequest requestDto) {
        String username = requestDto.getEmail();
        User user = userRepository.findByUsernameAndStatus(username, Status.ACTIVE);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        String token = jwtTokenProvider.createToken(username, user.getRoles());
        return new LoginResponse()
            .setUsername(username)
            .setToken(token)
            .setRoles(user.getRoles()
                    .stream()
                    .map(Role::getName)
                    .collect(Collectors.toList())
            );
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        if (userRepository.findByUsernameAndStatus(userRegisterRequest.getEmail(), Status.ACTIVE) != null) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
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
        return new UserRegisterResponse()
                .setUser(userDto);
    }

    @Override
    public boolean hasUserRole(SecurityContext context) {
        return context.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean hasAdministratorRole(SecurityContext context) {
        return context.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMINISTRATOR"));
    }

    @Override
    public boolean hasDoctorRole(SecurityContext context) {
        return context.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("DOCTOR"));
    }

    @Override
    public boolean hasGrandAdminRole(SecurityContext context) {
        return context.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("GRAND_ADMIN"));
    }

    @Override
    public User getUser(SecurityContext context) {
        return userRepository.findByUsernameAndStatus(context.getAuthentication().getName(), Status.ACTIVE);
    }
}
