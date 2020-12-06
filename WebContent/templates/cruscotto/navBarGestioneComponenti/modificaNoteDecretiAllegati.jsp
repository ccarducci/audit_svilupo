<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="portlet">
	<div class="portlet-body">
		<div class="row margin-top-10">
				<div class="col-md-12">
                    
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <div class="portlet">
				        <div class="row">
                          
                            <div class="col-lg-8 col-lg-offset-2 col-xs-12" style="padding-left: 70px;">
                                <div class="actions add-action">
                                    <a data-toggle="modal" onclick="slideAsideNew('add')" class="btn btn-default btn-sm">
                                    <i class="fa fa-plus"></i> Aggiungi </a>
                                </div>
                                <div class="actions add-action">
                                    <a data-toggle="modal" onclick="showRemoveModalNoteDecreti()" class="btn btn-default btn-sm del">
                                    <i class="fa fa-trash"></i> Elimina </a>
                                </div>
                               
                                 <div class="actions add-action">
                                  
                                    <a onclick="scaricaAllegatoNormativa(2)" class="btn btn-default btn-sm del" download>
                                    <i class="fa fa-download"></i> Scarica </a>
                                </div>
                                 
                            </div>
                        </div>
                        <div class="portlet-body">
                                <table class="table table-striped table-bordered table-advance table-hover" id="allegatiNoteDecretiTable">
                                    <thead>
                                    	<th></th>
                                    	<th>Nome file</th> 
                                        <th>Descrizione</th>
                                        <th>Data inizio</th>      
                                        
                                                         
                                    	</thead>
                                    <tbody>
                                    </tbody>
                                </table>
                        </div>
                    </div>
                    <!-- END SAMPLE TABLE PORTLET-->

				</div>
			</div>
	
	
	</div>
</div>
 <!-- MODAL -->
            <div class="modalAddAllegato">
            	<s:include value="sideBar/addAllegatoNoteDecretiAside.jsp" />	
            </div>
            
            
	
	
	<!-- MODALE CONFERMA ELIMINA DOMINIO -->
  	<div class="modal fade" tabindex="-1" id="deleteAllegatiModalND" aria-hidden="true">
	  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <div class="caption font-green">
                   <span class="caption-subject bold uppercase">ELIMINA ALLEGATO</span>
               </div>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <p>Si sta eliminando l'allegato selezionato. Procedere con l' operazione?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" data-dismiss="modal" class="btn default">No</button>
	        <button type="button" class="btn blue" onclick="eliminaAllegatoNormativa(2);">Si</button>
	      </div>
	    </div>
	  </div>
	</div>
	