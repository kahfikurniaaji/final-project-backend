/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.serversidecar.repository;

import com.serversidecar.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author M@1
 */
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByName(String name);
    Boolean existsByName(String name);
}
