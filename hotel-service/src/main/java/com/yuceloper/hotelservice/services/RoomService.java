package com.yuceloper.hotelservice.services;

import com.yuceloper.hotelservice.models.dtos.request.RoomRequest;
import com.yuceloper.hotelservice.models.dtos.response.RoomResponse;
import com.yuceloper.hotelservice.models.entity.RoomEntity;
import com.yuceloper.hotelservice.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomResponse save(RoomRequest request) {
        RoomEntity room = new RoomEntity();
        room.setHotelId(request.hotelId());
        room.setRoomNumber(request.roomNumber());
        room.setCapacity(request.capacity());
        room.setPricePerNight(request.pricePerNight());

        RoomEntity saved = roomRepository.save(room);
        return new RoomResponse(saved.getId(), saved.getRoomNumber(), saved.getCapacity(),
                saved.getPricePerNight(), saved.getHotelId());
    }

    public List<RoomResponse> findAll() {
        return roomRepository.findAll().stream()
                .map(r -> new RoomResponse(r.getId(), r.getRoomNumber(), r.getCapacity(),
                        r.getPricePerNight(), r.getHotelId()))
                .toList();
    }
}

