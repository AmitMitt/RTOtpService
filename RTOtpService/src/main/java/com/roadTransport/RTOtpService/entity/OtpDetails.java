package com.roadTransport.RTOtpService.entity;

import javax.persistence.*;

@Entity
@Table
public class OtpDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long Id;

    @Column
    private long otpNumber;

    @Column
    private long otpStartTime;

    @Column
    private long otpEndTime;

    @Column
    private long userMobileNumber;

    public long getUserMobileNumber() {
        return userMobileNumber;
    }

    public void setUserMobileNumber(long userMobileNumber) {
        this.userMobileNumber = userMobileNumber;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getOtpNumber() {
        return otpNumber;
    }

    public void setOtpNumber(long otpNumber) {
        this.otpNumber = otpNumber;
    }

    public long getOtpStartTime() {
        return otpStartTime;
    }

    public void setOtpStartTime(long otpStartTime) {
        this.otpStartTime = otpStartTime;
    }

    public long getOtpEndTime() {
        return otpEndTime;
    }

    public void setOtpEndTime(long otpEndTime) {
        this.otpEndTime = otpEndTime;
    }

    @Override
    public String toString() {
        return "OtpDetails{" +
                "Id=" + Id +
                ", otpNumber=" + otpNumber +
                ", otpStartTime=" + otpStartTime +
                ", otpEndTime=" + otpEndTime +
                ", userMobileNumber=" + userMobileNumber +
                '}';
    }
}