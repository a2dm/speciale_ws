package br.com.a2dm.spdmws.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private DateUtils() {
	}
	
	public static String formatDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	public static String formatDatePtBr(Date date) {
		return formatDate(date, "dd/MM/yyyy");
	}

}
