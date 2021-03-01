package com.cotivity.online_ticket_reservation_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ErrorResponse {

    private String message;
    private String apiUrl;
}
