/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serversidecar.controller;

import com.serversidecar.model.Rental;
import com.serversidecar.service.RentalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author M@1
 */
@RestController
@RequestMapping("/rental")
public class RentalController {
    
    private RentalService rentalService;
    
    @Autowired
    public RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }
    
    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals(){
        return new ResponseEntity(rentalService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        return new ResponseEntity(rentalService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Rental> postRental(@RequestBody Rental rental){
        return new ResponseEntity(rentalService.create(rental), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable Long id, @RequestBody Rental rental){
        return new ResponseEntity(rentalService.update(id, rental), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Rental> deleteRental(@PathVariable Long id){
        return new ResponseEntity(rentalService.delete(id), HttpStatus.OK);
    }
}
