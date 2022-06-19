package com.example.animalspring.controller;


import com.example.animalspring.repository.TipAnimalRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/tipAnimal")
public class ControllerTipAnimal {

    private TipAnimalRepo tipAnimalRepo;

    public ControllerTipAnimal(TipAnimalRepo tipAnimalRepo) {
        this.tipAnimalRepo = tipAnimalRepo;
    }


    public String findAnimName(String otherName){

        return this.tipAnimalRepo.findName(otherName);

    }

    @GetMapping("/foundName/{name}")
    public boolean validateAnimName(@PathVariable String name){

        if(findAnimName(name).equals(null)){

            return false;

        }else{

            return true;
        }

    }





}
