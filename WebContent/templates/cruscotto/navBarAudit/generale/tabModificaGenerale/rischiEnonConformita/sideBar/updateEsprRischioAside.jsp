<%@ taglib prefix="s" uri="/struts-tags" %>

<aside class="menu-sx-levelone-facility aside-dx-cruscottoAudit update-expressRisk">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                <input type="hidden" id="idEsprRiskAside" value="">
                    <i class="icon-pin font-green"></i>
                    <span class="caption-subject bold uppercase">MODIFICA ESPRESSIONE DI RISCHIO - <span style="color: #b7b7b7; font-size: 14px;">Audit <s:property value="%{idAudit}" /></span></span>
                </div>
            </div>
            <div class="portlet">
                <div class="portlet-body form">
                    <form role="form">
                        <div class="form-body">
                            <div class="row" style="margin-bottom: 40px;">
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group">
                                        <label for="form_control_2" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Rischio <i class="fa fa-asterisk obbligatorio"></i></label><br>
                                        <select class="form-control input-medium select2me" id="scegliRischioUp" data-placeholder="Scegli Rischio">
                                          <option id="optRischio" value=""> </option>
                                            <s:iterator value="rischiDropDown" var="snd">
											
                    							<option id="<s:property value="#snd.idMRischio" />" value="<s:property value="#snd.idMRischio" />">
                        							<s:property value="#snd.codiceRischio" /> - <s:property value="#snd.descrizioneRischio" />
                   								</option>
                														
            								</s:iterator>	
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="descrizioneEspr" value="Espressione Rischio 1" placeholder="" style="height: 38px;">
                                        <label for="descrizioneEspr">Descrizione <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="codiceE" value="Codice 1" placeholder="" style="height: 38px;">
                                        <label for="codiceE">Codice <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="percentualeT" value="Percentuale 1" placeholder="" style="height: 38px;">
                                        <label for="percentualeT">Percentuale su totale <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="margin-bottom: 40px;">
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="raggruppamento" value="Raggruppamento 1" placeholder="" style="height: 38px;">
                                        <label for="raggruppamento">Raggruppamento rischio <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group">
                                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                                <span class="help-block">Data inizio<i class="fa fa-asterisk obbligatorio"></i></span>
                                            <input id="dataInizioE" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                        </div>
                                        <!-- /input-group -->
                                      
                                    </div>
                                </div>
                                <div class="col-md-3" style="margin-bottom: 15px;">
                                    <div class="form-group">
                                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                           <!-- /input-group -->
                                        <span class="help-block">
                                        Data fine </span>
                                             <input id="dataFineE" class="form-control form-control-inline input-medium date-picker" size="16" type="text" >
                                        </div>
                                   
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="form-actions noborder" style="position: absolute; bottom: 50px;">
                    <button type="button" class="btn blue" onclick="salvaModificaEsprRischio()">Salva</button>
                    <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
                </div>
            </div>
            <!-- END SAMPLE TABLE PORTLET-->
        </div>
    </aside>