<%@ taglib prefix="s" uri="/struts-tags" %>

<aside class="menu-sx-levelone-facility add">

        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">Nuovo dominio</span>
                </div>
            </div>
            <div class="portlet-body form">
            
            <form>
					
             
                    <div class="form-body">
                        <div class="row" style="margin-bottom: 60px;">
                            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descrDom" maxlength="200" />
                                    <label for="form_control_1">Descrizione <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-3 col-xs-12" style="margin-bottom: 15px;">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="codDom" maxlength="5" />
                                    <label for="form_control_1">Codice <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            
                  			
                  			<div class="col-md-3" style="margin-bottom: 15px;">
            					<span class="help-block">Data Inizio<i class="fa fa-asterisk obbligatorio"></i></span>
            					<input id="dataInizioDom"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
							</div>
                  			
                  			<div class="col-md-3" style="margin-bottom: 15px;">
            					<span class="help-block">Data Fine</span>
            					<input id="dataFineDom"   class="form-control form-control-inline input-medium date-picker" type="text" size="16" >
							</div>
                           
                        </div>
                        
                        
                    </div>
                    <div class="form-actions noborder" style="position: absolute; bottom: 10px;">
                    <!-- modificaPraticheAccessi.js -->
                       <button type="button" class="btn default" onclick="salvaDominio();">Salva</button>
                        
                        <button type="button" class="btn default" onclick="closeAsideDom()">Annulla</button>
                    </div>
               	</form>
            </div>
        </div>
    </aside>