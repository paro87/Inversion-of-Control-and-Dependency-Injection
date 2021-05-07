package com.paro.carfactory2.service;

import com.paro.carfactory2.exception.exceptions.CarTypeNotSupportedException;
import com.paro.carfactory2.model.CarModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarFactoryService {

    private Map<String, Car> carMap;

    public CarFactoryService(List<Car> carList){
        carMap = carList.stream().collect(Collectors.toMap(Car::getType, Function.identity()));
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
