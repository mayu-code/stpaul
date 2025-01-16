package com.college.stpaul.Helper;

// import java.io.ByteArrayOutputStream;
// import java.io.IOException;
// import java.util.List;

// import org.apache.poi.ss.usermodel.*;

// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Service;

// import com.college.stpaul.entities.Student;

// @Component
// public class ExcelExporter {
//     public byte[] exportToExcel(List<Student> students) throws IOException {
//         Workbook workbook = new XSSFWorkbook();
//         Sheet sheet = workbook.createSheet("Students");

//         // Header Row
//         Row headerRow = sheet.createRow(0);
//         String[] headers = {"ID", "First Name", "Father Name", "Mother Name", "Email", "Phone No", "DOB", "Roll No", "Class"};
//         for (int i = 0; i < headers.length; i++) {
//             Cell cell = headerRow.createCell(i);
//             cell.setCellValue(headers[i]);
//         }

//         // Data Rows
//         int rowIndex = 1;
//         for (Student student : students) {
//             Row row = sheet.createRow(rowIndex++);
//             row.createCell(0).setCellValue(student.getId());
//             row.createCell(1).setCellValue(student.getFirstName());
//             row.createCell(2).setCellValue(student.getFatherName());
//             row.createCell(3).setCellValue(student.getMotherName());
//             row.createCell(4).setCellValue(student.getEmail());
//             row.createCell(5).setCellValue(student.getPhoneNo());
//             row.createCell(6).setCellValue(student.getDob());
//             row.createCell(7).setCellValue(student.getRollNo());
//             row.createCell(8).setCellValue(student.getCurrentClass());
//         }

//         // Auto-size columns
//         for (int i = 0; i < headers.length; i++) {
//             sheet.autoSizeColumn(i);
//         }

//         // Write data to a byte array
//         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//         workbook.write(outputStream);
//         workbook.close();

