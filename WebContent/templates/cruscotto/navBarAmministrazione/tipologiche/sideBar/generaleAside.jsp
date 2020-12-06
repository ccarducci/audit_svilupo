<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- BEGIN SIDEBAR DX -->
<aside class="menu-sx-levelone-facility add" style="margin-top: -270px;">
   <div class="portlet light">
      <div class="portlet-title">
         <div class="caption font-green"><i class="icon-pin font-green"></i>
            <span class="caption-subject bold uppercase">NUOVA TIPOLOGICA</span>
         </div>
      </div>
      <div class="portlet-body form">
         <form role="form">
         	
            <div class="form-body">
               <label style="color:red;" id="info" hidden>I campi contrassegnati da *  sono obbligatori</label>
               <div class="row" style="margin-bottom: 40px;">
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="tipo" value="" style="height: 38px;">
                        <label for="nome">Tipo <i class="fa fa-asterisk obbligatorio"></i></label>
                        
                     </div>
                  </div>
                 
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="codifica" value="" style="height: 38px;">
                        <label for="cognome">Codifica <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                  <div class="col-md-4" style="margin-bottom: 15px;">
                     <div class="form-group form-md-line-input"><input type="text"
                        class="form-control" id="descrizione" value="" style="height: 38px;">
                        <label for="username">Descrizione <i class="fa fa-asterisk obbligatorio"></i></label>
                     </div>
                  </div>
                   
               </div>
              
               <div class="row">
                 <div class="col-md-4" style="margin-bottom: 15px;"></div>
                  <div class="col-md-4" style="margin-bottom: 15px;"></div>
	                  	<div id="bottoniAudit" class="col-md-4" style="margin-bottom: 15px;">
	                  		<button type="button" class="btn blue" onclick="saveNewTipologica()">Aggiungi</button>
	               			<button type="button" class="btn default" onclick="slideAsideNew(''); hideInfo();">Chiudi</button>
	                  </div>
                  
               </div>
            </div>
         </form>
      </div>
   </div>
</aside>