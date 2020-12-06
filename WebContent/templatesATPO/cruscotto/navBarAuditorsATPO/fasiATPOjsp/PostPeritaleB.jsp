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


    <!-- Post peritale -->
    <div class="portlet-body form">
        <form role="form">
            <div class="form-body">
                <div class="row" style="margin-bottom: 40px;">
                    
                    <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                        <span class="help-block" style="margin-top: 0;">
                        	Data termine dissenso in SISCO </span>
                        <!--<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                        <s:set var="dataTermDiss" value="%{dataDissenso}" />
                         <input type="text" class="form-control" id="dataTermD" value="<s:date name="dataTermDiss" format="dd/MM/yyyy" />" 
                         readonly onchange="calcolaIntTempDataTrasmLps('false')">
                            <span class="input-group-btn">
                            	<button class="btn default" type="button"  disabled><i class="fa fa-calendar"></i></button>
                            </span>
                        </div>
                        
                        -->
                        <input id="dataTermD" onchange="calcolaIntTempPerB('false')" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataTermDiss" format="dd/MM/yyyy" />" ">
                        
                    </div>
                    
                    <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                        <span class="help-block" style="margin-top: 0;">
                        	Data deposito dissenso
                        </span>
                        <!--<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                        <s:set var="dataDepDissenso" value="%{postPeritale.dataDepDiss}" />
                            <input type="text" class="form-control" id="dataDepD" value="<s:date name="dataDepDissenso" format="dd/MM/yyyy" />" 
                            readonly onchange="calcolaIntTempPerB('false')">
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataT('dataDepD')"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div>
                        -->
                        <input id="dataDepD" onchange="calcolaIntTempPerB('false')" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataDepDissenso" format="dd/MM/yyyy" />" ">
                        
                    </div>
                   
                    <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group form-md-line-input">
                        <s:set var="intTempDiss" value="%{postPeritale.intTempDepDiss}" />
                        
 							<input type="text" class="form-control" id="intTempDissens" value="<s:property value="intTempDiss"/>" style="text-align:right;" readonly>
                            <label for="form_control_1" style="    margin-top: -24px; font-size: 11px; line-height: 1.2;">
                            	Intervallo temporale da data deposito dissenso a data termine dissenso
                                <!--<i class="fa fa-asterisk obbligatorio"></i>--></label>
                        </div>
                    </div>
                </div>
                
                <div class="row" style="margin-bottom: 40px;">
                    <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="codChiusCorretto" style="font-size: 13px; color: #888888;">Comunicazione deposito dissenso a Uff.legale<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="comDepDissUff" value="%{postPeritale.comDepDissUffLegale}" />
                            <s:set var="codComDepDissUffLegale" value="%{codComDepDissUffLegale}" />
                            <select id="comunDepDiss" class="form-control select2me">
                                <option selected value="<s:property value="#codComDepDissUffLegale" />"><s:property value="comDepDissUff"/></option>
                                <s:iterator value="comunicatoDepDissUffL" var="listDepDiss">
                    					<option id="<s:property value="#listDepDiss.idTplTopologiche" />" value="<s:property value="#listDepDiss.codifica" />">
                        					<s:property value="#listDepDiss.descrizione" />
                    					</option>
                					
            					</s:iterator>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                        <span class="help-block" style="margin-top: 0;">
                        Data deposito ricorso di 1 grado
                        </span>
                        <!--<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                        <s:set var="dataDepRiscorso" value="%{postPeritale.dataDepRicPrimoG}" />
                            <input type="text" class="form-control" id="dataDepRic" value="<s:date name="dataDepRiscorso" format="dd/MM/yyyy" />" 
                            readonly onchange="calcolaIntTempDataTrasmLps()">
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataT('dataDepRic')"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div>
                        -->
                        <input id="dataDepRic"  class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataDepRiscorso" format="dd/MM/yyyy" />" ">
                        
                    </div>
                    <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                        <span class="help-block" style="margin-top: 0;">
                        Data di definizione pratica
                        </span>
                        <!--<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                        <s:set var="dataDefPratica" value="%{postPeritale.dataDefPratica}" />
                            <input type="text" class="form-control" id="dataDefP" value="<s:date name="dataDefPratica" format="dd/MM/yyyy" />" 
                            readonly onchange="calcolaIntTempDataTrasmLps()">
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataT('dataDefP')"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div>
                        -->
                        <input id="dataDefP"  class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataDefPratica" format="dd/MM/yyyy" />" ">
                        
                    </div>
                    
                    
                    
                    
                   
                </div>
            	 <div class="row" style="margin-bottom: 40px;">
		            	 <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
		                        <div class="form-group" style="margin-bottom: 0;">
		                            <label for="form_control_2" style="font-size: 13px; color: #888888;">Tipo dissenso<i class="fa fa-asterisk obbligatorio"></i></label>
		                            <br>
		                            <s:set var="tipoDissenso" value="%{postPeritale.tipoDissenso}" />
		                             <s:set var="tipoD" value="%{tipoDiss}" />
		                             
		                            <select id="tipoDiss" class="form-control select2me">
		                             <option selected value="<s:property value="#tipoD" />"><s:property value="tipoDissenso" /></option>
		                                <s:iterator value="tipoDis" var="listTipoDiss">
		                    		   		
		                        				<option id="<s:property value="#listTipoDiss.idTplTopologiche" />"  value="<s:property value="#listTipoDiss.codifica" />">
		                            				<s:property value="#listTipoDiss.descrizione" />
		                            			</option>
		                            		
		                            	</s:iterator>
		                            </select>
		                        </div>
	                    </div>
	                    
                	<div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group form-md-line-input">
                        <s:set var="spesePagate" value="%{postPeritale.spesePagate}" />
                            <input type="text" class="form-control" id="spesePagate"  value="<s:property value="spesePagate"/>" style="height: 38px; text-align:right;" >
                            <label for="form_control_1" style="    margin-top: 0;">
                            	Spese legali pagate
                            </label>
                        </div>
                    </div>
            	 </div>
                
            </div>
           <s:set var="statoEsamePratica" value="%{statoEsamePratica.trim()}" />
            
             <s:if test='%{#statoEsamePratica == "C"}'>
      	 			 <div class="form-actions noborder" style="padding-top: 0;">
                <button type="button" disabled class="btn blue"  onclick="salvaPostPeritaleB()" <s:property value="salvataggioAbilitato" /> >Salva</button>
                
            </div>
      		</s:if>
      		<s:else>
      			<input id="codChiusuraCorretto" type="hidden" value="<s:property value="%{codChiusuraCorretto}"/>" />
      		    <input id="codChiusuraInserito" type="hidden" value="<s:property value="%{codChiusuraInserito}"/>" />
      		    
      	 		<div class="form-actions noborder" style="padding-top: 0;">
                <button type="button" class="btn blue" onclick="salvaPostPeritaleB()" <s:property value="salvataggioAbilitato" /> >Salva</button>
                <button type="button" class="btn default" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getPostPeritaleB', 'postPeritale', null, 'initDatePicker');"  <s:property value="salvataggioAbilitato" />  >Annulla</button>
            </div>
      		</s:else>
            
        </form>
    </div>

   