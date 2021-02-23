package net.courseproject.alex.veterinary.controller;

import lombok.AllArgsConstructor;
import net.courseproject.alex.veterinary.dto.AuthenticationRequestDto;
import net.courseproject.alex.veterinary.dto.GuestDto;
import net.courseproject.alex.veterinary.dto.UserDto;
import net.courseproject.alex.veterinary.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/veterinary/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            return ResponseEntity.ok(authenticationService.login(requestDto));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody GuestDto guestDto) {
        UserDto newUser = authenticationService.register(guestDto);
        if (newUser != null) {
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        throw new BadCredentialsException("Invalid username or password");
    }
}
