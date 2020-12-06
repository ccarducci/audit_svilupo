package it.tecnet.crs.ATPO.auditors.web.beans;


public class ModelDateBetween {
	private String d1;
	private String d2;
	private int days;
	private String calcoloConFestivita;
	
	public String getD1() {
		return d1;
	}
	public void setD1(String d1) {
		this.d1 = d1;
	}
	public String getD2() {
		return d2;
	}
	public void setD2(String d2) {
		this.d2 = d2;
	}
	
	public String getCalcoloConFestivita() {
		return calcoloConFestivita;
	}
	public void setCalcoloConFestivita(String calcoloConFestivita) {
		this.calcoloConFestivita = calcoloConFestivita;
	}
	
	public int getDays() {
		return days;
	}
	
	public void setDays(int days) {
		this.days = days;
	}

	
}
