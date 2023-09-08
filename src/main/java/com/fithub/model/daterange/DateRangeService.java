package com.fithub.model.daterange;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateRangeService {

	public DateRange getRangeDate(int monthBefore, int monthAfter) {
		LocalDate today = LocalDate.now();
		LocalDate startDate = today.minusMonths(monthBefore).withDayOfMonth(1);
		LocalDate endDate = today.plusMonths(monthAfter).withDayOfMonth(today.lengthOfMonth());

		return new DateRange(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
				Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}

}
