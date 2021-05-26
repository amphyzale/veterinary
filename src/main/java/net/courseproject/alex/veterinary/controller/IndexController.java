package net.courseproject.alex.veterinary.controller;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.response.AppointmentResponse;
import net.courseproject.alex.veterinary.dto.response.DoctorResponse;
import net.courseproject.alex.veterinary.dto.response.ServiceResponse;
import net.courseproject.alex.veterinary.manager.IAppointmentManager;
import net.courseproject.alex.veterinary.service.IAppointmentService;
import net.courseproject.alex.veterinary.service.IDoctorService;
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
    private final IDoctorService doctorService;
    private final IAppointmentService appointmentService;

    @GetMapping("/promo_services")
    public ResponseEntity<List<ServiceResponse>> getPromoServices() {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<ServiceResponse> response = veterinaryServiceService.getPromoServices();
        return responseEntity.body(response);
    }

    @GetMapping("/all_doctors")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<DoctorResponse> response = doctorService.getAllDoctors();
        return responseEntity.body(response);
    }

    @GetMapping("/all_services")
    public ResponseEntity<List<ServiceResponse>> getServices(){
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<ServiceResponse> response = veterinaryServiceService.getServices();
        return responseEntity.body(response);
    }

    @GetMapping("/all_appointments")
    public ResponseEntity<List<AppointmentResponse>> getAllAppoints() {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<AppointmentResponse> response = appointmentService.getAllAppoints();
        return responseEntity.body(response);
    }
}
