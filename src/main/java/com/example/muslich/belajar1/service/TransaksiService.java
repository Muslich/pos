package com.example.muslich.belajar1.service;

import com.example.muslich.belajar1.model.Barang;
import com.example.muslich.belajar1.model.Transaksi;
import com.example.muslich.belajar1.model.User;
import com.example.muslich.belajar1.repository.BarangRepository;
import com.example.muslich.belajar1.repository.TransaksiRepository;
import com.example.muslich.belajar1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class TransaksiService {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BarangRepository barangRepository;

    @Transactional
    public Transaksi createTransaksi(long userid,long barangid,long quantity) throws Exception {
        Transaksi transaksi = new Transaksi();
        Optional<User> user = userRepository.findById(userid);
        if(!user.isPresent()){
            throw new Exception("user tidak valid");
           // return new ResponseEntity("user tidak ada", HttpStatus.BAD_REQUEST);
        }
        Optional<Barang>barang = barangRepository.findById(barangid);
        if(!barang.isPresent()){
            throw new Exception("barang tidak tersedia");
         //   return new ResponseEntity("barang tidak ada",HttpStatus.BAD_REQUEST);
        }
        Date date = new Date();
        transaksi.setUser(user.get());
        transaksi.setBarang(barang.get());
        transaksi.setDate(date);
        transaksi.setQuantity(quantity);
        Transaksi transaksiCreate = transaksiRepository.save(transaksi);
        Barang barangTransaksi = barang.get();//objek baru untuk mengambil quantity yg ada di database barang
        barangTransaksi.setQuantity(barangTransaksi.getQuantity() - quantity);//logic pengurangan stock quantity
        if(barangTransaksi.getQuantity() < 0){
            throw new Exception("Stock kurang dari quantity input");
        }
        barangRepository.save(barangTransaksi);//untuk memeperbahurui stock barang yang sudah terjadi transaksi
        return transaksiCreate;
    }

}
