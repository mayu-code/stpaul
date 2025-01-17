package com.college.stpaul.Helper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.college.stpaul.constants.PaymentType;
import com.college.stpaul.constants.Result;
import com.college.stpaul.entities.AdmissionForm;
import com.college.stpaul.entities.BankDetails;
import com.college.stpaul.entities.BioFocalSubject;
import com.college.stpaul.entities.GuardianInfo;
import com.college.stpaul.entities.LastCollege;
import com.college.stpaul.entities.PaymentDetails;
import com.college.stpaul.entities.Student;
import com.college.stpaul.entities.Subject;
import com.college.stpaul.entities.Subjects;
import com.college.stpaul.entities.User;
import com.college.stpaul.services.serviceImpl.AdmissionFormImpl;
import com.college.stpaul.services.serviceImpl.BankDetailsServiceImpl;
import com.college.stpaul.services.serviceImpl.BioFocalSubjectServiceImpl;
import com.college.stpaul.services.serviceImpl.DocumentsServiceImpl;
import com.college.stpaul.services.serviceImpl.GuardianInfoServiceImpl;
import com.college.stpaul.services.serviceImpl.LastCollegeServiceImpl;
import com.college.stpaul.services.serviceImpl.PaymentDetailServiceImpl;
import com.college.stpaul.services.serviceImpl.StudentServiceImpl;
import com.college.stpaul.services.serviceImpl.SubjectServiceImpl;
import com.college.stpaul.services.serviceImpl.SubjectsServiceImpl;
import com.college.stpaul.services.serviceImpl.UserServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelExporter {

    @Autowired
        private AdmissionFormImpl admissionFormImpl;
    
        @Autowired
        private UserServiceImpl userServiceImpl;
    
        @Autowired
        private StudentServiceImpl studentServiceImpl;
    
        @Autowired
        private BankDetailsServiceImpl bankDetailsServiceImpl;
    
        @Autowired
        private LastCollegeServiceImpl lastCollegeServiceImpl;
    
        @Autowired
        private GuardianInfoServiceImpl guardianInfoServiceImpl;
    
        @Autowired
        private DocumentsServiceImpl documentsServiceImpl;
    
        @Autowired
        private SubjectsServiceImpl subjectsServiceImpl;
    
        @Autowired
        private BioFocalSubjectServiceImpl bioFocalSubjectServiceImpl;

        @Autowired
        private SubjectServiceImpl subjectServiceImpl;


        @Autowired
        private PaymentDetailServiceImpl paymentDetailServiceImpl;

    public byte[] exportToExcel(List<Student> students) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");

        // Header Row
        Row headerRow = sheet.createRow(0);
        String[] headers = {
            "ID", "First Name", "Father Name", "Mother Name", "Surname", "Email", "Phone No",
            "DOB","Adhar No","Blood group","Current Class","Result","Roll No","Session",
            "Gender", "Caste", "Category", "Scholarship Category",
            "Local Address", "Permanent Address",
            "Guardian Name", "Guardian Relation", "Guardian Phone", "Guardian Occupation", "Guardian Income",
            "Bank Name", "Bank Account No", "Bank Branch", "IFSC Code",
            "Last College Name", "Last College Roll No","U Dise No","Past Student Id","Exam month","Result", "Examination", "Marks Obtained", "ATKT",
            "Installments", "Installment Gap", "Total Fees", "Paid Amount", "Balance Amount","Payment Type", "Installment Amount",
            "Due Date",
            "Stream", "Substream", "Subjects",
            "BioFocal Stream","bioFocal Subjects"
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



    // import students 

    public void extractStudents(MultipartFile file,AdmissionForm admissionForm,User user) throws IOException {

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
    
        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            rowIterator.next(); // Skip header row
        }
    
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Student student = new Student();
            AdmissionForm admission = new AdmissionForm();
            admission.setSession(admissionForm.getSession());
            admission.setSection(admissionForm.getSection());
            admission.setStdClass(admissionForm.getStdClass());
            admission.setUser(user);
            admission = this.admissionFormImpl.addAdmissionForm(admission);
    
            // Map Student Basic Info
            student.setFirstName(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
            student.setFatherName(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
            student.setMotherName(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
            student.setSurname(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
            student.setEmail(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);
            student.setPhoneNo(row.getCell(6) != null ? row.getCell(6).getStringCellValue() : null);
            student.setDob(row.getCell(7) != null ? row.getCell(7).getStringCellValue() : null);
            student.setAdharNo(row.getCell(8) != null ? row.getCell(8).getStringCellValue() : null);
            student.setBloodGroup(row.getCell(9) != null ? row.getCell(9).getStringCellValue() : null);
            student.setCurrentClass(row.getCell(10) != null ? row.getCell(10).getStringCellValue() : null);
            student.setResult(row.getCell(11) != null ? Result.valueOf(row.getCell(11).getStringCellValue().toUpperCase()) : null);
            student.setRollNo(row.getCell(12) != null ? row.getCell(12).getStringCellValue() : null);
            student.setSession(row.getCell(13) != null ? row.getCell(13).getStringCellValue() : null);
            student.setGender(row.getCell(14) != null ? row.getCell(14).getStringCellValue() : null);
            student.setCaste(row.getCell(15) != null ? row.getCell(15).getStringCellValue() : null);
            student.setCategory(row.getCell(16) != null ? row.getCell(16).getStringCellValue() : null);
            student.setScholarshipCategory(row.getCell(17) != null ? row.getCell(17).getStringCellValue() : null);
            student.setLocalAddress(row.getCell(18) != null ? row.getCell(18).getStringCellValue() : null);
            student.setPermanentAddress(row.getCell(19) != null ? row.getCell(19).getStringCellValue() : null);
            student.setAdmissionForm(admission);
            student = this.studentServiceImpl.addStudent(student);

    
            // Map Guardian Info
            GuardianInfo guardianInfo = new GuardianInfo();
            guardianInfo.setGuardianName(row.getCell(20) != null ? row.getCell(20).getStringCellValue() : null);
            guardianInfo.setGuardianRelation(row.getCell(21) != null ? row.getCell(21).getStringCellValue() : null);
            guardianInfo.setGuardianPhoneNo(row.getCell(22) != null ? row.getCell(22).getStringCellValue() : null);
            guardianInfo.setGuardianOccupation(row.getCell(23) != null ? row.getCell(23).getStringCellValue() : null);
            guardianInfo.setGuardianIncome(row.getCell(24) != null ? row.getCell(24).getStringCellValue() : null);
            guardianInfo.setStudent(student);
            this.guardianInfoServiceImpl.addGuardianInfo(guardianInfo);
    
            // Map Bank Details
            BankDetails  bankDetails= new BankDetails();
            bankDetails.setBankName(row.getCell(25) != null ? row.getCell(25).getStringCellValue() : null);
            bankDetails.setAccountNo(row.getCell(26) != null ? row.getCell(26).getStringCellValue() : null);
            bankDetails.setBankBranch(row.getCell(27) != null ? row.getCell(27).getStringCellValue() : null);
            bankDetails.setIfscCode(row.getCell(28) != null ? row.getCell(28).getStringCellValue() : null);
            bankDetails.setStudent(student);
            this.bankDetailsServiceImpl.addBankDetails(bankDetails);
    
            // Map Last College Info
            LastCollege lastCollege = new LastCollege();
            lastCollege.setCollegeName(row.getCell(29) != null ? row.getCell(29).getStringCellValue() : null);
            lastCollege.setRollNo(row.getCell(30) != null ? row.getCell(30).getStringCellValue() : null);
            lastCollege.setUdiseNo(row.getCell(31) != null ? row.getCell(31).getStringCellValue() : null);
            lastCollege.setLastStudentId(row.getCell(32) != null ? row.getCell(32).getStringCellValue() : null);
            lastCollege.setExamMonth(row.getCell(33) != null ? row.getCell(33).getStringCellValue() : null);
            lastCollege.setResult(row.getCell(34) != null ? Result.valueOf(row.getCell(34).getStringCellValue().toUpperCase()) : null);
            lastCollege.setExamination(row.getCell(35) != null ? row.getCell(35).getStringCellValue() : null);
            lastCollege.setMarksObtained(row.getCell(36) != null ? (int) row.getCell(36).getNumericCellValue() : 0);
            lastCollege.setAtkt(row.getCell(37) != null && row.getCell(37).getBooleanCellValue());
            lastCollege.setStudent(student);
            this.lastCollegeServiceImpl.addLastCollege(lastCollege);
    
            // Map Payment Details
            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setInstallments(row.getCell(38) != null ? (int) row.getCell(38).getNumericCellValue() : 0);
            paymentDetails.setInstallmentGap(row.getCell(39) != null ? (int) row.getCell(39).getNumericCellValue() : 0);
            paymentDetails.setTotalFees(row.getCell(40) != null ? (Double) row.getCell(40).getNumericCellValue() : 0.0);
            paymentDetails.setPaidAmount(row.getCell(41) != null ? (Double) row.getCell(41).getNumericCellValue() : 0.0);
            paymentDetails.setBalanceAmount(row.getCell(42) != null ? (Double) row.getCell(42).getNumericCellValue() : 0.0);
            paymentDetails.setPaymentType(row.getCell(43) != null ? PaymentType.valueOf(row.getCell(43).getStringCellValue().toUpperCase()) : null);
            paymentDetails.setInstallmentAmount(row.getCell(44) != null ? (Double) row.getCell(44).getNumericCellValue() : 0.0);
            paymentDetails.setDueDate(row.getCell(45) != null ? row.getCell(45).getStringCellValue() : null);
            paymentDetails.setStudent(student);
            this.paymentDetailServiceImpl.addPaymentDetails(paymentDetails);
    
            // Map Subjects
            Subjects subjects = new Subjects();
            subjects.setStream(row.getCell(46) != null ? row.getCell(46).getStringCellValue() : null);
            subjects.setSubStream(row.getCell(47) != null ? row.getCell(47).getStringCellValue() : null);
            String[] subjectDetails = row.getCell(48) != null ? row.getCell(48).getStringCellValue().split("\\n") : new String[0];
            subjects.setStudent(student);
            subjects.setSubject(null);
            subjects = this.subjectsServiceImpl.addSubjects(subjects);
            for (String detail : subjectDetails) {
                String[] subjectParts = detail.split("\\(");
                if (subjectParts.length == 2) {
                    Subject subject = new Subject();
                    subject.setName(subjectParts[0].trim());
                    subject.setMedium(subjectParts[1].replace(")", "").trim());
                    subject.setSubjects(subjects);
                    this.subjectServiceImpl.addSubject(subject);
                }
            }
    
            // Map BioFocal Subjects
            BioFocalSubject bioFocalSubject = new BioFocalSubject();
            bioFocalSubject.setStream(row.getCell(49) != null ? row.getCell(49).getStringCellValue() : null);
            String[] bioSubjectDetails = row.getCell(50) != null ? row.getCell(50).getStringCellValue().split("\\n") : new String[0];
            bioFocalSubject.setStudent(student);
            bioFocalSubject.setSubject(null);
            bioFocalSubject = this.bioFocalSubjectServiceImpl.addBioFocalSubject(bioFocalSubject);
            for (String detail : bioSubjectDetails) {
                String[] bioParts = detail.split("\\(");
                if (bioParts.length == 2) {
                    Subject bioSubject = new Subject();
                    bioSubject.setName(bioParts[0].trim());
                    bioSubject.setMedium(bioParts[1].replace(")", "").trim());
                    bioSubject.setBioFocalSubject(bioFocalSubject); // Link BioFocal subjects to subjects
                    this.subjectServiceImpl.addSubject(bioSubject);
                }
            }

        }
    
        workbook.close();
        return ;
    }
    
    
}

