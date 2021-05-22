package net.courseproject.alex.veterinary.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import net.courseproject.alex.veterinary.domain.Gender;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class PetResponse {
    private Long id;
    private String nickname;
    private String petPic;
    private String kind;
    private String breed;
    private Gender gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private UserResponse owner;
}
