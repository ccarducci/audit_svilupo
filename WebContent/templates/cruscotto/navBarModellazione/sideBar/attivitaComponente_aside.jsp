<%@ taglib prefix="s" uri="/struts-tags" %>
 <aside class="menu-sx-levelone-facility add">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">NUOVA ATTIVITA COMPONENTE</span>
                </div>
            </div>
            <div class="portlet-body form">
                <form role="form">
                    <div class="form-body">
                        <div class="row" style="margin-bottom: 15px;">
                        
                        	<div class="col-md-3">
                                <div class="form-group">
                                    <label for="form_control_1" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Area <i class="fa fa-asterisk obbligatorio"></i></label>
                                    <s:select class="form-control input-medium select2me" 
                               			data-placeholder="Area" 
                               			id="comboArea" 
	                              		list="areaList"
	                              		headerKey="" 
	                              		headerValue=""
	                              		listKey="idArea"
										listValue="descrizione"	
										onChange="caricaComboProcesso();" />
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="form_control_1" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Processo <i class="fa fa-asterisk obbligatorio"></i></label>
                                    <select class="form-control input-medium select2me" 
                                    		id="comboProcesso" 
                                    		name="comboProcesso"
                                    		onChange="caricaComboSottoProcesso();" />
                                </div>
                            </div>
                        
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="form_control_1" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Fase <i class="fa fa-asterisk obbligatorio"></i></label>
                                    <select class="form-control input-medium select2me" 
                                    		id="comboSottoProcesso" 
                                    		name="comboSottoProcesso" />
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <div class="input-group input-medium">
                                         <span class="help-block">Data Inizio <i class="fa fa-asterisk obbligatorio"></i></span>
                                    
                                        <input id="dataInizio" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                    <!-- /input-group -->
                                  
                                </div>
                            </div>
                        </div>
                        <div class="row">
                       	 <div class="col-md-3">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="ordinamento" value="" style="height: 38px;">
                                    <label for="form_control_2">Ordinamento <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-3" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descrizione" value="" style="height: 38px;">
                                    <label for="form_control_2">Descrizione <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            
                            <div class="col-md-3">
                                <div class="form-group">
                                    <div class="input-group input-medium">
                                       <span class="help-block">
                                    Data fine </span>
                                     
                                      <input id="dataFine" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >  
                                    </div>
                                    <!-- /input-group -->
                                  
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-md-6" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="vincolo" value="" style="height: 38px;">
                                    <label for="form_control_2">Vincolo</label>
                                </div>
                            </div>
                        </div>
                        <!-- 
                        <div id="statoRadioButton">
	                        <div class="row">
		                        	<div class="form-group form-md-radios">
											<div class="md-radio-list">	
												<div class="col-md-3 col-xs-6">
													<label for="form_control_2" style="margin-right: 20px;">Stato Attivita</label>
														<input type="radio" name="statoRB" id="statoAttivo" value="A">
														<span style="margin-left: 10px;">Attivo </span></>
												</div>
												<div class="col-md-3 col-xs-6">
														<input type="radio" name="statoRB" id="statoDisattivo" value="D">
														<span style="margin-left: 10px;">Disattivo </span></>
			                               		</div>
			                               	</div>	
									</div>	
	                        </div>
	                    </div>
                        -->
                        
                    </div>
                    <div class="form-actions noborder" style="position: absolute; bottom: 10px;">
                        <button type="button" class="btn blue" onclick="salvaAttivitaComponente('I');">Salva</button>
                        <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
                    </div>
                </form>
            </div>
        </div>
</aside>