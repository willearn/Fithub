package com.fithub.model.linepay;

import lombok.Data;

@Data
public class RedirectUrls {
	private String appPackageName;
	private String confirmUrl;
	private String cancelUrl;
}
