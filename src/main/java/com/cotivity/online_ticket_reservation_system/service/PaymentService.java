package com.cotivity.online_ticket_reservation_system.service;

import com.cotivity.online_ticket_reservation_system.dto.PaymentDTO;

public interface PaymentService {

    void paymentReceived(long id);
}
