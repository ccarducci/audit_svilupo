package it.tecnet.crs.audit.service;

import it.tecnet.crs.util.PraticaUtil;

import java.util.Date;

public class CommonUtilsServiceImpl implements CommonUtilsService {

	@Override
	public Integer daysBetween(Date d1, Date d2, boolean calcoloConFestivita) {
		return PraticaUtil.daysBetween(d1, d2, calcoloConFestivita);
	}

}
