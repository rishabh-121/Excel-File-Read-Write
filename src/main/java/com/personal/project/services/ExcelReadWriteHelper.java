package com.personal.project.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.personal.project.entity.EmployeeData;

public class ExcelReadWriteHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] headers = { "id", "name", "designation", "mobNo" };
	static String SHEET = "Sheet2";

	public ExcelReadWriteHelper() {

	}

	public static boolean hasExcelFormat(MultipartFile file) {
		if (TYPE.equals(file.getContentType())) {
			return true;
		}
		return false;

	}

	public static List<EmployeeData> excelToEmployeeData(InputStream is) {
	
		try {
			Workbook workbook = new XSSFWorkbook(is);
			
			Sheet sheet = workbook.getSheet(SHEET);
			//System.out.println(workbook.getNumberOfSheets());
			//System.out.println(sheet);
			//System.out.println(sheet.getFirstRowNum());
			Iterator<Row> rows = sheet.iterator();

			List<EmployeeData> employeeDatas = new ArrayList<EmployeeData>();
			int rowNum = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				// To skip Header
				if (rowNum == 0) {
					rowNum++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				EmployeeData employeeData = new EmployeeData();
				// employeeData.setId(cellsInRow.next());
				int cellIndx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					
				switch (cellIndx) {
				case 0:
					employeeData.setId((long) currentCell.getNumericCellValue());
					break;
					
				case 1:
					employeeData.setName(currentCell.getStringCellValue());
					break;
					
				case 2:
					employeeData.setDesignation(currentCell.getStringCellValue());
					break;
				case 3:
					employeeData.setMobNo(currentCell.getStringCellValue());
					break;

				default:
					break;
				}
				cellIndx++;
				}
				employeeDatas.add(employeeData);

			}
			workbook.close();
			return employeeDatas;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//System.out.println("e");
			throw new RuntimeException("Fail to parse excel file: "+e.getMessage());
		}

	}
}
