package com.college.stpaul.request;

import com.college.stpaul.constants.PaymentType;

import lombok.Data;

@Data
public class PaymentRequest {
    private long studentId;
    private int installments;
    private int installmentGap;
    private double totalFees;
    private double paidAmount;
    private PaymentType paymentType;
    private double balanceAmount;
    private double installmentAmount;
    private String transactionId;
    private PaymentType paymentMode;
}
