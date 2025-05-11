package com.messapp.messapp.middleware;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;

public class DateRangeUtil {

	    public static Date getStartOfYear(long year) {
	        return Date.valueOf(LocalDate.of((int) year, 1, 1));
	    }

	    public static Date getEndOfYear(long year) {
	        return Date.valueOf(LocalDate.of((int) year, 12, 31));
	    }
	    
	    public static Date getStartOfMonth(long year, int month) {
	        return Date.valueOf(LocalDate.of((int) year, month, 1));
	    }

	    public static Date getEndOfMonth(long year, int month) {
	        YearMonth yearMonth = YearMonth.of((int) year, month);
	        return Date.valueOf(yearMonth.atEndOfMonth());
	    }
	    
	    

}
