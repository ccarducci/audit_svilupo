package it.tecnet.crs.componenti.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;

import it.tecnet.crs.componenti.jpa.model.CrsDocumentiMedia;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.jpa.model.CrsDominiValori;
import it.tecnet.crs.componenti.service.GestioneComponentiService;
import it.tecnet.crs.componenti.web.bean.CircolariInps;
import it.tecnet.crs.componenti.web.bean.DominiPaginator;
import it.tecnet.crs.componenti.web.bean.DominiValoriPaginator;
import it.tecnet.crs.util.datatables.DataTablesParamUtility;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class DominiValoriTableAction extends BaseAction implements ModelDriven<DominiValoriPaginator> {

	
	private static final long serialVersionUID = 1L;
	private DominiValoriPaginator model= new DominiValoriPaginator();
	private GestioneComponentiService gestioneComponentiService;
	
	
	public DominiValoriTableAction(GestioneComponentiService gestioneComponentiService){
		super();
		this.setGestioneComponentiService(gestioneComponentiService);
	}
	
	
	public String getDominiValori(){
		
		
		return SUCCESS;
	}
	
	public String getDominiValoriTable(){
		
		DataTablesParamUtility.loadParameters(getModel(),ActionContext.getContext().getParameters());
		 
		List<CrsDominiValori> returnList = gestioneComponentiService.getDominiValoriTable(getModel());
											
		int total = gestioneComponentiService.countAllDominiValoriTable(getModel());
		
		getModel().setiTotalDisplayRecords(total);
		getModel().setiTotalRecords(total);
		
		for(Object normativa : returnList){	
			getModel().getaaData().add(normativa);
		}
			

		return SUCCESS;
		
	
	}
	
	public String checkCodiceDominiValori(){
		
		try{
			String codice = getModel().getCodiceValore().trim();
			Long idDominiValori = getModel().getIdDominiValori();
			
			Boolean c=gestioneComponentiService.checkCodEsistenteDominiValori(codice);
			
			if(idDominiValori == 0){
				//la chiamata arriva da nuovo inserimento
				getModel().setCodEsistente(c);
				return SUCCESS;
			}else{
				//la chiamata arriva dal salvataggio della modifica
				if(c){
					//controllo che il codice trovato uguale non sia dell'oggetto che si sta modificando
					//(caso in cui il codice non venga modificato..)
					CrsDominiValori d = gestioneComponentiService.cerca(CrsDominiValori.class, idDominiValori);
					if(codice.equalsIgnoreCase(d.getCodiceValore().trim())){
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

	
	public String salvaDominiValori(){
		
		CrsDominiValori dominiValori = null;

		try {

			dominiValori = getModel().getDominiValori();
			
			gestioneComponentiService.salva(dominiValori);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
		
	}
	

	public String eliminaDominiValori(){
		
		String listaId=getModel().getListId();
		String[] idDaCancellare=listaId.split(",");
		for(String i : idDaCancellare){
			try{
				CrsDominiValori dom = gestioneComponentiService.cerca(CrsDominiValori.class, Long.valueOf(i));
				gestioneComponentiService.remove(dom);
			}catch(Exception e){
				e.printStackTrace();
			}

		}
		
		return SUCCESS;
	}
	
	
	@Override
	public DominiValoriPaginator getModel() {
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
