package com.paro.garage.model.vehicles;

import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle{
    private final String TYPE ="Car";
    private String plate;
    private String color;
    private final int SLOT = 1;
    @Override
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate){
        this.plate = plate;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getColor() {
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }

    @Override
    public int getSlot() {
        return SLOT;
    }
}
