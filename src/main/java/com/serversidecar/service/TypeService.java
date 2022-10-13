/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serversidecar.service;

import com.serversidecar.model.Type;
import com.serversidecar.repository.TypeRepository;
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
public class TypeService {

    private TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    public Type getById(Long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "type not found!"));
    }

    public Type create(Type type) {
        if (type.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Type already exist");
        }
        if (typeRepository.existsByName(type.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Type name already exist");
        }
        return typeRepository.save(type);
    }

    public Type update(Long id, Type type) {
        if (typeRepository.existsByName(type.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Type name already exist");
        }
        getById(id);
        type.setId(id);
        return typeRepository.save(type);
    }

    public Type delete(Long id) {
        Type type = getById(id);
        typeRepository.delete(type);
        return type;
    }

    public Type getByName(String name) {
        return typeRepository.findByName(name);
    }
}
