package net.courseproject.alex.veterinary.repository;

import net.courseproject.alex.veterinary.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByUserId(Long userId);
    List<Appointment> findByDoctorId(Long userId);
    List<Appointment> findByServiceId(Long userId);
    List<Appointment> findByPetId(Long userId);
}
