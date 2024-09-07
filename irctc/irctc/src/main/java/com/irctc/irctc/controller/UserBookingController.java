package com.irctc.irctc.controller;

import com.irctc.irctc.bean.User;
import com.irctc.irctc.service.BookingService;
import com.irctc.irctc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserBookingController {

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    @PostMapping("/bookSeat")
    public ResponseEntity<?> bookSeat(@RequestParam Long trainId, @RequestParam int seats, @RequestHeader("user-token") String apiKey) {
        if (!userService.isValid(apiKey, "USER")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid API Key");
        }
        if (bookingService.bookSeat(trainId, userService.getUserByApiKey(apiKey), seats)) {
            return ResponseEntity.ok("Seat booked successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed to book seat");
        }
    }

    @GetMapping("/bookingDetails")
    public ResponseEntity<?> getBookingDetails(@RequestHeader("user-token") String apiKey) {
        if (!userService.isValid(apiKey, "USER")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid API Key");
        }
        User user = userService.getUserByApiKey(apiKey);
        return ResponseEntity.ok(bookingService.getBookingDetails(user));
    }
}
