package com.paro.garage.service.orders;

import com.paro.garage.exception.exceptions.VehicleTypeNotFoundException;
import com.paro.garage.exception.exceptions.WrongParkOrderException;
import com.paro.garage.model.containers.Container;
import com.paro.garage.service.garages.Garage;
import com.paro.garage.model.vehicles.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ParkOrder implements Order{
    private final String orderType= "park";
    private String plate;
    private String color;
    private String carType;

    private final Map<String, Vehicle> vehicleMap;
    private final Garage garage;

    @Autowired
    public ParkOrder(List<Vehicle> vehicleList, Garage garage){
        vehicleMap = vehicleList.stream().collect(Collectors.toMap(Vehicle::getType, Function.identity()));
        this.garage = garage;
    }

    @Override
    public String getType() {
        return orderType;
    }

    @Override
    public String execute(String[] order) {
        if (order.length!=4){
            //Exception
            log.error("EXCEPTION: Wrong park order: {}", order.toString());
            throw new WrongParkOrderException("EXCEPTION: Wrong park order: "+order.toString());
        }
        plate = order[1];
        color = order[2];
        carType = order[3];

        Vehicle vehicle = vehicleMap.get(carType);
        if (vehicle == null){
            log.error("EXCEPTION: Not supported vehicle type: {}", carType);
            throw new VehicleTypeNotFoundException("EXCEPTION: Not supported vehicle type: "+carType);
        }
        log.info("Vehicle to be parked: {} ", vehicle.getType());
        vehicle.setPlate(plate);
        vehicle.setColor(color);
        Container container = new Container();
        container.setVehicle(vehicle);
        String result = garage.addContainer(container);
        log.info("Vehicle parked: {} {} {}", vehicle.getPlate(), vehicle.getColor(), vehicle.getType());
        return result;

    }
}
