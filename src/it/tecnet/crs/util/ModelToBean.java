package it.tecnet.crs.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import it.tecnet.crs.ATPO.auditors.web.dto.PraticheAtpoDto;
import it.tecnet.crs.audit.web.dto.ReportSediDto;
import it.tecnet.crs.componenti.jpa.model.CrsDatiTipo;
import it.tecnet.crs.componenti.jpa.model.CrsTipo;
import it.tecnet.crs.componenti.web.bean.CircolariInps;
import it.tecnet.crs.componenti.web.bean.DatiTipoNormativa;
import it.tecnet.crs.componenti.web.bean.LeggiDecreti;
import it.tecnet.crs.componenti.web.bean.MessaggiInps;
import it.tecnet.crs.componenti.web.bean.NoteDecreti;
import it.tecnet.crs.componenti.web.bean.TipoNormativa;
import it.tecnet.crs.jpa.model.CrsUtente;
import it.tecnet.crs.mod.jpa.model.CrsArea;
import it.tecnet.crs.mod.jpa.model.CrsAttivitaComponente;
import it.tecnet.crs.mod.jpa.model.CrsAttivitaDettaglio;
import it.tecnet.crs.mod.jpa.model.CrsProcesso;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;
import it.tecnet.crs.mod.service.ModellazioneService;
import it.tecnet.crs.mod.service.ModellazioneServiceImpl;
import it.tecnet.crs.mod.web.bean.Area;
import it.tecnet.crs.mod.web.bean.AttivitaComponente;
import it.tecnet.crs.mod.web.bean.AttivitaDettaglio;
import it.tecnet.crs.mod.web.bean.Processo;
import it.tecnet.crs.mod.web.bean.SottoProcesso;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.web.beans.AppUser;


public class ModelToBean {
	
	
	public static Area modelToBean( CrsArea areaJpa ){
		
		if( areaJpa == null){
			return null;
		}
			
		Area areaBean = new Area();
		areaBean.setIdArea(areaJpa.getIdArea());
		if( areaJpa.getDataInizio()!= null ){
			areaBean.setDataInizio(areaJpa.getDataInizio());
			areaBean.setDataInizioAsString(new SimpleDateFormat("dd/MM/yyyy").format(areaJpa.getDataInizio()));
		}
		
		if( areaJpa.getDataFine() != null ){
			areaBean.setDataFine(areaJpa.getDataFine());
			areaBean.setDataFineAsString(new SimpleDateFormat("dd/MM/yyyy").format(areaJpa.getDataFine()));
		}
	
		areaBean.setDescrizione(areaJpa.getDescrizione());
		areaBean.setStato(areaJpa.getStato());
				
		return areaBean;
	}
	
