package com.tsms.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	private static final String TIME_FORMAT = "HH:mm:ss";
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final String DATE_FORMAT3 = "yyyy-MM-dd";
	public static final String DATE_FORMAT_ACCEPT = "dd-mm-yy";
	public static final String DATE_FORMAT_FOR_REPORT = "yyyy-mm-dd";

	private static final String DATE_FORMAT1 = "dd/MM/yyyy HH:mm:ss";
	private static final String DATE_FORMAT2 = "yyyy-MM-dd HH:mm:ss";
	
	public static String convertOnestringFtoAnother(String dateaccepted) {
		String dateInString = "";
		try {

			Date date = new SimpleDateFormat(DATE_FORMAT_ACCEPT).parse(dateaccepted);
			dateInString = new SimpleDateFormat(DATE_FORMAT_FOR_REPORT).format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateInString;
	}
	
	public static String formatDate(String date) {
		String selectdate = "";
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date dates = (Date) formatter.parse(date);
			SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy");
			selectdate = newFormat.format(dates);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectdate;
	}

	
	public static String formatDateForSystemGeneratedDate() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy-HH:mm");
		Date d = new Date(System.currentTimeMillis());
		return sdf.format(d);
	}
	

	public static Date getCurrentDateTime() throws ParseException {
		LocalDateTime currentdatetime = LocalDateTime.now();

		// Convert LocalDateTime to a java.util.Date
		Date utilDate = Date.from(currentdatetime.atZone(ZoneId.systemDefault()).toInstant());

		// Format the Date as a string
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String formattedDate = formatter.format(utilDate);

		return utilDate;
	}
	
	public static String formatDate(Date date, String format) {
		String formatedDate = null;
		try {
			if (date != null)
				formatedDate = new SimpleDateFormat(format).format(date);
			else
				formatedDate = "";
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return formatedDate;
	}

	public static Date getCurrentDate() {
		Date currentDate = null;
		try {
			currentDate = new Date(Calendar.getInstance().getTimeInMillis());
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return currentDate;
	}

	public static Date getTime(String time) {
		Date date = null;
		try {
			date = new Date(new SimpleDateFormat("HH:mm:ss").parse(time).getTime());
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return date;
	}

	public static Date parseDate(String dateInString) {
		Date date = null;
		try {
			if (dateInString != null && dateInString.trim().length() > 0) {
				date = new SimpleDateFormat(DATE_FORMAT).parse(dateInString);
			}

		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return date;
	}
	
	public static String formatDate(Date date) {
		String formatedDate = null;
		try {
			if (date != null)
				formatedDate = new SimpleDateFormat(DATE_FORMAT).format(date);
			else
				formatedDate = "";
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return formatedDate;
	}

	public static String formatTime(Date date) {
		String formatedTime = null;
		try {
			if (date != null)	
				formatedTime = new SimpleDateFormat(TIME_FORMAT).format(date);
			else
				formatedTime = "";
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return formatedTime;
	}

	public static Date getDateBefore(int noOfDays) {
		Date date = null;
		Calendar calendar = null;
		try {
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -noOfDays);
			date = calendar.getTime();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return date;
	}
	
	public static Date getDateBefore(int noOfDays,Date dateBefore) {
		Date date = null;
		Calendar calendar = null;
		try {
			calendar = Calendar.getInstance();
			calendar.setTime(dateBefore);
			calendar.add(Calendar.DATE, -noOfDays);
			date = calendar.getTime();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return date;
	}

	public static Date getDateAfter(int noOfDays) {
		Date date = null;
		Calendar calendar = null;
		try {
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, noOfDays);
			date = calendar.getTime();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return date;
	}

	public static Date getDateBeforeTime(int mins) {
		Date date = null;
		long miliseconds = 0;
		try {
			miliseconds = Calendar.getInstance().getTimeInMillis() - (mins * 60 * 1000);
			date = new Date(miliseconds);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return date;
	}
	
	public static Date getDateAfterTime(int mins) {
		Date date = null;
		long miliseconds = 0;
		try {
			miliseconds = Calendar.getInstance().getTimeInMillis() + (mins * 60 * 1000);
			date = new Date(miliseconds);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return date;
	}

	public static String formatDate1(String dates) {
		 String outputDate = null;
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-M-dd");
			Date date = inputFormat.parse(dates);
			outputDate = outputFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputDate;
	}
	

}
