/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serversidecar.controller;

import com.serversidecar.model.Profile;
import com.serversidecar.model.dto.request.ProfileRequest;
import com.serversidecar.service.ProfileService;
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
@RequestMapping("/profile")
public class ProfileController {
    
    private ProfileService profileService;
    
    @Autowired
    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }
        
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles(){
        return new ResponseEntity(profileService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        return new ResponseEntity(profileService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody ProfileRequest profileRequest){
        return new ResponseEntity(profileService.create(profileRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile profile){
        return new ResponseEntity(profileService.update(id, profile), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profile> deleteProfile(@PathVariable Long id){
        return new ResponseEntity(profileService.delete(id), HttpStatus.OK);
    }
}


