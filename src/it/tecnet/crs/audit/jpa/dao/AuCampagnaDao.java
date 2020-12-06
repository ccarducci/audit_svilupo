package it.tecnet.crs.audit.jpa.dao;
import it.tecnet.crs.ATPO.auditors.web.dto.AtpoAuMContestoDto;
import it.tecnet.crs.audit.model.AuSediCritiche;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuCampione;
import it.tecnet.crs.jpa.model.AuRegoleCampagna;
import it.tecnet.crs.jpa.model.AuRisultatiCampagna;
import it.tecnet.crs.jpa.model.AuRisultatiCampione;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSede;
import it.tecnet.crs.jpa.model.AuSessioni;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface AuCampagnaDao {
	
	public long getIdDirigenteFromDelegato(long idDelegato);
	
	public long getIdAuditByIdCampagna(long idCampagna);
	public AtpoAuMContestoDto saveDatiContesto(AtpoAuMContestoDto atpoAuMContestoDto);
	Object[] getDatiContesto(long idSessione, long idCampagna);
	public List<Object[]> getListaVerbaliCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	public List<Object[]> getListaVerbaliCampagna(long idCampagna);
	public List<Object[]> getListaVerbaliCampagnaSede(long idCampagna,String sede);
	
	public Integer countListaVerbaliCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
	public List<AuRegoleCampagna> getListaRegoleCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
	public Integer countListaRegoleCampagna(long idCampagna,Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);

	public List getListaCampagna(Integer pageNumber,
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
	
	public void eliminaSessione(long idSessione);
	
	public AuRisultatiCampagna getRisultatoRegolaCampagna(long idRegolaCampagna, long idCampagna);
		
	public AuRisultatiCampione getRisultatoRegolaCampione(long idRegolaCampagna, long idCampione);
	
	public List<AuSessioni> getListaSessioniCampagna(long idCampagna);
	
	public AuCampione getCampioneBySessione(long idSessione);
	
	public List getListaReportSedi(long idCampagna, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
	public Integer countReportSedi(long idCampagna, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
//	public List<AuReportSediLabel> getListaReportSediLabel(long idCampagna);
	
	public List<AuSessioni> getListaSessioniCampagna(long idCampagna, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
	public Integer  countListaSessioniCampagna(long idCampagna,Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch);
	
	public List<Object[]> getListaSessioni(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch, long idUtente);
	
	public Integer  countListaSessioni(Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch, long idUtente);
	

	public List<Object[]> getRisultatiRegolaCampagnaCampione(long idRegola);

	public  List<Object[]> getListaVerbaliCampioneSess(long idCampione, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,
			String textSearch);

	public Integer countListaVerbaliCampioneSess(long idCampione, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);

	public List<Object[]> getListaVerbaliCampagnaSess(long idCampione,
			Integer pageNumber, Integer pageSize, Integer columnNameToOrder,
			String orderType, String textSearch);

	public Integer countListaVerbaliCampagnaSess(long idSessione, Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,
			String textSearch);

	
	public List<Long> getIdRegoleCampione(long idSessione);

	public List<AuSede> getSedi();
	
	public int countSessioniAssociateCampagna(long idCampagna);

	@Transactional
	public void aggiornaDataFineAccessiCampagna(AuCampagna campagna);
	
	public void deletePraticaCampione(long idCampione, long idPratica, long idSPratica);
	
	@Transactional
	public void aggiungiPraticaAlCampione(long idCampione , long idPratica);
	
	public AuSPratica getAuSPraticaById(long idPratica);
	
	public Integer countSediCritiche(Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
	
	public List<AuSediCritiche> getListaSediCritiche( Integer pageNumber,
			Integer pageSize, Integer columnNameToOrder, String orderType,String textSearch);
}
	