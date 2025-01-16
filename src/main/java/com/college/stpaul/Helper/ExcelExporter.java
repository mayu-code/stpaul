package com.college.stpaul.Helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.college.stpaul.entities.Student;

@Component
public class ExcelExporter {
    public byte[] exportToExcel(List<Student> students) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");

        // Header Row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "First Name", "Father Name", "Mother Name", "Email", "Phone No", "DOB", "Roll No", "Class"};
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
            row.createCell(4).setCellValue(student.getEmail());
            row.createCell(5).setCellValue(student.getPhoneNo());
            row.createCell(6).setCellValue(student.getDob());
            row.createCell(7).setCellValue(student.getRollNo());
            row.createCell(8).setCellValue(student.getCurrentClass());
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
