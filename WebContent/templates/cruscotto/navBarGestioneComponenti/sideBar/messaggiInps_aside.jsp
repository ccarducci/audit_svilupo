<%@ taglib prefix="s" uri="/struts-tags" %>
	
	
	<s:hidden id="idMessaggiInps" value="0"/>
	
	<!-- BEGIN SIDEBAR DX -->
    <aside class="menu-sx-levelone-facility add">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">NORMATIVA - MESSAGGI</span>
                </div>
            </div>
            <div class="portlet-body form">
                <form role="form" id="formMessaggiInps">
                    <div class="form-body">
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-3">
                                <div class="form-group">
                                	<span class="help-block">Data Emissione <i class="fa fa-asterisk obbligatorio"></i></span>
                                   <div class="input-group input-medium ">
                                      
                                        <input id="dataEmissioneMI" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                </div>
                            </div>
			                <div class="col-md-3">
                                <div class="form-group">
                                    <label for="form_control_1" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Ente Emittente <i class="fa fa-asterisk obbligatorio"></i></label>
                                    <s:select class="form-control input-medium select2me" 
                               			data-placeholder="EnteEmittente" 
                               			id="comboEnteEmittenteMI" 
	                              		list="entiEmittenti"
	                              		listKey="codice"
										listValue="descrizione"	/>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 10px;">    
                            <div class="col-md-3">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="codiceMI" value="" style="height: 28px;">
                                    <label for="form_control_1">Numero Messaggio <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="oggettoMI" value="" style="height: 28px;">
                                    <label for="form_control_1">Oggetto <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-6">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descSinteticaMI" value="" style="height: 28px;">
                                    <label for="form_control_1">Descrizione Sintetica <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descDettaglioMI" value="" style="height: 28px;">
                                    <label for="form_control_1">Descrizione Dettaglio <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-3">
                                <div class="form-group">
                                   <div class="input-group input-medium ">
                                            <span class="help-block">Data Inizio <i class="fa fa-asterisk obbligatorio"></i></span> 
                                      
                                         <input id="dataInizioMI" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                       
                                    </div>
                               
                                </div>
                        	</div>
                        	<div class="col-md-3">
                                <div class="form-group">
                                   <div class="input-group input-medium">
                                          <span class="help-block">Data Fine</i></span>
                                       
                                         <input id="dataFineMI" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                  
                                </div>
                            </div>
                        </div>
                       
	                    <div class="form-actions noborder" style="position: absolute; bottom: 10px;">
	                        <button type="button" class="btn blue" onclick="salvaMessaggiInps('I');">Salva</button>
	                        <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
	                    </div>
	                </div>
                </form>
            </div>
        </div>
    </aside>
    
    <!-- END SIDEBAR DX -->
    