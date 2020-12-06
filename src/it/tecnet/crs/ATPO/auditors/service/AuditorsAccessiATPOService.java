package it.tecnet.crs.ATPO.auditors.service;

import java.util.List;


import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.report.web.dto.ReportAccessoPDFDto;
import it.tecnet.crs.web.dto.AuMVarCompDto;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuSRischio;
import it.tecnet.crs.jpa.model.AuSVarcomp;
import it.tecnet.crs.ATPO.auditors.web.dto.AtpoAuMContestoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.AuSMEsprRischioDTO;
import it.tecnet.crs.ATPO.auditors.web.dto.PraticheAtpoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.TipologiaPraticheCampioneDto;
import it.tecnet.crs.jpa.model.AuAudit;
import it.tecnet.crs.web.dto.AuSSessioneDto;
import it.tecnet.crs.web.dto.NonConformitaAccessiDto;

public interface AuditorsAccessiATPOService {

	//	TABELLA PRATICHE ATPO
	List<PraticheAtpoDto> getPraticheATPO(int idSessione, int filtroEsito, int filtroStato,int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch);
	
	Integer countListaPraticheATPO(int idSessione, int filtroEsito, int filtroStato, int getiDisplayStart, int getiDisplayLength,
			int getiSortCol_0, String getsSortDir_0, String getsSearch);
	
	AtpoAuMContestoDto getDatiContesto(long idSessione, long idCampagna);
	
	
	public <T> T cerca(Class<T> obj , Object pk);
	//***************************************************************************

	AuAudit getAuditByIdCampagna(long idCampagna);

	AuSSessioneDto getSSessione(long idSessione);

	Integer getNumeroPraticheEsaminate(long idSessione);

	List<TipologiaPraticheCampioneDto> getTipologiaPraticheCampione(long idSessione);

	List<AuMNonConf> getNonConformitaAccessiAtpo(Long idAudit);

	
	public NonConformitaAccessiDto getSNConfByIdMNonConf(long idMNonConf,
			long idSSessione);

	public List<AuMVarCompDto> getVarComp(long idMNonConf, long idSSessione,int getiDisplayStart, int getiDisplayLength,
			int getiSortCol_0, String getsSortDir_0, String getsSearch);

	int countVarComp(long idMNonConf, long idSSessione,int getiDisplayStart, int getiDisplayLength,
			int getiSortCol_0, String getsSortDir_0, String getsSearch);

	List<AuMRischio> getTabRischiPM(Long idAudit);

	List<AuSMEsprRischioDTO> searchEsprRischiTablePM(long idRischio,
			long idSSessione, int getiDisplayStart, int getiDisplayLength,
			int getiSortCol_0, String getsSortDir_0, String getsSearch);

	int countEsprRischiTablePM(long idRischio, long idSSessione, int getiDisplayStart, int getiDisplayLength,
			int getiSortCol_0, String getsSortDir_0, String getsSearch);

	public <T> T salva(T obj);

	List<ReportAccessoPDFDto> getRisultatiByTempo(long idSSessione,
			int getiDisplayStart, int getiDisplayLength, int getiSortCol_0,
			String getsSortDir_0, String getsSearch);

	int countListTempo(long idSSessione, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch);

	

	
	

	
}
