package it.tecnet.crs.audit.service;

import java.util.Date;

public interface CommonUtilsService {
	public Integer daysBetween(Date d1, Date d2, boolean calcoloConFestivita);
}
