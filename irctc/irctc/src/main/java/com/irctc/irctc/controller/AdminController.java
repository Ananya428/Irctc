package com.irctc.irctc.controller;

import com.irctc.irctc.bean.Train;
import com.irctc.irctc.service.TrainService;
import com.irctc.irctc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController  {
    @Autowired
    TrainService trainService;

    @Autowired
    UserService userService;

    @PostMapping("/addTrain")
    public ResponseEntity<?> addTrain(@RequestBody Train train, @RequestHeader("admin-token") String apiKey) {
        if (!userService.isValid(apiKey, "ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid API Key");
        }
        trainService.addTrain(train);
        return ResponseEntity.ok("Train added successfully");
    }
}
