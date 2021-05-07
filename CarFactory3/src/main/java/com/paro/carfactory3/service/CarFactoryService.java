package com.paro.carfactory3.service;

import com.paro.carfactory3.exception.exceptions.CarTypeNotSupportedException;
import com.paro.carfactory3.model.CarModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class CarFactoryService {

    private Map<String, Car> carMap = new HashMap<>();

    public void register(String carType, Car car) {
        carMap.put(carType, car);
    }

    public String produceCar(CarModel carModel) throws NoSuchElementException {
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
