package com.college.stpaul.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int installment;
    private double totalFees;
    private double paidAmount;
    private double balanceAmount;
    private double installmentAmount;
    private String dueDate;


    @OneToOne
    @JoinColumn(name = "student_id",nullable = false)
    private Student student;
}
