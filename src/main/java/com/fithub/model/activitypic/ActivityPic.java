package com.fithub.model.activitypic;

import com.fithub.model.activity.Activity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Activitypic")
public class ActivityPic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Lob
	@Column(name="photofile")
	private byte[] photofile;
	
	@ManyToOne
	@JoinColumn(name="activityid")
    private Activity activity;
	
	public ActivityPic() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getPhotofile() {
		return photofile;
	}

	public void setPhotofile(byte[] photofile) {
		this.photofile = photofile;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	
	
}

