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

    @SuppressWarnings("null")
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
            if (student.getPhoneNo() != null) {
                row.createCell(6).setCellValue(Long.parseLong(student.getPhoneNo()));
            }            
            row.createCell(7).setCellValue(student.getDob());
            if (student.getAdharNo() != null) {
                row.createCell(8).setCellValue(Long.parseLong(student.getAdharNo()));
            }            
            row.createCell(9).setCellValue(student.getBloodGroup());
            row.createCell(10).setCellValue(student.getCurrentClass());
            row.createCell(11).setCellValue(student.getResult().toString());
            if (student.getRollNo() != null) {
                row.createCell(12).setCellValue(Long.parseLong(student.getRollNo()));
            }            
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
            if (guardianInfo != null && guardianInfo.getGuardianPhoneNo() != null) {
                row.createCell(22).setCellValue(Long.parseLong(guardianInfo.getGuardianPhoneNo()));
            }            
            row.createCell(23).setCellValue(guardianInfo != null ? guardianInfo.getGuardianOccupation() : "N/A");
            if (guardianInfo != null && guardianInfo.getGuardianIncome() != null) {
                row.createCell(24).setCellValue(Long.parseLong(guardianInfo.getGuardianIncome()));
            }
            

            // Bank Details
            BankDetails bankDetails = student.getBankDetails();
            row.createCell(25).setCellValue(bankDetails != null ? bankDetails.getBankName() : "N/A");
            if (bankDetails != null && bankDetails.getAccountNo() != null) {
                row.createCell(26).setCellValue(Long.parseLong(bankDetails.getAccountNo()));
            }            
            row.createCell(27).setCellValue(bankDetails != null ? bankDetails.getBankBranch() : "N/A");
            row.createCell(28).setCellValue(bankDetails != null ? bankDetails.getIfscCode() : "N/A");

            // Last College Info
            LastCollege lastCollege = student.getLastCollege();
            row.createCell(29).setCellValue(lastCollege != null ? lastCollege.getCollegeName() : "N/A");
            if (lastCollege != null && lastCollege.getRollNo() != null) {
                row.createCell(30).setCellValue(Long.parseLong(lastCollege.getRollNo()));
            }
            if (lastCollege != null && lastCollege.getUdiseNo() != null) {
                row.createCell(31).setCellValue(Long.parseLong(lastCollege.getUdiseNo()));
            } 
            if (lastCollege != null && lastCollege.getLastStudentId() != null) {
                row.createCell(32).setCellValue(Long.parseLong(lastCollege.getLastStudentId()));
            }
            row.createCell(33).setCellValue(lastCollege != null ? lastCollege.getExamMonth() : "N/A");
            row.createCell(34).setCellValue(lastCollege != null ? lastCollege.getResult().toString() : "N/A");
            row.createCell(35).setCellValue(lastCollege != null ? lastCollege.getExamination() : "N/A");
            row.createCell(36).setCellValue(lastCollege != null ? lastCollege.getMarksObtained() : 0);
            if(lastCollege != null){
                if(lastCollege.isAtkt()){
                    row.createCell(37).setCellValue("yes");
                }else{
                    row.createCell(37).setCellValue("no");
                }
            }

            // Payment Info
            PaymentDetails paymentDetails = student.getPaymentDetails();
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
            student.setFirstName(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
            student.setFatherName(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
            student.setMotherName(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
            student.setSurname(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
            student.setEmail(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
            student.setPhoneNo(row.getCell(5) != null ? String.valueOf((long)row.getCell(5).getNumericCellValue()) : null);
            student.setDob(row.getCell(6) != null ? row.getCell(6).getStringCellValue() : null);
            student.setAdharNo(row.getCell(7) != null ? String.valueOf((long)row.getCell(7).getNumericCellValue()) : null);
            student.setBloodGroup(row.getCell(8) != null ? row.getCell(8).getStringCellValue() : null);
            student.setCurrentClass(row.getCell(9) != null ? row.getCell(9).getStringCellValue() : null);
            student.setResult(row.getCell(10) != null ? Result.valueOf(row.getCell(10).getStringCellValue().toUpperCase()) : null);
            student.setRollNo(row.getCell(11) != null ? String.valueOf((long)row.getCell(11).getNumericCellValue()) : null);
            student.setSession(row.getCell(12) != null ? row.getCell(12).getStringCellValue() : null);
            student.setGender(row.getCell(13) != null ? row.getCell(13).getStringCellValue() : null);
            student.setCaste(row.getCell(14) != null ? row.getCell(14).getStringCellValue() : null);
            student.setCategory(row.getCell(15) != null ? row.getCell(15).getStringCellValue() : null);
            student.setScholarshipCategory(row.getCell(16) != null ? row.getCell(16).getStringCellValue() : null);
            student.setLocalAddress(row.getCell(17) != null ? row.getCell(17).getStringCellValue() : null);
            student.setPermanentAddress(row.getCell(18) != null ? row.getCell(18).getStringCellValue() : null);
            student.setAdmissionForm(admission);
            student = this.studentServiceImpl.addStudent(student);

    
            // Map Guardian Info
            GuardianInfo guardianInfo = new GuardianInfo();
            guardianInfo.setGuardianName(row.getCell(19) != null ? row.getCell(19).getStringCellValue() : null);
            guardianInfo.setGuardianRelation(row.getCell(20) != null ? row.getCell(20).getStringCellValue() : null);
            guardianInfo.setGuardianPhoneNo(row.getCell(21) != null ? String.valueOf((long)row.getCell(21).getNumericCellValue()) : null);
            guardianInfo.setGuardianOccupation(row.getCell(22) != null ? row.getCell(22).getStringCellValue() : null);
            guardianInfo.setGuardianIncome(row.getCell(23) != null ? String.valueOf((long)row.getCell(23).getNumericCellValue()) : null);
            guardianInfo.setStudent(student);
            this.guardianInfoServiceImpl.addGuardianInfo(guardianInfo);
    
            // Map Bank Details
            BankDetails  bankDetails= new BankDetails();
            bankDetails.setBankName(row.getCell(24) != null ? row.getCell(24).getStringCellValue() : null);
            bankDetails.setAccountNo(row.getCell(25) != null ? String.valueOf((long)row.getCell(25).getNumericCellValue()) : null);
            bankDetails.setBankBranch(row.getCell(26) != null ? row.getCell(26).getStringCellValue() : null);
            bankDetails.setIfscCode(row.getCell(27) != null ? row.getCell(27).getStringCellValue() : null);
            bankDetails.setStudent(student);
            this.bankDetailsServiceImpl.addBankDetails(bankDetails);
    
            // Map Last College Info
            LastCollege lastCollege = new LastCollege();
            lastCollege.setCollegeName(row.getCell(28) != null ? row.getCell(28).getStringCellValue() : null);
            lastCollege.setRollNo(row.getCell(29) != null ? String.valueOf((long)row.getCell(29).getNumericCellValue()) : null);
            lastCollege.setUdiseNo(row.getCell(30) != null ?String.valueOf((long)row.getCell(30).getNumericCellValue()) : null);
            lastCollege.setLastStudentId(row.getCell(31) != null ? String.valueOf((long)row.getCell(31).getNumericCellValue()) : null);
            lastCollege.setExamMonth(row.getCell(32) != null ? row.getCell(32).getStringCellValue() : null);
            lastCollege.setResult(row.getCell(33) != null ? Result.valueOf(row.getCell(33).getStringCellValue().toUpperCase()) : null);
            lastCollege.setExamination(row.getCell(34) != null ? row.getCell(34).getStringCellValue() : null);
            lastCollege.setMarksObtained(row.getCell(35) != null ? (int) row.getCell(35).getNumericCellValue() : 0);
            if(row.getCell(36) != null){
                if(row.getCell(36).getStringCellValue().equalsIgnoreCase("no")){
                    lastCollege.setAtkt(false);
                }else if(row.getCell(36).getStringCellValue().equalsIgnoreCase("yes")){
                    lastCollege.setAtkt(true);
                }
            }
            lastCollege.setStudent(student);
            this.lastCollegeServiceImpl.addLastCollege(lastCollege);
    
            // Map Payment Details
            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setInstallments(row.getCell(37) != null ? (int) row.getCell(37).getNumericCellValue() : 0);
            paymentDetails.setInstallmentGap(row.getCell(38) != null ? (int) row.getCell(38).getNumericCellValue() : 0);
            paymentDetails.setTotalFees(row.getCell(39) != null ? (Double) row.getCell(39).getNumericCellValue() : 0.0);
            paymentDetails.setPaidAmount(row.getCell(40) != null ? (Double) row.getCell(40).getNumericCellValue() : 0.0);
            paymentDetails.setBalanceAmount(row.getCell(41) != null ? (Double) row.getCell(41).getNumericCellValue() : 0.0);
            paymentDetails.setPaymentType(row.getCell(42) != null ? PaymentType.valueOf(row.getCell(42).getStringCellValue().toUpperCase()) : null);
            paymentDetails.setInstallmentAmount(row.getCell(43) != null ? (Double) row.getCell(43).getNumericCellValue() : 0.0);
            paymentDetails.setDueDate(row.getCell(44) != null ? row.getCell(44).getStringCellValue() : null);
            paymentDetails.setStudent(student);
            this.paymentDetailServiceImpl.addPaymentDetails(paymentDetails);
    
            // Map Subjects
            Subjects subjects = new Subjects();
            subjects.setStream(row.getCell(45) != null ? row.getCell(45).getStringCellValue() : null);
            subjects.setSubStream(row.getCell(46) != null ? row.getCell(46).getStringCellValue() : null);
            String[] subjectDetails = row.getCell(47) != null ? row.getCell(47).getStringCellValue().split("\\n") : new String[0];
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
            bioFocalSubject.setStream(row.getCell(48) != null ? row.getCell(48).getStringCellValue() : null);
            String[] bioSubjectDetails = row.getCell(49) != null ? row.getCell(49).getStringCellValue().split("\\n") : new String[0];
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
    
        // Write header cells
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
    
        // Adding one row of dummy data manually
        Row dataRow = sheet.createRow(1);
    
        Cell cell1 = dataRow.createCell(0);
        cell1.setCellValue("john");
    
        Cell cell2 = dataRow.createCell(1);
        cell2.setCellValue("marcus");
    
        Cell cell3 = dataRow.createCell(2);
        cell3.setCellValue("marry");
    
        Cell cell4 = dataRow.createCell(3);
        cell4.setCellValue("doe");
    
        Cell cell5 = dataRow.createCell(4);
        cell5.setCellValue("john@gmail.com");
    
        Cell cell6 = dataRow.createCell(5);
        cell6.setCellValue(1234567890);
    
        Cell cell7 = dataRow.createCell(6);
        cell7.setCellValue("2025-01-22");
    
        Cell cell8 = dataRow.createCell(7);
        cell8.setCellValue(324234234);
    
        Cell cell9 = dataRow.createCell(8);
        cell9.setCellValue("B+");
    
        Cell cell10 = dataRow.createCell(9);
        cell10.setCellValue("11th");
    
        Cell cell11 = dataRow.createCell(10);
        cell11.setCellValue("ON_GOING");
    
        Cell cell12 = dataRow.createCell(11);
        cell12.setCellValue(123);
    
        Cell cell13 = dataRow.createCell(12);
        cell13.setCellValue("2025-2026");
    
        Cell cell14 = dataRow.createCell(13);
        cell14.setCellValue("male");
    
        Cell cell15 = dataRow.createCell(14);
        cell15.setCellValue("kunbi");
    
        Cell cell16 = dataRow.createCell(15);
        cell16.setCellValue("OBC");
    
        Cell cell17 = dataRow.createCell(16);
        cell17.setCellValue("EBC");
    
        Cell cell18 = dataRow.createCell(17);
        cell18.setCellValue("Mumbai");
    
        Cell cell19 = dataRow.createCell(18);
        cell19.setCellValue("pune");
    
        Cell cell20 = dataRow.createCell(19);
        cell20.setCellValue("marcus");
    
        Cell cell21 = dataRow.createCell(20);
        cell21.setCellValue("father");
    
        Cell cell22 = dataRow.createCell(21);
        cell22.setCellValue(245757877);
    
        Cell cell23 = dataRow.createCell(22);
        cell23.setCellValue("job");
    
        Cell cell24 = dataRow.createCell(23);
        cell24.setCellValue(70000);
    
        Cell cell25 = dataRow.createCell(24);
        cell25.setCellValue("sbi");
    
        Cell cell26 = dataRow.createCell(25);
        cell26.setCellValue(12345678);
    
        Cell cell27 = dataRow.createCell(26);
        cell27.setCellValue("wardhman");
    
        Cell cell28 = dataRow.createCell(27);
        cell28.setCellValue("CGFR6534");
    
        Cell cell29 = dataRow.createCell(28);
        cell29.setCellValue("xyz");
    
        Cell cell30 = dataRow.createCell(29);
        cell30.setCellValue(125);
    
        Cell cell31 = dataRow.createCell(30);
        cell31.setCellValue(222122);
    
        Cell cell32 = dataRow.createCell(31);
        cell32.setCellValue(453453);
    
        Cell cell33 = dataRow.createCell(32);
        cell33.setCellValue("2025-06");
    
        Cell cell34 = dataRow.createCell(33);
        cell34.setCellValue("FAIL");
    
        Cell cell35 = dataRow.createCell(34);
        cell35.setCellValue("cbsc");
    
        Cell cell36 = dataRow.createCell(35);
        cell36.setCellValue(73);
    
        Cell cell37 = dataRow.createCell(36);
        cell37.setCellValue("No");
    
        Cell cell38 = dataRow.createCell(37);
        cell38.setCellValue(0);
    
        Cell cell39 = dataRow.createCell(38);
        cell39.setCellValue(0);
    
        Cell cell40 = dataRow.createCell(39);
        cell40.setCellValue(0);
    
        Cell cell41 = dataRow.createCell(40);
        cell41.setCellValue(0);
    
        Cell cell42 = dataRow.createCell(41);
        cell42.setCellValue(0);
    
        Cell cell43 = dataRow.createCell(42);
        cell43.setCellValue("cash");
    
        Cell cell44 = dataRow.createCell(43);
        cell44.setCellValue(0);
    
        Cell cell45 = dataRow.createCell(44);
        cell45.setCellValue("2025-01-22");
    
        Cell cell46 = dataRow.createCell(45);
        cell46.setCellValue("Science");
    
        Cell cell47 = dataRow.createCell(46);
        cell47.setCellValue("General Science");
    
        Cell cell48 = dataRow.createCell(47);
        cell48.setCellValue("English (English)\nPhysics (English)\nSanskrit (English)\nChemistry (English)\nBiology (English)");
    
        Cell cell49 = dataRow.createCell(48);
        cell49.setCellValue("Electronics");
    
        Cell cell50 = dataRow.createCell(49);
        cell50.setCellValue("Maths (English)\nChemistry (English)\nPhysics (English)\nEnglish\nMayur(Marathi)");
    
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

