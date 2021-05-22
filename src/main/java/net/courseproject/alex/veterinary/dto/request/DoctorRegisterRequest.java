package net.courseproject.alex.veterinary.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DoctorRegisterRequest extends DoctorRequest {
    private String password;
}
