package net.courseproject.alex.veterinary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class LoginResponse {
    private String username;
    private String token;
    private List<String> roles;
}
