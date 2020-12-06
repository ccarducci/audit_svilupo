 <%@ taglib prefix="s" uri="/struts-tags" %>
 
 <!-- BEGIN SIDEBAR DX -->
    <aside class="menu-sx-levelone-facility aside-dx-cruscottoAudit add-answer">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">NUOVA RISPOSTA</span>
                </div>
            </div>
            <div class="portlet">
                <div class="portlet-body form">
                    <form role="form">
                        <div class="form-body">
                            <div class="row" style="margin-bottom: 40px;">
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="descrizioneRisp" value="" placeholder="Inserisci nuova risposta" style="height: 38px;">
                                        <label for="descrizioneRisp">Descrizione <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="valRisposta" value="" placeholder="Inserisci nuovo valore" style="height: 38px;">
                                        <label for="valRisposta">Valore risposta <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="coperturaPercent" value="" placeholder="Inserisci copertura percentuale" style="height: 38px;">
                                        <label for="coperturaPercent">Copertura percentuale <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="margin-bottom: 40px;">
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group">
                                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                            <!-- /input-group -->
                                        <span class="help-block">
                                        Data inizio </span>
                                        
                                                  <input id="dataInizioRsp" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >      
                                        </div>
                                      
                                    </div>
                                </div>
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group">
                                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                            <!-- /input-group -->
                                        <span class="help-block">
                                        Data fine </span>
                                          
                                              <input id="dataFineRsp" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >  
                                        </div>
                                     
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="form-actions noborder" style="position: absolute; bottom: 50px;">
                    <button type="button" class="btn blue" onclick="salvaNuovaRisposta()">Salva</button>
                    <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
                </div>
            </div>
            <!-- END SAMPLE TABLE PORTLET-->
        </div>
    </aside>