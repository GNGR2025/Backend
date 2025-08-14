package com.gngr.backend.entities.dtos;

import lombok.Data;

@Data
public class OtpVerificationRequest {

    private String phoneNumber;
    private String otpCode;

}
