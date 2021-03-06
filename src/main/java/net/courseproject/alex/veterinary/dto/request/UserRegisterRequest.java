package net.courseproject.alex.veterinary.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterRequest {
    @NotBlank
    @JsonProperty("firstName")
    private String firstName;
    @NotBlank
    @JsonProperty("lastName")
    private String lastName;
    @NotBlank
    @JsonProperty("patronymic")
    private String patronymic;
    @NotBlank
    @JsonProperty("email")
    private String email;
    @NotBlank
    @JsonProperty("phone")
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phone;
    @NotBlank
    @JsonProperty("password")
    private String password;
    @JsonProperty("userPic")
    private String userPic;
    @NotBlank
    @JsonProperty("locale")
    private String locale;
    @NotNull
    @JsonDeserialize
    private Gender gender;
    private List<String> roles;
    private DoctorRequest doctorRequest;

    public enum Gender {
        MALE("MALE"),

        FEMALE("FEMALE"),

        IT("IT");

        private final String value;

        Gender(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static Gender fromValue(String text) {
            for (Gender b : Gender.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return Gender.IT;
        }
    }
}