	public static Processo modelToBean( CrsProcesso processoJpa ){
		
		if( processoJpa == null){
			return null;
		}
			
		Processo processoBean = new Processo();
		processoBean.setArea(new Area());
		
		processoBean.setIdProcesso(processoJpa.getIdProcesso());
		if( processoJpa.getDataInizio()!= null ){
			processoBean.setDataInizio(processoJpa.getDataInizio());
			processoBean.setDataInizioAsString(new SimpleDateFormat("dd/MM/yyyy").format(processoJpa.getDataInizio()));
		}
		if( processoJpa.getDataFine() != null ){
			processoBean.setDataFine(processoJpa.getDataFine());
			processoBean.setDataFineAsString(new SimpleDateFormat("dd/MM/yyyy").format(processoJpa.getDataFine()));
		}
		processoBean.setDescrizione(processoJpa.getDescrizione());
		processoBean.setStato(processoJpa.getStato());
		processoBean.getArea().setIdArea(processoJpa.getIdArea());
		processoBean.setInput(processoJpa.getInput());
		processoBean.setOutput(processoJpa.getOutput());
		processoBean.setOrdinamento(processoJpa.getOrdinamento());
		processoBean.setOwner(processoJpa.getOwner());
		processoBean.setPubblicazione(processoJpa.getPubblicazione());
				
		return processoBean;
	}
	
	
	public static SottoProcesso modelToBean( CrsSottoprocesso sottoProcessoJpa ){
		
		if( sottoProcessoJpa == null){
			return null;
		}
			
		SottoProcesso sottoProcessoBean = new SottoProcesso();
		Processo processo = new Processo();
		
		sottoProcessoBean.setProcesso(processo);
		sottoProcessoBean.setIdSottoProcesso(sottoProcessoJpa.getIdSottoprocesso());
		sottoProcessoBean.getProcesso().setIdProcesso(sottoProcessoJpa.getIdProcesso());
		
		if( sottoProcessoJpa.getDataInizio()!= null ){
			sottoProcessoBean.setDataInizio(sottoProcessoJpa.getDataInizio());
			sottoProcessoBean.setDataInizioAsString(new SimpleDateFormat("dd/MM/yyyy").format(sottoProcessoJpa.getDataInizio()));
		}
		if( sottoProcessoJpa.getDataFine() != null ){
			sottoProcessoBean.setDataFine(sottoProcessoJpa.getDataFine());
			sottoProcessoBean.setDataFineAsString(new SimpleDateFormat("dd/MM/yyyy").format(sottoProcessoJpa.getDataFine()));
		}
		sottoProcessoBean.setDescrizione(sottoProcessoJpa.getDescrizione());
		sottoProcessoBean.setStato(sottoProcessoJpa.getStato());
		sottoProcessoBean.setInput(sottoProcessoJpa.getInput());
		sottoProcessoBean.setOutput(sottoProcessoJpa.getOutput());
		sottoProcessoBean.setUoCoinvolte(sottoProcessoJpa.getUoCoinvolte());
		sottoProcessoBean.setOrdinamento(sottoProcessoJpa.getOrdinamento());
				
		return sottoProcessoBean;
	}
	
