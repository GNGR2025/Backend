package com.gngr.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gngr.backend.entities.dtos.AuthResponse;
import com.gngr.backend.entities.dtos.OtpVerificationRequest;
import com.gngr.backend.entities.dtos.PhoneRequest;
import com.gngr.backend.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody PhoneRequest request) {
        authService.sendOtp(request.getPhoneNumber());
        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponse> verifyOtp(@RequestBody OtpVerificationRequest request) {
        AuthResponse response = authService.verifyOtp(request.getPhoneNumber(), request.getOtpCode());
        return ResponseEntity.ok(response);
    }

}
