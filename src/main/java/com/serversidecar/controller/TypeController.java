/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serversidecar.controller;

import com.serversidecar.model.Type;
import com.serversidecar.service.TypeService;
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
@RequestMapping("/type")
public class TypeController {
    
    private TypeService typeService;
    
    @Autowired
    public TypeController(TypeService typeService){
        this.typeService = typeService;
    }
    
    @GetMapping
    public ResponseEntity<List<Type>> getAllTypes(){
        return new ResponseEntity(typeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable Long id) {
        return new ResponseEntity(typeService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Type> postType(@RequestBody Type type){
        return new ResponseEntity(typeService.create(type), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type> updateType(@PathVariable Long id, @RequestBody Type type){
        return new ResponseEntity(typeService.update(id, type), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Type> deleteType(@PathVariable Long id){
        return new ResponseEntity(typeService.delete(id), HttpStatus.OK);
    }
}
