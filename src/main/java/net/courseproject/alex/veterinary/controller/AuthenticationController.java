package net.courseproject.alex.veterinary.controller;

import lombok.AllArgsConstructor;
import net.courseproject.alex.veterinary.dto.request.AuthenticationRequest;
import net.courseproject.alex.veterinary.dto.request.UserRegisterRequest;
import net.courseproject.alex.veterinary.dto.response.LoginResponse;
import net.courseproject.alex.veterinary.dto.response.UserRegisterResponse;
import net.courseproject.alex.veterinary.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veterinary/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated AuthenticationRequest requestDto) {
            ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
            LoginResponse response = authenticationService.login(requestDto);
            return responseEntity.body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody @Validated UserRegisterRequest guestDto) {
            ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(HttpStatus.CREATED);
            UserRegisterResponse response = authenticationService.register(guestDto);
            return responseEntity.body(response);
    }
}
