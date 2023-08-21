package com.fithub.model.backstageaccount;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fithub.model.employee.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity @Table(name="backstageaccount")
@Component
public class BackStageAccount {
	
	
//	@GenericGenerator(name="generator",strategy="foreign",parameters=@Parameter(name="property",value="employee"))
//	@GeneratedValue(generator="generator")
	@Column(name="EMPLOYEEID")
	private int employeeid;
	
	@Id @Column(name="EMPLOYEEACCOUNT")
	private String employeeaccount;
	private String employeepassword;
	private int loa;
	
	//屬性不需要持久化（不需要存儲到數據庫）
//	@Transient
//	private String employeename;
	
	@OneToOne
	@JoinColumn(name = "EMPLOYEEID", insertable = false, updatable = false)
	private Employee employee;

	public BackStageAccount() {
	}

	public BackStageAccount(int employeeid,String employeeaccount, String employeepassword, int loa) {
		this.employeeid = employeeid;
		this.employeeaccount = employeeaccount;
		this.employeepassword = employeepassword;
		this.loa = loa;
	}

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public String getEmployeeaccount() {
		return employeeaccount;
	}

	public void setEmployeeaccount(String employeeaccount) {
		this.employeeaccount = employeeaccount;
	}

	public String getEmployeepassword() {
		return employeepassword;
	}

	public void setEmployeepassword(String employeepassword) {
		this.employeepassword = employeepassword;
	}

	public int getLoa() {
		return loa;
	}

	public void setLoa(int loa) {
		this.loa = loa;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

//	public String getEmployeename() {
//		return employeename;
//	}
//
//	public void setEmployeename(String employeename) {
//		this.employeename = employeename;
//	}
	
	

}
