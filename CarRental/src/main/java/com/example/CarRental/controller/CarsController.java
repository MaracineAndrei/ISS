package com.example.CarRental.controller;
import com.example.CarRental.model.CarModel;
import com.example.CarRental.model.Rental;
import com.example.CarRental.model.UsersModel;
import com.example.CarRental.service.CarsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/cars")
@SessionAttributes("user")
public class CarsController {
    private final CarsService carsService;

    public CarsController(CarsService carsService) {
        this.carsService = carsService;
    }

    @RequestMapping
    public String getUserPage(@ModelAttribute("user") UsersModel user, Model model) {
        List<CarModel> cars = carsService.FindAll();
        model.addAttribute("cars", cars);
        model.addAttribute("days", IntStream.rangeClosed(1, 31).boxed().collect(Collectors.toList()));
        model.addAttribute("months", IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList()));
        int currentYear = LocalDate.now().getYear();
        model.addAttribute("years", IntStream.rangeClosed(currentYear, currentYear + 1).boxed().collect(Collectors.toList()));
        return "cars_page";
    }

    @PostMapping
    public String getRentalsPage(@ModelAttribute("user") UsersModel user, Model model) {
        model.addAttribute("user", user);
        return "redirect:/rentals";
    }
}
