package com.example.CarRental.repository;

import com.example.CarRental.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface IUsersRepository extends JpaRepository<UsersModel, Integer> {
    Optional<UsersModel> findFirstByUsername(String username);

    Optional<UsersModel> findFirstByEmail(String email);

    Optional<UsersModel> findById(Long userId);
}
