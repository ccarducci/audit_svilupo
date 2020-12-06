<%@ taglib prefix="s" uri="/struts-tags" %>


            <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
            	<div class="form-group form-md-line-input">
        				<s:set var="valoreIncc" value="%{auSNonConf.valoreINCC}" />
         				<input type="text" class="form-control" id="incc"
            			value="<s:property value="valoreIncc" />"  style="height: 38px;" disabled>
         				<label for="incc">Valore INCC</label>
        			</div>
     
   				</div>
   				<div class="col-md-4">
   					<div class="form-group form-md-line-input">
        				<s:set var="valorePraticaNonsoggetta" value="%{auSNonConf.praticaNonSoggetta}" />
         				<input type="text" class="form-control" id="valorePraticaNonsoggetta"
           				 value="<s:property value="valorePraticaNonsoggetta" />"  style="height: 38px;" disabled>
         				<label for="valorePraticaNonsoggetta">Valore pratica non soggetta</label>
        			</div>  
   				</div>
   


	<div class="col-md-12 col-xs-12" style="margin-bottom: 15px;">
	<table class="table table-striped table-bordered table-advance table-hover" id="variantComp">
         <thead>
            <tr>
               <th style="display:none;">
               </th>
               <th>
                  Descrizione Varianti Comportamentali
               </th>
               <th>
                  % su PS
               </th>
               <th>
                  Criticita
               </th>
               <th>
                  Azioni correttive
               </th>
               <th>
               </th>
               
            </tr>
         </thead>
         <tbody>
            <!-- pratiche.js -->
         </tbody>
      </table>
</div>