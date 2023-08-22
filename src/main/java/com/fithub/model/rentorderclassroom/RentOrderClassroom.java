package com.fithub.model.rentorderclassroom;

import com.fithub.model.classroom.Classroom;
import com.fithub.model.rentorder.RentOrder;

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
@Table(name = "RentOrderClassroom")
public class RentOrderClassroom {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rentorderclassroomid")
	private Integer rentorderClassroomId;
	
	private Integer rentorderid;
	private Integer classroomid;
	
	@ManyToOne
	@JoinColumn(name = "rentorderid")
	private RentOrder rentOrder;

	@ManyToOne
	@JoinColumn(name = "classroomid")
	private Classroom classroom;

}
