package net.courseproject.alex.veterinary.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationRequest {
    @NotBlank
    @JsonProperty("email")
    private String email;
    @NotBlank
    @JsonProperty("password")
    private String password;
}
