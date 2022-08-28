package com.example.animalspring.controller;


import com.example.animalspring.exceptii.AnimaleDomestice;
import com.example.animalspring.exceptii.MissingAnimalException;
import com.example.animalspring.exceptii.NumarMicAnimale;
import com.example.animalspring.exceptii.NumaruMareAnumale;
import com.example.animalspring.model.Animal;
import com.example.animalspring.repository.AnimalRepository;
import com.example.animalspring.repository.TipAnimalRepo;
import com.example.animalspring.service.ServiceAnimal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/animal")
public class ControllAnimal {

    private AnimalRepository animalRepository;

    private TipAnimalRepo tipAnimalRepo;

    private ServiceAnimal serviceAnimal;


    public ControllAnimal(AnimalRepository animalRepository, TipAnimalRepo tipAnimalRepo, ServiceAnimal serviceAnimal) {

        this.animalRepository = animalRepository;

        this.tipAnimalRepo = tipAnimalRepo;

        this.serviceAnimal = serviceAnimal;

    }


    @GetMapping("/allAnimals")
    public ResponseEntity<List<Animal>>getAllMyAnimals() throws InterruptedException {

        List<Animal> myAnimals = serviceAnimal.getAllAnimals();

        return new ResponseEntity<>(myAnimals, HttpStatus.OK);
    }



    @PostMapping("/addAnimal")
    public ResponseEntity<Animal> addMyAnimal(@RequestBody Animal animal){

        Animal a = this.serviceAnimal.addTheAnimal(animal);

        return new ResponseEntity<>(a, HttpStatus.CREATED);

    }

    @GetMapping("/findAnimal/{id}")
    public Animal getById(@PathVariable Long id){

        Animal a = this.animalRepository.findById(id).get();

        return a;
    }



    @PutMapping("/updateAnimal")
    public ResponseEntity<Animal> updateAnimal(@RequestBody Animal animal){

        Animal animal1 = serviceAnimal.updateAnimal(animal);

        return new ResponseEntity<>(animal1, HttpStatus.OK);

    }




    @DeleteMapping("/deleteAnim/{id}")
    public ResponseEntity<Long> deleteMyAnim(@PathVariable Long id){

        this.serviceAnimal.deleteAnimal(id);

        return new ResponseEntity<>(id,HttpStatus.OK);


    }



    @GetMapping("/orderAlphabetically")
    public ResponseEntity<List<Animal>> getAnimalAlpha(){

       List<Animal>azList = serviceAnimal.orderAtoZ();

        return new ResponseEntity<>(azList, HttpStatus.OK);
    }

    @GetMapping("/animalsByNumber/{number}")
    public ResponseEntity<List<Animal>> getAnimalsByNumber(@PathVariable int number){


            List<Animal> totalList = this.animalRepository.findAll();

            totalList.sort(Comparator.comparing(Animal::getNo));

            if(number > totalList.get(totalList.size()-1).getNo()){

                throw new NumaruMareAnumale("Numarul e prea mare !!!");
            }else if (number < totalList.get(0).getNo()){

                throw new NumarMicAnimale(("Numar prea mic !!!"));
            }

            else{

                List<Animal> otherList = this.animalRepository.animalByNumber(number);
                return new ResponseEntity<>(otherList,HttpStatus.ACCEPTED);

            }






    }


}
