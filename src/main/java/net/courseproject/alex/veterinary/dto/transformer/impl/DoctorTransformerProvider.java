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
public class DoctorTransformerProvider {

    public DoctorDto transformDomainToDto(Doctor domain) {
        return new DoctorDto()
                .setFirstName(domain.getFirstName())
                .setLastName(domain.getLastName())
                .setPatronymic(domain.getPatronymic())
                .setEmail(domain.getEmail())
                .setPhone(domain.getPhone())
                .setUserPic(domain.getUserPic())
                .setRoles(domain.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toList())
                )
                .setLastVisit(domain.getLastVisit())
                .setStatus(domain.getStatus())
                .setFio(domain.getFio())
                .setLocale(domain.getLocale())
                .setGender(domain.getGender())
                .setDescription(domain.getDescription())
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
}
