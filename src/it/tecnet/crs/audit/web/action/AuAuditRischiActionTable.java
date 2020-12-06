package it.tecnet.crs.audit.web.action;


import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.audit.service.AuAuditRischiENonConformitaService;
import it.tecnet.crs.audit.web.beans.ModelRischiENonConformitaRischio;

import it.tecnet.crs.jpa.model.AuMRischio;

import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;


import com.opensymphony.xwork2.ModelDriven;

public class AuAuditRischiActionTable  extends BaseAction implements
ModelDriven<ModelRischiENonConformitaRischio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 36985269261422651L;

	ModelRischiENonConformitaRischio model= new ModelRischiENonConformitaRischio();
	
	private AuAuditRischiENonConformitaService service;
	


	public AuAuditRischiActionTable(AuAuditRischiENonConformitaService service){
		super();
		this.setService(service);
	}
	
	public String getPageTabRischio(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
		
			long idAudit=user.getIdAudit();
			getModel().setIdAudit(idAudit);
		}
		return SUCCESS;
	}
	
	
	public String searchRischi(){
		
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
		
			long idAudit=user.getIdAudit();
			getModel().setIdAudit(idAudit);
		List<AuMRischio>l =service.getRischi(idAudit, getModel().getiDisplayStart(), 
				getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),
				getModel().getsSortDir_0(),
				getModel().getsSearch());
		
		
		 int total = service.countListaRischi(idAudit,getModel()
					.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
					.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
					.getsSearch());

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);
		 
		 if(l.size()!=0){
			 for(Object s : l){
					getModel().getAaData().add(s);
				}
		 }
		
		}
		
		
		
		
		return SUCCESS;
	}
	
	
	public String salvaNuovoRischio(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			
			AuMRischio m= getModel().getRischio();
			
			
			m.setIdAudit(user.getIdAudit());
			try{
				service.salva(m);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		
		
		return  SUCCESS;
	}
	//controlla che in tabella non ci sia un altro rischio con questo codice
	//viene chiamato in fase di modifica e salvataggio
	public String checkCodiceRischio() {
		
		try{
			List<AuMRischio> rischi=service.checkCodiceRischio();
			if(rischi != null){
				for(AuMRischio i : rischi){
					getModel().getRischi().add(i);
				}
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String eliminaRischio(){
		//lista di id che arriva dai checkbox selezionati
		String idRischi = getModel().getListaIdRischio();
		String [] idRischio= idRischi.split(",");
		
		//lista che contiene tutti i rischi selezionati
		List<AuMRischio> rischiDaEliminareSelezionati=new ArrayList<AuMRischio>();
		
		//rischi che non possono essere eliminati
		List<AuMRischio> rischiDaNonEliminare=new ArrayList<AuMRischio>();
		
		//rischi che possono essere eliminati
		List<AuMRischio> rischiDaEliminare=new ArrayList<AuMRischio>();
		
		//riempie lista rischiDaElimanare
		for(int i=0; i< idRischio.length;i++){
			long idR=Long.valueOf(idRischio[i]).longValue();
			AuMRischio rischioDaEliminare=  service.cerca( AuMRischio.class, idR);
			rischiDaEliminareSelezionati.add(rischioDaEliminare);
		}
		
		//controlla che non ci siano rischi presenti in auDomanda e auMRisepr
		//se ci sono vengono inseriti nella lista  rischiDaNonEliminare
		for(AuMRischio i : rischiDaEliminareSelezionati){
			AuMRischio rischio= service.checkRischioInAuDomandaAuRisepr(i);
			if(rischio != null){
				//aggiungo alla lista di rischi da non eliminare il rischio corrente
				rischiDaNonEliminare.add(rischio);
			}else{
				rischiDaEliminare.add(i);
			}
			
		}
		//elimino rischi in rischi da elimanare
		for(AuMRischio i : rischiDaEliminare){
				service.deleteRischio(i);
			}
		
		//serve per mostrare lato client la notifica dei rischi non eliminati
		getModel().setRischiDaNonEliminare(rischiDaNonEliminare);
			
		return SUCCESS;
		}
		
		
		
		
		
	
	
	@Override
	public ModelRischiENonConformitaRischio getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public AuAuditRischiENonConformitaService getService() {
		return service;
	}


	public void setService(AuAuditRischiENonConformitaService service) {
		this.service = service;
	}

}
