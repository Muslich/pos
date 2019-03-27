package com.example.muslich.belajar1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "itemparkir")
public class ItemParkir {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "namakendaraan")
    private String namaKendaraan;

    @Column(name = "tarif")
    private double tarif;

    @Column(name = "tariftambahan")
    private double tarifTambahan;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        this.namaKendaraan = namaKendaraan;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public double getTarifTambahan() {
        return tarifTambahan;
    }

    public void setTarifTambahan(double tarifTambahan) {
        this.tarifTambahan = tarifTambahan;
    }
}


