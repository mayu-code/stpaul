package com.college.stpaul.Helper;

import org.springframework.stereotype.Service;

@Service
public class EmailFromat {
    
    public String admissionEmail(){
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Admission successfull in S.T. paul college");

        return emailContent.toString();
    }

    public String paymentEmaill(){
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Payment sucessfull ! ");

        return emailContent.toString();
    }
}
