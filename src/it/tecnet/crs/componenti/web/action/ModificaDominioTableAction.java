package it.tecnet.crs.componenti.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.componenti.jpa.model.CrsAssDominiDocumenti;
import it.tecnet.crs.componenti.jpa.model.CrsDocumentiMedia;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.service.GestioneComponentiService;
import it.tecnet.crs.componenti.web.bean.DominiPaginator;
import it.tecnet.crs.componenti.web.dto.CrsDocumentiAllegatiDto;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class ModificaDominioTableAction extends BaseAction implements ModelDriven<DominiPaginator> {



	private static final long serialVersionUID = 1L;
	private DominiPaginator model= new DominiPaginator();
	private GestioneComponentiService gestioneComponentiService;


	public String modificaDominio(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idDominio= getModel().getIdDominio();
		user.setIdDominio(idDominio);
		try{
			
			CrsDomini d= gestioneComponentiService.cerca(CrsDomini.class, idDominio);
			getModel().setDominio(d);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public String getAllegati(){
	
		
		return SUCCESS;
	}
	
	public String searchAllegatiDominio(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idDominio=user.getIdDominio();

		List<CrsDocumentiAllegatiDto> dominiList =	gestioneComponentiService.getAllegatiDominio(idDominio, getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());


		try{
			int total = gestioneComponentiService.countListAllegatiDominio(idDominio,getModel().getsSearch()).intValue();

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(dominiList==null){
				return SUCCESS;
			}

			for(Object o : dominiList){	
				getModel().getAaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;
		
	}
	
	public String getDocNoAllegatiAlDominio(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idDominio=user.getIdDominio();

		List<CrsDocumentiMedia> dominiList =	gestioneComponentiService.getNonAllegatiDominio(idDominio, getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());


		try{
			int total = gestioneComponentiService.countListNonAllegatiDominio(idDominio,getModel().getsSearch()).intValue();

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(dominiList==null){
				return SUCCESS;
			}

			for(Object o : dominiList){	
				getModel().getAaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;
		

	}
	

	public String addAllegatoDominio(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idDominio=user.getIdDominio();
		long idFile=getModel().getIdFile();
		CrsAssDominiDocumenti allegato= new CrsAssDominiDocumenti();
		allegato.setIdDocumento(idFile);
		allegato.setIdDominio(idDominio);
		try{
			gestioneComponentiService.salva(allegato);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String eliminaAllegato(){
		
		String listaId=getModel().getListId();
		String[] idDaCancellare=listaId.split(",");
		for(String i : idDaCancellare){
			try{
				CrsAssDominiDocumenti docM= gestioneComponentiService.cerca(CrsAssDominiDocumenti.class, Long.valueOf(i) );
				gestioneComponentiService.remove(docM);
			}catch(Exception e){
				e.printStackTrace();
			}
			

		}

		return SUCCESS;
	}

	public ModificaDominioTableAction(GestioneComponentiService gestioneComponentiService){
		super();
		this.setGestioneComponentiService(gestioneComponentiService);
	}


	public void setGestioneComponentiService(GestioneComponentiService gestioneComponentiService) {
		this.gestioneComponentiService = gestioneComponentiService;
	}


	public GestioneComponentiService getGestioneComponentiService() {
		return gestioneComponentiService;
	}


	@Override
	public DominiPaginator getModel() {
		// TODO Auto-generated method stub
		return model;
	}
}
