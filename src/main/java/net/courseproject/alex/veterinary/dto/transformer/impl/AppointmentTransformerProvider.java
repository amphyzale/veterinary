package net.courseproject.alex.veterinary.dto.transformer.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.domain.Appointment;
import net.courseproject.alex.veterinary.dto.request.AppointmentRequest;
import net.courseproject.alex.veterinary.dto.response.AppointmentResponse;
import net.courseproject.alex.veterinary.dto.response.UserResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AppointmentTransformerProvider {

    private final PetTransformerProvider petTransformer;
    private final UserTransformerProvider userTransformer;
    private final DoctorTransformerProvider doctorTransformer;
    private final ServiceTransformerProvider serviceTransformer;

    public AppointmentResponse transformDomainTo(Appointment domain) {
        return new AppointmentResponse()
                .setId(domain.getId())
                .setStatus(domain.getStatus())
                .setStart(domain.getStart())
                .setEnd(domain.getEnd())
                .setDoctor(doctorTransformer.transformDomainTo(domain.getDoctor()))
                .setUser(new UserResponse()
                    .setId(domain.getUser().getId())
                    .setFio(domain.getUser().getFio())
                    .setFirstName(domain.getUser().getFirstName())
                    .setPatronymic(domain.getUser().getPatronymic())
                    .setUserPic(domain.getUser().getUserPic())
                )
                .setPet(petTransformer.transformDomainTo(domain.getPet()))
                .setService(serviceTransformer.transformDomainTo(domain.getService()));
    }

    public Appointment transformToDomain(AppointmentRequest request) {
        Appointment appointment = new Appointment();
        appointment.setStart(request.getStart());
        appointment.setUpdated(LocalDateTime.now());
        return appointment;
    }
}
