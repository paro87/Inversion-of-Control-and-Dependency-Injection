package com.paro.garage.model.vehicles;

import org.springframework.stereotype.Component;

@Component
public class Jeep implements Vehicle{
    private final String TYPE ="Jeep";
    private String plate;
    private String color;
    private final int SLOT = 2;
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
