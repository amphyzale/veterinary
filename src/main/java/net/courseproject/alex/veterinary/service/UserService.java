package net.courseproject.alex.veterinary.service;

import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.dto.GuestDto;

import java.util.List;

public interface UserService {
    User register(GuestDto guestDto);
    List<User> getAll();
    User findByName(String name);
    User findById(String id);
    void delete(String id);

    boolean save(User user);
}
