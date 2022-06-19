package com.example.animalspring.repository;

import com.example.animalspring.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {

    @Query("select a from Animal a order by a.name")
    public List<Animal> orderAlphabetically();

    @Query("select a from Animal a where a.no > ?1")
    public List<Animal> animalByNumber(int number);




}
