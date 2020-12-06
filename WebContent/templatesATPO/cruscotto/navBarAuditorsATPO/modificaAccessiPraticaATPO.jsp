<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.session.DatiUtente"%>

		<script type="text/javascript">
		$(function() {
			if($('#chkPrestEconomica').is(":checked")){
				$('#impPrestErogata').removeAttr('disabled');
			}else{
				$('#impPrestErogata').attr("disabled", "disabled");
			}
		});
		</script>

        <div class="page-head" id="tassHead"">
            <!-- BEGIN PAGE TITLE -->
            <div class="page-title">
             <s:set var="fascicolo" value="%{praticheAtpoDto.fascicolo}" />
             <s:set var="parte" value="%{praticheAtpoDto.parte}" />
                <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
                <h1>Accessi - Pratica - <s:property value="parte" />  <s:property value="fascicolo" /></h1> 
            </div>
            <!-- BEGIN PAGE BREADCRUMB -->
            <ul class="page-breadcrumb breadcrumb">
                <li>
                    Auditors ATPO
                    <i class="fa fa-circle"></i>
                </li>
                <li>
                    <a href="#" onclick="clickMenu('auditors-accessi'); loadDiv('/CruscottoAuditAtpoWebWeb/accessi', 'appView', null, 'initAuditors' );">
                    
                          Accessi</a>

                    <i class="fa fa-circle"></i>
                </li>
                <li>

                    <%	DatiUtente datiUtenteSession = (DatiUtente) request.getSession().getAttribute("DatiUtente");
                  			long idSessione = datiUtenteSession.getIdSessione();%>

                        <a href="#" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/modificaAccessoATPO?idSessione=<%=idSessione%>', 'appView', null, 'initTablesModificaAuditorATPO');">
                       Modifica Accessi</a>
                        <i class="fa fa-circle"></i>
                </li>
                <li>
                    Pratica
                </li>
            </ul>
            <!-- END PAGE BREADCRUMB -->
            <!-- END PAGE TITLE -->
        </div>
        <!-- END PAGE HEAD -->

        <!-- BEGIN PAGE CONTENT INNER -->
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
                                                <!-- TAB 1 -->
                                                <a href="#portlet_tab1" data-toggle="tab">
                                                    Dati sistemi alimentanti </a>
                                            </li>
                                            <li>
                                                <!-- TAB 2 -->
                                                <a href="#portlet_tab2" data-toggle="tab">
                                                   Spese/prestazioni </a>
                                            </li>
                                            <li>
                                                <!-- TAB 3 -->
                                                <a href="#portlet_tab3" data-toggle="tab" onclick ="resetFasi()">
                                                    Fasi </a>
                                            </li>
                                            <li>
                                                <!-- TAB 3 -->
                                                <a href="#portlet_nonConf" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getTabNonConfPraticheAtpo', 'portlet_nonConf', null, 'initTableNonConfPraticaAtpo');" data-toggle="tab">
                                                    Non conformita </a>
                                            </li>
                                            <li>
                                                <!-- TAB 3 -->
                                                <a href="#portlet_rischi" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getTabRischiPraticheAtpo', 'portlet_rischi', null, 'initTableRischiPraticaAtpo');" data-toggle="tab">
                                                    Rischi </a>
                                            </li>
                                            <li>
                                                <!-- TAB 3 -->
                                                <a href="#portlet_datiCalc" data-toggle="tab">
                                                    Dati calcolati </a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="tab-content">

                                            <!-- TAB 1 (Dati generali) -->
                                            <div class="tab-pane active" id="portlet_tab1">
                                                <div class="portlet" style="margin-bottom: 0px;">
                                                    <div class="portlet-body form">
                                                        <form role="form">
                                                            <div class="form-body">
																<div class="row" style="margin-bottom: 30px;">
																	<s:property value="controlDisabled" />
																	
																	<s:set var="statoEsamePratica" value="%{praticheAtpoDto.statoEsamePratica}" />
																	<s:set var="praticaModificabile" value="%{praticheAtpoDto.praticaModificable}" />
																	
																	
																	<s:if test='%{#praticaModificabile == "V"}'>
																		<s:if test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_APERTO}'>
																			<s:set var="CalcolaIndicatoriPraticaButton" value="%{'disabled'}" />
																			<s:set var="ApriPraticaButton" value="%{'disabled'}" />
																			<s:set var="salvataggioAbilitato" value="%{''}" />
																		</s:if>
																		<s:elseif test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_IN_LAVORAZIONE}'>
																		    <s:set var="LavoraPraticaButton" value="%{'disabled'}" />
																		    <s:set var="salvataggioAbilitato" value="%{''}" />
																		</s:elseif>
																		<s:elseif test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_CHIUSO}'>
																		    <s:set var="CalcolaIndicatoriPraticaButton" value="%{'disabled'}" />
																		    <s:set var="salvataggioAbilitato" value="%{'disabled'}" />
																		</s:elseif>
																		<s:elseif test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_ELABORAZIONE}'>
																		    <s:set var="ApriPraticaButton" value="%{'disabled'}" />
																	   		<s:set var="LavoraPraticaButton" value="%{'disabled'}" />
																	   		<s:set var="CalcolaIndicatoriPraticaButton" value="%{'disabled'}" />
																	   		<s:set var="salvataggioAbilitato" value="%{'disabled'}" />
																		</s:elseif>
																	</s:if>
																	<s:else>
																			<s:set var="salvataggioAbilitato" value="%{'disabled'}" />
																		    <s:set var="ApriPraticaButton" value="%{'disabled'}" />
																	   		<s:set var="LavoraPraticaButton" value="%{'disabled'}" />
																	   		<s:set var="CalcolaIndicatoriPraticaButton" value="%{'disabled'}" />
																	</s:else>
																	
										
																 	<!-- INIZIO CAMBO ESITO PRATICA -->
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <s:if test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_APERTO}'>
																			<div class="form-group form-md-line-input">
                                                                            	<input id="statusPratica" type="text" class="form-control" id="form_control_1" value="Aperta" style="height: 38px;" readonly>
                                                                            	<label for="form_control_1">Stato</label>
                                                                        	</div>
																		</s:if>
																		<s:elseif test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_IN_LAVORAZIONE}'>
																			<div class="form-group form-md-line-input">
                                                                            	<input id="statusPratica" type="text" class="form-control" id="form_control_1" value="In Lavorazione" style="height: 38px;" readonly>
                                                                            	<label for="form_control_1">Stato </label>
                                                                        	</div>
																		</s:elseif>
																		<s:elseif test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_CHIUSO}'>
																			<div class="form-group form-md-line-input">
                                                                            	<input id="statusPratica" type="text" class="form-control" id="form_control_1" value="Chiusa" style="height: 38px;" readonly>
                                                                            	<label for="form_control_1">Stato </label>
                                                                        	</div>
																		</s:elseif>
																		<s:elseif test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_ELABORAZIONE}'>
																			<div class="form-group form-md-line-input">
                                                                            	<input id="statusPratica" type="text" class="form-control" id="form_control_1" value="In Elaborazione" style="height: 38px;" readonly>
                                                                            	<label for="form_control_1">Stato </label>
                                                                        	</div>
																		</s:elseif>
                                                                    </div>
                                                                    <div class="col-md-9 col-xs-12">
                                                                      	<div class="form-group form-md-line-input">
                                                                        	<button id="LavoraPratica" type="button" onclick="calcolaIndicatoriPratica(this,'LavoraPratica')" class="btn blue-madison" <s:property value="LavoraPraticaButton" /> >Lavora Pratica</button>
                                                                        	<button id="ApriPratica" type="button" onclick="riapriPratica(this)" class="btn blue-madison" <s:property value="ApriPraticaButton" /> >Rilascio Pratica</button>
                                                                        	<button id="CalcolaIndicatoriPratica" type="button" onclick="calcolaIndicatoriPratica(this,'CalcolaIndicatoriPratica')" class="btn blue-madison" <s:property value="CalcolaIndicatoriPraticaButton" /> >Chiudi Pratica</button>
                                                                        </div>
                                                                    </div>
                                                                    
																 	<!-- FINE CAMBO ESITO PRATICA -->
																</div>
                                                                
                                                                <div class="row" style="margin-bottom: 30px;">
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="sede" value="%{praticheAtpoDto.nomeSede}" />

                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="sede" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Sede </label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="fascicolo" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Fascicolo </label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="richiesta" value="%{praticheAtpoDto.richiesta}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="richiesta" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Richiesta</label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="esito" value="%{praticheAtpoDto.esito}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="esito" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Esito </label>
                                                                        </div>
                                                                    </div>

                                                                </div>

                                                                <div class="row" style="margin-bottom: 30px;">
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">

                                                                            <s:set var="autotutela" value="%{praticheAtpoDto.autotutela}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="autotutela" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Autotutela </label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="parte" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Ricorrente </label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="notifica" value="%{praticheAtpoDto.notifica}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:date name="notifica" format="dd/MM/yyyy" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Data notifica</label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="apertura" value="%{praticheAtpoDto.apertura}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:date name="apertura" format="dd/MM/yyyy" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Data apertura </label>
                                                                        </div>
                                                                    </div>

                                                                </div>

                                                                <div class="row" style="margin-bottom: 30px;">
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="cost" value="%{praticheAtpoDto.costituzione}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:date name="cost" format="dd/MM/yyyy" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Data costituzione in giudizio </label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="udienza" value="%{praticheAtpoDto.udienza}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:date name="udienza" format="dd/MM/yyyy" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Data udienza </label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="parMedico" value="%{praticheAtpoDto.parereMedico}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="parMedico" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Parere medico</label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="dissenso" value="%{praticheAtpoDto.dissenso}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:date name="dissenso" format="dd/MM/yyyy" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Data termine dissenso in SISCO </label>
                                                                        </div>
                                                                    </div>

                                                                </div>

                                                                <div class="row" style="margin-bottom: 30px;">
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="dissInps" value="%{praticheAtpoDto.dissensoINPS}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:date name="dissInps" format="dd/MM/yyyy" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Data deposito dissenso INPS </label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="prov" value="%{praticheAtpoDto.provv}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:date name="prov" format="dd/MM/yyyy" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Data deposito del decreto di omologa </label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="notProvv" value="%{praticheAtpoDto.notProvv}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:date name="notProvv" format="dd/MM/yyyy" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Data notifica Decreto di omologa</label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="com" value="%{praticheAtpoDto.com}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:date name="com" format="dd/MM/yyyy" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Data trasmissione decreto alla LPS </label>
                                                                        </div>
                                                                    </div>

                                                                </div>

                                                                <div class="row" style="margin-bottom: 30px;">
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="car" value="%{praticheAtpoDto.car}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:date name="car" format="dd/MM/yyyy" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Data presa in carico Decreto di omologa da LPS </label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="esec" value="%{praticheAtpoDto.esec}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:date name="esec" format="dd/MM/yyyy" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Data liquidazione prestazione da LPS </label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="ric" value="%{praticheAtpoDto.prRic}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="ric" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">PR RIC</label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="prBzz" value="%{praticheAtpoDto.prBzz}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="prBzz" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">PR BZZ </label>
                                                                        </div>
                                                                    </div>

                                                                </div>

                                                                <div class="row" style="margin-bottom: 30px;">
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="prDef" value="%{praticheAtpoDto.prDef}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="prDef" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">PR DEF </label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="rg" value="%{praticheAtpoDto.rg}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="rg" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">RG </label>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="visitaPeritale" value="%{praticheAtpoDto.visitaPeritale}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="visitaPeritale" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Visita Peritale </label>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="osservazioniSanitarie" value="%{praticheAtpoDto.osservazioniSanitarie}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="osservazioniSanitarie" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1">Osservazioni Sanitarie </label>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="row" style="margin-bottom: 30px;">

                                                                <!--     <div class="col-md-3 col-xs-12">
                                                                        <div class="form-group form-md-line-input">
                                                                            <s:set var="rg" value="%{praticheAtpoDto.dSeiMuno}" />
                                                                            <input type="text" class="form-control" id="form_control_1" value="<s:property value="rg" />" style="height: 38px;" readonly>
                                                                            <label for="form_control_1"> D6 </label>
                                                                        </div>
                                                                    </div>
                                                                    
                                                                    <div class="col-md-3 col-xs-12">

                                                                    </div>
                                                                    
                                                                    <div class="col-md-6 col-xs-12">
                                                                    </div>-->

                                                                </div> 

                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- TAB 2 (Dati fasi) -->
                                            <div class="tab-pane" id="portlet_tab2">
                                                <div class="portlet" style="margin-bottom: 0px;">
                                                    <div class="portlet-body form">
                                                        <form role="form">
                                                            <div class="form-body">
																
                                                                <div class="portlet light bordered">
                                                                	 <label style="font-size: 13px; color: #888888;">
                                                                                                    <span></span>
                                                                                                    <span class="check"></span>
                                                                                                    <span class="box"></span>Codice Chiusura Inserito<i class="fa fa-asterisk obbligatorio"></i> </label>
                                                                    <s:set var="giudizio" value="%{faseDati.giudizio}" />
                                                                    <s:set var="fp" value="%{faseDati.fasePronta.trim()}" />
                                                                    <s:set var="codGiudizio" value="%{codificaGiudizio.trim()}" />
                                                                    <div class="portlet-body">
                                                                        <div class="md-radio-inline">
                                                                       	
																		 <s:if test="#fp == \"N\" ">
																			<s:iterator value="radioButtons" var="list">
                                                                                <s:if test="%{#codGiudizio eq #list.codifica.trim()}">
                                                                                    <div class="md-radio">
                                                                                        <input type="radio"  id="<s:property value="#list.codifica" />" checked="checked" name="radio2" class="md-radiobtn" value="<s:property value=" #list.codifica " />">
                                                                                        <label for="<s:property value="#list.codifica" />" style="font-size: 13px; color: #888888;">
                                                                                            <span></span>
                                                                                            <span class="check"></span>
                                                                                            <span class="box"></span>
                                                                                            <s:property value="#list.descrizione.trim()" /></label>
                                                                                    </div>
                                                                                </s:if>
                                                                                <s:else>
                                                                                    <div class="md-radio">
                                                                                        <input type="radio" id="<s:property value=" #list.codifica" />" name="radio2" class="md-radiobtn" value="<s:property value="#list.codifica" />">
                                                                                        <label for="<s:property value="#list.codifica" />" style="font-size: 13px; color: #888888;">
                                                                                            <span></span>
                                                                                            <span class="check"></span>
                                                                                            <span class="box"></span>
                                                                                            <s:property value="#list.descrizione.trim()" /> </label>
                                                                                    </div>
                                                                                </s:else>

                                                                            </s:iterator>
																		
																		
																		</s:if>
																		<s:else>
																		
																		<s:iterator value="radioButtons" var="list">
                                                                                <s:if test="%{#codGiudizio eq #list.codifica.trim()}">
                                                                                    <div class="md-radio">
                                                                                        <input type="radio" disabled id="<s:property value="#list.codifica" />" checked="checked" name="radio2" class="md-radiobtn" value="<s:property value=" #list.codifica " />">
                                                                                        <label for="<s:property value="#list.codifica" />" style="font-size: 13px; color: #888888;">
                                                                                            <span></span>
                                                                                            <span class="check"></span>
                                                                                            <span class="box"></span>
                                                                                            <s:property value="#list.descrizione.trim()" /></label>
                                                                                    </div>
                                                                                </s:if>
                                                                                <s:else>
                                                                                    <div class="md-radio">
                                                                                        <input type="radio" disabled id="<s:property value=" #list.codifica" />" name="radio2" class="md-radiobtn" value="<s:property value="#list.codifica" />">
                                                                                        <label for="<s:property value="#list.codifica" />" style="font-size: 13px; color: #888888;">
                                                                                            <span></span>
                                                                                            <span class="check"></span>
                                                                                            <span class="box"></span>
                                                                                            <s:property value="#list.descrizione.trim()" /> </label>
                                                                                    </div>
                                                                                </s:else>

                                                                            </s:iterator>
																		</s:else>
																		

                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="portlet light bordered">
                                                                    <div class="portlet-body">
                                                                        <div class="row" style="margin-bottom: 30px;">
                                                                            
                                                                            <s:set var="prestEc" value="%{faseDati.prestazioneEconomica.trim()}" />
                                                                            <s:if test='%{#prestEc == "s"}'>
                                                                                <div class="col-md-3 col-xs-12">

                                                                                    <div class="form-group form-md-checkboxes">
                                                                                        <div class="md-checkbox-inline">
                                                                                            <div class="md-checkbox">
                                                                                                <input type="checkbox"  <s:property value="salvataggioAbilitato" />  id="chkPrestEconomica" class="md-check" onclick="enablePrestErogata()" checked>
                                                                                                <label for="chkPrestEconomica" style="padding-left: 30px; font-size: 13px; color: #888888;">
                                                                                                    <span></span>
                                                                                                    <span class="check"></span>
                                                                                                    <span class="box"></span> Prestazione Economica </label>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>

                                                                                </div>
                                                                                <div class="col-md-3 col-xs-12">
                                                                                    <div class="form-group form-md-line-input">
                                                                                        <s:set var="prestEr" value="%{faseDati.importoPrestazioneErogata}" />
                                                                                          <input type="text"  <s:property value="salvataggioAbilitato" />  onmouseover="activeTooltip()" class="form-control tooltips" data-toggle="m-tooltip" data-container="body" data-placement="right" data-original-title="Inserire la sorte, primo pagamento ed arretrati" id="impPrestErogata" value="<s:property value="prestEr"/>" style="height: 38px;text-align:right;">

                                                                                        <label for="form_control_1">Importo prestazione erogata </label>
                                                                                    </div>
                                                                                </div>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <div class="col-md-3 col-xs-12">

                                                                                    <div class="form-group form-md-checkboxes">
                                                                                        <div class="md-checkbox-inline">
                                                                                            <div class="md-checkbox">
                                                                                                <input type="checkbox" <s:property value="salvataggioAbilitato" /> id="chkPrestEconomica" class="md-check" onclick="enablePrestErogata()">
                                                                                                <label for="chkPrestEconomica" style="padding-left: 30px; font-size: 13px; color: #888888;">
                                                                                                    <span></span>
                                                                                                    <span class="check"></span>
                                                                                                    <span class="box"></span> Prestazione Economica </label>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>

                                                                                </div>

                                                                                <div class="col-md-3 col-xs-12">
                                                                                    <div class="form-group form-md-line-input">
                                                                                        <s:set var="prestEr" value="%{faseDati.importoPrestazioneErogata}" />
                                                                                        <input type="text" <s:property value="salvataggioAbilitato" /> onmouseover="activeTooltip()" class="form-control tooltips" data-toggle="m-tooltip" data-container="body" data-placement="right" data-original-title="Inserire la sorte, primo pagamento ed arretrati" id="impPrestErogata" value="<s:property value="prestEr"/>" style="height: 38px; text-align:right;" >
                                                                                        <label for="form_control_1">Importo prestazione erogata </label>
                                                                                    </div>
                                                                                </div>
                                                                            </s:else>

                                                                        </div>

                                                                        <div style="margin-bottom: 20px;">
																			<s:if test="%{#fp == \"N\"}">
																				<button id="salvaDatiF" type="button" data-toggle="modal" data-target="#confirm" class="btn blue-madison" style="width: 70px;"  <s:property value="salvataggioAbilitato" /> >Salva</button>
																			</s:if>
																			<s:else>
																				<button id="salvaDatiF" type="button" onclick="salvaDatiFasi()" class="btn blue-madison" style="width: 70px;" <s:property value="salvataggioAbilitato" /> >Salva</button>
																			</s:else>
                                                                            
																			
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </form>

                                                    </div>
                                                </div>

                                            </div>

                                            <!-- TAB 3 (Fasi) -->
                                            <div class="tab-pane" id="portlet_tab3">
                                                <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                <div class="portlet" style="margin-bottom: 0px;">
                                                    <div class="portlet-body">
                                                        <div class="row" style="margin-bottom: 10px;">
                                                            <div class="col-lg-6 col-xs-12">
                                                                <div class="form-group">
                                                                    <label for="form_control_2" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Fasi</label>
                                                                    <br>

                                                                    <!-- auditorsATPO.js -->

                                                                    <select id="tipo-report-isc" class="form-control input-xlarge select2me" data-placeholder="Tutti" onchange="showPage();">
                                                                        <option value=""></option>
                                                                        <option value="acquisizioneIstanza"> Acquisizione istanza</option>
                                                                        <option value="autotutelaResGiudizio">Autotutela/Resistenza in giudizio</option>
                                                                        <option value="gestioneIstruttoria">Gestione istruttoria</option>
                                                                        <option value="peritale">Peritale</option>
                                                                        <option value="postPeritale">Post peritale</option>
                                                                        <option value="esecuzioneProvvedimenti">Esecuzione provvedimenti</option>
                                                                        <option value="riepilogoFascicolo">Riepilogo fascicolo</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="head-fase acquisizioneIstanza" id="acquisizioneIstanza"> </div>
                                                        <div class="head-fase autotutelaResGiudizio" id="autotutelaResGiudizio"> </div>
                                                        <div class="head-fase gestioneIstruttoria" id="gestioneIstruttoria"> </div>
                                                        <div class="head-fase peritale" id="peritale"> </div>
                                                        <div class="head-fase postPeritale" id="postPeritale"></div>
                                                        <div class="head-fase esecuzioneProvvedimenti" id="esProvvedimenti"></div>
                                                        <div class="head-fase riepilogoFascicolo" id="riepFascicolo"></div>
                                                        <!-- END SAMPLE TABLE PORTLET-->
                                                    </div>

                                                </div>
                                            </div>

                                            <!-- tab non conformita -->
                                            <div class="tab-pane" id="portlet_nonConf">
                                            </div>

                                            <!-- tab rischi -->
                                            <div class="tab-pane" id="portlet_rischi">
                                            </div>

                                            <!-- tab dati calcolati -->
                                            <div class="tab-pane" id="portlet_datiCalc">
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END SAMPLE TABLE PORTLET-->

                    </div>
                </div>

                <!-- MODAL -->
                <div class="modal-custom">
                </div>
           <div class="modal fade" tabindex="-1" id="confirm" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">Attenzione</span>
	                </div>
			      
			      </div>
			       <div class="modal-body">
			        <p>Il "giudizio" non potrà più essere modificato. <br> Procedere con l'operazione?</p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" data-dismiss="modal" class="btn default">No</button>
			        <!-- generale.js -->
			        <button type="button" class="btn blue" onclick="salvaDatiFasi();">Si</button>
			      </div>
			    </div>
			  </div>
			</div> 