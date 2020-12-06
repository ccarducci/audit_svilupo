package it.tecnet.crs.audit.web.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.audit.service.AuAuditRischiENonConformitaService;
import it.tecnet.crs.audit.service.AuAuditService;
import it.tecnet.crs.audit.web.beans.ModelModificaDomanda;
import it.tecnet.crs.audit.web.dto.AuAuditDomandaDto;
import it.tecnet.crs.jpa.model.AuMDomanda;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisposta;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditModificaDomandaAction extends BaseAction implements ModelDriven<ModelModificaDomanda> {

	
	private static final long serialVersionUID = 1L;
	private AuAuditService auditService;
	private AuAuditRischiENonConformitaService service; 
	private ModelModificaDomanda model= new ModelModificaDomanda();
	
	
	
	public AuAuditModificaDomandaAction(AuAuditService auditService, AuAuditRischiENonConformitaService service) {
		super();
		this.setAuditService(auditService);
		this.service=service;
		}
	
	//ritorna model e pagina
	public String modificaDomanda(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		
		long idDomanda= getModel().getIdDomanda();
		user.setIdDomanda(idDomanda);
		//domanda da modificare
		AuMDomanda d= service.cerca(AuMDomanda.class, idDomanda);
		
		if(d != null){
			//creazione domanda dto per mostrare la descrizione di AuMRischio
			AuAuditDomandaDto dDTO= new AuAuditDomandaDto();
			long idRischio= d.getIdMRischio();
			AuMRischio r=service.cerca(AuMRischio.class, idRischio);
			dDTO.setControlloProcesso(d.getControlloProcesso());
			dDTO.setDescrizione(d.getDescrizione());
			dDTO.setDescrizioneRischio(r.getDescrizioneRischio());
			dDTO.setIdDomanda(d.getIdDomanda());
			dDTO.setIdQuestionario(d.getIdQuestionario());
			dDTO.setIdRischio(d.getIdMRischio());
			dDTO.setPeso(d.getPesoDomanda());
			dDTO.setPesoPErcentuale(d.getPesoPercentuale());
			dDTO.setValMaxRsp(d.getValoreMaxRisposta());
			dDTO.setCodiceRischio(r.getCodiceRischio());
			
			
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			if(d.getDataInizio() != null){
				String dataInizio = simpleDateFormat.format(d.getDataInizio());
				dDTO.setDataInizio(dataInizio);
			}
			
			if(d.getDataFine()!= null){
				String dataFine=simpleDateFormat.format(d.getDataFine());
				dDTO.setDataFine(dataFine);
				
			}
			
			
		
			//domanda da mostrare nel tab modifica domanda
			getModel().setDomandaDto(dDTO);
			
			//dropdown scelta del rischio
			 //lista rischi per dropdown
			List<AuMRischio> rischi= service.getRischiPerDropDown(user.getIdAudit());
			if(rischi != null || !rischi.isEmpty()){
				for(AuMRischio m: rischi){
					getModel().getRischiAssociatiUpD().add(m);
				}
			}
			
		}
		
		
		return SUCCESS;
	}
	
	

	
	public String salvaDomandaModificata(){
		 HttpServletRequest request = ServletActionContext.getRequest();

			DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
			if (user!= null) {
			
				long idQuestionario=user.getIdQuestionario();
				long idDomanda= user.getIdDomanda();
				AuMDomanda d= getModel().getD();
				
				if(d!= null){
					
					d.setIdQuestionario(idQuestionario);
					d.setIdDomanda(idDomanda);
					d.setPercRipartita(BigDecimal.valueOf(ricalcolaPercentualeRipartita(d)));
					auditService.salva(d);
					
				}
				
			}
		
		
		
		return SUCCESS;
	}
	
	private Double ricalcolaPercentualeRipartita(AuMDomanda d){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		Double pesoPercentualeDomanda= d.getPesoPercentuale().doubleValue();
		
		long idRischio= d.getIdMRischio();
		long idQuestionario= user.getIdQuestionario();
		long idDomanda= d.getIdDomanda();
		
		
		long numDomandeStessoRischio=auditService.getNumDomandeStessoRischio(idRischio, idQuestionario);
		if(numDomandeStessoRischio ==0){
			//solo la domanda corrente ha quel rischio associato
			numDomandeStessoRischio =1;
			long pesoDomanda=d.getPesoDomanda();
			Double result=pesoPercentualeDomanda/numDomandeStessoRischio /(pesoDomanda/numDomandeStessoRischio);
			return result;
		}
		//somma di tutti i pesi delle domande con stesso rischio
		Double sommaPesiPercDomandeStessoRischio =auditService.sommaPesiDomandeStessoRischio(idRischio, idQuestionario, idDomanda);
		
		Double sumPesiPercDomStessoRischioCorrente=sommaPesiPercDomandeStessoRischio + d.getPesoPercentuale().doubleValue();
		
		
		double result=pesoPercentualeDomanda/(numDomandeStessoRischio)/(sumPesiPercDomStessoRischioCorrente/(numDomandeStessoRischio));
		
		return result;
		
		
		
	}
	
	public String searchRisposteDomanda(){
		
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
		
			long idDomanda=user.getIdDomanda();
			getModel().setIdDomanda(idDomanda);
		List<AuMRisposta> r =auditService.getRisposteDomanda(idDomanda, getModel().getiDisplayStart(), 
				getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),
				getModel().getsSortDir_0(),
				getModel().getsSearch());
		
		
		 int total = auditService.countRisposte(idDomanda, getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch());

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);
		 
		 if(r!=null && r.size()!=0){
			 for(Object s : r){
					getModel().getAaData().add(s);
				}
		 }
		
		}
		
		return SUCCESS;
		
		
	}
	
	
	public String  salvaNuovaRisposta(){
		
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			
			AuMRisposta r= getModel().getR();
			long idDomanda=user.getIdDomanda();
			
			r.setIdDomanda(idDomanda);
			
			try{
				service.salva(r);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		
		return SUCCESS;
	}
	
	public String salvaModificaRisposta(){
		
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			
			AuMRisposta r= getModel().getR();
			long idDomanda=user.getIdDomanda();
			
			r.setIdDomanda(idDomanda);
			
			try{
				service.salva(r);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		
		return SUCCESS;
	}
	
	public String eliminaRisposte(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			String idRisposte= getModel().getListaIdRisposte();
			String [] idRisp= idRisposte.split(",");
			for(int i=0; i< idRisp.length;i++){
				long idR=Long.valueOf(idRisp[i]).longValue();
				AuMRisposta risposta=  service.cerca( AuMRisposta.class, idR);
				
				service.remove(risposta);
					
				
			}
		
	
		}
		
		
		return SUCCESS;
	}
	
	public void setAuditService(AuAuditService auditService) {
		this.auditService = auditService;
	}

	public AuAuditService getAuditService() {
		return auditService;
	}

	@Override
	public ModelModificaDomanda getModel() {
		// TODO Auto-generated method stub
		return model;
	}

}
