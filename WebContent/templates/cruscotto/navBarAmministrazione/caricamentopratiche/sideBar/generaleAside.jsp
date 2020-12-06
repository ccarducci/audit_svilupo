<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- BEGIN SIDEBAR DX -->
<aside class="menu-sx-levelone-facility add" style="margin-top: -270px;">
   <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">NUOVO CARICAMENTO PRATICHE</span>
         </div>
      </div>
      <div class="portlet-body form">
         <form role="form">
         	
            <div class="form-body">
               <label style="color:red;" id="info" hidden>I campi contrassegnati da *  sono obbligatori</label>
               <div class="row" style="margin-bottom: 40px;">
                  <div class="col-md-4"  >
                     <div class="form-group">
                        	<label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Campagna
                        	<i class="fa fa-asterisk obbligatorio"></i></label><br>
                        	
                        	<div class="">
									<select id="idCampagna" class="form-control select2me">
										<s:iterator value="#session.campagneList">
											<option id="<s:property value="idCampagna" />" value="<s:property value="idCampagna" />"><s:property value="nome" /></option>    
										</s:iterator>
          							</select>
							</div>
                    
                  	</div>
                  </div>
                 
                  <div class="col-md-4" >
                     <div class="form-group">
                       <label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Sede
                        	<i class="fa fa-asterisk obbligatorio"></i></label><br>
                        <div class="">
									<select id="codiceSede" class="form-control select2me">
										<s:iterator value="#session.sediList">
											<option id="<s:property value="codiceSede" />" value="<s:property value="codiceSede" />"><s:property value="nomeSede" /></option>    
										</s:iterator>
          							</select>
							</div>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="annoConclusione" value="" style="height: 38px;">
                        <label for="annoConclusione">Anno conclusione <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                   
               </div>
              
               <div class="row">
                 <div class="col-md-4" style="margin-bottom: 15px;"></div>
                  <div class="col-md-4" style="margin-bottom: 15px;"></div>
	                  	<div id="bottoniAudit" class="col-md-4" style="margin-bottom: 15px;">
	                  		<button type="button" class="btn blue" onclick="saveNewCaricamentoPratiche()">Aggiungi</button>
	               			<button type="button" class="btn default" onclick="slideAsideNew(''); hideInfo();">Chiudi</button>
	                  </div>
                  
               </div>
            </div>
         </form>
      </div>
   </div>
</aside>