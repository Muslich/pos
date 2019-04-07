package com.example.muslich.belajar1.controllers;

import com.example.muslich.belajar1.model.ItemParkir;
import com.example.muslich.belajar1.model.TransaksiParkir;
import com.example.muslich.belajar1.service.ItemParkirService;
import com.example.muslich.belajar1.service.TransaksiParkirService;
import com.example.muslich.belajar1.vo.ReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaksiparkir")
public class TransaksiParkirController {

    @Autowired
    private TransaksiParkirService transaksiParkirService;

    @Autowired
    private ItemParkirService itemParkirService;

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<TransaksiParkir>> getTransaksiParkir() {
        List<TransaksiParkir> transaksiParkirs = transaksiParkirService.findAll();
        return new ResponseEntity(transaksiParkirs, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<List<TransaksiParkir>>createTransaksiParkir(HttpServletRequest httpServletRequest){
        long itemparkirid = Long.valueOf(httpServletRequest.getParameter("itemparkirid"));
        String nokendaraan =httpServletRequest.getParameter("nokendaraan");
        try {
            TransaksiParkir transaksiParkir = transaksiParkirService.createTransaksiParkir(itemparkirid, nokendaraan);
            return new ResponseEntity(transaksiParkir, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/parkirKeluar", method = RequestMethod.POST)
    public ResponseEntity<List<TransaksiParkir>> parkirKeluar(HttpServletRequest httpServletRequest){
        long noTransaksi = Long.valueOf(httpServletRequest.getParameter("noTransaksi"));
        try {
            TransaksiParkir transaksiParkir = transaksiParkirService.keluarParkir(noTransaksi);
            return new ResponseEntity(transaksiParkir, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @ResponseBody
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity<List<ReportVo>>getreportVos() {
        List<ReportVo> transaksiParkirReport = transaksiParkirService.transaksiParkirReport();
        return new ResponseEntity(transaksiParkirReport, HttpStatus.OK);

    }



}
