<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="it.tecnet.crs.web.beans.AppUser;"%>

<%
   AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
   String ruoloAttivo=user.getRuoloAttivo();
%> 

			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Modifica Generale <!-- <small>- Modifica Generale</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Audit
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        <a href="#" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/auAudit', 'appView', null, 'initAuditGenerale' );">
                        Generale
                        </a>
					<i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Modifica Generale
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
                                                <li class="active"> <!-- TAB 1 -->
                                                    <a href="#portlet_tab1" data-toggle="tab">
                                                    Dati </a>
                                                </li>
<%
if(ruoloAttivo.toLowerCase().indexOf("dirigente") != -1){
%>
                                                <li> <!-- TAB 1 -->
                                                    <a href="#portlet_tab2" data-toggle="tab">
                                                    Delegati </a>
                                                </li>
<%
}
%>
                                                <li> <!-- TAB 2 -->
                                                    <a href="#portlet_tab3" data-toggle="tab">
                                                    Processo/Fasi </a>
                                                </li>
                                                <!-- generale.js -->
                                                 <li> <!-- TAB 2 -->
                                                    <a href="#portlet_tab4" data-toggle="tab" onclick="showtab('tabRischio')">
                                                    Rischi e Non Conformita </a>
                                                </li>
                                                <li> <!-- TAB 3 -->
                                              
                                                  <a href="#portlet_tab5_q" data-toggle="tab" onclick="showtab('questionario')">
                                                    Questionario </a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="portlet-body">
                                            <div class="tab-content">
        <!--********************************************************** TAB 1 (Dati) ***************************************************************************************************** -->
                                                <div class="tab-pane active" id="portlet_tab1">
                                                    <div class="portlet-body form">
                                                        <div class="form-body">
                                                            <div class="row" style="margin-bottom: 40px;">
                                                                <div class="col-md-4" style="margin-bottom: 15px;">
                                                                    <div class="form-group form-md-line-input">
                                                                    
                                                                     <s:set var="nomeAudit" value="%{audit.nome}" />
                                                                       
                                                                        <input type="text" 
                                                                        	class="form-control"  id="nomeAuditUp"
                                                                        	 value="<s:property value="nomeAudit" />" style="height: 38px;">
                                                                        <label for="nomeAuditUp">Nome<i class="fa fa-asterisk obbligatorio"></i></label>
                                                                    </div>
                                                                </div>                                
                                                                <div class="col-md-4" style="margin-bottom: 15px;">
                                                                    <div class="form-group form-md-line-input">
                                                                        <s:set var="dirigenteAudit" value="%{nomeDirigenteAudit}" />
                                                                        <input type="text" class="form-control" id="dirigenteAuditUp"
                                                                         value="<s:property value="dirigenteAudit" />"  style="height: 38px;" disabled>
                                                                        <label for="dirigenteAuditUp">Dirigente</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row" style="margin-bottom: 40px;">
                                                            <div class="col-md-4" style="margin-bottom: 15px;">
                                                                    
                                                                    <div class="form-group form-md-line-input">
                                                                      <s:set var="dataInizioAudit" value="%{audit.dataInizio}" />
                                                                         
                                                                        <input id="dataInizioAuditUp"  class="form-control form-control-inline input-medium date-picker" size="16" type="text"  value="<s:date name="dataInizioAudit" format="dd/MM/yyyy" />" >
                                                                        <label for="dataInizioAuditUp">Data inizio<i class="fa fa-asterisk obbligatorio"></i></label>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-4" style="margin-bottom: 15px;">
                                                                    
                                                                    <div class="form-group form-md-line-input">
                                                                      <s:set var="dataFineAudit" value="%{audit.dataFine}" />
                                                                       
                                                                         
                                                                        <input id="dataFineAuditUp"  class="form-control form-control-inline input-medium date-picker" size="16" type="text"  value="<s:date name="dataFineAudit" format="dd/MM/yyyy" />" >   
                                                                  
                                                        				
                                                                        <label for="dataFineAuditUp">Data fine</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row" style="margin-bottom: 40px;">
                                                            	<div class="col-md-4" style="margin-bottom: 15px;">
												                   <div class="form-group form-md-line-input">
												                   	  <s:set var="descrizioneAudit" value="%{audit.descrizione}" />
												                      <textarea class="form-control" id="descrizioneAuditUp" rows="3"  maxlength="150"><s:property value="descrizioneAudit"/></textarea>
												                      <label for="descrizioneAuditUp">Descrizione</label>
												                   </div>
												                </div>
												                <div class="col-md-4" style="margin-bottom: 15px;">
												                   <div class="form-group form-md-line-input">
												                   	  <s:set var="inputAudit" value="%{audit.input}" />
												                      <textarea class="form-control" id="inputAuditUp" rows="3"  maxlength="150"><s:property value="inputAudit"/></textarea>
												                      <label for="inputAuditUp">Input</label>
												                   </div>
												                </div>
												                <div class="col-md-4" style="margin-bottom: 15px;">
												                   <div class="form-group form-md-line-input">
												                   	  <s:set var="outputAudit" value="%{audit.output}" />
												                      <textarea class="form-control" id="outputAuditUp" rows="3"  maxlength="150"><s:property value="outputAudit"/></textarea>
												                      <label for="descrizioneAuditUp">Output</label>
												                   </div>
												                </div>
                                                            </div>
                                                            <div class="row" style="margin-bottom: 40px;">
                                                            	<div class="col-md-4" style="margin-bottom: 15px;">
												                   <div class="form-group form-md-line-input">
												                   	  <s:set var="obiettiviAudit" value="%{audit.obiettivo}" />
																	  <textarea class="form-control" id="obiettiviAuditUp" rows="3"  maxlength="150"><s:property value="obiettiviAudit"/></textarea>
												                      <label for="obiettiviAuditUp">Obiettivi</label>
												                   </div>
												                </div>
                                                                <div class="col-md-4" style="margin-bottom: 15px; margin-top: 55px; padding-left: 100px;">
                                                                	<label style="color:red;" id="info" hidden>Data inizio e nome sono obbligatori</label>
                                                                </div>
                                                                <div class="col-md-4" style="margin-bottom: 15px; padding-left: 200px; margin-top: 75px;">
                                                                	<button type="button" class="btn blue" onclick="salvaModificheAudit()">Salva Modifiche</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
    <!--********************************************** TAB 2 (Delegati)************************************************************************************************************************************* -->
