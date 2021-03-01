package com.cotivity.online_ticket_reservation_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationDTO {

    private long id;
    private String location;
    private double price;
    private boolean status;
}
