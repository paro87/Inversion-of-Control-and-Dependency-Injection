package com.paro.garage.controller;

import com.paro.garage.model.request.OrderRequest;
import com.paro.garage.service.GarageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/garage")
@Slf4j
public class GarageController {
    private final GarageService garageService;

    @Autowired
    public GarageController(GarageService garageService){
        this.garageService = garageService;
    }


    @PostMapping(value = "/execute",  consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String executeOrder(@RequestBody OrderRequest order) throws NoSuchElementException {
        log.info("Order received : {}", order.getOrderRequest());
        return garageService.executeOrder(order.getOrderRequest());
    }



}
