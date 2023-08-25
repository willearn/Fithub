package com.fithub.model.announcement;

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
import lombok.Data;

@Data
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
	
	
	@Column(name = "annimg")
	private String annimg;

	private String anndate;
	private String anndisplay;
	private String annon;
	private String annoff;
	private Integer annsort;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeid", insertable = false, updatable = false)
	private Employee employee;

}
