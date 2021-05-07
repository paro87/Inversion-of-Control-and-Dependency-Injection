package com.paro.garage.service.garages;

import com.paro.garage.model.containers.Container;

import java.util.List;

public interface Garage {
    String addContainer(Container container);
    void removeContainer(int carToLeave);
    List<String> getGarageStatus();
}
