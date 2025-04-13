package com.yuceloper.hotelservice.models.dtos.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RoomRequest(
        @NotNull Long hotelId,
        @NotBlank String roomNumber,
        @Min(1) int capacity,
        @DecimalMin(value = "0.0", inclusive = false) BigDecimal pricePerNight
) {}

