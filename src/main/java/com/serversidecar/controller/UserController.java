/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serversidecar.controller;

import com.serversidecar.model.Profile;
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
import com.serversidecar.model.User;
import com.serversidecar.model.dto.request.AddRoleUserRequest;
import com.serversidecar.model.dto.request.ProfileRequest;
import com.serversidecar.service.ProfileService;
import com.serversidecar.service.UserService;

/**
 *
 * @author M@1
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;
    private ProfileService profileService;

    @Autowired
    public UserController(UserService userService, ProfileService profileService) {
        this.userService = userService;
        this.profileService = profileService;
    }
    
    @GetMapping
    public ResponseEntity<List<User>> getAllCountries(){
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity(userService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Profile> create(@RequestBody ProfileRequest profile) {
        return new ResponseEntity(profileService.create(profile), HttpStatus.CREATED);
    }

    @PostMapping("/addRole")
    public ResponseEntity<Profile> AddRoles(@RequestBody AddRoleUserRequest addRoleUserRequest) {
        return new ResponseEntity(userService.addRole(addRoleUserRequest), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return new ResponseEntity(userService.update(id, user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        return new ResponseEntity(userService.delete(id), HttpStatus.OK);
    }
}
