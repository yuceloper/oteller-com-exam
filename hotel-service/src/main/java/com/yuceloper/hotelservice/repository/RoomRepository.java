package com.yuceloper.hotelservice.repository;

import com.yuceloper.hotelservice.models.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {}

