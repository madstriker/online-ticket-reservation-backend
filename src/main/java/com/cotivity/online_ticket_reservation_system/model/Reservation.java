package com.cotivity.online_ticket_reservation_system.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "tbl_reservation")
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantity;
    @CreationTimestamp
    private LocalDate orderedDate;
    private LocalDate travelDate;
    private long userId;

    @ManyToOne
    private Location location;
    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;


}
