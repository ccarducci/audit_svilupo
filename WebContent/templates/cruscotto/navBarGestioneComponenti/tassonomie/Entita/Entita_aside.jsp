<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- BEGIN SIDEBAR DX -->
<aside class="menu-sx-levelone-facility tassonomie entita add">
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption font-green"><i class="icon-pin font-green"></i>
				<span class="caption-subject bold uppercase">Entita'</span></div>
		</div>
		<div class="portlet-body form">
				<form role="form" id="entita">
					<input type="hidden" id="entitaID"> 
					<div class="form-body">
						<div class="row" style="margin-bottom: 10px;">
						   <div class="col-md-4">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="entitaDesc"  style="height: 28px;">
                                    <label for="form_control_1">Entia' <i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            <div class="col-md-4">
			                     <div class="form-group">
			                		<span class="help-block"> Data Inizio<i class="fa fa-asterisk obbligatorio"></i> </span>
			                        <div class="input-group input-medium date date-picker"
			                           data-date-format="dd-mm-yyyy" data-date-start-date="+0d"><input
			                           type="text" class="form-control" id="dataInizioEntita" >
			                           <span class="input-group-btn">
			                           <button class="btn default" type="button"
			                              onclick="openDatePicker('dataInizioEntita')"><i
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
			                           type="text" class="form-control" id="dataFineEntita" >
			                           <span class="input-group-btn">
			                           <button class="btn default" type="button"
			                              onclick="openDatePicker('dataFineEntita')"><i
			                              class="fa fa-calendar"></i></button>
			                           </span>
			                        </div>
			                     </div>
                        	</div>
						</div> 
					</div>
					
					<div class="row" style="margin-bottom: 10px;">
							
							<div class="col-md-4">
					           <div class="form-group">
                                    <label for="form_control_1" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Tipo entita <i class="fa fa-asterisk obbligatorio"></i></label>
                                    <select class="form-control input-medium select2me" 
                                    		id="comboTipoEntita" 
                                    		name="comboTipoEntita"
                                    		 >
                                    		<option value=""></option>
                                    		<option value="A">A</option>
                                    		<option value="M">M</option>
                                    		<option value="T">T</option>
                                    </select>
                                </div>
							</div>
							
							<div class="col-md-8">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descrizioneEntita"  style="height: 28px;">
                                    <label for="form_control_1">Descrizione entita'<i class="fa"></i></label>
                                </div>
                            </div>			
					</div>
					<span></span>
					<div class="form-actions noborder" style="bottom: 50px;">
						<button type="button" id="salvaEntita" class="btn blue" onclick="salvaTassonomia();">Salva</button>
						<button type="button" id="annullaEntita" class="btn default"
							onclick="slideAsideTassonomiaNewChiudi();">Annulla</button>
					</div>
				
				</form>
		</div>
	</div>
</aside>
<!-- END SIDEBAR DX -->
