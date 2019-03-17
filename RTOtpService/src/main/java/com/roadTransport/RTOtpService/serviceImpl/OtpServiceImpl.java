package com.roadTransport.RTOtpService.serviceImpl;

import com.roadTransport.RTOtpService.entity.OtpDetails;
import com.roadTransport.RTOtpService.repository.OtpDetailsRepository;
import com.roadTransport.RTOtpService.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpDetailsRepository otpDetailsRepository;

    @Override
    public OtpDetails generateOtp(long userMobileNumber) {

        OtpDetails otpDetails = new OtpDetails();
        Calendar now = Calendar.getInstance();
        Random random = new Random();

        long otp = 100000+ random.nextInt(900000);
        otpDetails.setOtpNumber(otp);
        otpDetails.setOtpStartTime(now.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
        otpDetails.setUserMobileNumber(userMobileNumber);

        otpDetailsRepository.saveAndFlush(otpDetails);

        return otpDetails;
    }

    @Override
    public boolean verifyOtp(long otp, long userMobileNumber) throws Exception {

        OtpDetails otpDetails = otpDetailsRepository.findByOtp(otp, userMobileNumber);

        if(otpDetails==null){
            throw new Exception("Otp is not Valid.");
        }

        if(otp != otpDetails.getOtpNumber()){

            throw new Exception("Otp is not Valid.");

        }
        else{

            long currentTime = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis();
            long diff = currentTime - otpDetails.getOtpStartTime();

            if(diff >=300000 || diff<=0){
                return false;
            }
            else {
                return true;
            }
        }
    }
}
