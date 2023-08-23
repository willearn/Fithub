package com.fithub.model.specialty;

import java.util.HashSet;
import java.util.Set;

import com.fithub.model.coachspecialty.CoachSpecialty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity 
@Table(name="specialty")
public class Specialty {

	@Id
	@Column(name="SPECIALTYID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer specialtyid;
	
	private String specialtyname;
	

}
