package com.cotivity.online_ticket_reservation_system.controller;


import com.cotivity.online_ticket_reservation_system.dto.ReservationDTO;
import com.cotivity.online_ticket_reservation_system.exception.messasge_reponse.MessageResponse;
import com.cotivity.online_ticket_reservation_system.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.cotivity.online_ticket_reservation_system.utils.RequiredConstant.*;

@RestController
@RequestMapping(URLConstant.API_VERSION + FeatureAPIConstant.RESERVATION_API)
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @RequestMapping(FeatureAPIConstant.LOCATION_API + PathVariableConstant.LOCATION)
    public ResponseEntity<MessageResponse> add(@Valid @RequestBody ReservationDTO reservationDTO, @PathVariable long locationId) {
        reservationService.add(reservationDTO, locationId);
        MessageResponse messageResponse = new MessageResponse(MessageConstant.RESERVATION_CREATED);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping(PathVariableConstant.USER_ID)
    public ResponseEntity<List<ReservationDTO>> getNonPaymentReservation(@PathVariable long userId) {
        List<ReservationDTO> reservationDTO = reservationService.getNonPaymentReservation(userId);
        return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
    }


}
