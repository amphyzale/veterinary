package net.courseproject.alex.veterinary.repository;

import net.courseproject.alex.veterinary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
