/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.serversidecar.repository;

import com.serversidecar.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author M@1
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);    
}
