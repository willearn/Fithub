package com.fithub.model.jobtitle;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fithub.model.employee.Employee;

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
@Table(name="jobtitle")
public class JobTitle {

	@Id @Column(name = "JOBTITLEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jobtitleid;
	private String jobtitlename;
	
	@OneToMany(mappedBy = "jobtitle")
	@JsonBackReference
	private Set<Employee> employee = new HashSet<Employee>();
}
