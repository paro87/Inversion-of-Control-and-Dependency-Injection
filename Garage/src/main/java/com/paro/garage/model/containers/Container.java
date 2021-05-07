package com.paro.garage.model.containers;

import com.paro.garage.model.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Container {
    private Vehicle vehicle;
    private int startIndex;
    private int lastIndex;
    private int adjacentSlot;
    private int containerSize;
    private List<Integer> allocatedSlots;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        containerSize = vehicle.getSlot();
        allocatedSlots = new ArrayList<>();
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public int getAdjacentSlot() {
        return adjacentSlot;
    }

    public void setAdjacentSlot(int adjacentSlot) {
        this.adjacentSlot = adjacentSlot;
    }

    public boolean isAdjacentSlotSet(){
        return adjacentSlot != 0;
    }

    public int getContainerSize() {
        return containerSize;
    }

    public String getAllocatedSlots() {
        Integer[] array = allocatedSlots.toArray(new Integer[0]);
        return Arrays.toString(array);
    }

    public void setAllocatedSlots(int slot) {
        this.allocatedSlots.add(slot);
    }
}
