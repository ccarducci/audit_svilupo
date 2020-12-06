<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet">
   <div class="row">
      <div class="col-lg-6 col-lg-offset-4 col-xs-12">
         <div class="actions add-action">
            <a data-toggle="modal" href="#" onclick="slideAsideInps('new-expressRisk')" class="btn btn-default btn-sm">
            <i class="fa fa-plus"></i> Nuovo </a>
         </div>
         <div class="actions add-action">
            <a data-toggle="modal"  href="#" onclick="getCheckBoxID('modificaEsprRischio')" class="btn btn-default btn-sm">
            <i class="fa fa-plus"></i> Modifica </a>
         </div>
         <div class="actions add-action">
            <a data-toggle="modal" href="#" onclick="getCheckBoxID('eliminaEsprRischio')" class="btn btn-default btn-sm del">
            <i class="fa fa-trash"></i> Elimina </a>
         </div>
         <div class="actions add-action">
            <a href="#" class="btn btn-default btn-sm" onclick="reloadTable('esprRischioTab')">
            <i class="fa fa-refresh"></i> Aggiorna </a>
         </div>
      </div>
   </div>
   <div class="portlet-body">
      <table class="table table-striped table-bordered table-advance table-hover" id="espressRischioTable">
         <thead>
            <tr>
               <th style="width: 80px; text-align: center;">
                  <div class="form-group form-md-checkboxes">
                     <div class="md-checkbox-inline">
                        <div class="md-checkbox">
                           <input type="checkbox" id="checkAllEspr" class="md-check" onclick="selectAllEspr()">
                           <label for="checkAllEspr">
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
                  Rischio
               </th>
               <th>
                  Codice
               </th>
               <th>
                  Percentuale su totale
               </th>
               <th>
                  Raggruppamento rischio
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
   
   <s:include value="sideBar/esprRischioAside.jsp" />	
   <s:include value="sideBar/updateEsprRischioAside.jsp" />
   
   <div class="modal fade" tabindex="-1" id="deleteEsprRischioModal" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">Sei sicuro di voler cancellare le espressioni rischio selezionate?</span>
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
			        <button type="button" class="btn blue" onclick="eliminaEsprRischio();">Si</button>
			      </div>
			    </div>
			  </div>
			</div> 
</div>
<!-- END SAMPLE TABLE PORTLET-->