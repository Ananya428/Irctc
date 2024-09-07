package com.irctc.irctc.controller;

import com.irctc.irctc.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.irctc.irctc.bean.User;
import com.irctc.irctc.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@Autowired
	TrainService trainService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user){
		return ResponseEntity.ok(userService.register(user));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody ObjectNode credential){
		return ResponseEntity.ok(userService.login(credential.get("username").asText(), credential.get("password").asText()));
	}

	@GetMapping("/trains")
	public ResponseEntity<?> getTrains(@RequestParam String source, @RequestParam String destination){
		return ResponseEntity.ok(trainService.findTrains(source, destination));
	}
}
