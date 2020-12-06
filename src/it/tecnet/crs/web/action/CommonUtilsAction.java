package it.tecnet.crs.web.action;

import it.tecnet.crs.ATPO.auditors.web.beans.ModelDateBetween;
import it.tecnet.crs.audit.service.CommonUtilsService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.ModelDriven;

public class CommonUtilsAction extends BaseAction implements
		ModelDriven<ModelDateBetween> {

	private static final long serialVersionUID = 1L;
	private ModelDateBetween model = new ModelDateBetween();

	// Service Common
	private CommonUtilsService commonUtilsService;

	public CommonUtilsAction(CommonUtilsService commonUtilsService) {
		super();
		this.commonUtilsService = commonUtilsService;
	}

	@Override
	public ModelDateBetween getModel() {
		return model;
	}
	
	//http://localhost:9081/CruscottoAuditAtpoWebWeb/jsonATPO/getBetweenDays?d1=13-09-2016&d2=09-11-2016&calcoloConFestivita=true
	public String getBetweenDays() throws Exception {
		return getBetweenDaysByStringDate(getModel().getD1(), getModel().getD2(), getModel().getCalcoloConFestivita());
	}

	public String getBetweenDays(Date d1, Date d2, boolean calcoloConFestivita) {
		commonUtilsService.daysBetween(d1, d2, calcoloConFestivita);
		return SUCCESS;
	}

	public String getBetweenDaysByStringDate(String dt1, String dt2, String calcoloConFestivita) throws ParseException {
		Date d1 = new SimpleDateFormat("dd-MM-yyyy").parse(dt1);
		Date d2 = new SimpleDateFormat("dd-MM-yyyy").parse(dt2);
		boolean ccf=false;
		if(calcoloConFestivita.equals("true")) ccf=true;
//		model.setD1(d1);
//		model.setD2(d2);
		model.setDays(commonUtilsService.daysBetween(d1, d2, ccf));
		return SUCCESS;
	}
}