	public static SottoProcesso modelToBean(Object[] objList){
		
		if( objList == null){
			return null;
		}
			
		SottoProcesso sottoProcessoBean = new SottoProcesso();
		Processo processo = new Processo();
		Area area = new Area();
		
		processo.setArea(area);
		sottoProcessoBean.setProcesso(processo);
		
		sottoProcessoBean.setIdSottoProcesso((Long)objList[0]);
		//sottoProcessoBean.getProcesso().setIdProcesso((Long)objList[1]);
		sottoProcessoBean.setOrdinamento((Integer)objList[1]);
		sottoProcessoBean.setDescrizione((String)objList[2]);
		sottoProcessoBean.getProcesso().getArea().setDescrizione((String)objList[3]);
		sottoProcessoBean.getProcesso().setDescrizione((String)objList[4]);
		sottoProcessoBean.setInput((String)objList[5]);
		sottoProcessoBean.setOutput((String)objList[6]);
		sottoProcessoBean.setUoCoinvolte((String)objList[7]);
		sottoProcessoBean.setStato((String)objList[8]);
		
		return sottoProcessoBean;
	}
	
	
	public static AttivitaComponente modelToAttivitaComponenteBean(Object[] objList){
		
		if( objList == null){
			return null;
		}
			
		AttivitaComponente attivitaComponenteBean = new AttivitaComponente();
		SottoProcesso sottoProcesso = new SottoProcesso();
		
		attivitaComponenteBean.setSottoProcesso(sottoProcesso);
		
		attivitaComponenteBean.setIdAttivitaComponente((Long)objList[0]);
		attivitaComponenteBean.setOrdinamento((Integer)objList[1]);
		attivitaComponenteBean.setDescrizione((String)objList[2]);
		attivitaComponenteBean.setDescrizioneArea((String)objList[3]);
		attivitaComponenteBean.setDescrizioneProcesso((String)objList[4]);
		attivitaComponenteBean.getSottoProcesso().setDescrizione((String)objList[5]);
		if( objList[6]!= null ){
			attivitaComponenteBean.setDataInizio((Date)objList[6]);
			attivitaComponenteBean.setDataInizioAsString(new SimpleDateFormat("dd/MM/yyyy").format((Date)objList[6]));
		}
		if( objList[7] != null ){
			attivitaComponenteBean.setDataFine((Date)objList[7]);
			attivitaComponenteBean.setDataFineAsString(new SimpleDateFormat("dd/MM/yyyy").format((Date)objList[7]));
		}
		
		attivitaComponenteBean.setVincolo((String)objList[8]);
		attivitaComponenteBean.setStato((String)objList[9]);
		
		return attivitaComponenteBean;
	}

	
	public static AttivitaComponente modelToAttivitaComponenteBean(CrsAttivitaComponente attivitaComponenteJpa){
		
		if( attivitaComponenteJpa == null){
			return null;
		}
			
		AttivitaComponente attivitaComponenteBean = new AttivitaComponente();
		SottoProcesso sottoProcesso = new SottoProcesso();
		
		attivitaComponenteBean.setSottoProcesso(sottoProcesso);
		attivitaComponenteBean.setIdAttivitaComponente(attivitaComponenteJpa.getIdAttivitaComponente());
		attivitaComponenteBean.getSottoProcesso().setIdSottoProcesso(attivitaComponenteJpa.getIdSottoprocesso());
		
		if( attivitaComponenteJpa.getDataInizio()!= null ){
			attivitaComponenteBean.setDataInizio(attivitaComponenteJpa.getDataInizio());
			attivitaComponenteBean.setDataInizioAsString(new SimpleDateFormat("dd/MM/yyyy").format(attivitaComponenteJpa.getDataInizio()));
		}
		if( attivitaComponenteJpa.getDataFine() != null ){
			attivitaComponenteBean.setDataFine(attivitaComponenteJpa.getDataFine());
			attivitaComponenteBean.setDataFineAsString(new SimpleDateFormat("dd/MM/yyyy").format(attivitaComponenteJpa.getDataFine()));
		}
		attivitaComponenteBean.setDescrizione(attivitaComponenteJpa.getDescrizione());
		attivitaComponenteBean.setStato(attivitaComponenteJpa.getStato());
		attivitaComponenteBean.setVincolo(attivitaComponenteJpa.getVincolo());
		attivitaComponenteBean.setOrdinamento(attivitaComponenteJpa.getOrdinamento());
				
		return attivitaComponenteBean;
	}
	
	
	public static AttivitaDettaglio modelToAttivitaDettaglioBean(Object[] objList){
		
		if( objList == null){
			return null;
		}
		
		AttivitaDettaglio attivitaDettaglioBean = new AttivitaDettaglio();
		AttivitaComponente attivitaComponenteBean = new AttivitaComponente();
		
		attivitaDettaglioBean.setAttivitaComponente(attivitaComponenteBean);
		
		attivitaDettaglioBean.setIdAttivitaDettaglio((Long)objList[0]);
		attivitaDettaglioBean.setOrdinamento((Integer)objList[1]);
		attivitaDettaglioBean.setDescrizione((String)objList[2]);
		attivitaDettaglioBean.setDescrizioneArea((String)objList[3]);
		attivitaDettaglioBean.setDescrizioneProcesso((String)objList[4]);
		attivitaDettaglioBean.setDescrizioneSottoProcesso((String)objList[5]);
		attivitaDettaglioBean.getAttivitaComponente().setDescrizione((String)objList[6]);
		if( objList[7]!= null ){
			attivitaDettaglioBean.setDataInizio((Date)objList[7]);
			attivitaDettaglioBean.setDataInizioAsString(new SimpleDateFormat("dd/MM/yyyy").format((Date)objList[7]));
		}
		if( objList[8] != null ){
			attivitaDettaglioBean.setDataFine((Date)objList[8]);
			attivitaDettaglioBean.setDataFineAsString(new SimpleDateFormat("dd/MM/yyyy").format((Date)objList[8]));
		}
		
		attivitaDettaglioBean.setStato((String)objList[9]);
		
		return attivitaDettaglioBean;
	}
	
	
	public static AttivitaDettaglio modelToAttivitaDettaglioBean(CrsAttivitaDettaglio attivitaDettaglioJpa){
		
		if( attivitaDettaglioJpa == null){
			return null;
		}
			
		AttivitaDettaglio attivitaDettaglioBean = new AttivitaDettaglio();
		AttivitaComponente attivitaComponente = new AttivitaComponente();
		
		attivitaDettaglioBean.setAttivitaComponente(attivitaComponente);
		attivitaDettaglioBean.setIdAttivitaDettaglio(attivitaDettaglioJpa.getIdAttivitaDettaglio());
		attivitaDettaglioBean.getAttivitaComponente().setIdAttivitaComponente(attivitaDettaglioJpa.getIdAttivitaComponente());
		
		if( attivitaDettaglioJpa.getDataInizio()!= null ){
			attivitaDettaglioBean.setDataInizio(attivitaDettaglioJpa.getDataInizio());
			attivitaDettaglioBean.setDataInizioAsString(new SimpleDateFormat("dd/MM/yyyy").format(attivitaDettaglioJpa.getDataInizio()));
		}
		if( attivitaDettaglioJpa.getDataFine() != null ){
			attivitaDettaglioBean.setDataFine(attivitaDettaglioJpa.getDataFine());
			attivitaDettaglioBean.setDataFineAsString(new SimpleDateFormat("dd/MM/yyyy").format(attivitaDettaglioJpa.getDataFine()));
		}
		attivitaDettaglioBean.setDescrizione(attivitaDettaglioJpa.getDescrizione());
		attivitaDettaglioBean.setStato(attivitaDettaglioJpa.getStato());
		attivitaDettaglioBean.setOrdinamento(attivitaDettaglioJpa.getOrdinamento());
				
		return attivitaDettaglioBean;
	}
	
	
	public static TipoNormativa modelToBean(CrsTipo tipoNormativaJpa){
		
		if( tipoNormativaJpa == null){
			return null;
		}
			
		TipoNormativa tipoNormativaBean = new TipoNormativa();
		
		tipoNormativaBean.setIdTipo(tipoNormativaJpa.getIdTipo());
		tipoNormativaBean.setIdClasse(tipoNormativaJpa.getIdClasse());
		tipoNormativaBean.setDescrizione(tipoNormativaJpa.getDescrizione());
		
		if( tipoNormativaJpa.getDataInizio()!= null ){
			tipoNormativaBean.setDataInizio(tipoNormativaJpa.getDataInizio());
		}
		if( tipoNormativaJpa.getDataFine() != null ){
			tipoNormativaBean.setDataFine(tipoNormativaJpa.getDataFine());
		}
		
				
		return tipoNormativaBean;
	}

	
	public static DatiTipoNormativa modelToDatiTipoNormativaBean(Object[] objList){
		
		DatiTipoNormativa bean = new DatiTipoNormativa();
		
		bean.setIdDatiTipo((Long)objList[0]);
		
		if( objList[1]!= null ){
			bean.setDate1((Date)objList[1]);
			bean.setDate1AsString(new SimpleDateFormat("dd/MM/yyyy").format((Date)objList[1]));
		}
		if( objList[2] != null ){
			bean.setDate2((Date)objList[2]);
			bean.setDate2AsString(new SimpleDateFormat("dd/MM/yyyy").format((Date)objList[2]));
		}
		
		bean.setText1((String)objList[3]);
		bean.setText2((String)objList[4]);
		bean.setText3((String)objList[5]);
		bean.setText4((String)objList[6]);
		bean.setText5((String)objList[7]);
		bean.setText6((String)objList[8]);
		bean.setText7((String)objList[9]);
		bean.setText8((String)objList[10]);
		bean.setText9((String)objList[11]);
		bean.setText10((String)objList[12]);
		
		if(objList[13] != null){
			bean.setNumber1((Double)objList[13]);
		}
		if(objList[14] != null){
			bean.setNumber2((Double)objList[14]);
		}
		if(objList[15] != null){
			bean.setNumber3((Double)objList[15]);
		}
		if(objList[16] != null){
			bean.setNumber4((Double)objList[16]);
		}
		if(objList[17] != null){
			bean.setNumber5((Double)objList[17]);
		}
		if(objList[18] != null){
			bean.setNumber6((Double)objList[18]);
		}
		if(objList[19] != null){
			bean.setNumber7((Double)objList[19]);
		}
		if(objList[20] != null){
			bean.setNumber8((Double)objList[20]);
		}
		if(objList[21] != null){
			bean.setNumber9((Double)objList[21]);
		}
		if(objList[22] != null){
			bean.setNumber10((Double)objList[22]);
		}
		
		return bean;
	}
	
	
	public static DatiTipoNormativa modelToBean(CrsDatiTipo datiTipoJpa){
		
		if( datiTipoJpa == null){
			return null;
		}
			
		DatiTipoNormativa bean = new DatiTipoNormativa();
		
		bean.setIdDatiTipo(datiTipoJpa.getIdDatiTipo());
		bean.setIdDescrizioneTipo(datiTipoJpa.getIdDescrizioneTipo());
		
		if( datiTipoJpa.getDate1()!= null ){
			bean.setDate1(datiTipoJpa.getDate1());
			bean.setDate1AsString(new SimpleDateFormat("dd/MM/yyyy").format(datiTipoJpa.getDate1()));
		}
		if( datiTipoJpa.getDate2() != null ){
			bean.setDate2(datiTipoJpa.getDate2());
			bean.setDate2AsString(new SimpleDateFormat("dd/MM/yyyy").format(datiTipoJpa.getDate2()));
		}
		
		bean.setText1(datiTipoJpa.getText1());
		bean.setText2(datiTipoJpa.getText2());
		bean.setText3(datiTipoJpa.getText3());
		bean.setText4(datiTipoJpa.getText4());
		bean.setText5(datiTipoJpa.getText5());
		bean.setText6(datiTipoJpa.getText6());
		bean.setText7(datiTipoJpa.getText7());
		bean.setText8(datiTipoJpa.getText8());
		bean.setText9(datiTipoJpa.getText9());
		bean.setText10(datiTipoJpa.getText10());
		
		if(datiTipoJpa.getNumber1() != null){
			bean.setNumber1(datiTipoJpa.getNumber1());
		}
		if(datiTipoJpa.getNumber2() != null){
			bean.setNumber2(datiTipoJpa.getNumber2());
		}
		if(datiTipoJpa.getNumber3() != null){
			bean.setNumber3(datiTipoJpa.getNumber3());
		}
		if(datiTipoJpa.getNumber4() != null){
			bean.setNumber4(datiTipoJpa.getNumber4());
		}
		if(datiTipoJpa.getNumber5() != null){
			bean.setNumber5(datiTipoJpa.getNumber5());
		}
		if(datiTipoJpa.getNumber6() != null){
			bean.setNumber6(datiTipoJpa.getNumber6());
		}
		if(datiTipoJpa.getNumber7() != null){
			bean.setNumber7(datiTipoJpa.getNumber7());
		}
		if(datiTipoJpa.getNumber8() != null){
			bean.setNumber8(datiTipoJpa.getNumber8());
		}
		if(datiTipoJpa.getNumber9() != null){
			bean.setNumber9(datiTipoJpa.getNumber9());
		}
		if(datiTipoJpa.getNumber10() != null){
			bean.setNumber10(datiTipoJpa.getNumber10());
		}
		
		return bean;
	}
	
