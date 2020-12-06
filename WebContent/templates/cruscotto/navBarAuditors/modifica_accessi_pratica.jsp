<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="it.tecnet.crs.session.DatiUtente"%>
<!-- **
   QUESTA PAGINA è ACCESSIBILE DAL BUTTON MODIFICA DEL TAB PRATICHE
   ** -->
<!-- BEGIN CONTENT -->

      <!-- BEGIN PAGE HEAD -->
      <div class="page-head">
         <!-- BEGIN PAGE TITLE -->
         <div class="page-title">
            <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
            <h1>Accessi - Pratica <small>Sede di <s:property value="%{nomeSede}" /></small></h1>
         </div>
         <!-- BEGIN PAGE BREADCRUMB -->
         <ul class="page-breadcrumb breadcrumb">
            <li>Auditors <i class="fa fa-circle"></i></li>
            <li>
               <!-- cruscottoScript.js --> <a href="#"
                  onclick="clickMenu('auditors-accessi'); loadDiv('/CruscottoAuditAtpoWebWeb/accessi', 'appView', null, 'initAuditors' );">
               Accessi</a> <i class="fa fa-circle"></i>
            </li>
            <li>
               <%
                  DatiUtente datiUtenteSession = (DatiUtente) request.getSession()
                  		.getAttribute("DatiUtente");
                  long idSessione = datiUtenteSession.getIdSessione();%> 
                  <!-- cruscottoScript.js -->
               <!--  <a href="#" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/templates/cruscotto/navBarAuditors/modifica-accessi.jsp', 'appView', null, 'initTablesModificaAuditor' );">-->
               <a href="#" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/modificaAccesso?idSessione= <%=idSessione%>' , 'appView', null, 'initTablesModificaAuditor' );">
               Modifica Accessi</a> <i class="fa fa-circle"></i>
            </li>
            <li>Pratica</li>
         </ul>
         <!-- END PAGE BREADCRUMB --> <!-- END PAGE TITLE -->
      </div>
      <!-- END PAGE HEAD --> <!-- BEGIN PAGE CONTENT INNER -->
      <div class="row margin-top-10">
         <div class="col-md-12">
            <!-- BEGIN SAMPLE TABLE PORTLET-->
            <div class="portlet">
               <div class="portlet-body">
                  <div class="row">
                     <div class="col-md-12 ">
                        <!-- BEGIN Portlet PORTLET-->
                        <div class="portlet light accessori">
                           <div class="portlet-title tabbable-line">
                              <ul class="nav nav-tabs">
                                 <li class="active">
                                    <!-- TAB 1 --> <a href="#portlet_tab1"
                                       data-toggle="tab"> Dati generali </a>
                                 </li>
                                 <li>
                                    <!-- TAB 2 --> <a href="#portlet_tab2" data-toggle="tab">
                                    Fase Notifica </a>
                                 </li>
                                 <li>
                                    <!-- TAB 3 --> <a href="#portlet_tab3" data-toggle="tab">
                                    Fase Definizione </a>
                                 </li>
                                 <li>
                                     <a href="#portlet_tab_rischi" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getTabRischioPratiche', 'portlet_tab_rischi', null, 'initRischiPraticaTable');" data-toggle="tab">
                                    Rischi </a>
                                 </li>
                                 <li>
                                     <a href="#portlet_tab_non_conformita" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getTabNonConformitaPratiche', 'portlet_tab_non_conformita', null, 'initNonConformitaPratiche');" data-toggle="tab">
                                    Non Conformita </a>
                                 </li>
								 <li>
                                  <a href="#portlet_tab_dati_calcolati" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getTabDatiCalcolatiPratica', 'portlet_tab_dati_calcolati', null, null);" data-toggle="tab"> Dati calcolati </a>
                                 </li>
                              </ul>
                           </div>
                           <div class="portlet-body">
                              <div class="tab-content">
                                 <!-- ************************************************************** TAB 1 (Dati generali)********************************* -->
                                 <div class="tab-pane active" id="portlet_tab1">
                                    <div class="portlet light">
                                       <div class="portlet-body">
                                         <div class="row" style="margin-bottom: 30px;">
  												<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
  												       <div class="form-group form-md-line-input">
  												        	
  												        		<s:set var="statoEsamePratica" value="%{pratica.esamePratica}" />
			                                         			
					  											<select id="statoEsamePratica" disabled="disabled" class="form-control select2me">
															
																<s:if test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticheUtil@ESAME_PRATICA_APERTO}'>
																	<s:set var="pulsanteIndicatoriDisabilitato" value="%{''}" />
																	<option id="esamePraticaAperta" selected value="<s:property value="@it.tecnet.crs.util.PraticheUtil@ESAME_PRATICA_APERTO" />">Aperta</option>
					  											 	<option id="esamePraticaChiusa" value="<s:property value="@it.tecnet.crs.util.PraticheUtil@ESAME_PRATICA_CHIUSO" />">Chiusa</option>
																</s:if>
															
																<s:if test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticheUtil@ESAME_PRATICA_CHIUSO}'>
																   	<option id="esamePraticaAperta" value="<s:property value="@it.tecnet.crs.util.PraticheUtil@ESAME_PRATICA_APERTO" />">Aperta</option>
	                    								         	<option id="esamePraticaChiusa" selected value="<s:property value="@it.tecnet.crs.util.PraticheUtil@ESAME_PRATICA_CHIUSO" />">Chiusa</option>
																</s:if>
	                  										
	                  										</select>
	                  										
	                                                        <label for="form_control_1">Stato Esame Pratica</label>
                                                      </div>
  												</div>
												<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
													<div class="form-group form-md-line-input">
												 		<label for="form_control_1"></label>
												 		<button id="CalcolaIndicatoriPratica" type="button" class="btn blue" <s:property value="pulsanteIndicatoriDisabilitato" /> onclick="CalcolaIndicatoriPratica(this)">Chiudi Pratica</button>
                                                    </div>
												</div>
												<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;"></div>
                                       	 </div>
                                          <div class="row" style="margin-bottom: 30px;">
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                                   <s:set var="idV" value="%{verbale.idVerbale}" />
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="idV" />" style="height: 38px;"> 
                                                      <label for="form_control_1">Protocollo</label>
                                                </div>
                                             </div>
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                                   <s:set
                                                      var="ragioneSociale" value="%{verbale.ragioneSociale}" />
                                                   <input
                                                      type="text" class="form-control" readonly id="form_control_1"
                                                      value="<s:property value="ragioneSociale" />" style="height: 38px;">
                                                   <label for="form_control_1">Azienda</label>
                                                </div>
                                             </div>
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                                   <s:set
                                                      var="fineIspezione" value="%{verbale.dataFineIspezione}" />
                                                   <input
                                                      type="text" class="form-control" readonly id="form_control_1"
                                                      value="<s:property value="fineIspezione" />" style="height: 38px;">
                                                   <label for="form_control_1">Data Fine Ispezione</label>
                                                </div>
                                             </div>
                                          </div>
                                          <div class="row" style="margin-bottom: 30px;">
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                                   <s:set
                                                      var="dataNotificaVW" value="%{verbale.dataNotificaVW}" />
                                                   <input
                                                      type="text" class="form-control" readonly id="form_control_1"
                                                      value="<s:property value="dataNotificaVW" />" style="height: 38px;">
                                                   <label for="form_control_1">Data Notifica</label>
                                                </div>
                                             </div>
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                                   <s:set var="tipoNotifica" value="%{verbale.tipoNotifica}" />
                                                   <input
                                                      type="text" class="form-control" readonly id="form_control_1"
                                                      value="<s:property value="tipoNotifica" />" style="height: 38px;">
                                                   <label for="form_control_1">Modalita Notifica in Procedura</label>
                                                </div>
                                             </div>
                                              <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                               <s:set var="np" value="%{verbale.numeroProtocollo}" />
                                               
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="np" />" style="height: 38px;">
                                                   <label for="form_control_1">Data Protocollo</label>
                                                </div>
                                             </div>
                                          </div>
                                          <div class="row" style="margin-bottom: 30px;">
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                               <s:set var="din" value="%{verbale.dataInserimentoNotifica}" />
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="din" />
                                                   " style="height: 38px;">
                                                   <label for="form_control_1">Data Inserimento Notifica</label>
                                                </div>
                                             </div>
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                               <s:set var="dcv" value="%{verbale.dataChiusuraVerbale}" />
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="dcv" />" style="height: 38px;">
                                                   <label for="form_control_1">Data Chiusura Verbale</label>
                                                </div>
                                             </div>
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                               <s:set var="ef" value="%{verbale.esitoFlusso}" />
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="ef" />" style="height: 38px;">
                                                   <label for="form_control_1">Esito Regolarizzazione In Procedura</label>
                                                </div>
                                             </div>
                                           </div>
                                           <div class="row" style="margin-bottom: 30px;">

                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                               <s:set var="dvw" value="%{verbale.destinatarioVW}" />
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="dvw" />" style="height: 38px;">
                                                   <label for="form_control_1">Destinatario Notifica</label>
                                                </div>
                                             </div>
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                               <s:set var="posvw" value="%{verbale.presenzaObbligatiSolidaliVw}" />
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="posvw" />" style="height: 38px;">
                                                   <label for="form_control_1">Presenza Obbligati Solidali</label>
                                                </div>
                                             </div>
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                               <s:set var="cs" value="%{verbale.codiceSospensione}" />
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="cs" />" style="height: 38px;">
                                                   <label for="form_control_1">Codice Sospensione</label>
                                                </div>
                                             </div>
                                           </div>
                                           <div class="row" style="margin-bottom: 30px;">
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                               <s:set var="ic" value="%{verbale.contributiPratica}" />
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="ic" />" style="height: 38px;">
                                                   <label for="form_control_1">Importo Credito</label>
                                                </div>
                                             </div>
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                               <s:set var="s" value="%{verbale.sanzione}" />
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="s" />" style="height: 38px;">
                                                   <label for="form_control_1">Importo Sanzioni</label>
                                                </div>
                                             </div>
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                                   <s:set var="dtl" value="%{verbale.dataTrasmissioneLegale}" />
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="dtl" />" style="height: 38px;">
                                                   <label for="form_control_1">Data Trasmissione al Legale</label>
                                                </div>
                                             </div>
                                           </div>
                                           <div class="row" style="margin-bottom: 30px;">
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                               <s:set var="niu" value="%{verbale.niu}" />
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="niu" />" style="height: 38px;">
                                                   <label for="form_control_1">Numero verbale </label>
                                                </div>
                                             </div>
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                               <s:set var="matricola" value="%{verbale.matricola}" />
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="matricola" />" style="height: 38px;">
                                                   <label for="form_control_1">Matricola</label>
                                                </div>
                                             </div>
                                             <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                <div class="form-group form-md-line-input">
                                               <s:set var="esitoAccertamento" value="%{verbale.esitoAccertamento}" />
                                                   <input type="text" class="form-control" readonly id="form_control_1" value="<s:property value="esitoAccertamento" />" style="height: 38px;">
                                                   <label for="form_control_1">Tipologia verbale ispettivo</label>
                                                </div>
                                             </div>
                                           </div>
                                          </div>
                                       </div>
                                    </div>
                                 
                                 <!-- **************************************       TAB 2 (Fase Notifica) ***********************************************************-->
                                 <div class="tab-pane" id="portlet_tab2">
                                  
                                    <div class="portlet light">
                                       <div class="portlet-body form">
                                          <form role="form">
                                             <div class="form-body">
                                                <div class="row" style="margin-bottom: 30px;">
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input">
                                                         <s:set
                                                            var="fineIspezione" value="%{verbale.dataFineIspezione}" />
                                                         <input type="text" class="form-control"  id="dataFineIspezioneFN" value="<s:date name="fineIspezione" format="dd/MM/yyyy" />" style="height: 38px;" readonly>
                                                         <label for="form_control_1">Data Fine Ispezione <i class="fa fa-asterisk obbligatorio"></i>
                                                         </label>
                                                      </div>
                                                   </div>
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input">
                                                         <s:set var="pec" value="%{faseNotifica.indirizzoPecReale}" />
                                                         <s:if test="%{#pec == NULL ">
                                                         	<s:set var="pecV" value="%{verbale.indirizzoPecVW}" />
                                                         	 <input type="text" class="form-control" id="pecFN" value="<s:property value="pecV" />"style="height: 38px;"> 
                                                         	<label for="form_control_1"> PEC
                                                         </s:if>
                                                         <s:else>
     														<input type="text" class="form-control" id="pecFN" value="<s:property value="pec" />"style="height: 38px;"> 
                                                         	<label for="form_control_1"> PEC
														</s:else>
                                                      </div>
                                                   </div>
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input">
                                                         <s:set var="destinatarioNotifica" value="%{faseNotifica.destinatarioReale}" />
                  
				  										<select id="destinatarioNotifica" class="form-control select2me">
                    										<s:if test="%{#destinatarioNotifica == null">
																
																<s:set var="destinatarioNotifica" value="%{verbale.destinatarioVW}" />
																<option selected><s:property value="destinatarioNotifica"/></option>
                   											</s:if>
															<s:else>
																<option selected><s:property value="destinatarioNotifica"/></option>
															</s:else>
															<s:iterator value="correttoErrato" var="ce">
                			
																<s:if test="%{#destinatarioNotifica == NULL || #ce.valore.trim() != #destinatarioNotifica.trim()}">
                    												<option id="<s:property value="#ce.id" />" value="<s:property value="#ce.id" />">
                        												<s:property value="#ce.valore" />
                   													</option>
                												</s:if>
            												</s:iterator>
                  										</select>
                                                         <label for="form_control_1">Destinatario Notifica Corretto</label>
                                                            
                                                      </div>
                                                   </div>
                                                </div>
                                                <div class="row" style="margin-bottom: 30px;">
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      
                                                         <div class="form-group form-md-line-input">
                                                         <s:set var="modalNotif" value="%{faseNotifica.modalitaNotifica}" />
                  
				 											 <select id="modalitaNotifica" class="form-control select2me">
																	<option selected><s:property value="modalNotif"/></option>
                    											
																	<s:iterator value="modalitaNotifica" var="mn">
                			
																		
                    														<option id="<s:property value="#mn.idTplTopologiche" />" value="<s:property value="#mn.statoProcesso" />">
                        														<s:property value="#mn.statoProcesso" />
                   															</option>
                														
            														</s:iterator>
                  											</select>
                                                         	<label for="form_control_1"> Modalita Notifica</label>
                                                     	 </div>
                                                      
                                                   </div>
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input input-group input-medium">
                                                      
                                                       <s:set var="dataNotifica" value="%{faseNotifica.dataNotifica}" />
                                                     	<s:if test="%{#dataNotifica == NULL">
                                                     		<s:set var="dataNotifica" value="%{verbale.dataNotificaVW}" />
                                                     	</s:if>
                                                      	 <input type="hidden" value="<s:date name="dataNotifica" format="dd/MM/yyyy" />" id="dataNotificaFNonModificabile" >
                                                         <input type="text" class="form-control" id="dataNotificaFN" readonly 
                                                        
                                                         value="<s:date name="dataNotifica" format="dd/MM/yyyy" />"
                                                         style="height: 38px; display: inline;"> 
                                                         <span class="input-group-btn">
                                                         
                                                         
                                                         <button id="dnv" onclick="setDatePicker('dataNotificaFN')" class="btn default"
                                                            type="button"><i class="fa fa-calendar"></i></button>
                                                         </span> <label for="form_control_1">Data Notifica verbale  </label>
                                                      </div>
                                                   </div>
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      
                                                         <div class="form-group form-md-line-input">
                                                         
                                                         
                                                         	 <s:set var="chiNotif" value="%{faseNotifica.chiNotifica}" />
                  
				 											 <select id="chiNotifica" class="form-control select2me">
																	<option selected><s:property value="chiNotif"/></option>
                    											
																	<s:iterator value="chiNotifica" var="cnf">
                			
																		<s:if test="%{#chiNotif == NULL || #cnf.statoProcesso.trim() != #chiNotif.trim()}">
                    														<option id="<s:property value="#cnf.idTplTopologiche" />" value="<s:property value="#cnf.statoProcesso" />">
                        														<s:property value="#cnf.statoProcesso" />
                   															</option>
                														</s:if>
            														</s:iterator>
                  											</select>
                  											<label for="chiNotifica"> Chi ha effettuato la notifica</label>
                                                     	 </div>
                                                     
                                                   </div>
                                                </div>
                                                
                                                
                                                <div class="row" style="margin-bottom: 30px;">
                                                	<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
	                                                   <div class="form-group form-md-line-input input-group input-medium">
	                                                   
	                                                   	
	                                                   	  <s:set var="dataprot" value="%{faseNotifica.dataProtocollo}" />
	                                                   <s:if test="%{#dataprot == NULL }">
	                                                   		 <!-- la prendo dal model splittando la stringa numero protocollo di auVerbale -->
	                                                   		 <s:set var="dataprot" value="%{dataProtocollo}" />
	                                                    </s:if>
	                                                   
	                                                   <input type="text" class="form-control" id="dataProtocollo" readonly
	                                                         value="<s:date name="dataprot" format="dd/MM/yyyy" />" style="height: 38px; display: inline;"> 
	                                                         <span class="input-group-btn">
		                                                         <button onclick="setDatePicker('dataProtocollo')" class="btn default"
		                                                            type="button"><i class="fa fa-calendar"></i></button>
	                                                         </span> <label for="form_control_1">Data Protocollo 
	                                                   </div>
	                                               </div>
	                                               
	                                               
	                                               <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
	                                                   <div class="form-group form-md-line-input input-group input-medium">
	                                                         <input type="text" class="form-control" id="dataInsDataNot" readonly="true"
	                                                          value="<s:date name="faseNotifica.dataInsDataNotifica" format="dd/MM/yyyy" />" style="height: 38px; display: inline;"> 
	                                                         <span class="input-group-btn">
		                                                         <button onclick="setDatePicker('dataInsDataNot')" class="btn default"
		                                                            type="button"><i class="fa fa-calendar"></i></button>
	                                                         </span> <label for="form_control_1">Data Inserimento Data Notifica <i class="fa fa-asterisk obbligatorio"></i></label>
	                                                   </div>
	                                                </div> 
	                                               <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">  
	                                                   <div class="form-group form-md-line-input input-group input-medium">
	                                                         
	                                                         <s:set var="dataChiusuraVerbale" value="%{faseNotifica.dataChiusuraVerb}" />
	                                                          <s:if test="%{#dataChiusuraVerbale == NULL">
																<s:set var="dataChiusuraVerbale" value="%{verbale.dataChiusuraVerbale}" />
                   											 </s:if>
	                                                         
	                                                         <input readonly type="text" class="form-control" id="dataChiuVerb" readonly="true"
	                                                          value="<s:date name="dataChiusuraVerbale" format="dd/MM/yyyy"  />" style="height: 38px; display: inline;"> 
	                                                         <span class="input-group-btn">
		                                                         <button onclick="setDatePicker('dataChiuVerb')" class="btn default"
		                                                            type="button"><i class="fa fa-calendar"></i></button>
	                                                         </span> <label for="form_control_1">Data Chiusura Verbale 
	                                                   </div>
	                                               </div>     
                                                </div>
                                                <div class="row" style="margin-bottom: 30px;">
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input input-group input-medium">
	                                                         <input type="text" class="form-control" id="dataConsFasc" readonly="true"
	                                                          value="<s:date name="faseNotifica.dataConsegnaFasc" format="dd/MM/yyyy" />" style="height: 38px; display: inline;"> 
	                                                         <span class="input-group-btn">
		                                                         <button onclick="setDatePicker('dataConsFasc')" class="btn default"
		                                                            type="button"><i class="fa fa-calendar"></i></button>
	                                                         </span> <label for="form_control_1">Data Consegna Fascicolo 
	                                                   </div>
                                                   </div>
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
	                                                   <div class="form-group form-md-line-input input-group input-medium">
	                                                   <s:set var="tipVerbaleIspettivo" value="%{faseNotifica.tipologiaVerbaleIspettivo}" />
				  										
                    										 <select id="tipVerbaleIspettivo" class="form-control select2me">
																<option selected><s:property value="tipVerbaleIspettivo"/></option>
																	<s:iterator value="tipologiaVerbaleIspettivo" var="ceidn">
																		
                    														<option id="<s:property value="#ceidn.idTplTopologiche" />" value="<s:property value="#ceidn.statoProcesso" />">
                        														<s:property value="#ceidn.statoProcesso" />
                   															</option>
                														
            														</s:iterator>
                  											</select>
                   
                                                         	<label for="form_control_1">Tipologia di verbale ispettivo <i class="fa fa-asterisk obbligatorio"></i></label>
	                                                   
	                                                   	</div>
	                                                </div>
                                                 <!--   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input input-group input-medium">
	                                                         <input type="text" class="form-control" id="dataTrsFasc" readonly="true"
	                                                          value="<s:date name="faseNotifica.dataTrasmisFascicolo" format="dd/MM/yyyy" />" style="height: 38px; display: inline;"> 
	                                                         <span class="input-group-btn">
		                                                         <button onclick="setDatePicker('dataTrsFasc')" class="btn default"
		                                                            type="button"><i class="fa fa-calendar"></i></button>
	                                                         </span> <label for="form_control_1">Data Trasmissione Fascicolo
	                                                   </div> 
                                                   </div>-->
                                                  <!--   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input">
                                                      <s:set var="inserimentoDataNotifica" value="%{faseNotifica.inserimentoDataNotifica}" />
				  										
                    										 <select id="inserimentoDataNotifica" class="form-control select2me">
																<option selected><s:property value="inserimentoDataNotifica"/></option>
																	<s:iterator value="correttoErrato" var="ceidn">
																		<s:if test="%{#inserimentoDataNotifica == NULL || #ceidn.valore.trim() != #inserimentoDataNotifica.trim()}">
                    														<option id="<s:property value="#ceidn.id" />" value="<s:property value="#ceidn.id" />">
                        														<s:property value="#ceidn.valore" />
                   															</option>
                														</s:if>
            														</s:iterator>
                  											</select>
                   
                                                         	<label for="form_control_1">Inserimento data notifica</label>
                                                      </div>
                                                   </div> -->
                                                   
                                                </div>
                                                 <div class="row" style="margin-bottom: 30px;">
                                                	
	                                            </div>
                                             </div>
                                             <div class="form-actions noborder" style="padding-top: 0;">
                                                <!-- script modificaPraticheAccessi.js -->
                                                <button type="button" class="btn blue" onclick="salvaNotifica()">Salva</button>
                                             </div>
                                          </form>
                                       </div>
                                    
                                    </div>
                                 </div>
                                 <!-- *******************************************************TAB 3 (Fase Definizione) ***********************************************-->
                                 <div class="tab-pane" id="portlet_tab3">
                                    <div class="portlet light">
                                       <div class="portlet-body form">
                                          <form role="form">
                                             
                                             <div class="form-body">
                                                <div class="row" style="margin-bottom: 30px;">
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input">
                                                           
														<s:set var="esReg" value="%{faseDefinizione.esitoRegolarizzazione}" />
                                                         <select class="form-control" id="esitoReg">
															   <option selected><s:property value="esReg"/></option> 
																	<s:iterator value="esitoReg" var="er">
																		<s:if test="%{#esReg == NULL || #er.statoProcesso.trim() != #esReg.trim()}">
                    														<option id="<s:property value="#er.idTplTopologiche" />" value="<s:property value="#er.idTplTopologiche" />">
                        														<s:property value="#er.statoProcesso" />
                   															</option>
                														</s:if>
            														</s:iterator>	
            														
            																											  
														 </select>
															
															<label for="form_control_1"> Esito Regolarizzazione <i class="fa fa-asterisk obbligatorio"></i></label>
                                                      </div>
                                                   </div>
                                                   <s:set var="dnv" value="%{verbale.dataNotificaVW}" />
                                                   
                                                   <input type="hidden" id="dataNotificaverbaleDef" value="<s:property value="dnv" />"/>
                                                   
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input input-group">
                                                         <input type="text" class="form-control" id="dataEsitoReg"
                                                            readonly="true"
                                                            value="<s:date name="faseDefinizione.dataEsitoRegolarizzazione" format="dd/MM/yyyy" />"
                                                         style="height: 38px; display: inline;"> <span class="input-group-btn">
                                                         <button onclick="setDatePicker('dataEsitoReg')" class="btn default"
                                                            type="button"><i class="fa fa-calendar"></i></button>
                                                         </span> <label for="form_control_1">Data Esito Regolarizzazione </label>
                                                      </div>
                                                   </div>
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input">
                                                       <s:set var="comunicazioniInviate" value="" />
                                                        <s:set var="errEsito" value="%{faseDefinizione.erroreEsito}" />
                                                         <select class="form-control" id="erroreEsito">
															  <option selected><s:property value="errEsito"/></option>
																	<s:iterator value="erroreEsito" var="es">
																		<s:if test="%{#errEsito == NULL || #es.statoProcesso.trim() != #errEsito.trim()}">
                    														<option id="<s:property value="#es.idTplTopologiche" />" value="<s:property value="#es.idTplTopologiche" />">
                        														<s:property value="#es.statoProcesso" />
                   															</option>
                														</s:if>
            														</s:iterator>														  
														 </select>
                                                         <label for="form_control_1"> Errore Esito <i class="fa fa-asterisk obbligatorio"></i></label>
                                                      </div>
                                                   </div>
                                                </div>
                                                <div class="row" style="margin-bottom: 30px;">
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input">
														
														<s:set var="preObblSol" value="%{faseDefinizione.presenzaObbligati}" />
														<s:if test="%{#preObblSol == NULL">
																<s:set var="preObblSol" value="%{verbale.presenzaObbligatiSolidaliVw}" />
                   											 </s:if>
                                                         <select class="form-control" id="preObblSol">
															  <option selected><s:property value="preObblSol"/></option>
																	<s:iterator value="siNoDropd" var="snd">
																		<s:if test="%{#preObblSol == NULL || #snd.valore.trim() != #preObblSol.trim()}">
                    														<option id="<s:property value="#snd.id" />" value="<s:property value="#snd.id" />">
                        														<s:property value="#snd.valore" />
                   															</option>
                														</s:if>
            														</s:iterator>														  
														 </select>
														 <label for="form_control_1"> Presenza Obbligati Solidali <i class="fa fa-asterisk obbligatorio"></i></label>
                                                      </div>
                                                   </div>
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input input-group">
                                                         
                                                          <s:set var="cSospensione" value="%{faseDefinizione.codiceSospensione}" />
                                                            <s:if test="%{#cSospensione == NULL }">
	                                                   		 <s:set var="cSospensione" value="%{verbale.codiceSospensione}" />
	                                                    </s:if>	
                                                         
                                                         <input type="text" class="form-control" id="cSospensione"  value="<s:property value="cSospensione" />" class="input-group-btn">
                                                         
                                                         <label for="form_control_1">Codice Sospensione</label>
                                                      </div>
                                                   </div>
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                       <div class="form-group form-md-line-input">
                                                        
														<s:set var="modComDisc" value="%{faseDefinizione.modComunDisconosc}" />
                                                         <select class="form-control" id="modComDisc">
															  <option selected><s:property value="modComDisc"/></option>
																	<s:iterator value="comDiscRdl" var="cdr">
																		<s:if test="%{#modComDisc == NULL || #cdr.statoProcesso.trim() != #modComDisc.trim()}">
                    														<option id="<s:property value="#cdr.idTplTopologiche" />" value="<s:property value="#cdr.idTplTopologiche" />">
                        														<s:property value="#cdr.statoProcesso" />
                   															</option>
                														</s:if>
            														</s:iterator>														  
														 </select>
														 
														 <label for="form_control_1">Modalita Comunicazione Disconoscimento RDL</label>
                                                      </div>
                                                   </div>
                                                </div>
                                                <div class="row" style="margin-bottom: 30px;">
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      
                                                      <div class="form-group form-md-line-input input-group">
                                                         <input type="text" class="form-control" id="dataInvioComDisc"
                                                            readonly="true"
                                                            value="<s:date name="faseDefinizione.dataInvioComunDisconosc" format="dd/MM/yyyy" />"
                                                         style="height: 38px; display: inline;"> <span
                                                            class="input-group-btn">
                                                         <button onclick="setDatePicker('dataInvioComDisc')" class="btn default"
                                                            type="button"><i class="fa fa-calendar"></i></button>
                                                         </span> <label for="form_control_1">Data Invio Comunicazione Disconoscimento RDL</label>
                                                      </div>
                                                   
                                                   </div>
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input input-group">
                                                         <input type="text" class="form-control" id="dataTrasmissione"
                                                            readonly="true"
                                                            value="<s:date name="faseDefinizione.dataTrasmissione" format="dd/MM/yyyy" />"
                                                         style="height: 38px; display: inline;"> <span
                                                            class="input-group-btn">
                                                         <button onclick="setDatePicker('dataTrasmissione')" class="btn default"
                                                            type="button"><i class="fa fa-calendar"></i></button>
                                                         </span> <label for="form_control_1">Data Trasmissione UL 13 al legale</label>
                                                      </div>
                                                   </div>
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                   	  <div class="form-group form-md-line-input">
                                                        
														 <s:set var="gestContrAff" value="%{faseDefinizione.gestContributiAffini}" />
                                                         <select class="form-control" id="gestContrAff">
															  <option selected><s:property value="gestContrAff"/></option>
																	<s:iterator value="siNoDropd" var="snda">
																		<s:if test="%{#gestContrAff == NULL || #snda.valore.trim() != #gestContrAff.trim()}">
                    														<option id="<s:property value="#snda.id" />" value="<s:property value="#snda.id" />">
                        														<s:property value="#snda.valore" />
                   															</option>
                														</s:if>
            														</s:iterator>														  
														 </select>
                                                         <label for="form_control_1">Gestione Contributiva o Affini <i class="fa fa-asterisk obbligatorio"></i></label>
                                                      </div>
                                                   </div>
                                                </div>
                                                <div class="row" style="margin-bottom: 30px;">
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input">
                                                         <s:set var="fallimento" value="%{faseDefinizione.fallimento}" />
                                                         <select class="form-control" id="fallimento">
															  <option selected><s:property value="fallimento"/></option>
																	<s:iterator value="siNoDropd" var="sndf">
																		<s:if test="%{#fallimento == NULL || #sndf.valore.trim() != #fallimento.trim()}">
                    														<option id="<s:property value="#sndf.id" />" value="<s:property value="#sndf.id" />">
                        														<s:property value="#sndf.valore" />
                   															</option>
                														</s:if>
            														</s:iterator>														  
														 </select>
                                                         <label for="form_control_1">Fallimento <i class="fa fa-asterisk obbligatorio"></i></label>
                                                      </div>
                                                   
                                                   </div>
                                                   <!-- <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input input-group">
                                                         <input type="text" class="form-control" id="dataFall"
                                                            readonly="true"
                                                            value="<s:date name="faseDefinizione.dataFallimento" format="dd/MM/yyyy" />"
                                                         style="height: 38px; display: inline;"> <span
                                                            class="input-group-btn">
                                                         <button onclick="setDatePicker('dataFall')" class="btn default"
                                                            type="button"><i class="fa fa-calendar"></i></button>
                                                         </span> <label for="form_control_1">Data Fallimento</label>
                                                      </div>
                                                   </div>-->
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                   	  <div class="form-group form-md-line-input input-group">
                                                        
                                                         <s:set var="credPrescritto" value="%{faseDefinizione.creditoPrescritto}" />
                                                         <select class="form-control" id="credPrescritto">
															  <option selected><s:property value="credPrescritto"/></option>
																	<s:iterator value="creditoPrescritto" var="cpd">
																		<s:if test="%{#credPrescritto == NULL || #cpd.statoProcesso.trim() != #credPrescritto.trim()}">
                    														<option id="<s:property value="#snd.idTplTopologiche" />" value="<s:property value="#cpd.idTplTopologiche" />">
                        														<s:property value="#cpd.statoProcesso" />
                   															</option>
                														</s:if>
            														</s:iterator>														  
														 </select>
                                                         <label for="form_control_1">Credito Prescritto</label>
                                                      </div>
                                                   </div>
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input input-group">
                                                        <s:set var="impCredito" value="%{faseDefinizione.importoCredito}" />
															<s:if test="%{#impCredito == NULL }">
																<s:set var="impCredito" value="%{verbale.contributiPratica}" />
															</s:if>	
                                                        
                                                         <input type="text" class="form-control" id="impCredito"  value="<s:property value="impCredito" />" class="input-group-btn">
                                                         
                                                         <label for="form_control_1">Importo Credito</label>
                                                      </div>
                                                   
                                                   </div>
                                                </div>
                                                <div class="row" style="margin-bottom: 30px;">
                                                   
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                     <div class="form-group form-md-line-input input-group">
                                                         
                                                         <s:set var="impSanz" value="%{faseDefinizione.importoSanzioni}" />
															<s:if test="%{#impSanz == NULL }">
																<s:set var="impSanz" value="%{verbale.sanzioni}" />
															</s:if>	
                                                         
                                                         <input type="text" class="form-control" id="impSanz"  value="<s:property value="impSanz" />" class="input-group-btn">
                                                         
                                                         <label for="form_control_1">Importo Sanzioni</label>
                                                      </div>
                                                   </div>
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                   	  <div class="form-group form-md-line-input ">
                                                         
														<s:set var="presFasc" value="%{faseDefinizione.presenzaFascCartaceo}" />
                                                         <select class="form-control" id="presFasc">
															  <option selected><s:property value="presFasc"/></option>
																	<s:iterator value="siNoDropd" var="sndpf">
																		<s:if test="%{#presFasc == NULL || #sndpf.valore.trim() != #presFasc.trim()}">
                    														<option id="<s:property value="#snd.id" />" value="<s:property value="#sndpf.id" />">
                        														<s:property value="#sndpf.valore" />
                   															</option>
                														</s:if>
            														</s:iterator>														  
														 </select>
                                                         <label for="form_control_1">Presenza Fascicolo Cartaceo Verbale <i class="fa fa-asterisk obbligatorio"></i></label>
                                                      </div>
                                                   </div>
                                                    <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                     <div class="form-group form-md-line-input input-group">
                                                         
                                                         <input type="text" class="form-control" id="dataConsFascCart"
                                                            readonly="true"
                                                            value="<s:date name="faseDefinizione.dataConsegnaFascCartaceo" format="dd/MM/yyyy" />"
                                                         	style="height: 38px; display: inline;"> <span
                                                            class="input-group-btn">
                                                         <button onclick="setDatePicker('dataConsFascCart')" class="btn default"
                                                            type="button"><i class="fa fa-calendar"></i></button>
                                                         </span> <label for="form_control_1">Data Consegna Fascicolo Cartaceo</label>
                                                      </div>
                                                   
                                                   </div>
                                                </div>
                                                <div class="row" style="margin-bottom: 30px;">
                                                  
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input input-group">
                                                         <input type="text" class="form-control" id="dataUdVerStPass"
                                                            readonly="true"
                                                            value="<s:date name="faseDefinizione.dataUdienzaStatoPassivo" format="dd/MM/yyyy" />"
                                                        	style="height: 38px; display: inline;"> <span
                                                            class="input-group-btn">
                                                         <button onclick="setDatePicker('dataUdVerStPass')" class="btn default"
                                                            type="button"><i class="fa fa-calendar"></i></button>
                                                         </span> <label for="form_control_1">Data Udienza Verifica Stato Passivo</label>
                                                      </div>
                                                   </div>
                                                    <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                   	 <div class="form-group form-md-line-input input-group">
                                                         <input type="text" class="form-control" id="dataScContApp"
                                                            readonly="true"
                                                            value="<s:date name="faseDefinizione.dataScadContrAppalto" format="dd/MM/yyyy" />"
                                                         	style="height: 38px; display: inline;"> <span
                                                            class="input-group-btn">
                                                         <button onclick="setDatePicker('dataScContApp')" class="btn default"
                                                            type="button"><i class="fa fa-calendar"></i></button>
                                                         </span> <label for="form_control_1">Data Scadenza Contr. App. (perobbl. solidale)</label>
                                                      </div>
                                                   </div>
                                                    <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                      <div class="form-group form-md-line-input input-group">
                                                         
                                                         
                                                         <s:set var="esReg" value="%{faseDefinizione.esitoRegolarizzazione}" />
                                                         <s:set var="esRegCorretto" value="%{faseDefinizione.esitoRegolarizzazioneCorretto}" />
                                                         
                                                         	<select class="form-control" id="esRegCorretto">
															  <option selected><s:property value="esRegCorretto"/></option>
																	<s:iterator value="esitoRegCorretto" var="erg">
																		<s:if test="%{#esRegCorretto == NULL || #erg.statoProcesso.trim() != #esRegCorretto.trim()}">
                    														<option id="<s:property value="#erg.idTplTopologiche" />" value="<s:property value="#erg.idTplTopologiche" />">
                        														<s:property value="#erg.statoProcesso" />
                   															</option>
                														</s:if>
            														</s:iterator>														  
														 </select>
                                                         <s:if test="%{#esReg != NULL ">	
                                                         	<label for="esRegCorretto">Esito Regolarizzazione corretto <i class="fa fa-asterisk obbligatorio"></i></label>
                                                         </s:if>
                                                         <s:else>
                                                         	<label for="esRegCorretto"> Esito Regolarizzazione corretto </label>
														</s:else>
                                                      </div>
                                                   
                                                   </div>
                                                </div>
                                                <div class="row" style="margin-bottom: 30px;">
                                                  
                                                   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                                                   	 <div class="form-group form-md-line-input input-group">
                                                         <input type="text" class="form-control" id="datadecruolo"
                                                            readonly="true"
                                                            value="<s:date name="faseDefinizione.dataDecadenzaRuolo" format="dd/MM/yyyy" />"
                                                         	style="height: 38px; display: inline;"> <span
                                                            class="input-group-btn">
                                                         
                                                         </span> <label for="datadecruolo">Data decadenza ruolo</label>
                                                      </div>
                                                   </div>
                                                </div>
                                                
                                                
                                               
                                                
                                                
                                                
                                             </div>
                                             <div class="form-actions noborder" style="padding-top: 0;">
                                                <!-- modificaPraticheAccessi.js -->
                                                <button type="button" class="btn blue" onclick="salvaDefinizione()">Salva</button>
                                             </div>
                                          </form>
                                       </div>
                                      
                                     
                                    </div>
                                 </div>
                                 <!-- DIV RISCHI -->
                                  <div class="tab-pane" id="portlet_tab_rischi">
                                    
                                </div>
                                <!-- DIV NON CONFORMITA -->
                                  <div class="tab-pane" id="portlet_tab_non_conformita">
                                    
                                </div>
                                <!-- DIV Dati Calcolati  -->
                                <div class="tab-pane" id="portlet_tab_dati_calcolati">

                                </div>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <!-- END SAMPLE TABLE PORTLET-->
         </div>
      </div>
      <div class="modalAside">
         <s:include
            value="sideBar/modificaIndicatoriAside.jsp" />
      </div>
