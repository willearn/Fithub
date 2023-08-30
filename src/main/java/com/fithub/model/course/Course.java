package com.fithub.model.course;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fithub.model.classes.Classes;
import com.fithub.model.coursecategories.CourseCategories;

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
import lombok.Data;

@Data
@Entity
@Table(name="course")
public class Course {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="courseid")
	private int courseId;

	@Column(name="coursename")
	private String courseName;

	@Column(name="categoryid")
	private int categoryId;

	@Column(name="courseimgpath")
	private String courseImgPath;

	@Column(name="coursedescription")
	private String courseDescription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CATEGORYID",insertable = false,updatable = false)
	private CourseCategories courseCategories;
	
	@JsonIgnore //等下刪
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	private Set<Classes> classes=new LinkedHashSet<Classes>();	


}
