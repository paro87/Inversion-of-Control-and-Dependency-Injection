package com.example.carfactory1.service;

import org.springframework.stereotype.Service;
import static com.example.carfactory1.service.CarType.CABRIO;

@Service(CABRIO)
public class Cabrio implements Car{
    private String carType = "Cabrio";
    @Override
    public String getType() {
        return carType;
    }
}