//         return outputStream.toByteArray();
//     }
// }

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.college.stpaul.entities.BankDetails;
import com.college.stpaul.entities.GuardianInfo;
import com.college.stpaul.entities.LastCollege;
import com.college.stpaul.entities.PaymentDetails;
import com.college.stpaul.entities.Receipt;
import com.college.stpaul.entities.Student;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelExporter {

    public byte[] exportToExcel(List<Student> students) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");

        // Header Row
        Row headerRow = sheet.createRow(0);
        String[] headers = {
            "ID", "First Name", "Father Name", "Mother Name", "Surname", "Email", "Phone No", 
            "Gender", "Caste", "Category", "Scholarship Category", "Local Address", 
            "Permanent Address", "DOB", "Aadhar No", "Blood Group", "Current Class", 
            "Result", "Roll No", "Session", "Admission Form", "Bank Details", 
            "Bank Account No", "Bank Branch", "IFSC Code", "Guardian Info", 
            "Guardian Name", "Guardian Relation", "Guardian Phone", "Guardian Occupation", 
            "Guardian Income", "Last College", "College Name", "Last College Roll No", 
            "Last College Exam", "Last College Exam Month", "Marks Obtained", 
            "ATKT", "Payment Details", "Installments", "Installment Gap", 
            "Total Fees", "Paid Amount", "Balance Amount", "Installment Amount", 
            "Due Date", "Receipts", "Receipt Number", "Amount Paid", 
            "Transaction ID", "Payment Mode", "Payment Date"
        };
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Data Rows
        int rowIndex = 1;
        for (Student student : students) {
            Row row = sheet.createRow(rowIndex++);
            
            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getFirstName());
            row.createCell(2).setCellValue(student.getFatherName());
            row.createCell(3).setCellValue(student.getMotherName());
            row.createCell(4).setCellValue(student.getSurname());
            row.createCell(5).setCellValue(student.getEmail());
            row.createCell(6).setCellValue(student.getPhoneNo());
            row.createCell(7).setCellValue(student.getGender());
            row.createCell(8).setCellValue(student.getCaste());
            row.createCell(9).setCellValue(student.getCategory());
            row.createCell(10).setCellValue(student.getScholarshipCategory());
            row.createCell(11).setCellValue(student.getLocalAddress());
            row.createCell(12).setCellValue(student.getPermanentAddress());
            row.createCell(13).setCellValue(student.getDob());
            row.createCell(14).setCellValue(student.getAdharNo());
            row.createCell(15).setCellValue(student.getBloodGroup());
            row.createCell(16).setCellValue(student.getCurrentClass());
            row.createCell(17).setCellValue(student.getResult().toString());
            row.createCell(18).setCellValue(student.getRollNo());
            row.createCell(19).setCellValue(student.getSession());

            // Adding Admission Form
            row.createCell(20).setCellValue(student.getAdmissionForm() != null ? student.getAdmissionForm().toString() : "N/A");

            // Adding Bank Details
            BankDetails bankDetails = student.getBankDetails();
            row.createCell(21).setCellValue(bankDetails != null ? bankDetails.getBankName() : "N/A");
            row.createCell(22).setCellValue(bankDetails != null ? bankDetails.getAccountNo() : "N/A");
            row.createCell(23).setCellValue(bankDetails != null ? bankDetails.getBankBranch() : "N/A");
            row.createCell(24).setCellValue(bankDetails != null ? bankDetails.getIfscCode() : "N/A");

            // Adding Guardian Info
            GuardianInfo guardianInfo = student.getGuardianInfo();
            row.createCell(25).setCellValue(guardianInfo != null ? guardianInfo.getGuardianName() : "N/A");
            row.createCell(26).setCellValue(guardianInfo != null ? guardianInfo.getGuardianRelation() : "N/A");
            row.createCell(27).setCellValue(guardianInfo != null ? guardianInfo.getGuardianPhoneNo() : "N/A");
            row.createCell(28).setCellValue(guardianInfo != null ? guardianInfo.getGuardianOccupation() : "N/A");
            row.createCell(29).setCellValue(guardianInfo != null ? guardianInfo.getGuardianIncome() : "N/A");

            // Adding Last College
            LastCollege lastCollege = student.getLastCollege();
            row.createCell(30).setCellValue(lastCollege != null ? lastCollege.getCollegeName() : "N/A");
            row.createCell(31).setCellValue(lastCollege != null ? lastCollege.getRollNo() : "N/A");
            row.createCell(32).setCellValue(lastCollege != null ? lastCollege.getExamination() : "N/A");
            row.createCell(33).setCellValue(lastCollege != null ? lastCollege.getExamMonth() : "N/A");
            row.createCell(34).setCellValue(lastCollege != null ? lastCollege.getMarksObtained() : 0);
            row.createCell(35).setCellValue(lastCollege != null ? lastCollege.isAtkt() : false);

            // Adding Payment Details
            PaymentDetails paymentDetails = student.getPaymentDetails();
            row.createCell(36).setCellValue(paymentDetails != null ? paymentDetails.getInstallments() : 0);
            row.createCell(37).setCellValue(paymentDetails != null ? paymentDetails.getInstallmentGap() : 0);
            row.createCell(38).setCellValue(paymentDetails != null ? paymentDetails.getTotalFees() : 0.0);
            row.createCell(39).setCellValue(paymentDetails != null ? paymentDetails.getPaidAmount() : 0.0);
            row.createCell(40).setCellValue(paymentDetails != null ? paymentDetails.getBalanceAmount() : 0.0);
            row.createCell(41).setCellValue(paymentDetails != null ? paymentDetails.getInstallmentAmount() : 0.0);
            row.createCell(42).setCellValue(paymentDetails != null ? paymentDetails.getDueDate() : "N/A");

            // Adding Receipts
            List<Receipt> receipts = paymentDetails != null ? paymentDetails.getReceipts() : null;
            StringBuilder receiptDetails = new StringBuilder();
            if (receipts != null) {
                for (Receipt receipt : receipts) {
                    receiptDetails.append("Receipt #: ").append(receipt.getReceiptNumber())
                            .append(", Amount: ").append(receipt.getAmountPaid())
                            .append(", Transaction ID: ").append(receipt.getTransactionId())
                            .append(", Date: ").append(receipt.getPayDate()).append("\n");
                }
            }
            row.createCell(43).setCellValue(receiptDetails.toString());
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write data to a byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}

