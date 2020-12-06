<aside class="menu-sx-levelone-facility addProfilo">
   <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">NUOVO PROFILO</span>
         </div>
      </div>
      <div class="portlet-body form">
         <form role="form">
            <div class="form-body">
               <div class="row" style="margin-bottom: 40px;">
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="nomeProfilo" value="" style="height: 38px;">
                        <label for="nomeProfilo">Nome <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="cognomeProfilo" value="" style="height: 38px;">
                        <label for="cognomeProfilo">Cognome <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="usernameProfilo" value="" style="height: 38px;">
                        <label for="usernameProfilo">Username <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
               </div>
               <div class="row" style="margin-bottom: 40px;">
				  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="emailProfilo" value="" style="height: 38px;">
                        <label for="emailProfilo">Email <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                        <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group">
                        <label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Ruolo
                        <i class="fa fa-asterisk obbligatorio"></i></label><br>
                        <select class="form-control input-medium select2me" data-placeholder="Ruolo" id="selectRuoloProfilo">
                           <option value=""></option>
                           <option value="auditors">Auditors</option>
                           <option value="auditorsATPO">Auditors ATPO</option>
                           <option value="dirigente">Dirigente</option>
                           <option value="delegato">Delegato</option>
                           <option value="dirGenerale">Dir. Generale</option>
                           <option value="masterDCSIT">Master DCSIT</option>
                        </select>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group">
                        <label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Audit
                        <i class="fa fa-asterisk obbligatorio"></i></label><br>
                        <select class="form-control input-medium select2me" data-placeholder="Audit" id="selectAuditProfilo">
                           <option value=""></option>
                           <option value="vigilanza">Vigilanza</option>
                           <option value="ATPO">ATPO</option>
                        </select>
                     </div>
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input">
                     	<label style="color:red; margin-left: 200; margin-top: 65; " id="info" hidden>Tutti i campi sono obbligatori</label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;"></div>
                  	<div id="bottoniProfilo" class="col-md-4" style="margin-top: 80; padding-left: 300;">
                  		<button type="button" class="btn blue" onclick="addNewProfilo();">Aggiungi</button>
               			<button type="button" class="btn default" onclick="slideAsideNew(''); hideInfo();">Chiudi</button>
                  </div>
               </div>
            </div>
         </form>
      </div>
   </div>
</aside>