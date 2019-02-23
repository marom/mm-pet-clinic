package com.marom.mmpetclinic.bootstrap;

import com.marom.mmpetclinic.model.Owner;
import com.marom.mmpetclinic.model.PetType;
import com.marom.mmpetclinic.model.Vet;
import com.marom.mmpetclinic.services.OwnerService;
import com.marom.mmpetclinic.services.PetTypeService;
import com.marom.mmpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Kazimierz");
        owner2.setLastName("Burczymucha");
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
