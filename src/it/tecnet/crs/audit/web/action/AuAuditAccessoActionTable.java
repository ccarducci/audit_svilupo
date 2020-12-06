package it.tecnet.crs.audit.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import it.tecnet.crs.audit.service.AuCampagnaService;
import it.tecnet.crs.audit.web.beans.ModelAccesso;
import it.tecnet.crs.audit.web.dto.CampagnaDto;
import it.tecnet.crs.audit.web.dto.SessioniDto;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuSede;
import it.tecnet.crs.jpa.model.AuSessioni;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;
import com.opensymphony.xwork2.ModelDriven;

public class AuAuditAccessoActionTable extends BaseAction implements ModelDriven<ModelAccesso> {

	private static final long serialVersionUID = 1L;
	private AuCampagnaService campagnaService;
	private ModelAccesso ma= new ModelAccesso();



	public AuAuditAccessoActionTable(AuCampagnaService campagnaService) {
		super();
		this.campagnaService=campagnaService;
	}

	public String initAccessoAudit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user = (AppUser) request.getSession().getAttribute("AppUser");
		long idUtente = user.getIdUtente();
		String tipo=user.getRuoloAttivo();	

		List<CampagnaDto> l =campagnaService.getListaCampagna(-1, getModel().getiDisplayLength(),getModel().getiSortCol_0(),
				getModel().getsSortDir_0(), 
				getModel().getsSearch(),idUtente);

		try {
			for (CampagnaDto o : l) {
				getModel().getCampagnaDtoList().add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		try {
			//popolo menu a tendina SEDI nell aside aggiungi Accesso

			List<AuSede> ls= campagnaService.getSedi();
			for (AuSede sede : ls) {
				getModel().getListSede().add(sede);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


	public String getListaSessioni(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user = (AppUser) request.getSession().getAttribute("AppUser");
		long idUtente = user.getIdUtente();
		String tipo=user.getRuoloAttivo();	

		List<SessioniDto> l=campagnaService.getListaSessioni(getModel().getiDisplayStart(), getModel().getiDisplayLength(),getModel().getiSortCol_0(),
				getModel().getsSortDir_0(), 
				getModel().getsSearch(),
				idUtente);

		try{
			int total = campagnaService.countListaSessioni(getModel().getiDisplayStart(), getModel().getiDisplayLength(),getModel().getiSortCol_0(),
					getModel().getsSortDir_0(), 
					getModel().getsSearch(),
					idUtente);
			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);
			for(Object o : l){	

				getModel().getAaData().add(o);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}


	public String modificaAccesso(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser appuser = (AppUser) request.getSession().getAttribute("AppUser");
		long idUtente = appuser.getIdUtente();
		String tipo=appuser.getRuoloAttivo();	
		// il model viene inizializzato dalla loadDiv(..?idAudit=checkboxID)
		SessioniDto sessioniDto= new SessioniDto();


		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		if (user != null) {
			long idSessione=getModel().getIdSessione();
			user.setIdSessione(idSessione);

			AuSessioni s= null;
			//recupero tutti i dati dell accesso da modificare
			try{
				s = (AuSessioni) campagnaService.cerca(AuSessioni.class,  idSessione);
				getModel().setAccesso(s);
			}catch(Exception e){
				e.printStackTrace();
			}


			//mi serve per scrivere in background nella select il nome della campagna da
			//modificare
			long  idCampagna=getModel().getAccesso().getIdCampagna();

			AuCampagna c= null;
			try{
				c= (AuCampagna) campagnaService.cerca(AuCampagna.class, idCampagna); 
			}catch(Exception e){
				e.printStackTrace();
			}


			if(c != null){
				getModel().setCampagna(c);

				//ci scrivo gli inputText
				sessioniDto.setIdSessione(s.getIdSessione());
				sessioniDto.setSede(s.getSede());
				sessioniDto.setDataInizio(s.getDataInizio());
				sessioniDto.setDataFine(s.getDataFine());
				sessioniDto.setStato(s.getStato());
				sessioniDto.setDirigente(s.getDirigente());
				sessioniDto.setCampagnaDesc(c.getNome());
			}


			// popolo menu a tendina Campagna
			//-1 significa che non sto popolando una tabella
			try{
				List<CampagnaDto> l =campagnaService.getListaCampagna(-1, getModel().getiDisplayLength(),getModel().getiSortCol_0(),
						getModel().getsSortDir_0(), 
						getModel().getsSearch(), idUtente);
				for (CampagnaDto o : l) {
					getModel().getCampagnaDtoList().add(o);
				}
			}catch(Exception e){
				e.printStackTrace();
			}


			//popolo menu a tendina SEDI nell aside aggiungi Accesso


			try {
				List<AuSede> ls= campagnaService.getSedi();
				for (AuSede sede : ls) {
					getModel().getListSede().add(sede);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			//salvo nel model
			getModel().setSessioneDto(sessioniDto);


		}
		return SUCCESS;

	}


	public String salvaNuovoAccessoAudit(){
		AuSessioni s= getModel().getAccesso();
		try{
			campagnaService.salva(s);
		}catch(Exception e){
			log.error(e);
			return "actionError";
		}


		return SUCCESS;
	}

	public String salvaModificheAccesso(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			AuSessioni s= getModel().getAccesso();
			s.setIdSessione(user.getIdSessione());
			try{
				campagnaService.salva(s);
			}catch(Exception e){
				e.printStackTrace();
			}

		}


		return SUCCESS;
	}

	public String eliminaAccesso(){
		long idSessione= getModel().getIdSessione();
		try{
			campagnaService.eliminaSessione(idSessione);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public ModelAccesso getModel() {
		return ma;
	}
}
