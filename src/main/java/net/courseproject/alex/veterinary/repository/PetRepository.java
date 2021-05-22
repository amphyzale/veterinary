package net.courseproject.alex.veterinary.repository;

import net.courseproject.alex.veterinary.domain.Pet;
import net.courseproject.alex.veterinary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findAllByOwner(User owner);

    List<Pet> findAllByOwnerId(Long id);

}
