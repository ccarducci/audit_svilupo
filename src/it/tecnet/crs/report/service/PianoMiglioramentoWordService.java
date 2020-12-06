package it.tecnet.crs.report.service;

import it.tecnet.crs.report.web.dto.ReportAccessoPDFDto;

import java.util.List;

public interface PianoMiglioramentoWordService {

	public ReportAccessoPDFDto getReportAccessoPDF(long idSSessione);

	public List<ReportAccessoPDFDto> getRiepilogoFasi(long idSSessione);

	public List<ReportAccessoPDFDto> getMNonConfByIdFase(long idFase,
			long idSSessione);

	public List<ReportAccessoPDFDto> getVarCompByIdMNonConf(Long sessione,
			Long idFase, Long idMNonConf);

	public List<ReportAccessoPDFDto> getVarCompByIdMNonConf(long idMNonConf);

	public List<ReportAccessoPDFDto> getRiepilogoRischi(long idSSessione);

	public List<ReportAccessoPDFDto> getRisEsprByIdMNonConf(long idMRischio,
			long idSSessione);

	public List<ReportAccessoPDFDto> getRiepilogoFascicolo(long idSSessione);

	public List<ReportAccessoPDFDto> getRiepilogoDocManc(long idSSessione);

	public List<ReportAccessoPDFDto> getEsitoByTipoDifesa(long idSSessione);

	public List<ReportAccessoPDFDto> getEsitoByTipoDifesaWithCode(
			long idSSessione);

	public List<ReportAccessoPDFDto> getEsitoByTipoDifesaWithCodeAndDissenso(
			long idSSessione);

	public List<ReportAccessoPDFDto> getRiepilogoGiudizi(long idSSessione);

	public List<ReportAccessoPDFDto> getRisultatiByTempo(long idSSessione);

	public List<ReportAccessoPDFDto> getRiepilogoIstanze(long idSSessione);

	public List<String> getListaAuditors(long idSSessione);

	public List<ReportAccessoPDFDto> getGiudiziDefinitiviSisco(
			long idSSessione, String tipoDifesa);

	public List<ReportAccessoPDFDto> getTipoDifesaIncompleta(long idSSessione,
			String tipoDifesa);
}
