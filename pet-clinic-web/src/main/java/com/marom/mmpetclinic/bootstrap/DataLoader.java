package com.marom.mmpetclinic.bootstrap;

import com.marom.mmpetclinic.services.OwnerService;
import com.marom.mmpetclinic.services.VetService;
import com.marom.mmpetclinic.services.map.OwnerServiceMap;
import org.springframework.boot.CommandLineRunner;

public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
