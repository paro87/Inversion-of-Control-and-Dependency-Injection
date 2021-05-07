package com.paro.garage.service.orders;

public interface Order {
    String execute(String[] order);
    String getType();
}
