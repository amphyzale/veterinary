package net.courseproject.alex.veterinary.manager.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.manager.IUserManager;
import net.courseproject.alex.veterinary.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserManagerImpl implements IUserManager {

    private final UserRepository userRepository;

    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }
}
