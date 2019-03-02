package com.roadTransport.RTOtpService.service;

import com.roadTransport.RTOtpService.entity.OtpDetails;
import org.springframework.stereotype.Service;

@Service
public interface OtpService {

    public OtpDetails generateOtp(long userMobileNumber);
    public boolean verifyOtp(long otp, long userMobileNumber) throws Exception;

}
