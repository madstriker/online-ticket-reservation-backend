package com.cotivity.online_ticket_reservation_system.controller;

import com.cotivity.online_ticket_reservation_system.exception.CustomException;
import com.cotivity.online_ticket_reservation_system.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> dataNotFound(HttpServletRequest  request, CustomException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), request.getRequestURL().toString());
        return new ResponseEntity<>(errorResponse,  ex.getHttpStatus());
    }

}
