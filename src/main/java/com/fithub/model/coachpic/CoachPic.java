package com.fithub.model.coachpic;

import com.fithub.model.employee.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CoachPic")
public class CoachPic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cpicid")
	private Integer cpicid;

	@Lob
	@Column(name = "cpicfile")
	private byte[] cpicfile;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeid")
	private Employee employee;

	public CoachPic() {
	}

	public Integer getCpicid() {
		return cpicid;
	}

	public void setCpicid(Integer cpicid) {
		this.cpicid = cpicid;
	}

	public byte[] getCpicfile() {
		return cpicfile;
	}

	public void setCpicfile(byte[] cpicfile) {
		this.cpicfile = cpicfile;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
