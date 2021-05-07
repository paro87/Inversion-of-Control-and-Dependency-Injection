package com.paro.garage.service.orders;

import com.paro.garage.service.garages.Garage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LeaveOrder implements Order{
    private final String orderType= "leave";
    private final Garage garage;

    @Autowired
    public LeaveOrder(Garage garage){
        this.garage = garage;
    }
    @Override
    public String getType() {
        return orderType;
    }
    @Override
    public String execute(String[] order) {
        int carToLeave = Integer.parseInt(order[1]);
        log.info("Car leaving the garage: " + carToLeave);
        garage.removeContainer(carToLeave);
        log.info("Car left the garage: " + carToLeave);
        return "";
    }


}
