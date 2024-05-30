package com.example.CarRental.controller;

import com.example.CarRental.model.CarModel;
import com.example.CarRental.model.Rental;
import com.example.CarRental.model.UsersModel;
import com.example.CarRental.service.CarsService;
import com.example.CarRental.service.RentalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/rentals")
@SessionAttributes("user")
public class RentalController {
    private final RentalService rentalService;

    private final CarsService carsService;

    public RentalController(RentalService rentalService, CarsService carsService) {
        this.rentalService = rentalService;
        this.carsService = carsService;
    }

    @PostMapping
    public String rentCar(@RequestParam Long carId,
                          @RequestParam int startDay, @RequestParam int startMonth, @RequestParam int startYear,
                          @RequestParam int endDay, @RequestParam int endMonth, @RequestParam int endYear,
                          @ModelAttribute("user") UsersModel user, Model model) {
        // Combine the day, month, and year into LocalDate objects
        LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
        LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);

        try {
            // Invoke the rental service to process the rental
            rentalService.rentCar(carId, user.getId(), startDate, endDate);
            model.addAttribute("successMessage", "Car rented successfully.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        // Redirect to the cars page
        return "redirect:/cars";
    }

//    @RequestMapping
//    public String getRentalsPage(@ModelAttribute("user") UsersModel user, Model model) {
//        List<Rental> rentals = rentalService.getAllRentals();
//        model.addAttribute("rentals", rentals);
//        return "rentals-page";
//    }

    @GetMapping
    public String getRentals(@ModelAttribute("user") UsersModel user, Model model) {
        List<Rental> rentals = rentalService.getAllRentals();
        List<CarModel> cars = carsService.FindAll();
        model.addAttribute("rentals", rentals);
        model.addAttribute("cars", cars);
        return "rentals-page";
    }
}