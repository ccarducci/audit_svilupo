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
		<h1>Processo <!-- <small>- Processo</small> --></h1>
	</div>
             <!-- BEGIN PAGE BREADCRUMB -->
             <ul class="page-breadcrumb breadcrumb">
                 <li>
                     Modellazione
                     <i class="fa fa-circle"></i>
                 </li>
                 <li>
                     Processo
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
                                 <a data-toggle="modal" href="#" onclick="showAsideProcesso();return false;"  class="btn btn-default btn-sm">
                                 <i class="fa fa-plus"></i> Nuovo </a>
                             </div>
                             <div class="actions add-action">
                                   <a href="#" onclick="getCheckBoxID('processoGenerale');" class="btn btn-default btn-sm">
                                 <i class="fa fa-pencil"></i> Modifica </a>
                             </div>
                             <div class="actions add-action">
                                <a onclick="getCheckBoxID('pubblicaProcesso');" class="btn btn-default btn-sm">
                                 <i class="fa fa-check sconto-up active-scont" style="color: green;" ></i>
                                 	Pubblica
                               </a>
                            </div>
                            <!--
                            <div class="actions add-action">
                            		<a onclick="getCheckBoxID('disattivaProcesso');" class="btn btn-default btn-sm">
                           			<i class="fa fa-close sconto-down" style="color: red;"></i>
                           			Disattiva 
                                </a>
                            </div>
                            -->
                             <div class="actions add-action">
                                 <a data-toggle="modal" onclick="getCheckBoxID('eliminaProcesso');" class="btn btn-default btn-sm del">
                                 <i class="fa fa-trash"></i> Elimina </a>
                             </div>
                         </div>
                     </div>
                     <div class="portlet-body">
                         <!--<div class="table-scrollable">-->
                             <table class="table table-striped table-bordered table-advance table-hover" id="processo_table">
                                 <thead>
                                     <tr>
                                         <th style="text-align: center;">
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
                                             Data inizio
                                         </th>
                                         <th>
                                             Data fine
                                         </th>
                                         <th style="text-align: center;">
                                             Pubblicazione
                                         </th>
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

         <s:include value="sideBar/processo_aside.jsp" />	
         <!-- MODAL -->
         <div class="modalProcesso modal-custom">
         </div>
         
         <!-- MODALE CONFERMA ELIMINA PROCESSO -->
        <div class="modal fade" tabindex="-1" id="deleteProcessoModal" aria-hidden="true">
		  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
		    <div class="modal-content">
		      <div class="modal-header">
		        <div class="caption font-green">
		                  <span class="caption-subject bold uppercase">ELIMINA PROCESSO</span>
		              </div>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <p>Si sta eliminando il processo selezionato. Continuare con l' operazione?</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" data-dismiss="modal" class="btn default">No</button>
		        <button type="button" class="btn blue" onclick="eliminaProcesso();">Si</button>
		      </div>
		    </div>
		  </div>
		</div>
         
         <!-- MODALE PER GESTIONE MESSAGGI DA DARE ALL'UENTE IN CASO DI ERRORI/WARNING -->
         <div class="modal fade" tabindex="-1" id="processoModal" aria-hidden="true">
		  <div class="modal-dialog"  style="width: 550px !important; height: 200px !important;">
		    <div class="modal-content">
		      <div class="modal-header">
		        <div class="caption font-green">
		                  <span class="caption-subject bold uppercase">GESTIONE PROCESSO</span>
		              </div>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body" id="messageProcessoDiv">
		        <p></p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" data-dismiss="modal" class="btn default">Chiudi</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
		<!-- MODALE CONFERMA PUBBLICAZIONE PROCESSO -->
        <div class="modal fade" tabindex="-1" id="pubblicaProcessoModal" aria-hidden="true">
		  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
		    <div class="modal-content">
		      <div class="modal-header">
		        <div class="caption font-green">
		                  <span class="caption-subject bold uppercase">PUBBLICAZIONE PROCESSO</span>
		              </div>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <p>Si sta pubblicando il processo selezionato.</p>
		        	L' operazione sarà IRREVERSIBILE. Si desidera continuare?
		      </div>
		      <div class="modal-footer">
		        <button type="button" data-dismiss="modal" class="btn default">No</button>
		        <button type="button" class="btn blue" onclick="pubblicaProcessoModal();">Si</button>
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
