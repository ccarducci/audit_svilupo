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
	if( $('#verifPagCtuEff').val() === '1' &&  $('#impSpeseCtuPagate').val() != "" &&  $('#impSpeseCtuPagate').val() > 0 ){

		setObbligatorieta('iImpSpeseCtuDovute' , true );
    }else{
    	setObbligatorieta('iImpSpeseCtuDovute' , false );
    } 
	*/
	impSpeseCtuPagateDissOnKeyUp();
</script>

<!-- Esecuzione provvedimenti -->
<div class="portlet-body form">
   <form role="form">
      <div class="form-body">
         <div class="row" style="margin-bottom: 40px;">
            <div class="col-md-3 col-xs-12" style="margin-top: -5px;">
               <span class="help-block">
               Data decreto liquidazione CTU </span>
               <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
               <s:set var="dataDecrLiqCtu" value="%{esecuzioneProvvedimenti.dataDecrLiqCtu}" />
               
                  <input id="dataDecrLiqCtu"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" value="<s:date name="dataDecrLiqCtu" format="dd/MM/yyyy" />">
               </div>
            </div>
            
            <div class="col-md-4 col-xs-12">
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
            
            <div class="col-md-4 col-xs-12">
               <span class="help-block" style="margin-top: 0;">
               	Data fattura 
               </span>
               <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                  <s:set var="dataFattura" value="%{esecuzioneProvvedimenti.dataFattura}" />
                  
                  <input id="dataFattura"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" value="<s:date name="dataFattura" format="dd/MM/yyyy" />">
               </div>
            </div>
            
         </div>
         
         
         <div class="row" style="margin-bottom: 40px;">
          <div class="col-md-3 col-xs-12">
               <span class="help-block" style="margin-top: 0;">Data liquidazione CTU ATPO </span>
               <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" >
                  <s:set var="dataLiqCtuAtpo" value="%{esecuzioneProvvedimenti.dataLiqCtuAtpo}" />
          
                 <input id="dataLiqCtuAtpo"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" value="<s:date name="dataLiqCtuAtpo" format="dd/MM/yyyy" />">
                  
               </div>
            </div>
         
         <div class="col-md-4 col-xs-12">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="form_control_2" style="font-size: 13px; color: #888888;">Condanna a pagamento CTU 1 grado<i class="fa fa-asterisk obbligatorio"></i></label><br>
                 <s:set var="condannaPagCtuPg" value="%{esecuzioneProvvedimenti.condannaPagCtu1g}" />
                 <s:set var="codCondannaPagCtuPg" value="%{codCondannaPagCtuPg}" />
                  
                  <select id="condannaPagCtuPrg" class="form-control select2me">
                   	<option selected value="<s:property value="codCondannaPagCtuPg" />"><s:property value="condannaPagCtuPg"/></option>
            			<s:iterator value="optionsSiNoEsProvv" var="listSiNoPg">
                			
                    			<option id="<s:property value="#listSiNoPg.idTplTopologiche" />" value="<s:property value="#listSiNoPg.codifica" />">
                        			<s:property value="#listSiNoPg.descrizione" />
                   				</option>
                			
            		</s:iterator>
                  </select>
               </div>
            </div>
            
            <div class="col-md-4 col-xs-12">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="form_control_2" style="font-size: 13px; color: #888888;">Verifica pagamento CTU già effettuato<i class="fa fa-asterisk obbligatorio"></i></label><br>
                 <s:set var="verPagCtuEff" value="%{esecuzioneProvvedimenti.verPagCtuEff}" />
                 <s:set var="codVerPagCtuEff" value="%{codVerPagCtuEff}" />
                  
                  <select id="verifPagCtuEff" class="form-control select2me" onchange="verifPagCtuEffOnChange()">
                   	<option selected value="<s:property value="codVerPagCtuEff" />"><s:property value="verPagCtuEff"/></option>
            			<s:iterator value="optionsVerPagCtuEff" var="listoptionsVerPagCtuEff">
                			
                    			<option id="<s:property value="#listoptionsVerPagCtuEff.idTplTopologiche" />" value="<s:property value="#listoptionsVerPagCtuEff.codifica" />">
                        			<s:property value="#listoptionsVerPagCtuEff.descrizione" />
                   				</option>
                			
            		</s:iterator>
                  </select>
               </div>
            </div>
            
         </div> 
      
         <div class="row" style="margin-bottom: 40px;">
         
         
             <div class="col-md-3 col-xs-12">
               <div class="form-group form-md-line-input">
				<s:set var="impSpeseCtuPagate" value="%{esecuzioneProvvedimenti.impSpeseCtuPagate}" />
                  <input type="text" class="form-control" id="impSpeseCtuPagate" value="<s:property value="impSpeseCtuPagate"/>" 
                  		onkeyup="impSpeseCtuPagateDissOnKeyUp()"
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
        
      </div>
      
      <div class="form-actions noborder" style="padding-top: 0;">
         <button type="button" class="btn blue" onclick="salvaEsecuzioneProvvedimentiB()" <s:property value="salvataggioAbilitato" /> >Salva</button>
         <button type="button" class="btn default" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getEsecuzioneProvvedimentoB', 'esProvvedimenti', null, null);" <s:property value="salvataggioAbilitato" /> >Annulla</button>
      </div>

      
   </form>
</div>
