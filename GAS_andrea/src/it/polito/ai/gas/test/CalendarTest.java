package it.polito.ai.gas.test;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarTest {

	
	public static void main(String[] args) {
		Calendar cal = new GregorianCalendar();
		System.out.println(cal);
		
		cal.set(1988, 13, 1);
		System.out.println(cal);
		
		
	}
}
