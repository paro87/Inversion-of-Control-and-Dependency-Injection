package com.paro.garage.service.orders;

import com.paro.garage.service.garages.Garage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class StatusOrder implements Order{
    private final String orderType= "status";
    private final Garage garage;

    @Autowired
    public StatusOrder(Garage garage){
        this.garage = garage;
    }
    @Override
    public String getType() {
        return orderType;
    }
    @Override
    public String execute(String[] order) {
        List<String> outputList= garage.getGarageStatus();
        log.info("Garage status: "+outputList.toString());
        return outputList.toString();
    }

}
