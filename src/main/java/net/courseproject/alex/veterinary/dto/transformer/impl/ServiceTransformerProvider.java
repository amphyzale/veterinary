package net.courseproject.alex.veterinary.dto.transformer.impl;

import net.courseproject.alex.veterinary.domain.Doctor;
import net.courseproject.alex.veterinary.domain.Service;
import net.courseproject.alex.veterinary.dto.DoctorDto;
import net.courseproject.alex.veterinary.dto.ServiceDto;
import net.courseproject.alex.veterinary.dto.request.ServiceRequest;
import net.courseproject.alex.veterinary.dto.response.ServiceResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceTransformerProvider {

    public ServiceResponse transformDomainTo(Service domain) {
        return new ServiceResponse()
                .setId(domain.getId())
                .setName(domain.getName())
                .setDescription(domain.getDescription())
                .setDuration(domain.getDuration())
                .setPrice(domain.getPrice())
                .setImage(domain.getImage())
                .setDoctors(getDoctorsFromDomain(domain.getDoctors()));
    }

    private List<DoctorDto> getDoctorsFromDomain(List<Doctor> doctors) {
        if (CollectionUtils.isEmpty(doctors)) {
            return Collections.emptyList();
        }
        return doctors.stream().map(doctor -> new DoctorDto()
                    .setFirstName(doctor.getFirstName())
                    .setLastName(doctor.getLastName())
                    .setPatronymic(doctor.getPatronymic())
                    .setEmail(doctor.getEmail())
                    .setPhone(doctor.getPhone())
                    .setUserPic(doctor.getUserPic())
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

    public Service transformToDomain(ServiceRequest serviceRequest) {
        Service service = new Service();
        service.setName(serviceRequest.getName());
        service.setDescription(serviceRequest.getDescription());
        service.setPrice(serviceRequest.getPrice());
        service.setImage(serviceRequest.getImage());
        service.setDuration(serviceRequest.getDuration());
        service.setPromo(service.isPromo());
        return service;
    }
}
