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

    @GetMapping("/getValidName/{name}")
    public String findTheName(String name){

        return null;

    }







}
