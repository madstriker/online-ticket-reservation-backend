package com.cotivity.online_ticket_reservation_system.service.impl;

import com.cotivity.online_ticket_reservation_system.dto.ReservationDTO;
import com.cotivity.online_ticket_reservation_system.mapper.Mapper;
import com.cotivity.online_ticket_reservation_system.model.Location;
import com.cotivity.online_ticket_reservation_system.model.Payment;
import com.cotivity.online_ticket_reservation_system.model.Reservation;
import com.cotivity.online_ticket_reservation_system.repository.LocationRepository;
import com.cotivity.online_ticket_reservation_system.repository.ReservationRepository;
import com.cotivity.online_ticket_reservation_system.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static com.cotivity.online_ticket_reservation_system.exception.CustomException.DataNotFound;
import static com.cotivity.online_ticket_reservation_system.utils.RequiredConstant.MessageConstant;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final LocationRepository locationRepository;
    private final Mapper<Reservation, ReservationDTO> reservationMapper;

    @Override
    public void add(ReservationDTO reservationDTO, long locationId) {
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> DataNotFound.dataNotFound(MessageConstant.LOCATION_NOT_FOUND, HttpStatus.NOT_FOUND));
        reservation.setLocation(location);
        int userId = (Integer) searchUserId().get("user_id");
        reservation.setUserId(userId);
        Payment payment = addPayment(reservation, location);
        reservation.setPayment(payment);
        reservationRepository.save(reservation);
    }

    private Payment addPayment(Reservation reservation, Location location){
        BiFunction<Double, Double,Double> totalAmount = (q, p) -> q * p;
        Payment payment = new Payment();
        payment.setTotalAmount(totalAmount.apply(reservation.getQuantity(), location.getPrice()));
        return payment;
    }

    private Map<String, Object> searchUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails)authentication.getDetails();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> claims = objectMapper.convertValue(oAuth2AuthenticationDetails.getDecodedDetails(), Map.class);
        return claims;
    }


    @Override
    public List<ReservationDTO> getNonPaymentReservation(long userId) {
        List<Reservation> reservations = reservationRepository.getActivePayment(false, userId);
        List<ReservationDTO> reservationDTOS = reservationMapper.toDTOs(reservations);
        return reservationDTOS;
    }
}
