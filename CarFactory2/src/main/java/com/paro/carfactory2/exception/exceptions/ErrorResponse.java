package com.paro.carfactory2.exception.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    private final String message;
    private final List<String> details;

    public ErrorResponse(String message, List<String> details){
        super();
        this.message = message;
        this.details = details;
    }
}
