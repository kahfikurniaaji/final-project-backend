/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serversidecar.service;

import com.serversidecar.model.Brand;
import com.serversidecar.repository.BrandRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author M@1
 */
@Service
public class BrandService {
    private BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    public Brand getById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "brand not found!"));
    }

    public Brand create(Brand brand) {
        if (brand.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Brand already exist");
        }
        if (brandRepository.existsByName(brand.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Brand name already exist");
        }
        return brandRepository.save(brand);
    }

    public Brand update(Long id, Brand brand) {
        if (brandRepository.existsByName(brand.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Brand name already exist");
        }
        getById(id);
        brand.setId(id);
        return brandRepository.save(brand);
    }

    public Brand delete(Long id) {
        Brand brand = getById(id);
        brandRepository.delete(brand);
        return brand;
    }

    public Brand getByName(String name) {
        return brandRepository.findByName(name);
    }
}
