<%@ taglib prefix="s" uri="/struts-tags" %>
<style>
	.centerText { text-align: center }
	
</style>
<div class="portlet" id="modificaNonConfAcces">
   <div class="portlet-body">
      <div class="row">
         <div class="col-lg-3">
            <div style="margin: 5px 0 15px; color: #697882; font-size: 20px; font-weight: 400;">Non Conformità </div>
         </div>
      </div>
        <div class="row">
        
        <div class="col-lg-3" style="margin-top: 5px;">
        		<div class="form-group">
              		 Filtra fase
                      <s:set var="descrFase" value="%{descrizioneFaseAcc}" />
                            <select class="form-control input-medium select2me" id="selectFaseAccNonConf" onchange="filtraFaseAccNonConf();">
                                <option selected value="" disabled></option>
                                <s:iterator value="descrFase" var="recInfoP">
                    		   		
                        				<option>
                            				<s:property value="#recInfoP" />
                            			</option>
                            	
                        		</s:iterator>
                        		<option>Tutte</option>
                            </select>
                       	 		
                
                </div>
                </div>
                <div class="col-lg-3" style="margin-top: 5px;">
	            <div class="actions add-action">
	               <!-- cruscottoScript.js -->
	             
	               <a href="#" onclick="getCheckBoxID('statoVarComp')"
	                  class="btn btn-default btn-sm" style="margin-top: 15px; margin-right: 10px">
	               <i class="fa fa-pencil"></i> Stato Varianti comportamentali </a>
	            </div>
       
         </div>
         <div class="col-lg-2" style="margin-top: 5px;">
	            <div class="actions add-action">
	               <!-- cruscottoScript.js -->
	             
	               <a href="#" onclick="getCheckBoxID('dettagliNonConfAccessi')"
	                  class="btn btn-default btn-sm" style="margin-top: 15px; margin-right: 10px">
	               <i class="fa fa-pencil"></i> Dettagli </a>
	            </div>
       
         </div>
         
     
     
      		
      </div>
      <table class="table table-striped table-bordered table-advance table-hover" id="nonConformAccessi">
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
                  Ordinamento
               </th>
                <th>
                  Fase
               </th>
               <th>
                  Non Conformità
               </th>
               <th >
                  Codice
               </th>
               <th>
                  Quantità
               </th>    
               <th>
                  Perc su Tot
               </th>
               <th>
                  valore (INCC)
               </th>
               <th>
                  Descrittivo INCC
               </th>
            </tr>
         </thead>
         <tbody>
           
         </tbody>
      </table>
   </div>
</div>