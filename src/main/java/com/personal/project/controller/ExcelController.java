package com.personal.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.personal.project.entity.EmployeeData;
import com.personal.project.message.ResponseMessage;
import com.personal.project.services.ExcelReadWriteHelper;
import com.personal.project.services.ExcelService;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

	@Autowired
	ExcelService excelService;

	public ExcelController() {

	}

	@PostMapping("/uploadData")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		if (ExcelReadWriteHelper.hasExcelFormat(file)) {

			try {
				excelService.saveEmployeeData(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				e.printStackTrace();
				message = "Could not upload the file: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "Please upload an excel file only";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	@GetMapping("/fetchEmployees")
	public ResponseEntity<List<EmployeeData>> getAllEmployees() {
		try {
			List<EmployeeData> employeeDatas = excelService.getAllEmployeeDatas();
			if (employeeDatas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(employeeDatas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
