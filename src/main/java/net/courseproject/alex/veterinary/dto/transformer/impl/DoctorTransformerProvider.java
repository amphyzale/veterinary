package net.courseproject.alex.veterinary.dto.transformer.impl;

import net.courseproject.alex.veterinary.domain.Doctor;
import net.courseproject.alex.veterinary.domain.Gender;
import net.courseproject.alex.veterinary.domain.Service;
import net.courseproject.alex.veterinary.domain.Status;
import net.courseproject.alex.veterinary.dto.ServiceDto;
import net.courseproject.alex.veterinary.dto.request.DoctorRegisterRequest;
import net.courseproject.alex.veterinary.dto.request.DoctorRequest;
import net.courseproject.alex.veterinary.dto.response.DoctorResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorTransformerProvider {

    public DoctorResponse transformDomainTo(Doctor domain) {
        return new DoctorResponse()
                .setFirstName(domain.getFirstName())
                .setLastName(domain.getLastName())
                .setPatronymic(domain.getPatronymic())
                .setEmail(domain.getEmail())
                .setPhone(domain.getPhone())
                .setUserPic(domain.getUserPic())
                .setLastVisit(domain.getLastVisit())
                .setStatus(domain.getStatus())
                .setFio(domain.getFio())
                .setLocale(domain.getLocale())
                .setGender(domain.getGender())
                .setDescription(domain.getDescription())
                .setStartOfPractice(domain.getStartOfPractice())
                .setSpecialization(domain.getSpecialization())
                .setServices(getServicesFromDomain(domain.getServices()));
    }

    private List<ServiceDto> getServicesFromDomain(List<Service> services) {
        return services.stream().map(service -> new ServiceDto()
                    .setName(service.getName())
                    .setDescription(service.getDescription())
                    .setDuration(service.getDuration())
                    .setPrice(service.getPrice()))
                .collect(Collectors.toList());
    }

    public Doctor transformToDomain(DoctorRegisterRequest doctorRequest) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setPatronymic(doctorRequest.getPatronymic());
        doctor.setFio(buildFIO(doctorRequest));
        doctor.setEmail(doctorRequest.getEmail());
        doctor.setGender(Gender.valueOf(doctorRequest.getGender().name()));
        doctor.setLocale(doctorRequest.getLocale());
        doctor.setPhone(doctorRequest.getPhone());
        doctor.setUserPic(doctorRequest.getUserPic());
        doctor.setDescription(doctorRequest.getDescription());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setStartOfPractice(doctorRequest.getStartOfPractice());
        doctor.setUsername(doctorRequest.getEmail());
        doctor.setStatus(Status.ACTIVE);
        doctor.setCreated(LocalDateTime.now());
        doctor.setLastVisit(LocalDateTime.now());
        doctor.setUpdated(LocalDateTime.now());
        return doctor;
    }

    public Doctor transformToDomain(DoctorRequest doctorRequest) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setPatronymic(doctorRequest.getPatronymic());
        doctor.setFio(buildFIO(doctorRequest));
        doctor.setEmail(doctorRequest.getEmail());
        doctor.setGender(Gender.valueOf(doctorRequest.getGender().name()));
        doctor.setLocale(doctorRequest.getLocale());
        doctor.setPhone(doctorRequest.getPhone());
        doctor.setUserPic(doctorRequest.getUserPic());
        doctor.setDescription(doctorRequest.getDescription());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setStartOfPractice(doctorRequest.getStartOfPractice());
        doctor.setUsername(doctorRequest.getEmail());
        return doctor;
    }

    private String buildFIO(DoctorRequest request) {
        return String.format("%s %s %s", request.getFirstName(), request.getLastName(), request.getPatronymic());
    }
}
