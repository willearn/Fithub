package com.fithub.model.employee;

import com.fithub.model.department.Department;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity @Table(name="employee")
public class Employee {

	@Id @Column(name="EMPLOYEEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeid;
	
	private String employeename;
	private String employeeemail;
	private String employeephone;
	private String employeegender;
	private String employeecity;
	private String employeezone;
	private String employeeaddress;
	
	
	@Column(name="DEPTID")
	private int deptid;
	private String employeetitle;
	private int manager;
	private String hiredate;
	private String resigndate;
	private int salary;
	private String employeebirthday;
	private String employeeintroduction;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="DEPTID",insertable = false,updatable = false)
	private Department department;

	

	public Employee() {
	}

	public Employee(String employeename, String employeeemail, String employeephone, String employeegender,
			String employeecity, String employeezone, String employeeaddress, int deptid, String employeetitle,
			int manager, String hiredate, String resigndate, int salary, String employeebirthday,
			String employeeintroduction) {
		this.employeename = employeename;
		this.employeeemail = employeeemail;
		this.employeephone = employeephone;
		this.employeegender = employeegender;
		this.employeecity = employeecity;
		this.employeezone = employeezone;
		this.employeeaddress = employeeaddress;
		this.deptid = deptid;
		this.employeetitle = employeetitle;
		this.manager = manager;
		this.hiredate = hiredate;
		this.resigndate = resigndate;
		this.salary = salary;
		this.employeebirthday = employeebirthday;
		this.employeeintroduction = employeeintroduction;
	}


	public int getEmployeeid() {
		return employeeid;
	}


	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}


	public String getEmployeename() {
		return employeename;
	}


	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}


	public String getEmployeeemail() {
		return employeeemail;
	}


	public void setEmployeeemail(String employeeemail) {
		this.employeeemail = employeeemail;
	}


	public String getEmployeephone() {
		return employeephone;
	}


	public void setEmployeephone(String employeephone) {
		this.employeephone = employeephone;
	}


	public String getEmployeegender() {
		return employeegender;
	}


	public void setEmployeegender(String employeegender) {
		this.employeegender = employeegender;
	}


	public String getEmployeecity() {
		return employeecity;
	}


	public void setEmployeecity(String employeecity) {
		this.employeecity = employeecity;
	}


	public String getEmployeezone() {
		return employeezone;
	}


	public void setEmployeezone(String employeezone) {
		this.employeezone = employeezone;
	}


	public String getEmployeeaddress() {
		return employeeaddress;
	}


	public void setEmployeeaddress(String employeeaddress) {
		this.employeeaddress = employeeaddress;
	}


	public int getDeptid() {
		return deptid;
	}


	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}


	public String getEmployeetitle() {
		return employeetitle;
	}


	public void setEmployeetitle(String employeetitle) {
		this.employeetitle = employeetitle;
	}


	public int getManager() {
		return manager;
	}


	public void setManager(int manager) {
		this.manager = manager;
	}


	public String getHiredate() {
		return hiredate;
	}


	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}


	public String getResigndate() {
		return resigndate;
	}


	public void setResigndate(String resigndate) {
		this.resigndate = resigndate;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public String getEmployeebirthday() {
		return employeebirthday;
	}


	public void setEmployeebirthday(String employeebirthday) {
		this.employeebirthday = employeebirthday;
	}


	public String getEmployeeintroduction() {
		return employeeintroduction;
	}


	public void setEmployeeintroduction(String employeeintroduction) {
		this.employeeintroduction = employeeintroduction;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
//	public BackStageAccount getBackstageaccount() {
//		return backstageaccount;
//	}
//
//	public void setBackstageaccount(BackStageAccount backstageaccount) {
//		this.backstageaccount = backstageaccount;
//	}


}
