package net.courseproject.alex.veterinary.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.courseproject.alex.veterinary.domain.Gender;
import net.courseproject.alex.veterinary.domain.Service;
import net.courseproject.alex.veterinary.domain.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DoctorDto extends AbstractDto {
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
    private List<String> roles;
    private String locale;
    private Gender gender;
    private String specialization;
    private LocalDate startOfPractice;
    private String description;
    private List<ServiceDto> services;
}
