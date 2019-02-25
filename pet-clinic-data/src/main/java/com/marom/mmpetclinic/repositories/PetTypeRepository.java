package com.marom.mmpetclinic.repositories;

import com.marom.mmpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
