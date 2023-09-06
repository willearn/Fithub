package com.fithub.model.verificationcode;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "verificationcode")
public class VerificationCode {

	@Id
	@Column(name = "EMAIL")
	private String email;
	private String verificationcode;
	private String codeGeneratedate;


}
