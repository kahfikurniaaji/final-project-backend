/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serversidecar.controller;

import com.serversidecar.model.Car;
import com.serversidecar.model.dto.request.CarRequest;
import com.serversidecar.service.CarService;
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

@RestController
@RequestMapping("/car")
public class CarController {
    
    private CarService carService;
    
    @Autowired
    public CarController(CarService carService){
        this.carService = carService;
    }
    
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(){
        return new ResponseEntity(carService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return new ResponseEntity(carService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Car> create(@RequestBody CarRequest carRequest){
        return new ResponseEntity(carService.create(carRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car){
        return new ResponseEntity(carService.update(id, car), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id){
        return new ResponseEntity(carService.delete(id), HttpStatus.OK);
    }
}
