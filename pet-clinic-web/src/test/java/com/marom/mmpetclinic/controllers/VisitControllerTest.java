package com.marom.mmpetclinic.controllers;

import com.marom.mmpetclinic.model.Pet;
import com.marom.mmpetclinic.model.Visit;
import com.marom.mmpetclinic.services.PetService;
import com.marom.mmpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    private VisitService visitService;

    @Mock
    private PetService petService;

    @InjectMocks
    private VisitController visitController;

    MockMvc mockMvc;
    Visit visit;

    //@Mock
    //Model model;


    @BeforeEach
    void setUp() {

        visit = Visit.builder().id(1L).build();

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();

        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());
        //when(visitController.loadPetWithVisit(anyLong(), )).thenReturn(visit);
    }


    @Test
    public void initNewVisitForm() throws Exception {

        mockMvc.perform(get("/owners/*/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"));
    }

    @Test
    public void processNewVisitFormSuccess() throws Exception {

        mockMvc.perform(post("/owners/1/pets/1/visits/new")
                    .param("name", "Mruczek")
                    .param("description", "Visit Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
    }

    @Test
    public void processNewVisitFormError() throws Exception {

        mockMvc.perform(post("/owners/1/pets/1/visits/new")
                .param("name", "Kazio"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/{ownerId}"));
    }


}
