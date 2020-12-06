<%@ taglib prefix="s" uri="/struts-tags" %> 

			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Modifica Campagna <!-- <small>- Modifica Campagna</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Audit
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                       <a href="#" onclick="clickMenu('audit-campagna'); loadDiv('/CruscottoAuditAtpoWebWeb/initCampagna', 'appView', null, 'initAuditCampagna' );">
             	Campagna</a>
					   <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Modifica Campagna
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
                                                    Dati Generali</a>
                                                </li>
                                                <li> <!-- TAB 1 -->
                                                    <a href="#portlet_tab2" data-toggle="tab">
                                                    Report Sedi </a>
                                                </li>
                                                <li> <!-- TAB 2 -->
                                                    <a href="#portlet_tab3" data-toggle="tab">
                                                    Accesso </a>
                                                </li>
                                                <!--<li>  TAB 3 
                                                    <a href="#portlet_tab4" data-toggle="tab">
                                                    Regole </a>
                                                </li>
                                                -->
                                                <li> <!-- TAB 4 -->
                                                    <a href="#portlet_tab5" data-toggle="tab">
                                                    Verbali </a>
                                                </li>
                                                <li> <!-- TAB 5 -->
                                                    <a href="#portlet_tab6" data-toggle="tab">
                                                    Report </a>
                                                </li>
                                            </ul>
                                        </div>
                                        
                                        <div class="portlet-body">
                                            <div class="tab-content">
  <!--*************************************************** TAB 1 (Dati)************************************************************************************************** -->
                                                <div class="tab-pane active" id="portlet_tab1">
                                                    <div class="portlet-body form">
                                                        <div class="form-body">
                                                         <s:set var="idCampagna" value="%{campagnaDto.idCampagna}" />
                                                				<input type="hidden" value="<s:property value="idCampagna" />" id="idCampagnaDaModificare">
                                                            <div class="row" style="margin-bottom: 40px;">
                                                                <div class="col-md-6" style="margin-bottom: 15px;">
                                                                    
                                                                    <s:set var="nomeAudit" value="%{campagnaDto.nomeAudit}" />
                                                                     <s:set var="idAudit" value="%{campagnaDto.idAudit}" />
                                                                     <s:set var="IdAuditSelezionato" value="%{IdAuditSelezionato}" />
                                                                
                                                                    <label for="form_control_2">Audit</label>
                                                                    <select class="form-control input-medium select2me" data-placeholder="<s:property value="nomeAudit" />" id="selectModifica" disabled="true">
                           													<option></option>
                           													
                           													<s:iterator value="listAudit" var="list">
                             												 	<s:if test="#list.idAudit == IdAuditSelezionato">
    																				<option id=" <s:property value="#list.idAudit" />" value=" <s:property value="#list.idAudit" /> " selected> <s:property value="#list.nome" /></option>
																				</s:if><s:else>
   																					<option id=" <s:property value="#list.idAudit" />" value=" <s:property value="#list.idAudit" /> "><s:property value="#list.nome" /></option>
																				</s:else>
                           													</s:iterator>
                        											</select>
                                                                    
                                                                </div>
                                                                <div class="col-md-6" style="margin-bottom: 15px;">
                                                                 <s:set var="nomeCampagna" value="%{campagnaDto.nome}" />
                                                                    <div class="form-group form-md-line-input">
                                                                        <input type="text" class="form-control" id="nomeCampagna"  value="<s:property value="nomeCampagna" />"  style="height: 38px;">
                                                                        <label for="form_control_2">Campagna</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row" style="margin-bottom: 40px;">
                                                            <div class="col-md-6" style="margin-bottom: 15px;">
                                                                <s:set var="dataInizio" value="%{campagnaDto.dataInizio}" />
                                                                    <div class="form-group form-md-line-input">
                                                                          <span>Data inizio<i class="fa fa-asterisk obbligatorio"></i></span>
                                                                       
                                                                       <input id="dataInizioModifica"  value="<s:date name="dataInizio" format="dd/MM/yyyy" />" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                                                      
                                                                      
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-6" style="margin-bottom: 15px;">
                                                                <s:set var="dataFine" value="%{campagnaDto.dataFine}" />
                                                                    <div class="form-group form-md-line-input">
                                                                      <span>Data fine</span>
                                                                     
                                                                        <input id="dataFineModifica"  value="<s:date name="dataFine" format="dd/MM/yyyy" />" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                                                     
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row" style="margin-bottom: 40px;">
                                                                <div class="col-md-6" style="margin-bottom: 15px;">
                                                                <s:set var="dataInizioOsservazione" value="%{campagnaDto.dataInizioOsservazione}" />
                                                                    <div class="form-group form-md-line-input">
                                                                      <span>Data inizio osservazione<i class="fa fa-asterisk obbligatorio"></i></span>
                                                                      
                                                                        <input id="dataInizioOsservazione"  value="<s:date name="dataInizioOsservazione" format="dd/MM/yyyy" />" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                                                        
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-6" style="margin-bottom: 15px;">
                                                                <s:set var="dataFineOsservazione" value="%{campagnaDto.dataFineOsservazione}" />
                                                                    <div class="form-group form-md-line-input">
                                                                    
                                                                        <span>Data fine osservazione<i class="fa fa-asterisk obbligatorio"></i></span>
                                                                           <input id="dataFineOsservazione"  value="<s:date name="dataFineOsservazione" format="dd/MM/yyyy" />" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-actions noborder" style="position: absolute;">
               												 <!-- campagna.js -->
                   										 	<!-- <button type="button" class="btn blue" onclick="salvaModificheCampagna()">Salva Modifiche</button> -->
                   										 	<button type="button" class="btn blue" onclick="salvaModificheCampagnaWithCheckDataFine()">Salva Modifiche</button>
               											 </div>
                                                </div>
            <!--*************************************************** TAB 2 (SEDI)************************************************************************************************** -->
                                                
                                                <div class="tab-pane" id="portlet_tab2">
                                                    <div class="portlet">
                                                       
                                                       
                                                        <div class="portlet-body">
                                                            <table class="table table-striped table-bordered table-advance table-hover" id="reportSediTable">
                                                                <thead>
                                                                	
                                                                    <th >id</th>
                                                                	<th>Sede</th>
                                                                	<th>Anno</th>
                                                                	<th>Numero Istanze</th>
                                                                	<th>Tempo Medio Apertura Pratica</th>
                                                                    <th>Tempo Medio Chiusura Pratica</th>
                                                                    <th>Sfavorevoli</th>
                                                                    <th>Perc. Sfavorevoli</th>
                                                                    <th>Con Fascicoli Aperti Senza Documenti</th>
                                                                    <th>Perc. Con Fascicoli Aperti Senza Documenti</th>
                                                                    <th>Con Visite Peritali</th>
                                                                    <th>Perc. Con Visite Peritali</th>
                                                                    <th>Con Presenza Atto Costituzione</th>
                                                                    <th>Con Presenza Parere Autotutela</th>   
                                                                    <th>Con Presenza Parere Sanitario</th>
                                                                    <th>Con Presenza Parere Sanitario Bozza</th>
                                                                    <th>Con Presenza Parere Sanitario Definitiva</th> 
                                                                 </thead>
                                                                 <tbody>
                                                                 </tbody>
                                                            </table>
                                                         </div>
                                                       
                                                       
                                                       
                                                           
                                                          
                                                           
                                                        </div>
                                                    
                                                   
                                                </div>
   <!--*************************************************** TAB 3 (Accesso)*************************************************************************************************************************** -->
                                                <div class="tab-pane" id="portlet_tab3">
                                                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                    <div class="portlet">
                                                        <div class="row">
                                                            <div class="col-lg-4 col-lg-offset-4">
                                                                
                                                            </div>
                                                        </div>
                                                        <div class="portlet-body">
                                                            <table class="table table-striped table-bordered table-advance table-hover" id="campagnaAccessoTable">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="width: 80px; text-align: center;">
                                                                            
                                                                        </th>
                                                                      
                                                                        <th>
                                                                            Sede
                                                                        </th>
                                                                        <th>
                                                                            Tipo
                                                                        </th>
                                                                        <th>
                                                                            Data Inizio
                                                                        </th>
                                                                        <th>
                                                                            Data Fine
                                                                        </th>
                                                                        <!-- 
                                                                        <th>
                                                                            Dirigente
                                                                        </th>
                                                                        <th>
                                                                            Auditors
                                                                        </th>
                                                                         -->
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <!-- END SAMPLE TABLE PORTLET-->
                                                </div>

  <!--*************************************************** TAB 4 (Regole) ***************************************************************************************************-->
                                                <div class="tab-pane" id="portlet_tab4">
                                                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                    <div class="portlet">
                                                        <div class="row">
                                                            <div class="col-lg-6 col-lg-offset-4">
                                                                <div class="actions add-action">
                                                                    <div class="actions add-action">
                                                                        <a href="#" onclick="getCheckBoxID('attivaRegolaCampagna');"class="btn btn-default btn-sm">
                                                                       <i class="fa fa-check sconto-up active-scont" style="color: green;"></i> Attiva </a>
                                                                    </div>
                                                                    <div class="actions add-action">
                                                                        <a href="#" onclick="getCheckBoxID('disattivaRegolaCampagna');"class="btn btn-default btn-sm">
                                                                        <i class="fa fa-close sconto-down" style="color: red;"></i> Disattiva </a>
                                                                    </div>
                                                                </div>
                                                               
                                                            </div>
                                                        </div>
                                                        <div class="portlet-body">
                                                            <table class="table table-striped table-bordered table-advance table-hover" id="campagnaRegoleTable">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="width: 80px; text-align: center;">
                                                                            
                                                                        </th>
                                                                        <th>
                                                                            Regola
                                                                        </th>
                                                                        <th>
                                                                            Condizione 1
                                                                        </th>
                                                                        <th>
                                                                            Condizione 2
                                                                        </th>
                                                                        <th>
                                                                            Condizione 3
                                                                        </th>
                                                                        <th>
                                                                            Condizione 4
                                                                        </th>
                                                                        <th>
                                                                            Condizione 5
                                                                        </th>
                                                                        <th>
                                                                            Stato
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
                                                
  <!-- *************************************************** TAB 5 (Verbali)************************************************************************************************************************* -->
                                                <div class="tab-pane" id="portlet_tab5">
                                                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                    
                                                    <div class="portlet-body">
                                                         <table class="table table-striped table-bordered table-advance table-hover" id="campagnaVerbaleTable">
                                                             <thead>
                                                                 <tr>
                                                                 	<th style="display:none"> </th>
                                                                     <th>Sede</th>
                                                                     <th>Fascicolo</th>
                                                                     <th>Data Notifica</th>
                                                                     <th>Data Apertura</th>
                                                                     <th>RG</th>
                                                                     <th>Parte</th>
                                                                     <th>Visita Peritale</th>
                                                                    
                                                                 </tr>
                                                             </thead>
                                                             <tbody>
                                                             </tbody>
                                                         </table>
                                                     </div>
                                                    
                                                    <!-- END SAMPLE TABLE PORTLET-->
                                                </div>
                                                
                                                
