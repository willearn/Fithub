package com.fithub.model.department;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fithub.model.employee.Employee;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity 
@Table(name="department")
public class Department {

	@Id @Column(name="DEPTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deptid;
	private String deptname;
	
	
//	@OneToMany(mappedBy = "department")
	@OneToMany(mappedBy = "department")
	@JsonBackReference
	private Set<Employee> employee = new HashSet<Employee>();
	

}
