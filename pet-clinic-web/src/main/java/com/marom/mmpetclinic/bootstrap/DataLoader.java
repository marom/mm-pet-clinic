package com.marom.mmpetclinic.bootstrap;

import com.marom.mmpetclinic.model.*;
import com.marom.mmpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }

    }

    private void loadData() {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);


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

        Visit catVisit = new Visit();
        catVisit.setPet(kaziksPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        Vet vet1 = new Vet();
        vet1.setFirstName("Janek");
        vet1.setLastName("Wiewiora");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Bogdan");
        vet2.setLastName("Kozihelper");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("All data are loaded.....");
    }
}
