package net.courseproject.alex.veterinary.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import net.courseproject.alex.veterinary.domain.Gender;
import net.courseproject.alex.veterinary.domain.Role;
import net.courseproject.alex.veterinary.domain.Status;
import net.courseproject.alex.veterinary.dto.ServiceDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class DoctorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String fio;
    private String userPic;
    private String email;
    private Status status;
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;
    private String locale;
    private Gender gender;
    private String specialization;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startOfPractice;
    private String description;
    private String role;
    private List<ServiceDto> services;
}
