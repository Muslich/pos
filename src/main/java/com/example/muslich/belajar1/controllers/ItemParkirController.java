package com.example.muslich.belajar1.controllers;

import com.example.muslich.belajar1.model.ItemParkir;
import com.example.muslich.belajar1.service.ItemParkirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itemparkir")
public class ItemParkirController {

    @Autowired
    private ItemParkirService itemParkirService;

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<ItemParkir>>getItemParkir(){
        List<ItemParkir> itemParkirs = itemParkirService.findAll();
        return new ResponseEntity(itemParkirs, HttpStatus.OK);

    }
}
