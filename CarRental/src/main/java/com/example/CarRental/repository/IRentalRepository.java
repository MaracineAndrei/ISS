package com.example.CarRental.repository;

import com.example.CarRental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IRentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserId(Long userId);

    @Query("SELECT r FROM Rental r WHERE r.carId = :carId AND " +
            "((:startDate BETWEEN r.startDate AND r.endDate) OR " +
            "(:endDate BETWEEN r.startDate AND r.endDate) OR " +
            "(r.startDate BETWEEN :startDate AND :endDate))")
    List<Rental> findOverlappingRentals(@Param("carId") Long carId,
                                        @Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);
}