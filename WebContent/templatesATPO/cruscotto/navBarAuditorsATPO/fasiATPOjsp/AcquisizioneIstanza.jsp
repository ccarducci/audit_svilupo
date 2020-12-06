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

<!-- Acquisizione istanza -->
<div class="portlet-body form">
   <form role="form">
      <div class="form-body">
         <div class="row" style="margin-bottom: 40px;">
            
           
            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
            <s:set var="dp" value="%{acquisizioneIstanza.dataProtocollo}" />
               <span class="help-block" style="margin-top: 0;"> Data protocollo </span>
               <!-- 
               <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                  <input type="text" id="dataProt" class="form-control" value="<s:date name="dp" format="dd/MM/yyyy" />" readonly onchange="calcolaIntervalloDpDn('true');">
                  <span class="input-group-btn">
                  <button class="btn default" type="button" onclick="initCalendarDataProtocollo()"><i class="fa fa-calendar"></i></button>
                  </span>
               </div>
                -->
               
              
				<input id="dataProt"  onchange="calcolaIntervalloDpDn('true'); checkProtConImg()" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dp" format="dd/MM/yyyy" />" ">
					
				
            </div>
           
           
           
            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
             <s:set var="dn" value="%{acquisizioneIstanza.dataNotifica}" />
               <span class="help-block" style="margin-top: 0;"> Data notifica </span>
               <!-- 
               <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                  <input type="text" id="dataNotif" class="form-control" value="<s:date name="dn" format="dd/MM/yyyy" />" readonly onchange="calcolaIntervalloDpDn('true'); calcolaIntDnDa('true');">
                  <span class="input-group-btn">
                  <button class="btn default" type="button" onclick="initCalendarDataNotifica()"><i class="fa fa-calendar"></i></button>
                  </span>
               </div>
               -->
               <input id="dataNotif"  onchange="calcolaIntervalloDpDn('true'); calcolaIntDnDa('true');" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dn" format="dd/MM/yyyy" />" ">
				
            </div>
            
            
            
            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="form_control_2" style="font-size: 13px; color: #888888;">Voce titolario<i class="fa fa-asterisk obbligatorio"></i></label><br>
                  <s:set var="voceTi" value="%{acquisizioneIstanza.voceTitolarioErrata}" />
                   <s:set var="codVoceTi" value="%{codificaVoceTitolario}" />
                  
                  <s:if test="%{#dp != null}">
                  	<select id="voceTit" class="form-control select2me" >
                  </s:if>
                  <s:else>
                  	<select id="voceTit" class="form-control select2me" disabled>
                  </s:else>
                  		<option selected value="<s:property value="#codVoceTi" />"><s:property value="voceTi" /></option>
                    	<s:iterator value="vocetitolario" var="listV">
                    		
                        	<option id="<s:property value="#listV.idTplTopologiche" />" 
                        	value="<s:property value="#listV.codifica" />">
                            	<s:property value="#listV.descrizione" />
                            </option>
                          
                        </s:iterator>
                 </select>
                  
               </div>
            </div>
            
            
            
            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
               <div class="form-group form-md-line-input">
                <s:set var="intTemp" value="%{acquisizioneIstanza.intervalloNotificaProtocollo}" />
                  <input type="text" class="form-control" id="intTempProt" 
                   value="<s:property value="intTemp" />" style="height: 38px; text-align:right;" disabled>
                  <label for="form_control_1" style="    margin-top: -18px;">
                     Intervallo temporale da data notifica a data protocollo 
                  </label>
               </div>
            </div>
         </div>
         <div class="row" style="margin-bottom: 30px;">
            <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="form_control_2" style="font-size: 13px; color: #888888;">Protocollo con immagine<i class="fa fa-asterisk obbligatorio"></i></i></label><br>
                   <s:set var="pconi" value="%{acquisizioneIstanza.protocolloConImg}" />
                    <s:set var="codPconi" value="%{codificaProtImg}" />
                   <s:if test="%{#dp != null}">
                   <select id="tipo-report-iscP" class="form-control select2me"  onchange="checkDataProtocollo()">
                  </s:if>
                  <s:else>
                  	 <select id="tipo-report-iscP" class="form-control select2me" disabled onchange="checkDataProtocollo()">
                  </s:else>
                  		
                  		<option selected value="<s:property value="#codPconi" />"><s:property value="pconi"/></option>
                    	<s:iterator value="siNo" var="listsiNo">
                    	
                        	<option id="<s:property value="#listsiNo.idTplTopologiche" />" 
                        	value="<s:property value="#listsiNo.codifica" />">
                            	<s:property value="#listsiNo.descrizione" />
                            </option>
                           
                        </s:iterator>
                 </select>
                  
               </div>
            </div>
            <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
               <span class="help-block" style="margin-top: 0;">Data acquisizione in SISCO </span>
                <s:set var="dataSisco" value="%{acquisizioneIstanza.dataAcquisizioneSISCO}" />
                <!--           
               <div class="input-group input-large date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                  <input type="text" id="acquSisco" class="form-control" value="<s:date name="dataSisco" format="dd/MM/yyyy" />" readonly onchange="calcolaIntDnDa('true');">
                  <span class="input-group-btn">
                  <button class="btn default" type="button" onclick="initCalendarAcquSisco()"><i class="fa fa-calendar"></i></button>
                  </span>
               </div>
               -->
               <input id="acquSisco"  onchange="calcolaIntDnDa('true');" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="<s:date name="dataSisco" format="dd/MM/yyyy" />" ">
				
            </div>
            
            <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
               <div class="form-group form-md-line-input">
                 <s:set var="acquisSisco" value="%{acquisizioneIstanza.intervalloNotificaSISCO}" />
                  <input type="text" class="form-control" id="intNotifSisco" value="<s:property value="acquisSisco" />" style="height: 38px; text-align:right;" disabled>
                  <label for="form_control_1" style=" margin-top: -18px;">
                     Intervallo temporale da data notifica a data acquisizione in SISCO <!--<i class="fa fa-asterisk obbligatorio"></i>-->
                  </label>
               </div>
            </div>
         </div>
      </div>
      <div class="form-actions noborder" style="padding-top: 0;">
      
		 <div class="form-actions noborder" style="padding-top: 0;">
	      	 <button id="salvaAi" type="button" class="btn blue" onclick="salvaAcquisizioneDati();" <s:property value="salvataggioAbilitato" /> >Salva</button>
	         <button type="button" class="btn default" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getAcquisizioneIstanza', 'acquisizioneIstanza', null, null)" <s:property value="salvataggioAbilitato" /> >Annulla</button>
	     </div> 
         
      </div>
   </form>
</div>

