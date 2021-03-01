package com.cotivity.online_ticket_reservation_system.service;

import com.cotivity.online_ticket_reservation_system.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    void add(ReservationDTO reservationDTO, long locationId);
    List<ReservationDTO> getNonPaymentReservation(long userId);
}
