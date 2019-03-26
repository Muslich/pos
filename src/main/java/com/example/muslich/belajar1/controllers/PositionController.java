package com.example.muslich.belajar1.controllers;


import com.example.muslich.belajar1.model.Position;
import com.example.muslich.belajar1.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;

    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity<List<Position>> getPosition(){
        List<Position> positions = positionRepository.findAll();
        return new ResponseEntity(positions,HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<List<Position>> createPosition(HttpServletRequest httpServletRequest) {
        String name = httpServletRequest.getParameter("name");
        Position position = new Position();
        position.setName(name);
        Position positionCreate = positionRepository.save(position);
        return new ResponseEntity(positionCreate, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<List<Position>> deletePosition(@PathVariable(name = "id") Long id) {
        Optional<Position> position = positionRepository.findById(id);
        if(position.isPresent()){
            positionRepository.delete(position.get());
            return new ResponseEntity(position, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<List<Position>> updatePosition(@PathVariable(name = "id") Long id,
                                                         //menjelaskan value ="/update
                                                         HttpServletRequest httpServletRequest ) {//untuk menampung request dari client
        Optional<Position> position = positionRepository.findById(id);//method pencari
        String name = httpServletRequest.getParameter("name");//mendapatkan parameter dari postman
        if(position.isPresent()){
            Position positionUpdate = position.get();
            positionUpdate.setName(name);
            positionRepository.save(positionUpdate);
            return new ResponseEntity(positionUpdate, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }
}
