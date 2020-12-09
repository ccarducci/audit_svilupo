package it.tecnet.crs.indicatori.campagna;

import it.tecnet.crs.audit.jpa.dao.AuCalcolaIndicatoriDao;
import it.tecnet.crs.audit.jpa.dao.CampagnaDto;
import it.tecnet.crs.audit.jpa.dao.CampagnaMVarCompDto;
import it.tecnet.crs.audit.jpa.dao.SoglieDto;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class CalcoloIndicatoriCampagnaService {
	protected static Logger log = Logger
			.getLogger(CalcoloIndicatoriCampagnaService.class);

  
	private AuCalcolaIndicatoriDao auCalcolaIndicatoriDao;
	
	public void setAuCalcolaIndicatoriDao(AuCalcolaIndicatoriDao auCalcolaIndicatoriDao) {
		this.auCalcolaIndicatoriDao =  auCalcolaIndicatoriDao;
	}
	
	
	@Transactional
	private List<AU_C_VARCOMP> calcolaVarComp(long idCampagna){
		List<AU_C_VARCOMP> lista = new ArrayList<AU_C_VARCOMP>();
		
		System.out.println("--------------------------------- BEGIN VARCONF --------------------------------------------------");
		auCalcolaIndicatoriDao.deleteDatiCampagnaVarComp(idCampagna);
		List<AU_C_VARCOMP> listaAU_C_VARCOMP = new  ArrayList<AU_C_VARCOMP>();
		auCalcolaIndicatoriDao.getDatiCampagnaVarCompDto(idCampagna,listaAU_C_VARCOMP);

		for (AU_C_VARCOMP campagnaDto : listaAU_C_VARCOMP) {
			auCalcolaIndicatoriDao.insertDatiCampagnaVarComp(campagnaDto);
			lista.add(campagnaDto);
		}
		System.out.println("--------------------------------- END VARCONF --------------------------------------------------");
		return lista;
	}
	
	private List<AU_C_NONCONF> calcolaNonConf(long idCampagna, List<AU_C_VARCOMP> listaCVarComplista) {
		System.out.println("--------------------------------- BEGIN NONCONF --------------------------------------------------");
		auCalcolaIndicatoriDao.deleteDatiCampagnaNonCConf(idCampagna);
		List<AU_C_NONCONF> listaNonConf = new ArrayList<AU_C_NONCONF>();
		
		auCalcolaIndicatoriDao.getDatiCampagnaVNonConfDto(idCampagna,listaNonConf);
		
		for (AU_C_NONCONF nonConf : listaNonConf) {
			auCalcolaIndicatoriDao.insertDatiCampagnaNonConf(nonConf);
		}
		
		auCalcolaIndicatoriDao.updateNonConf(idCampagna);
		System.out.println("--------------------------------- END NONCONF --------------------------------------------------");
		return listaNonConf;
	}

	private List<AU_C_RISESPR>  calcolaRisEspr(long idCampagna) {
		System.out.println("--------------------------------- BEGIN RISESPR --------------------------------------------------");
		auCalcolaIndicatoriDao.deleteDatiRisEspr(idCampagna);
		List<CampagnaRisEsprDto> listCampagnaDto = auCalcolaIndicatoriDao.getDatiCampagnaCampagnaRisEsprDto(idCampagna);
		List<AU_C_RISESPR> listaESPR = new ArrayList<AU_C_RISESPR>();
		
		auCalcolaIndicatoriDao.getDatiCampagnaRisEsprDto(idCampagna,listaESPR);
		
		for (AU_C_RISESPR au_c_risespr : listaESPR) {
			auCalcolaIndicatoriDao.insertDatiCampagnaRisEspr(au_c_risespr);
		}
		System.out.println("--------------------------------- END RISESPR --------------------------------------------------");
		return listaESPR;
	}
	
	private void  calcolaRischio(long idCampagna){
		System.out.println("--------------------------------- BEGIN RIS  --------------------------------------------------");
		auCalcolaIndicatoriDao.deleteDatiRischio(idCampagna);
		
		List<CampagnaRischiofDto> listCampagnaDto = auCalcolaIndicatoriDao.getDatiCampagnaRischiofDto(idCampagna);
		System.out.println("--------------------------------- END RIS  --------------------------------------------------");
	}
	
	@Transactional
	public void calcolaIndicatoriCampagna(long idCampagna) {
		log.info("FINE CALCOLI CAMPAGNA " + idCampagna);
		
		List<AU_C_VARCOMP> listaCVarComplista = calcolaVarComp(idCampagna);
		List<AU_C_NONCONF> listaCNonConf = calcolaNonConf(idCampagna,listaCVarComplista);
		List<AU_C_RISESPR> listaESPR = calcolaRisEspr(idCampagna);
		calcolaRischio(idCampagna);
	
		auCalcolaIndicatoriDao.aggiornaStatoCampagna(idCampagna, "C");
		log.info("FINE CALCOLI CAMPAGNA " + idCampagna);
	}

	@Transactional
	public void aggiornaStatoCampagna(long idCampagna, String Stato) {
		auCalcolaIndicatoriDao.aggiornaStatoCampagna(idCampagna, Stato);
	}

}
