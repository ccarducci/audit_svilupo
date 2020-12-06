<!-- BEGIN SIDEBAR DX -->
<aside class="menu-sx-levelone-facility add" style="margin-top: -270px;">
   <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">NUOVO GENERALE</span>
         </div>
      </div>
      <div class="portlet-body form">
         <form role="form">
            <div class="form-body">
               <div class="row" style="margin-bottom: 40px;">
               <div class="col-md-4" style="margin-bottom: 15px;">
                     <label for="dataInizioAudit" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data inizio<i class="fa fa-asterisk obbligatorio"></i></label>
                     <div class="form-group">
                        <div class="input-group input-medium date date-picker"
                           data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                           <input id="dataInizioAudit"  class="form-control form-control-inline input-medium date-picker" size="16" type="text" > 
                           
                        </div>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <label for="dataFineAudit" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data fine </label>
                     <div class="form-group">
                        <div class="input-group input-medium date date-picker"
                           data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                               <input id="dataFineAudit"  class="form-control form-control-inline input-medium date-picker" size="16" type="text" > 
                       
                        </div>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="nomeAudit" value="" style="height: 38px;">
                        <label for="nomeAudit">Nome <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
               </div>
               <div class="row" style="margin-bottom: 40px;">
				<div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input">
                        <textarea class="form-control" id="descrizioneAudit" rows="3"  maxlength="150" ></textarea>
                        <label for="versioneAudit">Descrizione</label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input">
                        <textarea class="form-control" id="inputAudit" rows="3" maxlength="150"></textarea>
                        <label for="inputAudit">Input</label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input">
                        <textarea class="form-control" id="outputAudit" rows="3" maxlength="150"></textarea>
                        <label for="outputAudit">Output</label>
                     </div>
                  </div>
               </div>
               <div class="row">
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input">
                        <textarea class="form-control" id="obiettiviAudit" rows="3" maxlength="150"></textarea>
                        <label for="obiettiviAudit">Obiettivi</label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input">
                     	<label style="color:red; margin-left: 200; margin-top: 65; " id="info" hidden>Data inizio e nome sono obbligatori</label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;"></div>
                  	<div id="bottoniAudit" class="col-md-4" style="margin-bottom: 15px;">
                  		<button type="button" class="btn blue" onclick="saveNewAudit()">Aggiungi</button>
               			<button type="button" class="btn default" onclick="slideAsideNew(''); hideInfo();">Chiudi</button>
                  </div>
               </div>
            </div>
         </form>
      </div>
   </div>
</aside>