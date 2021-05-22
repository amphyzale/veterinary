package net.courseproject.alex.veterinary.manager;

import net.courseproject.alex.veterinary.dto.response.PetResponse;
import net.courseproject.alex.veterinary.dto.request.PetRequest;

import java.util.List;

public interface IPetManager {
    PetResponse addPet(PetRequest petRequest);
    List<PetResponse> getPets();
    List<PetResponse> getPetsByUserId(Long id);
    PetResponse updatePetInfo(PetRequest petRequest, Long id);
    PetResponse updatePetInfoById(PetRequest petRequest, Long id);
    void deletePet(Long id);
    void deletePetById(Long id);
}
