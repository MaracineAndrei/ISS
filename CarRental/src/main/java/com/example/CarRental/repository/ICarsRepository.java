package com.example.CarRental.repository;

import com.example.CarRental.model.CarModel;
import com.example.CarRental.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface ICarsRepository extends JpaRepository<CarModel, Integer> {
    Optional<CarModel> findFirstById(Long id);
}
