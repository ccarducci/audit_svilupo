<%@ taglib prefix="s" uri="/struts-tags" %>
	
	
	<s:hidden id="idLeggiDecreti" value="0"/>
	
	<!-- BEGIN SIDEBAR DX -->
    <aside class="menu-sx-levelone-facility add">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">NORMATIVA - LEGGI E DECRETI</span>
                </div>
            </div>
            <div class="portlet-body form">
                <form role="form" id="formLeggiDecreti">
                    <div class="form-body">
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-3">
                                <div class="form-group">
                                <span class="help-block">Data Emissione <i class="fa fa-asterisk obbligatorio"></i></span>
                                   <div class="input-group input-medium ">
                                      
                                   <input id="dataEmissioneLD" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="form_control_1" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Tipo Legge <i class="fa fa-asterisk obbligatorio"></i></label>
                                    <s:select class="form-control input-medium select2me" 
                               			data-placeholder="TipoLegge" 
                               			id="comboTipoLeggeLD" 
	                              		list="tipiLegge"
	                              		listKey="codice"
										listValue="descrizione"	/>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 10px;">   
                            <div class="col-md-3">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="codiceLD" value="" style="height: 28px;">
                                    <label for="form_control_1">Codice <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="oggettoLD" value="" style="height: 28px;">
                                    <label for="form_control_1">Oggetto <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-6">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descSinteticaLD" value="" style="height: 28px;">
                                    <label for="form_control_1">Descrizione Sintetica <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descDettaglioLD" value="" style="height: 28px;">
                                    <label for="form_control_1">Descrizione Dettaglio <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-3">
                                <div class="form-group">
                                   <div class="input-group input-medium ">
                                         <span class="help-block">Data Inizio <i class="fa fa-asterisk obbligatorio"></i></span>
                                   
                                         <input id="dataInizioLD" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                   
                                </div>
                        	</div>
                        	<div class="col-md-3">
                                <div class="form-group">
                                   <div class="input-group input-medium ">
                                         <span class="help-block">Data Fine</span>
                                        <input id="dataFineLD" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                    </div>
                                 
                                </div>
                            </div>
                        </div>
                       
                       <div class="row" style="margin-bottom: 10px;">
			                <div class="col-md-6">
			                    <div class="form-group form-md-line-input">
			                        <input type="text" class="form-control" id="articoloLD" value="" style="height: 28px;">
			                        <label for="form_control_1">Articolo </label>
			                    </div>
			                </div>
			                <div class="col-md-2">
			                    <div class="form-group form-md-line-input">
			                        <s:textfield maxlength="4" cssClass="form-control" id="annoGuiLD" value="" cssStyle="height: 28px"></s:textfield>
			                        <label for="form_control_1">Anno G.U.I. </label>
			                    </div>
			                </div>
			                <div class="col-md-2">
			                    <div class="form-group form-md-line-input">
			                        <input type="text" class="form-control" id="numeroGuiLD" value="" style="height: 28px;">
			                        <label for="form_control_1">Numero G.U.I. </label>
			                    </div>
			                </div>
		                </div>
		                		           
	                    <div class="form-actions noborder" style="bottom: 10px;">
	                        <button type="button" class="btn blue" onclick="salvaLeggiDecreti('I');">Salva</button>
	                        <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
	                    </div>
	                </div>
                </form>
            </div>
        </div>
    </aside>
    
    <!-- END SIDEBAR DX -->
    