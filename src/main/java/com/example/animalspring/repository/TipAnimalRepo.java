package com.example.animalspring.repository;

import com.example.animalspring.model.Animal;
import com.example.animalspring.model.TipuriPermiseDeAnimale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TipAnimalRepo extends JpaRepository<TipuriPermiseDeAnimale,Long> {

    @Query("select n.name from TipuriPermiseDeAnimale n where n.name = ?1")
    public String findName(String name);




}
