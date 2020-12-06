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

    <!-- Gestione istruttoria -->
    <div class="portlet-body form">
        <form role="form">
            <div class="form-body">
            	
                <div class="row" style="margin-bottom: 40px;">
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="eccNonRilevabili" style="font-size: 13px; color: #888888; margin-top: -18px;">Atto di costituzione rintracciabile<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="yn" value="%{gestioneIStruttoria.eccezioniNonRilevabili.trim()}" />
                            <s:set var="ce" value="%{codificaEccNonRilevabili}" />
                            	<select id="eccNonRilevabili" class="form-control select2me" onchange="checkEccNonRilevabili()">
                               		<option selected value="<s:property value="#ce" />"><s:property value="yn"/></option>
                               		<s:iterator value="yesNo" var="listYN">
                    		   		
                        				<option id="<s:property value="#listYN.idTplTopologiche" />" value="<s:property value="#listYN.codifica" />">
                            				<s:property value="#listYN.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="litispendenza" style="font-size: 13px; color: #888888;">Litispendenza<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                             <s:set var="lit" value="%{gestioneIStruttoria.litispendenza.trim()}" />
                             <s:set var="codlit" value="%{codificaLitispendenza.trim()}" />
                            <s:if test="%{#yn != 'No'}">
                            	<select id="litispendenza" class="form-control select2me" >
                            </s:if>
                            <s:else>
                            	<select id="litispendenza" class="form-control select2me" disabled>
                            </s:else>
                               
                               <option selected value="<s:property value="#codlit" />"><s:property value="lit" /></option>
                               		<s:iterator value="optionsDropDownCondivisi" var="listOpt">
                    		   		
                        				<option id="<s:property value="#listOpt.idTplTopologiche" />" value="<s:property value="#listOpt.codifica" />">
                            				<s:property value="#listOpt.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="decadenza" style="font-size: 13px; color: #888888;">Decadenza<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="dec" value="%{gestioneIStruttoria.decadenza}" />
                             <s:set var="codec" value="%{codificaDecadenza}" />
                            
                            <s:if test="%{#yn != 'No'}">
                            	<select id="decadenza" class="form-control select2me">
                            </s:if>
                            <s:else>
                            	<select id="decadenza" class="form-control select2me" disabled>
                            </s:else>
                               <option selected value="<s:property value="#codec" />"><s:property value="dec" /></option>
                               		<s:iterator value="optionsDropDownCondivisi" var="listOptD">
                    		   		
                        				<option id="<s:property value="#listOptD.idTplTopologiche" />" value="<s:property value="#listOptD.codifica" />">
                            				<s:property value="#listOptD.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                        	</select>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="corrispAtp"  style="font-size: 13px; color: #888888; margin-top: -18px;">Corrispondenza tra istanza ATP e domanda amministrativa di invalidità<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="corrisp" value="%{gestioneIStruttoria.corrispAtpDomAmmInv}" />
                            <s:set var="codCorrisp" value="%{codificaCorrisp}" />
                             <s:if test="%{#yn != 'No'}">
                            	 <select id="corrispAtp" class="form-control select2me">
                            </s:if>
                            <s:else>
                            	<select id="corrispAtp" class="form-control select2me" disabled>
                            </s:else>
                           
                               <option selected value="<s:property value="#codCorrisp" />"><s:property value="corrisp" /></option>
                               		<s:iterator value="corrispondenzaIstanzaATP" var="listAtp">
                    		   		
                        				<option id="<s:property value="#listAtp.idTplTopologiche" />" value="<s:property value="#listAtp.codifica" />">
                            			<s:property value="#listAtp.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                        	</select>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 40px;">
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2"  style="font-size: 13px; color: #888888; margin-top: -18px;">Verifica correttezza dichiarazione di esenzione pagamento spese<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="verifica" value="%{gestioneIStruttoria.verificaDicEsPagSpese}" />
                            <s:set var="codificaCorrett" value="%{codificaCorrett}" />
                            
                           
                            <s:if test="%{#yn != 'No'}">
                            	 <select id="verifica" class="form-control select2me">
                            </s:if>
                            <s:else>
                            	<select id="verifica" class="form-control select2me" disabled>
                            </s:else>
                               <option selected value="<s:property value="#codificaCorrett" />"><s:property value="verifica" /></option>
                               		<s:iterator value="correttezzaDichiarazioneEsenzione" var="listCorret">
                    		   		
                        				<option id="<s:property value="#listCorret.idTplTopologiche" />" 
                        				value="<s:property value="#listCorret.codifica" />">
                            			<s:property value="#listCorret.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                        	</select>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2"  style="font-size: 13px; color: #888888; margin-top: -18px;">Indeterminatezza dell'oggetto della domanda<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                             <s:set var="indet" value="%{gestioneIStruttoria.indeterminatezzaOggDom}" />
                             <s:set var="codIndet" value="%{codificaIndet}" />
                            <s:if test="%{#yn != 'No'}">
                            	<select id="oggDomanda" class="form-control select2me">
                            </s:if>
                            <s:else>
                            	<select id="oggDomanda" class="form-control select2me" disabled>
                            </s:else>
                            
                            
                               <option selected value="<s:property value="#codIndet" />"><s:property value="indet" /></option>
                               		<s:iterator value="optionsDropDownCondivisi" var="listIndet">
                    		   		
                        				<option id="<s:property value="#listIndet.idTplTopologiche" />" 
                        				value="<s:property value="#listIndet.codifica" />">
                            			<s:property value="#listIndet.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                        	</select>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="carenza"  style="font-size: 13px; color: #888888; margin-top: -18px;">Carenza interesse ad agire (art. 100 c.p.c.)<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                           <s:set var="car" value="%{gestioneIStruttoria.carenzaInteresseAdAgire}" />
                           <s:set var="codCar" value="%{codificacar}" />
                            <s:if test="%{#yn != 'No'}">
                            	<select id="carenza" class="form-control select2me">
                            </s:if>
                            <s:else>
                            	<select id="carenza" class="form-control select2me" disabled>
                            </s:else>
                            
                            
                               <option selected value="<s:property value="#codCar" />"><s:property value="car" /></option>
                               		<s:iterator value="optionsDropDownCondivisi" var="listCar">
                    		   		
                        				<option id="<s:property value="#listCar.idTplTopologiche" />" 
                        				value="<s:property value="#listCar.codifica" />">
                            			<s:property value="#listCar.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                        	</select>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <span class="help-block" style="margin-top: 0;">
                        	Data di costituzione in giudizio 
                        </span>
                        <!--
                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                        <s:set var="dataCostGiud" value="%{gestioneIStruttoria.dataCostitGiudizio}" />
                            <input type="text" id="dataCostGiud" class="form-control" value="<s:date name="dataCostGiud" format="dd/MM/yyyy" />" readonly onchange="calcolaIntervalloTemporale('false')">
                            <span class="input-group-btn">
                             <s:if test="%{#yn == 'No'}">
                            	<button id="dataCostGiudButton" class="btn default" type="button" onclick="initCalendarDataCostGiud()"><i class="fa fa-calendar"></i></button>
                            </s:if>
                            <s:else>
                            	<button id="dataCostGiudButton" class="btn default" type="button" onclick="initCalendarDataCostGiud()" ><i class="fa fa-calendar"></i></button>
                            </s:else>
                            	
                            </span>
                        </div>                        
                        -->
                        <s:set var="dataCostGiud" value="%{gestioneIStruttoria.dataCostitGiudizio}" />
                        <s:if test="%{#yn != 'No'}">
                        	<input id="dataCostGiud"  onchange="calcolaIntervalloTemporale('false');checkCostInGiudizio()" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataCostGiud" format="dd/MM/yyyy" />">
                        </s:if>
                        <s:else>	
							<input id="dataCostGiud"  onchange="calcolaIntervalloTemporale('false');checkCostInGiudizio()" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataCostGiud" format="dd/MM/yyyy" />" >
						</s:else>		
                    </div>
                </div>
                <div class="row" style="margin-bottom: 30px;">
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="costGiudizio" style="font-size: 13px; color: #888888;">Costituzione in giudizio telematica<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                             <s:set var="costG" value="%{gestioneIStruttoria.costGiudTelematica}" />
                              <s:set var="codCostG" value="%{codificatel}" />
                             <s:if test="%{#yn != 'No'}">
                            	 <select id="costGiudizio" class="form-control select2me" onchange="checkDataCostituzioneGiudizio()">
                               		<option selected value="<s:property value="#codCostG" />"><s:property value="costG" /></option>
                               			<s:iterator value="yesNo" var="listSiNoG">
                    		   		
                        					<option id="<s:property value="#listSiNoG.idTplTopologiche" />" 
                        						value="<s:property value="#listSiNoG.codifica" />">
                            					<s:property value="#listSiNoG.descrizione" />
                            				</option>
                            		
                        				</s:iterator>
                        		</select>
                            </s:if>
                            <s:else>
                            	<select id="costGiudizio" class="form-control select2me" disabled onchange="checkDataCostituzioneGiudizio()">
                               		<option selected value="n">No</option>
                               		<s:iterator value="yesNo" var="listSiNoG">
                    		   		
                        					<option id="<s:property value="#listSiNoG.idTplTopologiche" />" 
                        						value="<s:property value="#listSiNoG.codifica" />">
                            					<s:property value="#listSiNoG.descrizione" />
                            				</option>
                            		
                        				</s:iterator>	
                        		</select>
                            </s:else>
                           
                        	
                        	
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <span class="help-block" style="margin-top: 0;">
                        	Data 1^ udienza 
                        </span>
                        <!--  
                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                         <s:set var="dataPrimaUdienza" value="%{gestioneIStruttoria.dataPrimaUdienza}" />
                            <input type="text" class="form-control" value="<s:date name="dataPrimaUdienza" format="dd/MM/yyyy" />" id="dataUdienza" readonly onchange="calcolaIntervalloTemporale('false')">
                            <span class="input-group-btn">
                            <s:if test="%{#yn == 'No'}">
                            	<button id="dataPUdButton" class="btn default" type="button" onclick="initCalendarDataUdienza()"><i class="fa fa-calendar"></i></button>
                            </s:if>
                            <s:else>
                            	<button id="dataPUdButton" class="btn default" type="button" onclick="initCalendarDataUdienza()" ><i class="fa fa-calendar"></i></button>
                            </s:else>
                            
                            
                            	
                            </span>
                        </div>
                        -->
                        <s:set var="dataPrimaUdienza" value="%{gestioneIStruttoria.dataPrimaUdienza}" />
                        <s:if test="%{#yn == 'No'}">
                        	<input id="dataUdienza"  onchange="calcolaIntervalloTemporale('false');" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataPrimaUdienza" format="dd/MM/yyyy" />" ">
                        </s:if>
                        <s:else>	
							<input id="dataUdienza"  onchange="calcolaIntervalloTemporale('false');" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataPrimaUdienza" format="dd/MM/yyyy" />" ">
						</s:else>	
                    </div>
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group form-md-line-input">
                         <s:set var="costInGiud" value="%{gestioneIStruttoria.intervalloCostGiudUdienza}" />
                            <input type="text" class="form-control" id="intTempCostInGiud" value="<s:property value="costInGiud"/> " style="height: 38px; text-align:right;" readonly>
                            <label for="form_control_1" style="margin-top: -36px;">Intervallo temporale (in giorni) da data costituzione in giudizio a data 1^ udienza
                            </label>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2"  style="font-size: 13px; color: #888888;">Altre eccezioni processuali<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                           <s:set var="eccProcess" value="%{gestioneIStruttoria.altreEccProcessuali}" />
                           <s:set var="codEccProcess" value="%{codifiAltreC}" />
                            
                           
                             <s:if test="%{#yn != 'No'}">
                            	 <select id="eccProcess" class="form-control select2me">
                            </s:if>
                            <s:else>
                            	<select id="eccProcess" class="form-control select2me" disabled>
                            </s:else>  
                               
                               <option selected value="<s:property value="#codEccProcess" />"><s:property value="eccProcess" /></option>
                               		<s:iterator value="optionsDropDownCondivisi" var="listEccp">
                    		   			
                        					<option id="<s:property value="#listEccp.idTplTopologiche" />" 
                        					value="<s:property value="#listEccp.codifica" />">
                            				<s:property value="#listEccp.descrizione" />
                            				</option>
                            		
                        		</s:iterator>
                        	</select>
                        </div>
                    </div>
                </div>
            </div>
            

      	<div class="form-actions noborder" style="padding-top: 0;">
      	 <!-- script infondo alla pagina -->
         <button type="button" class="btn blue" onclick="salvaGestioneIstruttoria();" <s:property value="salvataggioAbilitato" /> >Salva</button>
         <button type="button" class="btn default" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getGestioneIstruttoria', 'gestioneIstruttoria', null, null);" <s:property value="salvataggioAbilitato" /> >Annulla</button>
		</div>            
            
            
            
        </form>
        
    </div>

   
    
    
