package string.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.*;
import java.util.stream.Collectors;


import java.time.format.DateTimeFormatter;

public class SQLQueryMaker {
	
	private static final String PASSWORD_PATTERN = "^([0-9]+)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-[{}]:;',?/*~$^+=<>]).{5,20}$";
	private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
	private static final String commonDate = "^([0-9]+){1,4}([/|-])([0-9]+){1,2}([/|-])([0-9]+){1,4}$";
	private static final String date = "((?:19|20)[0-9][0-9])-*/*(0?[1-9]|1[012])-*/*(0?[1-9]|[12][0-9]|3[01])";
	private static final String date1 = "(((0?[1-9]|[12][0-9]|3[01])-*/*(0?[1-9]|1[012])-*/*(?:19|20)[0-9][0-9]))";
	private static final String date2 = "(((0?[1-9]|1[012])-*/*(0?[1-9]|[12][0-9]|3[01])-*/*(?:19|20)[0-9][0-9]))";
	private static final String time = "^([0-9]+){1,2}:([0-9]+){1,2}:*([0-9]+)*{1,2}\\s*([[pm|am]|[PM|AM]])*{2}$";
	private static final String[] dateTimePatterns = {"yyyy-MM-dd HH:mm", "yyyy-MM-dd H:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd H:mm:ss"};
	private static final boolean checkLDT(String lDateTime) {
		try {
			LocalDateTime.parse(lDateTime, DateTimeFormatter.ofPattern(dateTimePatterns[0]));
			return true; 
		} catch (Exception e) {
			try {
				LocalDateTime.parse(lDateTime, DateTimeFormatter.ofPattern(dateTimePatterns[1]));
				return true; 
			} catch (Exception e1) {
					try {
						LocalDateTime.parse(lDateTime, DateTimeFormatter.ofPattern(dateTimePatterns[2]));
						return true; 
					} catch (Exception e2) {
						try {
							LocalDateTime.parse(lDateTime, DateTimeFormatter.ofPattern(dateTimePatterns[3]));
							return true; 
						} catch (Exception e3) {
							return false;
						}
					}
				}
			}
	}
	private static final String ddlPattern = "CREATE TABLE ";
	private static final String insertPattern = "INSERT INTO ";
	
	public enum Result {
		String("VARCHAR(100)"),
		Integer("INT"),
		Double("DOUBLE"),
		LocalDate("DATE"),
		LocalTime("TIME"),
		LocalDateTime("DATETIME"),
		text("TEXT");
		
		private String s;
		
		Result(String s) {
			this.s = s;
		}
		public String getS() {
			return s;
		}
	}
	
	public static String formatQueryDataType(String s) {
		String result = null;
		if (s.length() > 255) {
			result = Result.valueOf("text").getS();
		} else {
			s = s.replaceAll("%", "").replaceAll(" %", "").replace("$", "")
					.replaceAll(" $", "").replace("/$", "")
					.replaceAll(" /$", "").trim();
			if (s.contains("-") && !s.matches(commonDate) && checkLDT(s) == false) {
				result = Result.valueOf(s.getClass().getSimpleName()).getS();
			} else if (s.contains("-") && s.contains(" ") && s.length() > 10 && checkLDT(s) == true) {
				LocalDate ld = parseDate(s.split(" ")[0]);
				String t = s.split(" ")[1];
				LocalTime lt = null;
				if (t.split(":").length == 2) {
					t = t + ":55";
					lt = parseTime(t);
				} else {
					t = leadingZero(t.split(":")[0]) + ":" + leadingZero(t.split(":")[1]) + ":" + leadingZero(t.split(":")[2]);
					lt = parseTime(t);
				}
				LocalDateTime ldt = LocalDateTime.of(ld, lt);
				result = Result.valueOf(ldt.getClass().getSimpleName()).getS();
			} else if (s.contains("/") || s.contains("-") && s.matches(commonDate)) {
				result = Result.valueOf(parseDate(s).getClass().getSimpleName()).getS();
			} else if (s.contains(":") && s.matches(time)) {
				result = Result.valueOf(parseTime(s).getClass().getSimpleName()).getS();
			} else {
				try {
					result = Result.valueOf(Integer.valueOf(s).getClass().getSimpleName()).getS();
				} catch (NumberFormatException e) {
					try {
						result = Result.valueOf(Double.valueOf(s).getClass().getSimpleName()).getS();
					} catch (NumberFormatException ex) {
						result = "VARCHAR(150)";
					}
				}
			}
		}
		return result;
	}
	
	public static boolean isPassword(String s) {
		Matcher matcher = passwordPattern.matcher(s);
        return matcher.matches();
		
	}
	
