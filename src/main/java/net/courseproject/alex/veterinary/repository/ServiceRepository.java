package net.courseproject.alex.veterinary.repository;

import net.courseproject.alex.veterinary.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findByIsPromoTrue();
    List<Service> findAllByNameIn(List<String> names);
}
