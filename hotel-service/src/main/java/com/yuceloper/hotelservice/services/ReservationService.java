package com.yuceloper.hotelservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuceloper.hotelservice.models.dtos.request.ReservationRequest;
import com.yuceloper.hotelservice.models.dtos.response.ReservationResponse;
import com.yuceloper.hotelservice.models.entity.ReservationEntity;
import com.yuceloper.hotelservice.models.event.ReservationCreatedEvent;
import com.yuceloper.hotelservice.repository.ReservationRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public ReservationService(ReservationRepository reservationRepository,
                              KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.reservationRepository = reservationRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public ReservationResponse create(ReservationRequest request) throws JsonProcessingException {
        var conflicts = reservationRepository.findConflictingReservations(
                request.roomId(), request.checkInDate(), request.checkOutDate());

        if (!conflicts.isEmpty()) {
            throw new IllegalStateException("Bu oda belirtilen tarihlerde zaten rezerve edilmiş.");
        }

        ReservationEntity r = new ReservationEntity();
        r.setHotelId(request.hotelId());
        r.setRoomId(request.roomId());
        r.setGuestName(request.guestName());
        r.setCheckInDate(request.checkInDate());
        r.setCheckOutDate(request.checkOutDate());

        var saved = reservationRepository.save(r);

        sendReservationCreatedEvent(saved);

        return new ReservationResponse(saved.getId(), saved.getHotelId(), saved.getRoomId(),
                saved.getGuestName(), saved.getCheckInDate(), saved.getCheckOutDate());
    }

    public void sendReservationCreatedEvent(ReservationEntity reservation) throws JsonProcessingException {
        ReservationCreatedEvent event = new ReservationCreatedEvent();
        event.setReservationId(reservation.getId());
        event.setHotelId(reservation.getHotelId());
        event.setRoomId(reservation.getRoomId());
        event.setGuestName(reservation.getGuestName());
        event.setCheckInDate(reservation.getCheckInDate());
        event.setCheckOutDate(reservation.getCheckOutDate());

        String eventJson = objectMapper.writeValueAsString(event);
        kafkaTemplate.send("reservation-created", eventJson);
    }

    public List<ReservationResponse> getReservations(String username) {
        List<ReservationEntity> reservationEntities = reservationRepository.findByGuestName(username);

        List<ReservationResponse> reservationResponses = new ArrayList<>();
        for (ReservationEntity reservationEntity : reservationEntities) {
            reservationResponses.add(mapToResponse(reservationEntity));
        }

        return reservationResponses;
    }

    private ReservationResponse mapToResponse(ReservationEntity entity) {
        return ReservationResponse.builder()
                .id(entity.getId())
                .roomId(entity.getRoomId())
                .guestName(entity.getGuestName())
                .checkInDate(entity.getCheckInDate())
                .checkOutDate(entity.getCheckOutDate())
                .build();
    }

}

