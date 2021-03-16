package net.courseproject.alex.veterinary.repository;

import net.courseproject.alex.veterinary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String name);
}
