package com.paro.carfactory2.service;

import org.springframework.stereotype.Service;

@Service
public class Cabrio implements Car{
    private String carType = "cabrio";

    @Override
    public String getType() {
        return carType;
    }
}
