<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="tab-pane active" id="datiGenerali">
    <form role="form" id="CircolartiInpsForm">
        <div class="form-body" style="margin: 30px 0 40px;">
			<div class="row" style="margin-bottom: 20px;">
			   <div class="col-md-4">
			 <div class="page-head" style="margin-bottom: 20px">
              	<div class="page-title">
                 	<h1> Circolare</h1>
                </div>
            </div>
            </div>
            </div>
            <input type="hidden" value="1" id="tipoNormativa">
            <div class="row" style="margin-bottom: 20px;">
                <div class="col-md-4">
                    <label for="dataInizioAudit" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data Emissione </label>
                    <div class="form-group">
                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                            <s:set var="dataEmissioneVal" value="%{circolariInps.dataEmissione}" />
                            <input type="text" class="form-control" readonly id="dataEmissione" value="<s:date name="dataEmissioneVal" format="dd/MM/yyyy " />">
                        </div>

                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                     <div class="form-group form-md-line-input">
                        <s:set var="annoEm" value="%{circolariInps.anno}" />
                        <input type="text"  class="form-control" maxlength="4" id="anno" value="<s:property value="annoEm" />" style="height: 28px" readonly>
                        <label for="form_control_1">Anno Emissione</label>
                    </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <div class="form-group form-md-line-input">
                        <s:set var="enteEmitt" value="%{circolariInps.enteEmittente}" />
                        <input type="text" class="form-control" id="ent" value="<s:property value="enteEmitt" />" style="height: 28px;" readonly>
                        <label for="form_control_1">Ente Emittente</label>
                    </div>
                    </div>
                </div>
            </div>
           <div class="row" style="margin-bottom: 20px;">
                <div class="col-md-4">
                    <div class="form-group form-md-line-input">
                        <s:set var="codice" value="%{circolariInps.codice}" />
                        <input type="text" class="form-control" id="codice" value="<s:property value="codice" />" style="height: 28px;" readonly>
                        <label for="form_control_1">Numero Circolare</label>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group form-md-line-input">
                        <s:set var="oggetto" value="%{circolariInps.oggetto}" />
                        <input type="text" class="form-control" id="oggetto" value="<s:property value="oggetto" />" style="height: 28px;" readonly>
                        <label for="form_control_1">Oggetto</label>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group form-md-line-input">
                        <s:set var="sommario" value="%{circolariInps.sommario}" />
                        <input type="text" class="form-control" id="sommario" value="<s:property value="sommario" />" style="height: 28px;" readonly>
                        <label for="form_control_1">Sommario </label>
                    </div>
                </div>
            </div>

            <div class="row" style="margin-bottom: 20px;">
                <div class="col-md-4">
                    <div class="form-group form-md-line-input">
                        <s:set var="descSintetica" value="%{circolariInps.descSintetica}" />
                        <input type="text" class="form-control" id="descSintetica" value="<s:property value="descSintetica" />" style="height: 28px;" readonly>
                        <label for="form_control_1">Descrizione Sintetica</label>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group form-md-line-input">
                        <s:set var="descDettaglio" value="%{circolariInps.descDettaglio}" />
                        <input type="text" class="form-control" id="descDettaglio" value="<s:property value="descDettaglio" />" style="height: 28px;" readonly>
                        <label for="form_control_1">Descrizione Dettaglio </label>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-bottom: 20px;">
                <div class="col-md-4">
                    <label for="dataInizioAudit" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data Inizio</label>
                    <div class="form-group">
                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                            <s:set var="dataInizioVal" value="%{circolariInps.dataInizio}" />
                            <input type="text" class="form-control" readonly id="dataInizio" value="<s:date name="dataInizioVal" format="dd/MM/yyyy " />">
                          
                        </div>

                    </div>
                </div>
                <div class="col-md-4">
                    <label for="dataInizioAudit" style="top: 0;margin-bottom: 0;font-size: 13px;color: #888888;opacity: 1;"> Data Fine</label>
                    <div class="form-group">
                        <div class="input-group input-medium date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
                            <s:set var="dataFineVal" value="%{circolariInps.dataFine}" />
                            <input type="text" class="form-control" readonly id="dataFine" value="<s:date name="dataFineVal" format="dd/MM/yyyy " />">
                        </div>

                    </div>
                </div>
            </div>

            <div class="row" style="margin-bottom: 20px;">
                <div class="col-md-4">
                    <div class="form-group form-md-line-input">
                        <s:set var="direzioneEmittente1" value="%{circolariInps.direzioneEmittente1}" />
                        <input type="text" class="form-control" id="direzioneEmittente1" value="<s:property value="direzioneEmittente1" />" readonly style="height: 28px;">
                        <label for="form_control_1">Direzione Emittente1</label>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group form-md-line-input">
                        <s:set var="direzioneEmittente2" value="%{circolariInps.direzioneEmittente2}" />
                        <input type="text" class="form-control" id="direzioneEmittente2" value="<s:property value="direzioneEmittente2" />" style="height: 28px;" readonly>
                        <label for="form_control_1">Direzione Emittente2 </label>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group form-md-line-input">
                        <s:set var="direzioneEmittente3" value="%{circolariInps.direzioneEmittente3}" />
                        <input type="text" class="form-control" id="direzioneEmittente3" value="<s:property value="direzioneEmittente3" />" style="height: 28px;" readonly>
                        <label for="form_control_1">Direzione Emittente3 </label>
                    </div>
                </div>
            </div>

            <div class="row" style="margin-bottom: 20px;">
                <div class="col-md-4">
                    <div class="form-group form-md-line-input">
                        <s:set var="direzioneEmittente4" value="%{circolariInps.direzioneEmittente4}" />
                        <input type="text" class="form-control" id="direzioneEmittente4" value="<s:property value="direzioneEmittente4" />" style="height: 28px;" readonly>
                        <label for="form_control_1">Direzione Emittente4 </label>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group form-md-line-input">
                        <s:set var="direzioneEmittente5" value="%{circolariInps.direzioneEmittente5}" />
                        <input type="text" class="form-control" id="direzioneEmittente5" value="<s:property value="direzioneEmittente5" />" style="height: 28px;" readonly>
                        <label for="form_control_1">Direzione Emittente5 </label>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group form-md-line-input">
                        <s:set var="direzioneEmittente6" value="%{circolariInps.direzioneEmittente6}" />
                        <input type="text" class="form-control" id="direzioneEmittente6" value="<s:property value="direzioneEmittente6" />" style="height: 28px;" readonly>
                        <label for="form_control_1">Direzione Emittente6 </label>
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
                                  										<a onclick="scaricaAllegatoNormativa(1)" class="btn btn-default btn-sm del" download>
                                    									<i class="fa fa-download"></i> Scarica </a>
                                								</div>
                                							</div>
                                						</div>
                                					</div>
            <div class="row">
            	<div class="col-md-12 ">
                     <div class="portlet-body">
                         <table class="table table-striped table-bordered table-advance table-hover" id="allegatiCircolariInpsTable">
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