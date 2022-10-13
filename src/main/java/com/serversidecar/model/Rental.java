package com.serversidecar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_rental")
public class Rental {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date rentalDate;

    @Column(nullable = false)
    private Date returnDate;

    @Column(nullable = false)
    private Long rentCost;

    @Column(nullable = false)
    private String status;
    
    @ManyToOne
    private Car car;
    
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Profile profile;

}
