package com.paro.garage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paro.garage.exception.exceptions.OrderNotFoundException;
import com.paro.garage.exception.exceptions.VehicleNotFoundException;
import com.paro.garage.exception.exceptions.VehicleTypeNotFoundException;
import com.paro.garage.exception.exceptions.WrongParkOrderException;
import com.paro.garage.model.request.OrderRequest;
import com.paro.garage.service.GarageService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GarageController.class)
public class GarageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GarageController garageController;

    @MockBean
    private GarageService garageService;

    @Test
    public void shouldReturnMessageAfterVehicleParks() throws Exception {
        when(this.garageService.executeOrder("park 34-SO-1988 Black Car")).thenReturn("Allocated 1 slot.");

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderRequest("park 34-SO-1988 Black Car");
        String content = (new ObjectMapper()).writeValueAsString(orderRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/garage/execute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.garageController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Allocated 1 slot.")));
    }

    @Test
    public void shouldReturnNoMessageAfterVehicleLeaves() throws Exception {
        when(this.garageService.executeOrder("leave 3")).thenReturn("");

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderRequest("leave 3");
        String content = (new ObjectMapper()).writeValueAsString(orderRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/garage/execute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.garageController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("")));
    }

    @Test
    public void shouldThrowOrderNotFoundException() throws Exception {
        String errorMessage = "EXCEPTION: Not supported order type : move";
        when(this.garageService.executeOrder("move 34-SO-1988 Black Car")).thenThrow(new OrderNotFoundException(errorMessage));

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderRequest("move 34-SO-1988 Black Car");
        String content = (new ObjectMapper()).writeValueAsString(orderRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/garage/execute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.garageController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof OrderNotFoundException))
                .andExpect(result -> assertEquals(errorMessage, result.getResolvedException().getMessage()));

    }

    @Test
    public void shouldThrowVehicleTypeNotFoundException() throws Exception {
        String errorMessage = "EXCEPTION: Not supported vehicle type: Bus";
        when(this.garageService.executeOrder("park 34-SO-1988 Black Bus")).thenThrow(new VehicleTypeNotFoundException(errorMessage));

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderRequest("park 34-SO-1988 Black Bus");
        String content = (new ObjectMapper()).writeValueAsString(orderRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/garage/execute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.garageController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof VehicleTypeNotFoundException))
                .andExpect(result -> assertEquals(errorMessage, result.getResolvedException().getMessage()));

    }

    @Test
    public void shouldThrowVehicleNotFoundException() throws Exception {
        String errorMessage = "EXCEPTION: The car does not exist: 19";
        when(this.garageService.executeOrder("leave 19")).thenThrow(new VehicleNotFoundException(errorMessage));

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderRequest("leave 19");
        String content = (new ObjectMapper()).writeValueAsString(orderRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/garage/execute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.garageController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof VehicleNotFoundException))
                .andExpect(result -> assertEquals(errorMessage, result.getResolvedException().getMessage()));

    }

    @Test
    public void shouldThrowWrongParkOrder() throws Exception {
        String errorMessage = "EXCEPTION: Wrong park order: +order.toString()";
        when(this.garageService.executeOrder("34-SO-1988 Black Bus")).thenThrow(new WrongParkOrderException(errorMessage));

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderRequest("34-SO-1988 Black Bus");
        String content = (new ObjectMapper()).writeValueAsString(orderRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/garage/execute")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.garageController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof WrongParkOrderException))
                .andExpect(result -> assertEquals(errorMessage, result.getResolvedException().getMessage()));

    }
}

