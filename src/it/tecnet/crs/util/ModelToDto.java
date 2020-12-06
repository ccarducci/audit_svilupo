package it.tecnet.crs.util;

import it.tecnet.crs.audit.web.dto.AuAuditDomandaDto;
import it.tecnet.crs.audit.web.dto.AuAuditDto;
import it.tecnet.crs.audit.web.dto.AuAuditMRisesprDto;
import it.tecnet.crs.audit.web.dto.AuAuditNonConfDto;
import it.tecnet.crs.audit.web.dto.AuAuditVarCompDto;
import it.tecnet.crs.audit.web.dto.AuAuditorsDto;
import it.tecnet.crs.audit.web.dto.AuditDelegatoDto;
import it.tecnet.crs.audit.web.dto.AuditFasiDto;
import it.tecnet.crs.audit.web.dto.CampagnaDto;
import it.tecnet.crs.audit.web.dto.ReportSediDto;
import it.tecnet.crs.audit.web.dto.RisultatoRegolaCampagnaCampioneDto;
import it.tecnet.crs.audit.web.dto.SessioniDto;
import it.tecnet.crs.jpa.model.AuTplIsnc;
import it.tecnet.crs.jpa.model.AuTplTipologiche;
import it.tecnet.crs.jpa.model.CrsBatchCaricamentoPratiche;
import it.tecnet.crs.jpa.model.CrsUtenteAdv;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.report.web.dto.ReportAccessoPDFDto;
import it.tecnet.crs.web.dto.AuSSessioneDto;
import it.tecnet.crs.web.dto.NonConformitaAccessiDto;
import it.tecnet.crs.web.dto.NonConformitaDto;
import it.tecnet.crs.web.dto.NonConformitaVerbaleDto;
import it.tecnet.crs.web.dto.PraticaConRischioDto;
import it.tecnet.crs.web.dto.RischiAccessiDto;
import it.tecnet.crs.web.dto.SessioneDto;
import it.tecnet.crs.web.dto.VerbaleDto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ModelToDto {
	
	public static SessioniDto modelToSessioniDto(Object[] objList){
		SessioniDto dto= new SessioniDto();
		dto.setIdSessione((Long)objList[0]);
		dto.setCampagnaDesc((String)objList[1]);
		dto.setSede((String)objList[2]);
		dto.setDataInizio((Date)objList[3]);
		dto.setDataFine((Date)objList[4]);
		dto.setStato((String)objList[5]);
		
		return dto;
	}
	
	public static RisultatoRegolaCampagnaCampioneDto modelToRisultatoRegolaCampagnaCampioneDto(Object[] objList){
		RisultatoRegolaCampagnaCampioneDto dto= new RisultatoRegolaCampagnaCampioneDto();
		//int index=0;
		dto.setTipo((String)objList[0]);
		dto.setDescrizione((String)objList[1]);
		dto.setRes1((String)objList[2]);
		dto.setRes2((String)objList[3]);
		dto.setRes3((String)objList[4]);
		dto.setRes4((String)objList[5]);
		
		return dto;
	}
	
	public static CampagnaDto modelToCampagnaDto(Object[] objList){
		CampagnaDto dto= new CampagnaDto();
		//int index=0;
		dto.setIdCampagna((Long)objList[0]);
		dto.setNome((String)objList[1]);
		dto.setNomeAudit((String)objList[2]);
		dto.setDataInizio((Date)objList[3]);
		dto.setDataFine((Date)objList[4]);
		dto.setDataInizioOsservazione((Date)objList[5]);
		dto.setDataFineOsservazione((Date)objList[6]);
		dto.setStato((String)objList[7]);
		dto.setTipo((String)objList[8]);
		
		return dto;
	}
	
	public static ReportSediDto modelToReportSediDto(Object[] objList){
		ReportSediDto dto= new ReportSediDto();
		dto.setIdSede((Long)objList[0]);
		dto.setSede((String)objList[1]);
		dto.setIndicatore1((String)objList[2]);
		dto.setIndicatore2((String)objList[3]);
		dto.setIndicatore3((String)objList[4]);
		dto.setIndicatore4((String)objList[5]);
		dto.setIndicatore5((String)objList[6]);
		dto.setIndicatore6((String)objList[7]);
		dto.setIndicatore7((String)objList[8]);
		dto.setIndicatore8((String)objList[9]);
		dto.setIndicatore9((String)objList[10]);
		dto.setIndicatore10((String)objList[11]);
		
		return dto;
	}
	
	public static SessioneDto modelToDto(Object[] objList){
		SessioneDto sessione= new SessioneDto();
		int i=0;

		try{
			if(objList[i]!=null){
				sessione.setIdSessione((Long)objList[i]);
			}i++;
			if(objList[i]!=null){
				sessione.setCampagna((String)objList[i]);
			}i++;
			if(objList[i]!=null){
				sessione.setAudit((String)objList[i]);
			}i++;
			if(objList[i]!=null){
				sessione.setSede((String)objList[i]);
			}i++;
			if(objList[i]!=null){
				sessione.setDataInizio(objList[i]);
			}i++;
			if(objList[i]!=null){
				sessione.setDataFine(objList[i]);
			}i++;
			if(objList[i]!=null){
				sessione.setStato((String)objList[i]);
			}i++;
			if(objList[i]!=null){
				sessione.setDirigente((String)objList[i]);
			}i++;
			if(objList[i]!=null){
				sessione.setTipo((String)objList[i]);
			}i++;
		}catch(Exception e){
			//e.printStackTrace();
		}
		
		return sessione;
		
	}
	
	public static VerbaleDto modelToVerbaleDto(Object[] objList){
		
		int index=0;
		
		VerbaleDto verbale= new VerbaleDto();
		verbale.setIdVerbale((Long)objList[index]);
		
		if(objList[index++] !=null){
			verbale.setProtocollo((String)objList[index]);
		}
		if(objList[index++] != null || !objList[index].toString().isEmpty()){
			verbale.setAzienda((String)objList[index]);
		}
		
		if(objList[index++] != null || !objList[index].toString().isEmpty()){
			verbale.setCodiceFiscale((String)objList[index]);
		}
		
		if(objList[index++] !=null || !objList[index].toString().isEmpty()){
			verbale.setDataFineIspezione((Date)objList[index]);
		}
		if(objList[index++] !=null || !objList[index].toString().isEmpty()){
			verbale.setDataInserimentoNotifica((Date)objList[index]);
		}
		if(objList[index++] != null || !objList[index].toString().isEmpty()){
			verbale.setTipoNotifica((String)objList[index]);
		}
		if(objList[index++] !=null){
			verbale.setImporto((BigDecimal )objList[index]);
		}
		
		return verbale;	
		
	}
	public static PraticaConRischioDto modelToPraticaConRischioDto(Object[] objList){
		
		PraticaConRischioDto praticaConRischio= new PraticaConRischioDto();
		
		praticaConRischio.setIdVerbale((Long)objList[0]);
		praticaConRischio.setProtocollo((String)objList[1]);
		praticaConRischio.setAzienda((String)objList[2]);
		praticaConRischio.setPartitaIva((String)objList[3]);
		praticaConRischio.setDataFineIspezione(objList[4]);
		praticaConRischio.setDataNotifica(objList[5]);
		
		praticaConRischio.setTipoNotifica((String)objList[6]);
		praticaConRischio.setImporto((BigDecimal)objList[7]);
		praticaConRischio.setRischi((String)objList[8]);
		
		
		return praticaConRischio;	
		
	}
	
	
	
	


	
	public static ProcessoDto modelToProcessoDto(Object[] objList){
		
		ProcessoDto processo= new ProcessoDto();
		
		processo.setIdProcesso((Long)objList[0]);
		if(objList[1] != null){
			processo.setOrdinamento((Integer)objList[1]);
		}
		
		processo.setDescrizione((String)objList[2]);
		processo.setDescrizioneArea((String)objList[3]);
		processo.setDataInizioAsString(new SimpleDateFormat("dd/MM/yyyy").format(objList[4]));
		if(objList[5] != null){
			processo.setDataFineAsString(new SimpleDateFormat("dd/MM/yyyy").format(objList[5]));	
		}
		processo.setStato((String)objList[6]);
		processo.setIdArea((Long)objList[7]);
		processo.setPubblicazione((String)objList[8]);
		
//		processo.setDataInizioArea(new SimpleDateFormat("dd/MM/yyyy").format(objList[8]));
//		processo.setDataFineArea(new SimpleDateFormat("dd/MM/yyyy").format(objList[9]));
//		processo.setStatoArea((String)objList[12]);
		
		return processo;
		
	}
	
	public static NonConformitaVerbaleDto modelToNonConformitaVerbaleDto(Object[] objList){
		
		NonConformitaVerbaleDto verbale= new NonConformitaVerbaleDto();
		verbale.setIdNCV((Long)objList[0]);
		verbale.setVComp((String)objList[1]);
		verbale.setRischio((String)objList[2]);
		verbale.setValoreCalcolato((java.math.BigDecimal)objList[3]);
		verbale.setValoreReale((java.math.BigDecimal)objList[4]);
		verbale.setNote((String)objList[5]);
		return verbale;			
	}
	
	//popola le liste di fase definizione e fase notifica del TAB modifica-accessi
	
	public static NonConformitaDto modelToNonConformitaDto(Object[] objList){
		NonConformitaDto nonConformita= new NonConformitaDto();
		nonConformita.setIdNonConformita((Long)objList[0]);
		nonConformita.setDescrizioneNonConformita((String)objList[1]);
		nonConformita.setDescrizione((String)objList[2]);
		nonConformita.setMedia((java.math.BigDecimal)objList[3]);
		
		return nonConformita;
	}

	public static AuAuditDto modelToAuditDto(Object[] obj) {
		AuAuditDto a= new AuAuditDto();
		a.setIdAudit((Long)obj[0]);
		a.setNome((String)obj[1]);
		a.setDescrizione((String)obj[2]);
		a.setDataInizio((Date)obj[3]);
		a.setDataFine((Date)obj[4]);
		a.setInput((String)obj[5]);
		a.setOutput((String)obj[6]);
		a.setObiettivo((String)obj[7]);
		
		return a;
	}
	
	public static AuTplTipologiche modelToTipologicheDto(Object[] obj) {
		AuTplTipologiche a= new AuTplTipologiche();
		
		
		a.setIdTplTipologica((Long)obj[0]);
		a.setTipo((String)obj[1]);
		a.setCodifica((String)obj[2]);
		a.setDescrizione((String)obj[3]);
		return a;
	}
	public static CrsBatchCaricamentoPratiche modelTocaricamentoPraticheDto(Object[] obj) {
		CrsBatchCaricamentoPratiche a= new CrsBatchCaricamentoPratiche();
		
		
		a.setIdBcp((Long)obj[0]);
		a.setIdCampagna((Long)obj[1]);
		a.setCodiceSede((String)obj[2]);
		a.setAnnoConclusione((Integer)obj[3]);
		
		a.setDataInizio((Date)obj[4]);
		a.setDataFine((Date)obj[5]);
		a.setEsito((String)obj[6]);
		a.setDescErrore((String)obj[7]);
		if(obj.length>7){
			a.setNomeCampagna((String)obj[8]);
			a.setNomeSede((String)obj[9]);
		}
		
		return a;
	}
	public static AuTplIsnc  modelToIsncDto(Object[] obj) {
		AuTplIsnc a= new AuTplIsnc();
		
		
		a.setIdTplIsnc((Long)obj[0]);
		a.setSoglia((String)obj[1]);
		a.setDataInizio((Date)obj[2]);
		a.setDataFine((Date)obj[3]);
		a.setColore((String)obj[4]);
		a.setIdAudit((Long)obj[5]);
		if(obj.length>5)
			a.setNomeaudit((String)obj[6]);
		
		return a;
	}


	public static CrsUtenteAdv modelToUsersDto(Object[] obj) {
		CrsUtenteAdv a= new CrsUtenteAdv();
		
		
		a.setIdUtente((Long)obj[0]);
		a.setNome((String)obj[1]);
		a.setCognome((String)obj[2]);
		a.setUsername((String)obj[3]);
		a.setEmail((String)obj[4]);
		a.setAttivo((String)obj[5]);
		a.setDataUltimoAccesso((Date)obj[6]);
//		a.setObiettivo((String)obj[7]);
		
		return a;
	}

	public static AuditFasiDto modelToAuditFasiDto(Object[] obj, long idAudit) {
		AuditFasiDto a = new AuditFasiDto();
		a.setIdAudit(idAudit);
		
		a.setIdSottoprocesso((Long) obj[0]);
		a.setDescrizioneProcesso((String) obj[1]);
		a.setDescrizione((String)obj[2]);
		a.setInput((String)obj[3]);
		a.setOutput((String)obj[4]);
		a.setUnitaOperativeCoinvolte((String)obj[5]);
		a.setStato((String) obj[6]);
		
		
		return a;
	}
	public static AuditFasiDto modelToAuditFasiProcessiDto(Object[] obj, long idAudit) {
		AuditFasiDto a = new AuditFasiDto();
		a.setIdAudit(idAudit);
		a.setIdSottoprocesso((Long) obj[0]);
		a.setIdProcesso((Long) obj[1]);
		a.setDescrizioneProcesso((String) obj[2]);
		a.setDescrizione((String)obj[3]);
		a.setUnitaOperativeCoinvolte((String)obj[4]);
		a.setInput((String)obj[5]);
		a.setOutput((String)obj[6]);
		a.setStato((String) obj[7]);
		
		
		return a;
	}
	
	public static AuditFasiDto modelToAuditFasiDtoAside(Object[] obj, long idProcesso, long idAudit) {
		AuditFasiDto a = new AuditFasiDto();
		
		a.setIdProcesso(idProcesso);
		a.setIdAudit(idAudit);
		a.setIdSottoprocesso((Long) obj[0]);
		a.setDescrizioneProcesso((String) obj[1]);
		a.setDescrizione((String)obj[2]);
		a.setUnitaOperativeCoinvolte((String)obj[3]);
		a.setInput((String)obj[4]);
		a.setOutput((String)obj[5]);
		a.setStato((String) obj[6]);
		
		
		
		return a;
	}
	
	public static VerbaleDto modelToAuditVerbaleDto(Object[] obj) {
		VerbaleDto a = new VerbaleDto();
		
		a.setIdVerbale((Long) obj[0]);
		a.setSede((String) obj[1]);
		a.setAzienda((String)obj[2]);
		a.setCodiceFiscale((String)obj[3]);
		a.setImporto((java.math.BigDecimal)obj[4]);
		a.setDataFineIspezione((Date)obj[5]);
		
		
		
		return a;
	}
	

	public static AuAuditMRisesprDto modelToEsprRischioDto(Object[] o) {
		
		
			AuAuditMRisesprDto esprRDto=new AuAuditMRisesprDto();
			esprRDto.setIdMRisepr((Long)o[0]);
			esprRDto.setCodiceRischio((String)o[1]);
			esprRDto.setIdMRischio((Long)o[2]);
			esprRDto.setDescrizione((String)o[3]);
			esprRDto.setDescrizioneRischio((String)o[4]);
			esprRDto.setCodice((String)o[5]);
			esprRDto.setPercTotaleRischio((BigDecimal)o[6]);
			esprRDto.setRaggruppamentoRischio((String)o[7]);
			esprRDto.setDataInizio((Date)o[8]);
			esprRDto.setDataFine((Date)o[9]);
			
	
		return esprRDto;
	}
public static AuditDelegatoDto modelToDelegatoDto(Object[] obj){
		
		AuditDelegatoDto delegato=new AuditDelegatoDto();
		delegato.setIdUtente((Long)obj[0]);
		delegato.setNome(obj[1].toString());
		delegato.setCognome(obj[2].toString());
		delegato.setAssociazione(obj[3].toString());
		
		return delegato;
	}

public static AuAuditorsDto modelToAuditorsDto(Object[] obj){
	
	AuAuditorsDto auditors=new AuAuditorsDto();
	auditors.setIdUtente((Long)obj[0]);
	auditors.setNome(obj[1].toString());
	auditors.setCognome(obj[2].toString());
	auditors.setUsername(obj[3].toString());
	auditors.setAssociazione(obj[4].toString());
	
	return auditors;
}

public static AuAuditNonConfDto modelToNonConfDto(Object[] o) {
		AuAuditNonConfDto nc= new AuAuditNonConfDto();
		nc.setIdNonConform((Long)o[0]);
		nc.setIdFase((Long)o[1]);
		nc.setCodiceNc((String) o[2]);
		nc.setDescrizioneNonConform((String) o[3]);
		nc.setDescrizioneAudit((String) o[4]);
		nc.setDescrizioneFase((String) o[5]);
		nc.setPeso(((BigDecimal )o[6]).doubleValue());
		nc.setDataInizio((Date)o[7]);
		nc.setDataFine((Date)o[8]);
		return nc;
	}

public static AuAuditVarCompDto modelToVarCompDto(Object[] o) {
	AuAuditVarCompDto vc= new AuAuditVarCompDto();
	vc.setIdVarC((Long)o[0]);
	vc.setIdNC((Long)o[1]);
	vc.setDescrizioneVC((String) o[2]);
	vc.setDescrizioneNC((String) o[3]);
	vc.setCodiceVC((String) o[4]);
	vc.setPesoVC((BigDecimal)o[5]);
	vc.setDataInizioVC((Date)o[6]);
	vc.setDataFineVC((Date)o[7]);
	return vc;
}

public static AuAuditDomandaDto modelToDomandaDto(Object[] o) {
	AuAuditDomandaDto d = new AuAuditDomandaDto();
	d.setIdQuestionario((Long)o[0]);
	d.setIdDomanda((Long)o[1]);
	d.setIdRischio((Long)o[2]);
	d.setDescrizione((String) o[3]);
	d.setPeso((Long)o[4]);
	d.setValMaxRsp((Long)o[5]);
	d.setDescrizioneRischio((String) o[6]);
	d.setPesoPErcentuale((BigDecimal)o[7]);
	d.setControlloProcesso((String) o[8]);
	
	return d;
}


public static AuSSessioneDto modelToSSessioneDto(Object[] o) {
	
	AuSSessioneDto ss = new AuSSessioneDto();
	
	if(o[0] != null){
		ss.setIdSSessione((Long)o[0]);
	}
	if(o[1] != null){
		ss.setDataInizio((Date)o[1]);
	}
	if(o[2] != null){
		ss.setDataFine((Date)o[2]);
	}
	if(o[3] != null){
		ss.setNumeroPratiche((Integer)o[3]);
	}
	if(o[4] != null){
		ss.setNumeroPraticheEsaminate((Integer)o[4]);
	}
	if(o[5] != null){
		//ss.setMinimo((Integer)o[5]);
		ss.setMinimo(((BigDecimal)o[5]).doubleValue());
	}
	if(o[6] != null){
		//ss.setMassimo((Integer)o[6]);
		ss.setMassimo(((BigDecimal)o[6]).doubleValue());
	}
	if(o[7] != null){
		//ss.setMedia((Integer)o[7]);
		ss.setMedia(((BigDecimal)o[7]).doubleValue());
	}
	if(o[8] != null){
		//ss.setDevStandard((Integer)o[8]);
		ss.setDevStandard(((BigDecimal)o[8]).doubleValue());
	}
	if(o[9] != null){
		//ss.setINCC((Double)o[9]);
		ss.setINCC(((BigDecimal)o[9]).doubleValue());
	}
	if(o[10] != null){
		ss.setINCCDescrizione((String)o[10]);
	}
	if(o[11] != null){
		ss.setStatoPratiche((String)o[11]);
	}
	if(o[12] != null){
		ss.setStatoEsameSessione((String)o[12]);
	}
	if(o[13] != null){
		ss.setDataAggiornamentoDatiSessione((Date)o[13]);
	}
	// ----
	if(o[14] != null){
		ss.setNumDissAmm((Integer)o[14]);
	}
	if(o[15] != null){
		ss.setNumDissSan((Integer)o[15]);
	}
	if(o[16] != null){
		ss.setPercnumDissAmm(((BigDecimal)o[16]).doubleValue());
	}
	if(o[17] != null){
		ss.setPercnumDissSan(((BigDecimal)o[17]).doubleValue());
	}
	return ss;
}

public static NonConformitaAccessiDto modelToNonConformitaAccessiDto(Object[] o, boolean isDettaglio) {
	
	NonConformitaAccessiDto nca = new NonConformitaAccessiDto();
	int i=0;
	if(o[i] != null){
		nca.setIdMNonConf(((Long)o[i]).longValue());
	}i++;
	if(o[i] != null){
		nca.setIdSNonConf(((Long)o[i]).longValue());
	}i++;
	if(!isDettaglio){
		if(o[i] != null){
			nca.setOrdinamento(((Integer)o[i]));
		}i++;
		if(o[i] != null){
			nca.setDescrizioneFase(((String)o[i]).toString().trim());
		}i++;
	}
	
	if(o[i] != null){
		nca.setDescrizione((String)o[i].toString().trim());
	}i++;
	if(o[i] != null){
		nca.setCodice((String)o[i].toString().trim());
	}i++;
	if(o[i] != null){
		nca.setDataInizio((String)o[i]);
	}i++;
	if(o[i] != null){
		nca.setDataFine((String)o[i]);
	}i++;
	if(o[i] != null){
		nca.setQuantita(((Integer)o[i]).intValue());
	}i++;
	if(o[i] != null){
		nca.setValoreINCC(((BigDecimal)o[i]).doubleValue());
	}i++;
	
	if(isDettaglio){
		
		if(o[i] != null){
			nca.setPeso((BigDecimal)o[i]);
		}i++;
		if(o[i] != null){
			nca.setValorepesato((BigDecimal)o[i]);
		}i++;
		if(o[i] != null){
			nca.setMinimo(((Integer)o[i]).intValue());
		}i++;
		if(o[i] != null){
			nca.setMassimo(((Integer)o[i]).intValue());
		}i++;
		if(o[i] != null){
			nca.setNcConValoreMinimo((String)o[i]);
		}i++;
		if(o[i] != null){
			nca.setNcConValoreMassimo((String)o[i]);
		}i++;
	}
	
	return nca;
	
}


public static RischiAccessiDto modelToRischiAccessiDto(Object[] o, boolean isDettaqlio) {
	
	RischiAccessiDto rsa = new RischiAccessiDto();
	
	if(o[0] != null){
		rsa.setIdRischioM(((Long)o[0]).longValue());
	}
	if(o[1] != null){
		rsa.setIdRischioS(((Long)o[1]).longValue());
	}
	if(o[2] != null){
		rsa.setCodice((String)o[2].toString().trim());
	}
	if(o[3] != null){
		rsa.setDescrizione((String)o[3].toString().trim());
	}
	if(o[4] != null){
		rsa.setNum(((Integer)o[4]).intValue());
	}
	if(o[5] != null){
		BigDecimal stp=(BigDecimal)o[5];
		rsa.setSuTotPerc(stp.multiply(new BigDecimal(100)));
	}
	if(o[6] != null){
		BigDecimal sps=(BigDecimal)o[6];
		rsa.setSuPsPerc(sps.multiply(new BigDecimal(100)));
	}
	
	if(isDettaqlio){
		
		if(o[7] != null){
			BigDecimal pr=(BigDecimal)o[7];
			rsa.setPesoRischio(pr.multiply(new BigDecimal(100)));
		}
		if(o[8] != null){
			BigDecimal imp=(BigDecimal)o[8];
			rsa.setImporto(imp.multiply(new BigDecimal(100)));
		}

	}
	
	return rsa;
	
}
public static ReportAccessoPDFDto modelToAllegatoReportAccessoPDFDto(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
//	pra.ID_PRATICA,	mr.ID_M_RISCHIO  	, mr.CODICE_RISCHIO  	, mr.DESCRIZIONE_RISCHIO  	, risespr.CODICE  	, risespr.DESCRIZIONE  	, spris.DATA_ATTRIBUZIONE  	, spris.IMPORTO ,pratic.FASCICOLO
	
	if(o[0] != null){
		dto.setDirigente((String)o[0]);
	}
	if(o[1] != null){
		dto.setSede((String)o[1]);
	}
	if(o[2] != null){
		dto.setIdSessione((Long)o[2]);
	}
	if(o[3] != null){
		dto.setIdSSessione((Long)o[3]);
	}
	if(o[4] != null){
		dto.setDataInizio((String)o[4]);
	}
	if(o[5] != null){
		dto.setDataFine((String)o[5]);
	}
	if(o[6] != null){
		dto.setDataInizioOsservazione((String)o[6]);
	}
	if(o[7] != null){
		dto.setDataFineOsservazione((String)o[7]);
	}
	if(o[8] != null){
		dto.setNumeroPraticheEsaminate((Integer)o[8]);
	}
	if(o[9] != null){
		dto.setNumeroPraticheND((Integer)o[9]);
	}
	if(o[10] != null){
		dto.setNumeroPratiche((Integer)o[10]);
	}
	if(o[11] != null){
		dto.setINCC(((BigDecimal)o[11]).doubleValue());
	}
	if(o[12] != null){
		dto.setINCCDescrizione((String)o[12]);
	}
	if(o[13] != null){
		dto.setDataAggiornamentoDatiSessione((String)o[13]);
	}
		
	return dto;
}

public static ReportAccessoPDFDto modelToReportAccessoPDFDto(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setDirigente((String)o[0]);
	}
	if(o[1] != null){
		dto.setSede((String)o[1]);
	}
	if(o[2] != null){
		dto.setIdSessione((Long)o[2]);
	}
	if(o[3] != null){
		dto.setIdSSessione((Long)o[3]);
	}
	if(o[4] != null){
		dto.setDataInizio((String)o[4]);
	}
	if(o[5] != null){
		dto.setDataFine((String)o[5]);
	}
	if(o[6] != null){
		dto.setDataInizioOsservazione((String)o[6]);
	}
	if(o[7] != null){
		dto.setDataFineOsservazione((String)o[7]);
	}
	if(o[8] != null){
		dto.setNumeroPraticheEsaminate((Integer)o[8]);
	}
	if(o[9] != null){
		dto.setNumeroPraticheND((Integer)o[9]);
	}
	if(o[10] != null){
		dto.setNumeroPratiche((Integer)o[10]);
	}
	if(o[11] != null){
		dto.setINCC(((BigDecimal)o[11]).doubleValue());
	}
	if(o[12] != null){
		dto.setINCCDescrizione((String)o[12]);
	}
	if(o[13] != null){
		dto.setDataAggiornamentoDatiSessione((String)o[13]);
	}
		
	return dto;
}

