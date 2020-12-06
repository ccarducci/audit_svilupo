<%@ taglib prefix="s" uri="/struts-tags" %>
    <div class="tab-pane active" id="datiGenerali">
        <form role="form" id="CircolartiInpsForm">
            <div class="form-body" style="margin: 30px 0 40px;">
                <div class="row" style="margin-bottom: 20px;">
                    <div class="col-md-4">
                        <div class="page-head" style="margin-bottom: 20px">
                            <div class="page-title">
                                <h1> Messaggio</h1>
                            </div>
                        </div>
                    </div>
                </div>
                <input type="hidden" value="1" id="tipoNormativa">
                <div class="row" style="margin-bottom: 10px;">
                    <div class="col-md-3">
                        <label for="dataInizioAudit" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data Emissione</label>
                        <div class="form-group">
                            <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                <s:set var="dataEmissioneVal" value="%{messaggiInps.dataEmissione}" />
                                <input type="text" class="form-control" readonly id="dataEmissioneMI" value="<s:date name="dataEmissioneVal" format="dd/MM/yyyy " />">
                            </div>

                        </div>
                    </div>
                    <div class="col-md-3">
                       <div class="form-group form-md-line-input">
                           	   <s:set var="enteEm" value="%{messaggiInps.enteEmittente}" />
                             <input type="text" class="form-control" id="codiceMI" value="<s:property value="enteEm" />" style="height: 28px;" readonly>
                              <label for="form_control_1">Ente Emittente</label>
                        </div>
                    </div>
                </div>

                <div class="row" style="margin-bottom: 15px;">
                    <div class="col-md-3">
                        <div class="form-group form-md-line-input">
                            <s:set var="codice" value="%{messaggiInps.codice}" />
                            <input type="text" class="form-control" id="codiceMI" value="<s:property value="codice" />" style="height: 28px;" readonly>
                            <label for="form_control_1">Numero Messaggio</label>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group form-md-line-input">
                            <s:set var="oggetto" value="%{messaggiInps.oggetto}" />
                            <input type="text" class="form-control" id="oggettoMI" value="<s:property value="oggetto" />" style="height: 28px;" readonly>
                            <label for="form_control_1">Oggetto</label>
                        </div>
                    </div>
                </div>

                <div class="row" style="margin-bottom: 15px;">
                    <div class="col-md-3">
                        <div class="form-group form-md-line-input">
                            <s:set var="descSintetica" value="%{messaggiInps.descSintetica}" />
                            <input type="text" class="form-control" id="descSinteticaMI" value="<s:property value="descSintetica" />" style="height: 28px;" readonly>
                            <label for="form_control_1">Descrizione Sintetica</label>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group form-md-line-input">
                            <s:set var="descDettaglio" value="%{messaggiInps.descDettaglio}" />
                            <input type="text" class="form-control" id="descDettaglioMI" value="<s:property value="descDettaglio" />" style="height: 28px;" readonly>
                            <label for="form_control_1">Descrizione Dettaglio</label>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 15px;">
                    <div class="col-md-3">
                        <label for="dataInizioAudit" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data Inizio</label>
                        <div class="form-group">
                            <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                <s:set var="dataInizioVal" value="%{messaggiInps.dataInizio}" />
                                <input type="text" class="form-control" readonly id="dataInizioMI" value="<s:date name="dataInizioVal" format="dd/MM/yyyy " />">
                            </div>

                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 50px">
                    <div class="col-md-4 ">
                        <div class="page-head">
                            <div class="page-title">
                                <h1> Allegati</h1>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 ">
                        <div class="portlet-body">
                            <div class="actions add-action">
                                <a onclick="scaricaAllegatoNormativa(3)" class="btn btn-default btn-sm del" download>
                                    <i class="fa fa-download"></i> Scarica </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 ">
                        <div class="portlet-body">
                            <table class="table table-striped table-bordered table-advance table-hover" id="allegatiMessaggiInpsTable">
                                <thead>
                                    <th style="text-align: center;"></th>
                                    <th>Nome file</th>
                                    <th>Oggetto</th>
                                    <th>Data inizio</th>

                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>

        </form>
    </div>