package com.cotivity.online_ticket_reservation_system.controller;


import com.cotivity.online_ticket_reservation_system.dto.DashboardDetailsDTO;
import com.cotivity.online_ticket_reservation_system.service.impl.DashboardDetailServiceImpl;
import com.cotivity.online_ticket_reservation_system.utils.RequiredConstant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cotivity.online_ticket_reservation_system.utils.RequiredConstant.*;

@RestController
@RequestMapping(RequiredConstant.URLConstant.API_VERSION + "/dashboard")
@AllArgsConstructor
public class DashboardController {

    private final DashboardDetailServiceImpl dashboardDetailService;

    @GetMapping(PathVariableConstant.USER_ID)
    public ResponseEntity<DashboardDetailsDTO> getDashboardDetails(@PathVariable long userId) {
        DashboardDetailsDTO dashboardDetailsDTO = dashboardDetailService.getDashboardDetails(userId);
        return new ResponseEntity<>(dashboardDetailsDTO, HttpStatus.OK);
    }
}
