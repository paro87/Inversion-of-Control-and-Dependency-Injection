package com.example.carfactory1.service;

import org.springframework.stereotype.Service;
import static com.example.carfactory1.service.CarType.HATCHBACK;

@Service(HATCHBACK)
public class Hatchback implements Car{
    private String carType = "Hatchback";
    @Override
    public String getType() {
        return carType;
    }
}
