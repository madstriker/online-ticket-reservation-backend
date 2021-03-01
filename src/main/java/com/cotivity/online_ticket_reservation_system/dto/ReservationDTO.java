package com.cotivity.online_ticket_reservation_system.dto;

import com.cotivity.online_ticket_reservation_system.date_converter.CustomDateDeserializer;
import com.cotivity.online_ticket_reservation_system.date_converter.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationDTO {

    private long id;

    private Double quantity;
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate orderedDate;
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate travelDate;

    private PaymentDTO payment;
    private LocationDTO location;
}
