package com.cotivity.online_ticket_reservation_system.service.impl;

import com.cotivity.online_ticket_reservation_system.dto.DashboardDetailsDTO;
import com.cotivity.online_ticket_reservation_system.model.Reservation;
import com.cotivity.online_ticket_reservation_system.repository.ReservationRepository;
import com.cotivity.online_ticket_reservation_system.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DashboardDetailServiceImpl implements DashboardService {

    private final ReservationRepository reservationRepository;

    @Override
    public DashboardDetailsDTO getDashboardDetails(long userId) {

        List<Reservation> reservations = reservationRepository.findAllByUserId(userId);
        double totalOverallReservation = reservations.stream().mapToDouble(Reservation::getQuantity).sum();
        double totalOverallAmount = reservations.stream().filter(reservation -> reservation.getPayment().isStatus())
                .mapToDouble(reservation -> reservation.getPayment().getTotalAmount()).sum();
        double totalDailyReservation = reservations.stream().filter(reservation -> reservation.getOrderedDate().equals(LocalDate.now()))
                .mapToDouble(reservation -> reservation.getQuantity()).sum();

       DashboardDetailsDTO dashboardDetailsDTO = getDailyMetrics(userId);
       dashboardDetailsDTO.setTotalOverallAmount(totalOverallAmount);
       dashboardDetailsDTO.setTotalOverallReserved(totalOverallReservation);
       dashboardDetailsDTO.setTotalDailyReserved(totalDailyReservation);

       return dashboardDetailsDTO;
    }

    private DashboardDetailsDTO getDailyMetrics(long userId){
        List<Reservation> todaysReservation = reservationRepository.getTodaysMetric(true, userId, LocalDate.now());
        double totalDailyAmount = todaysReservation.stream().filter(reservation -> reservation.getPayment().isStatus())
                .mapToDouble(reservation -> reservation.getPayment().getTotalAmount()).sum();
        DashboardDetailsDTO dashboardDetailsDTO = new DashboardDetailsDTO();
        dashboardDetailsDTO.setTotalDailyAmount(totalDailyAmount);
        return dashboardDetailsDTO;
    }
}