public static ReportAccessoPDFDto modelToRiepilogoFasiPDF(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setIdFase((Long)o[0]);
	}
	if(o[1] != null){
		dto.setDescrizioneFase((String)o[1]);
	}
	if(o[2] != null){
		dto.setRiepilogoINCC((BigDecimal)o[2]);
	}

		
	return dto;
}

public static ReportAccessoPDFDto modelToVarCompPDF(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setDescrizioneVarComp((String)o[0]);
	}
	if(o[1] != null){
		dto.setNumSVarComp((Integer)o[1]);
	}
	if(o[2] != null){
		dto.setNumSNonConf((Integer)o[2]);
	}
	if(o[3] != null){
		dto.setPercentuale((BigDecimal)o[3]);
	}
	if(o[4] != null){
		dto.setColore((String)o[4]);
	}
	if(o.length>4 && o[5] != null){
		dto.setCodiceVC((String)o[5]);
	}	
	return dto;
}

public static ReportAccessoPDFDto modelToRiepilogoRischiPDF(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setIdMRischio((Long)o[0]);
	}
	if(o[1] != null){
		dto.setIdSRischio((Long)o[1]);
	}
	if(o[2] != null){
		dto.setDescrizioneRischio((String)o[2]);
	}
	if(o[3] != null){
		dto.setImportoRischio((BigDecimal)o[3]);
	}
	if(o[4] != null){
		dto.setPercRischio((BigDecimal)o[4]);
	}
	if(o[5] != null){
		dto.setNumSRischio((Integer)o[5]);
	}
	return dto;
}

