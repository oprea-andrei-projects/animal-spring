package com.example.animalspring;

import com.example.animalspring.model.Animal;
import com.example.animalspring.model.TipuriPermiseDeAnimale;
import com.example.animalspring.repository.AnimalRepository;
import com.example.animalspring.repository.TipAnimalRepo;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnimalSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalSpringApplication.class, args);
    }



    @Bean
    CommandLineRunner commandLineRunner(
           AnimalRepository animalRepository){
            return args -> {



        };
    };
}
