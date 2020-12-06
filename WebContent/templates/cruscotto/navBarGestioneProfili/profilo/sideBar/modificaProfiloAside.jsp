<aside class="menu-sx-levelone-facility updateProfilo">
   <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">MODIFICA PROFILO</span>
         </div>
      </div>
      <div class="portlet-body form">
         <form role="form">
            <div class="form-body">
               <div class="row" style="margin-bottom: 40px;">
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="modificaNomeProfilo" value="" style="height: 38px;">
                        <label for="modificaNomeProfilo">Nome <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="modificaCognomeProfilo" value="" style="height: 38px;">
                        <label for="modificaCognomeProfilo">Cognome <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="modificaUsernameProfilo" value="" style="height: 38px;">
                        <label for="modificaUsernameProfilo">Username <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
               </div>
               <div class="row" style="margin-bottom: 40px;">
				  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="modificaEmailProfilo" value="" style="height: 38px;">
                        <label for="modificaEmailProfilo">Email <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                        <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group">
                        <label for="form_control_2"
                           style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Ruolo
                        <i class="fa fa-asterisk obbligatorio"></i></label><br>
                        <select class="form-control input-medium select2me" data-placeholder="Ruolo" id="selectModificaRuoloProfilo">
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
                        <select class="form-control input-medium select2me" data-placeholder="Audit" id="selectModificaAuditProfilo">
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
                     	<label style="color:red; margin-left: 200; margin-top: 65; " id="infoModifca" hidden>Tutti i campi sono obbligatori</label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;"></div>
                  	<div id="bottoniModificaProfilo" class="col-md-4" style="margin-top: 80; padding-left: 300;">
                  		<button type="button" class="btn blue" onclick="updateProfilo();">Modifica</button>
               			<button type="button" class="btn default" onclick="slideAsideNew(''); hideInfo();">Chiudi</button>
                  </div>
               </div>
            </div>
         </form>
      </div>
   </div>
</aside>