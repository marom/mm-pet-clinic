package com.marom.mmpetclinic.repositories;

import com.marom.mmpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
