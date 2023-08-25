package com.fithub.model.coursecategories;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fithub.model.course.Course;

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
@Table(name="coursecategories")
public class CourseCategories {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="categoryId")
	private int categoryId;

	@Column(name="categoryName")
	private String categoryName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "courseCategories")
	private Set<Course> courses = new LinkedHashSet<Course>();

}
