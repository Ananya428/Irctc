package com.irctc.irctc.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irctc.irctc.bean.User;
import com.irctc.irctc.repository.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;
	
	public String register(User user) {
		try {
			userRepository.save(user);
			return "User registered successfully";
		} catch(Exception e) {
			return "Error occured";
		}
	}
	
	public Map<String, String> login(String username, String password) {
		Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
		if (user.isEmpty()) {
			throw new RuntimeException("UnAuthorized");
		}
		Map<String,String> token = new HashMap<>();
		if(user.get().getRole().equals("ADMIN"))
			token.put("admin-token", Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
		else
			token.put("user-token", Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
		return token;
	}

	public boolean isValid(String apiKey, String role){
		String credential = new String(Base64.getDecoder().decode(apiKey), StandardCharsets.UTF_8);
		String[] credentials =  credential.split(":");
		Optional<User> user = userRepository.findByUsernameAndPassword(credentials[0], credentials[1]);
        return user.map(value -> value.getRole().equals(role)).orElse(false);
    }

	public User getUserByApiKey(String apiKey){
		String credential = new String(Base64.getDecoder().decode(apiKey), StandardCharsets.UTF_8);
		String[] credentials =  credential.split(":");
		Optional<User> user = userRepository.findByUsernameAndPassword(credentials[0], credentials[1]);
		return user.orElse(null);
	}

	public static String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		return bytesToHex(encodedhash);
	}

	private static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (byte b : hash) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
