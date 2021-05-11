package net.courseproject.alex.veterinary.dto.response;

import lombok.Data;
import net.courseproject.alex.veterinary.dto.ServiceDto;

import java.util.List;

@Data
public class ServicesPromoResponse {
    private List<ServiceDto> serviceResponseList;
}
