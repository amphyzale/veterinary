package net.courseproject.alex.veterinary.repository;


import net.courseproject.alex.veterinary.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    List<Role> findByNameIn(List<String> names);
}
