package com.example.animalspring.model;


import com.github.javafaker.Faker;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="animal")
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int no;

    public Animal(String name, int no) {
        this.name = name;
        this.no = no;
    }
}
