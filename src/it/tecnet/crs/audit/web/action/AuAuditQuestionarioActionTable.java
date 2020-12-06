package it.tecnet.crs.audit.web.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.audit.service.AuAuditRischiENonConformitaService;
import it.tecnet.crs.audit.service.AuAuditService;
import it.tecnet.crs.audit.web.beans.ModelQuestionarioAudit;
import it.tecnet.crs.audit.web.dto.AuAuditDomandaDto;
import it.tecnet.crs.jpa.model.AuMDomanda;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuQuestionario;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditQuestionarioActionTable extends BaseAction implements ModelDriven<ModelQuestionarioAudit> {

	
	private static final long serialVersionUID = 1L;
	private AuAuditService auditService;
	private AuAuditRischiENonConformitaService service;
	
	ModelQuestionarioAudit model= new ModelQuestionarioAudit();
	
	public AuAuditQuestionarioActionTable(AuAuditService auditService, AuAuditRischiENonConformitaService service) {
		super();
		this.setAuditService(auditService);
		this.service= service;
	}
	
	public String getDomande(){
		
		 HttpServletRequest request = ServletActionContext.getRequest();

			DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
			if (user!= null) {
			
				long idAudit=user.getIdAudit();
				getModel().setIdAudit(idAudit);
				
				AuQuestionario q =auditService.cercaQuestionario(idAudit);
				if(q != null){
					user.setIdQuestionario(q.getIdQuestionario());
				}
				
				
				 //lista rischi per dropdown
				List<AuMRischio> rischi= service.getRischiPerDropDown(idAudit);
				if(rischi != null || !rischi.isEmpty()){
					for(AuMRischio m: rischi){
						getModel().getRischiAssociati().add(m);
					}
				}
				
				
			}
		
		return SUCCESS;
	}
	
	
	
	
	
	public String searchDomandeQuestionario(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
		
			long idAudit=user.getIdAudit();
			getModel().setIdAudit(idAudit);
			
			
			
				List<AuAuditDomandaDto>l =auditService.getDomande(idAudit, getModel().getiDisplayStart(), 
						getModel().getiDisplayLength(),
						getModel().getiSortCol_0(),
						getModel().getsSortDir_0(),
						getModel().getsSearch());
				if(l.size()!=0){
					 int total = auditService.countDomande(idAudit,getModel()
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
				
			
		
		}
		
		
		
		return SUCCESS;
	}
	
	public String salvaNuovaDomanda(){

		 HttpServletRequest request = ServletActionContext.getRequest();

			DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
			if (user!= null) {
			
				long idQuestionario=user.getIdQuestionario();
				AuMDomanda d= getModel().getD();
				
				if(d!= null){
					
					d.setIdQuestionario(idQuestionario);
					d.setPercRipartita(BigDecimal.valueOf(calcolaPercentualeRipartita(d)));
					auditService.salva(d);
					
				}
				
				
				
				
			}
		
		
		
		return SUCCESS;
	}
	
	
	private Double calcolaPercentualeRipartita(AuMDomanda d){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		Double pesoPercentualeDomanda= d.getPesoPercentuale().doubleValue();
		
		long idRischio= d.getIdMRischio();
		long idQuestionario= user.getIdQuestionario();
		long numDomandeStessoRischio=auditService.getNumDomandeStessoRischio(idRischio, idQuestionario);
		if(numDomandeStessoRischio ==0){
			//solo la domanda corrente ha quel rischio associato
			numDomandeStessoRischio =1;
			long pesoDomanda=d.getPesoDomanda();
			Double result=pesoPercentualeDomanda/numDomandeStessoRischio /(pesoDomanda/numDomandeStessoRischio);
			return result;
		}
		//somma di tutti i pesi delle domande con stesso rischio
		Double sommaPesiPercDomandeStessoRischio =auditService.sommaPesiDomandeStessoRischio(idRischio, idQuestionario);
		
		Double sumPesiPercDomStessoRischioCorrente=sommaPesiPercDomandeStessoRischio + d.getPesoPercentuale().doubleValue();
		
		//aggiungo 1 perche calcola anche la domanda corrente
		double result=pesoPercentualeDomanda/(numDomandeStessoRischio+1)/(sumPesiPercDomStessoRischioCorrente/(numDomandeStessoRischio+1));
		
		return result;
		
		
	}
	
	public String eliminaDomande(){
		
		String idDomande= getModel().getListaIdDomande();
		
		String [] idDom= idDomande.split(",");
		
		//lista che contiene tutte le domande selezionate
		List<AuMDomanda> domandeDaEliminareSelezionate=new ArrayList<AuMDomanda>();
		
		//domande che non possono essere eliminate
		List<AuMDomanda> domandeDaNonEliminare=new ArrayList<AuMDomanda>();
		
		//domande che possono essere eliminate
		List<AuMDomanda> domandeDaEliminare=new ArrayList<AuMDomanda>();
		
		//riempie lista domandeDaElimanare
		for(int i=0; i< idDom.length;i++){
			long idDomanda=Long.valueOf(idDom[i]).longValue();
			AuMDomanda domandaDaEliminare=  service.cerca( AuMDomanda.class, idDomanda);
			domandeDaEliminareSelezionate.add(domandaDaEliminare);
		}
		
		//controlla che non ci siano idDomanda presenti in AuMRisposta
		//se ci sono vengono inseriti nella lista  domandeDaNonEliminare
		for(AuMDomanda i : domandeDaEliminareSelezionate){
			AuMDomanda domanda= auditService.checkRisposteDomanda(i);
			if(domanda != null){
				//aggiungo alla lista domande da non eliminare la domanda corrente
				domandeDaNonEliminare.add(domanda);
			}else{
				domandeDaEliminare.add(i);
			}
			
		}
		//elimino domande in domande da eliminare
		for(AuMDomanda i : domandeDaEliminare){
				service.remove(i);
			}
		
		//serve per mostrare lato client la notifica delle domande non eliminati
		getModel().setDomandeDaNonEliminare(domandeDaNonEliminare);
		
		
		return SUCCESS;
	}
	
	public String checkRischiAssociatiAllaDomanda(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
		
			long idAudit=user.getIdAudit();
		 //lista rischi per dropdown
		List<AuMRischio> rischi= service.getRischiPerDropDown(idAudit);
		if(rischi != null || !rischi.isEmpty()){
			for(AuMRischio m: rischi){
				getModel().getRischiAssociati().add(m);
			}
		}
		}
		return SUCCESS;
	}
	
	public ModelQuestionarioAudit getModel() {
		return model;
	}



	public void setAuditService(AuAuditService auditService) {
		this.auditService = auditService;
	}



	public AuAuditService getAuditService() {
		return auditService;
	}
	
	

}
