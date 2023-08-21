package com.fithub.model.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fithub.model.rentorder.RentOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity @Table(name = "member")
public class Member {

	@Id  @Column(name="memberid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberid;
	private String memberphoneno;
	private String memberpassword;
	private String membername;
	private String membergender;
	private String memberemail;
	private String membercity;
	private String memberzone;
	private String memberaddress;
	private Date memberbirthday;
	private Date memberaccountsince;
	
	@OneToMany(mappedBy = "member")
	private List<RentOrder> rentOrders = new ArrayList<>();
	
	public List<RentOrder> getRentOrders() {
		return rentOrders;
	}

	public void setRentOrders(List<RentOrder> rentOrders) {
		this.rentOrders = rentOrders;
	}

	public Member() {
		
	}
	
	public Integer getMemberid() {
		return memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	public String getMemberphoneno() {
		return memberphoneno;
	}

	public void setMemberphoneno(String memberphoneno) {
		this.memberphoneno = memberphoneno;
	}

	public String getMemberpassword() {
		return memberpassword;
	}

	public void setMemberpassword(String memberpassword) {
		this.memberpassword = memberpassword;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getMembergender() {
		return membergender;
	}

	public void setMembergender(String membergender) {
		this.membergender = membergender;
	}

	public String getMemberemail() {
		return memberemail;
	}

	public void setMemberemail(String memberemail) {
		this.memberemail = memberemail;
	}

	public String getMembercity() {
		return membercity;
	}

	public void setMembercity(String membercity) {
		this.membercity = membercity;
	}

	public String getMemberzone() {
		return memberzone;
	}

	public void setMemberzone(String memberzone) {
		this.memberzone = memberzone;
	}

	public String getMemberaddress() {
		return memberaddress;
	}

	public void setMemberaddress(String memberaddress) {
		this.memberaddress = memberaddress;
	}

	public Date getMemberbirthday() {
		return memberbirthday;
	}

	public void setMemberbirthday(Date memberbirthday) {
		this.memberbirthday = memberbirthday;
	}

	public Date getMemberaccountsince() {
		return memberaccountsince;
	}

	public void setMemberaccountsince(Date memberaccountsince) {
		this.memberaccountsince = memberaccountsince;
	}
}
