 <%@ taglib prefix="s" uri="/struts-tags" %>

<aside class="menu-sx-levelone-facility aside-dx-cruscottoAudit update-risk">
        <div class="portlet light">
            <div class="portlet-title">
                <div class="caption font-green">
                <input type="hidden" id="idRiskAside" value="">
                    <i class="icon-pin font-green"></i>
                     <s:set var="idAudit" value="%{idAudit}" />
                    <span class="caption-subject bold uppercase">MODIFICA RISCHIO - <span style="color: #b7b7b7; font-size: 14px;">Audit <s:property value="%{idAudit}" /></span></span>
                </div>
            </div>
            <div class="portlet">
                <div class="portlet-body form">
                    <form role="form">
                        <div class="form-body">
                            <div class="row" style="margin-bottom: 40px;">
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                    <s:set var="cSospensione" value="%{faseDefinizione.codiceSospensione}" />
                                        <input type="text" class="form-control" id="descrizioneRischioAside" value="" placeholder="" style="height: 38px;">
                                        <label for="descrizioneRischio">Descrizione <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="pesoRischioAside" value="" placeholder="" style="height: 38px;">
                                        <label for="pesoRischio">Peso rischio <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="mediaAside" value="" placeholder="" style="height: 38px;">
                                        <label for="media">Valore decremento della media <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="margin-bottom: 40px;">
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group form-md-line-input">
                                        <input type="text" class="form-control" id="codiceRischioAside" value="" placeholder="" style="height: 38px;">
                                        <label for="codiceRischio">Codice rischio <i class="fa fa-asterisk obbligatorio"></i></label>
                                    </div>
                                </div>
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group">
                                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                                <span class="help-block">Data inizio<i class="fa fa-asterisk obbligatorio"></i></span>
                                          	 <input id="dataInizioAside" class="form-control form-control-inline input-medium date-picker" size="16" value=""  type="text" >
                                        </div>
                                        <!-- /input-group -->
                                    </div>
                                </div>
                                <div class="col-md-4" style="margin-bottom: 15px;">
                                    <div class="form-group">
                                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                        <span class="help-block">
                                        Data fine </span>
                                            <input id="dataFineAside" class="form-control form-control-inline input-medium date-picker" size="16" value=""  type="text" >
                                        </div>
                                       
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="form-actions noborder" style="position: absolute; bottom: 50px;">
                    <button type="button" class="btn blue" onclick="salvaModificaRischio()">Salva</button>
                    <button type="button" class="btn default" onclick="slideAsideNew('');">Annulla</button>
                </div>
            </div>
            <!-- END SAMPLE TABLE PORTLET-->
        </div>
    </aside>