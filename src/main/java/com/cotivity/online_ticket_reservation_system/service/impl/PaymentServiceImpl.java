package com.cotivity.online_ticket_reservation_system.service.impl;

import com.cotivity.online_ticket_reservation_system.model.Payment;
import com.cotivity.online_ticket_reservation_system.repository.PaymentRepository;
import com.cotivity.online_ticket_reservation_system.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.cotivity.online_ticket_reservation_system.exception.CustomException.DataNotFound;
import static com.cotivity.online_ticket_reservation_system.utils.RequiredConstant.MessageConstant;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public void paymentReceived(long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(()-> DataNotFound.dataNotFound(MessageConstant.PAYMENT_NOT_FOUND, HttpStatus.NOT_FOUND));
        payment.setStatus(true);
        payment.setPaymentDate(LocalDate.now());
        paymentRepository.save(payment);
    }
}
