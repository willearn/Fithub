package com.fithub.model.employee;

import com.fithub.model.department.Department;
import com.fithub.model.jobtitle.JobTitle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


//@Component
@Data
@Entity @Table(name="employee")
public class Employee {

	@Id @Column(name="EMPLOYEEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeid;
	
	private String employeename;
	private String employeeemail;
	private String employeephone;
	private String employeegender;
	private String employeecity;
	private String employeezone;
	private String employeeaddress;
	
	
	@Column(name="DEPTID")
	private int deptid;
	
	@Column(name="JOBTITLEID")
	private String jobtitleid;
	private Integer managerid;
	private String hiredate;
	private String resigndate;
	private Integer salary;
	private String employeebirthday;
	private String employeeintroduction;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name="DEPTID",insertable = false,updatable = false)
//	@JsonBackReference
	private Department department;
	
	@ManyToOne
	@JoinColumn(name = "JOBTITLEID",insertable = false,updatable = false)
	private JobTitle jobtitle;
	
//	@OneToOne(fetch = FetchType.LAZY)
//	private BackStageAccount backstageaccount;
	


}
