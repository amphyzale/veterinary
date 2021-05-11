package net.courseproject.alex.veterinary.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class LoginResponse {
    private String username;
    private String token;
    private List<String> roles;
}
