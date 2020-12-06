package it.tecnet.crs.audit.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.audit.service.AuAuditRischiENonConformitaService;
import it.tecnet.crs.audit.web.beans.ModelRischiENonConformitaEsprRischio;
import it.tecnet.crs.audit.web.dto.AuAuditMRisesprDto;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditEspressioneRischioActionTable extends BaseAction implements ModelDriven<ModelRischiENonConformitaEsprRischio> {

	private static final long serialVersionUID = 1L;
	
	private AuAuditRischiENonConformitaService service;
	ModelRischiENonConformitaEsprRischio model = new ModelRischiENonConformitaEsprRischio();

	public AuAuditEspressioneRischioActionTable(AuAuditRischiENonConformitaService service){
		super();
		this.setService(service);
	}
	
	//ritorna  la pagina e il model
	public String getPageTabEsprRischio(){
		 HttpServletRequest request = ServletActionContext.getRequest();

			DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
			if (user!= null) {
			
				long idAudit=user.getIdAudit();
				getModel().setIdAudit(idAudit);
				 //lista rischi per dropdown
				List<AuMRischio> rischi= service.getRischiPerDropDown(idAudit);
				if(rischi != null || !rischi.isEmpty()){
					for(AuMRischio m: rischi){
						getModel().getRischiDropDown().add(m);
					}
				}
				
				
			}
		return SUCCESS;
	}
	
	//init della tabella
	public String searchRischiEnonConformitaEsprRischio(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
		
			long idAudit=user.getIdAudit();
			getModel().setIdAudit(idAudit);
			
			/*
			 * carica in tabella le espressioni rischio
			 */
	
		List<AuAuditMRisesprDto>l =service.getEsprRischi(idAudit, getModel().getiDisplayStart(), 
				getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),
				getModel().getsSortDir_0(),
				getModel().getsSearch());
		
			// COUNT RECORD
		 int total = service.countListaEsprRischi(idAudit,getModel()
					.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
					.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
					.getsSearch());

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);
		 
			//carica in model 
		 if(l.size()!=0){
			 for(Object s : l){
					getModel().getAaData().add(s);
			}
		 }
		}
		return SUCCESS;
	}
	
	public String checkCodiceEsprRischio(){
		try{
			List<AuMRisepr> esprRischi=service.checkCodiceEsprRischio();
			if(esprRischi != null){
				for(AuMRisepr i : esprRischi){
					getModel().getAuMRisepr().add(i);
				}
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String salvaNuovoEsprRischio(){
		 HttpServletRequest request = ServletActionContext.getRequest();

			DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
			if (user!= null) {
			
				long idAudit=user.getIdAudit();
				AuMRisepr m= getModel().getEsprRischio();
				// m.setIdAudit(idAudit);
				service.salva(m);
				
			}
		
		return SUCCESS;
	}
	
	public String eliminaEsprRischio(){
		String idEsprRischio = getModel().getListaIdEsprRischio();
		String [] idEsRischio= idEsprRischio.split(",");
		
		
		//riempie lista rischiDaElimanare
		for(int i=0; i< idEsRischio.length;i++){
			long idEsR=Long.valueOf(idEsRischio[i]).longValue();
			AuMRisepr esprRisepRischioDaEliminare=  service.cerca( AuMRisepr.class, idEsR);
			if(esprRisepRischioDaEliminare != null){
				service.remove(esprRisepRischioDaEliminare);
				
			}
		}
		
		
		return SUCCESS;
	}
	
	
	@Override
	public ModelRischiENonConformitaEsprRischio getModel() {
		return model;
	}

	public void setService(AuAuditRischiENonConformitaService service) {
		this.service = service;
	}

	public AuAuditRischiENonConformitaService getService() {
		return service;
	}

}
