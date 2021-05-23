package net.courseproject.alex.veterinary.manager.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.domain.Gender;
import net.courseproject.alex.veterinary.domain.Pet;
import net.courseproject.alex.veterinary.domain.Status;
import net.courseproject.alex.veterinary.domain.User;
import net.courseproject.alex.veterinary.dto.response.PetResponse;
import net.courseproject.alex.veterinary.dto.request.PetRequest;
import net.courseproject.alex.veterinary.dto.transformer.impl.PetTransformerProvider;
import net.courseproject.alex.veterinary.manager.IAuthenticationManager;
import net.courseproject.alex.veterinary.manager.IPetManager;
import net.courseproject.alex.veterinary.manager.IUserManager;
import net.courseproject.alex.veterinary.repository.PetRepository;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PetManagerImpl implements IPetManager {

    private final PetRepository petRepository;
    private final PetTransformerProvider transformer;
    private final IAuthenticationManager authenticationManager;

    @Override
    public PetResponse addPet(PetRequest petRequest) {
        User user = authenticationManager.getUser(SecurityContextHolder.getContext());
        Pet pet = transformer.transformToDomain(petRequest);
        pet.setStatus(Status.ACTIVE);
        pet.setCreated(LocalDateTime.now());
        pet.setUpdated(LocalDateTime.now());
        pet.setOwner(user);
        return transformer.transformDomainTo(petRepository.save(pet));
    }

    @Override
    public List<PetResponse> getPets() {
        if (authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            return petRepository.findAll().stream()
                    .map(transformer::transformDomainTo)
                    .collect(Collectors.toList());
        }
        User user = authenticationManager.getUser(SecurityContextHolder.getContext());
        return petRepository.findAllByOwner(user).stream()
                .map(transformer::transformDomainTo)
                .collect(Collectors.toList());
    }

    @Override
    public List<PetResponse> getPetsByUserId(Long id) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        return petRepository.findAllByOwnerId(id).stream()
                .map(transformer::transformDomainTo)
                .collect(Collectors.toList());
    }

    @Override
    public PetResponse updatePetInfo(PetRequest petRequest, Long id) {
        Pet pet = petRepository.getOne(id);
        updatePetInfo(petRequest, pet);
        return transformer.transformDomainTo(petRepository.save(pet));
    }

    @Override
    public PetResponse updatePetInfoById(PetRequest petRequest, Long id) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        return updatePetInfo(petRequest, id);
    }

    @Override
    public void deletePet(Long id) {
        petRepository.delete(petRepository.getOne(id));
    }

    @Override
    public void deletePetById(Long id) {
        if (!authenticationManager.hasAdministratorRole(SecurityContextHolder.getContext())) {
            throw new AuthorizationServiceException("Authorization error");
        }
        deletePet(id);
    }

    private void updatePetInfo(PetRequest petRequest, Pet pet) {
        pet.setNickname(petRequest.getNickname());
        pet.setKind(petRequest.getKind());
        pet.setBreed(petRequest.getBreed());
        pet.setGender(Gender.fromValue(petRequest.getGender().name()));
        pet.setPetPic(petRequest.getPetPic());
        pet.setBirthDate(petRequest.getBirthDate());
        pet.setUpdated(LocalDateTime.now());
    }
}
