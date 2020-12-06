<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.web.beans.AppUser;"%>
<%
   AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
   String ruoloAttivo=user.getRuoloAttivo();
%> 
	<!-- BEGIN CONTENT -->
	
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Gestione Soglie e Colori <!-- <small>- Generale</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Area Amministrativa
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Gestione Soglie e Colori
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
				        <div class="row">				        

                            <div class="col-lg-6 col-lg-offset-4 col-xs-12">
                                <div class="actions add-action">
                                   <a data-toggle="modal" href="#" onclick="initDatePicker();slideAsideNew('add');" class="btn btn-default btn-sm">
                                    <i class="fa fa-plus"></i> Nuovo </a>
                                </div>
                                <div class="actions add-action">
                                <!-- cruscottoScript.js -->
                                    <a href="#" onclick="getCheckBoxID('modificaIsnc');"
                                      class="btn btn-default btn-sm">
                                    <i class="fa fa-pencil"></i> Modifica </a>
                                </div>
                                <div class="actions add-action">
                                <!-- cruscottoscript.js -->
                                     <a data-toggle="modal" onclick="getCheckBoxID('eliminaIsnc');" class="btn btn-default btn-sm del">
                                    <i class="fa fa-trash"></i> Elimina </a>
                                </div>
                                <div class="actions add-action">
                                 <a href="#" onclick="reloadTable('generaleIsnc');"class="btn btn-default btn-sm">
                                    <i class="fa fa-refresh"></i> Aggiorna </a>
                                </div>
                            </div>

                        </div>
                        <div class="portlet-body">
                            <!--<div class="table-scrollable">-->
                                <table class="table table-striped table-bordered table-advance table-hover" id="isnc_table">
                                    <thead>
                                        <tr>
                                             <th style="text-align: center;">
                                                <div class="form-group form-md-checkboxes">
                                                    <div class="md-checkbox-inline">
                                                        <div class="md-checkbox">
                                                           
                                                        </div>
                                                    </div>
                                                </div>
                                            </th>
                                             
                                            
                                            <th>
                                                Soglia
                                            </th>
                                            <th>
                                                Data inizio
                                            </th>
                                            <th>
                                                Data fine
                                            </th>
                                            <th>
                                                Colore
                                            </th>
                                             <th>
                                                Audit
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       
                                    </tbody>
                                </table>
                            <!--</div>-->
                        </div>
                    </div>
                    <!-- END SAMPLE TABLE PORTLET-->

				</div>
			</div>
			
            <s:include value="sideBar/generaleAside.jsp" />			
            <!-- MODAL -->
            <div class="modal-custom">
            </div>	
            	<!-- MODALE CONFERMA ELIMINA AREA -->
           <div class="modal fade" tabindex="-1" id="deleteIsncModal" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">ELIMINA SOGLIA E COLORE</span>
	                </div>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <p>Si sta eliminando la soglia ed il colore selezionato. Procedere con l' operazione?</p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" data-dismiss="modal" class="btn default">No</button>
			        <!-- generale.js -->
			      <button type="button" class="btn blue" onclick="eliminaIsnc()">Si</button>
			      </div>
			    </div>
			  </div>
			</div>
			
			 	<!-- MODALE CONFERMA ELIMINA QUESTIONARIO -->
           <div class="modal fade" tabindex="-1" id="deleteQuestionarioModal" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">ELIMINA AUDIT</span>
	                </div>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <p>Presente un questionario associato a questo audit, procendendo con l'eliminazione i dati relativi al questionario verranno persi.<br>Procedere con l' operazione?</p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" data-dismiss="modal" class="btn default">No</button>
			        <!-- generale.js -->
			      <button type="button" class="btn blue" onclick="deleteQuestionario();">Si</button>
			      </div>
			    </div>
			  </div>
			</div>
			
			
            	
        
