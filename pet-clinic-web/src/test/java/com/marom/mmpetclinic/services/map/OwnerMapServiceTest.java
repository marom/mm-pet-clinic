package com.marom.mmpetclinic.services.map;

import com.marom.mmpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    Long ownerId = 1L;
    String lastName = "BobBuilder";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder().id(1L).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {

        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        Owner owner = ownerMapService.findById(ownerId);
        ownerMapService.delete(owner);
        assertNull(ownerMapService.findById(1L));
    }

    @Test
    void save() {
        Long owner2Id = 2L;
        ownerMapService.save(ownerMapService.save(Owner.builder().id(owner2Id).build()));
        assertEquals(owner2Id, ownerMapService.findById(owner2Id).getId());
    }

    @Test
    void findById() {
        assertEquals(ownerId, ownerMapService.findById(ownerId).getId());
    }

    @Test
    void findByLastNameExisting() {
        assertEquals(lastName, ownerMapService.findByLastName(lastName).getLastName());
    }

    @Test
    void findByLastNameNonExisting() {
        assertNull(ownerMapService.findByLastName("NonExisting"));
    }
}
