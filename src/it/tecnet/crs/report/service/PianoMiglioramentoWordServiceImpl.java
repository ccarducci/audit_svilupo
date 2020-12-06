package it.tecnet.crs.report.service;

import it.tecnet.crs.report.jpa.dao.PianoMiglioramentoWordDao;
import it.tecnet.crs.report.jpa.dao.ReportPDFDao;
import it.tecnet.crs.report.web.dto.ReportAccessoPDFDto;
import it.tecnet.crs.util.ModelToDto;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class PianoMiglioramentoWordServiceImpl implements
		PianoMiglioramentoWordService {

	protected static Logger log = Logger
			.getLogger(PianoMiglioramentoWordServiceImpl.class);

	private ReportPDFDao reportPDFDao;
	private PianoMiglioramentoWordDao pianoMiglioramentoWordDao;
	
	public PianoMiglioramentoWordDao getPianoMiglioramentoWordDao() {
		return pianoMiglioramentoWordDao;
	}

	public void setPianoMiglioramentoWordDao(
			PianoMiglioramentoWordDao pianoMiglioramentoWordDao) {
		this.pianoMiglioramentoWordDao = pianoMiglioramentoWordDao;
	}

	public ReportPDFDao getReportPDFDao() {
		return reportPDFDao;
	}

	public void setReportPDFDao(ReportPDFDao reportPDFDao) {
		this.reportPDFDao = reportPDFDao;
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

	public List<ReportAccessoPDFDto> getRiepilogoGiudizi(long idSSessione) {
		List<ReportAccessoPDFDto> lista = new ArrayList<ReportAccessoPDFDto>();
		List<Object[]> objList = reportPDFDao.getRiepilogoGiudizi(idSSessione);
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

}
