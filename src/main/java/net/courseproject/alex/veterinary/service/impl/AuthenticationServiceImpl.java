package net.courseproject.alex.veterinary.service.impl;

import lombok.AllArgsConstructor;
import net.courseproject.alex.veterinary.dto.request.AuthenticationRequest;
import net.courseproject.alex.veterinary.dto.request.UserRegisterRequest;
import net.courseproject.alex.veterinary.dto.response.LoginResponse;
import net.courseproject.alex.veterinary.dto.response.UserRegisterResponse;
import net.courseproject.alex.veterinary.manager.IAuthenticationManager;
import net.courseproject.alex.veterinary.service.IAuthenticationService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final IAuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(AuthenticationRequest requestDto) {
        return authenticationManager.login(requestDto);
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        return authenticationManager.register(userRegisterRequest);
    }
}
