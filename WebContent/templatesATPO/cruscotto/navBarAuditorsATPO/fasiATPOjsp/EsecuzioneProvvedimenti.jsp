<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.session.DatiUtente"%>


	<s:set var="statoEsamePratica" value="%{statoEsamePratica.trim()}" />
	<s:set var="praticaModificabile" value="%{praticaModificable}" />
	
	<s:if test='%{#praticaModificabile == "V"}'>
		<s:if test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_APERTO}'>
			<s:set var="salvataggioAbilitato" value="%{''}" />
		</s:if>
		<s:elseif test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_IN_LAVORAZIONE}'>
		    <s:set var="salvataggioAbilitato" value="%{''}" />
		</s:elseif>
		<s:elseif test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_CHIUSO}'>
		    <s:set var="salvataggioAbilitato" value="%{'disabled'}" />
		</s:elseif>
		<s:elseif test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_ELABORAZIONE}'>
	   		<s:set var="salvataggioAbilitato" value="%{'disabled'}" />
		</s:elseif>
	</s:if>
	<s:else>
			<s:set var="salvataggioAbilitato" value="%{'disabled'}" />
	   		<s:set var="CalcolaIndicatoriPraticaButton" value="%{'disabled'}" />
	</s:else>

<script>	
	/*
	if( $('#impSpeseCtuPagate').val() != ""  && $('#impSpeseCtuPagate').val() > 0 ){
		setObbligatorieta('iImpSpeseCtuDovute' , true );
    }else{
    	setObbligatorieta('iImpSpeseCtuDovute' , false );
    } 
    */
    impSpeseCtuPagateOnKeyUp();
    impSpeseMensileRataEuroOnKeyUp();
