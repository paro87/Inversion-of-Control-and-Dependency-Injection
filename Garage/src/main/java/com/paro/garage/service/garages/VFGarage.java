package com.paro.garage.service.garages;

import com.paro.garage.exception.exceptions.VehicleNotFoundException;
import com.paro.garage.model.containers.Container;
import com.paro.garage.model.vehicles.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class VFGarage implements Garage{
    private static final int ADJACENT_SLOT = 1;
    private static final int GARAGE_SIZE = 10;
    private static final Container[] GARAGE = new Container[GARAGE_SIZE];
    private static final List<Container> containerList= new ArrayList();

    public String addContainer(Container container){
        // requiredMinimumSlots - In case if we are at the end of the parking line. For example, we are parking a car in a parking line with only 2 slots left
        int requiredMinimumSlots = container.getVehicle().getSlot();
        int requiredMaximumSlots = requiredMinimumSlots+ADJACENT_SLOT;
        int countSlots = 0;
        for (int i = 0; i < GARAGE.length; i++) {
            if (GARAGE[i]!=null) {
                countSlots = 0;
                continue;
            }
            countSlots++;

            if (countSlots == requiredMaximumSlots){
                container.setStartIndex(i+1-countSlots);
                container.setLastIndex(i);
                container.setAdjacentSlot(ADJACENT_SLOT);
                for (int j = container.getStartIndex(); j <container.getLastIndex()+1 ; j++) {
                    if (j!= container.getLastIndex())
                        container.setAllocatedSlots(j+1);
                    GARAGE[j] = container;
                }
                containerList.add(container);
                String slotExpression = requiredMinimumSlots > 1 ? " slots." : " slot.";
                return "Allocated "+ requiredMinimumSlots+slotExpression;
            }else if (i== GARAGE.length-1 && countSlots==requiredMinimumSlots){
                container.setStartIndex(i+1-countSlots);
                container.setLastIndex(i);
                for (int j = container.getStartIndex(); j <container.getLastIndex()+1 ; j++) {
                    container.setAllocatedSlots(j+1);
                    GARAGE[j] = container;
                }
                containerList.add(container);
                String slotExpression = requiredMinimumSlots > 1 ? "slots." : "slot.";
                return "Allocated "+ requiredMinimumSlots+slotExpression;
            }
        }

        return "Garage is full";
    }

    public void removeContainer(int carToLeave){
        Container containerToRemove = containerList.get(carToLeave-1);
        if (containerToRemove==null){
            //Exception
            log.error("EXCEPTION: The slot is empty. The car you are looking for has already left or you have wrong parking ticket. The car does not exist: {}", carToLeave);
            throw new VehicleNotFoundException("EXCEPTION: The car does not exist: "+ carToLeave);
        }
        for (int j = containerToRemove.getStartIndex(); j <containerToRemove.getLastIndex()+1 ; j++) {
            GARAGE[j] = null;
        }
    }

    public List<String> getGarageStatus(){
        List<String> carsInGarage = new ArrayList<>();
        for (int i = 0; i < GARAGE.length; i++) {
            if (GARAGE[i]==null)
                continue;
            Vehicle vehicle = GARAGE[i].getVehicle();
            String statusExpression = vehicle.getPlate()+" "+vehicle.getColor()+" "+ GARAGE[i].getAllocatedSlots();
            carsInGarage.add(statusExpression);
            if (GARAGE[i].isAdjacentSlotSet()) {
                i = GARAGE[i].getLastIndex() + ADJACENT_SLOT;
            }
            else {
                i = GARAGE[i].getLastIndex();
            }
        }
        return carsInGarage;
    }
}
