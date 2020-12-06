<%@ taglib prefix="s" uri="/struts-tags" %>
	
	
	<s:hidden id="idNoteDecreti" value="0"/>
	
	<!-- BEGIN SIDEBAR DX -->
    <aside class="menu-sx-levelone-facility add">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">NORMATIVA - NOTE E DECRETI</span>
                </div>
            </div>
            <div class="portlet-body form">
                <form role="form" id="formNoteDecreti">
                    <div class="form-body">
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-3">
                                <div class="form-group">
                                	<span class="help-block">Data Emissione <i class="fa fa-asterisk obbligatorio"></i></span>
                                   <div class="input-group input-medium ">
                                      
                                       <input id="dataEmissioneND" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="codiceND" value="" style="height: 28px;">
                                    <label for="form_control_1">Codice <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="oggettoND" value="" style="height: 28px;">
                                    <label for="form_control_1">Oggetto <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-6">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descSinteticaND" value="" style="height: 28px;">
                                    <label for="form_control_1">Descrizione Sintetica <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descDettaglioND" value="" style="height: 28px;">
                                    <label for="form_control_1">Descrizione Dettaglio <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-3">
                                <div class="form-group">
                                   <div class="input-group input-medium ">
                                          <span class="help-block">Data Inizio <i class="fa fa-asterisk obbligatorio"></i></span>
                                      
                                        <input id="dataInizioND" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                 
                                </div>
                        	</div>
                        	<div class="col-md-3">
                                <div class="form-group">
                                   <div class="input-group input-medium ">
                                     <span class="help-block">Data Fine</i></span>
                                      
                                      <input id="dataFineND" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >  
                                    </div>
                                  
                                </div>
                            </div>
                        </div>
                       
		                <div class="col-md-4">
		                    <div class="form-group form-md-line-input">
		                        <input type="text" class="form-control" id="riferimentiND" value="" style="height: 28px;">
		                        <label for="form_control_1">Riferimenti </label>
		                    </div>
		                </div>
		           
	                    <div class="form-actions noborder" style="position: absolute; bottom: 10px;">
	                        <button type="button" class="btn blue" onclick="salvaNoteDecreti('I');">Salva</button>
	                        <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
	                    </div>
	                </div>
                </form>
            </div>
        </div>
    </aside>
    
    <!-- END SIDEBAR DX -->
    