package com.example.CarRental.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "car_table")
public class CarModel {
    @Id
    Long id;
    String model;
    String brand;
    String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModel carModel = (CarModel) o;
        return Objects.equals(id, carModel.id) && Objects.equals(model, carModel.model) && Objects.equals(brand, carModel.brand) && Objects.equals(image, carModel.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, brand, image);
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
