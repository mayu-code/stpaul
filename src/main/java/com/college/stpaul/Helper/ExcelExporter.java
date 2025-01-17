package com.college.stpaul.Helper;

import org.apache.poi.ss.usermodel.*;
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
import com.college.stpaul.services.serviceImpl.GuardianInfoServiceImpl;
import com.college.stpaul.services.serviceImpl.LastCollegeServiceImpl;
import com.college.stpaul.services.serviceImpl.PaymentDetailServiceImpl;
import com.college.stpaul.services.serviceImpl.StudentServiceImpl;
import com.college.stpaul.services.serviceImpl.SubjectServiceImpl;
import com.college.stpaul.services.serviceImpl.SubjectsServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelExporter {

    @Autowired
        private AdmissionFormImpl admissionFormImpl;
    
    
        @Autowired
        private StudentServiceImpl studentServiceImpl;
    
        @Autowired
        private BankDetailsServiceImpl bankDetailsServiceImpl;
    
        @Autowired
        private LastCollegeServiceImpl lastCollegeServiceImpl;
    
        @Autowired
        private GuardianInfoServiceImpl guardianInfoServiceImpl;
    
    
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
            "ID", "Session", "Current Class", "Section", "First Name", "Father Name", "Mother Name", "Surname", "Email", "Phone No",
            "DOB", "Adhar No", "Blood group", "Result", "Roll No", "Gender", "Caste", "Category", "Scholarship Category",
            "Local Address", "Permanent Address", "Guardian Name", "Guardian Relation", "Guardian Phone", "Guardian Occupation", "Guardian Income",
            "Bank Name", "Bank Account No", "Bank Branch", "IFSC Code", "Last College Name", "Last College Roll No", "U Dise No", 
            "Past Student Id", "Exam month", "Result", "Examination", "Marks Obtained", "ATKT",
            "Installments", "Installment Gap", "Total Fees", "Paid Amount", "Balance Amount", "Payment Type", "Installment Amount",
            "Due Date", "Stream", "Substream", "Subjects", "BioFocal Stream", "bioFocal Subjects"
        };
    
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
    
        int rowIndex = 1;
        for (Student student : students) {
            Row row = sheet.createRow(rowIndex++);
    
            // Basic Student Info
            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getSession());
            row.createCell(2).setCellValue(student.getCurrentClass());
            row.createCell(3).setCellValue(student.getSection());
            row.createCell(4).setCellValue(student.getFirstName());
            row.createCell(5).setCellValue(student.getFatherName());
            row.createCell(6).setCellValue(student.getMotherName());
            row.createCell(7).setCellValue(student.getSurname());
            row.createCell(8).setCellValue(student.getEmail());
            if (student.getPhoneNo() != null) {
                row.createCell(9).setCellValue(Long.parseLong(student.getPhoneNo()));
            }
            row.createCell(10).setCellValue(student.getDob());
            if (student.getAdharNo() != null) {
                row.createCell(11).setCellValue(Long.parseLong(student.getAdharNo()));
            }
            row.createCell(12).setCellValue(student.getBloodGroup());
            row.createCell(13).setCellValue(student.getResult().toString());
            if (student.getRollNo() != null) {
                row.createCell(14).setCellValue(Long.parseLong(student.getRollNo()));
            }
            row.createCell(15).setCellValue(student.getGender());
            row.createCell(16).setCellValue(student.getCaste());
            row.createCell(17).setCellValue(student.getCategory());
            row.createCell(18).setCellValue(student.getScholarshipCategory());
            row.createCell(19).setCellValue(student.getLocalAddress());
            row.createCell(20).setCellValue(student.getPermanentAddress());
    
            // Guardian Info
            GuardianInfo guardianInfo = student.getGuardianInfo();
            row.createCell(21).setCellValue(guardianInfo != null ? guardianInfo.getGuardianName() : "N/A");
            row.createCell(22).setCellValue(guardianInfo != null ? guardianInfo.getGuardianRelation() : "N/A");
            if (guardianInfo != null && guardianInfo.getGuardianPhoneNo() != null) {
                row.createCell(23).setCellValue(Long.parseLong(guardianInfo.getGuardianPhoneNo()));
            }
            row.createCell(24).setCellValue(guardianInfo != null ? guardianInfo.getGuardianOccupation() : "N/A");
            if (guardianInfo != null && guardianInfo.getGuardianIncome() != null) {
                row.createCell(25).setCellValue(Long.parseLong(guardianInfo.getGuardianIncome()));
            }
    
            // Bank Details
            BankDetails bankDetails = student.getBankDetails();
            row.createCell(26).setCellValue(bankDetails != null ? bankDetails.getBankName() : "N/A");
            if (bankDetails != null && bankDetails.getAccountNo() != null) {
                row.createCell(27).setCellValue(Long.parseLong(bankDetails.getAccountNo()));
            }
            row.createCell(28).setCellValue(bankDetails != null ? bankDetails.getBankBranch() : "N/A");
            row.createCell(29).setCellValue(bankDetails != null ? bankDetails.getIfscCode() : "N/A");
    
            // Last College Info
            LastCollege lastCollege = student.getLastCollege();
            row.createCell(30).setCellValue(lastCollege != null ? lastCollege.getCollegeName() : "N/A");
            if (lastCollege != null && lastCollege.getRollNo() != null) {
                row.createCell(31).setCellValue(Long.parseLong(lastCollege.getRollNo()));
            }
            if (lastCollege != null && lastCollege.getUdiseNo() != null) {
                row.createCell(32).setCellValue(Long.parseLong(lastCollege.getUdiseNo()));
            }
            if (lastCollege != null && lastCollege.getLastStudentId() != null) {
                row.createCell(33).setCellValue(Long.parseLong(lastCollege.getLastStudentId()));
            }
            row.createCell(34).setCellValue(lastCollege != null ? lastCollege.getExamMonth() : "N/A");
            row.createCell(35).setCellValue(lastCollege != null ? lastCollege.getResult().toString() : "N/A");
            row.createCell(36).setCellValue(lastCollege != null ? lastCollege.getExamination() : "N/A");
            row.createCell(37).setCellValue(lastCollege != null ? lastCollege.getMarksObtained() : 0);
            if (lastCollege != null) {
                if (lastCollege.isAtkt()) {
                    row.createCell(38).setCellValue("yes");
                } else {
                    row.createCell(38).setCellValue("no");
                }
            }
    
            // Payment Info
            PaymentDetails paymentDetails = student.getPaymentDetails();
            row.createCell(39).setCellValue(paymentDetails != null ? paymentDetails.getInstallments() : 0);
            row.createCell(40).setCellValue(paymentDetails != null ? paymentDetails.getInstallmentGap() : 0);
            row.createCell(41).setCellValue(paymentDetails != null ? paymentDetails.getTotalFees() : 0.0);
            row.createCell(42).setCellValue(paymentDetails != null ? paymentDetails.getPaidAmount() : 0.0);
            row.createCell(43).setCellValue(paymentDetails != null ? paymentDetails.getBalanceAmount() : 0.0);
            row.createCell(44).setCellValue(paymentDetails != null ? paymentDetails.getPaymentType().toString() : "N/A");
            row.createCell(45).setCellValue(paymentDetails != null ? paymentDetails.getInstallmentAmount() : 0.0);
            row.createCell(46).setCellValue(paymentDetails != null ? paymentDetails.getDueDate() : "N/A");
    
            // Subjects Info
            Subjects subjects = student.getSubjects();
            row.createCell(47).setCellValue(subjects != null ? subjects.getStream() : "N/A");
            row.createCell(48).setCellValue(subjects != null ? subjects.getSubStream() : "N/A");
    
            StringBuilder subjectsInfo = new StringBuilder();
            List<Subject> subjectList = subjects != null ? subjects.getSubject() : null;
            if (subjectList != null) {
                for (Subject subject : subjectList) {
                    subjectsInfo.append(subject.getName()).append(" (").append(subject.getMedium()).append(")\n");
                }
            }
            row.createCell(49).setCellValue(subjectsInfo.toString());
    
            // BioFocal Info
            BioFocalSubject bioFocalSubject = student.getBioFocalSubject();
            row.createCell(50).setCellValue(bioFocalSubject != null ? bioFocalSubject.getStream() : "N/A");
    
            StringBuilder biosubjectsInfo = new StringBuilder();
            List<Subject> biosubjectList = bioFocalSubject != null ? bioFocalSubject.getSubject() : null;
            if (biosubjectList != null) {
                for (Subject subject : biosubjectList) {
                    biosubjectsInfo.append(subject.getName()).append(" (").append(subject.getMedium()).append(")\n");
                }
            }
            row.createCell(51).setCellValue(biosubjectsInfo.toString());
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
            student.setSection(admission.getSection());
            student.setCurrentClass(admission.getStdClass());
            student.setSession(admission.getSession());
    
            // Map Student Basic Info
            student.setFirstName(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
            student.setFatherName(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
            student.setMotherName(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
            student.setSurname(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
            student.setEmail(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
            student.setPhoneNo(row.getCell(5) != null ? String.valueOf((long) row.getCell(5).getNumericCellValue()) : null);
            student.setDob(row.getCell(6) != null ? row.getCell(6).getStringCellValue() : null);
            student.setAdharNo(row.getCell(7) != null ? String.valueOf((long) row.getCell(7).getNumericCellValue()) : null);
            student.setBloodGroup(row.getCell(8) != null ? row.getCell(8).getStringCellValue() : null);
            student.setResult(row.getCell(9) != null ? Result.valueOf(row.getCell(9).getStringCellValue().toUpperCase()) : null);
            student.setRollNo(row.getCell(10) != null ? String.valueOf((long) row.getCell(10).getNumericCellValue()) : null);
            student.setGender(row.getCell(11) != null ? row.getCell(11).getStringCellValue() : null);
            student.setCaste(row.getCell(12) != null ? row.getCell(12).getStringCellValue() : null);
            student.setCategory(row.getCell(13) != null ? row.getCell(13).getStringCellValue() : null);
            student.setScholarshipCategory(row.getCell(14) != null ? row.getCell(14).getStringCellValue() : null);
            student.setLocalAddress(row.getCell(15) != null ? row.getCell(15).getStringCellValue() : null);
            student.setPermanentAddress(row.getCell(16) != null ? row.getCell(16).getStringCellValue() : null);
            student.setAdmissionForm(admission);
            student = this.studentServiceImpl.addStudent(student);
            

    
            // Map Guardian Info
            // Map Guardian Information
            GuardianInfo guardianInfo = new GuardianInfo();
            guardianInfo.setGuardianName(row.getCell(17) != null ? row.getCell(17).getStringCellValue() : null);
            guardianInfo.setGuardianRelation(row.getCell(18) != null ? row.getCell(18).getStringCellValue() : null);
            guardianInfo.setGuardianPhoneNo(row.getCell(19) != null ? String.valueOf((long) row.getCell(19).getNumericCellValue()) : null);
            guardianInfo.setGuardianOccupation(row.getCell(20) != null ? row.getCell(20).getStringCellValue() : null);
            guardianInfo.setGuardianIncome(row.getCell(21) != null ? String.valueOf((long) row.getCell(21).getNumericCellValue()) : null);
            guardianInfo.setStudent(student);
            this.guardianInfoServiceImpl.addGuardianInfo(guardianInfo);

            // Map Bank Details
            BankDetails bankDetails = new BankDetails();
            bankDetails.setBankName(row.getCell(22) != null ? row.getCell(22).getStringCellValue() : null);
            bankDetails.setAccountNo(row.getCell(23) != null ? String.valueOf((long) row.getCell(23).getNumericCellValue()) : null);
            bankDetails.setBankBranch(row.getCell(24) != null ? row.getCell(24).getStringCellValue() : null);
            bankDetails.setIfscCode(row.getCell(25) != null ? row.getCell(25).getStringCellValue() : null);
            bankDetails.setStudent(student);
            this.bankDetailsServiceImpl.addBankDetails(bankDetails);

    
            // Map Last College Info
           // Map Last College Details
            LastCollege lastCollege = new LastCollege();
            lastCollege.setCollegeName(row.getCell(26) != null ? row.getCell(26).getStringCellValue() : null);
            lastCollege.setRollNo(row.getCell(27) != null ? String.valueOf((long) row.getCell(27).getNumericCellValue()) : null);
            lastCollege.setUdiseNo(row.getCell(28) != null ? String.valueOf((long) row.getCell(28).getNumericCellValue()) : null);
            lastCollege.setLastStudentId(row.getCell(29) != null ? String.valueOf((long) row.getCell(29).getNumericCellValue()) : null);
            lastCollege.setExamMonth(row.getCell(30) != null ? row.getCell(30).getStringCellValue() : null);
            lastCollege.setResult(row.getCell(31) != null ? Result.valueOf(row.getCell(31).getStringCellValue().toUpperCase()) : null);
            lastCollege.setExamination(row.getCell(32) != null ? row.getCell(32).getStringCellValue() : null);
            lastCollege.setMarksObtained(row.getCell(33) != null ? (int) row.getCell(33).getNumericCellValue() : 0);

            if(row.getCell(34) != null) {
                if(row.getCell(34).getStringCellValue().equalsIgnoreCase("no")){
                    lastCollege.setAtkt(false);
                } else if(row.getCell(34).getStringCellValue().equalsIgnoreCase("yes")){
                    lastCollege.setAtkt(true);
                }
            }

            lastCollege.setStudent(student);
            this.lastCollegeServiceImpl.addLastCollege(lastCollege);

            // Map Payment Details
            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setInstallments(row.getCell(35) != null ? (int) row.getCell(35).getNumericCellValue() : 0);
            paymentDetails.setInstallmentGap(row.getCell(36) != null ? (int) row.getCell(36).getNumericCellValue() : 0);
            paymentDetails.setTotalFees(row.getCell(37) != null ? (Double) row.getCell(37).getNumericCellValue() : 0.0);
            paymentDetails.setPaidAmount(row.getCell(38) != null ? (Double) row.getCell(38).getNumericCellValue() : 0.0);
            paymentDetails.setBalanceAmount(row.getCell(39) != null ? (Double) row.getCell(39).getNumericCellValue() : 0.0);
            paymentDetails.setPaymentType(row.getCell(40) != null ? PaymentType.valueOf(row.getCell(40).getStringCellValue().toUpperCase()) : null);
            paymentDetails.setInstallmentAmount(row.getCell(41) != null ? (Double) row.getCell(41).getNumericCellValue() : 0.0);
            paymentDetails.setDueDate(row.getCell(42) != null ? row.getCell(42).getStringCellValue() : null);
            paymentDetails.setStudent(student);
            this.paymentDetailServiceImpl.addPaymentDetails(paymentDetails);

    
            // Map Subjects
            Subjects subjects = new Subjects();
            subjects.setStream(row.getCell(43) != null ? row.getCell(43).getStringCellValue() : null);
            subjects.setSubStream(row.getCell(44) != null ? row.getCell(44).getStringCellValue() : null);
            String[] subjectDetails = row.getCell(45) != null ? row.getCell(45).getStringCellValue().split("\\n") : new String[0];
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
            bioFocalSubject.setStream(row.getCell(46) != null ? row.getCell(46).getStringCellValue() : null);
            String[] bioSubjectDetails = row.getCell(47) != null ? row.getCell(47).getStringCellValue().split("\\n") : new String[0];
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

    public byte[] emptyExcelSheet() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");
    
        // Header Row
        Row headerRow = sheet.createRow(0);
        String[] headers = {
            "First Name", "Father Name", "Mother Name", "Surname", "Email", "Phone No",
            "DOB", "Adhar No", "Blood group", "Result", "Roll No",
            "Gender", "Caste", "Category", "Scholarship Category",
            "Local Address", "Permanent Address",
            "Guardian Name", "Guardian Relation", "Guardian Phone", "Guardian Occupation", "Guardian Income",
            "Bank Name", "Bank Account No", "Bank Branch", "IFSC Code",
            "Last College Name", "Last College Roll No", "U Dise No", "Past Student Id", "Exam month", "Result", "Examination", "Marks Obtained", "ATKT",
            "Installments", "Installment Gap", "Total Fees", "Paid Amount", "Balance Amount", "Payment Type", "Installment Amount",
            "Due Date",
            "Stream", "Substream", "Subjects",
            "BioFocal Stream", "BioFocal Subjects"
        };
    
        // Write header cells
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
    
        // Adding one row of dummy data manually
        Row dataRow = sheet.createRow(1);
    
        // Assigning dummy values with proper numbering
        Cell cell1 = dataRow.createCell(0);  // First Name
        cell1.setCellValue("john");
    
        Cell cell2 = dataRow.createCell(1);  // Father Name
        cell2.setCellValue("marcus");
    
        Cell cell3 = dataRow.createCell(2);  // Mother Name
        cell3.setCellValue("marry");
    
        Cell cell4 = dataRow.createCell(3);  // Surname
        cell4.setCellValue("doe");
    
        Cell cell5 = dataRow.createCell(4);  // Email
        cell5.setCellValue("john@gmail.com");
    
        Cell cell6 = dataRow.createCell(5);  // Phone No
        cell6.setCellValue(1234567890);
    
        Cell cell7 = dataRow.createCell(6);  // DOB
        cell7.setCellValue("2025-01-22");
    
        Cell cell8 = dataRow.createCell(7);  // Adhar No
        cell8.setCellValue(324234234);
    
        Cell cell9 = dataRow.createCell(8);  // Blood Group
        cell9.setCellValue("B+");
    
        Cell cell10 = dataRow.createCell(9);  // Result
        cell10.setCellValue("PASS");
    
        Cell cell11 = dataRow.createCell(10);  // Roll No
        cell11.setCellValue(123);
    
        Cell cell12 = dataRow.createCell(11);  // Gender
        cell12.setCellValue("male");
    
        Cell cell13 = dataRow.createCell(12);  // Caste
        cell13.setCellValue("kunbi");
    
        Cell cell14 = dataRow.createCell(13);  // Category
        cell14.setCellValue("OBC");
    
        Cell cell15 = dataRow.createCell(14);  // Scholarship Category
        cell15.setCellValue("EBC");
    
        Cell cell16 = dataRow.createCell(15);  // Local Address
        cell16.setCellValue("Mumbai");
    
        Cell cell17 = dataRow.createCell(16);  // Permanent Address
        cell17.setCellValue("pune");
    
        Cell cell18 = dataRow.createCell(17);  // Guardian Name
        cell18.setCellValue("marcus");
    
        Cell cell19 = dataRow.createCell(18);  // Guardian Relation
        cell19.setCellValue("father");
    
        Cell cell20 = dataRow.createCell(19);  // Guardian Phone
        cell20.setCellValue(245757877);
    
        Cell cell21 = dataRow.createCell(20);  // Guardian Occupation
        cell21.setCellValue("job");
    
        Cell cell22 = dataRow.createCell(21);  // Guardian Income
        cell22.setCellValue(70000);
    
        Cell cell23 = dataRow.createCell(22);  // Bank Name
        cell23.setCellValue("sbi");
    
        Cell cell24 = dataRow.createCell(23);  // Bank Account No
        cell24.setCellValue(12345678);
    
        Cell cell25 = dataRow.createCell(24);  // Bank Branch
        cell25.setCellValue("wardhman");
    
        Cell cell26 = dataRow.createCell(25);  // IFSC Code
        cell26.setCellValue("CGFR6534");
    
        Cell cell27 = dataRow.createCell(26);  // Last College Name
        cell27.setCellValue("xyz");
    
        Cell cell28 = dataRow.createCell(27);  // Last College Roll No
        cell28.setCellValue(125);
    
        Cell cell29 = dataRow.createCell(28);  // U Dise No
        cell29.setCellValue(222122);
    
        Cell cell30 = dataRow.createCell(29);  // Past Student Id
        cell30.setCellValue(453453);
    
        Cell cell31 = dataRow.createCell(30);  // Exam Month
        cell31.setCellValue("2025-06");
    
        Cell cell32 = dataRow.createCell(31);  // Result
        cell32.setCellValue("FAIL");
    
        Cell cell33 = dataRow.createCell(32);  // Examination
        cell33.setCellValue("cbsc");
    
        Cell cell34 = dataRow.createCell(33);  // Marks Obtained
        cell34.setCellValue(73);
    
        Cell cell35 = dataRow.createCell(34);  // ATKT
        cell35.setCellValue("No");
    
        Cell cell36 = dataRow.createCell(35);  // Installments
        cell36.setCellValue(0);
    
        Cell cell37 = dataRow.createCell(36);  // Installment Gap
        cell37.setCellValue(0);
    
        Cell cell38 = dataRow.createCell(37);  // Total Fees
        cell38.setCellValue(0);
    
        Cell cell39 = dataRow.createCell(38);  // Paid Amount
        cell39.setCellValue(0);
    
        Cell cell40 = dataRow.createCell(39);  // Balance Amount
        cell40.setCellValue(0);
    
        Cell cell41 = dataRow.createCell(40);  // Payment Type
        cell41.setCellValue("cash");
    
        Cell cell42 = dataRow.createCell(41);  // Installment Amount
        cell42.setCellValue(0);
    
        Cell cell43 = dataRow.createCell(42);  // Due Date
        cell43.setCellValue("2025-01-22");
    
        Cell cell44 = dataRow.createCell(43);  // Stream
        cell44.setCellValue("Science");
    
        Cell cell45 = dataRow.createCell(44);  // Substream
        cell45.setCellValue("General Science");
    
        Cell cell46 = dataRow.createCell(45);  // Subjects
        cell46.setCellValue("English (English)\nPhysics (English)\nSanskrit (English)\nChemistry (English)\nBiology (English)");
    
        Cell cell47 = dataRow.createCell(46);  // BioFocal Stream
        cell47.setCellValue("Electronics");
    
        Cell cell48 = dataRow.createCell(47);  // BioFocal Subjects
        cell48.setCellValue("Maths (English)\nChemistry (English)\nPhysics (English)\nEnglish\nMayur(Marathi)");
    
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

