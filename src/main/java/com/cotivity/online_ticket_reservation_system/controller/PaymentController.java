package com.cotivity.online_ticket_reservation_system.controller;


import com.cotivity.online_ticket_reservation_system.exception.messasge_reponse.MessageResponse;
import com.cotivity.online_ticket_reservation_system.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cotivity.online_ticket_reservation_system.utils.RequiredConstant.*;

@RestController
@RequestMapping(URLConstant.API_VERSION + FeatureAPIConstant.PAYMENT_API)
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping(PathVariableConstant.ID)
    public ResponseEntity<MessageResponse> paymentReceived(@PathVariable long id) {
        paymentService.paymentReceived(id);
        MessageResponse messageResponse = new MessageResponse(MessageConstant.PAYMENT_RECEIVED);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
