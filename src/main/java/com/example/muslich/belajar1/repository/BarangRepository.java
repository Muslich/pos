package com.example.muslich.belajar1.repository;

import com.example.muslich.belajar1.model.Barang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarangRepository extends JpaRepository<Barang,Long> {
}
