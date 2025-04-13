package com.yuceloper.hotelservice;

import com.yuceloper.hotelservice.models.dtos.request.HotelRequest;
import com.yuceloper.hotelservice.models.dtos.response.HotelResponse;
import com.yuceloper.hotelservice.models.entity.HotelEntity;
import com.yuceloper.hotelservice.repository.HotelRepository;
import com.yuceloper.hotelservice.services.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HotelServiceTest {

    private HotelRepository hotelRepository;
    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        hotelRepository = mock(HotelRepository.class);
        hotelService = new HotelService(hotelRepository);
    }

    @Test
    void createHotel_ShouldReturnSavedHotel() {
        HotelRequest request = new HotelRequest("Test Otel", "Test Adres", 4);
        HotelEntity savedHotel = new HotelEntity(1L, "Test Otel", "Test Adres", 4);

        when(hotelRepository.save(any(HotelEntity.class))).thenReturn(savedHotel);

        HotelResponse result = hotelService.save(request);

        assertNotNull(result);
        assertEquals("Test Otel", result.name());
        verify(hotelRepository, times(1)).save(any(HotelEntity.class));
    }
}
