<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- BEGIN SIDEBAR DX -->
<aside class="menu-sx-levelone-facility tassonomie glossario add">
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption font-green"><i class="icon-pin font-green"></i>
				<span class="caption-subject bold uppercase">Glossario</span></div>
		</div>
		<div class="portlet-body form">
				<form role="form" id="glossario">
					<input type="hidden" id="glossarioID"> 
					<div class="form-body">
						<div class="row" style="margin-bottom: 10px;">
						   <div class="col-md-4">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="parolaGlossario"  style="height: 28px;">
                                    <label for="form_control_1">Parola <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-4">
			                     <div class="form-group">
			                		<span class="help-block"> Data Inizio<i class="fa fa-asterisk obbligatorio"></i> </span>
			                        <div class="input-group input-medium date date-picker"
			                           data-date-format="dd-mm-yyyy" data-date-start-date="+0d"><input
			                           type="text" class="form-control" id="dataInizioGlossario" >
			                           <span class="input-group-btn">
			                           <button class="btn default" type="button"
			                              onclick="openDatePicker('dataInizioGlossario')"><i
			                              class="fa fa-calendar"></i></button>
			                           </span>
			                        </div>

			                     </div>
                        	</div>
                            <div class="col-md-4">
			                     <div class="form-group">
			                		<span class="help-block"> Data Fine<i class="fa"></i> </span>
			                        <div class="input-group input-medium date date-picker"
			                           data-date-format="dd-mm-yyyy" data-date-start-date="+0d"><input
			                           type="text" class="form-control" id="dataFineGlossario" >
			                           <span class="input-group-btn">
			                           <button class="btn default" type="button"
			                              onclick="openDatePicker('dataFineGlossario')"><i
			                              class="fa fa-calendar"></i></button>
			                           </span>
			                        </div>
			                     </div>
                        	</div>
						</div> 
					</div>
					
					<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-12">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descrizioneParolaGlossario"  style="height: 28px;">
                                    <label for="form_control_1">Descrizione parola<i class="fa"></i></label>
                                </div>
                            </div>			
					</div>
					<span></span>
					<div class="form-actions noborder" style="bottom: 50px;">
						<button type="button" id="salvaParola" class="btn blue" onclick="salvaTassonomia();">Salva</button>
						<button type="button" id="annullaParola" class="btn default"
							onclick="slideAsideTassonomiaNewChiudi();">Annulla</button>
					</div>
				
				</form>
		</div>
	</div>
</aside>
<!-- END SIDEBAR DX -->
