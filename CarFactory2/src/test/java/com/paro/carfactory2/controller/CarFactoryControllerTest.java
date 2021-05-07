package com.paro.carfactory2.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.paro.carfactory2.exception.exceptions.CarTypeNotSupportedException;
import com.paro.carfactory2.model.CarModel;
import com.paro.carfactory2.service.CarFactoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {CarFactoryController.class})
@ExtendWith(SpringExtension.class)
public class CarFactoryControllerTest {
    @Autowired
    private CarFactoryController carFactoryController;

    @MockBean
    private CarFactoryService carFactoryService;

    @Test
    public void shouldReturnMessageForProducedSedan() throws Exception {
        String returnMessage = "sedan Car has produced";
        when(this.carFactoryService.produceCar((CarModel) any())).thenReturn(returnMessage);

        CarModel carModel = new CarModel();
        carModel.setCarType("sedan");
        String content = (new ObjectMapper()).writeValueAsString(carModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/produce")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.carFactoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(returnMessage)));
    }

    @Test
    public void shouldThrowCarTypeNotSupportedException() throws Exception {
        String errorMessage = "Car type not supported: mercedes";
        when(this.carFactoryService.produceCar((CarModel) any())).thenThrow(new CarTypeNotSupportedException(errorMessage));

        CarModel carModel = new CarModel();
        carModel.setCarType("mercedes");
        String content = (new ObjectMapper()).writeValueAsString(carModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/produce")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.carFactoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CarTypeNotSupportedException))
                .andExpect(result -> assertEquals(errorMessage, result.getResolvedException().getMessage()));

    }
}

