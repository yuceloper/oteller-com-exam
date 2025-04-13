package com.yuceloper.hotelservice.repository;

import com.yuceloper.hotelservice.models.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {}

