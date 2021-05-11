package net.courseproject.alex.veterinary.dto.transformer.impl;

import net.courseproject.alex.veterinary.domain.Doctor;
import net.courseproject.alex.veterinary.domain.Role;
import net.courseproject.alex.veterinary.domain.Service;
import net.courseproject.alex.veterinary.dto.DoctorDto;
import net.courseproject.alex.veterinary.dto.ServiceDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceTransformerProvider {

    public static ServiceDto transformDomainToDto(Service domain) {
        return new ServiceDto()
                .setName(domain.getName())
                .setDescription(domain.getDescription())
                .setDuration(domain.getDuration())
                .setPrice(domain.getPrice())
                .setDoctors(getDoctorsFromDomain(domain.getDoctors()));
    }

    private static List<DoctorDto> getDoctorsFromDomain(List<Doctor> doctors) {
        return doctors.stream().map(doctor -> new DoctorDto()
                    .setFirstName(doctor.getFirstName())
                    .setLastName(doctor.getLastName())
                    .setPatronymic(doctor.getPatronymic())
                    .setEmail(doctor.getEmail())
                    .setPhone(doctor.getPhone())
                    .setUserPic(doctor.getUserPic())
                    .setRoles(doctor.getRoles()
                            .stream()
                            .map(Role::getName)
                            .collect(Collectors.toList())
                    )
                    .setLastVisit(doctor.getLastVisit())
                    .setStatus(doctor.getStatus())
                    .setFio(doctor.getFio())
                    .setLocale(doctor.getLocale())
                    .setGender(doctor.getGender())
                    .setDescription(doctor.getDescription()))
                .collect(Collectors.toList());
    }

    public void transformDomainToDto(Service domain, ServiceDto dto) {
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());
        dto.setDuration(domain.getDuration());
        dto.setPrice(domain.getPrice());
    }

    public void transformDtoToDomain(ServiceDto dto, Service domain) {

    }
}
