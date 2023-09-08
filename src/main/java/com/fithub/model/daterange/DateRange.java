package com.fithub.model.daterange;

import java.util.Date;

import lombok.Data;

@Data
public class DateRange {
	private Date startDate;
	private Date endDate;

	public DateRange() {
	}

	public DateRange(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
