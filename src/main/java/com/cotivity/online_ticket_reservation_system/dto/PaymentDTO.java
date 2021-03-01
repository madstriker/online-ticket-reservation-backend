package com.cotivity.online_ticket_reservation_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO {

    private long id;
    private double totalAmount;
    private boolean status;
    private LocalDate paymentDate;
}
