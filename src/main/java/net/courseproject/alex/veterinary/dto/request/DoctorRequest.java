package net.courseproject.alex.veterinary.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.experimental.Accessors;
import net.courseproject.alex.veterinary.domain.Status;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
public class DoctorRequest {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String userPic;
    private String email;
    private String phone;
    private String locale;
    @JsonDeserialize
    private Gender gender;
    private String specialization;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startOfPractice;
    private String description;
    private List<String> services;

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
