package it.tecnet.crs.ATPO.auditors.web.action;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoDettaglioFascicolo;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoRiepilogoFascicolo;
import it.tecnet.crs.ATPO.auditors.service.AtpoFasiService;
import it.tecnet.crs.ATPO.auditors.web.beans.ModelAuditorsRiepilogoFascicoloATPO;
import it.tecnet.crs.ATPO.auditors.web.dto.AtpoDettaglioFascicoloDto;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class RiepilogoFascicoloAtpoAction extends BaseAction implements ModelDriven<ModelAuditorsRiepilogoFascicoloATPO> {

	protected static Logger log = Logger
	.getLogger(RiepilogoFascicoloAtpoAction.class);
	
	private static final long serialVersionUID = 1L;
	private ModelAuditorsRiepilogoFascicoloATPO model= new ModelAuditorsRiepilogoFascicoloATPO();
	private AtpoFasiService service;

	public RiepilogoFascicoloAtpoAction(AtpoFasiService service){
		super();
		this.service=service;
	}

	public String getRiepilogoFascicolo(){

		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		AtpoRiepilogoFascicolo fascicolo= null;

		if (user!= null) {
			try{
				getModel().setStatoEsamePratica(user.getStatoEsamePratica());
				//query per prendermi FASCICOLO
				fascicolo=service.getFascicolo(user.getIdFaseDati());

				if(fascicolo != null){
					
					user.setIdFascicolo(fascicolo.getIdRiepilogoFascicolo());
					fascicolo= convertCodToDescr(fascicolo);
					getModel().setFascicolo(fascicolo);
					
				}else{
					user.setIdFascicolo(-1);
				}

				// INIZIO - VERIFICA PRATICA MODIFICABILE
				AuSPratica auSPratica = service.cerca(AuSPratica.class,
						user.getIdSPratica());
				AppUser appUser = (AppUser) request.getSession()
						.getAttribute("AppUser");
				if (auSPratica.getUserOwner() == null)
					getModel().setPraticaModificable("V");
				else if (auSPratica.getUserOwner().equals(
						appUser.getUsername()))
					getModel().setPraticaModificable("V");
				else
					getModel().setPraticaModificable("F");
				// FINE - VERIFICA PRATICA MODIFICABILE				

			}catch(Exception e){
				e.printStackTrace();
			}
			// query per i dropdown fascilo cart e elettronico

			try{

				List<AuTplTipologicheAtpoDto> fascicoliElettroniciEcartacei= service.getTipologicaAtpo("V007");
				if(!fascicoliElettroniciEcartacei.isEmpty()){

					for(AuTplTipologicheAtpoDto s : fascicoliElettroniciEcartacei){
						getModel().getFascicoliElettroniciEcartacei().add(s);
					}

				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			// query per il dropdown Dettaglio documentazione mancante
			try{

				List<AuTplTipologicheAtpoDto> dettDocMancate=service.getTipologicaAtpo("V003");
				if(!dettDocMancate.isEmpty()){

					for(AuTplTipologicheAtpoDto a : dettDocMancate){
						getModel().getDettDocMancate().add(a);
					}

				}
			}catch(Exception e){
				e.printStackTrace();
			}

		}

		return SUCCESS;
	}
	
	private AtpoRiepilogoFascicolo convertCodToDescr(AtpoRiepilogoFascicolo fascicolo) {
		try{
			if(fascicolo.getFascicoloElettronico()!=null && !fascicolo.getFascicoloElettronico().trim().equals("")){
				String codFascElettr= fascicolo.getFascicoloElettronico().trim();
				getModel().setCodFascElettr(codFascElettr);
				AuTplTipologicheAtpoDto t=service.getDescrTipologicaByCodifica("V007",codFascElettr.trim());
				fascicolo.setFascicoloElettronico(t.getDescrizione());
			}
			/*if(fascicolo.getFascicoloCartaceo()!=null && !fascicolo.getFascicoloCartaceo().trim().equals("")){
				String codFascCart= fascicolo.getFascicoloCartaceo().trim();
				getModel().setCodFascCart(codFascCart);
				AuTplTipologicheAtpoDto t=service.getDescrTipologicaByCodifica(codFascCart.trim());
				fascicolo.setFascicoloCartaceo(t.getDescrizione());
			}*/
			if(fascicolo.getDettDocMancante()!=null && !fascicolo.getDettDocMancante().trim().equals("")){
				String codDocManc= fascicolo.getDettDocMancante().trim();
				getModel().setCodDocManc(codDocManc);
				AuTplTipologicheAtpoDto t=service.getDescrTipologicaByCodifica("V003",codDocManc.trim());
				fascicolo.setDettDocMancante(t.getDescrizione());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return fascicolo;
	}

	public String salvaRiepilogoFascicolo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			long idFascicolo =user.getIdFascicolo();
			long idFaseDati= user.getIdFaseDati();
			AtpoRiepilogoFascicolo  a= getModel().getFascicolo();
			a.setIdFaseDati(idFaseDati);
			String fascicoloE= a.getFascicoloElettronico().trim();
//			//String fascCartaceo= a.getFascicoloCartaceo().trim();
//
			a.setIdRiepilogoFascicolo(idFascicolo);
//			
			//a.setFascicoloCartaceo(fascCartaceo);
			a.setFascicoloElettronico(fascicoloE);
			a.setFasePronta("S");
			service.salva(a);
		}
		return SUCCESS;
	}
	public String aggiungiDocMancante(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			String codifica=getModel().getDettaglioDocMancante();
			long idRiepilogoFascicolo=user.getIdFascicolo();
			AtpoDettaglioFascicolo f= new AtpoDettaglioFascicolo();
			
			//controlla che non sia gia presente in tabella
			AtpoDettaglioFascicolo df=service.checkDocMancante(idRiepilogoFascicolo,codifica);
			if(df == null){
				f.setCodifica(codifica);
				f.setIdRiepilogoFascicolo(idRiepilogoFascicolo);
				try{
					service.salva(f);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			
		}
		return SUCCESS;
	}

	/*
	 * popola la tabella doc mancante
	 */
	public String getDocMancanteRiepFascTable(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		//AtpoRiepilogoFascicolo fascicolo= null;
		long idFascicolo= user.getIdFascicolo();
		log.info("getDocMancanteRiepFascTable for idFascicolo: " + idFascicolo );
		if (user!= null && idFascicolo != -1 ) {
		
		List<AtpoDettaglioFascicoloDto>l =service.getTabDocMancante(idFascicolo, getModel().getiDisplayStart(), 
				getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),
				getModel().getsSortDir_0(),
				getModel().getsSearch());
		
		
		 int total = service.countTabDocMancante(idFascicolo, getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch());

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

	
	//elimina doc mancante
	public String eliminaDocMancante(){
		String listIdDocMancante = getModel().getListaDocMancante();
		String [] idDocMancante= listIdDocMancante.split(",");
		
		
		//riempie lista rischiDaElimanare
		for(int i=0; i< idDocMancante.length;i++){
			long idDoc = Long.valueOf(idDocMancante[i]).longValue();
			AtpoDettaglioFascicolo dettFasc=  service.cerca( AtpoDettaglioFascicolo.class, idDoc);
			if(dettFasc != null){
				service.remove(dettFasc);
				
			}
		}
		
		
		return SUCCESS;
	}
	@Override
	public ModelAuditorsRiepilogoFascicoloATPO getModel() {
		return model;
	}

}
