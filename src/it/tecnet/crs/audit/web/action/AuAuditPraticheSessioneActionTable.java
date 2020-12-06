package it.tecnet.crs.audit.web.action;

import it.tecnet.crs.audit.service.AuCampagnaService;
import it.tecnet.crs.audit.web.beans.ModelPraticheSessione;
import it.tecnet.crs.audit.web.dto.PraticheCampagnaDto;
import it.tecnet.crs.jpa.model.AuAssCampVerb;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.dto.VerbaleDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditPraticheSessioneActionTable extends BaseAction implements ModelDriven<ModelPraticheSessione> {

	private static final long serialVersionUID = 1L;
	private ModelPraticheSessione mps= new ModelPraticheSessione();
	private AuCampagnaService campagnaService;

	public AuAuditPraticheSessioneActionTable( AuCampagnaService campagnaService){
		super();
		this.campagnaService= campagnaService;

	}

	//popola la tabella pratiche sessione dell asisde
	public String getListaVerbaliCampagna(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			int total=0;
			long idCampione=user.getIdCampione();

			List<PraticheCampagnaDto> l =campagnaService.getListaVerbaliCampagnaSess(idCampione,getModel()
					.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
					.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
					.getsSearch());

			total = campagnaService.countListaVerbaliCampagnaSess(idCampione,getModel()
					.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
					.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
					.getsSearch());

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);


			if(l.size() !=0){
				for(PraticheCampagnaDto s : l){
					getModel().getAaData().add(s);
				}
			}
		}



		return SUCCESS;
	}


	public String aggiungiPraticaAlCampione(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			long idCampione=user.getIdCampione();
			long idPratica=getModel().getIdPratica();

			
			campagnaService.aggiungiPraticaAlCampione(idCampione, idPratica);
			

		}

		return SUCCESS;
	}


	@Override
	public ModelPraticheSessione getModel() {
		// TODO Auto-generated method stub
		return mps;
	}

}
