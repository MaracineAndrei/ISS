package com.example.CarRental.service;

import com.example.CarRental.model.UsersModel;
import com.example.CarRental.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final IUsersRepository usersRepository;




    public UsersService(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public UsersModel registerUser(String username, String password, String email) {
        if (username == null || password == null)
            return null;
        if(usersRepository.findFirstByUsername(username).isPresent()) {
            System.out.println("Username already in use");
            return null;
        }

        if(usersRepository.findFirstByEmail(email).isPresent()) {
            System.out.println("Email already in use");
            return null;
        }

        UsersModel user = new UsersModel();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return usersRepository.save(user);
    }

    public UsersModel authentificate(String username, String password) {
        return usersRepository.findByUsernameAndPassword(username, password).orElse(null);
    }
}
