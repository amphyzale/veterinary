package net.courseproject.alex.veterinary.manager.impl;

import lombok.AllArgsConstructor;
import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.dto.AuthenticationRequestDto;
import net.courseproject.alex.veterinary.dto.GuestDto;
import net.courseproject.alex.veterinary.dto.UserDto;
import net.courseproject.alex.veterinary.manager.AuthenticationManager;
import net.courseproject.alex.veterinary.security.jwt.JwtTokenProvider;
import net.courseproject.alex.veterinary.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class AuthenticationManagerImpl implements AuthenticationManager {
    private final UserService userService;
    private final org.springframework.security.authentication.AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Map<Object, Object> login(AuthenticationRequestDto requestDto) {
        String username = requestDto.getEmail();
        User user = userService.findByName(username);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        String token = jwtTokenProvider.createToken(username, user.getRoles());
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        return response;
    }

    @Override
    public UserDto register(GuestDto guestDto) {
        User newUser = userService.register(guestDto);
        return UserDto.fromUser(newUser);
    }
}
