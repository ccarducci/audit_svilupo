<%@ taglib prefix="s" uri="/struts-tags" %>
<aside class="menu-sx-levelone-facility update">

	<input type="hidden" id="idArea" name="idArea" value="0">
	<input type="hidden" id="dataFineOrig" name="dataFineOrig" value="">
	
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">GESTIONE AREA</span>
                </div>
            </div>
            <div class="portlet-body form">
                <form role="form">
                    <div class="form-body">
                        <div class="row" style="margin-bottom: 30px;">
                            <div class="col-md-6 col-xs-12">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descrizione" value="" style="height: 38px;">
                                    <label for="form_control_2">Descrizione <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-3 col-xs-6">
                                <div class="form-group">
                                    <div class="form-group form-md-line-input">
                                        
                                   
                                         <input id="dataInizio" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                      <label for="dataInizio">Data Inizio <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                  
                                </div>
                            </div>
                            <div class="col-md-3 col-xs-6">
                                <div class="form-group">
                                   <div class="form-group form-md-line-input">
                                      
                                      
                                     <input id="dataFine" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                       <label for="dataFine">Data Inizio</label>
                                    </div>
                                    <!-- /input-group -->
                                   
                                </div>
                            </div>
                        </div>
                        
                    </div>
                    
                    <div id="divProcessiTable" style="height: 200px;overflow-y: auto; overflow-x: hidden"></div>
                    
                    <div class="form-actions noborder" style="position: absolute; bottom: 10px;">
                        <button type="button" class="btn blue" onclick="checkValiditaArea();">Salva</button>
                        <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
                    </div>
                </form>
            </div>
        </div>
    </aside>