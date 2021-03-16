package net.courseproject.alex.veterinary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import net.courseproject.alex.veterinary.dto.UserDto;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class UserRegisterResponse {
    private UserDto user;
}
