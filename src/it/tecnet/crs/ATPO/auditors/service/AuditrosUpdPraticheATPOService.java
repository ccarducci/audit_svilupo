package it.tecnet.crs.ATPO.auditors.service;

import java.util.List;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.web.beans.ModelAuditorsModificaPraticaATPO;
import it.tecnet.crs.ATPO.auditors.web.dto.AuTplTipologicheAtpoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.NonConformitaPraticheAtpoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.PraticheAtpoDto;
import it.tecnet.crs.ATPO.auditors.web.dto.RischipraticheAtpoDto;


public interface AuditrosUpdPraticheATPOService {

	PraticheAtpoDto getDatiPratica(long idPraticaAtpoDto, String fascicolo);

	

	AtpoFaseDati getFaseDati(String fascicolo, String codSede);
	
	
	/*
	 * TABELLA RISCHI IN MODIFICA PRATICA
	 */

	List<RischipraticheAtpoDto> getTabRischiPratAtpo(long idAudit,Integer pageNumber, Integer pageSize, int columnNameToOrder,String orderType, String search, long idPratica) ;
	int countTabRischiPratAtpo(long idAudit, String search, long idPratica) ;
	
	/*
	 *  TABELLA NON CONFORMITA IN MODIFICA PRATICA
	 */
	
	List<NonConformitaPraticheAtpoDto> getNonConfPratAtpo(long idAudit,long idPratica, String filtro, Integer pageNumber, Integer pageSize, int columnNameToOrder, String orderType, String search) ;
	int countNonConfPratAtpo(long idAudit, long idPratica, String filtro, String getsSearch);
	/*
	 * OP COMUNI
	 */
	public <T> T save(T entity);
	public <T> T cerca(Class<T> obj , Object pk);



	List<AuTplTipologicheAtpoDto> getTipologicaAtpo(String string);

	AuTplTipologicheAtpoDto getDescrTipologicaByCodifica(String trim);



	public Long getAuSPraticaByIdPratica(Long idPratica);



	List<String> getDescrizioneFaseAssociate(long idAudit);



	




}
