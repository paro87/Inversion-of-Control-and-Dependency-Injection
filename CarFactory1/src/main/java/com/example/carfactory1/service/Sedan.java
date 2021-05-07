package com.example.carfactory1.service;

import org.springframework.stereotype.Service;
import static com.example.carfactory1.service.CarType.SEDAN;

@Service(SEDAN)
public class Sedan implements Car{
    private String carType = "Sedan";
    @Override
    public String getType() {
        return carType;
    }
}
