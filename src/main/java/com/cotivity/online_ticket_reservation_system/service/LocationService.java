package com.cotivity.online_ticket_reservation_system.service;

import com.cotivity.online_ticket_reservation_system.dto.LocationDTO;

import java.util.List;

public interface LocationService {

    void add(LocationDTO locationDTO);

    List<LocationDTO> getAll();
}
