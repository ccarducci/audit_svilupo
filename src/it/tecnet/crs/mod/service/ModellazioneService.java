package it.tecnet.crs.mod.service;

import it.tecnet.crs.componenti.jpa.model.CrsComponenteTecnico;
import it.tecnet.crs.componenti.jpa.model.CrsDocumentiMedia;
import it.tecnet.crs.componenti.jpa.model.CrsDomini;
import it.tecnet.crs.componenti.web.bean.CircolariInps;
import it.tecnet.crs.componenti.web.bean.DatiTipoNormativa;
import it.tecnet.crs.componenti.web.bean.LeggiDecreti;
import it.tecnet.crs.componenti.web.bean.MessaggiInps;
import it.tecnet.crs.componenti.web.bean.NormativaTablePaginator;
import it.tecnet.crs.componenti.web.bean.NoteDecreti;
import it.tecnet.crs.componenti.web.dto.CrsCompTecnicoDto;
import it.tecnet.crs.mod.jpa.model.CrsAttivitaDettaglio;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;
import it.tecnet.crs.mod.web.bean.Area;
import it.tecnet.crs.mod.web.bean.AreaTablePaginator;
import it.tecnet.crs.mod.web.bean.AttivitaComponente;
import it.tecnet.crs.mod.web.bean.AttivitaComponenteTablePaginator;
import it.tecnet.crs.mod.web.bean.AttivitaDettaglio;
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
import it.tecnet.crs.mod.web.bean.Processo;
import it.tecnet.crs.mod.web.bean.ProcessoTablePaginator;
import it.tecnet.crs.mod.web.bean.SottoProcesso;
import it.tecnet.crs.mod.web.bean.SottoProcessoTablePaginator;
import it.tecnet.crs.mod.web.dto.CompTecAttCompDto;
import it.tecnet.crs.mod.web.dto.DocMediaAttCompDto;
import it.tecnet.crs.mod.web.dto.DomAttCompDto;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.web.beans.AssAcClasse;
import it.tecnet.crs.web.beans.AssAdClasse;
import it.tecnet.crs.web.beans.AssProcessoClasse;
import it.tecnet.crs.web.beans.AssSottoProcessoClasse;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;



public interface ModellazioneService {

	/**
	 * Metodo generico trasversale alla modellazione che controlla la presenza
	 * del campo ordinamento in fase di inserimento/modifica in base a ID e tabella in input.
	 * @param idTabella
	 * @param ordinamento
	 * @param tabella
	 * @return
	 */
	public boolean checkFieldOrdinamento(long idTabella, int ordinamento, String tabella);

	public List<Long> getIdPadriByIdAttivitaComponente(long idAttivitaComponente);

	/*
	 * 		AREA
	 * */
	@Transactional
	public void saveArea(Area area);

	public void deleteArea(long idArea);

	public List<Area> getListaAree (AreaTablePaginator filter);

	public Integer countAllAree(AreaTablePaginator filter);

	@Transactional
	public void disableArea(Long idArea);

	@Transactional
	public void enableArea(Long idArea);

	public Area getAreaById(Long idArea);

	public boolean chiudiValiditaArea(Area area); 

	/*
	 * 		PROCESSO
	 * */
	public void saveProcesso(Processo processo);

	public void deleteProcesso(long idProcesso);

	public List<ProcessoDto> getListaProcessi (ProcessoTablePaginator filter, long idUtente);

	public Integer countAllProcessi(ProcessoTablePaginator filter, long idUtente);

	public void disableProcesso(Long idProcesso);

	public void pubblicaProcesso(Long idProcesso);

	public List<Area> getComboArea();

	public Processo getProcessoById(Long idProcesso);

	public List<CircolariInps> getAssociabiliCircolariInpsTable(ProcessoTablePaginator filter);
	public List<NoteDecreti> getAssociabiliNoteDecreti(ProcessoTablePaginator filter);
	public List<MessaggiInps> getAssociabiliMessaggiInps(ProcessoTablePaginator filter);
	public List<LeggiDecreti> getAssociabiliLeggiDecreti(ProcessoTablePaginator filter);