<%
if(ruoloAttivo.toLowerCase().indexOf("dirigente") != -1){
%>                                           
                                             <div class="tab-pane" id="portlet_tab2">
                                                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                        <div class="portlet">
                                                    		<div class="row">
                                                                <div class="col-lg-6 col-lg-offset-3 col-xs-12" id="bottoniDelegati">
                                                                    <div class="actions add-action">
                                                                    <a data-toggle="modal" onclick="associaDelegato();" class="btn btn-default btn-sm del">
                                                                        <i class="fa fa-check"></i> Associa </a>
                                                                    </div>
                                                                    <div class="actions add-action">
                                                                        <a href="#" onclick="dissociaDelegato();"class="btn btn-default btn-sm">
                                                                        <i class="fa fa-times"></i> Dissocia </a>
                                                                    </div>
                                                                    
                                                                </div>
                                                            </div>
                                                            <div class="portlet-body">
                                                                <table class="table table-striped table-bordered table-advance table-hover" id="delegati_table">
                                                                    <thead>
                                                                        <tr>
                                                                            <th style="width: 80px; text-align: center;">
                                                                               
                                                                            </th>
                                                                            <th>
                                                                                Nome
                                                                            </th>
                                                                            <th>
                                                                                Cognome
                                                                            </th>
                                                                            <th>
                                                                                Associato
                                                                            </th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                       
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                        <!-- END SAMPLE TABLE PORTLET-->
                                                </div>
<%
}
%>                                                
   <!--********************************************** TAB 3 (Fasi)************************************************************************************************************************************* -->
                                                <div class="tab-pane" id="portlet_tab3">
                                                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                        <div class="portlet">
                                                            <div class="row">
                                                                <div class="col-lg-6 col-lg-offset-3 col-xs-12" id="bottoniProcessoFasi">
                                                                    <div class="actions add-action">
                                   										<a data-toggle="modal" href="#" onclick="slideAsideNew('add');" class="btn btn-default btn-sm">
                                                                        <i class="fa fa-plus"></i> Aggiungi </a>
                                                                    </div>
                                                                    <div class="actions add-action">
                                                                    <!-- cruscottoScript.js -->
                                                                    <a data-toggle="modal" onclick="getCheckBoxID('eliminaFase');" class="btn btn-default btn-sm del">
                                                                        <i class="fa fa-trash"></i> Rimuovi </a>
                                                                    </div>
                                                                    <div class="actions add-action">
                                                                        <a href="#" onclick="reloadTable('fasiTable');"class="btn btn-default btn-sm">
                                                                        <i class="fa fa-refresh"></i> Aggiorna </a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="portlet-body">
                                                                <table class="table table-striped table-bordered table-advance table-hover" id="fasi_table">
                                                                    <thead>
                                                                        <tr>
                                                                            <th style="width: 80px; text-align: center;">
                                                                               
                                                                            </th>
                                                                            <th>
                                                                                Descrizione processo
                                                                            </th>
                                                                            <th>
                                                                                Descrizione fase
                                                                            </th>
                                                                            <th>
                                                                                Input
                                                                            </th>
                                                                            <th>
                                                                                Output
                                                                            </th>
                                                                            <th>
                                                                                Unità Operative Coinvolte
                                                                            </th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                       
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                        <!-- END SAMPLE TABLE PORTLET-->
                                                </div>
