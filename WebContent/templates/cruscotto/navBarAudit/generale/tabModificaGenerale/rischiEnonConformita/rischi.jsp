<%@ taglib prefix="s" uri="/struts-tags" %>
   <!-- BEGIN SAMPLE TABLE PORTLET-->
   <div class="portlet">
      <div class="row">
         <div class="col-lg-6 col-lg-offset-4 col-xs-12">
            <div class="actions add-action">
               <a data-toggle="modal" href="#" onclick="slideAsideInps('new-risk')" class="btn btn-default btn-sm">
               <i class="fa fa-plus"></i> Nuovo </a>
            </div>
            <div class="actions add-action">
               <a data-toggle="modal" href="#" onclick="getCheckBoxID('modificaRischio')" class="btn btn-default btn-sm">
               <i class="fa fa-plus"></i> Modifica </a>
            </div>
            <div class="actions add-action">
               <a data-toggle="modal" href="#" onclick="getCheckBoxID('eliminaRischio');" class="btn btn-default btn-sm del">
               <i class="fa fa-trash"></i> Elimina </a>
            </div>
            <div class="actions add-action">
               <a href="#" onclick="reloadTable('rischiEnonConformitaRischioTable')" class="btn btn-default btn-sm">
               <i class="fa fa-refresh"></i> Aggiorna </a>
            </div>
         </div>
      </div>
      <div class="portlet-body">
         <table class="table table-striped table-bordered table-advance table-hover" id="rischiEnonConformitaRischioTable">
            <thead>
               <tr>
                  <th style="width: 80px; text-align: center;">
					<div class="form-group form-md-checkboxes">
						<div class="md-checkbox-inline">
							<div class="md-checkbox">
								<input type="checkbox" id="checkAll" onclick="selectAll()" class="md-check chkRischioAll">
                				<label for="checkAll">
                    				<span></span>
                    				<span class="check"></span>
                    				<span class="box"></span>
                				</label>
            				</div>
        				</div>
    				 </div>
				  </th>
                  <th>
                     Descrizione
                  </th>
                  <th>
                     Codice rischio
                  </th>
                  <th>
                     Peso rischio
                  </th>
                  <th>
                     Valore per media
                  </th>
                  <th>
                     Data inizio
                  </th>
                  <th>
                     Data fine
                  </th>
               </tr>
            </thead>
            <tbody>
              
            </tbody>
         </table>
      </div>
      <!-- aside -->
            
            	<s:include value="sideBar/rischioAside.jsp" />	
            	<s:include value="sideBar/updateRischioAside.jsp" />
         
         
          <div class="modal fade" tabindex="-1" id="deleteRischioModal" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">Sei sicuro di voler cancellare i rischi selezionati?</span>
	                </div>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <!-- <div class="modal-body">
			        <p>Si sta eliminando la risposta selezionata. Procedere con l' operazione?</p>
			      </div>-->
			      <div class="modal-footer">
			        <button type="button" data-dismiss="modal" class="btn default">No</button>
			        <!-- generale.js -->
			        <button type="button" class="btn blue" onclick="eliminaRischio();">Si</button>
			      </div>
			    </div>
			  </div>
			</div> 
      
   </div>
   <!-- END SAMPLE TABLE PORTLET-->
 