<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet">
   <div class="row">
      <div class="col-lg-6 col-lg-offset-4 col-xs-12">
         <div class="actions add-action">
            <a data-toggle="modal" href="#" onclick="slideAsideInps('new-varianteNot');" class="btn btn-default btn-sm">
            <i class="fa fa-plus"></i> Nuovo </a>
         </div>
         <div class="actions add-action">
            <a data-toggle="modal" href="#" onclick="getCheckBoxID('varComportamentale')" class="btn btn-default btn-sm">
            <i class="fa fa-plus"></i> Modifica </a>
         </div>
         <div class="actions add-action">
            <a data-toggle="modal" href="#" onclick="getCheckBoxID('deleteVarComportamentale')" class="btn btn-default btn-sm del">
            <i class="fa fa-trash"></i> Elimina </a>
         </div>
         <div class="actions add-action">
            <a href="javascript:;" class="btn btn-default btn-sm">
            <i class="fa fa-refresh"></i> Aggiorna </a>
         </div>
      </div>
   </div>
   <div class="portlet-body">
      <table class="table table-striped table-bordered table-advance table-hover" id="rischiEnonConformitaVarCompTable">
         <thead>
            <tr>
               <th style="width: 80px; text-align: center;">
                  <div class="form-group form-md-checkboxes">
                     <div class="md-checkbox-inline">
                        <div class="md-checkbox">
                           <input type="checkbox" id="checkAllvarComp" class="md-check" onclick="selectAllVC()">
                           <label for="checkAllvarComp">
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
                  Non conformità
               </th>
               <th>
                  Codice
               </th>
               <th>
                  Peso VC
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
   <s:include value="sideBar/varianteComportamentaleAside.jsp" />	
   <s:include value="sideBar/updateVarianteComportamentaleAside.jsp" />
            	
            	<div class="modal fade" tabindex="-1" id="deleteVcModal" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">E stata richiesta l eliminazione della 'variante comportamentale', si vuole procedere?</span>
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
			        <button type="button" class="btn blue" onclick="eliminaVC();">Si</button>
			      </div>
			    </div>
			  </div>
			</div> 
</div>
<!-- END SAMPLE TABLE PORTLET-->