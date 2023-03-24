package com.ducnguyen.sbkafka.util;

import com.ducnguyen.sbkafka.entities.Employee;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtil {

    public static List<Employee> excelEmployee(InputStream inputStream) {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<Employee> employeeList = new ArrayList<Employee>();
            DataFormatter formatter = new DataFormatter();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Employee employee = new Employee();
                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex) {
                        case 0:
                            employee.setId(currentCell.getNumericCellValue());
                            break;
                        case 1:
                            employee.setName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            employee.setAddress(currentCell.getStringCellValue());
                            break;
                        case 3:
                            employee.setPhone(String.valueOf((currentCell.getNumericCellValue())));
                            break;

                        default:
                            break;
                    }
                    cellIndex++;
                }
                employeeList.add(employee);
            }
            workbook.close();
            return employeeList;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel File" + e.getMessage());
        }
    }

}