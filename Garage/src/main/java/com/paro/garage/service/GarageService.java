package com.paro.garage.service;

import com.paro.garage.exception.exceptions.OrderNotFoundException;
import com.paro.garage.service.orders.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GarageService {
    private final Map<String, Order> orderMap;

    @Autowired
    public GarageService(List<Order> carList){
        orderMap = carList.stream().collect(Collectors.toMap(Order::getType, Function.identity()));
    }

    public String executeOrder(String orderString){
        String[] orderTokens = orderString.split(" ");
        String orderToBeExecuted = orderTokens[0];
        Order order = orderMap.get(orderToBeExecuted);
        if (order == null){
            log.error("EXCEPTION: Not supported order type: {}", orderToBeExecuted);
            throw new OrderNotFoundException("EXCEPTION: Not supported order type : "+orderToBeExecuted);
        }
        log.info("Order to be executed: {} ", order.getType());
        return order.execute(orderTokens);
    }
}
