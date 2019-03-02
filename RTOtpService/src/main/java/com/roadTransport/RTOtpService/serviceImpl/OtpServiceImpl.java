package com.roadTransport.RTOtpService.serviceImpl;

import com.roadTransport.RTOtpService.entity.OtpDetails;
import com.roadTransport.RTOtpService.repository.OtpDetailsRepository;
import com.roadTransport.RTOtpService.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    OtpDetailsRepository otpDetailsRepository;

    @Override
    public OtpDetails generateOtp(long userMobileNumber) {

        OtpDetails otpDetails = new OtpDetails();
        Calendar now = Calendar.getInstance();
        Random random = new Random(999999);

        long otp = random.nextInt();
        otpDetails.setOtpNumber(otp);
        otpDetails.setOtpStartTime(Long.parseLong(now.get(Calendar.HOUR_OF_DAY)
                + ":"
                + now.get(Calendar.MINUTE)
                + ":"
                + now.get(Calendar.SECOND)));

        now.add(Calendar.MINUTE,3);

        otpDetails.setOtpEndTime(Long.parseLong(now.get(Calendar.HOUR_OF_DAY)
                + ":"
                + now.get(Calendar.MINUTE)
                + ":"
                + now.get(Calendar.SECOND)));

        otpDetails.setUserMobileNumber(userMobileNumber);

        otpDetailsRepository.saveAndFlush(otpDetails);

        return otpDetails;
    }

    @Override
    public boolean verifyOtp(long otp, long userMobileNumber) throws Exception {

        OtpDetails otpDetails = otpDetailsRepository.findByOtp(otp, userMobileNumber);

        if(otpDetails==null){
            throw new Exception("Otp Is not match.");
        }
        else{

            long time =  otpDetails.getOtpEndTime()-otpDetails.getOtpStartTime();

            if(time <=3){
                return true;
            }
            else {
                return false;
            }
        }
    }
}
