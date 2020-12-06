<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- BEGIN SIDEBAR DX -->
<aside class="menu-sx-levelone-facility add" style="margin-top: -270px;">
   <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">NUOVA SOGLIA E COLORE</span>
         </div>
      </div>
      <div class="portlet-body form">
         <form role="form">
         	
            <div class="form-body">
               <label style="color:red;" id="info" hidden>I campi contrassegnati da *  sono obbligatori</label>
               <div class="row" style="margin-bottom: 40px;">
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="soglia" value="" style="height: 38px;">
                        <label for="soglia">Soglia <i class="fa fa-asterisk obbligatorio"></i></label>
                        
                     </div>
                  </div>
                 
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input">
                     <input id="dataInizio" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                        <label for="dataInizio">Data Inizio <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input">
                     	<input id="dataFine" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                        <label for="dataFine">Data Fine </label>
                     </div>
                  </div>
                   
               </div>
               <div class="row" style="margin-bottom: 40px;">
				
                 
                 
				  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group">
                        <label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; ">Colore
                        <i class="fa fa-asterisk obbligatorio"></i></label><br>
                        <select class="form-control input-medium select2me" data-placeholder="Colore" id="colore">
                           <option value="Rosso">Rosso</option>
                           <option value="Verde">Verde</option>
                           <option value="Giallo">Giallo</option>
                           <option value="Arancio">Arancio</option>
                           <option value="Bianco">Bianco</option>
                           
                        </select>
                    
                  		</div>  
                  		    
                  	</div> 
                  	<div class="col-md-4" style="margin-bottom: 15px;">
                  	<div class="form-group">
                        	<label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Audit
                        	<i class="fa fa-asterisk obbligatorio"></i></label><br>
	                        <div class="">
									<select id="idAudit" class="form-control select2me">
										<s:iterator value="#session.auditList">
											<option id="<s:property value="idAudit" />" value="<s:property value="idAudit" />"><s:property value="nome" /></option>    
										</s:iterator>
          							</select>
							</div>
                    
                  		</div>
                  	</div>
                  	  </div>           
               </div>
               <div class="row">
                 <div class="col-md-4" style="margin-bottom: 15px;"></div>
                  <div class="col-md-4" style="margin-bottom: 15px;"></div>
	                  	<div id="bottoniAudit" class="col-md-4" style="margin-bottom: 15px;">
	                  		<button type="button" class="btn blue" onclick="saveNewIsnc()">Aggiungi</button>
	               			<button type="button" class="btn default" onclick="slideAsideNew(''); hideInfo();">Chiudi</button>
	                  </div>
                  
               </div>
            </div>
         </form>
      </div>
   </div>
</aside>