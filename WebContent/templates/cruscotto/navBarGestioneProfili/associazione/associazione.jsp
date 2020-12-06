<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.web.beans.AppUser;"%>

	<!-- BEGIN CONTENT -->
	
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
                    <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
					<h1>Associazione <!-- <small>- Generale</small> --></h1>
				</div>
                <!-- BEGIN PAGE BREADCRUMB -->
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        Gestione Profili
                        <i class="fa fa-circle"></i>
                    </li>
                    <li>
                        Associazione
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
                                      <!-- admin/layout4/scripts/custom.js -->
                                   <a data-toggle="modal" href="#" onclick="" class="btn btn-default btn-sm">
                                    <i class="fa fa-plus"></i> Nuovo </a>
                                </div>
                                <div class="actions add-action">
                                <!-- cruscottoScript.js -->
                                    <a href="#" onclick="" class="btn btn-default btn-sm">
                                    <i class="fa fa-pencil"></i> Modifica </a>
                                </div>
                                <div class="actions add-action">
                                <!-- cruscottoscript.js -->
                                     <a data-toggle="modal" onclick="" class="btn btn-default btn-sm del">
                                    <i class="fa fa-trash"></i> Elimina </a>
                                </div>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <!--<div class="table-scrollable">-->
                                <table class="table table-striped table-bordered table-advance table-hover" id="associazione_table">
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
                                                Nome Dirigente
                                            </th>
                                            <th>
                                                Cognome Dirigente
                                            </th>
                                            <th>
                                                Utenza Dirigente
                                            </th>
                                            <th>
                                                Nome Delegato
                                            </th>
                                            <th>
                                                Cognome Delegato
                                            </th>
                                            <th>
                                                Utenza Delegato
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
			
            <!-- MODAL -->

            		
            	<!-- MODALE CONFERMA ELIMINA AREA -->
           <div class="modal fade" tabindex="-1" id="deleteAssociazioneModal" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">ELIMINA ASSOCIAZIONE</span>
	                </div>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <p>Si sta eliminando l'associazione selezionata. Procedere con l'eliminazione?</p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" data-dismiss="modal" class="btn default">No</button>
			        <!-- generale.js -->
			      <button type="button" class="btn blue" onclick="">Si</button>
			      </div>
			    </div>
			  </div>
			</div>