package it.tecnet.crs.audit.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.audit.service.AuCampagnaService;
import it.tecnet.crs.audit.web.beans.ModelRegoleAudit;
import it.tecnet.crs.jpa.model.AuRegoleCampagna;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditRegoleActionTable extends BaseAction implements ModelDriven<ModelRegoleAudit> {

	private static final long serialVersionUID = 1L;
	private ModelRegoleAudit mra= new ModelRegoleAudit();
	private AuCampagnaService campagnaService;

	public AuAuditRegoleActionTable( AuCampagnaService campagnaService){
		super();
		this.campagnaService= campagnaService;

	}

	public String searchRegole(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			int total=0;
			long idCampagna=user.getIdCampagna();


			List<AuRegoleCampagna> l=campagnaService.getListaRegoleCampagna(idCampagna,getModel()
					.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
					.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
					.getsSearch());

			total = campagnaService.countListaRegoleCampagna(idCampagna,getModel()
					.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
					.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
					.getsSearch()).intValue();

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


	public String attivaRegolaCampagna(){

		long idRegola=getModel().getIdRegola();
		AuRegoleCampagna r=campagnaService.cerca(AuRegoleCampagna.class, idRegola);
		r.setStato("A");
		campagnaService.salva(r);

		return SUCCESS;
	}

	public String disattivaRegolaCampagna(){

		long idRegola=getModel().getIdRegola();
		AuRegoleCampagna r=campagnaService.cerca(AuRegoleCampagna.class, idRegola);
		r.setStato("D");
		campagnaService.salva(r);

		return SUCCESS;
	}


	@Override
	public ModelRegoleAudit getModel() {
		return mra;
	}

}
