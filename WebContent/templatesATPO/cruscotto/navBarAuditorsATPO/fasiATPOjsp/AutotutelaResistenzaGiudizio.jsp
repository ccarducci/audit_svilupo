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

<!-- Autotutela/Resistenza in giudizio -->
<div class="portlet-body form">
   <form role="form">
      <div class="form-body">
         <div class="row" style="margin-bottom: 30px;">
            <div class="col-md-6 col-xs-12" style="margin-bottom: 15px;">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="parereOptions" style="font-size: 13px; color: #888888;">Parere<i class="fa fa-asterisk obbligatorio"></i></label><br>
                  <s:set var="par" value="%{autotuResGiudizio.parere.trim()}" />
                  <s:set var="codPar" value="%{codifParere.trim()}" />
                  
                  <select id="parereOptions" class="form-control select2me" onchange="checkDefTermini()">
                     <option selected value="<s:property value="#codPar" />"> <s:property value="par" />
                     </option>
                     <s:iterator value="optionsParere" var="list">
                        
                           <option id="<s:property value="#list.idTplTopologiche" />" value="<s:property value="#list.codifica" />">
                           		<s:property value="#list.descrizione" />
                           </option>
                        
                     </s:iterator>
                  </select>
               </div>
            </div>
            <div class="col-md-6 col-xs-12" style="margin-bottom: 15px;">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="terminiPrimaUdienza" style="font-size: 13px; color: #888888;">Definizione nei termini entro la 1^ udienza del procedimento di autotutela<i class="fa fa-asterisk obbligatorio"></i></label>
                 
                  <s:set var="procAuto" value="%{autotuResGiudizio.terminiPrimaUdienza}" />
                  <s:set var="codTerm" value="%{defTerminiEntroPudienza}" />
                  <s:if test="%{#codPar == 2 }">
                       <select id="terminiPrimaUdienza" class="form-control select2me">
                  </s:if>
                  <s:else>
                       <select id="terminiPrimaUdienza" class="form-control select2me" disabled>
                  </s:else>
                  
                     <option selected value="<s:property value="#codTerm" />"> <s:property value="procAuto" /> </option>
                     <s:iterator value="optionsTerminiUdienza" var="listT">
                      
                           <option id=" <s:property value="#listT.idTplTopologiche" /> " value="<s:property value="#listT.codifica" />  ">
                           		<s:property value="#listT.descrizione" />
                           </option>
                        
                     </s:iterator>
                  </select>
               </div>
            </div>
         </div>
      </div>
      
		<div class="form-actions noborder" style="padding-top: 0;">
		     	 <!-- script infondo alla pagina -->
		        <button type="button" class="btn blue" onclick="salvaAutotutelaResistenzaGiudizio()" <s:property value="salvataggioAbilitato" /> >Salva</button>
		        <button type="button" class="btn default" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getAutotutelaResInGiudizio', 'autotutelaResGiudizio', null, null)" <s:property value="salvataggioAbilitato" /> >Annulla</button>
		</div>
      
      
      
      
    
   </form>
</div>
