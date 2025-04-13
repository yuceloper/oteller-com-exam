package com.yuceloper.hotelservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuceloper.hotelservice.models.dtos.request.ReservationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationServiceIT {

    @LocalServerPort
    private int port = 8081;

    @Autowired
    private ObjectMapper objectMapper;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void createReservation_shouldReturnCreated() {
        String baseUrl = "http://localhost:" + port + "/hotel-service/api/reservations";

        ReservationRequest request = new ReservationRequest(1L, 2L, "Test Misafir",
                LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 3));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5dWNlbG9wZXIiLCJpYXQiOjE3NDQ1ODM5OTIsImV4cCI6MTc0NDY3MDM5Mn0.AwrcC2PLQ_3ydl31F9KE8a74Pb6f3Fl9sdCINwnUiig");

        HttpEntity<String> entity = new HttpEntity<>(toJson(request), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            return "";
        }
    }
}
