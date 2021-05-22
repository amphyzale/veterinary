package net.courseproject.alex.veterinary.service.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.dto.response.PetResponse;
import net.courseproject.alex.veterinary.dto.request.PetRequest;
import net.courseproject.alex.veterinary.manager.IPetManager;
import net.courseproject.alex.veterinary.service.IPetService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PetServiceImpl implements IPetService {

    private final IPetManager petManager;

    @Override
    public PetResponse addPet(PetRequest petRequest) {
        return petManager.addPet(petRequest);
    }

    @Override
    public List<PetResponse> getPets() {
        return petManager.getPets();
    }

    @Override
    public List<PetResponse> getPetsByUserId(Long id) {
        return petManager.getPetsByUserId(id);
    }

    @Override
    public PetResponse updatePetInfo(PetRequest petRequest, Long id) {
        return petManager.updatePetInfo(petRequest, id);
    }

    @Override
    public PetResponse updatePetInfoById(PetRequest petRequest, Long id) {
        return petManager.updatePetInfoById(petRequest, id);
    }

    @Override
    public void deletePet(Long id) {
        petManager.deletePet(id);
    }

    @Override
    public void deletePetById(Long id) {
        petManager.deletePetById(id);
    }
}
