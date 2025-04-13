package com.yuceloper.hotelservice.models.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationRequest(
        @NotNull Long hotelId,
        @NotNull Long roomId,
        @NotBlank String guestName,
        @NotNull LocalDate checkInDate,
        @NotNull LocalDate checkOutDate
) {}

