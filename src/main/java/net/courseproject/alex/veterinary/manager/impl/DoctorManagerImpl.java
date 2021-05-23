package net.courseproject.alex.veterinary.manager.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.domain.Doctor;
import net.courseproject.alex.veterinary.domain.Gender;
import net.courseproject.alex.veterinary.domain.Status;
import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.dto.request.DoctorRegisterRequest;
import net.courseproject.alex.veterinary.dto.request.DoctorRequest;
import net.courseproject.alex.veterinary.dto.request.UserRequest;
import net.courseproject.alex.veterinary.dto.response.DoctorResponse;
import net.courseproject.alex.veterinary.dto.transformer.impl.DoctorTransformerProvider;
import net.courseproject.alex.veterinary.exception.UserAlreadyExistsException;
import net.courseproject.alex.veterinary.manager.IAuthenticationManager;
import net.courseproject.alex.veterinary.manager.IDoctorManager;
import net.courseproject.alex.veterinary.repository.DoctorRepository;
import net.courseproject.alex.veterinary.repository.ServiceRepository;
import net.courseproject.alex.veterinary.security.jwt.JwtUser;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DoctorManagerImpl implements IDoctorManager {

    private final DoctorRepository doctorRepository;
    private final IAuthenticationManager authenticationManager;
    private final DoctorTransformerProvider transformer;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ServiceRepository serviceRepository;

    @Override
    public DoctorResponse register(DoctorRegisterRequest doctorRequest) {
        if (doctorRepository.findByEmailAndStatus(doctorRequest.getEmail(), Status.ACTIVE) != null) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        Doctor doctor = transformer.transformToDomain(doctorRequest);
        doctor.setPassword(passwordEncoder.encode(doctorRequest.getPassword()));
        doctor.setServices(serviceRepository.findAllByNameIn(doctorRequest.getServices()));
        return transformer.transformDomainTo(doctorRepository.save(doctor));
    }

    @Override
    public List<DoctorResponse> getAllDoctors() {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        return doctorRepository.findAll()
                .stream()
                .map(transformer::transformDomainTo)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorResponse getProfile() {
        SecurityContext context = SecurityContextHolder.getContext();
        Long id = ((JwtUser) context.getAuthentication().getPrincipal()).getId();
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return transformer.transformDomainTo(doctor.orElseThrow(UnknownError::new));
    }

    @Override
    public List<DoctorResponse> findDoctor(String searchQuery) {
        if (NumberUtils.isDigits(searchQuery)) {
            return Collections.singletonList(
                    transformer.transformDomainTo(
                            doctorRepository.findById(Long.parseLong(searchQuery)).orElseThrow(UnknownError::new))
            );
        }
        return doctorRepository.findAll()
                .stream()
                .filter(i -> doFilter(searchQuery, i))
                .map(transformer::transformDomainTo)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorResponse updateProfile(DoctorRequest doctorRequest) {
        SecurityContext context = SecurityContextHolder.getContext();
        Long id = ((JwtUser) context.getAuthentication().getPrincipal()).getId();
        Optional<Doctor> doctorOpt = doctorRepository.findById(id);
        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            updateProfile(doctor, doctorRequest);
            doctorRepository.save(doctor);
            return transformer.transformDomainTo(doctor);
        }
        throw new UnknownError();
    }

    @Override
    public DoctorResponse updateDoctorById(Long id, DoctorRequest doctorRequest) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        Optional<Doctor> doctorOpt = doctorRepository.findById(id);
        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            updateProfile(doctor, doctorRequest);
            doctorRepository.save(doctor);
            return transformer.transformDomainTo(doctor);
        }
        throw new UnknownError();
    }

    @Override
    public void deleteDoctorById(Long id) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        Optional<Doctor> doctorOpt = doctorRepository.findById(id);
        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            doctor.setStatus(Status.DELETED);
            doctorRepository.save(doctor);
        }
        throw new UnknownError();
    }

    private void updateProfile(Doctor doctor, DoctorRequest doctorRequest) {
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setPatronymic(doctorRequest.getPatronymic());
        doctor.setUserPic(doctorRequest.getUserPic());
        doctor.setEmail(doctorRequest.getEmail());
        doctor.setPhone(doctorRequest.getPhone());
        doctor.setLocale(doctorRequest.getLocale());
        doctor.setGender(Gender.fromValue(doctorRequest.getGender().name()));
        LocalDateTime now = LocalDateTime.now();
        doctor.setUpdated(now);
        doctor.setLastVisit(now);
        doctor.setFio(buildFIO(doctorRequest));
    }

    private String buildFIO(DoctorRequest doctorRequest) {
        return String.format("%s %s %s", doctorRequest.getFirstName(), doctorRequest.getLastName(), doctorRequest.getPatronymic());
    }

    private boolean doFilter(String searchQuery, Doctor doctor) {
        return doctor.getEmail() != null && doctor.getEmail().contains(searchQuery) || doctor.getFio() != null && doctor.getFio().contains(searchQuery);
    }
}
