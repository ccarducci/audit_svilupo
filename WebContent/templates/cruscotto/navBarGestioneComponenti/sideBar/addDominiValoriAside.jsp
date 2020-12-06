<%@ taglib prefix="s" uri="/struts-tags" %>
	
	
	<s:hidden id="idDominiValori" value="0"/>
	
	<!-- BEGIN SIDEBAR DX -->
    <aside class="menu-sx-levelone-facility add">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">DOMINI - VALORI</span>
                </div>
            </div>
            <div class="portlet-body form">
                <form role="form" id="formDominiValori">
                    <div class="form-body">
                        <div class="row" style="margin-bottom: 10px;">    
                            <div class="col-md-2">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="codiceDominio" value="" readonly="true" style="height: 28px;">
                                    <label for="form_control_1">Codice Dominio <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="codiceValore" value="" maxlength="5" style="height: 28px;">
                                    <label for="form_control_1">CodiceValore <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-6">
			                    <div class="form-group form-md-line-input">
			                        <input type="text" class="form-control" id="descrizione" value="" style="height: 28px;">
			                        <label for="form_control_1">Descrizione <i class="fa fa-asterisk obbligatorio"></i></label>
			                    </div>
			                </div>
                        </div>
                        <div class="row" style="margin-bottom: 10px;">
                            <div class="col-md-3">
                                   	<span class="help-block">Data Inizio<i class="fa fa-asterisk obbligatorio"></i></span>
            						<input id="dataInizioVal"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
                        	</div>
                        	<div class="col-md-3">
                                  	<span class="help-block">Data Fine</span>
            						<input id="dataFineVal"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
                            </div>
                        </div>
                       
		           
	                    <div class="form-actions noborder" style="bottom: 10px;">
	                        <button type="button" class="btn blue" onclick="salvaDominiValori('I');">Salva</button>
	                        <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
	                    </div>
	                </div>
                </form>
            </div>
        </div>
    </aside>
    
    <!-- END SIDEBAR DX -->
    