package it.tecnet.crs.util;


import it.tecnet.crs.componenti.jpa.model.CrsDatiTipo;
import it.tecnet.crs.componenti.web.bean.DatiTipoNormativa;
import it.tecnet.crs.jpa.model.CrsAssAcClasse;
import it.tecnet.crs.jpa.model.CrsAssAdClasse;
import it.tecnet.crs.jpa.model.CrsAssProcessoClasse;
import it.tecnet.crs.jpa.model.CrsAssSottoprocessoClasse;
import it.tecnet.crs.mod.jpa.model.CrsArea;
import it.tecnet.crs.mod.jpa.model.CrsAttivitaComponente;
import it.tecnet.crs.mod.jpa.model.CrsAttivitaDettaglio;
import it.tecnet.crs.mod.jpa.model.CrsProcesso;
import it.tecnet.crs.mod.jpa.model.CrsSottoprocesso;
import it.tecnet.crs.mod.web.bean.Area;
import it.tecnet.crs.mod.web.bean.AttivitaComponente;
import it.tecnet.crs.mod.web.bean.AttivitaDettaglio;
import it.tecnet.crs.mod.web.bean.Processo;
import it.tecnet.crs.mod.web.bean.SottoProcesso;
import it.tecnet.crs.mod.web.dto.ProcessoDto;
import it.tecnet.crs.web.beans.AssAcClasse;
import it.tecnet.crs.web.beans.AssAdClasse;
import it.tecnet.crs.web.beans.AssProcessoClasse;
import it.tecnet.crs.web.beans.AssSottoProcessoClasse;

import org.apache.commons.lang3.StringUtils;

public class BeanToModel {
	
	public static CrsArea beanToModel( Area area ){
		if( area == null )
			return null;
		
		CrsArea areaModel = new CrsArea();
		areaModel.setDataInizio( area.getDataInizio());
		areaModel.setDataFine(area.getDataFine());
		areaModel.setDescrizione( StringUtils.trim( area.getDescrizione() ) );
			
		if( area.getIdArea()>0 )
			areaModel.setIdArea(Long.valueOf(area.getIdArea()));
		
		areaModel.setStato(area.getStato());
		
		return areaModel;
	}
	
	
	public static CrsProcesso beanToModel(Processo processo){
		if( processo == null )
			return null;
		
		CrsProcesso processoModel = new CrsProcesso();
		
		if( processo.getIdProcesso() > 0 ){
			processoModel.setIdProcesso(Long.valueOf(processo.getIdProcesso()));
		}
		if( processo.getArea().getIdArea() > 0 ){
			processoModel.setIdArea(Long.valueOf(processo.getArea().getIdArea()));
		}
		processoModel.setDescrizione(StringUtils.trim(processo.getDescrizione()));
		processoModel.setDataInizio(processo.getDataInizio());
		processoModel.setDataFine(processo.getDataFine());
		
		processoModel.setStato(processo.getStato());
		processoModel.setInput(processo.getInput());
		processoModel.setOutput(processo.getOutput());
		processoModel.setOrdinamento(processo.getOrdinamento());
		processoModel.setOwner(processo.getOwner());
		if(StringUtils.isEmpty(processo.getPubblicazione())){
			processoModel.setPubblicazione("N");
		}
		
		return processoModel;
	}
	
	public static CrsSottoprocesso beanToModel(SottoProcesso sottoProcesso){
		
		if( sottoProcesso == null )
			return null;
		
		CrsSottoprocesso model = new CrsSottoprocesso();
		
		if( sottoProcesso.getIdSottoProcesso() > 0 ){
			model.setIdSottoprocesso(Long.valueOf(sottoProcesso.getIdSottoProcesso()));
		}
		if( sottoProcesso.getProcesso().getIdProcesso() > 0 ){
			model.setIdProcesso(Long.valueOf(sottoProcesso.getProcesso().getIdProcesso()));
		}
		model.setDescrizione(StringUtils.trim(sottoProcesso.getDescrizione()));
		model.setDataInizio(sottoProcesso.getDataInizio());
		model.setDataFine(sottoProcesso.getDataFine());
		
		model.setStato(sottoProcesso.getStato());
		model.setInput(sottoProcesso.getInput());
		model.setOutput(sottoProcesso.getOutput());
		model.setOrdinamento(sottoProcesso.getOrdinamento());
		
		return model;
	}
	
