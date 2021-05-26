package net.courseproject.alex.veterinary.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;
import net.courseproject.alex.veterinary.domain.Doctor;
import net.courseproject.alex.veterinary.dto.DoctorDto;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class ServiceResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer duration;
    private String image;
    private List<DoctorDto> doctors;
}
