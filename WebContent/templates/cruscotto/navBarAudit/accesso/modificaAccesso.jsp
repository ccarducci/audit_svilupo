<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.session.DatiUtente"%>


			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Modifica Accesso <!-- <small>- Modifica Accesso</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Audit
                        <i class="fa fa-circle"></i>
                    </li>
                 
                    <li>
                    <a href="#" onclick="clickMenu('audit-accesso'); loadDiv('/CruscottoAuditAtpoWebWeb/initAccessoAudit', 'appView', null, 'initAuditAccesso' );">
                    Accesso</a>
					   <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Modifica Accesso
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
                                                <li> <!-- TAB 1 -->
                                                    <a href="#portlet_tab2" data-toggle="tab">
                                                    Auditors </a>
                                                </li>
                                                <li> <!-- TAB 2 -->
                                                    <a href="#portlet_tab3" data-toggle="tab">
                                                    Campione </a>
                                                </li>
                                                <!--  
                                                <li> <!-- TAB 3 
                                                    <a href="#portlet_tab4" onclick="initTabRisultati()" data-toggle="tab">
                                                    Risultati </a>
                                                </li>
                                                -->
                                            </ul>
                                        </div>
                                        <div class="portlet-body">
                                            <div class="tab-content">
                                                <!-- TAB 1 (Dati) -->
                                                <div class="tab-pane active" id="portlet_tab1">
                                                    <div class="portlet-body form">
                                                        <div class="form-body">
                                                            <div class="row" style="margin-bottom: 40px;">
                                                                <div class="col-md-4" style="margin-bottom: 15px;">
                                                                    
                                                                    <s:set var="nomeCampagna" value="%{campagna.nome}" />
                                                                    <s:set var="idCampagna" value="%{campagna.idCampagna}" />
                                                                
                                                                  
                                                                     <label for="form_control_2" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Campagna </label><br>
                                   									 <div></div>
                                   									 <select class="form-control input-medium select2me" 
                                   									 data-placeholder="<s:property value="nomeCampagna" />"  id="selectCamp" disabled="true">
                           													<option selected hidden id="<s:property value="#idCampagna" />">
                           													<s:property value="nomeCampagna" /></option>
                           													<s:iterator value="campagnaDtoList" var="list">
                              										<option id="<s:property value="#list.idCampagna" />" value="<s:property value="#list.idCampagna" />">
                              														<s:property value="#list.nome" />
                              														</option>
                          		 											</s:iterator>
                       	 											</select>
                                                                </div>
                                                               
                                                                   
                                                                <div class="col-md-4" style="margin-bottom: 15px;">
                                                                    
                                                                    <s:set var="nomeSede" value="%{sessioneDto.sede}" />
                                                                   
                                                                
                                                                  
                                                                     <label for="form_control_2" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Sedi </label><br>
                                   									 <div></div>
                                   									 <select class="form-control input-medium select2me"  data-placeholder="<s:property value="nomeSede" />"  id="selectSede" disabled="true">
                           												<option selected hidden id="<s:property value="nomeSede" />" >
                           													<s:property value="nomeSede" /></option>
                           												<s:iterator value="listSede" var="listSede">
                              												<option id="<s:property value="#listSede.nomeSede" />" value="<s:property value="#listSede.nomeSede" />">
                              													<s:property value="#listSede.nomeSede" />
                              												</option>
                          		 										</s:iterator>
                       	 											</select>
                                                                </div>
                                                                 
                                                               </div>
                                                               <div class="row" style="margin-bottom: 40px;">
                                                                	
                                                                
                                                               	 	
                                                               	 	
                                                               	 	<div class="col-md-4" style="margin-bottom: 15px;">
                                                                    
                                                                    <div class="form-group form-md-line-input">
                                                                     <s:set var="dataInizio" value="%{sessioneDto.dataInizio}" />
                                                                        <label for="dataInizioAuditUp">Data Inizio Accesso<i class="fa fa-asterisk obbligatorio"></i></label>
                                                                         
                                                                        <input id="modificaDataInizio"  value="<s:date name="dataInizio" format="dd/MM/yyyy" />" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >  
                                                                         
                                                        				
                                                                       
                                                                    </div>
                                                                </div>
                                                               	 	
                                                               	 	
                                                               	 	
                                                               	 	
                                                               	 	<div class="col-md-4" style="margin-bottom: 15px;">
                                                                    
                                                                    <div class="form-group form-md-line-input">
                                                                    <s:set var="dataFine" value="%{sessioneDto.dataFine}" />
                                                                         <label for="dataInizioAuditUp">Data Fine Accesso</label>
                                                                      
                                                                          <input id="modificaDataFine"  value="<s:date name="dataFine" format="dd/MM/yyyy" />" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >  
                                                                       
                                                                    </div>
                                                                </div>
                                                                </div>
                                                                 <div class="form-actions noborder" style="position: absolute;">
               												 <!-- accesso.js -->
                   										 	<button type="button" class="btn blue" onclick="salvaModificheAccesso()">Salva Modifiche</button>
               											 </div>
                                                                
                                                            </div>
                                                         
                                                        
                                                         
                                                        </div>
                                                    </div>
                                                    
                                                <!-- TAB 2 (Auditors) -->
                                                <div class="tab-pane" id="portlet_tab2">
                                                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                    <div class="portlet">
												        <div class="row">
                                                                <div class="col-lg-6 col-lg-offset-3 col-xs-12" id="bottoniDelegati">
                                                                    <div class="actions add-action">
                                                                    <a data-toggle="modal" onclick="associaAuditors();" class="btn btn-default btn-sm del">
                                                                        <i class="fa fa-check"></i> Associa </a>
                                                                    </div>
                                                                    <div class="actions add-action">
                                                                        <a href="#" onclick="dissociaAuditors();"class="btn btn-default btn-sm">
                                                                        <i class="fa fa-times"></i> Dissocia </a>
                                                                    </div>
                                                                    
                                                                </div>
                                                            </div>
								                        <div class="portlet-body">
								                            <!--<div class="table-scrollable">-->
								                                <table class="table table-striped table-bordered table-advance table-hover" id="auditors_table">
								                                    <thead>
								                                        <tr>
								                                            <th style="text-align: center;">
								                                                
								                                            </th>
								                                            <th>
								                                                Nome
								                                            </th>
								                                            <th>
								                                                Cognome
								                                            </th>
								                                            <th>
								                                                Username
								                                            </th>
								                                            <th>
								                                                Associato
								                                            </th>
								                                        </tr>
								                                    </thead>
								                                    <tbody>
								                                        
								                                    </tbody>
								                                </table>
								                            <!--</div>-->
								                        </div>
								                    </div>
                								</div>
                                                    <!-- END SAMPLE TABLE PORTLET-->
                                            
                                                <!-- TAB 3 (Campione) -->
                                                <div class="tab-pane" id="portlet_tab3">
                                                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                    <div class="portlet">
                                                        <div class="row">
                                                            <div class="col-lg-4">
                                                           
                                                                <div class="form-group">
                                                                 	
                                                               		<input type="text" class="form-control" id="nomeCampione" value=""  style="height: 38px;" placeholder="Nome Campione">
                                                               		
                                                                </div>
                                                                
                                                            </div>
                                                            <div class="col-lg-4">
                                                                 <div class="form-group">
                                                                 	
               												 		<!-- accesso.js -->
                   										 			 <button type="button" class="btn blue" onclick="salvaNuovoCampioneSessione()">Salva Campione</button>
                   										 			 <button type="button" class="btn blue" onclick="calcolaDatiDiContesto()">Calcola dati di contesto</button>
                   										 		</div>
               											 	</div>
                                                                
                                                       </div>
                                                       </div>
                                                     
                            
                                                       <div class="row">
                                                            <div class="col-lg-6 col-lg-offset-4 col-xs-12" style="padding-top: 18px;">
                                                               
                                                                <div class="actions add-action">
                                                                    <a data-toggle="modal" id="addPratica" href="#" onclick="reloadTable('praticheSessioneTable'); slideAsideNew('add');" class="btn btn-default btn-sm" style="width:150px;">
                                                                    <i class="fa fa-edit"></i> Aggiungi Pratica </a>
                                                                </div>
                                                                <div class="actions add-action">
                                                                    <a data-toggle="modal" id="removePratica" onclick="getCheckBoxID('eliminaPraticaCampione')" href="#" class="btn btn-default btn-sm del" style="width:150px;">
                                                                    <i class="fa fa-trash"></i> Rimuovi Pratica </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="portlet-body">
                                                            <table class="table table-striped table-bordered table-advance table-hover" id="verbaliCampione">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="width: 80px; text-align: center;">
                                                                            
                                                                        </th>
                                                                       
                                                                     <th>Sede</th>
                                                                     <th>Fascicolo</th>
                                                                     <th>Esito</th>
                                                                     <th>Parte</th>
                                                                     <th>Data Notifica</th>
                                                                     <th>Data Apertura</th>
                                                                     
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                   
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <!-- END SAMPLE TABLE PORTLET-->
                                               

                                                <!-- TAB 4 (Risultati) -->
                                                <div class="tab-pane" id="portlet_tab4" >
                                                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                    <div class="portlet" id="risultatiTabPage">
                                                     
                                                  		<!--<s:include value="risultatiTab.jsp" />	-->
                                                    
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
                    </div>
                    <!-- END SAMPLE TABLE PORTLET-->

				</div>
			
			
            	<s:include value="sideBar/aggiungiPraticaAside.jsp" />	
            <!-- MODAL -->
            <div class="modal-custom">
            </div>
            <div>
            <div class="modal fade" tabindex="-1" id="deleteVerbaliCampione" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">RIMUOVI PRATICA</span>
	                </div>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <p>Eliminare pratica associata al campione?</p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" data-dismiss="modal" class="btn default">No</button>
			        <!-- accessoAudit.js -->
			        <button type="button" class="btn blue" onclick="eliminaPraticaCampione();">Si</button>
			      </div>
			    </div>
			  </div>
			</div>   
            
            
            </div>
   
