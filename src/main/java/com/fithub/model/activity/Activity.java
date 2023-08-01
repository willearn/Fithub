package com.fithub.model.activity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fithub.model.employee.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


//資料庫對應的table
@Entity @Table(name="activity")
@JsonIgnoreProperties("employee")
public class Activity {
	
	//設定主鍵
	@Id @Column(name="activityid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer activityid;
	
	private Integer employeeid;
	
	private String activityname;
	private String activitydescription;
	private String activitydate;
	private String activityurl;
	private String activitydisplay;
	private String activityon;
	private String activityoff;
	private Integer activitysort;
	 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="employeeid",insertable = false,updatable = false)
	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Activity() {
	}

	//提供參數建構子,減少代碼
	public Activity(String activityname, String activitydescription, String activitydate, String activityurl,
			String activitydisplay,int employeeid, String activityon, String activityoff, int activitysort) {	
		super();
		this.activityname = activityname;
		this.activitydescription = activitydescription;
		this.activitydate = activitydate;
		this.activityurl = activityurl;
		this.activitydisplay = activitydisplay;
		this.employeeid = employeeid;
		this.activityon = activityon;
		this.activityoff = activityoff;
		this.activitysort = activitysort;
	}

	public int getActivityid() {
		return activityid;
	}

	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	public String getActivitydescription() {
		return activitydescription;
	}

	public void setActivitydescription(String activitydescription) {
		this.activitydescription = activitydescription;
	}

	public String getActivitydate() {
		return activitydate;
	}

	public void setActivitydate(String activitydate) {
		this.activitydate = activitydate;
	}

	public String getActivityurl() {
		return activityurl;
	}

	public void setActivityurl(String activityurl) {
		this.activityurl = activityurl;
	}

	public String getActivitydisplay() {
		return activitydisplay;
	}

	public void setActivitydisplay(String activitydisplay) {
		this.activitydisplay = activitydisplay;
	}

	public String getActivityon() {
		return activityon;
	}

	public void setActivityon(String activityon) {
		this.activityon = activityon;
	}

	public String getActivityoff() {
		return activityoff;
	}

	public void setActivityoff(String activityoff) {
		this.activityoff = activityoff;
	}

	public int getActivitysort() {
		return activitysort;
	}

	public void setActivitysort(int activitysort) {
		this.activitysort = activitysort;
	}

	
}
