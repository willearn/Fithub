package com.fithub.model.coachspecialty;

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
@Table(name="coachspecialty")
public class CoachSpecialty {

	@Id
	@Column(name="COACHSPECIALTYID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer coachspecialtyid;
	
	@Column(name="EMPLOYEEID")
	private Integer employeeid;
	
	@Column(name="SPECIALTYID")
	private Integer specialtyid;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEEID",insertable = false,updatable = false)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="SPECIALTYID",insertable = false,updatable = false)
	private Specialty specialty;
	
}
