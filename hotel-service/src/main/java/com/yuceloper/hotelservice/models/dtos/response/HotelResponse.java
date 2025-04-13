package com.yuceloper.hotelservice.models.dtos.response;

import java.time.LocalDateTime;

public record HotelResponse(
        Long id,
        String name,
        String address,
        int starRating,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
