package com.cotivity.online_ticket_reservation_system.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

   public HttpStatus status;

    public BaseException(String message, HttpStatus status){
        super(message);
        this.status =  status;
    }

    public HttpStatus getHttpStatus(){
        return this.status;
    }

}
