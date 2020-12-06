package it.tecnet.crs.audit.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.audit.service.AuAuditRischiENonConformitaService;
import it.tecnet.crs.audit.web.beans.ModelVarComportamentale;
import it.tecnet.crs.audit.web.dto.AuAuditNonConfDto;
import it.tecnet.crs.audit.web.dto.AuAuditVarCompDto;
import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditVarComportamentaleActionTable extends BaseAction implements ModelDriven<ModelVarComportamentale> {

	private static final long serialVersionUID = 1L;

	private ModelVarComportamentale model = new ModelVarComportamentale();
	
	private AuAuditRischiENonConformitaService service;
	


	public AuAuditVarComportamentaleActionTable(AuAuditRischiENonConformitaService service){
		super();
		this.setService(service);
	}
	
	
	//ritorna pagina e model
	public String getPageTabVarComportamentale(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//carico le non conformita nel dropdox degli aside
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
		
			long idAudit=user.getIdAudit();
			getModel().setIdAudit(idAudit);
			 //lista rischi per dropdown
			List<AuMNonConf> nc= service.getNC(idAudit);
			if(nc != null || !nc.isEmpty()){
				for(AuMNonConf m: nc){
					getModel().getNonConformita().add(m);
				}
			}
			
			
		}
	
		
	
		return SUCCESS;
		
	}
	
	public String searchVariantiComportamentali(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
		
			long idAudit=user.getIdAudit();
			getModel().setIdAudit(idAudit);
			
			/*
			 * carica in tabella var comportamentale		 
			 */
	
		List<AuAuditVarCompDto>l =service.getVarComp(idAudit, getModel().getiDisplayStart(), 
				getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),
				getModel().getsSortDir_0(),
				getModel().getsSearch());
		
			// COUNT RECORD
		 int total = service.countListaVarComp(idAudit, getModel().getiDisplayStart(), 
					getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),
					getModel().getsSortDir_0(),
					getModel().getsSearch());

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
	
	public String checkCodiceVarComp(){
		
		try{
			List<AuMVarcomp> vc=service.checkCodice();
			if(vc != null){
				for(AuMVarcomp i : vc){
					getModel().getVcList().add(i);
				}
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String salvaNuovaVarComp(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			
			AuMVarcomp m= getModel().getVc();
			
			
			
			try{
				service.salva(m);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		
		
		return SUCCESS;
	}
	
	public String eliminaVarComp(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
			String listaId= getModel().getIdVarianteComportamentale();
			String [] array= listaId.split(",");
			for(int i =0; i< array.length;i++){
			
				long id=Long.valueOf(array[i]).longValue();
				AuMVarcomp a= service.cerca( AuMVarcomp.class, id);
				service.remove(a);
			}
		}
		
		
		
		return SUCCESS;
	}
	
	 @Override
	public ModelVarComportamentale getModel() {
		// TODO Auto-generated method stub
		return model;
	}




	public void setService(AuAuditRischiENonConformitaService service) {
		this.service = service;
	}




	public AuAuditRischiENonConformitaService getService() {
		return service;
	}

}
