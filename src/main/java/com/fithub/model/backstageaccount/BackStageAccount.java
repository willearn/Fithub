package com.fithub.model.backstageaccount;



import com.fithub.model.employee.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

//@Component
@Data
@Entity @Table(name="backstageaccount")
public class BackStageAccount {
	
	
//	@GenericGenerator(name="generator",strategy="foreign",parameters=@Parameter(name="property",value="employee"))
//	@GeneratedValue(generator="generator")
	@Column(name="EMPLOYEEID")
	private Integer employeeid;
	
	@Id @Column(name="EMPLOYEEACCOUNT")
	private String employeeaccount;
	private String employeepassword;
	private int loa;
	
	//屬性不需要持久化（不需要存儲到數據庫）
//	@Transient
//	private String employeename;
	
	@OneToOne
	@JoinColumn(name = "EMPLOYEEID", insertable = false, updatable = false)
	private Employee employee;
	

}
