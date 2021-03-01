package com.cotivity.online_ticket_reservation_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardDetailsDTO {

    private double totalDailyAmount;
    private double totalDailyReserved;
    private double totalOverallAmount;
    private double totalOverallReserved;
}
