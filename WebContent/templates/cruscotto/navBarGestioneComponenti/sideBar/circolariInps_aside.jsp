<%@ taglib prefix="s" uri="/struts-tags" %>
	
	
	<s:hidden id="idCircolariInps" value="0"/>
	
	<!-- BEGIN SIDEBAR DX -->
    <aside class="menu-sx-levelone-facility add">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">NORMATIVA - CIRCOLARI</span>
                </div>
            </div>
            <div class="portlet-body form">
                <form role="form" id="formCircolariInps">
                    <div class="form-body">
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-3">
                                <div class="form-group">
                                	<span class="help-block">Data Emissione <i class="fa fa-asterisk obbligatorio"></i></span>
                                   <div class="input-group input-medium date date-picker" data-date-format="dd/mm/yyyy">
                                        <input type="text" class="form-control" readonly id="dataEmissione" value="">
                                        <span class="input-group-btn">
                                        <button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                             <div class="col-md-2">
			                    <div class="form-group form-md-line-input">
			                        <s:textfield maxlength="4" cssClass="form-control" id="anno" value="" cssStyle="height: 28px"></s:textfield>
			                        <label for="form_control_1">Anno Emissione <i class="fa fa-asterisk obbligatorio"></i></label>
			                    </div>
			                </div>
			                <div class="col-md-3">
                                <div class="form-group">
                                    <label for="form_control_1" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Ente Emittente <i class="fa fa-asterisk obbligatorio"></i></label>
                                    <s:select class="form-control input-medium select2me" 
                               			data-placeholder="EnteEmittente" 
                               			id="comboEnteEmittente" 
	                              		list="entiEmittenti"
	                              		listKey="codice"
										listValue="descrizione"	/>
                                </div>
                            </div>
                        </div>
                            
                        <div class="row" style="margin-bottom: 10px;">    
                            <div class="col-md-2">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="codice" value="" style="height: 28px;">
                                    <label for="form_control_1">Numero Circolare <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="oggetto" value="" style="height: 28px;">
                                    <label for="form_control_1">Oggetto <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-6">
			                    <div class="form-group form-md-line-input">
			                        <input type="text" class="form-control" id="sommario" value="" style="height: 28px;">
			                        <label for="form_control_1">Sommario </label>
			                    </div>
			                </div>
                        </div>
                        
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-6">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descSintetica" value="" style="height: 28px;">
                                    <label for="form_control_1">Descrizione Sintetica <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descDettaglio" value="" style="height: 28px;">
                                    <label for="form_control_1">Descrizione Dettaglio <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-3">
                                <div class="form-group">
                                   <div class="input-group input-medium">
                                       <span class="help-block">Data Inizio <i class="fa fa-asterisk obbligatorio"></i></span>
                                       
                                        <input id="dataInizio" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                   
                                </div>
                        	</div>
                        	<div class="col-md-3">
                                <div class="form-group">
                                   <div class="input-group input-medium ">
                                     <span class="help-block">Data Fine</i></span>
                                       
                                          <input id="dataFine" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                  
                                </div>
                            </div>
                        </div>
                       
                       <div class="row" style="margin-bottom: 10px;">
			                <div class="col-md-4">
			                    <div class="form-group form-md-line-input">
			                        <input type="text" class="form-control" id="direzioneEmittente1" value="" style="height: 28px;">
			                        <label for="form_control_1">Direzione Emittente1 <i class="fa fa-asterisk obbligatorio"></i></label>
			                    </div>
			                </div>
			                 <div class="col-md-4">
			                    <div class="form-group form-md-line-input">
			                        <input type="text" class="form-control" id="direzioneEmittente2" value="" style="height: 28px;">
			                        <label for="form_control_1">Direzione Emittente2 </label>
			                    </div>
			                </div>
			                 <div class="col-md-4">
			                    <div class="form-group form-md-line-input">
			                        <input type="text" class="form-control" id="direzioneEmittente3" value="" style="height: 28px;">
			                        <label for="form_control_1">Direzione Emittente3 </label>
			                    </div>
			                </div>
			            </div>
			            
			            <div class="row" style="margin-bottom: 10px;">
			                <div class="col-md-4">
			                    <div class="form-group form-md-line-input">
			                        <input type="text" class="form-control" id="direzioneEmittente4" value="" style="height: 28px;">
			                        <label for="form_control_1">Direzione Emittente4 </label>
			                    </div>
			                </div>
			                 <div class="col-md-4">
			                    <div class="form-group form-md-line-input">
			                        <input type="text" class="form-control" id="direzioneEmittente5" value="" style="height: 28px;">
			                        <label for="form_control_1">Direzione Emittente5 </label>
			                    </div>
			                </div>
			                 <div class="col-md-4">
			                    <div class="form-group form-md-line-input">
			                        <input type="text" class="form-control" id="direzioneEmittente6" value="" style="height: 28px;">
			                        <label for="form_control_1">Direzione Emittente6 </label>
			                    </div>
			                </div>
			            </div>
		           
	                    <div class="form-actions noborder" style="bottom: 10px;">
	                        <button type="button" class="btn blue" onclick="salvaCircolariInps('I');">Salva</button>
	                        <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
	                    </div>
	                </div>
                </form>
            </div>
        </div>
    </aside>
    
    <!-- END SIDEBAR DX -->
    