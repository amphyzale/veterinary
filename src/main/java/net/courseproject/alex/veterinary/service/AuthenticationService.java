package net.courseproject.alex.veterinary.service;

import net.courseproject.alex.veterinary.dto.request.AuthenticationRequest;
import net.courseproject.alex.veterinary.dto.request.UserRegisterRequest;
import net.courseproject.alex.veterinary.dto.response.LoginResponse;
import net.courseproject.alex.veterinary.dto.response.UserRegisterResponse;

public interface AuthenticationService {
    LoginResponse login(AuthenticationRequest requestDto);
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);
}
