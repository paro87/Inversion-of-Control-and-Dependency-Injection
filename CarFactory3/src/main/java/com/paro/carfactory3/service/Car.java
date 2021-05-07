package com.paro.carfactory3.service;

import org.springframework.beans.factory.annotation.Autowired;

public interface Car {
    String getType();
    @Autowired
    default void registerMyself(CarFactoryService carFactoryService){
        carFactoryService.register(getType(), this);
    }
}
