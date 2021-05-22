package net.courseproject.alex.veterinary.service;

import net.courseproject.alex.veterinary.dto.response.PetResponse;
import net.courseproject.alex.veterinary.dto.request.PetRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IPetService {
    PetResponse addPet(PetRequest petRequest);
    List<PetResponse> getPets();
    List<PetResponse> getPetsByUserId(Long id);
    PetResponse updatePetInfo(PetRequest petRequest, Long id);
    PetResponse updatePetInfoById(PetRequest petRequest, Long id);
    void deletePet(Long id);
    void deletePetById(Long id);
}
