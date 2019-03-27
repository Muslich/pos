package com.example.muslich.belajar1.service;

import com.example.muslich.belajar1.model.ItemParkir;
import com.example.muslich.belajar1.repository.ItemParkirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemParkirService {

    @Autowired
    private ItemParkirRepository itemParkirRepository;

    @Transactional
    public ItemParkir createItemParkir(String namaKendaraan,double tarif,double tarifTambahan) throws Exception {

        ItemParkir itemParkir = new ItemParkir();
        itemParkir.setNamaKendaraan(namaKendaraan);
        if (namaKendaraan.isEmpty()){
            throw new Exception("nama kendaraan harus diinput");
        }
        itemParkir.setTarif(tarif);
        if (tarif == 0)
            throw new Exception("tarif belum di isi");
        itemParkir.setTarifTambahan(tarifTambahan);
        if (tarifTambahan == 0)
            throw new Exception("tarif tambahan belum di isi");
        ItemParkir itemParkirCreate = itemParkirRepository.save(itemParkir);
        return itemParkirCreate;

    }

    public List<ItemParkir> findAll(){
        List<ItemParkir> itemParkirs = itemParkirRepository.findAll();

        return itemParkirs ;


    }



}
