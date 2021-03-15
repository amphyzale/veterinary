package net.courseproject.alex.veterinary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import net.courseproject.alex.veterinary.domain.Role;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

@Data
@JsonComponent
@AllArgsConstructor
@Accessors(chain = true)
public class LoginResponse {
    private String username;
    private String token;
    private List<Role> roles;
}
