package com.yuceloper.hotelservice.services;

import com.yuceloper.hotelservice.models.dtos.request.HotelRequest;
import com.yuceloper.hotelservice.models.dtos.response.HotelResponse;
import com.yuceloper.hotelservice.models.entity.HotelEntity;
import com.yuceloper.hotelservice.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelResponse save(HotelRequest request) {
        HotelEntity hotel = new HotelEntity();
        hotel.setName(request.name());
        hotel.setAddress(request.address());
        hotel.setStarRating(request.starRating());
        hotel.setCreatedAt(LocalDateTime.now());
        hotel.setUpdatedAt(LocalDateTime.now());

        HotelEntity saved = hotelRepository.save(hotel);

        return new HotelResponse(
                saved.getId(),
                saved.getName(),
                saved.getAddress(),
                saved.getStarRating(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );
    }

    public List<HotelResponse> findAll() {
        return hotelRepository.findAll()
                .stream()
                .map(h -> new HotelResponse(
                        h.getId(),
                        h.getName(),
                        h.getAddress(),
                        h.getStarRating(),
                        h.getCreatedAt(),
                        h.getUpdatedAt()
                ))
                .toList();
    }
}

