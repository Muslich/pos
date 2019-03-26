package com.example.muslich.belajar1.repository;

import com.example.muslich.belajar1.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaksiRepository extends JpaRepository<Transaksi,Long> {
}
