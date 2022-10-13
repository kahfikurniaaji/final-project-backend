/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serversidecar.service;

import com.serversidecar.model.Role;
import com.serversidecar.model.User;
import com.serversidecar.model.dto.request.AddRoleUserRequest;
import com.serversidecar.repository.UserRepository;
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
public class UserService {
    private UserRepository userRepository;
    private RoleService roleService;

        
    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService){
        this.userRepository = userRepository;
        this.roleService = roleService;
    }
    
    public List<User> getAll(){	
        return userRepository.findAll();
    }
    public User getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found!"));
    }
    
    public User update(Long id, User user){
        User oldUser = getById(id);
        if(!oldUser.getUsername().equals(user.getUsername())){
            findByUsername(user.getUsername());
        }
        user.setId(id);
        user.setProfile(oldUser.getProfile());
        user.setRoles(oldUser.getRoles());
        return userRepository.save(user);
    }
    
    public User delete(Long id){
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }
    
    public void findByUsername(String name){
        if (userRepository.findByUsername(name).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User name already exist");
        }
    }
    
        public User addRole(AddRoleUserRequest addRoleUserRequest) {
        User user = getById(addRoleUserRequest.getUserId());
        List<Role> roles = user.getRoles();
        addRoleUserRequest.getRoles().forEach(role -> {
            roles.add(roleService.findByName(role));
        });
        user.setRoles(roles);
        return userRepository.save(user);
    }
}