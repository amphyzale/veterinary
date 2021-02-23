package net.courseproject.alex.veterinary.service.impl;

import lombok.AllArgsConstructor;
import net.courseproject.alex.veterinary.dto.AuthenticationRequestDto;
import net.courseproject.alex.veterinary.dto.GuestDto;
import net.courseproject.alex.veterinary.dto.UserDto;
import net.courseproject.alex.veterinary.manager.AuthenticationManager;
import net.courseproject.alex.veterinary.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    @Override
    public Map<Object, Object> login(AuthenticationRequestDto requestDto) {
        return authenticationManager.login(requestDto);
    }

    @Override
    public UserDto register(GuestDto guestDto) {
        return authenticationManager.register(guestDto);
    }
}
