package net.courseproject.alex.veterinary.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;
import net.courseproject.alex.veterinary.dto.UserDto;

@Data
@Accessors(chain = true)
public class UserRegisterResponse {
    private UserDto user;
}
