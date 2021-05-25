package net.courseproject.alex.veterinary.controller;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.request.DoctorRegisterRequest;
import net.courseproject.alex.veterinary.dto.request.DoctorRequest;
import net.courseproject.alex.veterinary.dto.response.DoctorResponse;
import net.courseproject.alex.veterinary.service.IDoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veterinary/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final IDoctorService doctorService;

    @GetMapping("/profile")
    public ResponseEntity<DoctorResponse> getProfile() {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        DoctorResponse response = doctorService.getProfile();
        return responseEntity.body(response);
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<DoctorResponse>> findDoctor(@RequestParam(name = "searchQuery", required = false, defaultValue = "0") String searchQuery) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<DoctorResponse> response = doctorService.findDoctor(searchQuery);
        return responseEntity.body(response);
    }

    @PatchMapping("/update_profile")
    public ResponseEntity<DoctorResponse> updateProfile(@RequestBody DoctorRequest doctorRequest) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        DoctorResponse response = doctorService.updateProfile(doctorRequest);
        return responseEntity.body(response);
    }

    @PatchMapping("/update_doctor")
    public ResponseEntity<DoctorResponse> updateDoctorById(
            @RequestParam Long id,
            @RequestBody DoctorRequest doctorRequest
    ) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        DoctorResponse response = doctorService.updateDoctorById(id, doctorRequest);
        return responseEntity.body(response);
    }

    @DeleteMapping("/delete_doctor")
    public ResponseEntity<String> deleteDoctorById(@RequestParam Long id) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        doctorService.deleteDoctorById(id);
        return responseEntity.body("deleted");
    }

    @PutMapping("/register")
    public ResponseEntity<DoctorResponse> register(@RequestBody DoctorRegisterRequest doctorRegisterRequest) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        DoctorResponse response = doctorService.register(doctorRegisterRequest);
        return responseEntity.body(response);
    }

}
