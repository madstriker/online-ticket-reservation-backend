package com.cotivity.online_ticket_reservation_system.service.impl;

import com.cotivity.online_ticket_reservation_system.dto.LocationDTO;
import com.cotivity.online_ticket_reservation_system.mapper.Mapper;
import com.cotivity.online_ticket_reservation_system.model.Location;
import com.cotivity.online_ticket_reservation_system.repository.LocationRepository;
import com.cotivity.online_ticket_reservation_system.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final Mapper<Location, LocationDTO> locationMapper;

    @Override
    public void add(LocationDTO locationDTO) {
        Location location = locationMapper.toEntity(locationDTO);
        location.setStatus(true);
        locationRepository.save(location);
    }

    @Override
    public List<LocationDTO> getAll() {
        List<Location> locations = locationRepository.findAll();
        List<LocationDTO> locationDTOS = locationMapper.toDTOs(locations);
        return locationDTOS;
    }
}
