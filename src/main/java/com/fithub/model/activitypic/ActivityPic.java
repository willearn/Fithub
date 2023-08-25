package com.fithub.model.activitypic;

import com.fithub.model.activity.Activity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Activitypic")
public class ActivityPic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "apicid")
	private Integer apicid;

	@Column(name = "apicfile")
	private String apicfile;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activityid")
	private Activity activity;

}
