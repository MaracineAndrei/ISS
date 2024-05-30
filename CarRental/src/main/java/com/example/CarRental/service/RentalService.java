package com.example.CarRental.service;

import com.example.CarRental.model.Rental;
import com.example.CarRental.repository.IRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {

    @Autowired
    private IRentalRepository rentalRepository;

    public void rentCar(Long carId, Long userId, LocalDate startDate, LocalDate endDate) {
        // Check if the car is available in the given period
        List<Rental> overlappingRentals = rentalRepository.findOverlappingRentals(carId, startDate, endDate);
        if (!overlappingRentals.isEmpty()) {
            throw new IllegalArgumentException("Car is already rented in the given period");
        }

        Rental rental = new Rental();
        rental.setCarId(carId);
        rental.setUserId(userId);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);

        rentalRepository.save(rental);
    }

    public List<Rental> getRentalsByUserId(Long userId) {
        return rentalRepository.findByUserId(userId);
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
}