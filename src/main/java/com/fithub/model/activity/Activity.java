package com.fithub.model.activity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fithub.model.activitypic.ActivityPic;
import com.fithub.model.employee.Employee;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "Activity")
public class Activity {

	// 設定主鍵
	@Id
	@Column(name = "activityid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer activityid;

	private Integer employeeid;

	private String activityname;
	private String activitydescription;
	private String activitydate;
	private String activitydisplay;
	private Date activityon;
	private String activityoff;
	private Integer activitysort;
	private String activitypic;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeid", insertable = false, updatable = false)
	private Employee employee;	
}
