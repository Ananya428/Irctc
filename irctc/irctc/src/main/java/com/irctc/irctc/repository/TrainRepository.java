package com.irctc.irctc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irctc.irctc.bean.Train;

public interface TrainRepository extends JpaRepository<Train, Long> {
	List<Train> findBySourceAndDestination(String source, String destination);
}
