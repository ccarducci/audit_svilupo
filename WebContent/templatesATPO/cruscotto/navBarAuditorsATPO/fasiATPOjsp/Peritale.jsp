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
	
    <!-- Peritale -->
    <div class="portlet-body form">
        <form role="form">
            <div class="form-body">
                <div class="row" style="margin-bottom: 40px;">
                    <div class="col-md-3 col-xs-12">
                        <span class="help-block" style="margin-top: -18px;">
                        	Data comunicazione inizio operazioni peritali da parte del CTU 
                        </span>
                        <!--<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                        <s:set var="dataComInizioOpPer" value="%{peritale.dataComOpPerCTU}" />
                        
                            <input type="text" class="form-control" id="dataComInizioOpPer" value="<s:date name="dataComInizioOpPer" format="dd/MM/yyyy" />" readonly>
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataComInizioOpPer()"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div>-->
                        <input id="dataComInizioOpPer" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataComInizioOpPer" format="dd/MM/yyyy" />" ">
                        
                    </div>
                    <div class="col-md-3 col-xs-12">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2" style="font-size: 13px; color: #888888; margin-top: -18px;">Registrazione informazioni operazioni peritali<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="recInfoOpPeritali" value="%{peritale.recInfoOpPeritali}" />
                            <s:set var="codRecInfoOpPeritali" value="%{recInfo}" />
                            
                            <select id="recOp" class="form-control select2me">
                                <option selected value="<s:property value="#codRecInfoOpPeritali" />"><s:property value="recInfoOpPeritali"/></option>
                                <s:iterator value="optionsRegInfo" var="recInfoP">
                    		   		
                        				<option id="<s:property value="#recInfoP.idTplTopologiche" />" value="<s:property value="#recInfoP.codifica" />">
                            				<s:property value="#recInfoP.descrizione" />
                            			</option>
                            	
                        		</s:iterator>
                            </select>
                            
                            
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2" style="font-size: 13px; color: #888888;">Assegnazione CTU a medico INPS<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="assCTU" value="%{peritale.assCTUMedicoInps}" />
                             <s:set var="codAssCTU" value="%{assegnCtuInps}" />
                            <select id="medicoINPS" class="form-control select2me">
                                <option selected value="<s:property value="#codAssCTU" />"><s:property value="assCTU" /></option>
                                <s:iterator value="optionsSiNoPeritale" var="listSN">
                    		   		
                        				<option id="<s:property value="#listSN.idTplTopologiche" />" value="<s:property value="#listSN.codifica" />">
                            				<s:property value="#listSN.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2" style="font-size: 13px; color: #888888;">Presenza CTP INPS operazioni peritali<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="opPeritali" value="%{peritale.ctpINPSopPeritali}" />
                            <s:set var="codOpPeritali" value="%{presenzaCTPINPS}" />
                            <select id="presCTPop" class="form-control select2me">
                               <option selected value="<s:property value="#codOpPeritali" />"><s:property value="opPeritali" /></option>
                                <s:iterator value="optionsSiNoPeritale" var="listSNF">
                    		   		
                        				<option id="<s:property value="#listSNF.idTplTopologiche" />" value="<s:property value="#listSNF.codifica" />">
                            				<s:property value="#listSNF.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 40px;">
                    
                    
                       <div class="col-md-3 col-xs-12">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2" style="font-size: 13px; color: #888888; margin-top: -18px;">Presenza medico INPS da documentazione CTU<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="presMedInpsDoc" value="%{peritale.presMedicoInpsDoc}" />
                            <s:set var="codPresMedicoInpsDoc" value="%{presMedicoInpsDoc}" />
                            
                            <select id="presMedDoc" class="form-control select2me">
                                <option selected value="<s:property value="#codPresMedicoInpsDoc" />"><s:property value="presMedInpsDoc"/></option>
                                <s:iterator value="optionsSiNoPeritale" var="lSN">
                    		   		
                        				<option id="<s:property value="#lSN.idTplTopologiche" />" value="<s:property value="#lSN.codifica" />">
                            				<s:property value="#lSN.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                            </select>
                            
                            
                        </div>
                    </div>
                    
                    
                    <div class="col-md-3 col-xs-12">
                        <span class="help-block" style="margin-top: 0;">
                        	 Data arrivo bozza 
                        </span>
                        <!--
                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                        <s:set var="dataBozza" value="%{peritale.dataArrBozza}" />
                            <input type="text" id="dataBozza" class="form-control" value="<s:date name="dataBozza" format="dd/MM/yyyy" />"readonly>
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataBozza()"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div> -->
                        <input id="dataBozza" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataBozza" format="dd/MM/yyyy" />" ">
                        
                    </div>
                    <div class="col-md-3 col-xs-12">
                        <span class="help-block" style="margin-top: 0;">
                        	Data protocollo bozza 
                        </span>
                        <!--<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                         <s:set var="dataProtBozza" value="%{peritale.dataProtBozza}" />
                            <input type="text" class="form-control" value="<s:date name="dataProtBozza" format="dd/MM/yyyy" />" id="dataProtBozza" readonly>
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataProtBozza()"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div>
                        -->
                        <input id="dataProtBozza" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataProtBozza" format="dd/MM/yyyy" />" ">
                        
                    </div>
                    <div class="col-md-3 col-xs-12">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2" style="font-size: 13px; color: #888888;">CTU bozza con immagine agli atti<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                              <s:set var="bozzaConImg" value="%{peritale.ctuBozzaImgAtti}" />
                              <s:set var="codCtDfImgAtti" value="%{codCtuDefImgAtti}" />
                            <select id="bozzaConImg" class="form-control select2me">
                                <option selected value="<s:property value="#codCtDfImgAtti" />"><s:property value="bozzaConImg" /></option>
                               <s:iterator value="optionsSiNoPeritale" var="ctuBozzImgAtti">
                    		   		
                        				<option id="<s:property value="#ctuBozzImgAtti.idTplTopologiche" />"  value="<s:property value="#ctuBozzImgAtti.codifica" />">
                            				<s:property value="#ctuBozzImgAtti.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                        		
                            </select>
                        </div>
                    </div>
                    
                </div>
                <div class="row" style="margin-bottom: 40px;">
                
                	<div class="col-md-3 col-xs-12">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2" style="font-size: 13px; color: #888888;">Parere su bozza CTU<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="parereBozzaCtu" value="%{peritale.parereBozzaCtu}" />
                            <s:set var="codParereBozzaCtu" value="%{codParereBozzaCtu}" />
                            
                            <select id="parereBozzaCtu" class="form-control select2me">
                                <option selected value="<s:property value="#codParereBozzaCtu" />"><s:property value="parereBozzaCtu" /></option>
                               <s:iterator value="optionsPareBozzaCtu" var="listParBozza">
                    		   		
                        				<option id="<s:property value="#listOptD.idTplTopologiche" />"  value="<s:property value="#listParBozza.codifica" />">
                            				<s:property value="#listParBozza.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                            </select>
                        </div>
                    </div>
                
                
                
                    <div class="col-md-3 col-xs-12">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2" style="font-size: 13px; color: #888888;">Osservazioni su bozza<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                             <s:set var="ossBozza" value="%{peritale.ossBozza}" />
                              <s:set var="codOssBozza" value="%{codOssBozza}" />
                              
                            <select id="ossBozza" class="form-control select2me">
                                <option selected value="<s:property value="#codOssBozza" />"><s:property value="ossBozza" /></option>
                               <s:iterator value="optionsSiNoPeritale" var="listSNP">
                    		   		
                        				<option id="<s:property value="#listSNP.idTplTopologiche" />"  value="<s:property value="#listSNP.codifica" />">
                            				<s:property value="#listSNP.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12">
                        <span class="help-block" style="margin-top: 0;">Data deposito CTU definitiva</span>
                        <!--<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                        <s:set var="dataCom" value="%{peritale.dataComDepCTUDefCanc}" />
                            <input type="text" class="form-control" id="dataComunicazione" value="<s:date name="dataCom" format="dd/MM/yyyy" />" readonly onchange="calcolaIntTempPeritale('false')"> 
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataComunicazione()"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div>
                        -->
                        <input id="dataComunicazione" onchange="calcolaIntTempPeritale('false')" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataCom" format="dd/MM/yyyy" />" ">
                        
                    </div>
                    <div class="col-md-3 col-xs-12">
                        <span class="help-block" style="margin-top: 0;">
                        	Data protocollo CTU definitiva 
                        </span>
                        <!--<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                         <s:set var="dataProtCTU" value="%{peritale.dataProtCTUDef}" />
                            <input type="text" class="form-control" id="dataProtCTU" value="<s:date name="dataProtCTU" format="dd/MM/yyyy" />" readonly onchange="calcolaIntTempPeritale('false')">
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataProtCTU()"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div>                      
                        -->
                        <input id="dataProtCTU" onchange="calcolaIntTempPeritale('false')" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataProtCTU" format="dd/MM/yyyy" />" ">
                        
                    </div>
                    
                </div>
                <div class="row" style="margin-bottom: 40px;">
                
                <div class="col-md-3 col-xs-12">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2" style="font-size: 13px; color: #888888;">CTU definitiva con immagine agli atti<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="ctuDefImgAtti" value="%{peritale.ctuDefImgAtti}" />
                            <s:set var="codCtuDefImgAtti" value="%{codCtuDefImgAtti}" />
                            <select id="ctuDefImgAtti" class="form-control select2me">
                                <option selected value="<s:property value="#codCtuDefImgAtti" />"><s:property value="ctuDefImgAtti" /></option>
                                <s:iterator value="optionsSiNoPeritale" var="listctuDefImgAtti">
                    		   		
                        				<option id="<s:property value="#listOptD.idTplTopologiche" />"  value="<s:property value="#listctuDefImgAtti.codifica" />">
                            				<s:property value="#listctuDefImgAtti.descrizione" />
                            			</option>
                            		
                        		</s:iterator>
                            </select>
                        </div>
                    </div>
                
                
                    <div class="col-md-3 col-xs-12">
                        <div class="form-group form-md-line-input">
                         <s:set var="intTempDepCTU" value="%{peritale.intTempComDepCTUtoCTUdef}" />
                          
                            <input type="text" class="form-control" id="intTempDepCTU" 
                            value="<s:property value="intTempDepCTU" />" style="height: 38px; text-align:right;" readonly>
                            <label for="form_control_1" style="    margin-top: -33px; font-size: 12px;">Intervallo temporale da data
                                <br> DEPOSITO CTU definitiva in Cancelleria
                                <br>a data protocollo CTU definitiva
                                <!--<i class="fa fa-asterisk obbligatorio"></i>--></label>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-12">
                        <span class="help-block" style="margin-top: 0;">
                        	Data termine DISSENSO in SISCO 
                        </span>
                        <!--<div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                         <s:set var="dataTermDissSisco" value="%{peritale.dataTermDissSisco}" />
                            <input type="text" class="form-control" id="dataTermDissSisco" value="<s:date name="dataTermDissSisco" format="dd/MM/yyyy" />" readonly>
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataTermDissSisco()"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div> -->
                        <input id="dataTermDissSisco"  class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataTermDissSisco" format="dd/MM/yyyy" />" ">
                        
                   </div>
                    <div class="col-md-3 col-xs-12">
                        <span class="help-block" style="margin-top: 0;">
                        	Data termine DISSENSO da decreto 
                        </span>
                        <!--
                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy">
                        	 <s:set var="dataTermDissDecr" value="%{peritale.dataTermDissDecr}" />
                            <input type="text" class="form-control" id="dataTermDissDecr" value="<s:date name="dataTermDissDecr" format="dd/MM/yyyy" />" readonly>
                            <span class="input-group-btn">
                            	<button class="btn default" type="button" onclick="initDataTermDissDecr()"><i class="fa fa-calendar"></i></button>
                            </span>
                        </div>
                        -->
                        <input id="dataTermDissDecr"  class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataTermDissDecr" format="dd/MM/yyyy" />" ">
            
                    </div>
                    
                </div>
                <div class="row" style="margin-bottom: 30px;">
                
                <div class="col-md-3 col-xs-12">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2" style="font-size: 13px; color: #888888;">Parere sanitario di dissenso/accettazione<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="parereDissAccettazione" value="%{peritale.parereDissAccetfascitazione}" />
                             <s:set var="codParereDissAccettazione" value="%{dissensAccett}" />
                           
                            <select id="parereDissAccettazione" class="form-control select2me">
                             <option selected value="<s:property value="#codParereDissAccettazione" />"><s:property value="parereDissAccettazione" /></option>
                                <s:iterator value="optionsPareBozzaCtu" var="listparereDissAccettazione">
                    		   		
                        				<option id="<s:property value="#listparereDissAccettazione.idTplTopologiche" />"  value="<s:property value="#listparereDissAccettazione.codifica" />">
                            				<s:property value="#listparereDissAccettazione.descrizione" />
                            			</option>
                            		
                            	</s:iterator>
                            </select>
                        </div>
                    </div>
                
                	
                
                    <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="form_control_2" style="font-size: 13px; color: #888888;">Osservazioni su parere definitivo<i class="fa fa-asterisk obbligatorio"></i></label>
                            <br>
                            <s:set var="ossParDef" value="%{peritale.ossParDef}" />
                            <s:set var="codOssParDef" value="%{ossParereDef}" />
                            
                            <select id="ossParDef" class="form-control select2me">
                             <option selected value="<s:property value="#codOssParDef" />"><s:property value="ossParDef" /></option>
                                <s:iterator value="optionsSiNoPeritale" var="listossParDef">
                    		   		
                        				<option id="<s:property value="#listossParDef.idTplTopologiche" />"  value="<s:property value="#listossParDef.codifica" />">
                            				<s:property value="#listossParDef.descrizione" />
                            			</option>
                            		
                            	</s:iterator>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
           	<div class="form-actions noborder" style="padding-top: 0;">
                	<button type="button" class="btn blue" onclick="salvaPeritale()" <s:property value="salvataggioAbilitato" /> >Salva</button>
                	<button type="button" class="btn default" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getPeritale', 'peritale', null, null);" <s:property value="salvataggioAbilitato" /> >Annulla</button>
            </div>

        </form>
    </div>
    