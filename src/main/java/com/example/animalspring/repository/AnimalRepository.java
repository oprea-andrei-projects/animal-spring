package com.example.animalspring.repository;

import com.example.animalspring.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {

    @Query("select a from Animal a order by a.name")
    List<Animal> orderAlphabetically();

    @Query("select a from Animal a where a.no > ?1")
    List<Animal> animalByNumber(int number);

    @Query("select a from Animal a where a.name = ?1")
    Optional<Animal> getAnimalByName(String name);


}
