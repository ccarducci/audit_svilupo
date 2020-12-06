<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet">
   <div class="row">
      <div class="col-lg-8 col-lg-offset-3" style="padding-top: 18px;">
         <div class="actions add-action">
         <!-- questionarioAuditOp.js -->
            <a data-toggle="modal" href="#" onclick="checkRischiAssociati()" class="btn btn-default btn-sm" style="width:150px;">
            <i class="fa fa-plus"></i> Nuova domanda </a>
         </div>
         <div class="actions add-action">
            <a href="#" class="btn btn-default btn-sm" onclick="getCheckBoxID('modifica-domanda')" style="width:150px;">
            <i class="fa fa-edit"></i> Modifica domanda </a>
         </div>
         <div class="actions add-action">
            <a data-toggle="modal" href="#" class="btn btn-default btn-sm del" style="width:150px;" onclick="getCheckBoxID('eliminaDomanda');">
            <i class="fa fa-trash"></i> Elimina domanda </a>
         </div>
      </div>
   </div>
   <div class="portlet-body">
      <table class="table table-striped table-bordered table-advance table-hover" id="auditQuestionarioTable">
         <thead>
            <tr>
               <th style="width: 80px; text-align: center;">
                  <div class="form-group form-md-checkboxes">
                     <div class="md-checkbox-inline">
                        <div class="md-checkbox">
                           <input type="checkbox" id="checkAllDom" class="md-check" onclick="selectAllDomande()">
                           <label for="checkAllDom">
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
                  Peso
               </th>
               <th>
                  Valore Max risposta
               </th>
               <th>
                  Rischio associato
               </th>
               <th>
                  Peso %
               </th>
               <th>
                  Controllo processo
               </th>
            </tr>
         </thead>
         <tbody>
           
         </tbody>
      </table>
   </div>
   
   <div class="modal fade" tabindex="-1" id="deleteDomandaModal" aria-hidden="true">
   		<div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			   <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">Eliminare le domande selezionate?</span>
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
			        <button type="button" class="btn blue" onclick="eliminaDmd();">Si</button>
			      </div>
			   </div>
		</div>
	</div> 
   
   <s:include value="sidebar/newDomandaAside.jsp" />	
  
</div>
<!-- END SAMPLE TABLE PORTLET-->