	public static String checkTableName(String fileName) {
		return fileName.substring(0, fileName.indexOf(".")).replace("-", "_").replace(" ", "_").toLowerCase();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> ArrayList<T> changeDataFormat(ArrayList<String> str) {
		ArrayList<T> result = new ArrayList<T>();
		for (String s : str) {
			if (s.length() > 255) {
				result.add((T) s);
			} else {
				if (!isPassword(s)) {
					s = s.replaceAll("%", "").replace(" %", "")
							.replace("$", "").replace(" $", "")
							.replace("'", "").replace(" '", "")
							.replace("/$", "").replace(" /$", "").trim();
				}
				if (s.contains("-") && s.length() >= 14 && checkLDT(s) == true && !isPassword(s)) {
					LocalDate ld = parseDate(s.split(" ")[0]);
					String t = s.split(" ")[1];
					if (t.split(":").length == 2) {
						t = t + ":05";
					} else {
						t = leadingZero(t.split(":")[0]) + ":" + leadingZero(t.split(":")[1]) + ":" + leadingZero(t.split(":")[2]);
					}
					LocalTime lt = parseTime(t);
					LocalDateTime ldt = LocalDateTime.of(ld, lt);
					result.add((T) ldt);
				} else if (s.contains("-") && !isPassword(s)) {
					result.add((T) s);
				} else if (s.contains("/") && s.matches(commonDate) && !isPassword(s)) {
					result.add((T) parseDate(s));
				} else if (s.contains(":") && s.matches(time) && !isPassword(s)) {
					result.add((T) parseTime(s));
				} else {
					try {
						result.add((T) Integer.valueOf(s));
					} catch (NumberFormatException e) {
						try {
							result.add((T) Double.valueOf(s));
						} catch (NumberFormatException ex) {
							result.add((T) s);
						}
					}
				}
			}
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> Object changeValueFormat(String s) {
		Object result;
		if (s.length() > 255) 
			result = s;
		if (!isPassword(s)) {
			s = s.replaceAll("%", "").replaceAll(" %", "")
					.replace("$", "").replaceAll(" $", "")
					.replace("'", "").replace(" '", "")
					.replace("/$", "").replaceAll(" /$", "").trim();
		}
		if (s.contains("-") && s.contains(" ") && s.length() > 10 && checkLDT(s) == true && !isPassword(s)) {
			LocalDate ld = parseDate(s.split(" ")[0]);
			String t = s.split(" ")[1];
			if (t.split(":").length == 2) {
				t = t + ":05";
			} else {
				t = leadingZero(t.split(":")[0]) + ":" + leadingZero(t.split(":")[1]) + ":" + leadingZero(t.split(":")[2]);
			}
			LocalTime lt = parseTime(t);
			LocalDateTime ldt = LocalDateTime.of(ld, lt);
			result = (T) ldt;
		} else if (s.contains("-") && !isPassword(s)) {
			result = s;
		} else if (s.contains("/") && s.matches(commonDate) && !isPassword(s)) {
			result = (T) parseDate(s);
		} else if (s.contains(":") && s.matches(time) && !SQLQueryMaker.isPassword(s)) {
			result = (T) parseTime(s);
		} else {
			try {
				result = (T) Integer.valueOf(s);
			} catch (NumberFormatException e) {
				try {
					result = (T) Double.valueOf(s);
				} catch (NumberFormatException ex) {
					result = s;
				}
			}
		}
		return result;
	}
	
	public static String createSQLDDLQuery(ArrayList<String> fields, ArrayList<String> values, String fileName) {
		fileName = fileName.substring(0, fileName.indexOf(".")).replace("-", "_").replace(" ", "_").toLowerCase();
		StringBuilder sb = new StringBuilder();
		sb.append(ddlPattern).append(fileName).append(" (unID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, ");
		for (int i = 0; i < fields.size(); i++) {
			sb.append(fields.get(i) + " ").append(formatQueryDataType(values.get(i)) + ", ");
		}
		return sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1).append(");").toString();
	}
	
	public static <T> String createSQLInsertQuery(ArrayList<String> fields, ArrayList<String> row, String fileName) {
		fileName = fileName.substring(0, fileName.indexOf(".")).replace("-", "_").replace(" ", "_").toLowerCase();
		StringBuilder sb = new StringBuilder();
		sb.append(insertPattern).append(fileName).append(" (")
					.append(fields.stream().collect(Collectors.joining(", ")))
					.append(") VALUES (");
		ArrayList<T> changedData = changeDataFormat(row);
		for (int i = 0; i < changedData.size(); i++) {
			if (changedData.get(i).getClass().getSimpleName().equals("String") || 
					changedData.get(i).getClass().getSimpleName().equals("LocalTime") || 
					changedData.get(i).getClass().getSimpleName().equals("LocalDate") ||
					changedData.get(i).getClass().getSimpleName().equals("LocalDateTime") ||
					(changedData.get(i).getClass().getSimpleName().equals("String") && changedData.get(i).toString().length() > 255))
				sb.append("\'" + changedData.get(i) + "\'" + ", ");
			else
				sb.append(changedData.get(i) + ", "); 
				
		}
		return sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1).append(");").toString();
	}
	
	public static <T> String createSQLUpdateFildsQuery(ArrayList<String> fields, ArrayList<String> values, String fileName, String condition) {
		fileName = fileName.substring(0, fileName.indexOf(".")).replace("-", "_").replace(" ", "_").toLowerCase();
		ArrayList<T> changedValues = changeDataFormat(values);
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ").append(fileName).append(" SET ");
		for (int i = 0; i < fields.size(); i ++) {
			sb.append(fields.get(i)).append(" = ");
			if (changedValues.get(i).getClass().getSimpleName().equals("String") || 
					changedValues.get(i).getClass().getSimpleName().equals("LocalTime") || 
					changedValues.get(i).getClass().getSimpleName().equals("LocalDate") ||
					changedValues.get(i).getClass().getSimpleName().equals("LocalDateTime") ||
					(changedValues.get(i).getClass().getSimpleName().equals("String") && changedValues.get(i).toString().length() > 255))
				sb.append("\'" + changedValues.get(i) + "\'" + ", ");
			else
				sb.append(changedValues.get(i) + ", "); 
		}
		sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1).append(" WHERE ").append(condition);
		return sb.append(";").toString();
	}
	
