package net.courseproject.alex.veterinary.manager;

import net.courseproject.alex.veterinary.domain.User;

public interface IUserManager {
    User getUserByName(String name);
    User saveUser(User user);
}
