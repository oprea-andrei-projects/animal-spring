package com.example.animalspring.exceptii;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NumaruMareAnumale extends RuntimeException{

    public NumaruMareAnumale(String msg){

        super(msg);
    }
}