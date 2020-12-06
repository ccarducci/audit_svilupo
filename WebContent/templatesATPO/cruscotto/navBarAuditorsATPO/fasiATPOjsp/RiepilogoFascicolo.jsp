<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.session.DatiUtente"%>

	<s:set var="statoEsamePratica" value="%{statoEsamePratica.trim()}" />
	<s:set var="praticaModificabile" value="%{praticaModificable}" />
	
	<s:if test='%{#praticaModificabile == "V"}'>
		<s:if test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_APERTO}'>
			<s:set var="salvataggioAbilitato" value="%{''}" />
		</s:if>
		<s:elseif test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_IN_LAVORAZIONE}'>
		    <s:set var="salvataggioAbilitato" value="%{''}" />
		</s:elseif>
		<s:elseif test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_CHIUSO}'>
		    <s:set var="salvataggioAbilitato" value="%{'disabled'}" />
		</s:elseif>
		<s:elseif test='%{#statoEsamePratica == @it.tecnet.crs.util.PraticaUtil@ESAME_PRATICA_ELABORAZIONE}'>
	   		<s:set var="salvataggioAbilitato" value="%{'disabled'}" />
		</s:elseif>
	</s:if>
	<s:else>
			<s:set var="salvataggioAbilitato" value="%{'disabled'}" />
	   		<s:set var="CalcolaIndicatoriPraticaButton" value="%{'disabled'}" />
	</s:else>

<!-- Riepilogo fascicolo -->
<div class="portlet-body form">
   <form role="form">
      <div class="form-body">
      	
         <div class="row" style="margin-bottom: 30px;">
            <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="form_control_2" style="font-size: 13px; color: #888888;">Fascicolo </label><br>
                  <s:set var="fascicoloElettronico" value="%{fascicolo.fascicoloElettronico}" />
                   <s:set var="codFascicoloElettronico" value="%{codFascElettr}" />
                  <select id="fascicoloElettronico" class="form-control select2me" onchange="checkDocMancante()">
                     <option selected value="<s:property value="#codFascicoloElettronico"/>"><s:property value="fascicoloElettronico"/></option>
                    <s:iterator value="fascicoliElettroniciEcartacei" var="fascElettr">
                			
                    			<option id="<s:property value="#fascElettr.idTplTopologiche" />" value="<s:property value="#fascElettr.codifica" />">
                        			<s:property value="#fascElettr.descrizione" />
                   				</option>
                		
            		</s:iterator>
                  </select>
               </div>
            </div>
            <!--   <div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
               <div class="form-group" style="margin-bottom: 0;">
                  <label for="form_control_2" style="font-size: 13px; color: #888888;">Fascicolo cartaceo</label><br>
                  <s:set var="fascicoloCartaceo" value="%{fascicolo.fascicoloCartaceo}" />
                  <s:set var="codFascicoloCartaceo" value="%{codFascCart}" />
                  <select id="fascicoloCartaceo" class="form-control select2me" onchange="checkDocMancante()">
                     <option selected value="<s:property value="#codFascicoloCartaceo"/>"><s:property value="fascicoloCartaceo"/></option>
                    <s:iterator value="fascicoliElettroniciEcartacei" var="fascCart">
                			
                    			<option id="<s:property value="#fascCart.idTplTopologiche" />" value="<s:property value="#fascCart.codifica" />">
                        			<s:property value="#fascCart.descrizione" />
                   				</option>
                			
            		</s:iterator>
                  </select>
               </div>
            </div> -->
            
         </div>
         <div class="row" style="margin-bottom:30px">
         	<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
               <div class="form-group" style="margin-bottom: 0;">
      	 		 		<div class="form-actions noborder" style="padding-top: 0;">
         					<button type="button" class="btn blue" onclick="salvaRiepilogoFascicolo()" <s:property value="salvataggioAbilitato" /> >Salva</button>
         					<button type="button" class="btn default" onclick="loadDiv('/CruscottoAuditAtpoWebWeb/getRiepilogoFascicolo', 'riepFascicolo', null, 'initDocMancanteTable');" <s:property value="salvataggioAbilitato" /> >Annulla</button>
     					</div>
     			</div>
     		</div>
         </div>
         
         
        <div class="row">
        <div class="col-lg-4" >
        	<div style="margin: 5px 0 15px; color: #697882; font-size: 20px; font-weight: 400;">Documentazione mancante</div>
        </div>
       		<div class="col-lg-4" >
              
               
                	<div class="actions add-action">
                		<a data-toggle="modal" id="addDoc" href="#" onclick="checkFascicoli()" class="btn blue" <s:property value="salvataggioAbilitato" /> >
                    	<i class="fa fa-edit"></i> Aggiungi  </a>
               	   </div>
                	<div class="actions add-action">
                   		<a data-toggle="modal" id="removeDoc" onclick="eliminaDocMancante()" href="#" class="btn default" style="width:150px;" <s:property value="salvataggioAbilitato" /> >
                   		<i class="fa fa-trash"></i> Rimuovi </a>
               		</div>
                                    
            	
           </div>
        </div>
          <div class="row" style="margin-bottom: 10px;">
 			<div class="col-lg-12 col-xs-12">
    			<div class="form-group">
        			<table class="table table-striped table-bordered table-advance table-hover" id="docMancanteTable">
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
                        
                    		</tr>
                		</thead>
               			<tbody>
                   
                		</tbody>
            		</table>
        		</div>
     		</div>
		</div>
    </div>
         
     <s:include value="sidebar/asideDocMancante.jsp" />			
         <div class="modal fade" tabindex="-1" id="deleteDocManc" aria-hidden="true">
			  <div class="modal-dialog"  style="width: 500px !important; height: 200px !important;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <div class="caption font-green">
	                    <span class="caption-subject bold uppercase">Sei sicuro di voler cancellare i record selezionati?</span>
	                </div>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			    
			      <div class="modal-footer">
			        <button type="button" data-dismiss="modal" class="btn default">No</button>
			        <!-- generale.js -->
			        <button type="button" class="btn blue" onclick="eliminaDettDocManc();">Si</button>
			      </div>
			    </div>
			  </div>
			</div> 
   </form>
</div>

