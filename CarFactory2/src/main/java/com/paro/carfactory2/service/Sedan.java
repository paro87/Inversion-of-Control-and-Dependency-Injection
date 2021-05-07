package com.paro.carfactory2.service;

import org.springframework.stereotype.Service;

@Service
public class Sedan implements Car{
    private String carType = "sedan";
    @Override
    public String getType() {
        return carType;
    }
}
