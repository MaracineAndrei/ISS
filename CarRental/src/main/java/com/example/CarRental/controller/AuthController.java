package com.example.CarRental.controller;

import com.example.CarRental.model.UsersModel;
import com.example.CarRental.service.CarsService;
import com.example.CarRental.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping({"","/"})
@SessionAttributes("user")
public class AuthController {

    private final UsersService usersService;

    public AuthController(UsersService usersService, CarsService carsService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        UsersModel user = new UsersModel();
        System.out.println(user);
        model.addAttribute("registerRequest", user);
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersmodel) {
        System.out.println("register request" + usersmodel);
        UsersModel registeredUser = usersService.registerUser(usersmodel.getUsername(), usersmodel.getPassword(),  usersmodel.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersmodel, Model model) {
        System.out.println("login request" + usersmodel);
        UsersModel authentificated = usersService.authentificate(usersmodel.getUsername(), usersmodel.getPassword());
        if (authentificated != null) {
            model.addAttribute("user", authentificated);
            return "redirect:/cars";
        }
        return "error_page";
    }

    @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/login";
    }


}