	public static CrsAttivitaComponente beanToModel(AttivitaComponente attivitaComponente){
		
		if( attivitaComponente == null )
			return null;
		
		CrsAttivitaComponente model = new CrsAttivitaComponente();
		
		if( attivitaComponente.getIdAttivitaComponente() > 0 ){
			model.setIdAttivitaComponente(Long.valueOf(attivitaComponente.getIdAttivitaComponente()));
		}
		if( attivitaComponente.getSottoProcesso().getIdSottoProcesso() > 0 ){
			model.setIdSottoprocesso(Long.valueOf(attivitaComponente.getSottoProcesso().getIdSottoProcesso()));
		}
		model.setDescrizione(StringUtils.trim(attivitaComponente.getDescrizione()));
		model.setDataInizio(attivitaComponente.getDataInizio());
		model.setDataFine(attivitaComponente.getDataFine());
		
		model.setStato(attivitaComponente.getStato());
		model.setVincolo(attivitaComponente.getVincolo());
		model.setOrdinamento(attivitaComponente.getOrdinamento());
		
		return model;
	}
	
	public static CrsAttivitaDettaglio beanToModel(AttivitaDettaglio attivitaDettaglio){
		
		if( attivitaDettaglio == null )
			return null;
		
		CrsAttivitaDettaglio model = new CrsAttivitaDettaglio();
		
		if( attivitaDettaglio.getIdAttivitaDettaglio() > 0 ){
			model.setIdAttivitaDettaglio(Long.valueOf(attivitaDettaglio.getIdAttivitaDettaglio()));
		}
		if( attivitaDettaglio.getAttivitaComponente().getIdAttivitaComponente() > 0 ){
			model.setIdAttivitaComponente(Long.valueOf(attivitaDettaglio.getAttivitaComponente().getIdAttivitaComponente()));
		}
		model.setDescrizione(StringUtils.trim(attivitaDettaglio.getDescrizione()));
		model.setDataInizio(attivitaDettaglio.getDataInizio());
		model.setDataFine(attivitaDettaglio.getDataFine());
		
		model.setStato(attivitaDettaglio.getStato());
		model.setOrdinamento(attivitaDettaglio.getOrdinamento());
		
		return model;
	}
	
	
	public static CrsDatiTipo beanToModel(DatiTipoNormativa normativa){
		
		if(normativa == null){
			return null;
		}
		
		CrsDatiTipo model = new CrsDatiTipo();
		
		if(normativa.getIdDatiTipo() > 0){
			model.setIdDatiTipo(Long.valueOf(normativa.getIdDatiTipo()));
		}
		
		if(normativa.getIdDescrizioneTipo() > 0){
			model.setIdDescrizioneTipo(Long.valueOf(normativa.getIdDescrizioneTipo()));
		}
		
		if(normativa.getDate1() != null){
			model.setDate1(normativa.getDate1());
		}
		
		if(normativa.getDate2() != null){
			model.setDate2(normativa.getDate2());
		}
		
		model.setText1(normativa.getText1());
		model.setText2(normativa.getText2());
		model.setText3(normativa.getText3());
		model.setText4(normativa.getText4());
		model.setText5(normativa.getText5());
		model.setText6(normativa.getText6());
		model.setText7(normativa.getText7());
		model.setText8(normativa.getText8());
		model.setText9(normativa.getText9());
		model.setText10(normativa.getText10());
		
		if(normativa.getNumber1() != null){
			model.setNumber1(Double.valueOf(normativa.getNumber1()));
		}
		if(normativa.getNumber2() != null){
			model.setNumber2(Double.valueOf(normativa.getNumber2()));
		}
		if(normativa.getNumber3() != null){
			model.setNumber3(Double.valueOf(normativa.getNumber3()));
		}
		if(normativa.getNumber4() != null){
			model.setNumber4(Double.valueOf(normativa.getNumber4()));
		}
		if(normativa.getNumber5() != null){
			model.setNumber5(Double.valueOf(normativa.getNumber5()));
		}
		if(normativa.getNumber6() != null){
			model.setNumber6(Double.valueOf(normativa.getNumber6()));
		}
		if(normativa.getNumber7() != null){
			model.setNumber7(Double.valueOf(normativa.getNumber7()));
		}
		if(normativa.getNumber8() != null){
			model.setNumber8(Double.valueOf(normativa.getNumber8()));
		}
		if(normativa.getNumber9() != null){
			model.setNumber9(Double.valueOf(normativa.getNumber9()));
		}
		if(normativa.getNumber10() != null){
			model.setNumber10(Double.valueOf(normativa.getNumber10()));
		}
		
		return model;
		
	}
	
	
	public static CrsAssProcessoClasse beanToModel(AssProcessoClasse bean){
		
		if( bean == null )
			return null;
		
		CrsAssProcessoClasse model = new CrsAssProcessoClasse();
		
		if( bean.getIdProcesso() > 0 ){
			model.setIdProcesso(Long.valueOf(bean.getIdProcesso()));
		}
		if( bean.getIdNormativa() > 0 ){
			model.setIdNormativa(Long.valueOf(bean.getIdNormativa()));
		}
		if( bean.getIdTipo() > 0 ){
			model.setIdTipo(Long.valueOf(bean.getIdTipo()));
		}
		
		return model;
	}
	
	
	public static CrsAssSottoprocessoClasse beanToModel(AssSottoProcessoClasse bean){
		
		if( bean == null )
			return null;
		
		CrsAssSottoprocessoClasse model = new CrsAssSottoprocessoClasse();
		
		if( bean.getIdSottoProcesso() > 0 ){
			model.setIdSottoprocesso(Long.valueOf(bean.getIdSottoProcesso()));
		}
		if( bean.getIdNormativa() > 0 ){
			model.setIdNormativa(Long.valueOf(bean.getIdNormativa()));
		}
		if( bean.getIdTipo() > 0 ){
			model.setIdTipo(Long.valueOf(bean.getIdTipo()));
		}
		
		return model;
	}
	
