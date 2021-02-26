package br.com.a2dm.spdmws.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private DateUtils() {
	}
	
	public static String formatDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	public static Date parseDate(String date, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(date);			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String formatDatePtBr(Date date) {
		return formatDate(date, "dd/MM/yyyy");
	}
	
	public static Date parseDatePtBr(String date) {
		return parseDate(date, "dd/MM/yyyy");
	}

}
