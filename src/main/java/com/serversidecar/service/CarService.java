package com.serversidecar.service;

import com.serversidecar.model.Brand;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.serversidecar.model.Car;
import com.serversidecar.model.Type;
import com.serversidecar.model.dto.request.CarRequest;
import com.serversidecar.repository.CarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarService {
    private CarRepository carRepository;

    private BrandService brandService;

    private TypeService typeService;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "car not found!"));
    }

    public Car create(CarRequest carRequest) {

        Brand brand = brandService.getByName(carRequest.getBrandName());
        if (brand == null) {
            brand = brandService.create(new Brand(null, carRequest.getBrandName()));
        }

        Type type = typeService.getByName(carRequest.getTypeName());
        if (type == null) {
            type = typeService.create(new Type(null, carRequest.getTypeName()));
        }

        Car car = new Car(
                null,
                carRequest.getName(),
                carRequest.getCost(),
                carRequest.getPlat(),
                carRequest.getGambar(),
                brand,
                type);

        return carRepository.save(car);

    }

    public Car update(Long id, Car car) {
        if (carRepository.existsByName(car.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Car name already exist");
        }
        getById(id);
        car.setId(id);
        return carRepository.save(car);
    }

    public Car delete(Long id) {
        Car car = getById(id);
        carRepository.delete(car);
        return car;
    }
}
