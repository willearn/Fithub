package com.fithub.model.announcement;

import com.fithub.model.employee.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Announcement")
public class Announcement {

	// 設定主鍵
	@Id
	@Column(name = "annid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer annid;

	private Integer employeeid;
	private String annname;
	private String anndescroption;
	
	@Lob
	@Column(name = "annimg")
	private byte[] annimg;

	private String anndate;
	private String anndisplay;
	private String annon;
	private String annoff;
	private Integer annsort;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeid", insertable = false, updatable = false)
	private Employee employee;

	public Announcement() {
	}
	
	public Integer getAnnid() {
		return annid;
	}

	public void setAnnid(Integer annid) {
		this.annid = annid;
	}

	public Integer getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	public String getAnnname() {
		return annname;
	}

	public void setAnnname(String annname) {
		this.annname = annname;
	}

	public String getAnndescroption() {
		return anndescroption;
	}

	public void setAnndescroption(String anndescroption) {
		this.anndescroption = anndescroption;
	}

	public byte[] getAnnimg() {
		return annimg;
	}

	public void setAnnimg(byte[] annimg) {
		this.annimg = annimg;
	}

	public String getAnndate() {
		return anndate;
	}

	public void setAnndate(String anndate) {
		this.anndate = anndate;
	}

	public String getAnndisplay() {
		return anndisplay;
	}

	public void setAnndisplay(String anndisplay) {
		this.anndisplay = anndisplay;
	}

	public String getAnnon() {
		return annon;
	}

	public void setAnnon(String annon) {
		this.annon = annon;
	}

	public String getAnnoff() {
		return annoff;
	}

	public void setAnnoff(String annoff) {
		this.annoff = annoff;
	}

	public Integer getAnnsort() {
		return annsort;
	}

	public void setAnnsort(Integer annsort) {
		this.annsort = annsort;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
