<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- BEGIN SIDEBAR DX -->
<aside class="menu-sx-levelone-facility add" style="margin-top: -270px;">
   <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">NUOVO UTENTE</span>
         </div>
      </div>
      <div class="portlet-body form">
         <form role="form">
         	
            <div class="form-body">
               <label style="color:red;" id="info" hidden>I campi contrassegnati da *  sono obbligatori</label>
               <div class="row" style="margin-bottom: 40px;">
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="nome" value="" style="height: 38px;">
                        <label for="nome">Nome <i class="fa fa-asterisk obbligatorio"></i></label>
                        
                     </div>
                  </div>
                 
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="cognome" value="" style="height: 38px;">
                        <label for="cognome">Cognome <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="username" value="" style="height: 38px;">
                        <label for="username">Username <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                   
               </div>
               <div class="row" style="margin-bottom: 40px;">
				
                 
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="email" value="" style="height: 38px;">
                        <label for="email">Email </i></label>
                     </div>
                  </div>
				  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group">
                        <label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; ">Attivo
                        <i class="fa fa-asterisk obbligatorio"></i></label><br>
                        <select class="form-control input-medium select2me" data-placeholder="Attivo" id="attivo">
                           <option value="A">SI</option>
                           <option value="N">NO</option>
                        </select>
                    
                  		</div>  
                  		    
                  	</div> 
                  	<div class="col-md-4" style="margin-bottom: 15px;">
                  	<div class="form-group">
                        	<label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Ruoli
                        	<i class="fa fa-asterisk obbligatorio"></i></label><br>
	                        <div id="statoRadioButton">
		                       
								<div class="md-radio-list" id="rolechecks">	
									
									<s:iterator value="#session.validRolesList">
									    <input type="checkbox" descr="<s:property value="descrizione" />" id="<s:property value="idRuolo" />" class="md-check ">
									    
										<span style="margin-left: 10px;"><s:property value="descrizione" /> </span></>
									    <br/>
									</s:iterator>
									
								
	                              	
		                    </div>
                    
                  		</div>
                  	</div>
                  	  </div>           
               </div>
               <div class="row">
                 <div class="col-md-4" style="margin-bottom: 15px;"></div>
                  <div class="col-md-4" style="margin-bottom: 15px;"></div>
	                  	<div id="bottoniAudit" class="col-md-4" style="margin-bottom: 15px;">
	                  		<button type="button" class="btn blue" onclick="saveNewUser()">Aggiungi</button>
	               			<button type="button" class="btn default" onclick="slideAsideNew(''); hideInfo();">Chiudi</button>
	                  </div>
                  
               </div>
            </div>
         </form>
      </div>
   </div>
</aside>