	public Integer countAllAssociabiliCircolariInps(ProcessoTablePaginator filter);
	public Integer countAllAssociabiliNoteDecreti(ProcessoTablePaginator filter);
	public Integer countAllAssociabiliMessaggiInps(ProcessoTablePaginator filter);
	public Integer countAllAssociabiliLeggiDecreti(ProcessoTablePaginator filter);

	public void associaProcessoNormativa(AssProcessoClasse associazione);

	public long getAssProcessoClassePK(AssProcessoClasse associazione);

	public void rimuoviAssociazioneProcessoNormativa(long idAssProcessoClasse);
	
	public String getUserNameDirigente(long idUtente);


	/*
	 * 		SOTTOPROCESSO
	 * */
	public void saveSottoProcesso(SottoProcesso sottoProcesso);

	public void deleteSottoProcesso(long idSottoProcesso);

	public List<SottoProcesso> getListaSottoProcessi (SottoProcessoTablePaginator filter, long idUtente);

	public Integer countAllSottoProcessi(SottoProcessoTablePaginator filter, long idUtente);

	public void disableSottoprocesso(long idSottoProcesso);

	public void enableSottoprocesso(long idSottoProcesso);

	public List<Processo> getComboProcessoByIdArea(long idArea);

	public SottoProcesso getSottoProcessoById(Long idSottoProcesso);

	public List<DatiTipoNormativa> getListaSottoProcessoNormative(SottoProcessoTablePaginator filter);

	public Integer countAllSottoProcessoNormative(SottoProcessoTablePaginator filter);

	public void associaSottoProcessoNormativa(AssSottoProcessoClasse associazione);

	public long getAssSottoProcessoClassePK(AssSottoProcessoClasse associazione);

	public void rimuoviAssociazioneSottoProcessoNormativa(long idAssSottoProcessoClasse);

	public long getIdAreaByIdSottoProcesso(long idSottoProcesso);

	/*
	 * 		ATTIVITA' COMPONENTE
	 * */
	public void saveAttivitaComponente(AttivitaComponente attivitaComponente);

	public void deleteAttivitaComponente(long idAttivitaComponente);

	public List<AttivitaComponente> getListaAttivitaComponente (AttivitaComponenteTablePaginator filter, long idUtente);

	public Integer countAllAttivitaComponente(AttivitaComponenteTablePaginator filter, long idUtente);

	public void disableAttivitaComponente(Long idAttivitaComponente);

	public void enableAttivitaComponente(Long idAttivitaComponente);

	public List<SottoProcesso> getComboSottoProcessoByIdProcesso(long idProcesso);

	public AttivitaComponente getAttivitaComponenteById(Long idAttivitaComponente);

	public List<DatiTipoNormativa> getListaAttivitaComponenteNormative(AttivitaComponenteTablePaginator filter);

	public Integer countAllAttivitaComponenteNormative(AttivitaComponenteTablePaginator filter);

	public void associaAttivitaComponenteNormativa(AssAcClasse associazione);

	public long getAssAttivitaComponenteClassePK(AssAcClasse associazione);

	public void rimuoviAssociazioneAttivitaComponenteNormativa(long idAssAcClasse);

	public long getIdAreaByIdAttivitaComponente(long idAttivitaComponente);

	public List<SottoProcesso> getComboSottoProcessoByIdArea(long idArea);



	/*
	 * 		ATTIVITA' DETTAGLIO
	 */

	public void saveAttivitaDettaglio(AttivitaDettaglio attivitaDettaglio);

	public void deleteAttivitaDettaglio(long idAttivitaDettaglio);

	public List<AttivitaDettaglio> getListaAttivitaDettaglio (AttivitaDettaglioTablePaginator filter,long idUtente);

	public Integer countAllAttivitaDettaglio(AttivitaDettaglioTablePaginator filter,long idUtente);

