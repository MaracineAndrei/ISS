package com.example.CarRental.service;

import com.example.CarRental.model.UsersModel;
import com.example.CarRental.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;
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

        String encryptedPassword = passwordEncoder.encode(password);
        UsersModel user = new UsersModel();
        user.setUsername(username);
        user.setPassword(encryptedPassword);
        user.setEmail(email);
        return usersRepository.save(user);
    }

    public UsersModel authentificate(String username, String password) {
        Optional<UsersModel> user = usersRepository.findFirstByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.get();
        }
        return null;
    }
}