	public static PraticheAtpoDto modelToPraticheAtpoDto(Object[] o){
		int i=0;
	
		PraticheAtpoDto p= new PraticheAtpoDto();
		if(o[i]!=null){
			p.setNomeSede((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setFascicolo((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setRichiesta((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setEsito((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setAutotutela((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setParte((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setNotifica((Date) o[i]);
		}i++;
		if(o[i]!=null){
			p.setApertura((Date) o[i]);
		}i++;
		if(o[i]!=null){
			p.setCostituzione((Date) o[i]);
		}i++;
		if(o[i]!=null){
			p.setUdienza((Date) o[i]);
		}i++;
		if(o[i]!=null){
			p.setParereMedico((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setDissenso((Date) o[i]);
		}i++;
		if(o[i]!=null){
			p.setCom((Date) o[i]);
		}i++;
		if(o[i]!=null){
			p.setCar((Date) o[i]);
		}i++;
		if(o[i]!=null){
			p.setEsec((Date) o[i]);
		}i++;
		if(o[i]!=null){
			p.setPrRic((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setPrBzz((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setPrDef((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setRg((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setVisitaPeritale((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setOsservazioniSanitarie((String) o[i]);
		}i++;
		if(o[i]!=null){
			p.setProvv((Date) o[i]);
		}i++;
		if(o[i]!=null){
			p.setNotProvv((Date) o[i]);
		}i++;
		if(o[i]!=null){
			p.setDissensoINPS((Date) o[i]);
		}i++;
		if(o[i]!=null){
			p.setStatoEsamePratica((String) o[i]);
		}i++;
		/*if(o[i]!=null){
			p.setDSeiMuno((String) o[i]);
		}i++;*/
		
		return p;
	}

	
	public static Processo modelToProcessoBean(Object[] objList){
		
		if( objList == null){
			return null;
		}
			
		Processo processo = new Processo();
		Area area = new Area();
		
		processo.setIdProcesso((Long)objList[0]);
		area.setIdArea((Long)objList[1]);
		processo.setDescrizione((String)objList[2]);
		processo.setDataInizio((Date) objList[3]);
		processo.setDataFine((Date) objList[4]);
		processo.setStato((String)objList[5]);
		processo.setInput((String)objList[6]);
		processo.setOutput((String)objList[7]);
		processo.setOrdinamento((Integer)objList[8]);
		
		
		processo.setArea(area);
		
		return processo;
	}
	
	public static SottoProcesso modelToSottoProcessoBean(Object[] objList){
		
		if( objList == null){
			return null;
		}
			
		SottoProcesso sottoProcessoBean = new SottoProcesso();
		Processo processo = new Processo();
		
		sottoProcessoBean.setProcesso(processo);
		
		sottoProcessoBean.setIdSottoProcesso((Long)objList[0]);
		sottoProcessoBean.getProcesso().setIdProcesso((Long)objList[1]);
		sottoProcessoBean.setDescrizione((String)objList[2]);
		sottoProcessoBean.setDataInizio((Date)objList[3]);
		sottoProcessoBean.setDataFine((Date)objList[4]);
		sottoProcessoBean.setInput((String)objList[5]);
		sottoProcessoBean.setOutput((String)objList[6]);
		sottoProcessoBean.setUoCoinvolte((String)objList[7]);
		sottoProcessoBean.setStato((String)objList[8]);
		sottoProcessoBean.setOrdinamento((Integer)objList[9]);
		
		return sottoProcessoBean;
	}
	
	public static AttivitaComponente modelToAttivitaComponenteBeanByIdArea(Object[] objList){
		
		if( objList == null){
			return null;
		}
			
		AttivitaComponente attivitaComponenteBean = new AttivitaComponente();
		SottoProcesso sottoProcesso = new SottoProcesso();
		
		attivitaComponenteBean.setSottoProcesso(sottoProcesso);
		
		attivitaComponenteBean.setIdAttivitaComponente((Long)objList[0]);
		attivitaComponenteBean.getSottoProcesso().setIdSottoProcesso((Long)objList[1]);
		attivitaComponenteBean.setDescrizione((String)objList[2]);
		if( objList[3]!= null ){
			attivitaComponenteBean.setDataInizio((Date)objList[3]);
		}
		if( objList[4] != null ){
			attivitaComponenteBean.setDataFine((Date)objList[4]);
		}
		attivitaComponenteBean.setVincolo((String)objList[5]);
		attivitaComponenteBean.setStato((String)objList[6]);
		attivitaComponenteBean.setOrdinamento((Integer)objList[7]);
		
		return attivitaComponenteBean;
	}
	
	
	public static AttivitaDettaglio modelToAttivitaDettaglioBeanByIdArea(Object[] objList){
		
		if( objList == null){
			return null;
		}
			
		AttivitaDettaglio attivitaDettaglioBean = new AttivitaDettaglio();
		AttivitaComponente attivitaComponente = new AttivitaComponente();
		
		attivitaDettaglioBean.setAttivitaComponente(attivitaComponente);
		
		attivitaDettaglioBean.setIdAttivitaDettaglio((Long)objList[0]);
		attivitaDettaglioBean.getAttivitaComponente().setIdAttivitaComponente((Long)objList[1]);
		attivitaDettaglioBean.setDescrizione((String)objList[2]);
		if( objList[3]!= null ){
			attivitaDettaglioBean.setDataInizio((Date)objList[3]);
		}
		if( objList[4] != null ){
			attivitaDettaglioBean.setDataFine((Date)objList[4]);
		}
		attivitaDettaglioBean.setStato((String)objList[5]);
		attivitaDettaglioBean.setOrdinamento((Integer)objList[6]);
		
		return attivitaDettaglioBean;
	}


	public static CircolariInps modelToCircolariInpsBean(Object[] objList){
		
		CircolariInps bean = new CircolariInps();
		
		bean.setIdCircolariInps((Long)objList[0]);
		
		bean.setDescSintetica((String)objList[1]);
		bean.setDescDettaglio((String)objList[2]);
		
		if( objList[3] != null ){
			bean.setDataEmissione((Date)objList[3]);
			bean.setDataEmissioneAsString(new SimpleDateFormat("dd/MM/yyyy").format((Date)objList[3]));
		}
		
		bean.setCodice((String)objList[4]);
		bean.setOggetto((String)objList[5]);
		bean.setDirezioneEmittente1((String)objList[6]);
		bean.setDirezioneEmittente2((String)objList[7]);
		bean.setDirezioneEmittente3((String)objList[8]);
		bean.setDirezioneEmittente4((String)objList[9]);
		bean.setDirezioneEmittente5((String)objList[10]);
		bean.setDirezioneEmittente6((String)objList[11]);
		
		bean.setSommario((String)objList[12]);
		
		if(objList[13] != null){
			bean.setAnno((Integer)objList[13]);
		}
		
		
		return bean;
	}
	
	public static NoteDecreti modelToNoteDecretiBean(Object[] objList){
		
		NoteDecreti bean = new NoteDecreti();
		
		bean.setIdNoteDecreti((Long)objList[0]);
		bean.setDescSintetica((String)objList[1]);
		bean.setDescDettaglio((String)objList[2]);
		
		if( objList[3] != null ){
			bean.setDataEmissione((Date)objList[3]);
			bean.setDataEmissioneAsString(new SimpleDateFormat("dd/MM/yyyy").format((Date)objList[3]));
		}
		
		bean.setCodice((String)objList[4]);
		bean.setOggetto((String)objList[5]);
		bean.setRiferimenti((String)objList[6]);
		
		return bean;
	}
	
	public static MessaggiInps modelToMessaggiInpsBean(Object[] objList){
		
		MessaggiInps bean = new MessaggiInps();
		
		bean.setIdMessaggiInps((Long)objList[0]);
		
		bean.setDescSintetica((String)objList[1]);
		bean.setDescDettaglio((String)objList[2]);
		
		if( objList[3] != null ){
			bean.setDataEmissione((Date)objList[3]);
			bean.setDataEmissioneAsString(new SimpleDateFormat("dd/MM/yyyy").format((Date)objList[3]));
		}
		
		bean.setCodice((String)objList[4]);
		bean.setOggetto((String)objList[5]);
		
		return bean;
	}
	
	
	public static LeggiDecreti modelToLeggiDecretiBean(Object[] objList){
		
		LeggiDecreti bean = new LeggiDecreti();
		
		bean.setIdLeggiDecreti((Long)objList[0]);
		
		bean.setDescSintetica((String)objList[1]);
		bean.setDescDettaglio((String)objList[2]);
		
		if( objList[3] != null ){
			bean.setDataEmissione((Date)objList[3]);
			bean.setDataEmissioneAsString(new SimpleDateFormat("dd/MM/yyyy").format((Date)objList[3]));
		}
		
		bean.setCodice((String)objList[4]);
		bean.setOggetto((String)objList[5]);
		
		bean.setArticolo((String)objList[6]);
		bean.setAnnoGui((Integer)objList[7]);
		bean.setNumeroGui((String)objList[8]);
		
		return bean;
	}
}
