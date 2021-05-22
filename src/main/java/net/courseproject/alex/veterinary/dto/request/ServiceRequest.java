package net.courseproject.alex.veterinary.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ServiceRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer duration;
    private String image;
    private boolean isPromo;
}
