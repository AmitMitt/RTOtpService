package com.roadTransport.RTOtpService.controller;

import com.roadTransport.RTOtpService.entity.OtpDetails;
import com.roadTransport.RTOtpService.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @Cacheable(value = "otp", key = "#id", unless = "#result.shares < 500")
    @GetMapping("/getOtp/{mdn}")
    public OtpDetails getOtp(@PathVariable("mdn") long userMobileNumber){

        OtpDetails otpDetails = otpService.generateOtp(userMobileNumber);

        return otpDetails;
    }

    @Cacheable(value = "otp", key = "#id", unless = "#result.shares < 500")
    @GetMapping("/verifyOtp/{otp}/{mdn}")
    public boolean verify(@PathVariable("otp") long otp, @PathVariable("mdn") long userMobileNumber) throws Exception {

        boolean verify = otpService.verifyOtp(otp, userMobileNumber);
        return verify;
    }
}
