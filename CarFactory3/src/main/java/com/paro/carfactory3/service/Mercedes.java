package com.paro.carfactory3.service;

import org.springframework.stereotype.Service;

@Service
public class Mercedes implements Car{
    private String carType = "mercedes";
    @Override
    public String getType() {
        return carType;
    }
}
