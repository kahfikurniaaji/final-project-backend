package com.serversidecar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.serversidecar.model.Rental;
import com.serversidecar.repository.RentalRepository;

@Service
public class RentalService {
    private RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    public Rental getById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "rental not found!"));
    }

    public Rental create(Rental rental) {
        if (rental.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Rental already exist");
        }
        return rentalRepository.save(rental);
    }

    public Rental update(Long id, Rental rental) {
        rental.setId(id);
        return rentalRepository.save(rental);
    }

    public Rental delete(Long id) {
        Rental rental = getById(id);
        rentalRepository.delete(rental);
        return rental;
    }

}