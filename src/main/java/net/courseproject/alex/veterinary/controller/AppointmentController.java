package net.courseproject.alex.veterinary.controller;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.request.AppointmentRequest;
import net.courseproject.alex.veterinary.dto.response.AppointmentResponse;
import net.courseproject.alex.veterinary.service.IAppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/veterinary/v1/appointment"))
@RequiredArgsConstructor
public class AppointmentController {

    private final IAppointmentService appointmentService;

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentResponse>> getAllAppoints() {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<AppointmentResponse> response = appointmentService.getAllAppoints();
        return responseEntity.body(response);
    }

    @GetMapping("/by_user")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointsByUserId(@RequestParam Long id) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<AppointmentResponse> response = appointmentService.getAllAppointsByUserId(id);
        return responseEntity.body(response);
    }

    @GetMapping("/by_doctor")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointsByDoctorId(@RequestParam Long id) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<AppointmentResponse> response = appointmentService.getAllAppointsByDoctorId(id);
        return responseEntity.body(response);
    }

    @GetMapping("/by_pet")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointsByPetId(@RequestParam Long id) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<AppointmentResponse> response = appointmentService.getAllAppointsByPetId(id);
        return responseEntity.body(response);
    }

    @PutMapping("/get_appoint")
    public ResponseEntity<AppointmentResponse> getAppoint(@RequestBody AppointmentRequest appointmentRequest) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        AppointmentResponse response = appointmentService.getAppoint(appointmentRequest);
        return responseEntity.body(response);
    }

    @PatchMapping("/change_appoint_status")
    public ResponseEntity<AppointmentResponse> changeAppointStatus(@RequestParam Long appointId, @RequestParam String status) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        AppointmentResponse response = appointmentService.changeAppointStatus(appointId, status);
        return responseEntity.body(response);
    }

    @DeleteMapping("delete_appoint")
    public ResponseEntity<String> deleteAppoint(@RequestParam Long id) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        appointmentService.deleteAppoint(id);
        return responseEntity.body("deleted");
    }

}
