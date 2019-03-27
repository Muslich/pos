package com.example.muslich.belajar1.repository;

import com.example.muslich.belajar1.model.TransaksiParkir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaksiParkirRepository extends JpaRepository<TransaksiParkir,Long> {
}
