<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- Peritale -->
    <div class="portlet-body form">
        <form role="form">
            <div class="form-body">
            	<div class="row">
                    <div class="col-lg-4">
                        <div style="margin: 5px 0 15px; color: #697882; font-size: 20px; font-weight: 400;">Dati Generali</div>
                    </div>
                                                              
             	</div>
                <div class="row" style="margin-bottom: 40px;">
                  
                  <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
                	<div class="form-group form-md-line-input">
                 		<s:set var="numProt" value="%{verb.numeroProtocollo}" />
                  		<input type="text" class="form-control" id="numeroProtocollo" readonly  value="<s:property value="numProt"/>" style="height: 38px;">
                  		<label for="numeroProtocollo">
                     		Numero protocollo
                  		</label>
               		</div>
                  </div>
                
               	<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
               		<div class="form-group form-md-line-input">
                 		<s:set var="cf" value="%{verb.codiceFiscale}" />
                  		<input type="text" class="form-control" id="codFiscale" readonly  value="<s:property value="cf"/>" style="height: 38px;">
                  		<label for="codFiscale">
                     		Codice Fiscale
                  		</label>
               		</div>
               	</div>
               	
               	<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
               		<div class="form-group form-md-line-input">
                 		<s:set var="descr" value="%{verb.ragioneSociale}" />
                  		<input type="text" class="form-control" id="descrizioneMRisEspr" readonly  value="<s:property value="descr"/>" style="height: 38px;">
                  		<label for="descrizioneMRisEspr">
                     		Descrizione
                  		</label>
               		</div>
               	</div>
               </div>	
                                      
              
        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="portlet">
	       
                     <div class="portlet-body">
                         <!--<div class="table-scrollable">-->
                             <table class="table table-striped table-bordered table-advance table-hover" id="nc_pratiche">
                                 <thead>
                                     <tr>
                                         
                                         
                                         <th>
                                             Fase
                                         </th>
                                         <th>
                                             Codice NC
                                         </th>
                                         <th>
                                             Descrizione NC
                                         </th>
                                         <th>
                                             Codice Variante Comp
                                         </th>
                                         <th>
                                             Variante Comp
                                         </th>
                                         <th>
                                             Peso Variante Comp
                                         </th>
                                         <th>
                                             Colore
                                         </th>
                                         <th>
                                             Data Attribuzione
                                         </th>
                                     </tr>
                                 </thead>
                                 <tbody>
                                     
                                 </tbody>
                             </table>
                         <!--</div>-->
                     </div>
                 </div>
                 <!-- END SAMPLE TABLE PORTLET-->
		</div>
	</form>
</div>
   
    

       