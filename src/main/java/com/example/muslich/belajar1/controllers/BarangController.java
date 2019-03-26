package com.example.muslich.belajar1.controllers;


import com.example.muslich.belajar1.model.Barang;
import com.example.muslich.belajar1.repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/barang")
public class BarangController {

    @Autowired
    private BarangRepository barangRepository;

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Barang>> getBarang(){
        List<Barang> barangs = barangRepository.findAll();
        return new ResponseEntity(barangs, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ArrayList<Barang>> createBarang(HttpServletRequest httpServletRequest) {
        String name = httpServletRequest.getParameter("name");
        double harga = Double.parseDouble(httpServletRequest.getParameter("harga"));
        long quantity = Long.parseLong(httpServletRequest.getParameter("quantity"));
        Barang barang = new Barang();
        barang.setName(name);
        barang.setHarga(harga);
        barang.setQuantity(quantity);
        Barang barangCreate = barangRepository.save(barang);
        return new ResponseEntity(barangCreate, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<List<Barang>> deleteBarang(@PathVariable(name = "id") Long id) {
        Optional<Barang> barang = barangRepository.findById(id);
        if (barang.isPresent()) {
            barangRepository.delete(barang.get());
            return new ResponseEntity(barang, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<List<Barang>> updateBarang(@PathVariable(name = "id") Long id,
                                                         //menjelaskan value ="/update
                                                         HttpServletRequest httpServletRequest ) {//untuk menampung request dari client
        Optional<Barang> barang = barangRepository.findById(id);//method pencari
        String name = httpServletRequest.getParameter("name");//mendapatkan parameter dari postman
        double harga = Double.parseDouble(httpServletRequest.getParameter("harga"));
        long quantity = Long.parseLong(httpServletRequest.getParameter("quantity"));
        if(barang.isPresent()){
            Barang barangUpdate = barang.get();
            barangUpdate.setName(name);
            barangUpdate.setHarga(harga);
            barangUpdate.setQuantity(quantity);
            barangRepository.save(barangUpdate);
            return new ResponseEntity(barangUpdate, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }



}
