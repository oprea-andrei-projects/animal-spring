package com.example.animalspring.controller;


import com.example.animalspring.exceptii.AnimaleDomestice;
import com.example.animalspring.exceptii.MissingAnimalException;
import com.example.animalspring.exceptii.NumarMicAnimale;
import com.example.animalspring.exceptii.NumaruMareAnumale;
import com.example.animalspring.model.Animal;
import com.example.animalspring.repository.AnimalRepository;
import com.example.animalspring.repository.TipAnimalRepo;
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



    public ControllAnimal(AnimalRepository animalRepository) {

        this.animalRepository = animalRepository;



    }



    @GetMapping("/allAnimals")
    public ResponseEntity<List<Animal>>getAllMyAnimals(){

        return new ResponseEntity<>(animalRepository.findAll(), HttpStatus.ACCEPTED);
    }

//    @PostMapping("/addAnimal")
//    public Animal addAnimal(@RequestBody Animal animal){
//        this.animalRepository.save(animal);
//        return animal;
//    }

//    public String findName(String otherName){
//
//        return this.tipAnimalRepo.findAll().stream()
//                .map(e->e.getName())
//                .filter(e->equals(otherName))
//                .collect(Collectors.toList()).get(0);
//    }






//    @PostMapping("/addAnimal")
//    public ResponseEntity<Animal> addMyAnimal(@RequestBody Animal animal){
//
//
//        if(animal.getName().equals(findName(animal.getName()))){
//
//            this.animalRepository.save(animal);
//        }else{
//
//            throw new AnimaleDomestice("Nu este animal domestic !");
//        }
//
//
//        return new ResponseEntity<>(animal,HttpStatus.ACCEPTED);
//    }

    @GetMapping("/findAnimal/{id}")
    public Animal getById(@PathVariable Long id){

        Animal a = this.animalRepository.findById(id).get();

        return a;
    }



    @PutMapping("/updateAnimal")
    public ResponseEntity<Animal> updateAnimal(@RequestBody Animal animal){

        Animal animal1 = this.animalRepository.findById(animal.getId()).get();
        animal1.setName(animal.getName());
        animal1.setNo((animal.getNo()));

        this.animalRepository.save(animal1);
        return new ResponseEntity<>(animal1, HttpStatus.ACCEPTED);

    }




    @DeleteMapping("/deleteAnim/{id}")
    public ResponseEntity<Animal> deleteMyAnim(@PathVariable Long id){



        Animal a = this.animalRepository.findById(id).get();

        if(this.animalRepository.findById(id).isEmpty()){

            //nu merge !!!!!


            throw new MissingAnimalException("Acest animal nu exista !");
        }else{

            this.animalRepository.delete(a);

        }


        return new ResponseEntity<>(a,HttpStatus.ACCEPTED);


    }



    @GetMapping("/orderAlphabetically")
    public ResponseEntity<List<Animal>>  getAnimalAlpha(){

        List<Animal> aList = this.animalRepository.orderAlphabetically();

        return new ResponseEntity<>(aList, HttpStatus.ACCEPTED);
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
