package net.courseproject.alex.veterinary.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class PetRequest {
    private String nickname;
    private String petPic;
    private String kind;
    private String breed;
    private Gender gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

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
