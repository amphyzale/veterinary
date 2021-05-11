package net.courseproject.alex.veterinary.repository;

import net.courseproject.alex.veterinary.domain.Status;
import net.courseproject.alex.veterinary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsernameAndStatus(String name, Status status);

    List<User> findUsersByIdOrEmailContainingOrFioContaining(Long id, String email, String fio);

    Optional<User> findById(Long id);
}
