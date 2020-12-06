<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet" style="margin-bottom: 0px;">
    <div class="portlet-body">
    <div class="row" style="margin-bottom: 10px;">
           
                <div class="col-lg-4 col-xs-12">
                <div class="form-group">
                <label for="selectFaseNonConf">Filtra fasi </label>
                      <s:set var="descrFase" value="%{descrizioneFase}" />
                            <select class="form-control input-medium select2me" id="selectFasePratNonConf" onchange="filtraFasePratNonConf();">
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
    </div>
    
        <div class="row" style="margin-bottom: 10px;">
            <div class="col-lg-12 col-xs-12">
                <div class="form-group">
                   <table class="table table-striped table-bordered table-advance table-hover" id="nonConfPraticheAtpo">
                <thead>
                    <tr>
                    	<th>
                    	</th>
                    	<th>
                            Ordinamento  
                        </th>
                        <th>
                            Descrizione fase 
                        </th>
                        <th>
                          	Descriz. non conformità
                        </th>
                        <th>
                            Codice variante comportamentale 
                        </th>
                        <th>
                           Variante comportamentale 
                        </th>
                        <th>
                            Peso variabile comportamentale (ISNC) 
                        </th>
                        <th>
                            Colore (ISNC)
                        </th>
                        <th>
                         Data attribuzione
                         </th>
                    </tr>
                </thead>
                <tbody>
                   
                </tbody>
            </table>
                </div>
            </div>
        </div>


    
    </div>

</div>