package it.tecnet.crs.mod.jpa.dao;

import it.tecnet.crs.componenti.jpa.model.CrsDocumentiMedia;
import it.tecnet.crs.componenti.web.bean.NormativaTablePaginator;
import it.tecnet.crs.jpa.model.CrsAssAcClasse;
import it.tecnet.crs.jpa.model.CrsAssAdClasse;
import it.tecnet.crs.jpa.model.CrsAssProcessoClasse;
import it.tecnet.crs.jpa.model.CrsAssSottoprocessoClasse;
import it.tecnet.crs.mod.jpa.model.CrsArea;
import it.tecnet.crs.mod.jpa.model.CrsAttivitaComponente;
import it.tecnet.crs.mod.jpa.model.CrsAttivitaDettaglio;
import it.tecnet.crs.mod.jpa.model.CrsProcesso;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;
import it.tecnet.crs.mod.web.bean.AreaTablePaginator;
import it.tecnet.crs.mod.web.bean.AttivitaComponente;
import it.tecnet.crs.mod.web.bean.AttivitaComponenteTablePaginator;
import it.tecnet.crs.mod.web.bean.AttivitaDettaglioTablePaginator;
import it.tecnet.crs.mod.web.bean.CompTecniciAttivitaDettaglioPaginator;
import it.tecnet.crs.mod.web.bean.CompTecniciProcessoPaginator;
import it.tecnet.crs.mod.web.bean.CompTecniciSottoProcessoPaginator;
import it.tecnet.crs.mod.web.bean.DocumentiAttivitaDettaglioPaginator;
import it.tecnet.crs.mod.web.bean.DocumentiProcessoPaginator;
import it.tecnet.crs.mod.web.bean.DocumentiSottoProcessoPaginator;
import it.tecnet.crs.mod.web.bean.DominiAttivitaDettaglioPaginator;
import it.tecnet.crs.mod.web.bean.DominiProcessoPaginator;
import it.tecnet.crs.mod.web.bean.DominiSottoProcessoPaginator;
import it.tecnet.crs.mod.web.bean.ProcessoTablePaginator;
import it.tecnet.crs.mod.web.bean.SottoProcessoTablePaginator;
import it.tecnet.crs.mod.web.dto.DocMediaAttCompDto;
import it.tecnet.crs.web.beans.AssAcClasse;
import it.tecnet.crs.web.beans.AssAdClasse;
import it.tecnet.crs.web.beans.AssProcessoClasse;
import it.tecnet.crs.web.beans.AssSottoProcessoClasse;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;



public interface ModellazioneDao {
	
	
	/**
	 * Metodo generico trasversale alla modellazione che controlla la presenza
	 * del campo ordinamento in fase di inserimento/modifica in base a ID e tabella in input.
	 * @param idTabella
	 * @param ordinamento
	 * @param tabella
	 * @return
	 */
	public boolean checkFieldOrdinamento(long idTabella, int ordinamento, String tabella);
	
	public List<Object[]> getIdPadriByIdAttivitaComponente(long idAttivitaComponente);
	
	
	/*
	 * 		AREA
	 * */
	@Transactional
	public void saveArea(CrsArea area);
	
	public void deleteArea(long idArea);
	
	public List<CrsArea> getListaAree (AreaTablePaginator filter);
	public Integer countAllAree(AreaTablePaginator filter);
	
	@Transactional
	public void disableArea(Long idArea);
	
	@Transactional
	public void enableArea(Long idArea);
	
	public CrsArea getAreaById(Long idArea);
	
	/*
	 * 		PROCESSO
	 * */
	@Transactional
	public void saveProcesso(CrsProcesso processo);
	
	public void deleteProcesso(long idProcesso);
	
	public List<Object[]> getListaProcessi (ProcessoTablePaginator filter, long idUtente);
	
	public Integer countAllProcessi(ProcessoTablePaginator filter, long idUtente);
	
	public List<CrsArea> getComboArea();
	
	@Transactional
	public void disableProcesso(Long idProcesso);
	
	@Transactional
	public void pubblicaProcesso(Long idProcesso);
	
	public CrsProcesso getProcessoById(Long idProcesso);
	
