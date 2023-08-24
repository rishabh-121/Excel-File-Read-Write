package com.personal.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Employees")
@Data
public class EmployeeData {
	
	public EmployeeData() {
		
	}
	
	@Column(name = "ID")
	@Id
	private long id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Designation")
	private String designation;
	
	@Column(name = "Mobile_Number")
	private String mobNo;
	

}
