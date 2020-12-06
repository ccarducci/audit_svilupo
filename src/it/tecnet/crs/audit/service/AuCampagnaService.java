package it.tecnet.crs.audit.service;

import it.tecnet.crs.ATPO.auditors.web.dto.AtpoAuMContestoDto;
import it.tecnet.crs.audit.model.AuSediCritiche;
import it.tecnet.crs.audit.web.dto.CampagnaDto;
import it.tecnet.crs.audit.web.dto.PraticheCampagnaDto;
import it.tecnet.crs.audit.web.dto.ReportSediDto;
import it.tecnet.crs.audit.web.dto.RisultatoRegolaCampagnaCampioneDto;
import it.tecnet.crs.audit.web.dto.SessioniDto;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuCampione;
import it.tecnet.crs.jpa.model.AuRegoleCampagna;
import it.tecnet.crs.jpa.model.AuRisultatiCampagna;
import it.tecnet.crs.jpa.model.AuRisultatiCampione;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSede;
import it.tecnet.crs.jpa.model.AuSessioni;

import java.util.List;

public interface AuCampagnaService {
	
	AtpoAuMContestoDto getDatiContesto(long idSessione, long idCampagna);
	
	
	
	public long getIdDirigenteFromDelegato(long idDelegato);
	
	public long getIdAuditByIdCampagna(long idCampagna);
	
	public List<PraticheCampagnaDto> getListaVerbaliCampagna(long idCampagna);
	public List<PraticheCampagnaDto> getListaVerbaliCampagnaSede(long idCampagna,String sede);
	
	public List<PraticheCampagnaDto> getListaVerbaliCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
	public Integer countListaVerbaliCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
	public List<AuRegoleCampagna> getListaRegoleCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
	public Integer countListaRegoleCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
	public List<CampagnaDto> getListaCampagna(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch, long idUtente);
	
	public Integer countListaCampagna(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch, long idUtente);
	
	public AuCampagna salvaCampagna(AuCampagna a);
	
	public void eliminaCampagna(long idCampagna);
	
	public AuCampagna getCampagnaDaModificare(long idCampagna);
	
	public void associaVerbaliCampagna(AuCampagna camp);
	
	
	
	public List<AuRegoleCampagna> getListaRegoleCampagna(long idCampagna);
	
	
	
	public <T> void salva(T obj) ;
	
	public <T> T cerca(Class<T> obj , Object pk);
	
	public AuRisultatiCampagna getRisultatoRegolaCampagna(long idRegolaCampagna, long idCampagna);
	
	
	
	public List<PraticheCampagnaDto> getListaVerbaliCampioneSess(long idCampione, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
	public AuRisultatiCampione getRisultatoRegolaCampione(long idRegolaCampagna, long idCampione);
	
	public List<AuSessioni> getListaSessioniCampagna(long idCampagna);
	
	public AuCampione getCampioneBySessione(long idSessione);
	
	public CampagnaDto getCampagnaDto(long idCampagna);
	
	public List<ReportSediDto> getListaReportSedi(long idCampagna, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
	public Integer countReportSedi(long idCampagna, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
//	public List<AuReportSediLabel> getListaReportSediLabel(long idCampagna);
	
	public List<AuSessioni> getListaSessioniCampagna(long idCampagna, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
	public Integer  countListaSessioniCampagna(long idCampagna,Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch);
	
	public List<SessioniDto> getListaSessioni(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch, long idUtente);
	
	public Integer  countListaSessioni(Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch, long idUtente);
			
	
	public List<RisultatoRegolaCampagnaCampioneDto> getRisultatiRegolaCampagnaCampione(long idRegola);
	
	public void eliminaSessione(long idSessione);
	
	public Integer countListaVerbaliCampioneSess(long idCampione, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	 
	public List<PraticheCampagnaDto> getListaVerbaliCampagnaSess(long idCampione,
			Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch);

	public int countListaVerbaliCampagnaSess(long idSessione, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);

	public void deletePraticaCampione(long idCampione, long idPratica, long idSPratica);

	public List<Long> getIdRegoleCampione(long idSessione);

	public List<AuSede> getSedi();
	
	
	public int countSessioniAssociateCampagna(long idCampagna);

	public void aggiornaDataFineAccessiCampagna(AuCampagna campagna);
	
	public void aggiungiPraticaAlCampione(long idCampione, long idPratica);
	
	
	public AuSPratica getAuSPraticaById(long idPratica);
	
	public List<AuSediCritiche> getListaSediCritiche(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
	public Integer countSediCritiche(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
}
