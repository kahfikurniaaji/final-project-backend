/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serversidecar.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author M@1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
    private String name;
    private Long cost;
    private String plat;
    private String gambar;
    private String typeName;
    private String brandName;   
    
}
