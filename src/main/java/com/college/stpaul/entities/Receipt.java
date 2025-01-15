package com.college.stpaul.entities;

import com.college.stpaul.constants.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_seq")
    @SequenceGenerator(name = "custom_seq", sequenceName = "custom_sequence", initialValue = 100, allocationSize = 1)
    private long receiptNumber;
    private double AmountPaid;
    private String transactionId;
    private PaymentType paymentMode;
    private String payDate;

    @ManyToOne
    @JoinColumn(name = "paymentDetails_id")
    @JsonIgnore
    private PaymentDetails paymentDetails;
}
