package com.marom.mmpetclinic.bootstrap;

import com.marom.mmpetclinic.model.Owner;
import com.marom.mmpetclinic.model.Pet;
import com.marom.mmpetclinic.model.PetType;
import com.marom.mmpetclinic.model.Vet;
import com.marom.mmpetclinic.services.OwnerService;
import com.marom.mmpetclinic.services.PetTypeService;
import com.marom.mmpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Maro");
        owner1.setLastName("Kowalski");
        owner1.setAddress("2 Main Street");
        owner1.setCity("London");
        owner1.setTelephone("1234567890");

        Pet marosPet = new Pet();
        marosPet.setName("Gustav");
        marosPet.setOwner(owner1);
        marosPet.setPetType(savedDogPetType);
        marosPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(marosPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Kazimierz");
        owner2.setLastName("Burczymucha");
        owner2.setAddress("12B Some Avenue");
        owner2.setCity("Romford");
        owner2.setTelephone("234543234");

        Pet kaziksPet = new Pet();
        kaziksPet.setName("Burczus");
        kaziksPet.setOwner(owner2);
        kaziksPet.setPetType(savedCatPetType);
        kaziksPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(kaziksPet);
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Janek");
        vet1.setLastName("Wiewiora");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Bogdan");
        vet2.setLastName("Kozihelper");
        vetService.save(vet2);

        System.out.println("All data are loaded.....");

    }
}
