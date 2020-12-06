package it.tecnet.crs.web.action;

import it.tecnet.crs.audit.service.AuCalcolaIndicatoriService;
import it.tecnet.crs.jpa.model.AuInccDes;
import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.service.AuditService;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.util.SessioneUtil;
import it.tecnet.crs.web.beans.NonConformitaAccessiTableActionPaginator;
import it.tecnet.crs.web.dto.AuMVarCompDto;
import it.tecnet.crs.web.dto.NonConformitaAccessiDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ibm.ws.webservices.engine.utils.SessionUtils;
import com.opensymphony.xwork2.ModelDriven;

public class NonConformitaAccessiTableAction extends BaseAction implements ModelDriven<NonConformitaAccessiTableActionPaginator> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NonConformitaAccessiTableActionPaginator model = new NonConformitaAccessiTableActionPaginator();
	private AuditService auditService;
	private AuCalcolaIndicatoriService auCalcolaIndicatoriService;

	public NonConformitaAccessiTableAction(AuditService auditService, AuCalcolaIndicatoriService auCalcolaIndicatoriService) {
		super();
		this.setAuditService(auditService);
		this.setAuCalcolaIndicatoriService(auCalcolaIndicatoriService);
		// AuAuditIndicatoriService
	}


	public String getTabNonConfAccessi(){

		//popolo dropdown fasi
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");

		//carico dropdown filtro descrizione fase
		long idAudit= user.getIdAudit();
		List<String> descrizioneFase= auditService.getDescrizioneFaseAssociate(idAudit);
		if(descrizioneFase !=null){
			getModel().setDescrizioneFaseAcc(descrizioneFase);
		}

		return SUCCESS;
	}

	public String getNonConfAccessiTabella(){

		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){

			long idAudit= user.getIdAudit();
			long idSSessione=user.getIdSSessione();
			String filtro= getModel().getFiltroFaseAcc().trim();
			if(filtro.equalsIgnoreCase("tutte")){
				filtro="";
			}

			List<AuInccDes> listAuInccDes = auCalcolaIndicatoriService.getAuInccDes();

			List<NonConformitaAccessiDto>l =auditService.getTabNonConfAccess(idAudit, idSSessione,filtro,getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch());


			int total = auditService.countTabNonConfAccess(idAudit, idSSessione,filtro, getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch());

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			Double totalePraticheEsaminate = auCalcolaIndicatoriService.getPraticheEsamintate(user.getIdSSessione());
			if(l!=null || l.size()!=0 ){
				for(NonConformitaAccessiDto s : l){

					// CALCOLO DESCRITTIVO SU INCC
					String descrizioneIncc = SessioneUtil.getInccDescrittivo(s.getValoreINCC(), listAuInccDes);
					if (descrizioneIncc != null)s.setDescrittivoINCC(descrizioneIncc);

					// CALCOLO
					if ( totalePraticheEsaminate != null && totalePraticheEsaminate.doubleValue() > 0 && s.getQuantita() > 0){
						Double perc = ( new Double(s.getQuantita()) / totalePraticheEsaminate ) * 100;
						s.setPercQuantSingolaNonConfsuSommatotale(perc);
					}

					getModel().getAaData().add(s);
				}
			}
		}

		return SUCCESS;
	}


	public String getDettagliNonConfAccessi(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){

			String id= getModel().getId();
			String []p= id.split("-");
			long idNonConfM= Long.valueOf(p[0]);
			long idNonConfS=Long.valueOf(p[1]);

			NonConformitaAccessiDto nc=auditService.getDettagliNonConformita(idNonConfM, idNonConfS);
			getModel().setNc(nc);
		}

		return SUCCESS;
	}

	public String getPageVarCompNonConfAccessi(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){

			String id= getModel().getId();
			String []p= id.split("-");
			long idNonConfM= Long.valueOf(p[0]);
			long idNonConfS=Long.valueOf(p[1]);

			user.setIdMNonConf(idNonConfM);
			user.setIdSNonConf(idNonConfS);

			try{
				AuMNonConf m= auditService.cerca(AuMNonConf.class, idNonConfM);
				NonConformitaAccessiDto nc= new NonConformitaAccessiDto();
				nc.setDescrizione(m.getDescrizione());
				getModel().setNc(nc);
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		return SUCCESS;
	}

	public String getVarCompNonConfAccessi(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if(user!=null){
			long idNonConfM= user.getIdMNonConf();
			long idNonConfS=user.getIdSNonConf();
			List<AuMVarCompDto> mv= auditService.getVarCompNonConfAccessi(idNonConfM, idNonConfS,getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch());

			int total=auditService.countVarCompNonConfAccessi(idNonConfM, idNonConfS,getModel().getsSearch());
			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);
			if(mv !=null || mv.size()!=0 ){
				for(AuMVarCompDto s : mv){

					getModel().getAaData().add(s);
				}
				


			}
		}


		return SUCCESS;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public AuditService getAuditService() {
		return auditService;
	}

	public AuCalcolaIndicatoriService getAuCalcolaIndicatoriService() {
		return auCalcolaIndicatoriService;
	}


	public void setAuCalcolaIndicatoriService(
			AuCalcolaIndicatoriService auCalcolaIndicatoriService) {
		this.auCalcolaIndicatoriService = auCalcolaIndicatoriService;
	}


	@Override
	public NonConformitaAccessiTableActionPaginator getModel() {
		// TODO Auto-generated method stub
		return model;
	}

}
