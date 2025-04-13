package com.yuceloper.hotelservice.repository;

import com.yuceloper.hotelservice.models.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query("""
        SELECT r FROM ReservationEntity r
        WHERE r.roomId = :roomId
          AND r.checkInDate < :checkOut
          AND r.checkOutDate > :checkIn
    """)
    List<ReservationEntity> findConflictingReservations(Long roomId, LocalDate checkIn, LocalDate checkOut);

    List<ReservationEntity> findByGuestName(String username);
}

