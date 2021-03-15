package net.courseproject.alex.veterinary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.courseproject.alex.veterinary.domain.Gender;
import net.courseproject.alex.veterinary.domain.Status;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Accessors(chain = true)
public class UserDto extends AbstractDto {
    private String id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String fio;
    private String userPic;
    private String email;
    private Status status;
    private String phone;
    private LocalDateTime lastVisit;
    private List<String> roles;
    private String locale;
    private Gender gender;
}
