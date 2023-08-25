package com.fithub.model.verificationcode;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VerificationCode")
public class VerificationCode {

	@Id
	@Column(name = "Email")
	private String email;
	private String verificationcode;
	private Date codeGeneratedate;

	public VerificationCode() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVerificationcode() {
		return verificationcode;
	}

	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}

	public Date getCodeGeneratedate() {
		return codeGeneratedate;
	}

	public void setCodeGeneratedate(Date codeGeneratedate) {
		this.codeGeneratedate = codeGeneratedate;
	}

}
