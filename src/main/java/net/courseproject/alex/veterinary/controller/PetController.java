package net.courseproject.alex.veterinary.controller;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.response.PetResponse;
import net.courseproject.alex.veterinary.dto.request.PetRequest;
import net.courseproject.alex.veterinary.service.IPetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veterinary/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final IPetService petService;

    @PutMapping("/add")
    public ResponseEntity<PetResponse> addPet(@RequestBody PetRequest petRequest) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        PetResponse response = petService.addPet(petRequest);
        return responseEntity.body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PetResponse>> getPets(){
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<PetResponse> response = petService.getPets();
        return responseEntity.body(response);
    }

    @GetMapping("/user_pets")
    public ResponseEntity<List<PetResponse>> getPetsByUserId(@RequestParam Long id){
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        List<PetResponse> response = petService.getPetsByUserId(id);
        return responseEntity.body(response);
    }

    @PatchMapping("/update_pet")
    public ResponseEntity<PetResponse> updatePet(@RequestBody PetRequest petRequest, @RequestParam Long id){
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        PetResponse response = petService.updatePetInfo(petRequest, id);
        return responseEntity.body(response);
    }

    @PatchMapping("/update_user_pet")
    public ResponseEntity<PetResponse> updatePetById(@RequestBody PetRequest petRequest, @RequestParam Long id){
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        PetResponse response = petService.updatePetInfoById(petRequest, id);
        return responseEntity.body(response);
    }

    @DeleteMapping("/delete_pet")
    public ResponseEntity<String> deletePet(@RequestParam Long id) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        petService.deletePet(id);
        return responseEntity.body("deleted");
    }

    @DeleteMapping("/delete_user_pet")
    public ResponseEntity<String> deletePetById(@RequestParam Long id) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.status(200);
        petService.deletePetById(id);
        return responseEntity.body("deleted");
    }

}
