package com.cotivity.online_ticket_reservation_system.controller;

import com.cotivity.online_ticket_reservation_system.dto.LocationDTO;
import com.cotivity.online_ticket_reservation_system.exception.messasge_reponse.MessageResponse;
import com.cotivity.online_ticket_reservation_system.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cotivity.online_ticket_reservation_system.utils.RequiredConstant.*;

@RestController
@RequestMapping(URLConstant.API_VERSION + FeatureAPIConstant.LOCATION_API)
@AllArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<MessageResponse> login(@RequestBody LocationDTO locationDTO) {
        locationService.add(locationDTO);
        MessageResponse messageResponse = new MessageResponse(MessageConstant.LOCATION_CREATED);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAll() {
        List<LocationDTO> locationDTOS = locationService.getAll();
        return new ResponseEntity<>(locationDTOS, HttpStatus.OK);
    }
}
