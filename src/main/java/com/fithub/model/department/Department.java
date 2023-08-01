package com.fithub.model.department;

import java.util.HashSet;
import java.util.Set;

import com.fithub.model.employee.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity @Table(name="department")
public class Department {

	@Id @Column(name="DEPTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deptid;
	private String deptname;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "department")
	private Set<Employee> employee = new HashSet<Employee>();
	
	public Department() {
	}

	public Department(String deptname) {
		this.deptname = deptname;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}

}