	public void disableAttivitaDettaglio(Long idAttivitaDettaglio);

	public void enableAttivitaDettaglio(Long idAttivitaDettaglio);

	public List<AttivitaComponente> getComboAttivitaComponenteByIdSottoProcesso(long idSottoProcesso);

	public AttivitaDettaglio getAttivitaDettaglioById(Long idAttivitaDettaglio);

	public List<DatiTipoNormativa> getListaAttivitaDettaglioNormative(AttivitaDettaglioTablePaginator filter);

	public Integer countAllAttivitaDettaglioNormative(AttivitaDettaglioTablePaginator filter);

	public void associaAttivitaDettaglioNormativa(AssAdClasse associazione);

	public long getAssAttivitaDettaglioClassePK(AssAdClasse associazione);

	public void rimuoviAssociazioneAttivitaDettaglioNormativa(long idAssAdClasse);

	public long getIdAreaByIdAttivitaDettaglio(long idAttivitaDettaglio);

	public List<AttivitaComponente> getComboAttivitaComponenteByIdArea(long idArea);


	public List<Processo> getProcessiByIdArea(long idArea);
	public List<SottoProcesso> getSottoProcessiByIdArea(long idArea);
	public List<AttivitaComponente> getAttivitaComponentiByIdArea(long idArea);
	public List<AttivitaDettaglio> getAttivitaDettagliByIdArea(long idArea);

	//MODIFICA ATTIVITA COMPONENTE DOC MEDIA
	public List<DocMediaAttCompDto> getDocMediaAttComp(long idAttivitaComponente, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch);

	public Integer countDocMediaAttComp(long idAttivitaComponente,
			String getsSearch);

	public List<CrsDocumentiMedia> getDocAttCompDaAssociare(long idAttivitaComponente, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch);

	public Integer countDocAttCompDaAssociare(long idAttivitaComponente,String getsSearch);

	public List<DomAttCompDto> getListDominiAttComp(long idAttivitaComponente, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch);

	public Integer countListDominiAttComp(long idAttivitaComponente,
			String search);

	public List<CrsDomini> getListDominiAttCompDaAssociare(
			long idAttivitaComponente, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch);

	public Integer countListDominiAttCompDaAssociare(long idAttivitaComponente,
			String getsSearch);
	
	public List<CompTecAttCompDto> getListCompTecAttComp(
			long idAttivitaComponente, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch);
	

	public Integer countListCompTecAttComp(long idAttivitaComponente,
			String getsSearch);
	
	public List<CrsCompTecnicoDto> getListCompTecAttCompDaAssociare(
			long idAttivitaComponente, int getiDisplayStart,
			int getiDisplayLength, int getiSortCol_0, String getsSortDir_0,
			String getsSearch);

	public Integer countListCompTecAttCompDaAssociare(
			long idAttivitaComponente, String getsSearch);


	
	
	
	//MODIFICA PROCESSO DOC MEDIA
	public List<DocMediaAttCompDto> getDocMediaProcesso(DocumentiProcessoPaginator filter);

	public Integer countDocMediaProcesso(DocumentiProcessoPaginator filter);

	public List<CrsDocumentiMedia> getDocProcessoDaAssociare(DocumentiProcessoPaginator filter);

	public Integer countDocProcessoDaAssociare(DocumentiProcessoPaginator filter);
	
	
	//MODIFICA PROCESSO DOMINI
	public List<DomAttCompDto> getDominiProcesso(DominiProcessoPaginator filter);

	public Integer countDominiProcesso(DominiProcessoPaginator filter);

	public List<CrsDomini> getDominiProcessoDaAssociare(DominiProcessoPaginator filter);

	public Integer countDominiProcessoDaAssociare(DominiProcessoPaginator filter);
	
	
	//MODIFICA PROCESSO COMPONENTI TECNICI
	public List<CompTecAttCompDto> getCompTecProcesso(CompTecniciProcessoPaginator filter);

