package net.courseproject.alex.veterinary.repository;

import net.courseproject.alex.veterinary.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