public static ReportAccessoPDFDto modelToRisEsprPDF(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setDescrizioneRisEspr((String)o[0]);
	}
	if(o[1] != null){
		dto.setNumSRischio((Integer)o[1]);
	}
	if(o[2] != null){
		dto.setSuPsPerc((BigDecimal)o[2]);
	}
	if(o[3] != null){
		dto.setImporto((BigDecimal)o[3]);
	}

		
	return dto;
}

public static ReportAccessoPDFDto modelToRiepilogoFascicoloPDF(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setDescrizioneFascicolo((String)o[0]);
	}
	if(o[1] != null){
		Class tipo  = o[1].getClass();
		if (tipo == Integer.class){
			dto.setQuantita((Integer)o[1]);
		}else if (tipo == Long.class){
			Long val = (Long)o[1];
			dto.setQuantita(val.intValue());
		}
		
	}

		
	return dto;
}


public static ReportAccessoPDFDto modelToEsitoDifesaPerSedePDF(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setTipoDifesa(((String)o[0]).trim());
	}
	if(o[1] != null){
		dto.setDescrizioneEsito(((String)o[1]).trim());
	}
	if(o[1] != null){
		dto.setQuantita((Integer)o[2]);
	}
	return dto;
}

public static ReportAccessoPDFDto modelToEsitoDifesaPDF(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setDescrizioneEsito((String)o[0]);
	}
	if(o[1] != null){
		dto.setQuantita((Integer)o[1]);
	}
	return dto;
}

