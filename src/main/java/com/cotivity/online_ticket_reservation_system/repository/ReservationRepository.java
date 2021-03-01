package com.cotivity.online_ticket_reservation_system.repository;

import com.cotivity.online_ticket_reservation_system.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("from Reservation r where r.payment.status = :paymentStatus and r.userId = :userId")
    List<Reservation> getActivePayment(@Param("paymentStatus") boolean paymentStatus, @Param("userId") long userId);

    @Query("from Reservation r where r.payment.status = :paymentStatus and r.userId = :userId and r.payment.paymentDate = :todayDate")
    List<Reservation> getTodaysMetric(@Param("paymentStatus") boolean paymentStatus, @Param("userId") long userId, @Param("todayDate") LocalDate todayDate);

    List<Reservation> findAllByUserId(long userId);
}
