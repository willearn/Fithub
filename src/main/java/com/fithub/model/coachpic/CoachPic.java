package com.fithub.model.coachpic;

import com.fithub.model.coachspecialty.CoachSpecialty;
import com.fithub.model.employee.Employee;
import com.fithub.model.specialty.Specialty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="coachpic")
public class CoachPic {

	@Id
	@Column(name="CPICID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cpicid;
	
	@Column(name="EMPLOYEEID")
	private Integer employeeid;
	
	private byte[] cpicfile;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEEID",insertable = false,updatable = false)
	private Employee employee;
}
