package it.tecnet.crs.audit.jpa.dao;

import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAcquisizioneIstanza;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseAutotutelaResistenzaGiudizio;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseDati;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseEsecuzioneProvvedimenti;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFaseGestioneIstruttoria;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePeritale;
import it.tecnet.crs.ATPO.auditors.jpa.model.AtpoFasePostPeritale;
import it.tecnet.crs.audit.web.dto.CalcoloIndicatoriRiepilogoPraticheNonConfFasi;
import it.tecnet.crs.indicatori.campagna.AU_C_VARCOMP;
import it.tecnet.crs.indicatori.campagna.CampagnaNonConfDto;
import it.tecnet.crs.indicatori.sessione.AuTotH3PerRischio;
import it.tecnet.crs.indicatori.sessione.RiepilogoTipologica;
import it.tecnet.crs.jpa.model.AuCampagna;
import it.tecnet.crs.jpa.model.AuInccDes;
import it.tecnet.crs.jpa.model.AuMNonConf;
import it.tecnet.crs.jpa.model.AuMRischio;
import it.tecnet.crs.jpa.model.AuMRisepr;
import it.tecnet.crs.jpa.model.AuMVarcomp;
import it.tecnet.crs.jpa.model.AuSPratica;
import it.tecnet.crs.jpa.model.AuSPraticaRis;
import it.tecnet.crs.jpa.model.AuSPraticaVarcomp;
import it.tecnet.crs.jpa.model.AuSTvalori;
import it.tecnet.crs.jpa.model.AuTplIsnc;
import it.tecnet.crs.jpa.model.AuTplTipologiche;
import it.tecnet.crs.web.dto.StatoSessionePratica;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface AuCalcolaIndicatoriDao {

	@Transactional
	public <T> Object salva(T obj);

	public int setEsamePratica(long idVerbale, String stato, String userId, String userIdToUpdate);
	
	public int setPraticaInLavorazione(long idVerbale, String userId);
	
	public int setPraticaInCalcolata(long idVerbale, String userId);

	public boolean cleanCalcoloIndiciPraticaLog(long idVerbale);

	public boolean isSSsessionOpen(Long idSSsessione);

	public StatoSessionePratica canCalculateIndicatori(long idVerbale);

	public <T> T findById(Class<T> entityClass, Object primaryKey);

	public AtpoFaseDati getFaseDatiByCodSedeAndFascicolo(String codSede,
			String fascicolo);

	public AtpoFasePostPeritale getAtpoFasePostPeritaleByIdFaseDati(
			long idFaseDati);

	public AtpoFaseEsecuzioneProvvedimenti getAtpoFaseEsecuzioneProvvedimentiByIdFaseDati(
			long idFaseDati);

	public AtpoFasePeritale getAtpoFasePeritaleByIdFaseDati(long idFaseDati);

	public AtpoFaseGestioneIstruttoria getAtpoFaseGestioneIstruttoriaByIdFaseDati(
			long idFaseDati);

	public AtpoFaseAutotutelaResistenzaGiudizio getAtpoFaseAutotutelaResistenzaGiudizioByIdFaseDati(
			long idFaseDati);

	public AtpoFaseAcquisizioneIstanza getAtpoFaseAcquisizioneIstanzaByIdFaseDati(
			long idFaseDati);

	public AuSPratica getSPraticaByIDPratica(long idPratica);

	public AuCampagna getCampagnaById(long idCampagna);

	public long getIdCambagnaByIdSSessione(long idSSessione);

	public List<AuMVarcomp> getAuMVarcomp();

	public List<AuMRischio> getRishioByAudit(long idAudit);

	public AuSPratica getSPraticaByIdPratica(long idVerbale);

	@Transactional
	public void deleteSPtaricaRisByIdSPratica(long idSPratica);

	@Transactional
	public void deleteSPtaricaVarcompByIdSPratica(long idSPratica);

	public void cleanCalcoloIndiciSessione(long idSSessione);

	public List<AuSPratica> getSPraticaByIdSSessione(long idSSessione);
	
	public List<AuSPratica> getSPraticaClosedByIdSSessione(long idSSessione);

	public List<AuSPraticaVarcomp> getAuSPraticaClosedVarcompBySessione(
			long idSSessione);

	public List<AuMNonConf> getAuNonconf();

	public List<AuMRisepr> getAuMRisepr();

	public List<AuSPraticaRis> getSPraticaRisClosedByIdSSessione(
			long idSSessione);
	
	public List<AuTotH3PerRischio> getNumPraticheRischiH3NonSoggette(
			long idSSessione);

	@Transactional
	public <T> T update(T entity);

	public List<AuMRischio> getAuMRischio();

	public List<AuInccDes> getAuInccDes();

	public List<AuSTvalori> getAuSTvalori();

	public void persist(Object entity);

	public List<Object[]> getRischioRaggruppamento(Long idSSessione);

	public void riapriSessione(long idSSessione);

	public List<RiepilogoTipologica> getRiepilogoTipologicaTDocmanc(long idSSessione);

	public List<RiepilogoTipologica> getRiepilogoTipologicaTFascicolo(
			long idSSessione);

	public List<RiepilogoTipologica> getRiepilogoTipologicaSTempi(
			long idSSessione);

	public List<RiepilogoTipologica> getRiepilogoTipologicaSTesito(
			long idSSessione);

	public List<AuTplTipologiche> getTipologica();

	int getNumDissensiByTipo(String tipo);

	public List<AuTplIsnc> getAuTplIsnc();

	public List<String> getAtpoFasePeritaleByIdSSessione(
			long idSSessione);

	public Double getPraticheEsamintate(long idSSessione);
	
	public List<CalcoloIndicatoriRiepilogoPraticheNonConfFasi> getRiepilogoFasiNonConf(long idSSessione);
    
	// ----------------------------------------------------------------------------
	public List<CampagnaDto> getDatiCampagnaVarCompDto(long idCampagna);
	List<CampagnaMVarCompDto> getSumiCampagnaByIdMVarCompDto(long idCampagna);
	void deleteDatiCampagnaVarComp(long idCampagna);
	public void insertDatiCampagnaVarComp(AU_C_VARCOMP itemToInsert);
	public List<SoglieDto> getSoglieTipologica();
	public void deleteDatiCampagnaNonCConf(long idCampagna);
	public List<CampagnaNonConfDto> getDatiCampagnaVNonConfDto(long idCampagna);
}
