package it.polito.ai.gas.forms;

import it.polito.ai.gas.business.User;

public class RegisterForm_no extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3477877270405511387L;
	// ---
	private int year, month, day;
	// ---
	
	// --- Getters & Setters ---

	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year -1900;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month -1;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}

		
}
