package com.fithub.model.companydata;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "CompanyData")
public class CompanyData {

	// 設定主鍵
	@Id
	@Column(name = "companyid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer companyid;
	

	private String companyname;

	@Lob
	@Column(name = "logoimg")
	private byte[] logoimg;


	private String companymap;
	private String companyphone;
	private String companycity;
	private String companyzone;
	private String companyaddress;
	private String companyemail;
	private String officerhour;
	private String vatnumber;
	private String companysince;
	private String companyabout;
	private String companyintroduction;

	public CompanyData() {
	}
	
	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public byte[] getLogoimg() {
		return logoimg;
	}

	public void setLogoimg(byte[] logoimg) {
		this.logoimg = logoimg;
	}

	public String getCompanymap() {
		return companymap;
	}

	public void setCompanymap(String companymap) {
		this.companymap = companymap;
	}

	public String getCompanyphone() {
		return companyphone;
	}

	public void setCompanyphone(String companyphone) {
		this.companyphone = companyphone;
	}

	public String getCompanycity() {
		return companycity;
	}

	public void setCompanycity(String companycity) {
		this.companycity = companycity;
	}

	public String getCompanyzone() {
		return companyzone;
	}

	public void setCompanyzone(String companyzone) {
		this.companyzone = companyzone;
	}

	public String getCompanyaddress() {
		return companyaddress;
	}

	public void setCompanyaddress(String companyaddress) {
		this.companyaddress = companyaddress;
	}

	public String getCompanyemail() {
		return companyemail;
	}

	public void setCompanyemail(String companyemail) {
		this.companyemail = companyemail;
	}

	public String getOfficerhour() {
		return officerhour;
	}

	public void setOfficerhour(String officerhour) {
		this.officerhour = officerhour;
	}

	public String getVatnumber() {
		return vatnumber;
	}

	public void setVatnumber(String vatnumber) {
		this.vatnumber = vatnumber;
	}

	public String getCompanysince() {
		return companysince;
	}

	public void setCompanysince(String companysince) {
		this.companysince = companysince;
	}

	public String getCompanyabout() {
		return companyabout;
	}

	public void setCompanyabout(String companyabout) {
		this.companyabout = companyabout;
	}

	public String getCompanyintroduction() {
		return companyintroduction;
	}

	public void setCompanyintroduction(String companyintroduction) {
		this.companyintroduction = companyintroduction;
	}

}
