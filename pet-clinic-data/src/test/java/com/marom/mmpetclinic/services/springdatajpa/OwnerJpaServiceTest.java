package com.marom.mmpetclinic.services.springdatajpa;

import com.marom.mmpetclinic.model.Owner;
import com.marom.mmpetclinic.repositories.OwnerRepository;
import com.marom.mmpetclinic.repositories.PetRepository;
import com.marom.mmpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerJpaService ownerJpaService;

    Owner smith;

    @BeforeEach
    void setUp() {
        smith = Owner.builder().id(1L).lastName("Smith").build();
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(any())).thenReturn(smith);

        assertEquals(smith, ownerJpaService.findByLastName(smith.getLastName()));
    }

    @Test
    void findAll() {

        Set<Owner> owners = new HashSet<>();
        Owner owner1 = Owner.builder().id(1L).build();
        Owner owner2 = Owner.builder().id(2L).build();
        owners.add(owner1);
        owners.add(owner2);

        when(ownerRepository.findAll()).thenReturn(owners);

        assertEquals(2, ownerJpaService.findAll().size());
    }

    @Test
    void findById() {

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(smith));

        assertEquals(smith, ownerJpaService.findById(smith.getId()));
    }

    @Test
    void findByIdNotFound() {

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertNull(ownerJpaService.findById(smith.getId()));
    }

    @Test
    void save() {

        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(ownerToSave);

        assertNotNull(ownerJpaService.save(ownerToSave));
    }

    @Test
    void delete() {

        ownerJpaService.delete(any());
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {

        ownerRepository.deleteById(anyLong());
        verify(ownerRepository).deleteById(anyLong());
    }
}
