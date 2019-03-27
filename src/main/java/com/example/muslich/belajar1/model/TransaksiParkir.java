package com.example.muslich.belajar1.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "transaksiparkir")
public class TransaksiParkir {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "nomerkendaraan")
    private String nomerkendaraan;

    @Column(name = "datein")
    private Date datein;

    @Column(name = "dateout")
    private Date dateout;

    @Column(name = "resultdate")
    private Date resultdate;

    @Column(name = "harga")
    private double harga;

    @Column(name = "totalHour")
    private long totalHour;


    @ManyToOne
    @JoinColumn(name = "itemparkir_id", referencedColumnName = "id")
    private ItemParkir itemParkir;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomerkendaraan() {
        return nomerkendaraan;
    }

    public void setNomerkendaraan(String nomerkendaraan) {
        this.nomerkendaraan = nomerkendaraan;
    }

    public Date getDatein() {
        return datein;
    }

    public void setDatein(Date datein) {
        this.datein = datein;
    }

    public Date getDateout() {
        return dateout;
    }

    public void setDateout(Date dateout) {
        this.dateout = dateout;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public ItemParkir getItemParkir() {
        return itemParkir;
    }

    public void setItemParkir(ItemParkir itemParkir) {
        this.itemParkir = itemParkir;
    }

    public Date getResultdate() {
        return resultdate;
    }

    public void setResultdate(Date resultdate) {
        this.resultdate = resultdate;
    }

    public long getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(long totalHour) {
        this.totalHour = totalHour;
    }
}

