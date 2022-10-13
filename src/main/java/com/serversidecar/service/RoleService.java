/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serversidecar.service;

import com.serversidecar.model.Role;
import com.serversidecar.repository.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author M@1
 */
@Service
public class RoleService {
    private RoleRepository roleRepository;
       
    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    public List<Role> getAll(){	
        return roleRepository.findAll();
    }
    public Role getById(Long id){
        return roleRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "role not found!"));
    }
    
    public Role create(Role role){
        if (role.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role already exist");
        }
        return roleRepository.save(role);
    }
    public Role update(Long id, Role role){
        Role oldRole = getById(id);
        if(!oldRole.getName().equals(role.getName())){
            findByName(role.getName());
        }
        role.setId(id);
        return roleRepository.save(role);
    }
    
    public Role delete(Long id){
        Role role = getById(id);
        roleRepository.delete(role);
        return role;
    }
    
    public Role findByName(String name){
        return roleRepository.findByName(name).get();
    }
}
