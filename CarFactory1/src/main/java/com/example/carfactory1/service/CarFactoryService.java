package com.example.carfactory1.service;

import com.example.carfactory1.exception.exceptions.CarTypeNotSupportedException;
import com.example.carfactory1.model.CarModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CarFactoryService {
    @Autowired
    private Map<String, Car> carMap;

    public String produceCar(CarModel carModel){
        String type = carModel.getCarType();
        Car car = carMap.get(type);
        if (car == null){
            log.error("Car type {} not supported", type);
            throw new CarTypeNotSupportedException("Car type not supported: "+type);
        }
        log.info("Car type {} produced", type);
        return car.getType()+" Car has produced";
    }

}
