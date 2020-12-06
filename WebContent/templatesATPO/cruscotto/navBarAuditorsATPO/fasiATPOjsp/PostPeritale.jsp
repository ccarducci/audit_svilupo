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
		speseDecrOmologaOnKeyUp();
	</script>
    <!-- Post peritale -->
    <div class="portlet-body form">
        <form role="form">
            <div class="form-body">
                <div class="row" style="margin-bottom: 40px;">
                    
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <span class="help-block" style="margin-top: -18px;">
                        	Data deposito del Decreto di<br>OMOLOGA </span>
                        <!--<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                        <s:set var="dataDepositoDecOmologa" value="%{postPeritale.dataDepositoDecOmologa}" />
                         <input type="text" class="form-control" id="dataDepositoDecOmologa" value="<s:date name="dataDepositoDecOmologa" format="dd/MM/yyyy" />" 
                         readonly onchange="calcolaIntTempDataTrasmLps('false')">
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataDepositoDecOmologa()"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div>
                        -->
                        <input id="dataDepositoDecOmologa" onchange="calcolaIntTempDataTrasmLps('false')" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataDepositoDecOmologa" format="dd/MM/yyyy" />" ">
                        
                    </div>
                    
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <span class="help-block" style="margin-top: 0;">
                        	Data NOTIFICA Decreto di OMOLOGA 
                        </span>
                        <!--<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                        <s:set var="dataNotificaDecOmologa" value="%{postPeritale.dataNotificaDecOmologa}" />
                            <input type="text" class="form-control" id="dataNotificaDecOmologa" value="<s:date name="dataNotificaDecOmologa" format="dd/MM/yyyy" />" 
                            readonly onchange="calcolaIntTempNotifOmg('false'); calcolaIntTempDataTrasmLps('false')">
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataNotificaDecOmologa()"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div>
                        -->
                        <input id="dataNotificaDecOmologa" onchange="calcolaIntTempNotifOmg('false'); calcolaIntTempDataTrasmLps('false')" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataNotificaDecOmologa" format="dd/MM/yyyy" />" ">
                        
                    </div>
                    
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <span class="help-block" style="margin-top: -18px;">
                        	Data protocollo Decreto di OMOLOGA<br>notificato
                       	</span>
                        <!--<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                            <s:set var="dataProtDecOmologaNotif" value="%{postPeritale.dataProtDecOmologaNotif}" />
                            <input type="text" class="form-control" id="dataProtDecOmologaNotif" value="<s:date name="dataProtDecOmologaNotif" format="dd/MM/yyyy" />" 
                            readonly onchange="calcolaIntTempNotifOmg('false')">
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataProtDecOmologaNotif()"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div>
                        
                        -->
                        <input id="dataProtDecOmologaNotif"  onchange="calcolaIntTempNotifOmg('false')" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataProtDecOmologaNotif" format="dd/MM/yyyy" />" ">
                        
                    </div>
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group form-md-line-input">
                        <s:set var="intTempNotifOmgProtOmg" value="%{postPeritale.intTempNotifOmgProtOmg}" />
                        
 							<input type="text" class="form-control" id="intTempNotifOmgProtOmg" style="text-align:right;" value="<s:property value="intTempNotifOmgProtOmg"/>" readonly>
                            <label for="form_control_1" style="    margin-top: -24px; font-size: 11px; line-height: 1.2;">
                            	Intervallo temporale da data NOTIFICA  Decreto di OMOLOGA a data protocollo
                                <br>Decreto di OMOLOGA notificato 
                                <!--<i class="fa fa-asterisk obbligatorio"></i>--></label>
                        </div>
                    </div>
                </div>
                
                <div class="row" style="margin-bottom: 40px;">
                <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group form-md-line-input">
                        <s:set var="spesePagate" value="%{postPeritale.spesePagate}" />
                            <input type="text" class="form-control" id="spesePagate"  value="<s:property value="spesePagate"/>" style="height: 38px; text-align:right;" >
                            <label for="form_control_1" style="    margin-top: 0;">
                            	Spese legali pagate <i id="iSpesePagate" class="fa fa-asterisk"></i>
                            </label>
                        </div>
                    </div>
                    
                    <!-- L inserimento è obbligatorio se Cod_chiu_corr = 3 -->
                      <s:set var="giudiziofD" value="%{giudiziofaseDati}" />
                     
                   <!--  <input type="hidden" value="<s:property value="giudiziofD" />" id="parzFav"> -->
                   
                    
                	
                	 <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group form-md-line-input">
                        <s:set var="speseDecrOmologa" value="%{postPeritale.speseDecrOmologa}" />
                            <input type="text" class="form-control" id="speseDecrOmologa" value="<s:property value="speseDecrOmologa"/>" style="height: 38px; text-align:right;"  onkeyup="speseDecrOmologaOnKeyUp();">
                            	<label for="speseDecrOmologa" style="margin-top: 0;">Spese legali nel decreto di omologa
                           	</label>
                        </div>
                    </div>
                	
                
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="codChiusCorretto" style="font-size: 13px; color: #888888;">Codice chiusura corretto<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                             <s:set var="codChiusuraCorretto" value="%{postPeritale.codChiusuraCorretto}" />
                    		<s:set var="codCodChiusuraCorretto" value="%{codificaCodChiusuraCorretto}" />
                            <select id="codChiusCorretto" class="form-control select2me" onchange="checkSpeseDecrOmologa()" disabled="disabled">
                                <option selected value="<s:property value="#codCodChiusuraCorretto" />"><s:property value="codChiusuraCorretto"/></option>
                                <s:iterator value="optionsCodChiusura" var="listCodChiusura">
                    					<option id="<s:property value="#listCodChiusura.idTplTopologiche" />" value="<s:property value="#listCodChiusura.codifica" />">
                        					<s:property value="#listCodChiusura.descrizione" />
                    					</option>
                					
            					</s:iterator>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2" style="font-size: 13px; color: #888888;">Codice chiusura inserito<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="codChiusuraInserito" value="%{postPeritale.codChiusuraInserito}" />
                            <s:set var="codCodChiusuraInserito" value="%{codificaCodChiusuraInserito}" />
                            <select id="codChiusuraInserito" class="form-control select2me" disabled="disabled">
                                <option selected value="<s:property value="#codCodChiusuraInserito" />"><s:property value="codChiusuraInserito"/></option>
                                <s:iterator value="optionsCodChiusura" var="listCodChiusuraIns">
                					
                    					<option id="<s:property value="#listCodChiusuraIns.idTplTopologiche" />" value="<s:property value="#listCodChiusuraIns.codifica" />">
                        					<s:property value="#listCodChiusuraIns.descrizione" />
                    					</option>
                					
            					</s:iterator>
                            </select>
                        </div>
                    </div>
                    
                    
                    
                   
                </div>
                <div class="row" style="margin-bottom: 40px;">
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2" style="font-size: 13px; color: #888888; margin-top: -18px;">Verifica corrispondenza tra decreto di OMOLOGA e CTU definitiva<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                             <s:set var="corrispDecrOmgEctuDef" value="%{postPeritale.corrispDecrOmgEctuDef}" />
                             <s:set var="codCorrispDecrOmgEctuD" value="%{codCorrispDecrOmgEctuDef}" />
                            <select id="corrispDecrOmgEctuDe" class="form-control select2me">
                                <option selected value="<s:property value="#codCorrispDecrOmgEctuD" />"><s:property value="corrispDecrOmgEctuDef"/></option>
                                <s:iterator value="corrispDecrOMGeCtuDef" var="listCorrispDecrOMGeCtuDef">
                					
                    					<option id="<s:property value="#listCorrispDecrOMGeCtuDef.idTplTopologiche" />" value="<s:property value="#listCorrispDecrOMGeCtuDef.codifica" />">
                        					<s:property value="#listCorrispDecrOMGeCtuDef.descrizione" />
                    					</option>
                					
            					</s:iterator>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2" style="font-size: 13px; color: #888888;">Cod. pagamento spese legali ins.<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="codPagamentoSpeseLegali" value="%{postPeritale.codPagamentoSpeseLegali}" />
                             <s:set var="codCodPagamentoSpeseLegali" value="%{codCodPagamentoSpeseLegali}" />
                            <select id="codPagamentoSpeseLegali" class="form-control select2me" >
                               <option selected value="<s:property value="#codCodPagamentoSpeseLegali" />"><s:property value="codPagamentoSpeseLegali"/></option>
                               		<s:iterator value="codPagamentoSpeseLegaliINPS" var="listCodPSpeseInps">
                   							<option id="<s:property value="#listCodPSpeseInps.idTplTopologiche" />" value="<s:property value="#listCodPSpeseInps.codifica" />">
                        						<s:property value="#listCodPSpeseInps.descrizione" />
                   							</option>
                						
            						</s:iterator>
                            </select>
                        </div>
                    </div>
                    
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                       <div class="form-group" style="margin-bottom: 0;">
                        <label for="form_control_2" style="font-size: 13px; color: #888888;">Cod. pagamento spese legali corretto<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                        <s:set var="codPagamentoSpeseLegaliCorretto" value="%{postPeritale.codPagamentoSpeseLegaliCorretto}" />
                        <s:set var="codCodPagamentoSpeseLegaliCorretto" value="%{codCodPagamentoSpeseLegaliCorretto}" />
                          
                        <select id="codPagamentoSpeseLegaliC" class="form-control select2me" >
                               <option selected value="<s:property value="#codCodPagamentoSpeseLegaliCorretto" />"><s:property value="codPagamentoSpeseLegaliCorretto"/></option>
                               		<s:iterator value="codPagamentoSpeseLegaliINPS" var="listCodPSpeseInpsCorr">
                   							<option id="<s:property value="#listCodPSpeseInpsCorr.idTplTopologiche" />" value="<s:property value="#listCodPSpeseInpsCorr.codifica" />">
                        						<s:property value="#listCodPSpeseInpsCorr.descrizione" />
                   							</option>
                						
            						</s:iterator>
                            </select>
                        </div>
                    </div>
                    
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="recDatiPratica" style="font-size: 13px; color: #888888;">Registrazione dati pratica<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="recDatiPratica" value="%{postPeritale.recDatiPratica}" />
                            <s:set var="codRecDatiPratica" value="%{codRecDatiPratica}" />
                            <select id="recDatiPratica" class="form-control select2me">
                                <option selected value="<s:property value="#codRecDatiPratica" />"><s:property value="recDatiPratica"/></option>
									<s:iterator value="optionsRecDatiPratica" var="listOpRecDatiPratica">
                						
                    						<option id="<s:property value="#listOpRecDatiPratica.idTplTopologiche" />" value="<s:property value="#listOpRecDatiPratica.codifica" />">
                        						<s:property value="#listOpRecDatiPratica.descrizione" />
                    						</option>
                					
            						</s:iterator>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 30px;">
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <span class="help-block" style="margin-top: 0;">
                        Data trasmissione Decreto alla LPS
                        </span>
                        <!--
                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                        <s:set var="dataTrasmissDecrLPS" value="%{postPeritale.dataTrasmissDecrLPS}" />
                            <input type="text" class="form-control" id="dataTrasmissDecrLPS" value="<s:date name="dataTrasmissDecrLPS" format="dd/MM/yyyy" />" 
                            readonly onchange="calcolaIntTempDataTrasmLps('false')">
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataTrasmissDecrLPS()"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div>-->
                        
                        <input id="dataTrasmissDecrLPS"  onchange="calcolaIntTempDataTrasmLps('false')" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataTrasmissDecrLPS" format="dd/MM/yyyy" />" ">
                        
                    </div>
                    
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="omologaAllegata" style="font-size: 13px; color: #888888;">Omologa allegata<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                             <s:set var="omologaAllegata" value="%{postPeritale.omologaAllegata}" />
                              <s:set var="codOmologaAllegata" value="%{codOmologaAllegata}" />
                            <select id="omologaAllegata" class="form-control select2me">
                                <option selected value="<s:property value="#codOmologaAllegata" />"><s:property value="omologaAllegata"/></option>
                                	<s:iterator value="optionsSiNoPP" var="listOptionsSiNoPP">
                						
                    						<option id="<s:property value="#listOptionsSiNoPP.idTplTopologiche" />" value="<s:property value="#listOptionsSiNoPP.codifica" />">
                        						<s:property value="#listOptionsSiNoPP.descrizione" />
                    						</option>
                						
            						</s:iterator>
                            </select>
                        </div>
                    </div>
                    
                    
                    <div class="col-md-6 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group form-md-line-input">
                        <s:set var="intTempNotifDecrOmgAdecrLps" value="%{postPeritale.intTempNotifDecrOmgAdecrLps}" />
                            <input type="text" class="form-control" id="intTempNotifDecrOmgAdecrLps" value="<s:property value="intTempNotifDecrOmgAdecrLps"/>" style="height: 38px; text-align:right;" readonly>
                            <label for="form_control_1" style="margin-top: -18px;">Intervallo temporale da data deposito Decreto di OMOLOGA a data trasmissione del Decreto alla LPS.
                            (Se data deposito non è specificata vedere data notifica decreto omologa)
                            </label>
                        </div>
                    </div>
                </div>
            </div>
      	 	
      	 	<div class="form-actions noborder" style="padding-top: 0;">
                <button type="button" class="btn blue" onclick="salvaPostPeritale()"  <s:property value="salvataggioAbilitato" />  >Salva</button>
                <button type="button" class="btn default" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getPostPeritale', 'postPeritale', null, null);"  <s:property value="salvataggioAbilitato" />  >Annulla</button>
            </div>
        </form>
    </div>

   