	public List<Object[]> getAssociabiliCircolariInpsTable(ProcessoTablePaginator filter);
	public List<Object[]> getAssociabiliNoteDecreti(ProcessoTablePaginator filter);
	public List<Object[]> getAssociabiliMessaggiInps(ProcessoTablePaginator filter);
	public List<Object[]> getAssociabiliLeggiDecreti(ProcessoTablePaginator filter);
	
	public Integer countAllAssociabiliCircolariInps(ProcessoTablePaginator filter);
	public Integer countAllAssociabiliNoteDecreti(ProcessoTablePaginator filter);
	public Integer countAllAssociabiliMessaggiInps(ProcessoTablePaginator filter);
	public Integer countAllAssociabiliLeggiDecreti(ProcessoTablePaginator filter);
	
	@Transactional
	public void associaProcessoNormativa(CrsAssProcessoClasse associazione);
	
	public long getAssProcessoClassePK(AssProcessoClasse associazione);
	
	public void rimuoviAssociazioneProcessoNormativa(long idAssProcessoClasse);
	
	public String getUserNameDirigente(long idUtente);
	
	
	/*
	 * 		SOTTOPROCESSO
	 * */
	@Transactional
	public void saveSottoProcesso(CrsSottoprocesso sottoProcesso);	
	public void deleteSottoProcesso(long idSottoProcesso);
	public void associaSottoProcessoAudit(long idAudit, long idSottoProcesso);

	public List<CrsSottoprocesso> getListaSottoProcessiByIdAudit (long idAudit, String ricerca, Integer pageNumber, Integer pageSize, String columnNameToOrder, String orderType, String textSearch, char stato);	
	
	public List<Object[]> getListaSottoProcessi (SottoProcessoTablePaginator filter, long idUtente);
	
	public Integer countAllSottoProcessi(SottoProcessoTablePaginator filter, long idUtente);
	
	public List<CrsProcesso> getComboProcessoByIdArea(long idArea);
	
	@Transactional
	public void disableSottoProcesso(long idSottoProcesso);
	
	@Transactional
	public void enableSottoProcesso(long idSottoProcesso);
	
	public CrsSottoprocesso getSottoProcessoById(Long idSottoProcesso);
	
	public List<Object[]> getListaSottoProcessoNormative(SottoProcessoTablePaginator filter);
	
	public Integer countAllSottoProcessoNormative(SottoProcessoTablePaginator filter);
	
	@Transactional
	public void associaSottoProcessoNormativa(CrsAssSottoprocessoClasse associazione);
	
	public long getAssSottoProcessoClassePK(AssSottoProcessoClasse associazione);
	
	public void rimuoviAssociazioneSottoProcessoNormativa(long idAssSottoProcessoClasse);
	
	public long getIdAreaByIdSottoProcesso(long idSottoProcesso);
	
	/*
	 * 		ATTIVITA' COMPONENTE
	 * */
	@Transactional
	public void saveAttivitaComponente(CrsAttivitaComponente attivitaComponente);
	
	public void deleteAttivitaComponente(long idAttivitaComponente);
	
	public List<Object[]> getListaAttivitaComponente (AttivitaComponenteTablePaginator filter,long idUtente);
	
	public Integer countAllAttivitaComponente(AttivitaComponenteTablePaginator filter,long idUtente);
	
	@Transactional
	public void disableAttivitaComponente(Long idAttivitaComponente);
	
	@Transactional
	public void enableAttivitaComponente(Long idAttivitaComponente);
	
	public List<CrsSottoprocesso> getComboSottoProcessoByIdProcesso(long idProcesso);
	
	public CrsAttivitaComponente getAttivitaComponenteById(Long idAttivitaComponente);
	
	
	public List<Object[]> getListaAttivitaComponenteNormative(AttivitaComponenteTablePaginator filter);
	
	public Integer countAllAttivitaComponenteNormative(AttivitaComponenteTablePaginator filter);
	
	@Transactional
	public void associaAttivitaComponenteNormativa(CrsAssAcClasse associazione);
	
	public long getAssAttivitaComponenteClassePK(AssAcClasse associazione);
	
