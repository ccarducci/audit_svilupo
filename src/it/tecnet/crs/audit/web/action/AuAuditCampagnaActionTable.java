package it.tecnet.crs.audit.web.action;

import it.tecnet.crs.audit.service.AuAuditService;
import it.tecnet.crs.audit.service.AuCampagnaService;
import it.tecnet.crs.audit.web.beans.ModelCampagna;
import it.tecnet.crs.audit.web.dto.AuAuditDto;
import it.tecnet.crs.audit.web.dto.CampagnaDto;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditCampagnaActionTable extends BaseAction implements
ModelDriven<ModelCampagna> {

	private static final long serialVersionUID = 1L;
	private AuCampagnaService campagnaService;
	private AuAuditService auditService;
	private ModelCampagna mc= new ModelCampagna();



	public AuAuditCampagnaActionTable(AuCampagnaService campagnaService,AuAuditService auditService ){
		super();
		this.setCampagnaService(campagnaService);
		this.auditService = auditService;
	}

	public String initCampagna() {
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		try {
			// popolo menu a tendina aside
			List<AuAuditDto> listAudit = auditService.getListAuditGenerale(user.getIdUtente().intValue(),-1,
					getModel().getiDisplayLength(), getModel().getiSortCol_0(),
					getModel().getsSortDir_0(), getModel().getsSearch());

			if(listAudit==null){

				return SUCCESS; 
			}

			if(listAudit.size()!=0) {
				for (AuAuditDto o : listAudit) {
					getModel().getListAudit().add(o);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String searchCamapagne() {


		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user = (AppUser) request.getSession().getAttribute("AppUser");
		long idUtente = user.getIdUtente();
		List<CampagnaDto> l = campagnaService.getListaCampagna(getModel()
				.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
				.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
				.getsSearch(),idUtente);

		int total = campagnaService.countListaCampagna(
				getModel().getiDisplayStart(), getModel().getiDisplayLength(),
				getModel().getiSortCol_0(), getModel().getsSortDir_0(),
				getModel().getsSearch(),idUtente);

		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);

		if(l.size()!=0){
			for (Object o : l) {
				getModel().getAaData().add(o);

			}
		}

		return SUCCESS;
	}

	public String newCampagna() {

		AuCampagna a = getModel().getCampagna();

		try {
			a=campagnaService.salvaCampagna(a);
			//ELIO l'associazione non viene piu fatta al momento della creazione
			// della campagna
			//campagnaService.associaVerbaliCampagna(a);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String eliminaCampagna() {
		campagnaService.eliminaCampagna(getModel().getIdCampagna());
		return SUCCESS;
	}

	public String modificaCampagna() {

		HttpServletRequest request = ServletActionContext.getRequest();
		// il model viene inizializzato dalla loadDiv(..?idAudit=checkboxID)
		AppUser appUser=(AppUser)request.getSession().getAttribute("AppUser");
		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
		"DatiUtente");
		if (user != null) {
			long idCampagna=getModel().getIdCampagna();
			user.setIdCampagna(idCampagna);
			CampagnaDto c = campagnaService.getCampagnaDto(idCampagna);
			if(c != null){
				// campagnaDto da mostrare nella pagina per essere modificato
				getModel().setCampagnaDto(c);
				Long idAudit=campagnaService.getIdAuditByIdCampagna(idCampagna);
				getModel().setIdAuditSelezionato(idAudit);
			}

			try {
				// popolo menu a tendina audit
				//-1 significa che non sto popolando una tabella
				List<AuAuditDto> listAudit = auditService.getListAuditGenerale(appUser.getIdUtente().intValue(),-1,getModel().getiDisplayLength(), getModel().getiSortCol_0(),getModel().getsSortDir_0(), getModel().getsSearch());
				for (AuAuditDto o : listAudit) {
					getModel().getListAudit().add(o);
				}

				//colonne tabella report sedi
//				List<AuReportSediLabel> labels= campagnaService.getListaReportSediLabel(idCampagna);
//
//				if(labels.size()!=0){
//					//contiene un elemento labels
//					getModel().getLabelList().add(labels.get(0));
//				}else{
//					//serve che le label siano inizializzate altrimenti il datatable va in errore
//					//perche trova meno <th></th> di quelle specificate nel js
//					AuReportSediLabel labelPerTabellaVuota= new AuReportSediLabel();
//					labelPerTabellaVuota.setLabel1(" ");
//					labelPerTabellaVuota.setLabel2(" ");
//					labelPerTabellaVuota.setLabel3(" ");
//					labelPerTabellaVuota.setLabel4(" ");
//					labelPerTabellaVuota.setLabel5(" ");
//					labelPerTabellaVuota.setLabel6(" ");
//					labelPerTabellaVuota.setLabel7(" ");
//					labelPerTabellaVuota.setLabel8(" ");
//					labelPerTabellaVuota.setLabel9(" ");
//					labelPerTabellaVuota.setLabel10(" ");
//					getModel().getLabelList().add(labelPerTabellaVuota);
//
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(getModel().getTipoCmp().equals("vigilanza")){
			return SUCCESS;
		}else {
			return "ATPO";
		}
	}

	/*
	 * Controlla il il numero di colonne che devono essere inizializzate
	 * (tabella report sedi dinamica)
	 */
//	public String contaColonneReportSedi(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//
//		DatiUtente user = (DatiUtente) request.getSession().getAttribute(
//		"DatiUtente");
//		int count=0;
//		if (user != null) {
//			long idCampagna=user.getIdCampagna();
//			//in realta è un oggetto e non una lista
//			List<AuReportSediLabel> labels= campagnaService.getListaReportSediLabel(idCampagna);
//
//			if(labels.size()!=0){
//				for(AuReportSediLabel i:labels){
//					if(i.getLabel1() != null && !i.getLabel1().isEmpty()){
//						count++;
//					}
//					if(i.getLabel2() != null && !i.getLabel2().isEmpty()){
//						count++;
//					}
//					if(i.getLabel3() != null && !i.getLabel3().isEmpty()){
//						count++;
//					}
//					if(i.getLabel4() != null && !i.getLabel4().isEmpty()){
//						count++;
//					}
//					if(i.getLabel5() != null && !i.getLabel5().isEmpty()){
//						count++;
//					}
//					if(i.getLabel6() != null && !i.getLabel6().isEmpty()){
//						count++;
//					}
//					if(i.getLabel7() != null && !i.getLabel7().isEmpty()){
//						count++;
//					}
//					if(i.getLabel8() != null && !i.getLabel8().isEmpty()){
//						count++;
//					}
//					if(i.getLabel9() != null && !i.getLabel9().isEmpty()){
//						count++;
//					}
//					if(i.getLabel10() != null && !i.getLabel10().isEmpty()){
//						count++;
//					}
//
//				}
//			}
//
//			getModel().setNumColonneSediReport(count);
//		}
//		return SUCCESS;
//	}



	public String salvaModificaCampagna() {
		AuCampagna a = getModel().getCampagna();

		try {
			AuCampagna campagna = campagnaService.salvaCampagna(a);

			// SE LA DATA FINE DELLA CAMPAGNA è VALORIZZATA
			// AGGIORNO LA DATA FINE ANCHE DEGLI ACCESSI CORRELATI
			if (campagna.getDataFine()!= null)campagnaService.aggiornaDataFineAccessiCampagna(campagna);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;

	}


	public String checkIfCampagnaHasSessioni(){
		getModel().setNumeroSessioniAssociate(campagnaService
				.countSessioniAssociateCampagna(getModel().getIdCampagna()));
		return SUCCESS;
	}

	@Override
	public ModelCampagna getModel() {

		return mc;
	}

	public void setCampagnaService(AuCampagnaService campagnaService) {
		this.campagnaService = campagnaService;
	}

	public AuCampagnaService getCampagnaService() {
		return campagnaService;
	}




}
