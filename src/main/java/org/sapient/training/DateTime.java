package org.sapient.training;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;

public class DateTime {
	LocalDate localDate;
	public DateTime(String date){
		LocalDate localDateNew = LocalDate.parse(date,
	            DateTimeFormatter.ofPattern("dd-LL-yyyy"));
	    //System.out.println(localDateNew);
	    this.localDate = localDateNew;
	}
	public DateTime(){
		LocalDate localDateNew = LocalDate.now();
	    //System.out.println(localDateNew);
	    this.localDate = localDateNew;
	}
	public LocalDate get() {
		return localDate;
	}
	public void set(String date){
		LocalDate localDateNew = LocalDate.parse(date,
	            DateTimeFormatter.ofPattern("dd-LL-yyyy"));
	    //System.out.println(localDateNew);
	    this.localDate = localDateNew;
	}
	public Integer getWeek() {
		return localDate.get( IsoFields.WEEK_OF_WEEK_BASED_YEAR );
	}
	public Integer getYear() {
		return localDate.getYear();
	}
	public Integer getDay() {
		return localDate.getDayOfMonth();
	}
	public Integer getMonth() {
		return localDate.getMonthValue();
	}
	public static String addDates(LocalDate localDate1,LocalDate localDate2 ) {
		LocalDate res = localDate1;
		res = res.plusDays(localDate2.getDayOfMonth());
		res = res.plusMonths(localDate2.getMonthValue());
		res = res.plusYears(localDate2.getYear());
		return res.toString();
	}
	public static String minusDates(LocalDate localDate1,LocalDate localDate2 ) {
		LocalDate res = localDate1;
		res = res.minusDays(localDate2.getDayOfMonth());
		res = res.minusMonths(localDate2.getMonthValue());
		res = res.minusYears(localDate2.getYear());
		return res.toString() + "\nDays -" + ChronoUnit.DAYS.between(localDate2, localDate1)
			+ "\nWeeks - " + ChronoUnit.WEEKS.between(localDate2, localDate1) 
			+ "\nMonths - " + ChronoUnit.MONTHS.between(localDate2, localDate1)
			+ "\nYears - " + ChronoUnit.YEARS.between(localDate2, localDate1);
	}
	
	public String minusNDays(int n) {
		localDate = localDate.minusDays(n);
		return localDate.toString();
	}
	
	public String minusNWeeks(int n) {
		localDate = localDate.minusWeeks(n);
		return localDate.toString();
	}
	
	public String minusNMonths(int n) {
		localDate = localDate.minusMonths(n);
		return localDate.toString();
	}
	
	public String minusNYears(int n) {
		localDate = localDate.minusYears(n);
		return localDate.toString();
	}
	
	public String addNDays(int n) {
		localDate = localDate.plusDays(n);
		return localDate.toString();
	}
	
	public String addNWeeks(int n) {
		localDate = localDate.plusWeeks(n);
		return localDate.toString();
	}
	
	public String addNMonths(int n) {
		localDate = localDate.plusMonths(n);
		return localDate.toString();
	}
	
	public String addNYears(int n) {
		localDate = localDate.plusYears(n);
		return localDate.toString();
	}
	
	
	public String naturalLangDate(String statement) {
		statement = statement.toLowerCase();
		String parseInteger = statement.replaceAll("[^-?0-9]+", " ").trim();
		int n = parseInteger.length()>0 ? Integer.parseInt(parseInteger):-1;
		boolean nDays = n==-1 ? false:true;
		//System.out.println(n);
		if(statement.indexOf("today")>=0||statement.indexOf("now")>=0) {
			if(nDays) {
				if(statement.indexOf("day")>=0) {
					return LocalDate.now().plusDays(n).toString();
				}
				if(statement.indexOf("month")>=0) {
					return LocalDate.now().plusMonths(n).toString();
				}
				if(statement.indexOf("year")>=0) {
					return LocalDate.now().plusYears(n).toString();
				}
				if(statement.indexOf("week")>=0) {
					return LocalDate.now().plusWeeks(n).toString();
				}
			}
			else
				return LocalDate.now().toString();
		}
		else if(statement.indexOf("tomorrow")>=0) {
			if(statement.indexOf("day")>=0 && statement.indexOf("after")>=0) {
				if(nDays)
					return LocalDate.now().plusDays(2).toString();
				else 
					return LocalDate.now().plusDays(n+1).toString();
			}
			else {
				return LocalDate.now().plusDays(1).toString();
			}
		}
		else if(statement.indexOf("yesterday")>=0) {
			if(statement.indexOf("day")>=0 && statement.indexOf("before")>=0) {
				if(nDays)
					return LocalDate.now().minusDays(2).toString();
				else 
					return LocalDate.now().minusDays(n+1).toString();
			}
			else {
				return LocalDate.now().minusDays(1).toString();
			}
		}
		else if(statement.indexOf("eariler")>=0||statement.indexOf("before")>=0||statement.indexOf("previous")>=0) {
			if(nDays) {
				if(statement.indexOf("day")>=0) {
					return localDate.minusDays(n).toString();
				}
				if(statement.indexOf("month")>=0) {
					return localDate.minusMonths(n).toString();
				}
				if(statement.indexOf("year")>=0) {
					return localDate.minusYears(n).toString();
				}
				if(statement.indexOf("week")>=0) {
					return localDate.minusWeeks(n).toString();
				}
			}
			else {
				if(statement.indexOf("day")>=0) {
					return localDate.minusDays(1).toString();
				}
				if(statement.indexOf("month")>=0) {
					return localDate.minusMonths(1).toString();
				}
				if(statement.indexOf("year")>=0) {
					return localDate.minusYears(1).toString();
				}
				if(statement.indexOf("week")>=0) {
					return localDate.minusWeeks(1).toString();
				}
			}
		}
		else if(statement.indexOf("after")>=0||statement.indexOf("next")>=0) {
			if(nDays) {
				if(statement.indexOf("day")>=0) {
					return localDate.plusDays(n).toString();
				}
				if(statement.indexOf("month")>=0) {
					return localDate.plusMonths(n).toString();
				}
				if(statement.indexOf("year")>=0) {
					return localDate.plusYears(n).toString();
				}
				if(statement.indexOf("week")>=0) {
					return localDate.plusWeeks(n).toString();
				}
			}
			else {
				if(statement.indexOf("day")>=0) {
					return localDate.plusDays(1).toString();
				}
				if(statement.indexOf("month")>=0) {
					return localDate.plusMonths(1).toString();
				}
				if(statement.indexOf("year")>=0) {
					return localDate.plusYears(1).toString();
				}
				if(statement.indexOf("week")>=0) {
					return localDate.plusWeeks(1).toString();
				}
			}
		}
		
		return "Couldn't understand the statement";
	}
	
}
