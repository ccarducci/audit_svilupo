<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.web.beans.AppUser;"%>
<%
   AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
   		
   %> 

		<!-- BEGIN PAGE HEAD -->
		<div class="page-head">
			<!-- BEGIN PAGE TITLE -->
			<div class="page-title">
                   <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
				<h1>Area <!-- <small>- Area</small> --></h1>
			</div>
               <!-- BEGIN PAGE BREADCRUMB -->
               <ul class="page-breadcrumb breadcrumb">
                   <li>
                       Modellazione
                       <i class="fa fa-circle"></i>
                   </li>
                   <li>
                       Area
                   </li>
               </ul>
               <!-- END PAGE BREADCRUMB -->
			<!-- END PAGE TITLE -->
               
		</div>
		<!-- END PAGE HEAD -->
		
		<!-- BEGIN PAGE CONTENT INNER -->
		<div class="row margin-top-10">
			<div class="col-md-12">

                   <!-- BEGIN AREA TABLE PORTLET-->
                   <div class="portlet">
			        <div class="row">
			        			        
                           <div class="col-lg-6 col-lg-offset-3 col-xs-12" style="margin-left: 400;">
                               <div class="actions add-action">
                               <!-- admin/layout4/scripts/custom.js -->
                                   <a data-toggle="modal" href="#" onclick="showAsideArea('INS');return false;" class="btn btn-default btn-sm">
                                   <i class="fa fa-plus"></i> Nuovo </a>
                               </div>
                               <div class="actions add-action">
                               <!-- admin/layout4/scripts/custom.js -->
                                   <a data-toggle="modal" href="#" onclick="getCheckBoxID('modificaArea');" class="btn btn-default btn-sm">
                                   <i class="fa fa-pencil"></i> Modifica </a>
                               </div>
                               <div class="actions add-action">
                                   <a data-toggle="modal" onclick="getCheckBoxID('eliminaArea');" class="btn btn-default btn-sm del">
                                   <i class="fa fa-trash"></i> Elimina </a>
                               </div>
                               <!--
                               <div class="actions add-action">
                                   <a href="javascript:;" class="btn btn-default btn-sm">
                                       <span class="like-sconto">
                                           <i class="fa fa-check sconto-up active-scont" onclick="getCheckBoxID('attivaArea');" style="color: green;" >
                                           	<span> Attiva</span></i>
                                           <i class="fa fa-close sconto-down" style="color: red;">
                                           	<span> Disattiva</span></i> 
                                       </span>
                                   </a>
                               </div>
                               -->
                               <div class="actions add-action">
                                   <a onclick="aggiornaAreaTable();" class="btn btn-default btn-sm">
                                   <i class="fa fa-refresh"></i> Aggiorna </a>
                               </div>
                           </div>
                       </div>
                       <div class="portlet-body">
                           <!--<div class="table-scrollable">-->
                               <table class="table table-striped table-bordered table-advance table-hover" id="area_table">
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
                                               Descrizione
                                           </th>
                                           <th>
                                               Data inizio
                                           </th>
                                           <th>
                                               Data fine
                                           </th>
                                           <!-- 
                                           <th>
                                               Stato
                                           </th>
                                           -->
                                       </tr>
                                   </thead>
                                   <tbody>
                                   
                                   </tbody>
                               </table>
                           <!--</div>-->
                       </div>
                   </div>
                   <!-- END AREA TABLE PORTLET-->

			</div>
		</div>
		
           <s:include value="sideBar/area_aside.jsp" />
           <!-- MODAL -->
           <div class="modalArea modal-custom">
           </div>
           
           <!-- MODALE CONFERMA ELIMINA AREA -->
           <div class="modal fade" tabindex="-1" id="deleteModal" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">ELIMINA AREA</span>
	                </div>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <p>Si sta eliminando l' area selezionata. Procede con l' operazione?</p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" data-dismiss="modal" class="btn default">No</button>
			        <button type="button" class="btn blue" onclick="eliminaArea();">Si</button>
			      </div>
			    </div>
			  </div>
			</div>
			
			
			<!-- MODALE PER GESTIONE MESSAGGI DA DARE ALL'UENTE IN CASO DI ERRORI/WARNING -->
           <div class="modal fade" tabindex="-1" id="activeModal" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">GESTIONE AREA</span>
	                </div>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body" id="messageDiv">
			        <p></p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" data-dismiss="modal" class="btn default">Chiudi</button>
			      </div>
			    </div>
			  </div>
			</div>
			
			
			<!-- MODALE CONFERMA CHIUSURA VALIDITA AREA -->
           <div class="modal fade" tabindex="-1" id="validitaModal" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">CONTROLLO VALIDITA' AREA</span>
	                </div>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <p>Attenzione! La Data Fine Validità impostata verrà propagata a tutte le entità collegate alla seguente Area. Procede con l' operazione?</p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" data-dismiss="modal" class="btn default">No</button>
			        <button type="button" class="btn blue" onclick="salvaArea();">Si</button>
			      </div>
			    </div>
			  </div>
			</div>
           
           	
           
   
           
		<!-- END PAGE CONTENT INNER -->

<!-- END CONTENT -->

<script>
	jQuery(document).ready(function() {
		ComponentsPickers.init();
    });
</script>
