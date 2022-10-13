/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serversidecar.controller;

import com.serversidecar.model.Brand;
import com.serversidecar.service.BrandService;
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
@RequestMapping("/brand")
public class BrandController {
    
    private BrandService brandService;
    
    @Autowired
    public BrandController(BrandService brandService){
        this.brandService = brandService;
    }
    
    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands(){
        return new ResponseEntity(brandService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        return new ResponseEntity(brandService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Brand> postBrand(@RequestBody Brand brand){
        return new ResponseEntity(brandService.create(brand), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand brand){
        return new ResponseEntity(brandService.update(id, brand), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Brand> deleteBrand(@PathVariable Long id){
        return new ResponseEntity(brandService.delete(id), HttpStatus.OK);
    }
}
