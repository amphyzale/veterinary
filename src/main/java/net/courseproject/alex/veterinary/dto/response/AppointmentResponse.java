package net.courseproject.alex.veterinary.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import net.courseproject.alex.veterinary.domain.Status;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class AppointmentResponse {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;
    private ServiceResponse service;
    private UserResponse user;
    private DoctorResponse doctor;
    private PetResponse pet;
    private Status status;
}
