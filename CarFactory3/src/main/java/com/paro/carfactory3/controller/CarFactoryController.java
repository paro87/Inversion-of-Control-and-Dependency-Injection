package com.paro.carfactory3.controller;


import com.paro.carfactory3.model.CarModel;
import com.paro.carfactory3.service.CarFactoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@Slf4j
public class CarFactoryController {
    private CarFactoryService carFactoryService;

    @Autowired
    public CarFactoryController(CarFactoryService carFactoryService){
        this.carFactoryService = carFactoryService;
    }

    @PostMapping(value = "/produce",  consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String produceCar(@RequestBody CarModel carModel) throws NoSuchElementException {
        log.info("Car type: {} received", carModel.getCarType());
        String producedCar = carFactoryService.produceCar(carModel);
        return producedCar;
    }
}
