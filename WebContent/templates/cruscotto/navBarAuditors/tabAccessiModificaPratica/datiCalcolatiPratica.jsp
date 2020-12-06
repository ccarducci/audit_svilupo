<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Peritale -->
<div class="portlet-body form">
<form role="form">
	<div class="form-body">
	
		<!-- riga 1 -->
		<div class="row" style="margin-bottom: 30px;">
			<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
				<div class="form-group form-md-line-input">
               		<s:set var="statoProcesso" value="%{pratica.statoProcesso}" />
                   	<input type="text" class="form-control" readonly id="statoProcesso" value="<s:property value="statoProcesso" />" style="height: 38px;">
                   	<label for="form_control_1">Stato Processo</label>
                </div>
			</div>
			<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
				<div class="form-group form-md-line-input">
                		<s:set var="tempiTempo" value="%{pratica.tempiProcesso}" />
                    	<input type="text" class="form-control" readonly id="tempiTempo" value="<s:property value="tempiTempo" />" style="height: 38px;">
                    	<label for="form_control_1">Tempi Processo</label>
                 </div>
			</div>
			<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
				<div class="form-group form-md-line-input">
               		<s:set var="deltaTempo" value="%{pratica.deltaProcesso}" />
                   	<input type="text" class="form-control" readonly id="deltaTempo" value="<s:property value="deltaTempo" />" style="height: 38px;">
                   	<label for="form_control_1">Delta Tempo</label>
                </div>
			</div>
		</div>
		
		<!-- riga 2 -->
		<div class="row" style="margin-bottom: 30px;">
			<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
				<div class="form-group form-md-line-input">
				   <s:set var="dtUdienzaMeno1" value="%{pratica.DataUdienzaMeno1}" />
				   <input type="text" class="form-control" readonly id="dtUdienzaMeno1" value="<s:date name="dtUdienzaMeno1" format="dd/MM/yyyy" />" style="height: 38px;">
				   <label for="form_control_1">1 Anno Da Data 1° Udienza Verifica Stato Passivo</label>
				</div>
			</div>
			<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
				<div class="form-group form-md-line-input">
				   <s:set var="dtNotificaMeno1" value="%{pratica.DataNotificaMeno1}" />
				   <input type="text" class="form-control" readonly id="dtNotificaMeno1" value="<s:date name="dtNotificaMeno1" format="dd/MM/yyyy" />" style="height: 38px;">
				   <label for="form_control_1">1 Anno Da Data Notifica Verbale</label>
				</div>
			</div>
			<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
				<div class="form-group form-md-line-input">
				   <s:set var="dtScadContrMeno2" value="%{pratica.DataScadContrMeno2}" />
				   <input type="text" class="form-control" readonly="readonly" id="dtScadContrMeno2" value="<s:date name="dtScadContrMeno2" format="dd/MM/yyyy" />" style="height: 38px;">
				   <label for="form_control_1">2 Anni Da Data Scadenza Contratto Di Appalto</label>
				</div>
			</div>
		</div>
		
		<!-- riga 3 -->
		<div class="row" style="margin-bottom: 30px;">
			<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
				<div class="form-group form-md-line-input">
					<s:set var="impImpattoPratica" value="%{pratica.impImpattoPratica}" />
					<input type="text" class="form-control" id="impPratica" readonly="readonly" value="<s:property value="impImpattoPratica" />" class="input-group-btn">
                    <label for="impPratica">Impatto Pratica (Euro)</label>
                </div>
			</div>
			<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
				<div class="form-group form-md-line-input">
					<s:set var="impContribuzNonIncassata" value="%{pratica.impContribuzNonIncassata}" />
					<input type="text" class="form-control" id="impContribuzNonIncassata" readonly="readonly" value="<s:property value="impContribuzNonIncassata" />" class="input-group-btn">
                  	<label for="impContribuzNonIncassata">Importo Contribuzione Non Incassata</label>
                </div>
			</div>
			<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
				<div class="form-group form-md-line-input">
					<s:set var="impIndebSospeso" value="%{pratica.impIndebSospeso}" />
					<input type="text" class="form-control" id="impIndebSospeso" readonly="readonly" value="<s:property value="impIndebSospeso" />" class="input-group-btn">
					<label for="impIndebSospeso">Importo Indebitamente Sospeso</label>
				</div>
			</div>
		</div>
		
		<!-- riga 4 -->
		<div class="row" style="margin-bottom: 30px;">
			<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
				<div class="form-group form-md-line-input">
					<s:set var="impPrescritto" value="%{pratica.impPrescritto}" />                                                
                    <input type="text" class="form-control" id="impSanzioniErrate" readonly="readonly" value="<s:property value="impPrescritto" />" class="input-group-btn">
                    <label for="impPrescritto">Importo Prescritto</label>
                </div>
			</div>
			<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
				<div class="form-group form-md-line-input">
					<s:set var="impSanzioniErrate" value="%{pratica.impSanzioniErrate}" />                                                
	               	<input type="text" class="form-control" id="impSanzioniErrate" readonly="readonly" value="<s:property value="impSanzioniErrate" />" class="input-group-btn">
	                <label for="impSanzioniErrate">Importo Sanzioni Errate</label>
	            </div>
			</div>
			<div class="col-md-4 col-xs-12" style="margin-bottom: 15px;">
			</div>
		 </div>
		
	</div>
</form>
</div>



