/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.serversidecar.repository;

import com.serversidecar.model.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author M@1
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {
     Optional<Profile> findByName(String name);
     
}
