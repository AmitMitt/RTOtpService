package com.roadTransport.RTOtpService.controller;

import com.roadTransport.RTOtpService.entity.OtpDetails;
import com.roadTransport.RTOtpService.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    OtpService otpService;

    @GetMapping("/getOtp/{mdn}")
    public OtpDetails getOtp(@Param("mdn") long userMobileNumber){

        OtpDetails otpDetails = otpService.generateOtp(userMobileNumber);

        return otpDetails;
    }

    @GetMapping("/verifyOtp/{otp}/{mdn}")
    public boolean verify(@Param("otp") long otp, @Param("mdn") long userMobileNumber) throws Exception {

        boolean verify = otpService.verifyOtp(otp, userMobileNumber);
        return verify;
    }
}