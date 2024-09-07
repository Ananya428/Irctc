package com.irctc.irctc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irctc.irctc.bean.Booking;
import com.irctc.irctc.bean.User;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUser(User user);
}
