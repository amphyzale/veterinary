package net.courseproject.alex.veterinary.manager;

import net.courseproject.alex.veterinary.dto.AuthenticationRequestDto;
import net.courseproject.alex.veterinary.dto.GuestDto;
import net.courseproject.alex.veterinary.dto.UserDto;

import java.util.Map;

public interface AuthenticationManager {
    Map<Object, Object> login(AuthenticationRequestDto requestDto);
    UserDto register(GuestDto guestDto);
}
