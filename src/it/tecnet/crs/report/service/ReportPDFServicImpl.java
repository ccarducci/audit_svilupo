package it.tecnet.crs.report.service;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPratiche;
import it.tecnet.crs.report.jpa.dao.ReportPDFDao;
import it.tecnet.crs.report.web.dto.ReportAccessoPDFDto;
import it.tecnet.crs.util.ModelToDto;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ReportPDFServicImpl implements ReportPDFService {
 
	protected static Logger log = Logger.getLogger(ReportPDFServicImpl.class);
	private ReportPDFDao reportPDFDao;
	public List<Object[]> getReportErmPDF(long idSSessione) {
		List<Object[]> obj = reportPDFDao.getReportErmPDF(idSSessione);
		return obj;
	}
	public List<AtpoPratiche> getReportAllegatiPDF(String sede, long idSessione) {
		return  reportPDFDao.getReportAllegatiPDF(sede,idSessione);
		
	}
	
	public ReportAccessoPDFDto getAllegatoReportAccessoPDF(long idSSessione) {
		ReportAccessoPDFDto accesso = new ReportAccessoPDFDto();
		Object[] obj = reportPDFDao.getAllegatoReportAccessoPDF(idSSessione);

		if (obj != null) {
			accesso = ModelToDto.modelToAllegatoReportAccessoPDFDto(obj);
		}

		return accesso;
	}
	public ReportAccessoPDFDto getReportAccessoPDF(long idSSessione) {
		ReportAccessoPDFDto accesso = new ReportAccessoPDFDto();
		Object[] obj = reportPDFDao.getReportAccessoPDF(idSSessione);

		if (obj != null) {
			accesso = ModelToDto.modelToReportAccessoPDFDto(obj);
		}

		return accesso;
	}

	public List<ReportAccessoPDFDto> getRiepilogoIstanze(long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRiepilogoIstanze(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRiepilogoIstanzePDF(obj));
		}

		return lista;
	}

	public List<ReportAccessoPDFDto> getRiepilogoFasi(long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRiepilogoFasi(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRiepilogoFasiPDF(obj));
		}

		return lista;
	}

	public List<ReportAccessoPDFDto> getMNonConfByIdFase(long idFase,
			long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getMNonConfByIdFase(idFase,
				idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToMNonConfPDF(obj));
		}
		
		return lista;
	}

	public List<ReportAccessoPDFDto> getVarCompByIdMNonConf(Long sessione,
			Long idFase, Long idMNonConf) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getVarCompByIdMNonConf(sessione,
				idFase, idMNonConf);

		for (Object[] obj : objList) {

			lista.add(ModelToDto.modelToVarCompPDF(obj));
		}
		
		return lista;
	}

	public List<ReportAccessoPDFDto> getVarCompByIdMNonConf(long idMNonConf) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao
				.getVarCompByIdMNonConf(idMNonConf);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToVarCompPDF(obj));
		}

		return lista;
	}

	public List<ReportAccessoPDFDto> getRiepilogoRischi(long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRiepilogoRischi(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRiepilogoRischiPDF(obj));
		}

		return lista;
	}

	public List<ReportAccessoPDFDto> getRisEsprByIdMNonConf(long idMRischio,
			long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRisEsprByIdMNonConf(
				idMRischio, idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRisEsprPDF(obj));
		}

		return lista;
	}

	public List<ReportAccessoPDFDto> getRiepilogoFascicolo(long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao
				.getRiepilogoFascicolo(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRiepilogoFascicoloPDF(obj));
		}

		return lista;
	}

	public List<ReportAccessoPDFDto> getRiepilogoDocManc(long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRiepilogoDocManc(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRiepilogoFascicoloPDF(obj));
		}

		return lista;
	}

	public List<ReportAccessoPDFDto> getEsitoByTipoDifesa(long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getEsitoByTipoDifesa(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToEsitoDifesaPDF(obj));
		}

		return lista;
	}

	public List<ReportAccessoPDFDto> getEsitoByTipoDifesaWithCode(
			long idSSessione) {

		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getEsitoByTipoDifesa(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToEsitoDifesaWithCodePDF(obj));
		}
		return lista;
	}

	public List<ReportAccessoPDFDto> getEsitoByTipoDifesaWithCodeAndDissenso(
			long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao
				.getEsitoByTipoDifesaWithDissenso(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToEsitoDifesaWithCodePDF(obj));
		}
		
		return lista;
	}

	public List<ReportAccessoPDFDto> getEsitoByTipoDifesaWithCodeAndDissensoByCampagna(
			long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao
				.getEsitoByTipoDifesaWithDissensoByCampagna(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToEsitoDifesaWithCodePDF(obj));
		}
		
		return lista;
	}
	
	
	public List<ReportAccessoPDFDto> getRiepilogoGiudizi(long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRiepilogoGiudizi(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRiepilogoGiudiziPDF(obj));
		}
		
		return lista;
	}

	@Override
	public List<ReportAccessoPDFDto> getRiepilogoGiudiziByCampagna(
			long idCampagna) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRiepilogoGiudiziByCampagna(idCampagna);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRiepilogoGiudiziPDF(obj));
		}
		
		return lista;
	}

	public List<ReportAccessoPDFDto> getRisultatiByTempo(long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRisultatiByTempo(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRisultatiByTempoPDF(obj));
		}

		return lista;
	}

	public List<String> getListaAuditors(long idSSessione) {
		return reportPDFDao.getListaAuditors(idSSessione);
	}

	public List<ReportAccessoPDFDto> getGiudiziDefinitiviSisco(
			long idSSessione, String tipoDifesa) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getGiudiziDefinitiviSisco(
				idSSessione, tipoDifesa);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToEsitoDifesaPDF(obj));
		}

		return lista;
	}

	@Override
	public List<ReportAccessoPDFDto> getGiudiziDefinitiviSiscoByCampagna(
			long idCampagna, String tipoDifesa) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getGiudiziDefinitiviSiscoByCampagna(idCampagna, tipoDifesa);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToEsitoDifesaPDF(obj));
		}

		return lista;
	}

	
	@Override
	public List<ReportAccessoPDFDto> getGiudiziDefinitiviSiscoByCampagnaAndSede(
			long idCampagna, String tipoDifesa, String codSede) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getGiudiziDefinitiviSiscoByCampagnaAndSede(idCampagna, tipoDifesa,codSede);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToEsitoDifesaPerSedePDF(obj));
		}

		return lista;
	}
	
	public List<ReportAccessoPDFDto> getGiudiziDefinitiviSiscoByCampagnaAndSedeWithOutFavorevoli(
			long idCampagna, String tipoDifesa, String codSede) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getGiudiziDefinitiviSiscoByCampagnaAndSedeWithOutFavorevoli(idCampagna, tipoDifesa,codSede);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToEsitoDifesaPerSedePDF(obj));
		}

		return lista;
	}
	
	public int getGiudiziDefinitiviSiscoByCampagnaTotale(long idCampagna, String tipoDifesa, String codSede){
		return reportPDFDao.getGiudiziDefinitiviSiscoByCampagnaTotale( idCampagna,  tipoDifesa,  codSede);
	}
	
	public List<Object[]> getGiudiziDefinitiviEtichette(){
		return reportPDFDao.getGiudiziDefinitiviEtichette();
	}
	
	public List<ReportAccessoPDFDto> getTipoDifesaIncompleta(long idSSessione,
			String tipoDifesa) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getTipoDifesaIncompleta(
				idSSessione, tipoDifesa);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToTipoDifesaIncompletaPDF(obj));
		}

		return lista;
	}

	
	
	public ReportPDFDao getReportPDFDao() {
		return reportPDFDao;
	}

	public void setReportPDFDao(ReportPDFDao reportPDFDao) {
		this.reportPDFDao = reportPDFDao;
	}
	@Override
	public String[] getElencoRischi() {
		return  reportPDFDao.getElencoRischi();
	}
	
	// -------------------------------------------------------------------------------------------
	// NUOVI REPORT ANGELO ACCESSO
	@Override
	public ReportAccessoPDFDto getReportAccessoAnnualePDF(long idCampagna) {
		ReportAccessoPDFDto accesso = new ReportAccessoPDFDto();
		Object[] obj = reportPDFDao.getReportAccessoPDFAnnuale(idCampagna);

		if (obj != null) {
			accesso = ModelToDto.modelToReportAccessoPDFDto(obj);
		}

		return accesso;
	}
	@Override
	public List<ReportAccessoPDFDto> getRiepilogoIstanzeAnnuale(long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRiepilogoIstanzeAnnuale(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRiepilogoIstanzePDF(obj));
		}

		return lista;
	}
	@Override
	public List<ReportAccessoPDFDto> getRiepilogoFasiAnnuale(long idCampagna) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRiepilogoFasiAnnuale(idCampagna);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRiepilogoFasiPDF(obj));
		}

		return lista;
	}
	@Override
	public List<ReportAccessoPDFDto> getRiepilogoRischiAnnuale(long idCampagna) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRiepilogoRischiAnnuale(idCampagna);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRiepilogoRischiPDF(obj));
		}

		return lista;
	}
	@Override
	public List<ReportAccessoPDFDto> getRisEsprByIdMNonConfAnnuale(Long idMRischio, long idCampagna) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRisEsprByIdMNonConfAnnuale(
				idMRischio, idCampagna);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRisEsprPDF(obj));
		}

		return lista;
	}
	@Override
	public List<ReportAccessoPDFDto> getRiepilogoFascicoloAnnuale(long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao
				.getRiepilogoFascicoloAnnuale(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRiepilogoFascicoloPDF(obj));
		}

		return lista;
	}
	
	@Override
	public List<ReportAccessoPDFDto> getRiepilogoDocMancAnnuale(long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRiepilogoDocMancAnnuale(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRiepilogoFascicoloPDF(obj));
		}

		return lista;
	}
	
	@Override
	public List<ReportAccessoPDFDto> getEsitoByTipoDifesaWithCodeAndDissensoAnnuale(long idCampagna) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao
				.getEsitoByTipoDifesaWithDissensoAnnuale(idCampagna);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToEsitoDifesaWithCodePDF(obj));
		}
		
		return lista;
	}
	
	@Override
	public List<ReportAccessoPDFDto> getRiepilogoGiudiziAnnuale(long idCampagna) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRiepilogoGiudiziAnnuale(idCampagna);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRiepilogoGiudiziPDF(obj));
		}
		
		return lista;
	}
	
	@Override
	public List<ReportAccessoPDFDto> getTipoDifesaIncompletaAnnuale(long idCampagna, String tipoDifesa) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getTipoDifesaIncompletaAnnuale(
				idCampagna, tipoDifesa);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToTipoDifesaIncompletaPDF(obj));
		}

		return lista;
	}
	
	@Override
	public List<ReportAccessoPDFDto> getRisultatiByTempoAnnuale(long idCampagna) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRisultatiByTempoAnnuale(idCampagna);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToRisultatiByTempoPDF(obj));
		}

		return lista;
	}
	
	@Override
	public List<ReportAccessoPDFDto> getMNonConfByIdFaseAnnuale(Long idFase, long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getMNonConfByIdFaseAnnuale(idFase,
				idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToMNonConfPDF(obj));
		}
		
		return lista;
	}
	
	@Override
	public List<ReportAccessoPDFDto> getVarCompByIdMNonConfAnnuale(long idSSessione, Long idFase, Long idMNonConf) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao
				.getVarCompByIdMNonConfAnnuale(idMNonConf);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToVarCompPDF(obj));
		}

		return lista;
	}
	
	@Override
	public List<ReportAccessoPDFDto> getEsitoByTipoDifesaAnnuale(long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getEsitoByTipoDifesaAnnuale(idSSessione);

		for (Object[] obj : objList) {
			lista.add(ModelToDto.modelToEsitoDifesaPDF(obj));
		}

		return lista;
	}
	
	@Override
	public List<String> getListaAuditorsAnnuale(long idSSessione) {
		return reportPDFDao.getListaAuditorsAnnuale(idSSessione);
	}
	
	@Override
	public List<Object[]> getGiudiziDefinitiviEtichetteAnnuale() {
		return reportPDFDao.getGiudiziDefinitiviEtichetteAnnuale();
	}
	
	// --------------------------------------------------------------------------------------------
	// NUOVI REPORT ANGELO ALLEGATO
	@Override
	public String getAllegatoReportAccessoPDFAnnuale(long idSSessione) {
		
		String obj = reportPDFDao.getAllegatoReportAccessoPDFAnnuale(idSSessione);

		if (obj != null) {
			return obj;
		}

		return null;
	}
	
	@Override
	public List<AtpoPratiche> getReportAllegatiPDFAnnuale(String sede, long idSessione) {
		return  reportPDFDao.getReportAllegatiPDFAnnuale(sede,idSessione);
	}
	
	@Override
	public String calcolaIndicatoriCampagna(long idCampanga) {
		return reportPDFDao.calcolaIndicatoriCampagna(idCampanga);
	}
}