public static ReportAccessoPDFDto modelToEsitoDifesaWithCodePDF(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setDescrizioneEsito((String)o[0]);
	}
	if(o[1] != null){
		Class tipo  = o[1].getClass();
		if (tipo == String.class){
			String difesa = (String)o[1];
			dto.setTipoDifesa(difesa);
		}
		
	}
	if(o[2] != null){
		dto.setQuantita((Integer)o[2]);
	}
	return dto;
}

public static ReportAccessoPDFDto modelToRiepilogoGiudiziPDF(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setDescrizioneEsito((String)o[0]);
	}
	if(o[1] != null){
		dto.setNumeroGiudizi((Integer)o[1]);
	}
	if(o[2] != null){
		dto.setPercentuale((BigDecimal)o[2]);
	}
	if(o[3] != null){
		dto.setNumeroPrestazioni((Integer)o[3]);
	}
	if(o[4] != null){
		dto.setImportoPrestazioni((BigDecimal)o[4]);
	}
	if(o[5] != null){
		dto.setSpeseLegali((BigDecimal)o[5]);
	}
	if(o[6] != null){
		dto.setSpeseCtu((BigDecimal)o[6]);
	}
		
	return dto;
}

public static ReportAccessoPDFDto modelToRisultatiByTempoPDF(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setDescrizioneTemporale((String)o[0]);
	}
	if(o[1] != null){
		dto.setMediaGG((BigDecimal)o[1]);
	}
	if(o[2] != null){
		dto.setNC((String)o[2]);
	}
		
	return dto;
}

public static ReportAccessoPDFDto modelToRiepilogoIstanzePDF(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setDescrizioneEsito((String)o[0]);
	}
	if(o[1] != null){
		dto.setQuantita((Integer)o[1]);
	}

		
	return dto;
}

public static ReportAccessoPDFDto modelToMNonConfPDF(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setIdMNonConf((Long)o[0]);
	}
	if(o[1] != null){
		dto.setDescrizioneMNonConf((String)o[1]);
	}
	if(o[2] != null){
		dto.setRiepilogoINCC((BigDecimal)o[2]);
	}

		
	return dto;
}

public static ReportAccessoPDFDto modelToTipoDifesaIncompletaPDF(Object[] o) {
	
	ReportAccessoPDFDto dto = new ReportAccessoPDFDto();
	
	if(o[0] != null){
		dto.setDescrizioneEsito((String)o[0]);
	}
	if(o[1] != null){
		dto.setQuantita((Integer)o[1]);
	}
	if(o[2] != null){
		dto.setPercentuale((BigDecimal)o[2]);
	}

		
	return dto;
}

}
