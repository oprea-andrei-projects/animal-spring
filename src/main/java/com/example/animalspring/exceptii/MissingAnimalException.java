package com.example.animalspring.exceptii;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingAnimalException extends RuntimeException{

    public MissingAnimalException(String msg){

        super(msg);
    }
}
