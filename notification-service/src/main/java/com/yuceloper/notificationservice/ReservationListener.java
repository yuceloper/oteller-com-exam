package com.yuceloper.notificationservice;

import com.yuceloper.notificationservice.model.event.ReservationCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Component
public class ReservationListener {

    private final ObjectMapper objectMapper;

    public ReservationListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "reservation-created", groupId = "notification-group")
    public void listen(String message) {
        try {
            ReservationCreatedEvent event = objectMapper.readValue(message, ReservationCreatedEvent.class);
            log.info("Yeni Rezervasyon: {} (Hotel) - {} (Room) - {} (Guest) ({} → {})",
                    event.getHotelId(),
                    event.getGuestName(), event.getRoomId(),
                    event.getCheckInDate(), event.getCheckOutDate());
        } catch (Exception e) {
            log.error("Kafka mesajı işlenirken hata oluştu", e);
        }
    }

}

