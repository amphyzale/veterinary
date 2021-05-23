package net.courseproject.alex.veterinary.manager.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.domain.*;
import net.courseproject.alex.veterinary.dto.request.AppointmentRequest;
import net.courseproject.alex.veterinary.dto.response.AppointmentResponse;
import net.courseproject.alex.veterinary.dto.transformer.impl.*;
import net.courseproject.alex.veterinary.manager.IAppointmentManager;
import net.courseproject.alex.veterinary.manager.IAuthenticationManager;
import net.courseproject.alex.veterinary.repository.*;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AppointmentManagerImpl implements IAppointmentManager {

    private final IAuthenticationManager authenticationManager;
    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final ServiceRepository serviceRepository;
    private final AppointmentTransformerProvider appointmentTransformer;

    @Override
    public AppointmentResponse getAppoint(AppointmentRequest appointmentRequest) {
        User user = authenticationManager.getUser(SecurityContextHolder.getContext());
        Appointment appointment = appointmentTransformer.transformToDomain(appointmentRequest);
        appointment.setCreated(LocalDateTime.now());
        appointment.setUser(user);
        appointment.setService(serviceRepository.getOne(appointmentRequest.getServiceId()));
        appointment.setDoctor(doctorRepository.getOne(appointmentRequest.getDoctorId()));
        appointment.setPet(petRepository.getOne(appointmentRequest.getPetId()));
        appointment.setEnd(appointment.getStart().plusHours(1));
        appointment.setStatus(Status.ACTIVE);
        return appointmentTransformer.transformDomainTo(appointmentRepository.save(appointment));
    }

    @Override
    public List<AppointmentResponse> getAllAppoints() {
        if (authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            return appointmentRepository.findAll().stream()
                    .map(appointmentTransformer::transformDomainTo)
                    .collect(Collectors.toList());
        }
        User user = authenticationManager.getUser(SecurityContextHolder.getContext());
        return appointmentRepository.findByUserId(user.getId()).stream()
                .map(appointmentTransformer::transformDomainTo)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getAllAppointsByUserId(Long userId) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        User user = userRepository.findById(userId).orElseThrow(UnknownError::new);
        return appointmentRepository.findByUserId(user.getId()).stream()
                .map(appointmentTransformer::transformDomainTo)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getAllAppointsByDoctorId(Long doctorId) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        Doctor doctor = doctorRepository.getOne(doctorId);
        return appointmentRepository.findByDoctorId(doctor.getId()).stream()
                .map(appointmentTransformer::transformDomainTo)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getAllAppointsByPetId(Long petId) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        Pet pet = petRepository.getOne(petId);
        return appointmentRepository.findByPetId(pet.getId()).stream()
                .map(appointmentTransformer::transformDomainTo)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponse changeAppointStatus(Long appointId, String status) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        Appointment appointment = appointmentRepository.getOne(appointId);
        appointment.setStatus(Status.valueOf(status));
        return appointmentTransformer.transformDomainTo(appointment);
    }

    @Override
    public void deleteAppoint(Long id) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        Appointment appointment = appointmentRepository.getOne(id);
        appointment.setStatus(Status.DELETED);
        appointmentRepository.save(appointment);
    }
}
