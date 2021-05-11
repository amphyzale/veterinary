package net.courseproject.alex.veterinary.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceDto extends AbstractDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer duration;
    private List<DoctorDto> doctors;
}
