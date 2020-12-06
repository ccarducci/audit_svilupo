package it.tecnet.crs.ATPO.auditors.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPraticheSISCO;
import it.tecnet.crs.ATPO.auditors.service.AuditorsAccessiATPOService;
import it.tecnet.crs.ATPO.auditors.web.beans.ModelAuditorsAccessiATPO;
import it.tecnet.crs.ATPO.auditors.web.dto.PraticheAtpoDto;
import it.tecnet.crs.audit.web.dto.SessioniDto;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuSSessione;
import it.tecnet.crs.jpa.model.AuSessioni;
import it.tecnet.crs.report.service.ReportPDFService;
import it.tecnet.crs.report.web.dto.ReportAccessoPDFDto;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.dto.AuSSessioneDto;
import it.tecnet.crs.web.dto.SessioneDto;

import com.opensymphony.xwork2.ModelDriven;

public class AccessiAtpoAction extends BaseAction implements ModelDriven<ModelAuditorsAccessiATPO> {


	private static final long serialVersionUID = 1L;

	private ModelAuditorsAccessiATPO modelAccessiATPO= new ModelAuditorsAccessiATPO();

	private AuditorsAccessiATPOService serviceAccessiATPO;
	

	public AccessiAtpoAction( AuditorsAccessiATPOService serviceAccessiATPO){
		super();
		this.serviceAccessiATPO=serviceAccessiATPO;
	}


	public String initAccessiAtpo(){

		return SUCCESS;
	}


	public String modificaAccessoATPO(){

		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		long idSessione = getModel().getIdSessione();
		//prendo l'audit dalla sessione


		try{
			AuSessioni s= serviceAccessiATPO.cerca(AuSessioni.class, idSessione);

			user.setNomeSede(s.getSede());
			getModel().setNomeSede(s.getSede());

			AuSSessioneDto ss= serviceAccessiATPO.getSSessione(idSessione);

			if(ss!=null){
				user.setIdSSessione(ss.getIdSSessione());
				getModel().setSsessione(ss);
			}


			long idCampagna= s.getIdCampagna();
			user.setIdCampagna(idCampagna);
			AuAudit a=serviceAccessiATPO.getAuditByIdCampagna(idCampagna);
			if(a!=null){
				user.setIdAudit(a.getIdAudit());
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		// metto idSessione in session
		user.setIdSessione(idSessione);
		user.setTipo("atpo");
		//user.setIdAudit(a.getIdAudit());

		return SUCCESS;
	}



	/*
	 * tabella pratiche ATPO in accessi auditors ATPO
	 */
	public String getTabAccessiPraticheATPO(){

		return SUCCESS;
	}

	/*
	 * tabella pratiche ATPO in accessi auditors ATPO
	 */
	public String getAccessiPraticheATPOTable(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		if(user != null){
			int idSessione= (int) user.getIdSessione();
			int filtroEsito=  getModel().getFiltroEsito();
			int filtroStato= getModel().getFiltroStato();
			

			try{
				List<PraticheAtpoDto> list= serviceAccessiATPO.getPraticheATPO(idSessione, filtroEsito, filtroStato, getModel().getiDisplayStart(), getModel().getiDisplayLength(),getModel().getiSortCol_0(),
						getModel().getsSortDir_0(), getModel().getsSearch());


				int total = serviceAccessiATPO.countListaPraticheATPO(idSessione,filtroEsito,filtroStato, getModel().getiDisplayStart(), getModel().getiDisplayLength(),getModel().getiSortCol_0(),
						getModel().getsSortDir_0(), 
						getModel().getsSearch()).intValue();
				getModel().setiTotalDisplayRecords(total);
				getModel().setiTotalRecords(total);
				for(Object o : list){	

					getModel().getAaData().add(o);
				}

			}catch(Exception e){
				e.printStackTrace();
			}

		}



		return SUCCESS;
	}
	
	public String getNotaSessione(){

		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		long idSSessione= user.getIdSSessione();
		try{
			AuSSessione s= serviceAccessiATPO.cerca(AuSSessione.class, idSSessione);
			getModel().setNotaSessione(s.getNota());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String salvaNotaSessione(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		long idSSessione= user.getIdSSessione();
		try{
			AuSSessione s= serviceAccessiATPO.cerca(AuSSessione.class, idSSessione);
			s.setNota(getModel().getNotaSessione());
			serviceAccessiATPO.salva(s);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String eliminaNotaSessione(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		long idSSessione= user.getIdSSessione();
		try{
			AuSSessione s= serviceAccessiATPO.cerca(AuSSessione.class, idSSessione);
			s.setNota("");
			serviceAccessiATPO.salva(s);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
		
		
	}
	
	/*
	 *  MEDIA TEMPI
	 */

	public String getMediaTempi(){
		
		
		return SUCCESS;
	}
	
	public String getMediaTempiTable(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");  
		try{
			List<ReportAccessoPDFDto> list=serviceAccessiATPO.getRisultatiByTempo(user.getIdSSessione(), getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch());
			
			int total = serviceAccessiATPO.countListTempo(user.getIdSSessione(), getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch());

			
			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);
			for(Object o : list){	

				getModel().getAaData().add(o);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	@Override
	public ModelAuditorsAccessiATPO getModel() {

		return modelAccessiATPO;
	}


	

}