	public void rimuoviAssociazioneAttivitaComponenteNormativa(long idAssAcClasse);
	
	public long getIdAreaByIdAttivitaComponente(long idAttivitaComponente);
	
	public List<CrsSottoprocesso> getComboSottoProcessoByIdArea(long idArea);
	
	/*
	 * 		ATTIVITA' DETTAGLIO
	 * */
	@Transactional
	public void saveAttivitaDettaglio(CrsAttivitaDettaglio attivitaDettaglio);
	
	public void deleteAttivitaDettaglio(long idAttivitaDettaglio);
	
	public List<Object[]> getListaAttivitaDettaglio (AttivitaDettaglioTablePaginator filter, long idUtente);
	
	public Integer countAllAttivitaDettaglio(AttivitaDettaglioTablePaginator filter, long idUtente);
	
	@Transactional
	public void disableAttivitaDettaglio(Long idAttivitaDettaglio);
	
	@Transactional
	public void enableAttivitaDettaglio(Long idAttivitaDettaglio);
	
	public List<CrsAttivitaComponente> getComboAttivitaComponenteByIdSottoProcesso(long idSottoProcesso);
	
	public CrsAttivitaDettaglio getAttivitaDettaglioById(Long idAttivitaDettaglio);
	
	public List<Object[]> getListaAttivitaDettaglioNormative(AttivitaDettaglioTablePaginator filter);
	
	public Integer countAllAttivitaDettaglioNormative(AttivitaDettaglioTablePaginator filter);
	
	@Transactional
	public void associaAttivitaDettaglioNormativa(CrsAssAdClasse associazione);
	
	public long getAssAttivitaDettaglioClassePK(AssAdClasse associazione);
	
	public void rimuoviAssociazioneAttivitaDettaglioNormativa(long idAssAdClasse);
	
	public long getIdAreaByIdAttivitaDettaglio(long idAttivitaDettaglio);
	
	public List<CrsAttivitaComponente> getComboAttivitaComponenteByIdArea(long idArea);
	

	
	public List<Object[]> getProcessiByIdArea(long idArea);
	public List<Object[]> getSottoProcessiByIdArea(long idArea);
	public List<Object[]> getAttivitaComponentiByIdArea(long idArea);
	public List<Object[]> getAttivitaDettagliByIdArea(long idArea);

	//DOCUMENTI MEDI ATTIVITA COMPONENTE
	public List<Object[]> getDocMediaAttComp(long idAttivitaComponente,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search);

	public Integer countDocMediaAttComp(long idAttivitaComponente, String search);

	public List<Object[]> getDocAttCompDaAssociare(long idAttivitaComponente,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search);

	public Integer countDocAttCompDaAssociare(long idAttivitaComponente,
			String getsSearch);


	public List<Object[]> getListDominiAttComp(long idAttivitaComponente,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search);

	public Integer countListDominiAttComp(long idAttivitaComponente,
			String search);
	
	public List<Object[]> getListDominiAttCompDaAssociare(long idAttivitaComponente,
			int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search);

	public Integer countListDominiAttCompDaAssociare(long idAttivitaComponente,
			String search);
	
	public List<Object[]> getListCompTecAttComp(long idAttivitaComponente,int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search);

	public Integer countListCompTecAttComp(long idAttivitaComponente,
			String search);


	public List<Object[]> getListCompTecAttCompDaAssociare(long idAttivitaComponente,int pageNumber, int pageSize, int columnNameToOrder,
			String orderType, String search);
	
	public Integer countListCompTecAttCompDaAssociare(
			long idAttivitaComponente, String getsSearch);

	
	
	
	//MODIFICA PROCESSO DOC MEDIA
	public List<Object[]> getDocMediaProcesso(DocumentiProcessoPaginator filter);

	public Integer countDocMediaProcesso(DocumentiProcessoPaginator filter);

	public List<Object[]> getDocProcessoDaAssociare(DocumentiProcessoPaginator filter);

	public Integer countDocProcessoDaAssociare(DocumentiProcessoPaginator filter);
	
	//MODIFICA PROCESSO DOMINI
	List<Object[]> getDominiProcesso(DominiProcessoPaginator filter);