<!-- *************************************************** TAB 5 (Verbali)************************************************************************************************************************* -->
                                                <div class="tab-pane" id="portlet_tab6">
                                                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                   
                                                    <div class="portlet-body">
											        	<div class="col-lg-6 col-lg-offset-4 col-xs-12">
											        		<div class="actions add-action">
											        		</div>
											        		<div class="actions add-action">
											        			<button type="button" class="btn blue" onclick="javascript:generaReportAccessiAnnualeIDCampagna()" >Genera Report Accesso Annuale</button>
											        			<br/>
											        			<br/>
											        			<br/>
											        			<button type="button" class="btn blue" onclick="javascript:generaAllegatoReportAccessiAnnualeIDCampagna()" >Genera Report Allegato Annuale</button>
											        		</div>
											        		
											        		<div class="actions add-action">
											        			
											        		</div>
											        		<div class="actions add-action">
											        		<br/>
											        		</div>
											        	</div>
                                                    </div>
                                                    
                                                    <!-- END SAMPLE TABLE PORTLET-->
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
           		<!--<s:include value="sideBar/nuovoAccessoAside.jsp" />	-->
            </div>
            
           <!-- MODALE PER DATA FINE -->
           <div class="modal fade" tabindex="-1" id="modificaDataFineModal" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">ATTENZIONE</span>
	                </div>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <p>Il salvataggio comporterà l'aggiornamento delle date fine degli accessi con quella della campagne, vuoi procedere?</p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" data-dismiss="modal" class="btn default">No</button>
			        <!-- campagna.js -->
			      <button type="button" class="btn blue" onclick="salvaModificheCampagna()">Si</button>
			      </div>
			    </div>
			  </div>
			</div>
            
			<!-- END PAGE CONTENT INNER -->
