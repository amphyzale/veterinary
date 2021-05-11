package net.courseproject.alex.veterinary.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import net.courseproject.alex.veterinary.domain.Gender;
import net.courseproject.alex.veterinary.domain.Status;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserResponse {
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
    private List<String> roles;
    private String locale;
    private Gender gender;
}
