<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.web.beans.AppUser;"%>
<%
   AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
   		
   %> 
<!-- BEGIN CONTENT -->
<!-- BEGIN PAGE HEAD -->
<div class="page-head">
	<!-- BEGIN PAGE TITLE -->
	<div class="page-title">
                 <!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
		<h1>Attivita Componente <!-- <small>- Processo</small> --></h1>
	</div>
             <!-- BEGIN PAGE BREADCRUMB -->
             <ul class="page-breadcrumb breadcrumb">
                 <li>
                     Modellazione
                     <i class="fa fa-circle"></i>
                 </li>
                 <li>
                     Attivita Componente
                 </li>
             </ul>
             <!-- END PAGE BREADCRUMB -->
	<!-- END PAGE TITLE -->
             
</div>
<!-- END PAGE HEAD -->

<!-- BEGIN PAGE CONTENT INNER -->
<div class="row margin-top-10">
	<div class="col-md-12">

                 <!-- BEGIN PROCESS TABLE PORTLET-->
                 <div class="portlet">
	        		<div class="row">
                         <div class="col-lg-6 col-lg-offset-3 col-xs-12" style="margin-left: 400;">
                             <div class="actions add-action">
                                 <a data-toggle="modal" href="#" onclick="showAsideAttivitaComponente();return false;"  class="btn btn-default btn-sm">
                                 <i class="fa fa-plus"></i> Nuovo </a>
                             </div>
                             <div class="actions add-action">
                                   <a href="#" onclick="getCheckBoxID('attivitaComponenteGenerale');" class="btn btn-default btn-sm">
                                 <i class="fa fa-pencil"></i> Modifica </a>
                             </div>
                             
                            
                             <div class="actions add-action">
                                 <a data-toggle="modal" onclick="getCheckBoxID('eliminaAttivitaComponente');" class="btn btn-default btn-sm del">
                                 <i class="fa fa-trash"></i> Elimina </a>
                             </div>
                         </div>
                     </div>
                     <div class="portlet-body">
                         <!--<div class="table-scrollable">-->
                             <table class="table table-striped table-bordered table-advance table-hover" id="attivitacomponente_table">
	                             <thead>
	                                 <tr>
                                     <th>
                        				</th>
                        				<th>
                                           Ordinamento
                                       </th>
                                       <th>
                                           Descrizione
                                       </th>
                                       <th>
                                           Area
                                       </th>
                                       <th>
                                           Processo
                                       </th>
                                       <th>
                                           Fase
                                       </th>
                                       <th>
                                           Data Inizio
                                       </th>
                                       <th>
                                           Data Fine
                                       </th>
                                       <!-- 
                                       <th>
                                           Vincolo
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
                 <!-- END PROCESS TABLE PORTLET-->

	</div>
</div>

         <s:include value="sideBar/attivitaComponente_aside.jsp" />	
         <!-- MODAL -->
         <div class="modalAttivitaComponente modal-custom">
         </div>
         
         <!-- MODALE CONFERMA ELIMINA ATTIVITA COMPONENTE -->
        <div class="modal fade" tabindex="-1" id="deleteAttivitaComponenteModal" aria-hidden="true">
		  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
		    <div class="modal-content">
		      <div class="modal-header">
		        <div class="caption font-green">
		                  <span class="caption-subject bold uppercase">ELIMINA ATTIVITA COMPONENTE</span>
		              </div>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <p>Si sta eliminando l' Attivita Componente selezionata. Continuare con l' operazione?</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" data-dismiss="modal" class="btn default">No</button>
		        <button type="button" class="btn blue" onclick="eliminaAttivitaComponente();">Si</button>
		      </div>
		    </div>
		  </div>
		</div>
         
         <!-- MODALE PER GESTIONE MESSAGGI DA DARE ALL'UENTE IN CASO DI ERRORI/WARNING -->
         <div class="modal fade" tabindex="-1" id="attivitaComponenteModal" aria-hidden="true">
		  <div class="modal-dialog"  style="width: 550px !important; height: 200px !important;">
		    <div class="modal-content">
		      <div class="modal-header">
		        <div class="caption font-green">
		                  <span class="caption-subject bold uppercase">GESTIONE ATTIVITA COMPONENTE</span>
		              </div>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body" id="messageAttivitaComponenteDiv">
		        <p></p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" data-dismiss="modal" class="btn default">Chiudi</button>
		      </div>
		    </div>
		  </div>
		</div>

	<!-- END CONTENT -->
	
<script>
	jQuery(document).ready(function() {
		ComponentsPickers.init();
    });
</script>
