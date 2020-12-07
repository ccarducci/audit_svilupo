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
	
	private AU_C_VARCOMP getAU_C_VARCOMP(Long idMVarConf,List<AU_C_VARCOMP> listaAU_C_VARCOMP){
		for (AU_C_VARCOMP au_c_varcomp : listaAU_C_VARCOMP) {
			if(au_c_varcomp.getID_M_VARCONP().equals(idMVarConf))return au_c_varcomp;
		}
		return null;
	}
	
	private CampagnaMVarCompDto getSumIdMVarComp(long id, List<CampagnaMVarCompDto> listaSumIdMVarComp){
		for (CampagnaMVarCompDto campagnaMVarCompDto : listaSumIdMVarComp) {
			if(campagnaMVarCompDto.getID_M_VARCOMP().equals(id))return campagnaMVarCompDto;
		}
		return null;
	}
	
	private SoglieDto findSglia(Long id_m_non_conf,List<SoglieDto> soglie) {
		for (SoglieDto soglieDto : soglie) {
			if(soglieDto.getID_M_NONCONF().equals(id_m_non_conf))return soglieDto;
		}
		return null;
	}
	
	@Transactional
	private List<AU_C_VARCOMP> calcolaVarComp(long idCampagna){
		List<AU_C_VARCOMP> lista = new ArrayList<AU_C_VARCOMP>();
		
		System.out.println("--------------------------------- BEGIN VARCONF --------------------------------------------------");
		auCalcolaIndicatoriDao.deleteDatiCampagnaVarComp(idCampagna);
		List<AU_C_VARCOMP> listaAU_C_VARCOMP = new  ArrayList<AU_C_VARCOMP>();
		auCalcolaIndicatoriDao.getSumiCampagnaByIdMVarCompDto(idCampagna,listaAU_C_VARCOMP);
		
		/*
		List<CampagnaMVarCompDto> listaSumIdMVarComp = auCalcolaIndicatoriDao.getSumiCampagnaByIdMVarCompDto(idCampagna);
		List<SoglieDto> soglie = auCalcolaIndicatoriDao.getSoglieTipologica();
		
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
				item.setPERC_PESATA(campagnaDto.getPERC_PESATA().doubleValue());
				item.setPERC_SU_PS(0D);
				item.setID_FASE(campagnaDto.getID_FASE());
				listaAU_C_VARCOMP.add(item);
			}else{
				Integer num = varConf.getNUM();
				varConf.setNUM(num + campagnaDto.getNUM());
			}
		}
		
		
		for (AU_C_VARCOMP campagnaDto : listaAU_C_VARCOMP) {
			CampagnaMVarCompDto comp = getSumIdMVarComp(campagnaDto.getID_M_NON_CONF(),listaSumIdMVarComp);
			campagnaDto.setPERC_SU_PS(campagnaDto.getNUM().doubleValue()/comp.getSUM().doubleValue());
		}
		
		for (AU_C_VARCOMP  campagnaDto: listaAU_C_VARCOMP) {
			SoglieDto soglia = findSglia(campagnaDto.getID_M_NON_CONF(),soglie);
			if(soglia != null){
				campagnaDto.setPERC_PESATA(soglia.getSOGLIA() * campagnaDto.getPERC_SU_PS());
			}
		}
		*/
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
		
		/*
		List<CampagnaNonConfDto> 
		
		for (CampagnaNonConfDto item : listCampagnaDto) {
			AU_C_NONCONF nonConf = findNonConfByID_M_NONCONF(item.getID_M_NONCONF(),listaNonConf);
			if(nonConf == null) {
				nonConf = new AU_C_NONCONF();
				if (item.getDATA_FINE()!=null)nonConf.setDATA_FINE(item.getDATA_FINE());
				if (item.getDATA_INIZIO()!=null)nonConf.setDATA_INIZIO(item.getDATA_INIZIO());
				if (item.getID_CAMPAGNA()!=null)nonConf.setID_C_CAMPAGNA(item.getID_CAMPAGNA());
				if (item.getID_M_NONCONF()!=null)nonConf.setID_M_NONCONF(item.getID_M_NONCONF());
				if (item.getPESO_NONCONF()!=null)nonConf.setPESO_NONCONF(item.getPESO_NONCONF());
				if (item.getCODICE()!= null)nonConf.setCODICE(item.getCODICE());
				Integer num = getNumFrom_AU_C_VARCOMP_By_ID_M_NONCONF(nonConf.getID_M_NONCONF(),listaCVarComplista);
				nonConf.setNUM(num);
				nonConf.setVALORE_INCC(getPercPesataFrom_AU_C_VARCOMP_By_ID_M_NONCONF(nonConf.getID_M_NONCONF(),listaCVarComplista));
				nonConf.setID_FASE(item.getID_FASE());
				
				listaNonConf.add(nonConf);
			}
		}
		
		Long totPesoNonConf = sumPesoNonConf(listaNonConf);
		
		for (AU_C_NONCONF nonConf : listaNonConf) {
			// VALORE_INCC di AU_C_NONCONF * PESO_NON_CONF di AU_C_NONCONF / (somma PESO_NON_CONF di AU_C_NONCONF per ID_C_CAMPAGNA di AU_C_NONCONF uguale)
			try{
				if (totPesoNonConf >0){
					Double vALORE_Pesato = ( nonConf.getVALORE_INCC() * nonConf.getPESO_NONCONF()) / totPesoNonConf;
					nonConf.setVALORE_PESATO(vALORE_Pesato);
				}
			}catch(Exception ex){
				
			}
		}
		
		for (AU_C_NONCONF nonConf : listaNonConf) {
			// VALORE_INCC di AU_C_NONCONF * PESO_NON_CONF di AU_C_NONCONF / (somma PESO_NON_CONF di AU_C_NONCONF
			// per ID_FASE di AU_C_NONCONF uguale)
			Double ret = nonConf.getVALORE_INCC() * nonConf.getPESO_NONCONF();
			Double ret2 = sumPesoByIdFase(listCampagnaDto,nonConf.getID_FASE());
			if(ret2 != null && ret2 != 0)ret = ret / ret2;
			nonConf.setVALORE_PESATO_BASE(ret);
		}
		*/
		for (AU_C_NONCONF nonConf : listaNonConf) {
			auCalcolaIndicatoriDao.insertDatiCampagnaNonConf(nonConf);
		}
		
		System.out.println("--------------------------------- END NONCONF --------------------------------------------------");
		return listaNonConf;
	}
	
	private Double sumPesoByIdFase(List<CampagnaNonConfDto> listCampagnaDto, Long idFase) {
		Double ret = 0D;
		
		for (CampagnaNonConfDto item : listCampagnaDto) {
			if(item.getID_FASE().equals(idFase)) {
				ret += item.getPESO_NONCONF();
			}
		}
		
		return ret;
	}
	
	
	private Long sumPesoNonConf(List<AU_C_NONCONF> listaNonConf ){
		Long num = 0L;
		for (AU_C_NONCONF au_c_nonconf : listaNonConf) {
			//if(au_c_nonconf.getPESO_NONCONF()!=null)num += au_c_nonconf.getPESO_NONCONF();
		}
		return num;
	}
	
	private AU_C_NONCONF findNonConfByID_M_NONCONF(Long ID_M_NONCONF , List<AU_C_NONCONF> listaNonConf) {
		for (AU_C_NONCONF item : listaNonConf) {
			if(item.getID_M_NONCONF().equals(ID_M_NONCONF)) return item;
		}
		return null;
	}
	
	private Double getPercPesataFrom_AU_C_VARCOMP_By_ID_M_NONCONF(long ID_M_NONCONF,List<AU_C_VARCOMP> listaCVarComplista){
		Double perRet = 0D;
		for (AU_C_VARCOMP au_C_VARCOMP : listaCVarComplista) {
			if(au_C_VARCOMP.getID_M_NON_CONF().equals(ID_M_NONCONF))
					perRet += au_C_VARCOMP.getPERC_PESATA();
		}
		return perRet;
	}
	
	private Integer getNumFrom_AU_C_VARCOMP_By_ID_M_NONCONF(long ID_M_NONCONF,List<AU_C_VARCOMP> listaCVarComplista){
		Integer perRet = 0;
		for (AU_C_VARCOMP au_C_VARCOMP : listaCVarComplista) {
			if(au_C_VARCOMP.getID_M_NON_CONF().equals(ID_M_NONCONF))
					perRet += au_C_VARCOMP.getNUM_VC();
		}
		return perRet;
	}
	
	private void  calcolaRischio(long idCampagna){
		auCalcolaIndicatoriDao.deleteDatiRischio(idCampagna);
		
		List<CampagnaRischiofDto> listCampagnaDto = auCalcolaIndicatoriDao.getDatiCampagnaRischiofDto(idCampagna);
	}
	
	private List<AU_C_RISESPR>  calcolaRisEspr(long idCampagna) {
		auCalcolaIndicatoriDao.deleteDatiRisEspr(idCampagna);
		List<CampagnaRisEsprDto> listCampagnaDto = auCalcolaIndicatoriDao.getDatiCampagnaCampagnaRisEsprDto(idCampagna);
		List<AU_C_RISESPR> listaESPR = new ArrayList<AU_C_RISESPR>();
		
		auCalcolaIndicatoriDao.getDatiCampagnaRisEsprDto(idCampagna,listaESPR);
		
		for (AU_C_RISESPR au_c_risespr : listaESPR) {
			auCalcolaIndicatoriDao.insertDatiCampagnaRisEspr(au_c_risespr);
		}
		
		return listaESPR;
	}
	
	@Transactional
	public void calcolaIndicatoriCampagna(long idCampagna) {
		log.info("FINE CALCOLI CAMPAGNA " + idCampagna);
		
		List<AU_C_VARCOMP> listaCVarComplista = calcolaVarComp(idCampagna);
		List<AU_C_NONCONF> listaCNonConf = calcolaNonConf(idCampagna,listaCVarComplista);
		List<AU_C_RISESPR> listaESPR = calcolaRisEspr(idCampagna);
		calcolaRischio(idCampagna);
	
		log.info("FINE CALCOLI CAMPAGNA " + idCampagna);
	}




}
