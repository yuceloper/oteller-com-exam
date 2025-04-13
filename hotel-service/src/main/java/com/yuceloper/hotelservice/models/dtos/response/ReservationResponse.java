package com.yuceloper.hotelservice.models.dtos.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ReservationResponse(
        Long id,
        Long hotelId,
        Long roomId,
        String guestName,
        LocalDate checkInDate,
        LocalDate checkOutDate
) {}

