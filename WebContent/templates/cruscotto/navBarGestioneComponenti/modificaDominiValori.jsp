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
                                    <a data-toggle="modal" onclick="initDatePicker();showAsideNewDocumentiValori('I');return false;" class="btn btn-default btn-sm">
                                    <i class="fa fa-plus"></i> Nuovo </a>
                                </div>
                                <div class="actions add-action">
                                    <a data-toggle="modal" onclick="modificaDominiValori()" class="btn btn-default btn-sm del">
                                    <i class="fa fa-pencil"></i> Modifica </a>
                                </div>
                               
                                 <div class="actions add-action">
                                  
                                    <a onclick="showRemoveModalDomVal()" class="btn btn-default btn-sm del" download>
                                    <i class="fa fa-trash"></i> Eliminia </a>
                                </div>
                                 
                            </div>
                        </div>
                        <div class="portlet-body">
                                <table class="table table-striped table-bordered table-advance table-hover" id="dominiValoriTable">
                                    <thead>
                                    	<th style="text-align: center;">
                                    		<div class="form-group form-md-checkboxes">
                     							<div class="md-checkbox-inline">
                        							<div class="md-checkbox">
                           								<input type="checkbox" id="checkAllDomVal" class="md-check" onclick="selectAllDomVal()">
                           									<label for="checkAllDomVal">
                           										<span></span>
                           										<span class="check"></span>
                           										<span class="box"></span>
                           									</label>
                        							</div>
                     							</div>
                  							</div>
                                    	
                                    	</th>
                                    	<th>Codice Dominio</th>
                                    	<th>Codice</th>
                                        <th>Descrizione</th>
                                        <th>Data Inizio</th>      
                                        <th>Data Fine</th>
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
            <div class="modalAddDominiValori">
            	<s:include value="sideBar/addDominiValoriAside.jsp" />	
            </div>
            
            
	
	
	<!-- MODALE CONFERMA ELIMINA DOMINIO -->
  	<div class="modal fade" tabindex="-1" id="deleteDominivaloriModal" aria-hidden="true">
	  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <div class="caption font-green">
                   <span class="caption-subject bold uppercase">ELIMINA VALORI</span>
               </div>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <p>Si sta eliminando il valore selezionato. Procedere con l' operazione?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" data-dismiss="modal" class="btn default">No</button>
	        <button type="button" class="btn blue" onclick="eliminaDominiValori();">Si</button>
	      </div>
	    </div>
	  </div>
	</div>
	