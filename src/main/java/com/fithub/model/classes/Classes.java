package com.fithub.model.classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fithub.model.cart.Cart;
import com.fithub.model.classesset.ClassesSet;
import com.fithub.model.classroom.Classroom;
import com.fithub.model.course.Course;
import com.fithub.model.employee.Employee;
import com.fithub.model.order.Order;
import com.fithub.model.wishlist.Wishlist;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "classes")
public class Classes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "classid")
	private int classId;

	@Column(name = "COURSEID")
	private int courseId;

	@Column(name = "classdate")
	private String classDate;

	@Column(name = "classtime")
	private String classTime;

	@Column(name = "coachsubstitute")
	private int coachSubstitute;

	@Column(name = "EMPLOYEEID")
	private int employeeId;

	@Column(name = "applicantsceil")
	private int applicantsCeil;

	@Column(name = "applicantsfloor")
	private int applicantsFloor;

	@Column(name = "price")
	private int price;

	@Column(name = "CLASSROOMID")
	private int classroomId;

	@JsonIgnoreProperties({ "classes","courseDescription" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COURSEID", insertable = false, updatable = false)
	private Course course;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEEID", insertable = false, updatable = false)
	private Employee employee;

	@JsonIgnoreProperties({ "classes","classroomPic" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLASSROOMID", insertable = false, updatable = false)
	private Classroom classroom;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classes")
	private Set<Wishlist> wishlist = new HashSet<Wishlist>();

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classes")
	private Set<Cart> cart = new HashSet<Cart>();

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "OrderItem", joinColumns = @JoinColumn(name = "classid"), inverseJoinColumns = @JoinColumn(name = "orderid"))
	private List<Order> order = new ArrayList<Order>();

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ClassesSetItem", joinColumns = @JoinColumn(name = "classid"), inverseJoinColumns = @JoinColumn(name = "classessetid"))
	private List<ClassesSet> classesSet = new ArrayList<ClassesSet>();

}