</script>
<!-- Esecuzione provvedimenti -->
<div class="portlet-body form">
   <form role="form">
      <div class="form-body">
      <s:set var="dataNotificaDecretoOmologa" value="%{dataNotificaDecretoOmologa}" />
      <input type="hidden" id="dataNotifDecrO" value="<s:date name="dataNotificaDecretoOmologa" format="dd/MM/yyyy" />">
      
       <s:set var="dataDepositoDecretoOmologa" value="%{dataDepositoDecretoOmologa}" />
      <input type="hidden" id="dataDepDecrO" value="<s:date name="dataDepositoDecretoOmologa" format="dd/MM/yyyy" />">
      
      <s:set var="dataTrasmissioneLps" value="%{dataTrasmissioneLps}" />
      <input type="hidden" id="dataTrasmisLps" value="<s:date name="dataTrasmissioneLps" format="dd/MM/yyyy" />">
      
            <div class="row" style="margin-bottom: 40px;">
            	
            	<div class="col-md-3 col-xs-12">
              		<div class="form-group" style="margin-bottom: 0;">
                  		<label for="form_control_2" style="font-size: 13px; color: #888888;">Presenza decreto di omologa nel fascicolo<i class="fa fa-asterisk obbligatorio"></i></label><br>
                  	<s:set var="presDecrOmgFasc" value="%{esecuzioneProvvedimenti.presDecrOmgFasc}" />
                  	<s:set var="codPresDecrOmgFasc" value="%{codPresDecrOmgFasc}" />
                  
                  <select id="presDecrOmgF" class="form-control select2me">
                   	<option selected value="<s:property value="#codPresDecrOmgFasc" />"><s:property value="presDecrOmgFasc"/></option>
            			<s:iterator value="optionsSiNoEsProvv" var="listoptSiNoEsProvv">
                    			<option id="<s:property value="#listoptSiNoEsProvv.idTplTopologiche" />" value="<s:property value="#listoptSiNoEsProvv.codifica" />">
                        			<s:property value="#listoptSiNoEsProvv.descrizione" />
                   				</option>
                			
            		</s:iterator>
                  </select>
               </div>
            </div>
            
            
            <div class="col-md-3 col-xs-12" style="margin-top: -5px;">
             <s:set var="dataDecrLiqCtu" value="%{esecuzioneProvvedimenti.dataDecrLiqCtu}" />
            	<span class="help-block">Data decreto liquidazione CTU </span>
            	<input id="dataDecrLiqCtu"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" value="<s:date name="dataDecrLiqCtu" format="dd/MM/yyyy" />">
			</div>
            
            
           
            
            <div class="col-md-3 col-xs-12">
            <s:set var="recDatiLiq" value="%{esecuzioneProvvedimenti.recDatiLiq}" />
               <span class="help-block" style="margin-top: 0;">Data registrazione dati liquidazione </span>
                  <input id="recDatiLiq" onchange="setIntTempDepDecrRecLiq('false')" type="text" size="16"  class="form-control form-control-inline input-medium date-picker"  value="<s:date name="recDatiLiq" format="dd/MM/yyyy" />"  >
            </div>
            
            <div class="col-md-3 col-xs-12">
               <div class="form-group form-md-line-input">
               <s:set var="ggNotifDecrOmgLiqPres" value="%{esecuzioneProvvedimenti.intDepDecrOmgDRecLiquid}" />
                  <input type="text" class="form-control" id="ggNotifDecrOmgLiqP" value="<s:property value="ggNotifDecrOmgLiqPres"/>" style="height: 38px; text-align:right;" readonly>
                  <label for="form_control_1" style="margin-top: -18px;">
                    Intervallo da Data deposito del Decreto di OMOLOGA a data registrazione dati liquidazione <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                  </label>
               </div>
            </div>
            
            
         </div>
         
         
         <div class="row" style="margin-bottom: 40px;">
         
         	<div class="col-md-3 col-xs-12" style="margin-top: -5px;">
         	<s:set var="liqPrestLps" value="%{esecuzioneProvvedimenti.dataLiqPrestLps}" />
               <span class="help-block">Data liquidazione prestazione da LPS </span>
               <input  id="liqPrestLps" type="text" size="16" class="form-control form-control-inline input-medium date-picker" value="<s:date name="liqPrestLps" format="dd/MM/yyyy" />"  onchange="calcolaIntTempTrasmLpsOmologa('false')">
            </div>
            
            <input  id="dataAccesso" type="hidden" value="<s:date name="dataAccesso" format="dd/MM/yyyy" />" >
         
            <div class="col-md-3 col-xs-12">
               <div class="form-group form-md-line-input">
                 <s:set var="importoRataMensile" value="%{esecuzioneProvvedimenti.importoRataMensile}" />
                  <input type="text" class="form-control" id="importoRataMensile"  value="<s:property value="importoRataMensile"/>" style="height: 38px; text-align:right;" onkeyup="impSpeseMensileRataEuroOnKeyUp();">
                  <label for="form_control_1">
                     Importo mensile rata Euro
                  </label>
               </div>
            </div>

            
             <div class="col-md-3 col-xs-12">
               <span class="help-block" style="margin-top: -18px;">Data presa in carico Decreto di<br>omologa da LPS </span>
                  <s:set var="dataPresaInCaricoDecrOmgLps" value="%{esecuzioneProvvedimenti.dataPresaInCaricoDecrOmgLps}" />
                  <input type="text" id="dataPresaInCaricoDecrOmgLps" class="form-control form-control-inline input-medium date-picker" value="<s:date name="dataPresaInCaricoDecrOmgLps" format="dd/MM/yyyy" />"  onchange="calcolaIntTempNotifDecrALiqPres('false')">
            </div>
            
            <div class="col-md-3 col-xs-12">
               <div class="form-group form-md-line-input">
            
               <s:set var="importoRataDovuta" value="%{esecuzioneProvvedimenti.importoRataDovuta}" />
                  <input type="text" class="form-control" id="importoRataDovuta" value="<s:property value="importoRataDovuta"/>" style="height: 38px; text-align:right;">
                  <label for="impRataDovuta">
                     Importo rata dovuta <i id="iImpRataDovuta" class="fa fa-asterisk"></i>
                  </label>
               </div>
            </div>
            
            
         </div>
         
         
         <div class="row" style="margin-bottom: 40px;">
         	 
            
            <div class="col-md-3 col-xs-12">
               <div class="form-group form-md-line-input">
               <s:set var="ggNotifDecrOmgLiqPres" value="%{esecuzioneProvvedimenti.ggNotifDecrOmgLiqPres}" />
                  <input type="text" class="form-control" id="ggNotifDecrOmgLiqPres" value="<s:property value="ggNotifDecrOmgLiqPres"/>" style="height: 38px; text-align:right;" readonly>
                  <label for="form_control_1" style="margin-top: -18px;">
                     gg da notifica Decreto di omologa<br>a liquidazione prestazione <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                  </label>
               </div>
            </div>
            
            <div class="col-md-3 col-xs-12">
               <div class="form-group form-md-line-input">
                <s:set var="ggTrasmDecrLpsDecrOmglps" value="%{esecuzioneProvvedimenti.ggTrasmDecrLpsDecrOmglps}" />
                  <input type="text" class="form-control" id="ggTrasmDecrLpsDecrOmglps" value="<s:property value="ggTrasmDecrLpsDecrOmglps"/>" style="height: 38px; text-align:right;" readonly>
                  <label for="form_control_1" style="margin-top: -17px; font-size: 12px;">
                     gg da trasm. del Decreto alla LPS a presa in carico Decreto di OMOLOGA da LPS <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                  </label>
               </div>
            </div>
            
            <div class="col-md-3 col-xs-12">
               <div class="form-group form-md-line-input">
               <s:set var="interessiLegaliPagati" value="%{esecuzioneProvvedimenti.interessiLegaliPagati}" />
                  <input type="text" class="form-control" id="interessiLegaliPagati" value="<s:property value="interessiLegaliPagati"/>" style="height: 38px; text-align:right;">
                  <label for="form_control_1">
                     Interessi legali pagati <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                  </label>
               </div>
            </div>
             <div class="col-md-3 col-xs-12">
               <div class="form-group form-md-line-input">
                <s:set var="interessiLegaliDovuti" value="%{esecuzioneProvvedimenti.interessiLegaliDovuti}" />
                  <input type="text" class="form-control" id="interessiLegaliDovuti" value="<s:property value="interessiLegaliDovuti"/>" style="height: 38px; text-align:right;">
                  <label for="form_control_1">
                     Interessi legali dovuti 
                  </label>
               </div>
            </div>
            
         </div>
         
         
         
         <div class="row" style="margin-bottom: 40px;">
            
           
            
            <div class="col-md-3 col-xs-12">
               <button type="button" class="btn blue-madison" style="margin-top: 23px;">Calcola interessi legali</button>
            </div>
            
            <div class="col-md-3 col-xs-12">
               <span class="help-block" style="margin-top: -18px;"> Data decorrenza per calcolo interessi<br>legali </span>
                  <s:set var="dataDecCalcoloIntLegali" value="%{esecuzioneProvvedimenti.dataDecCalcoloIntLegali}" />
                  <input type="text" class="form-control form-control-inline input-medium date-picker" id="dataDecCalcoloIntLegali" value="<s:date name="dataDecCalcoloIntLegali" format="dd/MM/yyyy" />" >
            </div>
            
            <div class="col-md-3 col-xs-12">
               <span class="help-block" style="margin-top: 0;"> Data decorrenza prestazione inserita </span>
              
                  <s:set var="dataDecPrestInserita" value="%{esecuzioneProvvedimenti.dataDecPrestInserita}" />
                  <input type="text" id="dataDecPrestInserita" class="form-control form-control-inline input-medium date-picker" value="<s:date name="dataDecPrestInserita" format="dd/MM/yyyy" />" >
            </div>
             <div class="col-md-3 col-xs-12">
               <span class="help-block" style="margin-top: 0;">Data decorrenza prestazione </span>
              
                  <s:set var="dataCorrDecPrestazione" value="%{esecuzioneProvvedimenti.dataCorrDecPrestazione}" />
                  <input type="text" class="form-control form-control-inline input-medium date-picker" id="dataCorrDecPrestazione" value="<s:date name="dataCorrDecPrestazione" format="dd/MM/yyyy" />" >
            </div>
            
         </div>
         
         <div class="row" style="margin-bottom: 40px;">
            
           
            <div class="col-md-3 col-xs-12">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="form_control_2" style="font-size: 13px; color: #888888;">Prestazione corrispondente al decreto di omologa<i class="fa fa-asterisk obbligatorio"></i></label><br>
                 
                  <s:set var="prestCorrisp" value="%{esecuzioneProvvedimenti.prestCorrisp}" />
                  <s:set var="codPrestCorrisp" value="%{codPrestCorrisp}" />
                  <select id="prestCorrisp" class="form-control select2me">
                   	<option selected value="<s:property value="#codPrestCorrisp" />"><s:property value="prestCorrisp"/></option>
            			<s:iterator value="optionsSiNoEsProvv" var="listoptSiNoPrestC">
                    			<option id="<s:property value="#listoptSiNoPrestC.idTplTopologiche" />" value="<s:property value="#listoptSiNoPrestC.codifica" />">
                        			<s:property value="#listoptSiNoPrestC.descrizione" />
                   				</option>
            		</s:iterator>
                  </select>
               </div>
            </div>
            
            
            
            <div class="col-md-3 col-xs-12">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="form_control_2" style="font-size: 13px; color: #888888;">Condanna a pagamento CTU ATPO<i class="fa fa-asterisk obbligatorio"></i></label><br>
                 <s:set var="condannaPagCtuAtpo" value="%{esecuzioneProvvedimenti.condannaPagCtuAtpo}" />
                 <s:set var="codCondannaPagCtuAtpo" value="%{codCondannaPagCtuAtpo}" />
                  
                  <select id="condannaPagCtuAtpo" class="form-control select2me">
                   	<option selected value="<s:property value="#codCondannaPagCtuAtpo" />"><s:property value="condannaPagCtuAtpo"/></option>
            			<s:iterator value="optionsSiNoEsProvv" var="listoptSiNoCondP">
                			
                    			<option id="<s:property value="#listoptSiNoCondP.idTplTopologiche" />" value="<s:property value="#listoptSiNoCondP.codifica" />">
                        			<s:property value="#listoptSiNoCondP.descrizione" />
                   				</option>
                			
            		</s:iterator>
                  </select>
               </div>
            </div>
             <div class="col-md-3 col-xs-12">
               <span class="help-block" style="margin-top: 0;">Data fattura/richiesta pagamento CTU </span>
                  <s:set var="dataFattura" value="%{esecuzioneProvvedimenti.dataFattura}" />
                  <input type="text" id="dataFattura" class="form-control form-control-inline input-medium date-picker" value="<s:date name="dataFattura" format="dd/MM/yyyy" />"  onchange="calcolaGGdFatturaDLiqCtu('false')">
             </div>
             <div class="col-md-3 col-xs-12">
               <span class="help-block" style="margin-top: 0;">Data liquidazione CTU</span>
                  <s:set var="dataLiqCtuAtpo" value="%{esecuzioneProvvedimenti.dataLiqCtuAtpo}" />
                  <input type="text" class="form-control form-control-inline input-medium date-picker" id="dataLiqCtuAtpo" value="<s:date name="dataLiqCtuAtpo" format="dd/MM/yyyy" />"  onchange="calcolaGGdFatturaDLiqCtu('false');calcolaIntTempNotifDecrALiqPres('false')">
            </div>
            
         </div>
         
         <div class="row" style="margin-bottom: 40px;">
            
           
            
            <div class="col-md-3 col-xs-12">
               <div class="form-group form-md-line-input">
                  <s:set var="intTempFattElettrpagCtuAtpo" value="%{esecuzioneProvvedimenti.intTempFattElettrpagCtuAtpo}" />
                  <input type="text" class="form-control" id="intTempFattElettrpagCtuAtpo" value="<s:property value="intTempFattElettrpagCtuAtpo"/>" style="height: 38px; text-align:right;" readonly>
                  <label for="intTempFattElettrpagCtuAtpo" style="margin-top: -18px;">
                     Intervallo temporale da data richiesta a data pagamento CTU 
                  </label>
               </div>
            </div>
            
            <div class="col-md-3 col-xs-12">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="form_control_2" style="font-size: 13px; color: #888888;">Anticipate spese CTU<i class="fa fa-asterisk obbligatorio"></i></label><br>
                  <s:set var="antSpeseCtu" value="%{esecuzioneProvvedimenti.antSpeseCtu}" />
                  <s:set var="codAntSpeseCtu" value="%{codAntSpeseCtu}" />
                  
                  <select id="antSpeseCtu" class="form-control select2me">
                   	<option selected value="<s:property value="#codAntSpeseCtu" />"><s:property value="antSpeseCtu"/></option>
            			<s:iterator value="optionsSiNoEsProvv" var="listoptSiNoAntSpeseCtu">
                    			<option id="<s:property value="#listoptSiNoAntSpeseCtu.idTplTopologiche" />" value="<s:property value="#listoptSiNoAntSpeseCtu.codifica" />">
                        			<s:property value="#listoptSiNoAntSpeseCtu.descrizione" />
                   				</option>
            		</s:iterator>
                  </select>
               </div>
            </div>
             <div class="col-md-3 col-xs-12">
               <div class="form-group form-md-line-input">
				<s:set var="impSpeseCtuPagate" value="%{esecuzioneProvvedimenti.impSpeseCtuPagate}" />
                  <input type="text" class="form-control" id="impSpeseCtuPagate" value="<s:property value="impSpeseCtuPagate"/>" 
                  		onkeyup="impSpeseCtuPagateOnKeyUp()"
                  		style="height: 38px; text-align:right;">
                  <label for="form_control_1">
                     Importo spese CTU pagate 
                  </label>
               </div>
            </div>
            <div class="col-md-3 col-xs-12">
               <div class="form-group form-md-line-input">
               <s:set var="impSpeseCtuDovute" value="%{esecuzioneProvvedimenti.impSpeseCtuDovute}" />
                  <input type="text" class="form-control" id="impSpeseCtuDovute" value="<s:property value="impSpeseCtuDovute"/>" style="height: 38px; text-align:right;">
                  <label for="form_control_1">
                     Importo spese CTU dovute <i id="iImpSpeseCtuDovute" class="fa fa-asterisk"></i>
                  </label>
               </div>
            </div>
            
         </div>
        
         <div class="row" style="margin-bottom: 40px;">
            
           
            
            
            
            <div class="col-md-3 col-xs-12">
               <span class="help-block" style="margin-top: -18px;">Data lettera di invito al pagamento<br>spese legali </span>
              
                  <s:set var="datalettInvPagSpeseLegali" value="%{esecuzioneProvvedimenti.datalettInvPagSpeseLegali}" />
                  <input type="text" id="datalettInvPagSpeseLegali" class="form-control form-control-inline input-medium date-picker" value="<s:date name="datalettInvPagSpeseLegali" format="dd/MM/yyyy" />" >
                         
            </div>
            
            <div class="col-md-3 col-xs-12">
               <span class="help-block" style="margin-top: 0;">Data lettera recupero spese CTU </span>
                  <s:set var="dataLetteraRecuperoSpeseCtu" value="%{esecuzioneProvvedimenti.dataLetteraRecuperoSpeseCtu}" />
                  <input type="text" class="form-control form-control-inline input-medium date-picker" id="dataLetteraRecuperoSpeseCtu" value="<s:date name="dataLetteraRecuperoSpeseCtu" format="dd/MM/yyyy" />" >
               
            </div>
            <div class="col-md-3 col-xs-12">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="form_control_2" style="font-size: 13px; color: #888888;">Condanna pagamento spese legali<i class="fa fa-asterisk obbligatorio"></i></label><br>
                  <s:set var="condannaPagSpeseLegali" value="%{esecuzioneProvvedimenti.condannaPagSpeseLegali}" />
                   <s:set var="codCondannaPagSpeseLegali" value="%{codCondannaPagSpeseLegali}" />
                  
                  <select id="condannaPagSpeseLegali" class="form-control select2me" onchange="changeCondannaPagSpeseLegali()">
                   	<option selected value="<s:property value="#codCondannaPagSpeseLegali" />"><s:property value="condannaPagSpeseLegali"/></option>
            			<s:iterator value="opzCondPagSpeseLegali" var="liscondannaPagSpeseLegali">
                    			<option id="<s:property value="#liscondannaPagSpeseLegali.idTplTopologiche" />" value="<s:property value="#liscondannaPagSpeseLegali.codifica" />">
                        			<s:property value="#liscondannaPagSpeseLegali.descrizione" />
                   				</option>
            			</s:iterator>
                  </select>
               </div>
            </div>
            <div class="col-md-3 col-xs-12">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="form_control_2" style="font-size: 13px; color: #888888;">Soggetto richiedente il pagamento<i class="fa fa-asterisk obbligatorio"></i></label><br>
                  <s:set var="soggRichPagamento" value="%{esecuzioneProvvedimenti.soggRichPagamento}" />
                   <s:set var="codSoggRichPagamento" value="%{codSoggRichPagamento}" />
                  
                  <select id="soggRichPagament" class="form-control select2me">
                   	<option selected value="<s:property value="#codSoggRichPagamento" />"><s:property value="soggRichPagamento"/></option>
            			<s:iterator value="opzSoggRichPagamento" var="listopzSoggRichPagamento">
                    			<option id="<s:property value="#listopzSoggRichPagamento.idTplTopologiche" />" value="<s:property value="#listopzSoggRichPagamento.codifica" />">
                        			<s:property value="#listopzSoggRichPagamento.descrizione" />
                   				</option>
            			</s:iterator>
                  </select>
               </div>
            </div>
         </div>
         
         <div class="row" style="margin-bottom: 40px;">
            
            
            
            <div class="col-md-3 col-xs-12">
               <span class="help-block" style="margin-top: 0;">Data richiesta spese legali </span>
               
                  <s:set var="dataArriviNotula" value="%{esecuzioneProvvedimenti.dataArriviNotula}" />
                  <input type="text" id="dataArriviNotula" class="form-control form-control-inline input-medium date-picker" value="<s:date name="dataArriviNotula" format="dd/MM/yyyy" />" >
               
               
            </div>
            <div class="col-md-3 col-xs-12">
               <span class="help-block" style="margin-top: -18px;">Data pagamento spese legali Avv.<br>c/parte </span>
               
                  <s:set var="dataPagSpseLegaliAvvCparte" value="%{esecuzioneProvvedimenti.dataPagSpseLegaliAvvCparte}" />
                  <input type="text" id="dataPagSpseLegaliAvvCparte" class="form-control form-control-inline input-medium date-picker" value="<s:date name="dataPagSpseLegaliAvvCparte" format="dd/MM/yyyy" />"  onchange="calcolaIntTempAvvCParte('false')">
                 
            </div>
             <div class="col-md-3 col-xs-12">
               <div class="form-group form-md-line-input">
                  <s:set var="intTdepDecromgPagSpeseL" value="%{esecuzioneProvvedimenti.intTdepDecromgPagSpeseL}" />
                  <input type="text" class="form-control" id="intTdepDecromgPagSpeseL" value="<s:property value="intTdepDecromgPagSpeseL" />" style="height: 38px; text-align:right;"  readonly>
                  <label for="intTdepDecromgPagSpeseL" style="margin-top: -24px; font-size: 12px; line-height: 1.2;">
                     Intervallo temporale da deposito del<br>Decreto di OMOLOGA a pagamento<br>spese legali Avv. c/parte <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                  </label>
               </div>
            </div>
             <div class="col-md-3 col-xs-12" style="margin-top: -17px;">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="form_control_2" style="font-size: 13px; color: #888888;">Presenza decreto/sentenza per<br>mancato pagamento prestazione<i class="fa fa-asterisk obbligatorio"></i></label><br>
                  <s:set var="presDecrSentMancPagPrest" value="%{esecuzioneProvvedimenti.presDecrSentMancPagPrest}" />
                   <s:set var="codDresDecrSentMancPagPrest" value="%{codDresDecrSentMancPagPrest}" />
                  <select id="presDecrSentMancPagPre" class="form-control select2me">
                   	<option selected value="<s:property value="#codDresDecrSentMancPagPrest"/>"><s:property value="presDecrSentMancPagPrest"/></option>
            			<s:iterator value="optionsSiNoEsProvv" var="listpresDecrSentMancPagPrest">
                    			<option id="<s:property value="#listpresDecrSentMancPagPrest.idTplTopologiche" />" value="<s:property value="#listpresDecrSentMancPagPrest.codifica" />">
                        			<s:property value="#listpresDecrSentMancPagPrest.descrizione" />
                   				</option>
                			
            		</s:iterator>
                  </select>
               </div>
            </div>
         </div>
         
         <div class="row" style="margin-bottom: 50px;">
            
            
            <div class="col-md-3 col-xs-12">
               <div class="form-group form-md-line-input">
                  <s:set var="costoGiudizioMancPagPrest" value="%{esecuzioneProvvedimenti.costoGiudizioMancPagPrest}" />
                  <input type="text" class="form-control" id="costoGiudizioMancPagPrest" value="<s:property value="costoGiudizioMancPagPrest"/>" style="height: 38px; text-align:right;">
                  <label for="form_control_1" style="margin-top: -18px;">
                     Costo giudizio per mancato<br> pagamento prestazione <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                  </label>
               </div>
            </div>
            
           <div class="col-md-3 col-xs-12">
               <span class="help-block" style="margin-top: -18px;">Data limite del calcolo impatto </span>
               
                  <s:set var="dataLimiteCalcImp" value="%{esecuzioneProvvedimenti.dataLimiteCalcImpatto}" />
                  <input type="text" id="dataLimCalcImp" class="form-control form-control-inline input-medium date-picker" value="<s:date name="dataLimiteCalcImp" format="dd/MM/yyyy" />"  >
                  
             
            </div>
            
         </div>
         <!-- Precetto _____________________________________________________-->
         
         <div class="portlet box blue">
            
            <div class="portlet-title">
               <div class="caption">
                  Precetto
               </div>
            </div>
            
           <s:set var="siNoPre" value="%{esecuzioneProvvedimenti.noPrecetto.trim()}" />
            
           <s:if test='%{#siNoPre == "v"}'>
           <!-- tutto disabilitato -->
            <div class="portlet-body">
            
               <div class="row" style="margin: 15px 0;">
              		<div class="col-md-1 col-xs-12" style="margin-top: 0;">
              		
              		 <!-- tutto disabilitato -->
                     <div class="form-group form-md-checkboxes">
                        <div class="md-checkbox-inline">
                           <div class="md-checkbox">
                              <input type="checkbox" value="<s:property value="siNoPre" />"  id="siNoPrecetto" name="siNoPrecetto" onchange="changeStatePrecetto()" class="md-check" checked>
                              <label for="siNoPrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              No </label>
                           </div>
                        </div>
                     </div>
                     
                    
                  </div>
               </div>
                <div class="row" style="margin: 15px 0;">
                  <div class="col-md-3 col-xs-12">
                   <s:set var="speseLegaliPrec" value="%{esecuzioneProvvedimenti.speseLegaliFlagPrec.trim()}" />
                    <s:if test='#speseLegaliPrec == "f"'>
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" value="<s:property value="speseLegaliPrec" />" id="speseLegaliPrecetto" name="speseLegaliPrecetto" class="md-check" onchange="changeStateSpeseLegali()" disabled>
                              <label for="speseLegaliPrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese legali </label>
                           </div>
                        </div>
                     </div>
                     </s:if>
                     <s:elseif test='#speseLegaliPrec == "v"'>
                     	<div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" value="<s:property value="speseLegaliPrec" />" id="speseLegaliPrecetto"  name="speseLegaliPrecetto" class="md-check" onchange="changeStateSpeseLegali()" disabled checked>
                              <label for="speseLegaliPrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese legali </label>
                           </div>
                        </div>
                     </div>
                     </s:elseif>
                  </div>
                  <div class="col-md-3 col-xs-12">
               			<span class="help-block" style="margin-top: 0;">Data spese legali precetto </span>
               			<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                  			<s:set var="dataSpeseLegaliPrec" value="%{esecuzioneProvvedimenti.dataSpeseLegaliPrec}" />
                 	    	<input type="text" id="dataSpeseLegaliP" name="dataSpeseLegaliP" class="form-control" value="<s:date name="dataSpeseLegaliPrec" format="dd/MM/yyyy" />" readonly>
                  			<span class="input-group-btn">
                  				<button class="btn default" type="button" id="buttonSpeseL" onclick="initDataT('dataSpeseLegaliP')"disabled><i class="fa fa-calendar" ></i></button>
                  			</span>
               		   </div>
               		    
            	 </div>
            	  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;"> Data comunicazione precetto spese legali </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                     <s:set var="dataComunicazionePrecettoSpL" value="%{esecuzioneProvvedimenti.dataComPreSl}" />
                        <input type="text" id="dataComunicazionePrecettoSpeseL" name="dataComunicazionePrecettoSpeseL" class="form-control" value="<s:date name="dataComunicazionePrecettoSpL" format="dd/MM/yyyy" />" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" id="buttonDataComPrecetto" onclick="initDataT('dataComunicazionePrecettoSpeseL')" type="button" disabled><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoSpeseLegaliPrec" value="%{esecuzioneProvvedimenti.costoPreSl}" />
                        <input type="text" class="form-control" id="costoSpeseLegaliPrecetto" name="costoSpeseLegaliPrecetto" value="<s:property value="costoSpeseLegaliPrec" />" disabled style="height: 38px;">
                        <label for="form_control_1">
                           Costo precetto spese legali
                        </label>
                     </div>
                  </div>
                  
                  
               </div>
                <div class="row" style="margin: 15px 0;">
                
                  <div class="col-md-3 col-xs-12">
                  <s:set var="speseCTUPrec" value="%{esecuzioneProvvedimenti.speseCtuFlagPrec.trim()}" />
                   <s:if test='#speseCTUPrec == "f"'>
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" value="<s:property value="speseCTUPrec" />" id="speseCTUPrecetto" name="speseCTUPrecetto" class="md-check" onchange="changeStateSpeseCtu()" disabled>
                              <label for="speseCTUPrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese CTU </label>
                           </div>
                        </div>
                     </div>
                   </s:if>
                   <s:elseif test='#speseCTUPrec == "v"'>
                   <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" value="<s:property value="speseCTUPrec" />" id="speseCTUPrecetto" name="speseCTUPrecetto" class="md-check" onchange="changeStateSpeseCtu()" checked disabled>
                              <label for="speseCTUPrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese CTU </label>
                           </div>
                        </div>
                     </div>
                   </s:elseif>
                   
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;"> Data precetto per spese legali CTU </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                     <s:set var="dataSpeseCtu" value="%{esecuzioneProvvedimenti.dataSpeseCtuPrec}" />
                        <input type="text" id="dataSpeseCt" name="dataSpeseCt" class="form-control" value="<s:date name="dataSpeseCtu" format="dd/MM/yyyy" />" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" id="buttonDataSpeseCtu" onclick="initDataT('dataSpeseCt')" type="button" disabled><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                     Data comunicazione precetto spese CTU </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                     <s:set var="dataComPrecSpCtu" value="%{esecuzioneProvvedimenti.dataComPreSctu}" />
                        <input type="text" class="form-control" name="dataComPrecSpeseCtu" id="dataComPrecSpeseCtu" value="<s:date name="dataComPrecSpCtu" format="dd/MM/yyyy" />" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" onclick="initDataT('dataComPrecSpeseCtu')" id="buttonDataComPrecSpeseCtu" disabled><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                   <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoPrecSpCtu" value="%{esecuzioneProvvedimenti.costoPreSctu}" />
                        <input type="text" class="form-control" id="costoPrecSpeseCtu" name="costoPrecSpeseCtu" value="<s:property value="costoPrecSpCtu" />" disabled style="height: 38px;">
                        <label for="form_control_1">
                           Costo precetto spese CTU <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
                  
                </div>
                <div class="row" style="margin: 15px 0;">
                
                  
                  <div class="col-md-3 col-xs-12">
                  <s:set var="prestazionePrec" value="%{esecuzioneProvvedimenti.prestazioneFlagPrec.trim()}" />
                  <s:if test='#prestazionePrec == "f"'>
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" value="<s:property value="prestazionePrec" />" id="prestazionePrecetto" name="prestazionePrecetto" disabled class="md-check" onchange="changeStateprestazionePrecetto()">
                              <label for="prestazionePrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Prestazione </label>
                           </div>
                        </div>
                     </div>
                   </s:if>
                   <s:elseif test='#prestazionePrec == "v"'>
                   <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" value="<s:property value="prestazionePrec" />" id="prestazionePrecetto" name="prestazionePrecetto" checked disabled class="md-check" onchange="changeStateprestazionePrecetto()">
                              <label for="prestazionePrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Prestazione </label>
                           </div>
                        </div>
                     </div>
                   </s:elseif>
                  </div>
                  
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                    Data precetto per la prestazione </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                     <s:set var="dataPrestazioneP" value="%{esecuzioneProvvedimenti.dataPrestazione}" />
                        <input type="text" class="form-control" id="dataP"  value="<s:date name="dataPrestazioneP" format="dd/MM/yyyy" />" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" disabled onclick="initDataT('dataP')" id="buttonDataPrestazione"><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                     Data comunicazione precetto prestazione </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataComunicazionePrecettoPrest" value="%{esecuzioneProvvedimenti.dataComPrePrest}" />
                        <input type="text" id="dataComunicazionePrecettoPrestazione"  class="form-control" value="<s:date name="dataComunicazionePrecettoPrest" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataComunicazionePrecetto" disabled  onclick="initDataT('dataComunicazionePrecettoPrestazione')" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                   <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                        <s:set var="costoPrecettoPrest" value="%{esecuzioneProvvedimenti.costoPrePrest}" />
                        <input type="text" class="form-control" id="costoPrecettoPrestazione" name="costoPrecettoPrestazione" disabled value="<s:property value="costoPrecettoPrest" />" style="height: 38px;" >
                        <label for="form_control_1">
                           Costo precetto prestazione <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
                  
                </div>
               
       
         </div>
         </s:if>
         <s:elseif test='%{#siNoPre == "f"}'>
        
         <!-- tutto abilitato -->
         <div class="portlet-body">
            
               <div class="row" style="margin: 15px 0;">
              		<div class="col-md-1 col-xs-12" style="margin-top: 0;">
              		
              		 <!-- tutto disabilitato -->
                     <div class="form-group form-md-checkboxes">
                        <div class="md-checkbox-inline">
                           <div class="md-checkbox">
                              <input type="checkbox" value="<s:property value="siNoPre" />" id="siNoPrecetto" name="siNoPrecetto" onchange="changeStatePrecetto()" class="md-check">
                              <label for="siNoPrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span> No </label>
                           </div>
                        </div>
                     </div>
                     
                    
                  </div>
               </div>
                <div class="row" style="margin: 15px 0;">
                <s:set var="speseLegaliPrec" value="%{esecuzioneProvvedimenti.speseLegaliFlagPrec.trim()}" />
                <s:if test='#speseLegaliPrec == "f"'>
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" value="<s:property value="speseLegaliPrec" />" id="speseLegaliPrecetto" name="speseLegaliPrecetto" class="md-check" onchange="changeStateSpeseLegali()">
                              <label for="speseLegaliPrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese legali </label>
                           </div>
                        </div>
                     </div>
                     
                  </div>
                  <div class="col-md-3 col-xs-12">
               			<span class="help-block" style="margin-top: 0;">Data spese legali precetto </span>
               			<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                  			<s:set var="dataSpeseLegaliPrec" value="%{esecuzioneProvvedimenti.dataSpeseLegaliPrec}" />
                 	    	<input type="text" id="dataSpeseLegaliP"  class="form-control" value="<s:date name="dataSpeseLegaliPrec" format="dd/MM/yyyy" />"  readonly>
                  			<span class="input-group-btn">
                  				<button class="btn default" type="button"  id="buttonSpeseL" onclick="initDataT('dataSpeseLegaliP')" disabled><i class="fa fa-calendar" ></i></button>
                  			</span>
               		   </div>
               		   
            	 </div>
            	  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;"> Data comunicazione precetto spese legali </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                     <s:set var="dataComunicazionePrecettoSpL" value="%{esecuzioneProvvedimenti.dataComPreSl}" />
                        <input type="text" id="dataComunicazionePrecettoSpeseL" class="form-control" value="<s:date name="dataComunicazionePrecettoSpL" format="dd/MM/yyyy" />" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" id="buttonDataComPrecetto"  onclick="initDataT('dataComunicazionePrecettoSpeseL')" type="button" disabled><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoSpeseLegaliPrec" value="%{esecuzioneProvvedimenti.costoPreSl}" />
                        <input type="text" class="form-control" id="costoSpeseLegaliPrecetto" value="<s:property value="costoSpeseLegaliPrec" />"  disabled style="height: 38px;">
                        <label for="form_control_1">
                           Costo precetto spese legali
                        </label>
                     </div>
                  </div>
                  
               </s:if>   
               <s:elseif test='#speseLegaliPrec == "v"'>
               	 <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" value="<s:property value="speseLegaliPrec" />" checked id="speseLegaliPrecetto" name="speseLegaliPrecetto" class="md-check" onchange="changeStateSpeseLegali()">
                              <label for="speseLegaliPrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese legali </label>
                           </div>
                        </div>
                     </div>
                     
                  </div>
                  <div class="col-md-3 col-xs-12">
               			<span class="help-block" style="margin-top: 0;">Data spese legali precetto </span>
               			<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                  			<s:set var="dataSpeseLegaliPrec" value="%{esecuzioneProvvedimenti.dataSpeseLegaliPrec}" />
                 	    	<input type="text" id="dataSpeseLegaliP" class="form-control" value="<s:date name="dataSpeseLegaliPrec" format="dd/MM/yyyy" />" readonly>
                  			<span class="input-group-btn">
                  				<button class="btn default" type="button" id="buttonSpeseL" onclick="initDataT('dataSpeseLegaliP')"><i class="fa fa-calendar" ></i></button>
                  			</span>
               		   </div>
               		   
            	 </div>
            	  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;"> Data comunicazione precetto spese legali </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                     <s:set var="dataComunicazionePrecettoSpL" value="%{esecuzioneProvvedimenti.dataComPreSl}" />
                        <input type="text" id="dataComunicazionePrecettoSpeseL" class="form-control" value="<s:date name="dataComunicazionePrecettoSpL" format="dd/MM/yyyy" />" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" id="buttonDataComPrecetto"  onclick="initDataT('dataComunicazionePrecettoSpeseL')" type="button" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoSpeseLegaliPrec" value="%{esecuzioneProvvedimenti.costoPreSl}" />
                        <input type="text" class="form-control" id="costoSpeseLegaliPrecetto" value="<s:property value="costoSpeseLegaliPrec" />"  style="height: 38px;">
                        <label for="form_control_1">
                           Costo precetto spese legali
                        </label>
                     </div>
                  </div>
               
               
               </s:elseif>
               </div>
                <div class="row" style="margin: 15px 0;">
                 <s:set var="speseCTUPrec" value="%{esecuzioneProvvedimenti.speseCtuFlagPrec.trim()}" />
                 <s:if test='#speseCTUPrec == "v"'>
                
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" value="<s:property value="speseCTUPrec" />" checked id="speseCTUPrecetto" name="speseCTUPrecetto" class="md-check" onchange="changeStateSpeseCtu()" >
                              <label for="speseCTUPrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese CTU </label>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;"> Data precetto per spese legali CTU </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                     <s:set var="dataSpeseCtu" value="%{esecuzioneProvvedimenti.dataSpeseCtuPrec}" />
                        <input type="text" id="dataSpeseCt" class="form-control" value="<s:date name="dataSpeseCtu" format="dd/MM/yyyy" />" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" id="buttonDataSpeseCtu" onclick="initDataT('dataSpeseCt')" type="button" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                     Data comunicazione precetto spese CTU </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                     <s:set var="dataComPrecSpCtu" value="%{esecuzioneProvvedimenti.dataComPreSctu}" />
                        <input type="text" class="form-control" id="dataComPrecSpeseCtu" value="<s:date name="dataComPrecSpCtu" format="dd/MM/yyyy" />" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" onclick="initDataT('dataComPrecSpeseCtu')" id="buttonDataComPrecSpeseCtu" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                   <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoPrecSpCtu" value="%{esecuzioneProvvedimenti.costoPreSctu}" />
                        <input type="text" class="form-control" id="costoPrecSpeseCtu" value="<s:property value="costoPrecSpCtu" />"  style="height: 38px;">
                        <label for="form_control_1">
                           Costo precetto spese CTU <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
                 </s:if>
                 <s:elseif test='#speseCTUPrec == "f"'> 
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" value="<s:property value="speseCTUPrec" />" id="speseCTUPrecetto" name="speseCTUPrecetto" class="md-check" onchange="changeStateSpeseCtu()" >
                              <label for="speseCTUPrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese CTU </label>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;"> Data precetto per spese legali CTU </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                     <s:set var="dataSpeseCtu" value="%{esecuzioneProvvedimenti.dataSpeseCtuPrec}" />
                        <input type="text" id="dataSpeseCt" class="form-control" value="<s:date name="dataSpeseCtu" format="dd/MM/yyyy" />" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" id="buttonDataSpeseCtu" onclick="initDataT('dataSpeseCt')" disabled type="button" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                     Data comunicazione precetto spese CTU </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                     <s:set var="dataComPrecSpCtu" value="%{esecuzioneProvvedimenti.dataComPreSctu}" />
                        <input type="text" class="form-control" id="dataComPrecSpeseCtu" value="<s:date name="dataComPrecSpCtu" format="dd/MM/yyyy" />" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" onclick="initDataT('dataComPrecSpeseCtu')" disabled id="buttonDataComPrecSpeseCtu" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                   <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoPrecSpCtu" value="%{esecuzioneProvvedimenti.costoPreSctu}" />
                        <input type="text" class="form-control" id="costoPrecSpeseCtu" value="<s:property value="costoPrecSpCtu" />" disabled style="height: 38px;">
                        <label for="form_control_1">
                           Costo precetto spese CTU <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
                 
                 
                 </s:elseif>
                </div>
                <div class="row" style="margin: 15px 0;">
                 <s:set var="prestazionePrec" value="%{esecuzioneProvvedimenti.prestazioneFlagPrec.trim()}" />
                 <s:if test='#prestazionePrec == "v"'>
                  <div class="col-md-3 col-xs-12">
                 
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" checked value="<s:property value="prestazionePrec" />" id="prestazionePrecetto" name="prestazionePrecetto"  class="md-check" onchange="changeStateprestazionePrecetto()">
                              <label for="prestazionePrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Prestazione </label>
                           </div>
                        </div>
                     </div>
                     
                  </div>
                  
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                     Data precetto per la prestazione </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                     <s:set var="dataPrestazioneP" value="%{esecuzioneProvvedimenti.dataPrestazione}" />
                        <input type="text" class="form-control" id="dataP"  value="<s:date name="dataPrestazioneP" format="dd/MM/yyyy" />" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" onclick="initDataT('dataP')" id="buttonDataPrestazione"><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                     Data comunicazione precetto prestazione </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataComunicazionePrecPrestazione" value="%{esecuzioneProvvedimenti.dataComPrePrest}" />
                        <input type="text" id="dataComunicazionePrecettoPrestazione"  class="form-control" value="<s:date name="dataComunicazionePrecPrestazione" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataComunicazionePrecetto" onclick="initDataT('dataComunicazionePrecettoPrestazione')" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                   <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                        <s:set var="costoPrecettoPrest" value="%{esecuzioneProvvedimenti.costoPrePrest}" />
                        <input type="text" class="form-control" id="costoPrecettoPrestazione"  value="<s:property value="costoPrecettoPrest" />" style="height: 38px;" >
                        <label for="form_control_1">
                           Costo precetto prestazione <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
                  </s:if>
                  <s:elseif test='#prestazionePrec == "f"'>
                   <div class="col-md-3 col-xs-12">
                 
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" value="<s:property value="prestazionePrec" />" id="prestazionePrecetto" name="prestazionePrecetto"  class="md-check" onchange="changeStateprestazionePrecetto()">
                              <label for="prestazionePrecetto" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Prestazione </label>
                           </div>
                        </div>
                     </div>
                     
                  </div>
                  
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                     Data precetto per la prestazione </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                     <s:set var="dataPrestazioneP" value="%{esecuzioneProvvedimenti.dataPrestazione}" />
                        <input type="text" class="form-control" id="dataP"  value="<s:date name="dataPrestazioneP" format="dd/MM/yyyy" />" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" onclick="initDataT('dataP')" disabled id="buttonDataPrestazione"><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                     Data comunicazione precetto prestazione </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataComunicazionePrecPrestazione" value="%{esecuzioneProvvedimenti.dataComPrePrest}" />
                        <input type="text" id="dataComunicazionePrecettoPrestazione"  class="form-control" value="<s:date name="dataComunicazionePrecPrestazione" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataComunicazionePrecetto" disabled  onclick="initDataT('dataComunicazionePrecettoPrestazione')" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                   <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                        <s:set var="costoPrecettoPrestazione" value="%{esecuzioneProvvedimenti.costoPrePrest}" />
                        <input type="text" class="form-control" id="costoPrecettoPrestazione"  value="<s:property value="costoPrecettoPrestazione" />" disabled style="height: 38px;" >
                        <label for="form_control_1">
                           Costo precetto prestazione <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
                  
                  
                  </s:elseif>
                </div>
               
       
         </div>
         
         </s:elseif>
         
         </div>
         
         
         
         
         <!-- Pignoramento_________________________________________________________________________________________________________________________ -->
         
         
         <div class="portlet box blue">
            <div class="portlet-title">
               <div class="caption">
                 Pignoramento
               </div>
            </div>
           
            <s:set var="siNoPign" value="%{esecuzioneProvvedimenti.noPignoramento.trim()}" />
            <s:if test='#siNoPign == "v"'>
            
            <div class="portlet-body">
            
               <div class="row" style="margin: 15px 0;">
                  <div class="col-md-1 col-xs-12" style="margin-top: 0;">
                     <div class="form-group form-md-checkboxes">
                        <div class="md-checkbox-inline">
                           <div class="md-checkbox">
                              <input type="checkbox" id="siNoPignoramento" name="siNoPignoramento" class="md-check" onclick="changeStatePignoramento()" checked>
                              <label for="siNoPignoramento" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              No </label>
                           </div>
                        </div>
                     </div>
                  </div>
                </div>
                <div class="row" style="margin: 15px 0;">  
                  <s:set var="speseLegaliPign" value="%{esecuzioneProvvedimenti.speseLegaliFlagPign.trim()}" />
                  <s:if test='#speseLegaliPign == "f"'>
                  <!-- riga disabilitata -->
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" id="idSpeseLegaliPignoramento" name="idSpeseLegaliPignoramento" class="md-check" onclick="changeStateSpeseLegaliPignoramento()" disabled>
                              <label for="idSpeseLegaliPignoramento" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese legali </label>
                           </div>
                        </div>
                     </div>
                     
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                    	Data spese legali pignoramento</span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataSpeseLPign" value="%{esecuzioneProvvedimenti.dataSpeseLegaliPign}" />
                        <input type="text" id="dataSpeseLPig"  class="form-control" value="<s:date name="dataSpeseLPign" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataSpeseL" disabled  onclick="initDataT('dataSpeseLPig')" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                        <s:set var="costoPignSpL" value="%{esecuzioneProvvedimenti.costoPignSl}" />
                        <input type="text" class="form-control" id="costoPignSpeseL" value="<s:property value="costoPignSpL" />" style="height: 38px;" disabled>
                        <label for="form_control_1">
                           Costo pignoramento spese legali <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
                  </s:if>
                  
                   <s:elseif test='#speseLegaliPign == "v" '>
                   
                   <!--RIGA  ABILITATA-->
                    <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" id="idSpeseLegaliPignoramento" name="idSpeseLegaliPignoramento" class="md-check" checked onclick="changeStateSpeseLegaliPignoramento()"  disabled>
                              <label for="idSpeseLegaliPignoramento" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese legali </label>
                           </div>
                        </div>
                     </div>
                     
                  </div>
                   <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                    	Data spese legali pignoramento</span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataSpeseLPign" value="%{esecuzioneProvvedimenti.dataSpeseLegaliPign}" />
                        <input type="text" id="dataSpeseLPig"  class="form-control" value="<s:date name="dataSpeseLPign" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataSpeseL" disabled  onclick="initDataT('dataSpeseLPig')" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                        <s:set var="costoPignSpL" value="%{esecuzioneProvvedimenti.costoPignSl}" />
                        <input type="text" class="form-control" id="costoPignSpeseL" value="<s:property value="costoPignSpL" />"" style="height: 38px;" disabled>
                        <label for="form_control_1">
                           Costo pignoramento spese legali <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
                   </s:elseif>
                  
               </div>
               
               <s:set var="spesePignCt" value="%{esecuzioneProvvedimenti.speseCtuFlagPign.trim()}" />
               <s:if test='#spesePignCt == "f" '>
               
               <!-- RIGA DISABILITATA -->
               
               <div class="row" style="margin: 15px 0;">
                
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" id="spesePignCtu" name="spesePignCtu" class="md-check" onclick="changeStateSpeseCtuPignoramento()" disabled>
                              <label for="spesePignCtu" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese CTU </label>
                           </div>
                        </div>
                     </div>
                 
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                    	Data pignoramento per spese legali CTU </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataSpeseCtuPign" value="%{esecuzioneProvvedimenti.dataSpeseCtuPign}" />
                        <input type="text" id="dataSpeseCtuPig"  class="form-control" value="<s:date name="dataSpeseCtuPign" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataSpesePignCtu"  onclick="initDataT('dataSpeseCtuPig')" disabled><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoPignSpCtu" value="%{esecuzioneProvvedimenti.costoPignSctu}" />
                        <input type="text" class="form-control" id="costoPignSpeseCtu" value="<s:property value="costoPignSpCtu" />" style="height: 38px;" disabled>
                        <label for="form_control_1">
                           Costo pignoramento spese CTU <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
               </div>
               
               </s:if>
               <s:elseif  test='#spesePignCt == "v" '>
                <!-- RIGA ABILITATA -->
               
               <div class="row" style="margin: 15px 0;">
                  
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" id="spesePignCtu" name="spesePignCtu" class="md-check" onclick="changeStateSpeseCtuPignoramento()" checked disabled>
                              <label for="spesePignCtu" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese CTU </label>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                    	Data pignoramento per spese legali CTU </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataSpeseCtuPign" value="%{esecuzioneProvvedimenti.dataSpeseCtuPign}" />
                        <input type="text" id="dataSpeseCtuPig"  class="form-control" value="<s:date name="dataSpeseCtuPign" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataSpesePignCtu"  onclick="initDataT('dataSpeseCtuPig')" disabled ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoPignSpCtu" value="%{esecuzioneProvvedimenti.costoPignSctu}" />
                        <input type="text" class="form-control" id="costoPignSpeseCtu" value="<s:property value="costoPignSpCtu" />" style="height: 38px;" disabled>
                        <label for="form_control_1">
                           Costo pignoramento spese CTU <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
               </div>
               </s:elseif>
       			<s:set var="prestazionePig" value="%{esecuzioneProvvedimenti.prestazioneFlagPign.trim()}" />
                <s:if test='#prestazionePig == "f"'>
                <!-- diabilititata -->
               <div class="row" style="margin: 15px 0;">
                  
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" id="prestazionePign" name="prestazionePign" class="md-check" onclick="changeStateprestazionePignoramento()" disabled>
                              <label for="prestazionePign" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Prestazione </label>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                    	Data pignoramentoper la prestazione </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataPrestazionePign" value="%{esecuzioneProvvedimenti.dataPignoramentoPres}" />
                        <input type="text" id="dataPrestazionePig"  class="form-control" value="<s:date name="dataPrestazionePign" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataPrestazionePign"  onclick="initDataT('dataPrestazionePig')" disabled><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoPignPrest" value="%{esecuzioneProvvedimenti.costoPignPrest}" />
                        <input type="text" class="form-control" id="costoPignPrestazione" value="<s:property value="costoPignPrest" />" style="height: 38px;" disabled>
                        <label for="form_control_1">
                           Costo pignoramento prestazione <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
               </div>
               </s:if>
               
               <s:elseif  test='#prestazionePig == "v"'>
               <!-- ABILITA -->
               <div class="row" style="margin: 15px 0;">
               
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" id="prestazionePign" name="prestazionePign" class="md-check" onclick="changeStateprestazionePignoramento()"  checked disabled>
                              <label for="prestazionePign" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Prestazione </label>
                           </div>
                        </div>
                     </div>
                    
                  </div>
               
                   <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                    	Data pignoramentoper la prestazione </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataPrestazionePign" value="%{esecuzioneProvvedimenti.dataPignoramentoPres}" />
                        <input type="text" id="dataPrestazionePig"  class="form-control" value="<s:date name="dataPrestazionePign" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataPrestazionePign"  onclick="initDataT('dataPrestazionePig')" disabled><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoPignPrest" value="%{esecuzioneProvvedimenti.costoPignPrest}" />
                        <input type="text" class="form-control" id="costoPignPrestazione" value="<s:property value="costoPignPrest" />"  style="height: 38px;" disabled>
                        <label for="form_control_1">
                           Costo pignoramento prestazione <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
               </div>
              </s:elseif>
            </div>
            </s:if>
            <s:elseif  test='#siNoPign == "f"'>
                 <div class="portlet-body">
            
               <div class="row" style="margin: 15px 0;">
                  <div class="col-md-1 col-xs-12" style="margin-top: 0;">
                     <div class="form-group form-md-checkboxes">
                        <div class="md-checkbox-inline">
                           <div class="md-checkbox">
                              <input type="checkbox" id="siNoPignoramento" name="siNoPignoramento" class="md-check" onclick="changeStatePignoramento()" >
                              <label for="siNoPignoramento" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              No </label>
                           </div>
                        </div>
                     </div>
                  </div>
                  </div>
                   <div class="row" style="margin: 15px 0;"> 
                  <s:set var="speseLegaliPign" value="%{esecuzioneProvvedimenti.speseLegaliFlagPign.trim()}" />
                  <s:if test='#speseLegaliPign == "f"'>
                  <!-- riga disabilitata -->
                  
                  	<div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" id="idSpeseLegaliPignoramento" name="idSpeseLegaliPignoramento" class="md-check" onclick="changeStateSpeseLegaliPignoramento()">
                              <label for="idSpeseLegaliPignoramento" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese legali </label>
                           </div>
                        </div>
                     </div>
                     
                  </div>
                   <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                    	Data spese legali pignoramento</span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataSpeseLPign" value="%{esecuzioneProvvedimenti.dataSpeseLegaliPign}" />
                        <input type="text" id="dataSpeseLPig"  class="form-control" value="<s:date name="dataSpeseLPign" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataSpeseL" disabled  onclick="initDataT('dataSpeseLPig')" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                        <s:set var="costoPignSpL" value="%{esecuzioneProvvedimenti.costoPignSl}" />
                        <input type="text" class="form-control" id="costoPignSpeseL" value="<s:property value="costoPignSpL" />" style="height: 38px;" disabled>
                        <label for="form_control_1">
                           Costo pignoramento spese legali <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
                  </s:if>
                  
                   <s:elseif test='#speseLegaliPign == "v" '>
                   
                   <!--RIGA  ABILITATA-->
                   <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" id="idSpeseLegaliPignoramento" name="idSpeseLegaliPignoramento" class="md-check"  onclick="changeStateSpeseLegaliPignoramento()" checked >
                              <label for="idSpeseLegaliPignoramento" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese legali </label>
                           </div>
                        </div>
                     </div>
                     
                  </div>
                   <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                    	Data spese legali pignoramento</span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataSpeseLPign" value="%{esecuzioneProvvedimenti.dataSpeseLegaliPign}" />
                        <input type="text" id="dataSpeseLPig"  class="form-control" value="<s:date name="dataSpeseLPign" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataSpeseL"  onclick="initDataT('dataSpeseLPig')" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                        <s:set var="costoPignSpL" value="%{esecuzioneProvvedimenti.costoPignSl}" />
                        <input type="text" class="form-control" id="costoPignSpeseL" value="<s:property value="costoPignSpL" />"" style="height: 38px;">
                        <label for="form_control_1">
                           Costo pignoramento spese legali <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
                   </s:elseif>
                  
               </div>
               
               <s:set var="spesePignCt" value="%{esecuzioneProvvedimenti.speseCtuFlagPign.trim()}" />
               <s:if test='#spesePignCt == "f" '>
               
               <!-- RIGA DISABILITATA -->
               
               <div class="row" style="margin: 15px 0;">
                  
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" id="spesePignCtu" name="spesePignCtu" class="md-check" onclick="changeStateSpeseCtuPignoramento()" >
                              <label for="spesePignCtu" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese CTU </label>
                           </div>
                        </div>
                     </div>
                 
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                    	Data pignoramento per spese legali CTU </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataSpeseCtuPign" value="%{esecuzioneProvvedimenti.dataSpeseCtuPign}" />
                        <input type="text" id="dataSpeseCtuPig"  class="form-control" value="<s:date name="dataSpeseCtuPign" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataSpesePignCtu"  onclick="initDataT('dataSpeseCtuPig')" disabled ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoPignSpCtu" value="%{esecuzioneProvvedimenti.costoPignSctu}" />
                        <input type="text" class="form-control" id="costoPignSpeseCtu" value="<s:property value="costoPignSpCtu" />" style="height: 38px;" disabled>
                        <label for="form_control_1">
                           Costo pignoramento spese CTU <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
               </div>
               
               </s:if>
               <s:elseif  test='#spesePignCt == "v" '>
                <!-- RIGA ABILITATA -->
               
               <div class="row" style="margin: 15px 0;">
                
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" id="spesePignCtu" name="spesePignCtu" class="md-check" onclick="changeStateSpeseCtuPignoramento()" checked >
                              <label for="spesePignCtu" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Spese CTU </label>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                    	Data pignoramento per spese legali CTU </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataSpeseCtuPign" value="%{esecuzioneProvvedimenti.dataSpeseCtuPign}" />
                        <input type="text" id="dataSpeseCtuPig"  class="form-control" value="<s:date name="dataSpeseCtuPign" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataSpesePignCtu"  onclick="initDataT('dataSpeseCtuPig')" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-4 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoPignSpCtu" value="%{esecuzioneProvvedimenti.costoPignSctu}" />
                        <input type="text" class="form-control" id="costoPignSpeseCtu" value="<s:property value="costoPignSpCtu" />" style="height: 38px;" >
                        <label for="form_control_1">
                           Costo pignoramento spese CTU <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
               </div>
               </s:elseif>
       			<s:set var="prestazionePig" value="%{esecuzioneProvvedimenti.prestazioneFlagPign.trim()}" />
                <s:if test='#prestazionePig == "f"'>
                <!-- diabilititata -->
               <div class="row" style="margin: 15px 0;">
                 
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" id="prestazionePign" name="prestazionePign" class="md-check" onclick="changeStateprestazionePignoramento()" >
                              <label for="prestazionePign" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Prestazione </label>
                           </div>
                        </div>
                     </div>
                  </div>
                   <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                    	Data pignoramentoper la prestazione </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataPrestazionePign" value="%{esecuzioneProvvedimenti.dataPignoramentoPres}" />
                        <input type="text" id="dataPrestazionePig"  class="form-control" value="<s:date name="dataPrestazionePign" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataPrestazionePign"  onclick="initDataT('dataPrestazionePig')" disabled><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoPignPrest" value="%{esecuzioneProvvedimenti.costoPignPrest}" />
                        <input type="text" class="form-control" id="costoPignPrestazione" value="<s:property value="costoPignPrest" />" style="height: 38px;" disabled>
                        <label for="form_control_1">
                           Costo pignoramento prestazione <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
               </div>
               </s:if>
               
               <s:elseif  test='#prestazionePig == "v"'>
               <!-- ABILITA -->
               <div class="row" style="margin: 15px 0;">
                 
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-checkboxes" style="margin-bottom: 0;">
                        <div class="md-checkbox-inline" style="margin: 0;">
                           <div class="md-checkbox">
                              <input type="checkbox" id="prestazionePign" name="prestazionePign" class="md-check" onclick="changeStateprestazionePignoramento()"  checked >
                              <label for="prestazionePign" style="padding-left: 30px; font-size: 13px; color: #888888;">
                              <span></span>
                              <span class="check"></span>
                              <span class="box"></span>
                              Prestazione </label>
                           </div>
                        </div>
                     </div>
                    
                  </div>
                   <div class="col-md-3 col-xs-12">
                     <span class="help-block" style="margin-top: 0;">
                    	Data pignoramento per la prestazione </span>
                     <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                       <s:set var="dataPrestazionePign" value="%{esecuzioneProvvedimenti.dataPignoramentoPres}" />
                        <input type="text" id="dataPrestazionePig"  class="form-control" value="<s:date name="dataPrestazionePign" format="dd/MM/yyyy"/>" readonly>
                        <span class="input-group-btn">
                        <button class="btn default" type="button" id="buttonDataPrestazionePign"  onclick="initDataT('dataPrestazionePig')" ><i class="fa fa-calendar"></i></button>
                        </span>
                     </div>
                  </div>
                  <div class="col-md-3 col-xs-12">
                     <div class="form-group form-md-line-input">
                     <s:set var="costoPignPrest" value="%{esecuzioneProvvedimenti.costoPignPrest}" />
                        <input type="text" class="form-control" id="costoPignPrestazione" value="<s:property value="costoPignPrest" />"  style="height: 38px;" >
                        <label for="form_control_1">
                           Costo pignoramento prestazione <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                        </label>
                     </div>
                  </div>
               </div>
              </s:elseif>
            </div>
            
             
             </s:elseif>
            
         </div> <!-- chiude portlet pignoramento -->
      
      </div>
      <div class="form-actions noborder" style="padding-top: 0;">
         <button type="button" class="btn blue" onclick="salvaEsecuzioneProvvedimenti()" <s:property value="salvataggioAbilitato" /> >Salva</button>
         <button type="button" class="btn default" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getEsecuzioneProvvedimento', 'esProvvedimenti', null, null);" <s:property value="salvataggioAbilitato" /> >Annulla</button>
      </div>
      
      
   </form>
</div>