	public Integer countDominiProcesso(DominiProcessoPaginator filter);

	public List<Object[]> getDominiProcessoDaAssociare(DominiProcessoPaginator filter);

	public Integer countDominiProcessoDaAssociare(DominiProcessoPaginator filter);
	
	
	//MODIFICA PROCESSO COMPONENTI TECNICI
	List<Object[]> getCompTecProcesso(CompTecniciProcessoPaginator filter);

	public Integer countCompTecProcesso(CompTecniciProcessoPaginator filter);

	public List<Object[]> getCompTecProcessoDaAssociare(CompTecniciProcessoPaginator filter);

	public Integer countCompTecProcessoDaAssociare(CompTecniciProcessoPaginator filter);
	
	
	
	//MODIFICA SOTTO PROCESSO DOC MEDIA
	public List<Object[]> getDocMediaSottoProcesso(DocumentiSottoProcessoPaginator filter);

	public Integer countDocMediaSottoProcesso(DocumentiSottoProcessoPaginator filter);

	public List<Object[]> getDocSottoProcessoDaAssociare(DocumentiSottoProcessoPaginator filter);

	public Integer countDocSottoProcessoDaAssociare(DocumentiSottoProcessoPaginator filter);
	
	//MODIFICA SOTTO PROCESSO DOMINI
	List<Object[]> getDominiSottoProcesso(DominiSottoProcessoPaginator filter);

	public Integer countDominiSottoProcesso(DominiSottoProcessoPaginator filter);

	public List<Object[]> getDominiSottoProcessoDaAssociare(DominiSottoProcessoPaginator filter);

	public Integer countDominiSottoProcessoDaAssociare(DominiSottoProcessoPaginator filter);
	
	
	//MODIFICA  SOTTO PROCESSO COMPONENTI TECNICI
	List<Object[]> getCompTecSottoProcesso(CompTecniciSottoProcessoPaginator filter);

	public Integer countCompTecSottoProcesso(CompTecniciSottoProcessoPaginator filter);

	public List<Object[]> getCompTecSottoProcessoDaAssociare(CompTecniciSottoProcessoPaginator filter);

	public Integer countCompTecSottoProcessoDaAssociare(CompTecniciSottoProcessoPaginator filter);
	
	
	
	//MODIFICA ATTIVITA DETTAGLIO DOC MEDIA
	public List<Object[]> getDocMediaAttivitaDettaglio(DocumentiAttivitaDettaglioPaginator filter);

	public Integer countDocMediaAttivitaDettaglio(DocumentiAttivitaDettaglioPaginator filter);

	public List<Object[]> getDocAttivitaDettaglioDaAssociare(DocumentiAttivitaDettaglioPaginator filter);

	public Integer countDocAttivitaDettaglioDaAssociare(DocumentiAttivitaDettaglioPaginator filter);
	
	//MODIFICA ATTIVITA DETTAGLIO DOMINI
	List<Object[]> getDominiAttivitaDettaglio(DominiAttivitaDettaglioPaginator filter);

	public Integer countDominiAttivitaDettaglio(DominiAttivitaDettaglioPaginator filter);

	public List<Object[]> getDominiAttivitaDettaglioDaAssociare(DominiAttivitaDettaglioPaginator filter);

	public Integer countDominiAttivitaDettaglioDaAssociare(DominiAttivitaDettaglioPaginator filter);
	
	//MODIFICA  ATTIVITA DETTAGLIO COMPONENTI TECNICI
	List<Object[]> getCompTecAttivitaDettaglio(CompTecniciAttivitaDettaglioPaginator filter);

	public Integer countCompTecAttivitaDettaglio(CompTecniciAttivitaDettaglioPaginator filter);

	public List<Object[]> getCompTecAttivitaDettaglioDaAssociare(CompTecniciAttivitaDettaglioPaginator filter);

	public Integer countCompTecAttivitaDettaglioDaAssociare(CompTecniciAttivitaDettaglioPaginator filter);
	
	
	
	/*
	 *  O P    C O M U N I______________________________-
	 */
	
	public <T> T salva(T entity);

	public void remove(Object o);
	
	public <T> T cerca(Class<T> obj, Object pk);

	
	

}
