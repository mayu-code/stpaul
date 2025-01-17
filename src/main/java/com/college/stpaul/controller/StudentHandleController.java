package com.college.stpaul.controller;


import java.io.IOException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.college.stpaul.Helper.DateTimeFormat;
import com.college.stpaul.Helper.ExcelExporter;
import com.college.stpaul.constants.Result;
import com.college.stpaul.entities.PaymentDetails;
import com.college.stpaul.entities.Receipt;
import com.college.stpaul.entities.Student;
import com.college.stpaul.request.PaymentRequest;
import com.college.stpaul.response.DataResponse;
import com.college.stpaul.response.PaginationResponse;
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

    @Autowired
    private ExcelExporter excelExporter;



    @GetMapping("/getStudent/{pageNo}")
    public ResponseEntity<DataResponse> getStudents(@RequestHeader("Authorization")String jwt,
                                @RequestParam(required = false) String query,
                                @RequestParam(required = false) Result result,
                                @RequestParam(required = false) String currentClass,
                                @RequestParam(required = false) String session,
                                @PathVariable("pageNo")int pageNo
                                ){
        
        DataResponse response = new DataResponse();
        System.out.println(query);
        try{

            response.setData(this.studentServiceImpl.getStudentByField(query,result,currentClass,session,pageNo));
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

    @GetMapping("getStudentById/{id}")
    public ResponseEntity<DataResponse> getStudentById(@PathVariable("id") long id){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.studentServiceImpl.getStudentById(id));
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(200);
            response.setMessage("get Student successfully !");
            return ResponseEntity.of(Optional.of(response));

        }catch(Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(500);
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("getAllFailStudents")
    public ResponseEntity<DataResponse> getAllFailStudent(){
        DataResponse response = new DataResponse();
        try{
            response.setData(this.studentServiceImpl.getAllFailedStudent());
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(200);
            response.setMessage("get failed Students successfully !");
            return ResponseEntity.of(Optional.of(response));

        }catch(Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(500);
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("getPaginationData")
    public ResponseEntity<PaginationResponse> paginationResponse(){
        PaginationResponse response = new PaginationResponse();
        try{
            response.setStudents(this.studentServiceImpl.paginationData());
            response.setPages((int)response.getStudents()/10);
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(200);
            response.setMessage("get pagination data successfully !");
            return ResponseEntity.of(Optional.of(response));

        }catch(Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(500);
            response.setMessage("something went wrong !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/students/excel")
    public ResponseEntity<ByteArrayResource> downloadExcel() throws IOException {
        List<Student> students = this.studentServiceImpl.getStudentByField(null, null, null, null, 0);
        byte[] excelData = excelExporter.exportToExcel(students);

        ByteArrayResource resource = new ByteArrayResource(excelData);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.xlsx")
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .body(resource);
    }
    

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");
            }

            // Parse the Excel file into a list of Student objects
            List<Student> students = excelExporter.extractStudents(file);
            // System.out.println(students.toString());
            return ResponseEntity.ok(students);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file format: " + e.getMessage());
        }
    }
    
}
