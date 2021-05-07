package com.paro.garage.model.vehicles;

public interface Vehicle {
    String getPlate();
    String getType();
    String getColor();
    int getSlot();
    void setPlate(String plate);
    void setColor(String color);
}
