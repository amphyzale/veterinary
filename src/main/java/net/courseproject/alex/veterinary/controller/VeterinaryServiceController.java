package net.courseproject.alex.veterinary.controller;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.request.PetRequest;
import net.courseproject.alex.veterinary.dto.request.ServiceRequest;
import net.courseproject.alex.veterinary.dto.response.PetResponse;
import net.courseproject.alex.veterinary.dto.response.ServiceResponse;
import net.courseproject.alex.veterinary.service.IVeterinaryServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veterinary/v1/services")
@RequiredArgsConstructor
public class VeterinaryServiceController {

    private final IVeterinaryServiceService veterinaryServiceService;

    @PutMapping("/add")
    public ResponseEntity<ServiceResponse> addService(@RequestBody ServiceRequest serviceRequest) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        ServiceResponse response = veterinaryServiceService.addService(serviceRequest);
        return responseEntity.body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceResponse>> getPets(){
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<ServiceResponse> response = veterinaryServiceService.getServices();
        return responseEntity.body(response);
    }

    @GetMapping("/service")
    public ResponseEntity<ServiceResponse> getPetsByUserId(@RequestParam Long id){
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        ServiceResponse response = veterinaryServiceService.getServiceById(id);
        return responseEntity.body(response);
    }

    @PatchMapping("/update_service")
    public ResponseEntity<ServiceResponse> updatePet(@RequestBody ServiceRequest serviceRequest, @RequestParam Long id){
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        ServiceResponse response = veterinaryServiceService.updateService(serviceRequest, id);
        return responseEntity.body(response);
    }

    @DeleteMapping("/delete_service")
    public ResponseEntity<String> deletePet(@RequestParam Long id) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        veterinaryServiceService.deleteService(id);
        return responseEntity.body("deleted");
    }

}
