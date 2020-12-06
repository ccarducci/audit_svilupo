package it.tecnet.crs.audit.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.audit.service.AuAuditRischiENonConformitaService;
import it.tecnet.crs.audit.web.beans.ModelRischiENonConformitaNonConformita;
import it.tecnet.crs.audit.web.dto.AuAuditNonConfDto;
import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class AuAuditNonConformActionTable  extends BaseAction implements
ModelDriven<ModelRischiENonConformitaNonConformita> {

	private static final long serialVersionUID = 1L;
	private ModelRischiENonConformitaNonConformita model= new ModelRischiENonConformitaNonConformita();
	private AuAuditRischiENonConformitaService service;
	
	public AuAuditNonConformActionTable(AuAuditRischiENonConformitaService service){
		super();
		this.setService(service);
	}
	
	//carica il model e ritorna la pagina nonConformita.jsp
	public String getPageTabNonConformita(){
		
		
		 HttpServletRequest request = ServletActionContext.getRequest();

			DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
			if (user!= null) {
			
				long idAudit=user.getIdAudit();
				getModel().setIdAudit(idAudit);
				 //lista rischi per dropdown
				List<CrsSottoprocesso> fasi= service.getFasi(idAudit);
				if(fasi != null || !fasi.isEmpty()){
					for(CrsSottoprocesso m: fasi){
						getModel().getFasiDropDown().add(m);
					}
				}
				
				
			}
		
		
		return SUCCESS;
	}
	
	
	public String searchRischiEnonConformitaNonConform(){
		
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
		
			long idAudit=user.getIdAudit();
			getModel().setIdAudit(idAudit);
			
			/*
			 * carica in tabella le non conf			 */
	
		List<AuAuditNonConfDto>l =service.getNonConf(idAudit, getModel().getiDisplayStart(), 
				getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),
				getModel().getsSortDir_0(),
				getModel().getsSearch());
		
			// COUNT RECORD
		 int total = service.countListaNonConf(idAudit,getModel()
					.getiDisplayStart(), getModel().getiDisplayLength(), getModel()
					.getiSortCol_0(), getModel().getsSortDir_0(), getModel()
					.getsSearch());

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
	
	public String  salvaNuovaNonConf(){
		HttpServletRequest request = ServletActionContext.getRequest();

		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		if (user!= null) {
		
			long idAudit=user.getIdAudit();
			AuMNonConf nc= getModel().getC();
			nc.setIdAudit(idAudit);
			service.salva(nc);
			
		}
		return SUCCESS;
	}
	
	public String 	eliminaNonConform(){
		
		//lista di id che arriva dai checkbox selezionati
		String idNonConformitaString = getModel().getListaIdNonConf();
		String [] idNonConfAndidFase= idNonConformitaString.split(",");
		List<String> idNonConf= new ArrayList<String>();
		for(int i=0; i< idNonConfAndidFase.length; i++){
			String [] idNc=idNonConfAndidFase[i].split("-");
			idNonConf.add(idNc[0]);
		}
		//lista che contiene tutte le nc selezionate
		List<AuMNonConf> ncDaEliminareSelezionate=new ArrayList<AuMNonConf>();
		
		//non conformita che non possono essere eliminate
		List<AuMNonConf> ncDaNonEliminare=new ArrayList<AuMNonConf>();
		
		//nc che possono essere eliminate
		List<AuMNonConf> ncDaEliminare=new ArrayList<AuMNonConf>();
		
		//riempie lista rischiDaElimanare
		for(int i=0; i< idNonConf.size();i++){
			long idNC=Long.valueOf(idNonConf.get(i)).longValue();
			AuMNonConf nonConfDaEliminare=  service.cerca( AuMNonConf.class, idNC);
			ncDaEliminareSelezionate.add(nonConfDaEliminare);
		}
		
		//controlla che non ci siano nc legame con una variante comportamentale
		//se ci sono vengono inseriti nella lista  ncDaNonEliminare
		for(AuMNonConf i : ncDaEliminareSelezionate){
			AuMNonConf nc= service.checkNcInVarComportamentale(i);
			if(nc != null){
				//aggiungo alla lista di nc da non eliminare la nc corrente
				ncDaNonEliminare.add(nc);
			}else{
				ncDaEliminare.add(i);
			}
			
		}
		//elimino nc in nc da elimanare
		for(AuMNonConf i : ncDaEliminare){
				service.remove(i);
			}
		
		//serve per mostrare lato client la notifica delle nc non eliminate
		getModel().setNcDaNonEliminare(ncDaNonEliminare);
			
		
		return SUCCESS;
	}
	
	/*
	 * controlla codice già esistente
	 */
	
	public String checkCodiceNC(){
		
			try{
				List<AuMNonConf> nc=service.checkCodiceNC();
				if(nc != null){
					for(AuMNonConf i : nc){
						getModel().getNc().add(i);
					}
				}
			
			}catch(Exception e){
				e.printStackTrace();
			}
		
		return SUCCESS;
	}

	@Override
	public ModelRischiENonConformitaNonConformita getModel() {
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
