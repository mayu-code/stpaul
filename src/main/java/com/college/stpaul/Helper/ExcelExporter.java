package com.college.stpaul.Helper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.college.stpaul.entities.BankDetails;
import com.college.stpaul.entities.BioFocalSubject;
import com.college.stpaul.entities.GuardianInfo;
import com.college.stpaul.entities.LastCollege;
import com.college.stpaul.entities.PaymentDetails;
import com.college.stpaul.entities.Student;
import com.college.stpaul.entities.Subject;
import com.college.stpaul.entities.Subjects;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
            "Gender", "Caste", "Category", "Scholarship Category",
            "Local Address", "Permanent Address",
            "Guardian Name", "Guardian Relation", "Guardian Phone", "Guardian Occupation", "Guardian Income",
            "Bank Name", "Bank Account No", "Bank Branch", "IFSC Code",
            "Last College Name", "Last College Roll No", "Examination", "Marks Obtained", "ATKT",
            "Installments", "Installment Gap", "Total Fees", "Paid Amount", "Balance Amount", "Installment Amount",
            "Due Date",
            "Stream", "Substream", "Subjects"
        };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Data Rows
        int rowIndex = 1;
        for (Student student : students) {
            Row row = sheet.createRow(rowIndex++);

            // Basic Student Info
            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getFirstName());
            row.createCell(2).setCellValue(student.getFatherName());
            row.createCell(3).setCellValue(student.getMotherName());
            row.createCell(4).setCellValue(student.getSurname());
            row.createCell(5).setCellValue(student.getEmail());
            row.createCell(6).setCellValue(student.getPhoneNo());
            row.createCell(7).setCellValue(student.getDob());
            row.createCell(8).setCellValue(student.getAdharNo());
            row.createCell(9).setCellValue(student.getBloodGroup());
            row.createCell(10).setCellValue(student.getCurrentClass());
            row.createCell(11).setCellValue(student.getResult().toString());
            row.createCell(12).setCellValue(student.getRollNo());
            row.createCell(13).setCellValue(student.getSession());
        
            row.createCell(14).setCellValue(student.getGender());
            row.createCell(15).setCellValue(student.getCaste());
            row.createCell(16).setCellValue(student.getCategory());
            row.createCell(17).setCellValue(student.getScholarshipCategory());
            row.createCell(18).setCellValue(student.getLocalAddress());
            row.createCell(19).setCellValue(student.getPermanentAddress());

            // Guardian Info
            GuardianInfo guardianInfo = student.getGuardianInfo();
            row.createCell(20).setCellValue(guardianInfo != null ? guardianInfo.getGuardianName() : "N/A");
            row.createCell(21).setCellValue(guardianInfo != null ? guardianInfo.getGuardianRelation() : "N/A");
            row.createCell(22).setCellValue(guardianInfo != null ? guardianInfo.getGuardianPhoneNo() : "N/A");
            row.createCell(23).setCellValue(guardianInfo != null ? guardianInfo.getGuardianOccupation() : "N/A");
            row.createCell(24).setCellValue(guardianInfo != null ? guardianInfo.getGuardianIncome() : "N/A");

            // Bank Details
            BankDetails bankDetails = student.getBankDetails();
            row.createCell(25).setCellValue(bankDetails != null ? bankDetails.getBankName() : "N/A");
            row.createCell(26).setCellValue(bankDetails != null ? bankDetails.getAccountNo() : "N/A");
            row.createCell(27).setCellValue(bankDetails != null ? bankDetails.getBankBranch() : "N/A");
            row.createCell(28).setCellValue(bankDetails != null ? bankDetails.getIfscCode() : "N/A");

            // Last College Info
            LastCollege lastCollege = student.getLastCollege();
            row.createCell(29).setCellValue(lastCollege != null ? lastCollege.getCollegeName() : "N/A");
            row.createCell(30).setCellValue(lastCollege != null ? lastCollege.getRollNo() : "N/A");
            row.createCell(31).setCellValue(lastCollege != null ? lastCollege.getUdiseNo(): "N/A");
            row.createCell(32).setCellValue(lastCollege != null ? lastCollege.getLastStudentId() : "N/A");
            row.createCell(33).setCellValue(lastCollege != null ? lastCollege.getExamMonth() : "N/A");
            row.createCell(34).setCellValue(lastCollege != null ? lastCollege.getResult().toString() : "N/A");
            row.createCell(35).setCellValue(lastCollege != null ? lastCollege.getExamination() : "N/A");
            row.createCell(36).setCellValue(lastCollege != null ? lastCollege.getMarksObtained() : 0);
            row.createCell(37).setCellValue(lastCollege != null ? lastCollege.isAtkt() : false);

            // Payment Info
            PaymentDetails paymentDetails = student.getPaymentDetails();
            // id 
            row.createCell(38).setCellValue(paymentDetails != null ? paymentDetails.getInstallments() : 0);
            row.createCell(39).setCellValue(paymentDetails != null ? paymentDetails.getInstallmentGap() : 0);
            row.createCell(40).setCellValue(paymentDetails != null ? paymentDetails.getTotalFees() : 0.0);
            row.createCell(41).setCellValue(paymentDetails != null ? paymentDetails.getPaidAmount() : 0.0);
            row.createCell(42).setCellValue(paymentDetails != null ? paymentDetails.getBalanceAmount() : 0.0);
            row.createCell(43).setCellValue(paymentDetails != null ? paymentDetails.getPaymentType().toString() : "N/A");
            
            row.createCell(44).setCellValue(paymentDetails != null ? paymentDetails.getInstallmentAmount() : 0.0);
            row.createCell(45).setCellValue(paymentDetails != null ? paymentDetails.getDueDate() : "N/A");


            // Subjects Info
            Subjects subjects = student.getSubjects();
            row.createCell(46).setCellValue(subjects != null ? subjects.getStream() : "N/A");
            row.createCell(47).setCellValue(subjects != null ? subjects.getSubStream() : "N/A");

            StringBuilder subjectsInfo = new StringBuilder();
            List<Subject> subjectList = subjects != null ? subjects.getSubject() : null;
            if (subjectList != null) {
                for (Subject subject : subjectList) {
                    subjectsInfo.append(subject.getName()).append(" (").append(subject.getMedium()).append(")\n");
                }
            }
            row.createCell(48).setCellValue(subjectsInfo.toString());

            BioFocalSubject bioFocalSubject = student.getBioFocalSubject();
            row.createCell(49).setCellValue(bioFocalSubject != null ? bioFocalSubject.getStream() : "N/A");

            StringBuilder biosubjectsInfo = new StringBuilder();
            List<Subject> biosubjectList = bioFocalSubject != null ? bioFocalSubject.getSubject() : null;
            if (biosubjectList != null) {
                for (Subject subject : biosubjectList) {
                    biosubjectsInfo.append(subject.getName()).append(" (").append(subject.getMedium()).append(")\n");
                }
            }
            row.createCell(50).setCellValue(biosubjectsInfo.toString());
            
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

    public byte[] exportSubjectsToExcel(List<Subjects> subjectsList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Subjects");

        // Header Row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Stream", "SubStream", "Subject Name", "Medium"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        int rowIndex = 1;

        for (Subjects subjects : subjectsList) {
            String stream = subjects.getStream();
            String subStream = subjects.getSubStream();
            List<Subject> subjectList = subjects.getSubject();

            for (int i = 0; i < subjectList.size(); i++) {
                Subject subject = subjectList.get(i);
                Row row = sheet.createRow(rowIndex++);

                if (i == 0) {
                    // Merge cells for "Stream"
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex - 1, rowIndex + subjectList.size() - 2, 0, 0));
                    Cell streamCell = row.createCell(0);
                    streamCell.setCellValue(stream);

                    // Merge cells for "SubStream"
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex - 1, rowIndex + subjectList.size() - 2, 1, 1));
                    Cell subStreamCell = row.createCell(1);
                    subStreamCell.setCellValue(subStream);
                }


                // Fill "Subject Name" and "Medium"
                row.createCell(2).setCellValue(subject.getName());
                row.createCell(3).setCellValue(subject.getMedium());
            }
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


     public List<Student> extractStudents(MultipartFile file) throws IOException {
        List<Student> students = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            rowIterator.next(); // Skip header row
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Student student = new Student();

            // Map basic student details
            // student.setId((long) row.getCell(0).getNumericCellValue());
            student.setFirstName(row.getCell(1).getStringCellValue());
            student.setFatherName(row.getCell(2).getStringCellValue());
            student.setMotherName(row.getCell(3).getStringCellValue());
            student.setSurname(row.getCell(4).getStringCellValue());
            student.setEmail(row.getCell(5).getStringCellValue());
            student.setPhoneNo(row.getCell(6).getStringCellValue());
            student.setGender(row.getCell(7).getStringCellValue());
            student.setCaste(row.getCell(8).getStringCellValue());
            student.setCategory(row.getCell(9).getStringCellValue());
            student.setScholarshipCategory(row.getCell(10).getStringCellValue());
            student.setLocalAddress(row.getCell(11).getStringCellValue());
            student.setPermanentAddress(row.getCell(12).getStringCellValue());

            // Map Guardian Info
            GuardianInfo guardianInfo = new GuardianInfo();
            guardianInfo.setGuardianName(row.getCell(13).getStringCellValue());
            guardianInfo.setGuardianRelation(row.getCell(14).getStringCellValue());
            guardianInfo.setGuardianPhoneNo(row.getCell(15).getStringCellValue());
            guardianInfo.setGuardianOccupation(row.getCell(16).getStringCellValue());
            guardianInfo.setGuardianIncome(row.getCell(17).getStringCellValue());
            guardianInfo.setStudent(student);
            student.setGuardianInfo(guardianInfo);

            // Map Subjects
            Subjects subjects = new Subjects();
            subjects.setStream(row.getCell(34).getStringCellValue());
            subjects.setSubStream(row.getCell(35).getStringCellValue());
            subjects.setStudent(student);

            List<Subject> subjectList = new ArrayList<>();
            String[] subjectDetails = row.getCell(36).getStringCellValue().split("\\n");

            for (String detail : subjectDetails) {
                String[] subjectParts = detail.split("\\(");
                if (subjectParts.length == 2) {
                    Subject subject = new Subject();
                    subject.setName(subjectParts[0].trim());
                    subject.setMedium(subjectParts[1].replace(")", "").trim());
                    subject.setSubjects(subjects);
                    subjectList.add(subject);
                }
            }
            subjects.setSubject(subjectList);
            student.setSubjects(subjects);

            students.add(student);
        }

        workbook.close();
        return students;
    }
}

