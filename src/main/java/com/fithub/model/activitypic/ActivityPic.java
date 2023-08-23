package com.fithub.model.activitypic;

import com.fithub.model.activity.Activity;

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
@Table(name = "Activitypic")
public class ActivityPic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "apicid")
	private Integer apicid;

	@Lob
	@Column(name = "apicfile")
	private byte[] apicfile;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activityid")
	private Activity activity;

	public ActivityPic() {

	}

	public Integer getApicid() {
		return apicid;
	}

	public void setApicid(Integer apicid) {
		this.apicid = apicid;
	}

	public byte[] getApicfile() {
		return apicfile;
	}

	public void setApicfile(byte[] apicfile) {
		this.apicfile = apicfile;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}