	public static LocalTime parseTime(String s) {
		String st = null;
		if ((s.split(":")[0]).length() == 1)
			s = "0" + s;
		if (s.endsWith("PM") || s.endsWith("pm") || s.endsWith("AM") || s.endsWith("am")) {
			String timeString = s.replace("PM", "").replace("pm", "").replace("AM", "").replace("am", "").trim();
			String temp = String.valueOf(Integer.parseInt(s.split(":")[0]));
			int length = timeString.length();
			if (s.substring(s.length() - 2, s.length()).equalsIgnoreCase("PM")) {
				temp = String.valueOf(Integer.parseInt(temp) + 12);
				if (Integer.parseInt(temp) == 24) {
					st = "00:" + timeString.substring(3, timeString.length());
				} else if (Integer.parseInt(temp) < 24) {
					if (temp.length() == 2)
						st = temp + timeString.substring(2, timeString.length());
					if (temp.length() == 1)
						st = "0" + temp + timeString.substring(2, timeString.length());
					if (st.length() == 5)
						st = st + ":55";
				} else {
					st = temp + timeString.substring(2, timeString.length());
					if (st.length() == 5)
						st = st + ":55";
				}
			} else {
				if (length == 7) {
					st = "0" + timeString;
				} else if (length == 5) {
					st = timeString + ":55";
				} else if (length == 4) {
					st = "0" + timeString + ":55";
				} else {
					st = timeString;
				}
			}
		} else {
			if (s.length() == 4) {
				st = "0" + s + ":55";
			} else if (s.length() == 5) {
				st = s + ":55";
			} else if (s.length() == 7) {
				st = "0" + s;
			} else {
				st = s;
			}
		} 
		LocalTime lt = LocalTime.parse(st, DateTimeFormatter.ISO_LOCAL_TIME);
		return lt;
	}
	
	public static LocalDate parseDate(String s) {
		s = s.replaceAll("/", "-");
		LocalDate locd = null;
		if (s.matches(date)) {
			s = (s.split("-"))[0] + "-" 
					+ leadingZero((s.split("-"))[1]) 
					+ "-" + leadingZero((s.split("-"))[2]);
			locd = LocalDate.parse(s);
		} else if (s.matches(date1)) {
			s = (s.split("-"))[2] + "-" 
					+ leadingZero((s.split("-"))[1]) 
					+ "-" + leadingZero((s.split("-"))[0]);
			locd = LocalDate.parse(s);
		} else if (s.matches(date2)) {
			s = (s.split("-"))[2] + "-" 
					+ leadingZero((s.split("-"))[0])
					+ "-" + leadingZero((s.split("-"))[1]);
			locd = LocalDate.parse(s);
		}
		return locd;
	}
	
	public static String leadingZero(String d) {
		return d.length() == 1 ? "0" + d : d;
	}
}