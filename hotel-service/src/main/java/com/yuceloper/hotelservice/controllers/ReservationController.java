package com.yuceloper.hotelservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yuceloper.hotelservice.models.dtos.request.ReservationRequest;
import com.yuceloper.hotelservice.models.dtos.response.ReservationResponse;
import com.yuceloper.hotelservice.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> reserveRoom(@Valid @RequestBody ReservationRequest request) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getReservations(@RequestHeader("X-User-Name") String username) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.getReservations(username));
    }
}

