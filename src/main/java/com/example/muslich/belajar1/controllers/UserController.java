package com.example.muslich.belajar1.controllers;

import com.example.muslich.belajar1.model.Position;
import com.example.muslich.belajar1.repository.PositionRepository;
import com.example.muslich.belajar1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.muslich.belajar1.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PositionRepository positionRepository;

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUser(){
        List<User> users = userRepository.findAll();
       // return ResponseEntity.ok().body(userRepository.findAll());
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<List<User>> createUser(HttpServletRequest httpServletRequest) {
        String name = httpServletRequest.getParameter("name");
        long positionid = Long.valueOf(httpServletRequest.getParameter("positionid"));
        Optional<Position> position = positionRepository.findById(positionid);
        User user = new User();
        user.setName(name);
        user.setPosition(position.get());
        User userCreate = userRepository.save(user);
        return new ResponseEntity(userCreate, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<List<User>> deleteUser(@PathVariable (name = "id") Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<List<User>> updateUser(@PathVariable(name = "id") Long id,  //menjelaskan value ="/update
                                                         HttpServletRequest httpServletRequest ) {//untuk menampung request dari client
        Optional<User> user = userRepository.findById(id);//method pencari
        String name = httpServletRequest.getParameter("name");//mendapatkan parameter dari postman
        if(user.isPresent()){
            User userUpdate = user.get();
            userUpdate.setName(name);
            userRepository.save(userUpdate);
            return new ResponseEntity(userUpdate, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

}
