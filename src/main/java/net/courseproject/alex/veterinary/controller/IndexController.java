package net.courseproject.alex.veterinary.controller;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.response.ServiceResponse;
import net.courseproject.alex.veterinary.service.IVeterinaryServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/veterinary/v1/index")
@RequiredArgsConstructor
public class IndexController {

    private final IVeterinaryServiceService veterinaryServiceService;

    @GetMapping("/promo_services")
    public ResponseEntity<List<ServiceResponse>> getPromoServices() {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<ServiceResponse> response = veterinaryServiceService.getPromoServices();
        return responseEntity.body(response);
    }
}
