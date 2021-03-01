package com.cotivity.online_ticket_reservation_system.mapper;

import java.util.List;

public interface Mapper<E,D> {

    E toEntity(D d);

    D toDTO(E e);

    List<D> toDTOs(List<E> e);
}
