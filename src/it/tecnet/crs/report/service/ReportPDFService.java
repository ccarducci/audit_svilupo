package it.tecnet.crs.report.service;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoPratiche;
import it.tecnet.crs.report.web.dto.ReportAccessoPDFDto;

import java.util.List;

public interface ReportPDFService {
	
	public List<Object[]> getReportErmPDF(long idSSessione);
	public List<AtpoPratiche> getReportAllegatiPDF(String sede, long idSessione);
	
	public ReportAccessoPDFDto getReportAccessoPDF(long idSSessione);
	public String[] getElencoRischi();
	public ReportAccessoPDFDto getAllegatoReportAccessoPDF(long idSSessione);
	
	
	public List<ReportAccessoPDFDto> getRiepilogoFasi(long idSSessione);
	
	public List<ReportAccessoPDFDto> getMNonConfByIdFase(long idFase, long idSSessione);
	
	public List<ReportAccessoPDFDto> getVarCompByIdMNonConf(Long sessione, Long idFase, Long idMNonConf);
	
	public List<ReportAccessoPDFDto> getVarCompByIdMNonConf(long idMNonConf);
	
	public List<ReportAccessoPDFDto> getRiepilogoRischi(long idSSessione);
	
	public List<ReportAccessoPDFDto> getRisEsprByIdMNonConf(long idMRischio, long idSSessione);
	
	public List<ReportAccessoPDFDto> getRiepilogoFascicolo(long idSSessione);
	
	public List<ReportAccessoPDFDto> getRiepilogoDocManc(long idSSessione);
	
	public List<ReportAccessoPDFDto> getEsitoByTipoDifesa(long idSSessione);
	
	public List<ReportAccessoPDFDto> getEsitoByTipoDifesaWithCode(long idSSessione);
	
	public List<ReportAccessoPDFDto> getEsitoByTipoDifesaWithCodeAndDissenso(long idSSessione);
	
	public List<ReportAccessoPDFDto> getEsitoByTipoDifesaWithCodeAndDissensoByCampagna(long idCampagna);
	
	public List<ReportAccessoPDFDto> getRiepilogoGiudizi(long idSSessione);
	
	public List<ReportAccessoPDFDto> getRiepilogoGiudiziByCampagna(long idCampagna);
	
	public List<ReportAccessoPDFDto> getRisultatiByTempo(long idSSessione);
	
	public List<ReportAccessoPDFDto> getRiepilogoIstanze(long idSSessione);

	public List<String> getListaAuditors(long idSSessione);
	
	public List<ReportAccessoPDFDto> getGiudiziDefinitiviSisco(long idSSessione, String tipoDifesa);
	
	public List<ReportAccessoPDFDto> getGiudiziDefinitiviSiscoByCampagna(long idCampagna, String tipoDifesa);
	
	public List<ReportAccessoPDFDto> getGiudiziDefinitiviSiscoByCampagnaAndSede(
			long idCampagna, String tipoDifesa, String codSede);
	
	public int getGiudiziDefinitiviSiscoByCampagnaTotale(long idCampagna, String tipoDifesa, String codSede);
	
	public List<Object[]> getGiudiziDefinitiviEtichette();
	
	public List<ReportAccessoPDFDto> getTipoDifesaIncompleta(long idSSessione, String tipoDifesa);

	public List<ReportAccessoPDFDto> getGiudiziDefinitiviSiscoByCampagnaAndSedeWithOutFavorevoli(
			long idCampagna, String tipoDifesa, String codSede);
	
	
	// -------------------------------------------------------------------------------------------
	// NUOVI REPORT ANGELO ACCESSO
	public ReportAccessoPDFDto getReportAccessoAnnualePDF(long idSSessione);
	public List<ReportAccessoPDFDto> getRiepilogoIstanzeAnnuale(long idSSessione);
	public List<ReportAccessoPDFDto> getRiepilogoFasiAnnuale(long idSSessione);
	public List<ReportAccessoPDFDto> getRiepilogoRischiAnnuale(long idSSessione);
	public List<ReportAccessoPDFDto> getRisEsprByIdMNonConfAnnuale(Long idMRischio, long idSSessione);
	public List<ReportAccessoPDFDto> getRiepilogoFascicoloAnnuale(long idSSessione);
	public List<ReportAccessoPDFDto> getRiepilogoDocMancAnnuale(long idSSessione);
	public List<ReportAccessoPDFDto> getEsitoByTipoDifesaWithCodeAndDissensoAnnuale(long idSSessione);
	public List<ReportAccessoPDFDto> getRiepilogoGiudiziAnnuale(long idSSessione);
	public List<ReportAccessoPDFDto> getTipoDifesaIncompletaAnnuale(long idSSessione, String tipoDifesa);
	public List<ReportAccessoPDFDto> getRisultatiByTempoAnnuale(long idSSessione);
	public List<ReportAccessoPDFDto> getMNonConfByIdFaseAnnuale(Long idFase, long idSSessione);
	public List<ReportAccessoPDFDto> getVarCompByIdMNonConfAnnuale(long idSSessione, Long idFase, Long idMNonConf);
	public List<ReportAccessoPDFDto> getEsitoByTipoDifesaAnnuale(long idSSessione);
	public List<String> getListaAuditorsAnnuale(long idSSessione);
	public List<Object[]> getGiudiziDefinitiviEtichetteAnnuale();
	
	// --------------------------------------------------------------------------------------------
	// NUOVI REPORT ANGELO ALLEGATO
	public String getAllegatoReportAccessoPDFAnnuale(long idSSessione);
	public List<AtpoPratiche> getReportAllegatiPDFAnnuale(String sede, long idSessione);
	
	// ------------------------------------------
	// Chiamata procedura per calcolo indicatori Campanga
	public String calcolaIndicatoriCampagna(long idCampanga);
	
	
}
