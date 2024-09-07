package com.irctc.irctc.service;

import com.irctc.irctc.bean.Train;
import com.irctc.irctc.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    @Autowired
    TrainRepository trainRepository;
    public void addTrain(Train train) {
        trainRepository.save(train);
    }

    public List<Train> findTrains(String source, String destination) {
        return trainRepository.findBySourceAndDestination(source, destination);
    }
}
