package com.fithub.model.daterange;

import java.text.SimpleDateFormat;

public class test {

	public static void main(String[] args) {

		DateRangeService dService = new DateRangeService();

		DateRange dateRange = dService.getRangeDate(1, 2);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println(dateFormat.format(dateRange.getStartDate()));
		System.out.println(dateFormat.format(dateRange.getEndDate()));

	}

}
