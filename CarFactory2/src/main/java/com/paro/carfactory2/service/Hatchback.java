package com.paro.carfactory2.service;

import org.springframework.stereotype.Service;

@Service
public class Hatchback implements Car{
    private String carType = "hatchback";
    @Override
    public String getType() {
        return carType;
    }
}
