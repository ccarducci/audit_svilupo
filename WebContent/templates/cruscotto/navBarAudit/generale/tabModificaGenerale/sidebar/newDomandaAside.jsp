<%@ taglib prefix="s" uri="/struts-tags" %>

<aside class="menu-sx-levelone-facility aside-dx-cruscottoAudit add-question">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">NUOVA DOMANDA</span>
                </div>
            </div>
            <div class="portlet">
                <div class="portlet-body form">
                    <form role="form">
                        <div class="form-body">
                            <div class="row" style="margin-bottom: 40px;">
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="descrizioneD" value="" placeholder="" style="height: 38px;">
                                        <label for="descrizioneD">Descrizione <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="pesoD" value="" placeholder="" style="height: 38px;">
                                        <label for="pesoD">Peso <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="vMaxR" value="" placeholder="" style="height: 38px;">
                                        <label for="vMaxR">Valore Max risposta <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group">
                                        <label for="rischioA" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Rischio associato <i class="fa fa-asterisk obbligatorio"></i></label><br>
                                        <select class="form-control input-medium select2me" id="rischioA" data-placeholder="Scegli Rischio">
                                          <option value=""></option>
                                            <s:iterator value="rischiAssociati" var="snd">
											
                    							<option id="<s:property value="#snd.idMRischio" />" value="<s:property value="#snd.idMRischio" />">
                        							<s:property value="#snd.codiceRischio" /> - <s:property value="#snd.descrizioneRischio" />
                   								</option>
                														
            								</s:iterator>	
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="margin-bottom: 40px;">
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="pesoP" value="" placeholder="" style="height: 38px;">
                                        <label for="pesoP">Peso % <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="cp" value="" placeholder="" style="height: 38px;">
                                        <label for="cp">Controllo processo <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group">
                                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                          <!-- /input-group -->
                                        <span class="help-block">
                                        Data inizio </span>
                                         <input id="diq" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >    
                                        </div>
                                       
                                    </div>
                                </div>
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group">
                                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                              <!-- /input-group -->
                                        <span class="help-block">
                                        Data fine </span>  
                                         
                                                      <input id="dfq" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                        </div>
                                    
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="form-actions noborder" style="position: absolute; bottom: 50px;">
                    <button type="button" class="btn blue" onclick="saveNewDomanda()">Salva</button>
                    <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
                </div>
            </div>
            <!-- END SAMPLE TABLE PORTLET-->
        </div>
    </aside>