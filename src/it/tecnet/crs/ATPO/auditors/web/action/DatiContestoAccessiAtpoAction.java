package it.tecnet.crs.ATPO.auditors.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import it.tecnet.crs.ATPO.auditors.service.AuditorsAccessiATPOService;
import it.tecnet.crs.ATPO.auditors.web.beans.ModelAuditorsAccessiDatiContestoATPO;
import it.tecnet.crs.ATPO.auditors.web.dto.AtpoAuMContestoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.TipologiaPraticheCampioneDto;
import it.tecnet.crs.session.DatiUtente;
import it.tecnet.crs.web.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

public class DatiContestoAccessiAtpoAction extends BaseAction implements ModelDriven<ModelAuditorsAccessiDatiContestoATPO> {
	

	private static final long serialVersionUID = 1L;
	private AuditorsAccessiATPOService serviceAccessiATPO;
	private ModelAuditorsAccessiDatiContestoATPO model= new ModelAuditorsAccessiDatiContestoATPO();
	public DatiContestoAccessiAtpoAction( AuditorsAccessiATPOService serviceAccessiATPO){
		super();
		this.setServiceAccessiATPO(serviceAccessiATPO);
	}

	
	public String getTabDatiContesto(){
		HttpServletRequest request = ServletActionContext.getRequest();
		DatiUtente user = (DatiUtente) request.getSession().getAttribute("DatiUtente");
		if(user != null){
			//long idAudit=user.getIdAudit();
			long idCampagna= user.getIdCampagna();
			long idSessione= user.getIdSessione();
			try{
				AtpoAuMContestoDto c= serviceAccessiATPO.getDatiContesto(idSessione, idCampagna);
				if(c!=null){
					//calcolo numero pratiche chiuse
					Integer numeroPraticheChiuse= serviceAccessiATPO.getNumeroPraticheEsaminate(idSessione);
					if(numeroPraticheChiuse!=null){
						c.setNumeroPraticheDef(numeroPraticheChiuse);
					}
					
					getModel().setC(c);
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try{
				List<TipologiaPraticheCampioneDto> t= serviceAccessiATPO.getTipologiaPraticheCampione(idSessione);
				if(t !=null || t.size()!=0){
					for(TipologiaPraticheCampioneDto f : t){
						String descrizione= f.getDescrizione();
						if(descrizione.contains("Favorevole")){
							getModel().setNumeroFavorevole(f.getNumero());
						}else if(descrizione.contains("Parzialmente")){
							getModel().setNumeroParzFavorevole(f.getNumero());
						}else if(descrizione.contains("Sfavorevole")){
							getModel().setSfavorevole(f.getNumero());
						}else if(descrizione.contains("Dissenso")){
							getModel().setDissenso(f.getNumero());
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		
		return SUCCESS;
	}

	@Override
	public ModelAuditorsAccessiDatiContestoATPO getModel() {
		// TODO Auto-generated method stub
		return model;
	}


	public void setServiceAccessiATPO(AuditorsAccessiATPOService serviceAccessiATPO) {
		this.serviceAccessiATPO = serviceAccessiATPO;
	}


	public AuditorsAccessiATPOService getServiceAccessiATPO() {
		return serviceAccessiATPO;
	}

}
