package com.yuceloper.hotelservice.models.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record HotelRequest(
        @NotBlank String name,
        @NotBlank String address,
        @Min(1) @Max(5) int starRating
) {}

