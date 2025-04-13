package com.yuceloper.hotelservice.controllers;

import com.yuceloper.hotelservice.models.dtos.request.HotelRequest;
import com.yuceloper.hotelservice.models.dtos.response.HotelResponse;
import com.yuceloper.hotelservice.services.HotelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(@Valid @RequestBody HotelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        return ResponseEntity.ok(hotelService.findAll());
    }
}

