package com.gngr.backend.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gngr.backend.entities.OtpRequests;
import com.gngr.backend.entities.Users;
import com.gngr.backend.entities.dtos.AuthResponse;
import com.gngr.backend.repositories.OtpRepository;
import com.gngr.backend.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final OtpRepository otpRepository;

    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    private final CustomUserDetailsService userDetailsService;

    public void sendOtp(String phoneNumber) {
        String otp = generateOtp();
        OtpRequests otpEntity = new OtpRequests();
        otpEntity.setPhoneNumber(phoneNumber);
        otpEntity.setOtpCode(otp);
        otpEntity.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        otpRepository.save(otpEntity);

        System.out.println("Generated OTP for " + phoneNumber + ": " + otp);
    }

    public AuthResponse verifyOtp(String phoneNumber, String otpCode) {
        OtpRequests otpEntity = otpRepository.findTopByPhoneNumberOrderByCreatedAtDesc(phoneNumber);

        if (otpEntity == null) {

            System.out.println("otp not found");

        }

        if (!otpEntity.getOtpCode().equals(otpCode) || otpEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid or expired OTP");
        }

        Users user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseGet(() -> {
                    Users newUser = new Users();
                    newUser.setPhoneNumber(phoneNumber);
                    newUser.setRole(Users.Role.PLAYER);
                    return userRepository.save(newUser);
                });

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getPhoneNumber());
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }

    private String generateOtp() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

}
