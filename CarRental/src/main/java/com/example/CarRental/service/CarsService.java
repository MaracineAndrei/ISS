package com.example.CarRental.service;

import com.example.CarRental.model.CarModel;
import com.example.CarRental.repository.ICarsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarsService {

    private final ICarsRepository carsRepository;

    public CarsService(ICarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public List<CarModel> FindAll() {
        return carsRepository.findAll();
    }

    public Optional<CarModel> FindById(Long id) {
        return carsRepository.findFirstById(id);
    }


}
