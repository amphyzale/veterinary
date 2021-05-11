package net.courseproject.alex.veterinary.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;
import net.courseproject.alex.veterinary.domain.Gender;

@Data
@Accessors(chain = true)
public class UserRequest {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String userPic;
    private String email;
    private String phone;
    private String locale;
    private Gender gender;
}
