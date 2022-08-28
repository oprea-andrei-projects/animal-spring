package com.example.animalspring.service;

import com.example.animalspring.model.Animal;
import com.example.animalspring.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;


@TestPropertySource(
        locations = "classpath:application-it.properties"
)
class ServiceAnimalTest {

    @Mock
    private AnimalRepository animalRepository;

    @Captor
    private ArgumentCaptor<Animal>animalArgumentCaptor;

    @InjectMocks
    private ServiceAnimal underTest;


   @BeforeEach
    void setUp(){
       MockitoAnnotations.openMocks(this);
       underTest = new ServiceAnimal(animalRepository);
   }

   @Test
    void getAllAnimalsOK(){
        Animal a1 = new Animal("animal1",10);
        Animal a2 = new Animal("animal2",20);

       List<Animal> animals = new ArrayList<>();
       animals.add(a1);
       animals.add(a2);

      when(animalRepository.findAll()).thenReturn(animals);
      assertEquals(underTest.getAllAnimals().size(),2);

   }

   @Test
    void getAllAnimalsNOK(){


       List<Animal> animals = new ArrayList<>();

       given(animalRepository.findAll()).willReturn(animals);

       assertThatThrownBy(()->underTest.getAllAnimals()).hasMessageContaining("There are no animals !!!");

   }

   @Test
    void addAnimalOK(){

       Animal a1 = new Animal("animal1",10);
       List<Animal> animals = new ArrayList<>();
       animals.add(a1);
       when(animalRepository.getAnimalByName(a1.getName())).thenReturn(Optional.empty());
       when(animalRepository.findAll()).thenReturn(animals);
       assertEquals(underTest.addTheAnimal(a1),a1);

   }

   @Test
    void addAnimalNOK(){

       Animal a1 = new Animal("animal1",10);
        given(this.animalRepository.getAnimalByName(a1.getName())).willReturn(Optional.of(a1));

        assertThatThrownBy(()->underTest.addTheAnimal(a1)).hasMessageContaining("Acest animal exista deja !!!");

   }

   @Test
    void deleteAnimalOK(){
       Animal a1 = new Animal("animal1",10);
       a1.setId(2L);
       given(this.animalRepository.findById(2L)).willReturn(Optional.of(a1));
        underTest.deleteAnimal(2L);
        then(animalRepository).should().delete(animalArgumentCaptor.capture());
        Animal deletedAnimal = animalArgumentCaptor.getValue();

        assertThat(deletedAnimal).isEqualTo(a1);

   }

   @Test
    void deleteAnimalNOK(){

       Animal a1 = new Animal("animal1",10);
       a1.setId(2L);
       given(this.animalRepository.findById(2L)).willReturn(Optional.empty());

       assertThatThrownBy(()->underTest.deleteAnimal(2L)).hasMessageContaining("Acest animal nu exista !!!");

   }

   @Test
    void updateAnimal(){

       Animal a1 = new Animal("animal1",10);
       a1.setId(2L);
       Animal a2 = new Animal("animal2",15);
       a2.setId(2L);
       given(this.animalRepository.findById(a2.getId())).willReturn(Optional.of(a1));
       underTest.updateAnimal(a2);
       then(animalRepository).should().save(a1);

       assertThat(a1.getNo()).isEqualTo(15);


   }

   @Test
    void orderAtoZOK(){

       Animal a1 = new Animal("trout",10);
       a1.setId(1L);
       Animal a2 = new Animal("bear",20);
        a2.setId(2L);


      List<Animal> animals = new ArrayList<>();
        animals.add(a1);
        animals.add(a2);
       given(this.animalRepository.findAll()).willReturn(animals);

       underTest.orderAtoZ();

        then(animalRepository).should().orderAlphabetically();

       assertThat(animals.get(0).getName()).isEqualTo(a2.getName());


   }




}