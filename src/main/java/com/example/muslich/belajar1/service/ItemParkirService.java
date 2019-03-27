package com.example.muslich.belajar1.service;

import com.example.muslich.belajar1.model.ItemParkir;
import com.example.muslich.belajar1.repository.ItemParkirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemParkirService {

    @Autowired
    private ItemParkirRepository itemParkirRepository;

    @Transactional
    public ItemParkir createItemParkir(String namaKendaraan,double tarif,double tarifTambahan){

        ItemParkir itemParkir = new ItemParkir();
        itemParkir.setNamaKendaraan(namaKendaraan);
        itemParkir.setTarif(tarif);
        itemParkir.setTarifTambahan(tarifTambahan);
        ItemParkir itemParkirCreate = itemParkirRepository.save(itemParkir);
        return itemParkirCreate;

    }

    public List<ItemParkir> findAll(){
        List<ItemParkir> itemParkirs = itemParkirRepository.findAll();

        return itemParkirs ;


    }



}
