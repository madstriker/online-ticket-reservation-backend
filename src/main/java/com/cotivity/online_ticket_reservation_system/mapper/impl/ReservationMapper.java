package com.cotivity.online_ticket_reservation_system.mapper.impl;

import com.cotivity.online_ticket_reservation_system.dto.ReservationDTO;
import com.cotivity.online_ticket_reservation_system.mapper.Mapper;
import com.cotivity.online_ticket_reservation_system.model.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationMapper implements Mapper<Reservation, ReservationDTO> {

    private final LocationMapper locationMapper;
    private final PaymentMapper paymentMapper;

    @Override
    public Reservation toEntity(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setQuantity(reservationDTO.getQuantity());
        reservation.setTravelDate(reservationDTO.getTravelDate());
        return reservation;
    }

    @Override
    public ReservationDTO toDTO(Reservation reservation) {

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setQuantity(reservation.getQuantity());
        reservationDTO.setTravelDate(reservation.getTravelDate());
        reservationDTO.setLocation(locationMapper.toDTO(reservation.getLocation()));
        reservationDTO.setPayment(paymentMapper.toDTO(reservation.getPayment()));
        return reservationDTO;
    }

    @Override
    public List<ReservationDTO> toDTOs(List<Reservation> reservations) {
        List<ReservationDTO> reservationDTOS = reservations.stream().map(this::toDTO).collect(Collectors.toList());
        return reservationDTOS;
    }
}
