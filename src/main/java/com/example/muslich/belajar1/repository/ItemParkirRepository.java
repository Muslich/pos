package com.example.muslich.belajar1.repository;

import com.example.muslich.belajar1.model.ItemParkir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ItemParkirRepository extends JpaRepository<ItemParkir,Long> {


    Optional<ItemParkir> findById(long id);
}