<!--************************************************** TAB 4 (Rischi e Non Conformita)****************************************************************************************************************************** -->
         
         
         										<div class="tab-pane" id="portlet_tab4">
                                                    <div class="row">
                                                        <div class="col-md-12 ">
                                                            <!-- BEGIN Portlet PORTLET-->
                                                            <div class="portlet light accessori">
                                                                <div class="portlet-title tabbable-line">
                                                                    <ul class="nav nav-tabs riskNC">
                                                                        <li class="active"> <!-- TAB 1 -->
                                                                            <a href="#portlet_tab1a" data-toggle="tab">
                                                                            Rischio </a>
                                                                        </li>
                                                                        <li> <!-- TAB 2 -->
                                                                            <a href="#portlet_tab2a" data-toggle="tab" onclick="showtab('tabEsprRischio')">
                                                                            Espressione Rischio </a>
                                                                        </li>
                                                                        <li> <!-- TAB 3 -->
                                                                            <a href="#portlet_tab3a" data-toggle="tab">
                                                                            Dettaglio Rischio </a>
                                                                        </li>
                                                                        <li> <!-- TAB 4 -->
                                                                            <a href="#portlet_tab4a" data-toggle="tab" onclick="showtab('tabNonConformita')">
                                                                            Non conformità </a>
                                                                        </li>
                                                                        <li> <!-- TAB 5 -->
                                                                            <a href="#portlet_tab5a" data-toggle="tab" onclick="showtab('tabVarComportamentale')">
                                                                            Variante Comportamentale </a>
                                                                        </li>
                                                                        <li> <!-- TAB 6 -->
                                                                            <a href="#portlet_tab6a" data-toggle="tab">
                                                                            Sotto Variante </a>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                                <div class="portlet-body">
                                                                    <div class="tab-content">
                                                                        
                                                                        <!-- TAB 1a (Rischio) -->
																		<div class="tab-pane active" id="portlet_tab1a">
																			<!-- l'include delle pagine avviene tramite funzione showTab in generale.js -->
																		</div>
																		
																		
                                                                        <!-- TAB 2a (Espressione Rischio) -->
                                                                        <div class="tab-pane" id="portlet_tab2a">
                                                                            
                                                                        </div>
                                                                        
                                                                        <!-- TAB 3a (Dettaglio Rischio) -->
                                                                        <div class="tab-pane" id="portlet_tab3a">
                                                                            <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                                            <div class="portlet">
                                                                                Dettaglio Rischio
                                                                            </div>
                                                                            <!-- END SAMPLE TABLE PORTLET-->
                                                                        </div>
                                                                        
                                                                        <!-- TAB 4a (Non conformità) -->
                                                                        <div class="tab-pane" id="portlet_tab4a">
                                                                            
                                                                        </div>
                                                                         <!-- TAB 5a (Variante Comportamentale) -->
                                                                        <div class="tab-pane" id="portlet_tab5a">
                                                                            
                                                                        </div>
                                                                        
                                                                        <!-- TAB 6a (Sotto Variante) -->
                                                                        <div class="tab-pane" id="portlet_tab6a">
                                                                            <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                                            <div class="portlet">
                                                                                Sotto Variante
                                                                            </div>
                                                                            <!-- END SAMPLE TABLE PORTLET-->
                                                                        </div>
                                                                        
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
         							
         
         <!--************************************************** TAB 5 (Questionario)****************************************************************************************************************************** -->
                                                <div class="tab-pane" id="portlet_tab5_q">
                                                   

												</div>
			</div>
			
            	<s:include value="sideBar/generaleAsideAggiungiFase.jsp" />	
            	
            	
            <!-- aside -->
            <div class="modal-custom">
           </div>
           
           <div>
           <!-- MODALE CONFERMA ELIMINA AREA -->
           <div class="modal fade" tabindex="-1" id="deleteFasiModal" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">ELIMINA FASE</span>
	                </div>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <p>Si sta eliminando la fase selezionata. Procedere con l' operazione?</p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" data-dismiss="modal" class="btn default">No</button>
			        <!-- generale.js -->
			        <button type="button" class="btn blue" onclick="eliminaFase();">Si</button>
			      </div>
			    </div>
			  </div>
			</div>
            
            </div>
            
         
            
			<!-- END PAGE CONTENT INNER -->
	