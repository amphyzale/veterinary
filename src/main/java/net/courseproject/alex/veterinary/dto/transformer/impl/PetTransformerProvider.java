package net.courseproject.alex.veterinary.dto.transformer.impl;

import lombok.RequiredArgsConstructor;
import net.courseproject.alex.veterinary.domain.Gender;
import net.courseproject.alex.veterinary.domain.Pet;
import net.courseproject.alex.veterinary.dto.request.PetRequest;
import net.courseproject.alex.veterinary.dto.response.PetResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PetTransformerProvider {

    private final UserResponseTransformerProvider transformer;

    public PetResponse transformDomainTo(Pet domain) {
        return new PetResponse()
                .setId(domain.getId())
                .setNickname(domain.getNickname())
                .setKind(domain.getKind())
                .setBreed(domain.getBreed())
                .setGender(domain.getGender())
                .setPetPic(domain.getPetPic())
                .setBirthDate(domain.getBirthDate())
                .setOwner(transformer.transformToResponse(domain.getOwner()));
    }

    public Pet transformToDomain(PetRequest petRequest) {
        Pet pet = new Pet();
        pet.setNickname(petRequest.getNickname());
        pet.setKind(petRequest.getKind());
        pet.setBreed(petRequest.getBreed());
        pet.setGender(Gender.fromValue(petRequest.getGender().name()));
        pet.setPetPic(petRequest.getPetPic());
        pet.setBirthDate(petRequest.getBirthDate());
        return pet;
    }
}
