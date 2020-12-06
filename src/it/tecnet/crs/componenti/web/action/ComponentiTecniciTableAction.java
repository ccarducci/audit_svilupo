package it.tecnet.crs.componenti.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.componenti.jpa.model.CrsComponenteTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.jpa.model.CrsTplCompTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsTplDocMedia;
import it.tecnet.crs.componenti.service.GestioneComponentiService;
import it.tecnet.crs.web.action.BaseAction;
import it.tecnet.crs.web.beans.AppUser;
import it.tecnet.crs.componenti.web.bean.ComponentiTecniciPaginator;
import it.tecnet.crs.componenti.web.dto.CrsCompTecnicoDto;

import com.opensymphony.xwork2.ModelDriven;

public class ComponentiTecniciTableAction extends BaseAction implements ModelDriven<ComponentiTecniciPaginator> {


	private static final long serialVersionUID = 1L;
	private ComponentiTecniciPaginator model= new ComponentiTecniciPaginator();
	private GestioneComponentiService gestioneComponentiService;


	public ComponentiTecniciTableAction(GestioneComponentiService gestioneComponentiService){
		super();
		this.setGestioneComponentiService(gestioneComponentiService);
	}


	public String getCompTecnici(){

		
		try{
			//popolo l'aside "tipo documento"
			List<CrsTplCompTecnico>list=gestioneComponentiService.getTplCompTec();
			getModel().setListCompTec(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String searchCompTecnici(){

		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		long idUtente=user.getIdUtente();
		try{
			List<CrsCompTecnicoDto> compTecniciList =	gestioneComponentiService.getListCompTecnico(getModel().getiDisplayStart(),getModel().getiDisplayLength(),
					getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());

			int total = gestioneComponentiService.countListCompTecnico(getModel().getsSearch()).intValue();

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(compTecniciList==null){
				return SUCCESS;
			}

			for(Object o : compTecniciList){	
				getModel().getAaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}

		return SUCCESS;
	}

	public String checkCodiceCompTec(){

		try{
			String codice= getModel().getCodiceCompTecnico().trim();
			Long idCompTec= getModel().getIdCompTecnico();
			Boolean c=gestioneComponentiService.checkCodEsistenteCompTecnico(codice);
			if(idCompTec == -1){
				//la chiamata arriva da nuovo inserimento
				getModel().setCodEsistente(c);
				return SUCCESS;
			}else{
				//la chiamata arriva dal salvataggio della modifica
				if(c){
					//controllo che il codice trovato uguale non sia dell'oggetto che si sta modificando
					//(caso in cui il codice non venga modificato..)
					CrsComponenteTecnico d=gestioneComponentiService.cerca(CrsComponenteTecnico.class, idCompTec);
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

	public String salvaCompTecnico(){
		CrsComponenteTecnico ct= null;
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");

		try {

			ct=getModel().getCompTecnico();

			gestioneComponentiService.salva(ct);


		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;

	}

	public String eliminaCompTec(){

		String listaId=getModel().getListId();
		String[] idDaCancellare=listaId.split(",");
		for(String i : idDaCancellare){
			try{
				CrsComponenteTecnico ct= gestioneComponentiService.cerca(CrsComponenteTecnico.class, Long.valueOf(i) );
				gestioneComponentiService.remove(ct);
			}catch(Exception e){
				e.printStackTrace();
			}


		}

		return SUCCESS;
	}

	public String searchCompTecniciStorico(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AppUser user=(AppUser)request.getSession().getAttribute("AppUser");
		long idUtente=user.getIdUtente();

		List<CrsCompTecnicoDto> compTecniciStoricoList =	gestioneComponentiService.getListCompTecnicoStorico(getModel().getiDisplayStart(),getModel().getiDisplayLength(),
				getModel().getiSortCol_0(),getModel().getsSortDir_0(),getModel().getsSearch());


		try{
			int total = gestioneComponentiService.countListCompTecnicoStorico(getModel().getsSearch()).intValue();

			getModel().setiTotalDisplayRecords(total);
			getModel().setiTotalRecords(total);

			if(compTecniciStoricoList==null){
				return SUCCESS;
			}

			for(Object o : compTecniciStoricoList){	
				getModel().getAaData().add(o);

			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
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