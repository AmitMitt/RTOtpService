package com.roadTransport.RTOtpService.repository;

import com.roadTransport.RTOtpService.entity.OtpDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpDetailsRepository extends JpaRepository<OtpDetails, Long> {

    @Query("Select o from OtpDetails o where o.otpNumber = :otpNumber and o.userMobileNumber = :userMobileNumber")
    public OtpDetails findByOtp(@Param("otpNumber") long otpNumber, @Param("userMobileNumber") long userMobileNumber);
}
