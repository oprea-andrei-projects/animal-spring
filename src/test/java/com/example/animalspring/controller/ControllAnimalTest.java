package com.example.animalspring.controller;

import com.example.animalspring.AnimalSpringApplication;
import com.example.animalspring.model.Animal;
import com.example.animalspring.repository.AnimalRepository;
import com.example.animalspring.service.ServiceAnimal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(
        locations="classpath:application-it.properties"
)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnimalSpringApplication.class)
@AutoConfigureMockMvc
class ControllAnimalTest {

    @MockBean
    private AnimalRepository animalRepository;

    @MockBean
    private ServiceAnimal serviceAnimal;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllTheAnimals() throws Exception {

        Animal a1 = new Animal("animal1",10);
        a1.setId(1L);
        Animal a2 = new Animal("animal2",20);
        a2.setId(2L);
        List<Animal> animals = new ArrayList<>();
        animals.add(a1);
        animals.add(a2);
        when(serviceAnimal.getAllAnimals()).thenReturn(animals);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/animal/allAnimals")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(animals)));


    }

    @Test
    void addTheAnimal() throws Exception {

        Animal a1 = new Animal("animal1",10);
        a1.setId(1L);
        animalRepository.save(a1);
        when(serviceAnimal.addTheAnimal(a1)).thenReturn(a1);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/animal/addAnimal")
               .contentType(MediaType.APPLICATION_JSON)
               .content(String.valueOf(asJsonString(a1))))
               .andExpect(status().isCreated());

    }

    @Test
    void deleteAnimal() throws Exception {
        Animal a1 = new Animal("animal1",10);
        a1.setId(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/animal/deleteAnim/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void updateAnimal() throws Exception {

        Animal a = new Animal("animal1",10);
        a.setId(1L);
        Animal a1 = new Animal();
        a1.setId(a.getId());
        when(serviceAnimal.updateAnimal(a)).thenReturn(a1);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/animal/updateAnimal")
                .content(String.valueOf(asJsonString(a1)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(a1)));



    }



    public static String asJsonString(final Object obj) throws JsonProcessingException {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}