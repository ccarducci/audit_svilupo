package it.tecnet.crs.componenti.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.componenti.jpa.model.CrsAssCompTecnDocumenti;
import it.tecnet.crs.componenti.jpa.model.CrsAssDominiDocumenti;
import it.tecnet.crs.componenti.jpa.model.CrsComponenteTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsDocumentiMedia;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.jpa.model.CrsTplCompTecnico;
import it.tecnet.crs.componenti.service.GestioneComponentiService;
import it.tecnet.crs.componenti.web.bean.ComponentiTecniciPaginator;
import it.tecnet.crs.componenti.web.dto.CrsDocumentiAllegatiDto;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class ModificaComponentiTecniciTableAction extends BaseAction implements ModelDriven<ComponentiTecniciPaginator> {

	private static final long serialVersionUID = 1L;

	private ComponentiTecniciPaginator model= new ComponentiTecniciPaginator();
	
	private GestioneComponentiService gestioneComponentiService;
	
	
	public ModificaComponentiTecniciTableAction(GestioneComponentiService gestioneComponentiService){
		super();
		this.setGestioneComponentiService(gestioneComponentiService);
	}
	
	public String modificaCompTecnico(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idCompTecnico= getModel().getIdCompTecnico();
		user.setIdComponenteTecnico(idCompTecnico);
		try{
			
			CrsComponenteTecnico d= gestioneComponentiService.cerca(CrsComponenteTecnico.class, idCompTecnico);
			CrsTplCompTecnico c= gestioneComponentiService.cerca(CrsTplCompTecnico.class, d.getIdTipoCompTecnico());
			getModel().setTipoCompTecnico(c);
		
			getModel().setCompTecnico(d);
			
				//popolo l'aside "tipo documento"
				List<CrsTplCompTecnico>list=gestioneComponentiService.getTplCompTec();
				getModel().setListCompTec(list);
		
		}catch(Exception e){
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public String getAllegatiCT(){
		
		return SUCCESS;
	}
	
	public String searchAllegatiCompTec(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idCT=user.getIdComponenteTecnico();

		List<CrsDocumentiAllegatiDto> dominiList =	gestioneComponentiService.getAllegatiCt(idCT, getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());


		try{
			int total = gestioneComponentiService.countListAllegatiCt(idCT,getModel().getsSearch()).intValue();

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
	
	public String getCtNoAllegatiAlDominio(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idCt=user.getIdComponenteTecnico();

		List<CrsDocumentiMedia> dominiList =	gestioneComponentiService.getNonAllegatiCompTec(idCt, getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());


		try{
			int total = gestioneComponentiService.countListNonAllegatiCompTec(idCt,getModel().getsSearch()).intValue();

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
	
	public String addAllegatoCompTec(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)request.getSession().getAttribute("DatiUtente");
		long idCt=user.getIdComponenteTecnico();
		long idFile=getModel().getIdFile();
		CrsAssCompTecnDocumenti allegato= new CrsAssCompTecnDocumenti();
		allegato.setIdDocumento(idFile);
		allegato.setIdCompTec(idCt);
		try{
			gestioneComponentiService.salva(allegato);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	
	}
	
	public String eliminaAllegatoCt(){
		String listaId=getModel().getListId();
		String[] idDaCancellare=listaId.split(",");
		for(String i : idDaCancellare){
			try{
				CrsAssCompTecnDocumenti docCt= gestioneComponentiService.cerca(CrsAssCompTecnDocumenti.class, Long.valueOf(i) );
				gestioneComponentiService.remove(docCt);
			}catch(Exception e){
				e.printStackTrace();
			}
			

		}

		return SUCCESS;
	}
	@Override
	public ComponentiTecniciPaginator getModel() {
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