	public static CrsAssAcClasse beanToModel(AssAcClasse bean){
		
		if( bean == null )
			return null;
		
		CrsAssAcClasse model = new CrsAssAcClasse();
		
		if( bean.getIdAttivitaComponente() > 0 ){
			model.setIdAttivitaComponente(Long.valueOf(bean.getIdAttivitaComponente()));
		}
		if( bean.getIdNormativa() > 0 ){
			model.setIdNormativa(Long.valueOf(bean.getIdNormativa()));
		}
		if( bean.getIdTipo() > 0 ){
			model.setIdTipo(Long.valueOf(bean.getIdTipo()));
		}
		
		return model;
	}
	
	public static CrsAssAdClasse beanToModel(AssAdClasse bean){
		
		if( bean == null )
			return null;
		
		CrsAssAdClasse model = new CrsAssAdClasse();
		
		if( bean.getIdAttivitaDettaglio() > 0 ){
			model.setIdAttivitaDettaglio(Long.valueOf(bean.getIdAttivitaDettaglio()));
		}
		if( bean.getIdNormativa() > 0 ){
			model.setIdNormativa(Long.valueOf(bean.getIdNormativa()));
		}
		if( bean.getIdTipo() > 0 ){
			model.setIdTipo(Long.valueOf(bean.getIdTipo()));
		}
		
		return model;
	}

}
