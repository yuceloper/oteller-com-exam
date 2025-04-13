package com.yuceloper.hotelservice.models.dtos.response;

import java.math.BigDecimal;

public record RoomResponse(
        Long id,
        String roomNumber,
        int capacity,
        BigDecimal pricePerNight,
        Long hotelId
) {}
