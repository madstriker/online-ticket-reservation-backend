package com.cotivity.online_ticket_reservation_system.repository;

import com.cotivity.online_ticket_reservation_system.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
