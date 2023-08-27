package com.fithub.model.imagejson;

import lombok.Data;

@Data
public class ImageJson {
	private String imageName;
	private String base64Image;
	
	public ImageJson(String imageName, String base64Image) {
		this.imageName = imageName;
		this.base64Image = base64Image;
	}
	
}
