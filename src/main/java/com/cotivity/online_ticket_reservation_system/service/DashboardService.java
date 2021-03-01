package com.cotivity.online_ticket_reservation_system.service;

import com.cotivity.online_ticket_reservation_system.dto.DashboardDetailsDTO;

public interface DashboardService {

    DashboardDetailsDTO getDashboardDetails(long userId);
}
