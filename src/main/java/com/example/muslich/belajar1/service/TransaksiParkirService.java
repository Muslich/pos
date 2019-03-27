package com.example.muslich.belajar1.service;


import com.example.muslich.belajar1.model.ItemParkir;
import com.example.muslich.belajar1.model.TransaksiParkir;
import com.example.muslich.belajar1.repository.ItemParkirRepository;
import com.example.muslich.belajar1.repository.TransaksiParkirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransaksiParkirService {

    @Autowired
    private TransaksiParkirRepository transaksiParkirRepository;

    @Autowired
    private ItemParkirRepository itemParkirRepository;

    @Transactional
    public TransaksiParkir keluarParkir (long itemparkirid) throws Exception {

        Optional<TransaksiParkir> transaksiParkir = transaksiParkirRepository.findById(itemparkirid);
        if(!transaksiParkir.isPresent()){
            throw new Exception("no transaksi tidak tersedia");
        }
        TransaksiParkir transaksiParkirUpdate = transaksiParkir.get();
        Date datein = transaksiParkirUpdate.getDatein();


        Date dateout = new Date();
        long out = dateout.getTime();
        long in = datein.getTime();
        long result = out - in;
        Date resultDate = new Date((result));
        transaksiParkirUpdate.setDateout(dateout);
        transaksiParkirUpdate.setResultdate(resultDate);
        long secs = result/ 1000;
        long hours = secs / 3600;
        secs = secs % 3600;
        long mins = secs / 60;
        long useHour = 0;
        if(mins/60 > hours){
            useHour = 1;
        }else {
            useHour = hours;
        }
        transaksiParkirUpdate.setTotalHour(hours);
        transaksiParkirUpdate.setHarga(transaksiParkirUpdate.getItemParkir().getTarif() * useHour);

        TransaksiParkir transaksiParkirCreate = transaksiParkirRepository.save(transaksiParkirUpdate);

        return transaksiParkirCreate;

    }

    @Transactional
    public TransaksiParkir createTransaksiParkir (long itemparkirid,String nomerkendaraan) throws Exception {

        TransaksiParkir transaksiParkir = new TransaksiParkir();
        Optional<ItemParkir> itemParkir = itemParkirRepository.findById(itemparkirid);
        if(!itemParkir.isPresent()){
            throw new Exception("item parkir tidak tersedia");
        }

        if(nomerkendaraan.isEmpty()){
            throw new Exception("NO kendaraan harus diisi");
        }

        Date datein = new Date();
//
//        Date dateout = new Date();
//
//        long out = dateout.getTime();
//        long in = datein.getTime();
//        long result = out - in;
//        Date resultDate = new Date((result));


        transaksiParkir.setItemParkir(itemParkir.get());
        transaksiParkir.setNomerkendaraan(nomerkendaraan);
        transaksiParkir.setDatein(datein);
        transaksiParkir.setTotalHour(0);
      //  transaksiParkir.setDateout(dateout);
       // transaksiParkir.setResultdate(resultDate);
       // transaksiParkir.setHarga(harga);

        TransaksiParkir transaksiParkirCreate = transaksiParkirRepository.save(transaksiParkir);
//        ItemParkir itemParkir1 =itemParkir.get();
//        double tarif1 = itemParkir1.getTarif();
//        double tarifTambahan1 = itemParkir1.getTarifTambahan();
//        double harga = tarif1 * resultDate;

        return transaksiParkirCreate;

    }

    public List<TransaksiParkir> findAll(){
        List<TransaksiParkir> transaksiParkirs = transaksiParkirRepository.findAll();

        return transaksiParkirs ;


    }

    public Optional<ItemParkir> findById(long id){
        Optional<ItemParkir> itemparkirs = itemParkirRepository.findById(id);

        return itemparkirs;
    }

}
