package com.personal.project.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personal.project.entity.EmployeeData;
import com.personal.project.repository.EmployeeRepo;

@Service
public class ExcelService {

	@Autowired
	EmployeeRepo employeeRepo;
	public ExcelService() {
		
	}
	
	public void saveEmployeeData(MultipartFile excelFile)
	{
		try {
			List<EmployeeData> employeeDatas=ExcelReadWriteHelper.excelToEmployeeData(excelFile.getInputStream());
			employeeRepo.saveAll(employeeDatas);
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}
	
	public List<EmployeeData> getAllEmployeeDatas()
	{
		return employeeRepo.findAll();
	}

}
