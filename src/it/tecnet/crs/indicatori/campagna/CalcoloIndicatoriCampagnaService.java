package it.tecnet.crs.indicatori.campagna;

import it.tecnet.crs.audit.jpa.dao.AuCalcolaIndicatoriDao;
import it.tecnet.crs.audit.jpa.dao.CampagnaDto;
import it.tecnet.crs.audit.jpa.dao.CampagnaMVarCompDto;
import it.tecnet.crs.audit.web.dto.CalcoloIndicatoriRiepilogoPraticheNonConfFasi;
import it.tecnet.crs.indicatori.pratica.NoPraticheException;
import it.tecnet.crs.jpa.model.AuInccDes;
import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.jpa.model.AuSNonconf;
import it.tecnet.crs.jpa.model.AuSPraCalIndLog;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSPraticaRis;
import it.tecnet.crs.jpa.model.AuSPraticaVarcomp;
import it.tecnet.crs.jpa.model.AuSRischio;
import it.tecnet.crs.jpa.model.AuSRisespr;
import it.tecnet.crs.jpa.model.AuSRisrag;
import it.tecnet.crs.jpa.model.AuSSessione;
import it.tecnet.crs.jpa.model.AuSTempi;
import it.tecnet.crs.jpa.model.AuSTesito;
import it.tecnet.crs.jpa.model.AuSTfascicolo;
import it.tecnet.crs.jpa.model.AuSTvalori;
import it.tecnet.crs.jpa.model.AuSVarcomp;
import it.tecnet.crs.jpa.model.AuTdocmanc;
import it.tecnet.crs.jpa.model.AuTplIsnc;
import it.tecnet.crs.jpa.model.AuTplTipologiche;
import it.tecnet.crs.report.service.ReportPDFService;
import it.tecnet.crs.util.SessioneUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class CalcoloIndicatoriCampagnaService {
	protected static Logger log = Logger
			.getLogger(CalcoloIndicatoriCampagnaService.class);

  
	private AuCalcolaIndicatoriDao auCalcolaIndicatoriDao;
	
	public void setAuCalcolaIndicatoriDao(AuCalcolaIndicatoriDao auCalcolaIndicatoriDao) {
		this.auCalcolaIndicatoriDao =  auCalcolaIndicatoriDao;
	}
	
	private AU_C_VARCOMP getAU_C_VARCOMP(Long idMVarConf,List<AU_C_VARCOMP> listaAU_C_VARCOMP){
		for (AU_C_VARCOMP au_c_varcomp : listaAU_C_VARCOMP) {
			if(au_c_varcomp.getID_M_VARCONF().equals(idMVarConf))return au_c_varcomp;
		}
		return null;
	}
	
	@Transactional
	private void calcolaVarComp(long idCampagna){
		auCalcolaIndicatoriDao.deleteDatiCampagnaVarComp(idCampagna);
		List<CampagnaMVarCompDto> listaSumIdMVarComp = auCalcolaIndicatoriDao.getSumiCampagnaByIdMVarCompDto(idCampagna);
		
		List<AU_C_VARCOMP> listaAU_C_VARCOMP = new ArrayList<AU_C_VARCOMP>();
		List<CampagnaDto> listCampagnaDto = auCalcolaIndicatoriDao.getDatiCampagnaVarCompDto(idCampagna);
		// AU_C_VARCOMP 
		for (CampagnaDto campagnaDto : listCampagnaDto) {
			AU_C_VARCOMP varConf = getAU_C_VARCOMP(campagnaDto.getID_M_VARCOMP(), listaAU_C_VARCOMP);
			if (varConf == null){
				AU_C_VARCOMP item = new AU_C_VARCOMP();
				item.setDATA_FINE(campagnaDto.getDATA_FINE());
				item.setDATA_INIZIO(campagnaDto.getDATA_INIZIO());
				item.setID_C_CAMPAGNA(idCampagna);
				item.setID_M_NON_CONF(campagnaDto.getID_M_NONCONF());
				item.setID_M_VARCONP(campagnaDto.getID_M_VARCOMP());
				item.setNUM(campagnaDto.getNUM());
				item.setPERC_PESATA(0D);
				item.setPERC_SU_PS(0D);
				listaAU_C_VARCOMP.add(item);
			}else{
				Integer num = varConf.getNUM();
				varConf.setNUM(num + campagnaDto.getNUM());
			}
		}
		
		for (AU_C_VARCOMP campagnaDto : listaAU_C_VARCOMP) {
			auCalcolaIndicatoriDao.insertDatiCampagnaVarComp(campagnaDto);
		}
		
		
	}
	
	private void  calcolaRischio(long idCampagna){
		
	}
	
	private void calcolaNonConf(long idCampagna) {
		// TODO Stub di metodo generato automaticamente
		
	}
	
	private void calcolaRisEspr(long idCampagna) {
		// TODO Stub di metodo generato automaticamente
		
	}
	
	@Transactional
	public void calcolaIndicatoriCampagna(long idCampagna) {
		log.info("FINE CALCOLI CAMPAGNA " + idCampagna);
		
		calcolaNonConf(idCampagna);
		calcolaVarComp(idCampagna);
		calcolaRischio(idCampagna);
		calcolaRisEspr(idCampagna);
		
		log.info("FINE CALCOLI CAMPAGNA " + idCampagna);
	}




}
