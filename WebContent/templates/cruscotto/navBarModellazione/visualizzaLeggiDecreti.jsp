<%@ taglib prefix="s" uri="/struts-tags" %>
    <div class="tab-pane active" id="datiGenerali">
        <form role="form" id="CircolartiInpsForm">
            <div class="form-body" style="margin: 30px 0 40px;">
                <div class="row" style="margin-bottom: 20px;">
                    <div class="col-md-4">
                        <div class="page-head" style="margin-bottom: 20px">
                            <div class="page-title">
                                <h1> Leggi e decreti</h1>
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
                                <s:set var="dataEmissioneVal" value="%{leggiDecreti.dataEmissione}" />
                                <input type="text" class="form-control" readonly id="dataEmissioneLD" value="<s:date name="dataEmissioneVal" format="dd/MM/yyyy" />">
                            </div>

                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="form-group form-md-line-input">
                            <s:set var="tipoLegge" value="%{leggiDecreti.tipoLegge}" />
                              <input type="text" class="form-control" id="oggettoLD" value="<s:property value="tipoLegge" />" style="height: 28px;" readonly>
                            <label for="form_control_1">Tipo Legge </label>
                            
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 15px;">
                    <div class="col-md-3">
                        <div class="form-group form-md-line-input">
                            <s:set var="codice" value="%{leggiDecreti.codice}" />
                            <input type="text" class="form-control" id="codiceLD" value="<s:property value="codice" />" style="height: 28px;" readonly>
                            <label for="form_control_1">Codice</label>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group form-md-line-input">
                            <s:set var="oggetto" value="%{leggiDecreti.oggetto}" />
                            <input type="text" class="form-control" id="oggettoLD" value="<s:property value="oggetto" />" style="height: 28px;" readonly>
                            <label for="form_control_1">Oggetto</label>
                        </div>
                    </div>
                </div>

                <div class="row" style="margin-bottom: 15px;">
                    <div class="col-md-3">
                        <div class="form-group form-md-line-input">
                            <s:set var="descSintetica" value="%{leggiDecreti.descSintetica}" />
                            <input type="text" class="form-control" id="descSinteticaLD" value="<s:property value="descSintetica" />" style="height: 28px;" readonly>
                            <label for="form_control_1">Descrizione Sintetica</label>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group form-md-line-input">
                            <s:set var="descDettaglio" value="%{leggiDecreti.descDettaglio}" />
                            <input type="text" class="form-control" id="descDettaglioLD" value="<s:property value="descDettaglio" />" style="height: 28px;" readonly>
                            <label for="form_control_1">Descrizione Dettaglio</label>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-bottom: 15px;">
                    <div class="col-md-3">
                        <label for="dataInizioAudit" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data Inizio</label>
                        <div class="form-group">
                            <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                <s:set var="dataInizioVal" value="%{leggiDecreti.dataInizio}" />
                                <input type="text" class="form-control" readonly id="dataInizioLD" value="<s:date name=" dataInizioVal " format="dd/MM/yyyy" />">
                            </div>

                        </div>
                    </div>
                    <div class="col-md-3">
                        <label for="dataInizioAudit" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data Fine</label>
                        <div class="form-group">
                            <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                                <s:set var="dataFineVal" value="%{leggiDecreti.dataFine}" />
                                <input type="text" class="form-control" readonly id="dataFineLD" value="<s:date name="dataFineVal" format="dd/MM/yyyy" />">
                            </div>

                        </div>
                    </div>
                </div>

                <div class="row" style="margin-bottom: 15px;">
                    <div class="col-md-3">
                        <div class="form-group form-md-line-input">
                            <s:set var="articolo" value="%{leggiDecreti.articolo}" />
                            <input type="text" class="form-control" id="articoloLD" value="<s:property value="articolo" />" style="height: 28px;" readonly>
                            <label for="form_control_1">Articolo </label>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group form-md-line-input">
                           <s:set var="annoG" value="%{leggiDecreti.annoGui}" />
                             <input type="text" class="form-control" id="articoloLD" value="<s:property value="annoG" />" style="height: 28px;" readonly>
                            <label for="form_control_1">Anno G.U.I. </label>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group form-md-line-input">
                            <s:set var="numeroGui" value="%{leggiDecreti.articolo}" />
                            <input type="text" class="form-control" id="numeroGuiLD" value="<s:property value=" numeroGui" />" style="height: 28px;" readonly>
                            <label for="form_control_1">Numero G.U.I. </label>
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
                                <a onclick="scaricaAllegatoNormativa(4)" class="btn btn-default btn-sm del" download>
                                    <i class="fa fa-download"></i> Scarica </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 ">
                        <div class="portlet-body">
                            <table class="table table-striped table-bordered table-advance table-hover" id="allegatiLeggiDecretiTable">
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