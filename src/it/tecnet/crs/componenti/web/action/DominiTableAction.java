package it.tecnet.crs.componenti.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;

import it.tecnet.crs.componenti.jpa.model.CrsDocumentiMedia;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.service.GestioneComponentiService;
import it.tecnet.crs.componenti.web.bean.DominiPaginator;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import com.opensymphony.xwork2.ModelDriven;

public class DominiTableAction extends BaseAction implements ModelDriven<DominiPaginator> {

	
	private static final long serialVersionUID = 1L;
	private DominiPaginator model= new DominiPaginator();
	private GestioneComponentiService gestioneComponentiService;
	
	
	public DominiTableAction(GestioneComponentiService gestioneComponentiService){
		super();
		this.setGestioneComponentiService(gestioneComponentiService);
	}
	
	
	public String getDomini(){
		
		
		return SUCCESS;
	}
	
	public String searchDomini(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		long idUtente=user.getIdUtente();

		List<CrsDomini> dominiList =	gestioneComponentiService.getListDomini(getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());


		try{
			int total = gestioneComponentiService.countListDomini(getModel().getsSearch()).intValue();

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
	
	public String checkCodiceDominio(){
		
		try{
			String codice= getModel().getCodiceDominio().trim();
			Long idDominio= getModel().getIdDominio();
			Boolean c=gestioneComponentiService.checkCodEsistenteDominio(codice);
			if(idDominio == -1){
				//la chiamata arriva da nuovo inserimento
				getModel().setCodEsistente(c);
				return SUCCESS;
			}else{
				//la chiamata arriva dal salvataggio della modifica
				if(c){
					//controllo che il codice trovato uguale non sia dell'oggetto che si sta modificando
					//(caso in cui il codice non venga modificato..)
					CrsDomini d=gestioneComponentiService.cerca(CrsDomini.class, idDominio);
					if(codice.equalsIgnoreCase(d.getCodice().trim())){
						getModel().setCodEsistente(false);
					}else{
						getModel().setCodEsistente(true);
					}
				}

			}

		}catch(Exception e){
			e.printStackTrace();
		}


		return SUCCESS;
	}
	
	public String salvaDominio(){
		CrsDomini dominio= null;
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");

		try {

			dominio=getModel().getDominio();
			
			gestioneComponentiService.salva(dominio);


		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
		
		
	}
	
	public String searchDominioByID(){
		
		long idDominio= getModel().getIdDominio();
		try{
			CrsDomini cd=gestioneComponentiService.cerca(CrsDomini.class, idDominio);
			getModel().setDominio(cd);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String eliminaDominio(){
		
		String listaId=getModel().getListId();
		String[] idDaCancellare=listaId.split(",");
		for(String i : idDaCancellare){
			try{
				CrsDomini dom= gestioneComponentiService.cerca(CrsDomini.class, Long.valueOf(i) );
				gestioneComponentiService.remove(dom);
			}catch(Exception e){
				e.printStackTrace();
			}
			

		}
		
		return SUCCESS;
	}
	
	public String searchDominiFiltro(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");

		List<CrsDomini> dominiList = gestioneComponentiService.getListDominiFiltro(getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());


		try{
			int total = gestioneComponentiService.countListDominiFiltro(getModel().getsSearch()).intValue();

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
	
	
	@Override
	public DominiPaginator getModel() {
		// TODO Auto-generated method stub
		return model;
	}



	public void setGestioneComponentiService(GestioneComponentiService gestioneComponentiService) {
		this.gestioneComponentiService = gestioneComponentiService;
	}



	public GestioneComponentiService getGestioneComponentiService() {
		return gestioneComponentiService;
	}

}
