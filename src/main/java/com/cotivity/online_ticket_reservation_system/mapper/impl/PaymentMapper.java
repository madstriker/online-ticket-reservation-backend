package com.cotivity.online_ticket_reservation_system.mapper.impl;

import com.cotivity.online_ticket_reservation_system.dto.PaymentDTO;
import com.cotivity.online_ticket_reservation_system.mapper.Mapper;
import com.cotivity.online_ticket_reservation_system.model.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMapper implements Mapper<Payment, PaymentDTO> {

    @Override
    public Payment toEntity(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setTotalAmount(paymentDTO.getTotalAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        return payment;
    }

    @Override
    public PaymentDTO toDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setTotalAmount(payment.getTotalAmount());
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        return paymentDTO;
    }

    @Override
    public List<PaymentDTO> toDTOs(List<Payment> e) {
        return null;
    }
}
