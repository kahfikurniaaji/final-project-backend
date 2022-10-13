/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serversidecar.controller;

import com.serversidecar.model.Role;
import com.serversidecar.service.RoleService;
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
@RequestMapping("/role")
public class RoleController {
    
    private RoleService roleService;
    
    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }
    
    @GetMapping
    public ResponseEntity<List<Role>> getAllCountries(){
        return new ResponseEntity(roleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return new ResponseEntity(roleService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Role> postRole(@RequestBody Role role){
        return new ResponseEntity(roleService.create(role), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role){
        return new ResponseEntity(roleService.update(id, role), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable Long id){
        return new ResponseEntity(roleService.delete(id), HttpStatus.OK);
    }
}
