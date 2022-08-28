package com.example.animalspring.service;


import com.example.animalspring.exceptii.AnimaleDomestice;
import com.example.animalspring.exceptii.MissingAnimalException;
import com.example.animalspring.model.Animal;
import com.example.animalspring.repository.AnimalRepository;
import com.example.animalspring.repository.TipAnimalRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAnimal {

    private AnimalRepository animalRepository;


    public ServiceAnimal(AnimalRepository animalRepository){

        this.animalRepository = animalRepository;

    }

    public List<Animal> getAllAnimals(){

        List<Animal> animals = animalRepository.findAll();

        if(animals.size()==0){

            throw new MissingAnimalException("There are no animals !!!");
        }

      return animals;
    }

    public Animal addTheAnimal(Animal animal){

        Optional<Animal> optionalS = this.animalRepository.getAnimalByName(animal.getName());

        if(!optionalS.isEmpty()){

            throw new AnimaleDomestice("Acest animal exista deja !!!");
        }

        this.animalRepository.save(animal);

        return animal;



    }

    public void deleteAnimal(Long id){

        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if(animalOptional.isEmpty()){

            throw new MissingAnimalException("Acest animal nu exista !!!");
        }

        this.animalRepository.delete(animalOptional.get());

    }

    public Animal updateAnimal(Animal animal){

        Animal animal1 = this.animalRepository.findById(animal.getId()).get();
        animal1.setName(animal.getName());
        animal1.setNo((animal.getNo()));
        this.animalRepository.save(animal1);

        return animal1;

    }

    public List<Animal> orderAtoZ(){

       return this.animalRepository.orderAlphabetically();
    }
}
