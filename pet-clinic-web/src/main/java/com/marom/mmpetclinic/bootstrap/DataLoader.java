package com.marom.mmpetclinic.bootstrap;

import com.marom.mmpetclinic.model.Owner;
import com.marom.mmpetclinic.model.Vet;
import com.marom.mmpetclinic.services.OwnerService;
import com.marom.mmpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Maro");
        owner1.setLastName("Kowalski");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Kazimierz");
        owner2.setLastName("Burczymucha");

        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Janek");
        vet1.setLastName("Wiewiora");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Bogdan");
        vet2.setLastName("Kozihelper");

        vetService.save(vet2);
        System.out.println("All data are loaded.....");

    }
}
