package it.tecnet.crs.ATPO.auditors.web.action;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPraticheSISCO;
import it.tecnet.crs.ATPO.auditors.service.AtpoFasiService;
import it.tecnet.crs.ATPO.auditors.service.AuditrosUpdPraticheATPOService;
import it.tecnet.crs.ATPO.auditors.web.beans.ModelAuditorsModificaPraticaATPO;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.NonConformitaPraticheAtpoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.PraticheAtpoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.RischipraticheAtpoDto;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class AccessiPraticaAtpoAction extends BaseAction implements ModelDriven<ModelAuditorsModificaPraticaATPO> {


	private static final long serialVersionUID = 1L;
	
	private ModelAuditorsModificaPraticaATPO model= new ModelAuditorsModificaPraticaATPO();
	private AuditrosUpdPraticheATPOService service;
	private AtpoFasiService fdService;

	public AccessiPraticaAtpoAction(AuditrosUpdPraticheATPOService service, AtpoFasiService fdService){
		super();
		this.service=service;
		this.fdService=fdService;
	}

	public String modificaPratica(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente)request.getSession().getAttribute("DatiUtente");
		AppUser appUser = (AppUser) request.getSession().getAttribute("AppUser");
		
		if (user!= null) {

			long idPratica=getModel().getIdPraticaATPO();
			user.setIdPraticaAtpo(idPratica);
			
			//PRENDO LA PRATICA SISCO PER LEGGERE IL FASCICOLO
			AtpoPraticheSISCO sisco= service.cerca(AtpoPraticheSISCO.class ,idPratica);
			
			
			String fascicolo = sisco.getFascicolo();
			user.setFascicolo(fascicolo.trim());
			user.setCodSede(sisco.getCodSede().trim());
			AtpoFaseDati faseDati=null;
			try{
				//CERCO IN FASE_DATI UN RECORD CON QUEL FASCICOLO
				faseDati=service.getFaseDati(user.getFascicolo(), user.getCodSede());

				System.out.println(faseDati.getGiudizio()+" - "+faseDati.getFasePronta());

				if(faseDati == null){
					//inserisco in fase_dati id e fascicolo
					faseDati = new AtpoFaseDati();
					faseDati.setFascicolo(sisco.getFascicolo().trim());
					faseDati.setFasePronta("N");
					faseDati= service.save(faseDati);
					getModel().setFaseDati(faseDati);
					
					//metto in sessione l id
					user.setIdFaseDati(faseDati.getIdfaseDati());
					//serve in fase post peritale per sapere quale maschera mostrare
					user.setGiudizioFaseDati("nd");
				}else if(faseDati.getGiudizio() == null || faseDati.getGiudizio().trim().equals("") || faseDati.getFasePronta().trim().equals("N")){
					user.setGiudizioFaseDati("nd");
				}else{
					//serve in fase post peritale per sapere quale maschera mostrare
					user.setGiudizioFaseDati(faseDati.getGiudizio().trim());
					getModel().setFaseDati(faseDati);
				}
				System.out.println("getGiudizioFaseDati:"+user.getGiudizioFaseDati());
				//dati tab dati generali
				PraticheAtpoDto pratica= service.getDatiPratica(idPratica, fascicolo);

				if(pratica !=null){
					getModel().setPraticheAtpoDto(pratica);
					//aggiungo lo stato della pratica in sessione
					user.setStatoEsamePratica(pratica.getStatoEsamePratica());
					
					// INIZIO - VERIFICA PRATICA MODIFICABILE
					Long idSPratica = service.getAuSPraticaByIdPratica(idPratica);
					AuSPratica auSPratica = service.cerca(AuSPratica.class ,idSPratica);
					getModel().getPraticheAtpoDto().setUserIdOwner(auSPratica.getUserOwner());
					user.setIdSPratica(idSPratica);
					if (auSPratica.getUserOwner() == null  )
						getModel().getPraticheAtpoDto().setPraticaModificable("V");
					else if( auSPratica.getUserOwner().equals(appUser.getUsername()) )
						getModel().getPraticheAtpoDto().setPraticaModificable("V");
					else
						getModel().getPraticheAtpoDto().setPraticaModificable("F");
					// FINE - VERIFICA PRATICA MODIFICABILE
					
				}
				//tab fase dati(Spese / Prestazioni)
				getModel().setFaseDati(faseDati);
				
				//prendo le tipologiche per i radio button
				try{
					
					List<AuTplTipologicheAtpoDto> radioButtons=service.getTipologicaAtpo("V019");
					if(!radioButtons.isEmpty()){
						
						for(AuTplTipologicheAtpoDto s : radioButtons){
							getModel().getRadioButtons().add(s);
						}
					
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
				//converto codiceGiudizio in descrizione
				faseDati=convertCodToDescr(faseDati);
				
				//metto in sessione l id fase dati e l id pratica
				user.setIdFaseDati(faseDati.getIdfaseDati());
				
			}catch(Exception e){
				e.printStackTrace();

			}
		}

		return SUCCESS;
	}

	public String salvaFaseDatiATPO(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			AtpoFaseDati fd= getModel().getFaseDati();
			fd.setIdfaseDati(user.getIdFaseDati());
			fd.setGiudizio(getModel().getFaseDati().getGiudizio().trim());
			fd.setImportoPrestazioneErogata(getModel().getFaseDati().getImportoPrestazioneErogata());
			if(fd.getImportoPrestazioneErogata()==null){
				fd.setImportoPrestazioneErogata(new BigDecimal(0.0));
			}
			//fd.setImportoSpeseCTU(getModel().getFaseDati().getImportoSpeseCTU());
			//fd.setImportoSpeseLegali(getModel().getFaseDati().getImportoSpeseLegali());
			fd.setPrestazioneEconomica(getModel().getFaseDati().getPrestazioneEconomica());
			fd.setFasePronta("S");
			fd.setFascicolo(user.getFascicolo());
			fd.setCodSede(user.getCodSede());
			user.setGiudizioFaseDati(fd.getGiudizio());
			service.save(fd);
			
			AtpoFasePostPeritale fpp= fdService.getFasePostPeritale(user.getIdFaseDati());
			fpp.setCodChiusuraCorretto(fd.getGiudizio());
			fdService.salva(fpp);
		}

		return SUCCESS;
	}
	
	//TAB RISCHI--------------------------------------------------------------------------------
	public String getTabRischiPraticheAtpo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		String fascicolo= user.getFascicolo();
		getModel().setFascicolo(fascicolo);
		
		return SUCCESS;
	}
	// TABELLA RISCHI-------------------------------
	public String getTableRischiPraticheAtpo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		
		//I Rischi legati alla pratica vengono visualizzati solo se Stato esame pratica = “C” su S-Pratica.
		if(user!=null && user.getStatoEsamePratica()!=null && user.getStatoEsamePratica().equalsIgnoreCase("C")){
			
			long idAudit= user.getIdAudit();
			List<RischipraticheAtpoDto>l = service.getTabRischiPratAtpo(idAudit, getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch(),
					user.getIdPraticaAtpo());
			
			
			 int total = service.countTabRischiPratAtpo(idAudit, getModel().getsSearch(),user.getIdPraticaAtpo());

				getModel().setiTotalDisplayRecords(total);
				getModel().setiTotalRecords(total);
			 
			 if(l!=null){
				 for(Object s : l){
						getModel().getAaData().add(s);
					}
			 }
	}
		return SUCCESS;
	}
	
	/*
	 * TAB NON CONFORMITA
	 */

	public String getTabNonConfPraticheAtpo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		String fascicolo= user.getFascicolo();
		getModel().setFascicolo(fascicolo);
		
		//carico dropdown filtro descrizione fase
		long idAudit= user.getIdAudit();
		List<String> descrizioneFase= service.getDescrizioneFaseAssociate(idAudit);
		if(descrizioneFase !=null){
			getModel().setDescrizioneFase(descrizioneFase);
		}
		
		
		return SUCCESS;
	}
	
	public String searchNonConfPraticaAtpo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		
		//Le Non Conformità legate alla pratica vengono visualizzate solo se Stato esame pratica = “C” su S-Pratica.
		if(user!=null && user.getStatoEsamePratica()!=null && user.getStatoEsamePratica().equalsIgnoreCase("C")){
			
			String filtro= getModel().getFiltroFase().trim();
			if(filtro.equalsIgnoreCase("tutte")){
				filtro="";
			}
			long idAudit= user.getIdAudit();
			long idPratica=user.getIdPraticaAtpo();
			List<NonConformitaPraticheAtpoDto>l =service.getNonConfPratAtpo(idAudit,idPratica, filtro,getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch());
			
			
			 int total = service.countNonConfPratAtpo(idAudit,idPratica,filtro, getModel().getsSearch());

				getModel().setiTotalDisplayRecords(total);
				getModel().setiTotalRecords(total);
			 
			 if(l != null){
				 for(Object s : l){
						getModel().getAaData().add(s);
					}
			 }
	}
		return SUCCESS;
	}
	
	
	
	
	@Override
	public ModelAuditorsModificaPraticaATPO getModel() {
		
		return model;
	}
	/*
	 * CONVERTE LE CODIFICHE TIPOLOGICHE NELLE RISPETTIVE DESCRIZIONI DA MOSTRARE
	 */
	private AtpoFaseDati  convertCodToDescr(AtpoFaseDati faseDati) {

		try{
			//eccezioni non rilevabili
			if(faseDati.getGiudizio()!=null && !faseDati.getGiudizio().trim().equals("")){
				String codificaGiudizio= faseDati.getGiudizio().trim();
				getModel().setCodificaGiudizio(codificaGiudizio);
				AuTplTipologicheAtpoDto t=service.getDescrTipologicaByCodifica(codificaGiudizio.trim());
				faseDati.setGiudizio(t.getDescrizione());
			
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return faseDati;
	}

}

