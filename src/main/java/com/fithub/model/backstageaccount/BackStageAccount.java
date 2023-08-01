package com.fithub.model.backstageaccount;

import com.fithub.model.employee.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity @Table(name="backstageaccount")
public class BackStageAccount {
	
	
//	@GenericGenerator(name="generator",strategy="foreign",parameters=@Parameter(name="property",value="employee"))
//	@GeneratedValue(generator="generator")
	@Column(name="EMPLOYEEID", insertable = false, updatable = false)
	private int employeeid;
	
	@Id @Column(name="EMPLOYEEACCOUNT")
	private String employeeaccount;
	private String employeepassword;
	private int loa;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEEID")
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

}