	public Integer countCompTecProcesso(CompTecniciProcessoPaginator filter);

	public List<CrsCompTecnicoDto> getCompTecProcessoDaAssociare(CompTecniciProcessoPaginator filter);

	public Integer countCompTecProcessoDaAssociare(CompTecniciProcessoPaginator filter);
	
	
	
	//MODIFICA SOTTO PROCESSO DOC MEDIA
	public List<DocMediaAttCompDto> getDocMediaSottoProcesso(DocumentiSottoProcessoPaginator filter);

	public Integer countDocMediaSottoProcesso(DocumentiSottoProcessoPaginator filter);

	public List<CrsDocumentiMedia> getDocSottoProcessoDaAssociare(DocumentiSottoProcessoPaginator filter);

	public Integer countDocSottoProcessoDaAssociare(DocumentiSottoProcessoPaginator filter);
	
	
	//MODIFICA SOTTO PROCESSO DOMINI
	public List<DomAttCompDto> getDominiSottoProcesso(DominiSottoProcessoPaginator filter);

	public Integer countDominiSottoProcesso(DominiSottoProcessoPaginator filter);

	public List<CrsDomini> getDominiSottoProcessoDaAssociare(DominiSottoProcessoPaginator filter);

	public Integer countDominiSottoProcessoDaAssociare(DominiSottoProcessoPaginator filter);
	
	
	//MODIFICA SOTTO PROCESSO COMPONENTI TECNICI
	public List<CompTecAttCompDto> getCompTecSottoProcesso(CompTecniciSottoProcessoPaginator filter);

	public Integer countCompTecSottoProcesso(CompTecniciSottoProcessoPaginator filter);

	public List<CrsCompTecnicoDto> getCompTecSottoProcessoDaAssociare(CompTecniciSottoProcessoPaginator filter);

	public Integer countCompTecSottoProcessoDaAssociare(CompTecniciSottoProcessoPaginator filter);
	
	
	
	//MODIFICA ATTIVITA DETTAGLIO DOC MEDIA
	public List<DocMediaAttCompDto> getDocMediaAttivitaDettaglio(DocumentiAttivitaDettaglioPaginator filter);

	public Integer countDocMediaAttivitaDettaglio(DocumentiAttivitaDettaglioPaginator filter);

	public List<CrsDocumentiMedia> getDocAttivitaDettaglioDaAssociare(DocumentiAttivitaDettaglioPaginator filter);

	public Integer countDocAttivitaDettaglioDaAssociare(DocumentiAttivitaDettaglioPaginator filter);
	
	
	//MODIFICA ATTIVITA DETTAGLIO DOMINI
	public List<DomAttCompDto> getDominiAttivitaDettaglio(DominiAttivitaDettaglioPaginator filter);

	public Integer countDominiAttivitaDettaglio(DominiAttivitaDettaglioPaginator filter);

	public List<CrsDomini> getDominiAttivitaDettaglioDaAssociare(DominiAttivitaDettaglioPaginator filter);

	public Integer countDominiAttivitaDettaglioDaAssociare(DominiAttivitaDettaglioPaginator filter);
	
	
	//MODIFICA ATTIVITA DETTAGLIO COMPONENTI TECNICI
	public List<CompTecAttCompDto> getCompTecAttivitaDettaglio(CompTecniciAttivitaDettaglioPaginator filter);

	public Integer countCompTecAttivitaDettaglio(CompTecniciAttivitaDettaglioPaginator filter);

	public List<CrsCompTecnicoDto> getCompTecAttivitaDettaglioDaAssociare(CompTecniciAttivitaDettaglioPaginator filter);

	public Integer countCompTecAttivitaDettaglioDaAssociare(CompTecniciAttivitaDettaglioPaginator filter);
	
	
	
	
	//op comuni
	public <T> T salva(T obj) ;

	public <T> T cerca(Class<T> obj , Object pk);

	public void remove(Object o);

	

}
