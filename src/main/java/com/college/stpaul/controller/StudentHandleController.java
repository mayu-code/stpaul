package com.college.stpaul.controller;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.college.stpaul.Helper.DateTimeFormat;
import com.college.stpaul.constants.Result;
import com.college.stpaul.entities.PaymentDetails;
import com.college.stpaul.entities.Receipt;
import com.college.stpaul.entities.Student;
import com.college.stpaul.request.PaymentRequest;
import com.college.stpaul.response.DataResponse;
import com.college.stpaul.response.SuccessResponse;
import com.college.stpaul.services.serviceImpl.PaymentDetailServiceImpl;
import com.college.stpaul.services.serviceImpl.ReceiptServiceImpl;
import com.college.stpaul.services.serviceImpl.StudentServiceImpl;

@RestController
@RequestMapping("/adminuser")
@CrossOrigin(origins = {"http://localhost:5173/","http://localhost:5174/"})
public class StudentHandleController {
    
    @Autowired
    private StudentServiceImpl studentServiceImpl;


    @Autowired
    private PaymentDetailServiceImpl paymentDetailServiceImpl;

    @Autowired
    private ReceiptServiceImpl receiptServiceImpl;



    @GetMapping("/getStudent")
    public ResponseEntity<DataResponse> getStudents(@RequestHeader("Authorization")String jwt,
                                @RequestParam(required = false) String query,
                                @RequestParam(required = false) Result result,
                                @RequestParam(required = false) String currentClass,
                                @RequestParam(required = false) String session
                                ){
        
        DataResponse response = new DataResponse();
        System.out.println(query);
        try{

            response.setData(this.studentServiceImpl.getStudentByField(query,result,currentClass,session));
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(200);
            response.setMessage("get Students Successfully !");
            return ResponseEntity.of(Optional.of(response));

        }catch(Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(500);
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
                                
    }

    @PostMapping("/addPaymentMethod")
    public ResponseEntity<SuccessResponse> PaymentDetails(@RequestHeader("Authorization")String jwt,
                                            @RequestBody PaymentRequest paymentRequest){

        SuccessResponse response = new SuccessResponse();
        PaymentDetails paymentDetails = new PaymentDetails();
        Student student = this.studentServiceImpl.getStudentById(paymentRequest.getStudentId());
        Receipt receipt = new Receipt();
        try{
            paymentDetails.setStudent(student);
            paymentDetails.setInstallments(paymentRequest.getInstallments());
            paymentDetails.setTotalFees(paymentRequest.getTotalFees());
            paymentDetails.setBalanceAmount(paymentRequest.getBalanceAmount());
            paymentDetails.setInstallmentGap(paymentRequest.getInstallmentGap());
            paymentDetails.setPaymentType(paymentRequest.getPaymentType());
            paymentDetails.setPaidAmount(paymentRequest.getPaidAmount());
            paymentDetails.setInstallmentAmount(paymentRequest.getInstallmentAmount());
            paymentDetails.setDueDate(DateTimeFormat.format(LocalDateTime.now().plusMonths(paymentRequest.getInstallmentGap())));

            paymentDetails = this.paymentDetailServiceImpl.addPaymentDetails(paymentDetails);

            receipt.setAmountPaid(paymentRequest.getPaidAmount());
            receipt.setPaymentMode(paymentRequest.getPaymentMode());
            receipt.setTransactionId(paymentRequest.getTransactionId());
            receipt.setPayDate(DateTimeFormat.format(LocalDateTime.now()));

            receipt.setPaymentDetails(paymentDetails);
            this.receiptServiceImpl.addReceipt(receipt);


            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(200);
            response.setMessage("payment Successful !");
            return ResponseEntity.of(Optional.of(response));
        }catch(Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(500);
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
}
