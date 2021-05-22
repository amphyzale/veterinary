package net.courseproject.alex.veterinary.repository;

import net.courseproject.alex.veterinary.domain.Doctor;
import net.courseproject.alex.veterinary.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByEmailAndStatus(String email, Status status);

    Doctor findByEmail(String email);

}
