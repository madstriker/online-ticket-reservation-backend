package com.cotivity.online_ticket_reservation_system.mapper.impl;

import com.cotivity.online_ticket_reservation_system.dto.LocationDTO;
import com.cotivity.online_ticket_reservation_system.mapper.Mapper;
import com.cotivity.online_ticket_reservation_system.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationMapper implements Mapper<Location, LocationDTO> {

    @Override
    public Location toEntity(LocationDTO locationDTO) {
        Location location = new Location();
        location.setLocation(locationDTO.getLocation());
        location.setPrice(locationDTO.getPrice());
        location.setStatus(locationDTO.isStatus());
        return location;
    }

    @Override
    public LocationDTO toDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setLocation(location.getLocation());
        locationDTO.setPrice(location.getPrice());
        locationDTO.setStatus(location.isStatus());
        return locationDTO;
    }

    public List<LocationDTO> toDTOs(List<Location> locations){
        List<LocationDTO> locationDTOS = locations.stream().map(this::toDTO).collect(Collectors.toList());
        return locationDTOS;

    }
}
