<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="portlet" id="modificaNonConfAcces">
   <div class="portlet-body">
      <div class="row">
         <div class="col-lg-4">
            <div style="margin: 5px 0 15px; color: #697882; font-size: 20px; font-weight: 400;">Rischi </div>
         </div>
      </div>
      <div class="row">
         <div class="col-lg-3" style="margin-top: 5px;">
	            <div class="actions add-action">
	               <!-- cruscottoScript.js -->
	             
	               <a href="#" onclick="getCheckBoxID('statoEsprRischio')"
	                  class="btn btn-default btn-sm" style="margin-bottom: 15px; margin-right: 10px">
	               <i class="fa fa-pencil"></i> Stato Espressioni di Rischio </a>
	            </div>
       
         </div>
         <div class="col-lg-4" style="margin-top: 5px;">
	            <div class="actions add-action">
	               <!-- cruscottoScript.js -->
	               <a href="#" onclick="getCheckBoxID('getDettagliRischiAccessi')"
	                  class="btn btn-default btn-sm">
	               <i class="fa fa-pencil"></i> Dettagli</a>
	            </div>
            
         </div>
      </div>
      <table class="table table-striped table-bordered table-advance table-hover" id="rischiAccessi">
         <thead>
            <tr>
               <th style="width: 80px; text-align: center;">
                  <div class="form-group form-md-checkboxes">
                     <div class="md-checkbox-inline">
                        <div class="md-checkbox">
                        </div>
                     </div>
                  </div>
               </th>
               <th>
                  Codice
               </th>
               <th>
                  Descrizione
               </th>
               <th>
                  Quantità
               </th>
               <th>
                 Su totale%
               </th>
               <th>
                 E.R. RM/EDU Su PS%
               </th>
            </tr>
         </thead>
         <tbody>
           
         </tbody>
      </table>
   </div>
</div>