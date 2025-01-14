package com.college.stpaul.entities;

import java.util.List;

import com.college.stpaul.constants.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int installments;
    private int installmentGap;
    private double totalFees;
    private double paidAmount;
    private PaymentType paymentType;
    private double balanceAmount;
    private double installmentAmount;
    private String dueDate;


    @OneToMany(mappedBy = "paymentDetails",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Receipt> receipts;

    @OneToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;
}
