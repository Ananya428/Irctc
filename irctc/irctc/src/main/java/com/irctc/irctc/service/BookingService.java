package com.irctc.irctc.service;

import com.irctc.irctc.bean.Booking;
import com.irctc.irctc.bean.Train;
import com.irctc.irctc.bean.User;
import com.irctc.irctc.repository.BookingRepository;
import com.irctc.irctc.repository.TrainRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Transactional
    public synchronized boolean bookSeat(Long trainId, User user, int seats) {
        Train train = trainRepository.findById(trainId).orElseThrow(() -> new RuntimeException("Train not found"));
        if (train.getAvailableSeats() >= seats) {
            train.setAvailableSeats(train.getAvailableSeats() - seats);
            trainRepository.save(train);
            Booking booking = new Booking();
            booking.setTrain(train);
            booking.setUser(user);
            booking.setSeatsBooked(seats);
            bookingRepository.save(booking);
            return true;
        } else {
            return false;
        }
    }

    public List<Booking> getBookingDetails(User user) {
        List<Booking> bookings = bookingRepository.findByUser(user);
        bookings.forEach(booking -> booking.getUser().setPassword(null));
        return bookings;
    }
}
