package com.example.muslich.belajar1.controllers;

import com.example.muslich.belajar1.model.Barang;
import com.example.muslich.belajar1.model.Transaksi;
import com.example.muslich.belajar1.model.User;
import com.example.muslich.belajar1.repository.BarangRepository;
import com.example.muslich.belajar1.repository.TransaksiRepository;
import com.example.muslich.belajar1.repository.UserRepository;
import com.example.muslich.belajar1.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private TransaksiService transaksiService;

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Transaksi>> getTransaksi(){
        List<Transaksi> transaksis = transaksiRepository.findAll();
        return new ResponseEntity(transaksis,HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<List<Transaksi>> createTransaksi (HttpServletRequest httpServletRequest){
        long userid = Long.valueOf(httpServletRequest.getParameter("userid"));
        Optional<User> user = userRepository.findById(userid);
        if(!user.isPresent()){
            return new ResponseEntity("user tidak ada",HttpStatus.BAD_REQUEST);
        }
        long barangid = Long.valueOf(httpServletRequest.getParameter("barangid"));
        Optional<Barang>barang = barangRepository.findById(barangid);
        if(!barang.isPresent()){
            return new ResponseEntity("barang tidak ada",HttpStatus.BAD_REQUEST);
        }
        Date date = new Date();
        Transaksi transaksi = new Transaksi();
        transaksi.setUser(user.get());
        transaksi.setBarang(barang.get());
        transaksi.setDate(date);
        Transaksi transaksiCreate = transaksiRepository.save(transaksi);
        return new ResponseEntity(transaksiCreate,HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "/createdenganservice", method = RequestMethod.POST)
    public ResponseEntity<List<Transaksi>> createTransaksiDenganService (HttpServletRequest httpServletRequest){
        long userid = Long.valueOf(httpServletRequest.getParameter("userid"));
        long barangid = Long.valueOf(httpServletRequest.getParameter("barangid"));
        long quantity = Long.valueOf(httpServletRequest.getParameter("quantity"));
        try {
            Transaksi transaksi = transaksiService.createTransaksi(userid,barangid,quantity);
            return new ResponseEntity(transaksi,HttpStatus.OK); //jika sukses return ini akan diekseusi

        }catch (Exception e){ // jika kena execption error ini yang dieksekusi
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);

        }


    }




}
