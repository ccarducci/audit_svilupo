<%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ page import="it.tecnet.crs.session.DatiUtente"%>
        <%@ page import="org.apache.struts2.ServletActionContext" %>
            <%@ page import="java.util.*" %>
                <%
    	HttpServletRequest r = ServletActionContext.getRequest();
		DatiUtente user= (DatiUtente)r.getSession().getAttribute("DatiUtente");
%>
                    <!-- BEGIN CONTENT -->

                    <!-- BEGIN PAGE HEAD -->
                    <div class="page-head">
                        <!-- BEGIN PAGE TITLE -->
                        <div class="page-title">
                            <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
                            <h1>Modifica Messaggi</h1>
                        </div>
                        <!-- BEGIN PAGE BREADCRUMB -->
                        <ul class="page-breadcrumb breadcrumb">
                            <li>
                                Gestione componenti
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#" onclick="clickMenu('componenti-normativa'); loadDiv('/CruscottoAuditAtpoWebWeb/getNormativaList', 'appView', null, 'initCircolariINps' );">
                          Normativa</a>

                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                Modifica Messaggi
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
                                                            <a href="#datiGenerali" oc data-toggle="tab">
                                                    			Dati generali </a>
                                                        </li>
                                                        <li>
                                                            <a href="#allegatiMI" onclick="caricaAllegati(3);" data-toggle="tab">
                                                    			Allegati </a>
                                                        </li>

                                                    </ul>
                                                </div>

                                                <div class="portlet-body">
                                                    <div class="tab-content">

                                                        <!-- ***************************************** TAB 1 (DATI GENERALI) ******************************************-->

														<s:hidden id="idMessaggiInps" value="%{messaggiInps.idMessaggiInps}"/>
														
                                                        <div class="tab-pane active" id="datiGenerali">
                                                        	<form role="form" id="CircolartiInpsForm">
                                                            	<div class="form-body" style="margin: 30px 0 40px;">
																
                                                                    <div class="row" style="margin-bottom: 10px;">
											                            <div class="col-md-3">
											                            <label for="dataInizioAudit" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data Emissione<i class="fa fa-asterisk obbligatorio"></i> </label>
											                                <div class="form-group">
											                                   <div class="input-group input-medium ">
											                                   		<s:set var="dataEmissioneVal" value="%{messaggiInps.dataEmissione}" />	
											                                     
											                                        
											                                       <input id="dataEmissioneMI" value="<s:date name="dataEmissioneVal" format="dd/MM/yyyy" />" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
											                                    </div>
											                                    
											                                </div>
											                            </div>
											                            <div class="col-md-3">
											                                <div class="form-group">
											                                    <label for="form_control_1" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Ente Emittente <i class="fa fa-asterisk obbligatorio"></i></label>
											                                    <s:select class="form-control input-medium select2me" 
											                               			data-placeholder="EnteEmittente" 
											                               			id="comboEnteEmittenteMI" 
												                              		list="entiEmittenti"
												                              		listKey="codice"
																					listValue="descrizione"	
																					value="%{messaggiInps.enteEmittente}"/>
											                                </div>
											                            </div>
											                        </div>
											                            
											                        <div class="row" style="margin-bottom: 15px;">    
											                            <div class="col-md-3">
											                                <div class="form-group form-md-line-input">
											                                <s:set var="codice" value="%{messaggiInps.codice}" />
											                                    <input type="text" class="form-control" id="codiceMI" value="<s:property value="codice" />" style="height: 28px;">
											                                    <label for="form_control_1">Numero Messaggio <i class="fa fa-asterisk obbligatorio"></i></label>
											                                </div>
											                            </div>
											                            <div class="col-md-6">
											                                <div class="form-group form-md-line-input">
											                                <s:set var="oggetto" value="%{messaggiInps.oggetto}" />
											                                    <input type="text" class="form-control" id="oggettoMI" value="<s:property value="oggetto" />" style="height: 28px;">
											                                    <label for="form_control_1">Oggetto <i class="fa fa-asterisk obbligatorio"></i></label>
											                                </div>
											                            </div>
											                        </div>
											                        
											                        <div class="row" style="margin-bottom: 15px;">
											                            <div class="col-md-6">
											                                <div class="form-group form-md-line-input">
											                                <s:set var="descSintetica" value="%{messaggiInps.descSintetica}" />
											                                    <input type="text" class="form-control" id="descSinteticaMI" value="<s:property value="descSintetica" />" style="height: 28px;">
											                                    <label for="form_control_1">Descrizione Sintetica <i class="fa fa-asterisk obbligatorio"></i></label>
											                                </div>
											                            </div>
											                            <div class="col-md-6">
											                                <div class="form-group form-md-line-input">
											                                <s:set var="descDettaglio" value="%{messaggiInps.descDettaglio}" />
											                                    <input type="text" class="form-control" id="descDettaglioMI" value="<s:property value="descDettaglio" />" style="height: 28px;">
											                                    <label for="form_control_1">Descrizione Dettaglio <i class="fa fa-asterisk obbligatorio"></i></label>
											                                </div>
											                            </div>
											                        </div>
											                        <div class="row" style="margin-bottom: 15px;">
											                            <div class="col-md-3">
											                                <label for="dataInizioAudit" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data Inizio<i class="fa fa-asterisk obbligatorio"></i> </label>
											                                <div class="form-group">
											                                   <div class="input-group input-medium ">
											                                   		<s:set var="dataInizioVal" value="%{messaggiInps.dataInizio}" />	
											                  
											                                        <input id="dataInizioMI" value="<s:date name="dataInizioVal" format="dd/MM/yyyy" />" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
											                                      
											                                    </div>
											                                    
											                                </div>
											                        	</div>
											                        	<div class="col-md-3">
											                                <label for="dataInizioAudit" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data Fine</label>
											                                <div class="form-group">
											                                   <div class="input-group input-medium ">
											                                   		<s:set var="dataFineVal" value="%{messaggiInps.dataFine}" />	
											                                      
											                                        
											                                       <input id="dataFineMI" value="<s:date name="dataFineVal" format="dd/MM/yyyy" />" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
											                                    </div>
											                                    
											                                </div>
											                            </div>
											                        </div>
											                       
                                                           		</div>
                                                           		
															 	<div class="form-actions noborder" style=" bottom: 15px;">
           															 <!-- modificaPraticheAccessi.js -->
               														<button type="button" class="btn blue" onclick="salvaMessaggiInps('U');">Salva</button>
                 												</div>
                                                                   
                                                           </form>
                                                        </div>
                                                        <!-- **************************************** TAB 2 (DATI CONTESTO) ***************************************** -->

                                                        <div class="tab-pane" id="allegatiMI">
                                                            <!-- CONTENUTO TAB  -->
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

                    <!-- MODAL -->
                    <div class="modal-custom">
                    </div>
                    
                    
                    
     <!-- MODALE IN MODIFICA -->
         <div class="modal fade" tabindex="-1" id="modificaMessaggiInpsModal" aria-hidden="true">
		  <div class="modal-dialog"  style="width: 550px !important; height: 200px !important;">
		    <div class="modal-content">
		      <div class="modal-header">
		        <div class="caption font-green">
		                  <span class="caption-subject bold uppercase">GESTIONE MESSAGGI INPS</span>
		              </div>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body" id="messageMessaggiInpsDiv">
		        <p></p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" data-dismiss="modal" class="btn default">Chiudi</button>
		      </div>
		    </div>
		  </div>
		</div>
