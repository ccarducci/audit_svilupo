<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- BEGIN SIDEBAR DX -->
<aside class="menu-sx-levelone-facility tassonomie sinonimo add">
<div class="portlet light">
	<div class="portlet-title">
		<div class="caption font-green"><i class="icon-pin font-green"></i>
			<span class="caption-subject bold uppercase">Sinonimo</span></div>
			</div>
			<div class="portlet-body form">
			
				<form role="form">
					<div class="form-body">
					
						<div class="row" style="margin-bottom: 10px;">
					

						   <div class="col-md-4">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="sinonimo" value=""  style="height: 28px;">
                                    <label for="form_control_1">Descrizione sinonimo<i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>
                            
                            <div class="col-md-4">
			                     <div class="form-group">
			                		<span class="help-block"> Data Inizio
			                        Osservazione <i class="fa"></i> </span>
			                        <div class="input-group input-medium date date-picker"
			                           data-date-format="dd-mm-yyyy" data-date-start-date="+0d"><input
			                           type="text" class="form-control" id="dataInizioSinonimo" >
			                           <span class="input-group-btn">
			                           <button class="btn default" type="button"
			                              onclick="openDatePicker('dataInizioSinonimo')"><i
			                              class="fa fa-calendar"></i></button>
			                           </span>
			                        </div>

			                     </div>
                        	</div>
                            
                            <div class="col-md-4">
			                     <div class="form-group">
			                		<span class="help-block"> Data Fine
			                        Osservazione <i class="fa"></i> </span>
			                        <div class="input-group input-medium date date-picker"
			                           data-date-format="dd-mm-yyyy" data-date-start-date="+0d"><input
			                           type="text" class="form-control" id="dataFineSinonimo" >
			                           <span class="input-group-btn">
			                           <button class="btn default" type="button"
			                              onclick="openDatePicker('dataFineSinonimo')"><i
			                              class="fa fa-calendar"></i></button>
			                           </span>
			                        </div>

			                     </div>
                        	</div>

						</div>
						
						
						<div class="row" style="margin-bottom: 10px;">
							<div class="col-md-12">
                                <div class="form-group form-md-line-input">
                                    <input type="text" class="form-control" id="descrizioneSinonimo" value=""  style="height: 28px;">
                                    <label for="form_control_1">Descrizione sinonimo<i class="fa fa-asterisk obbligatorio"></i></label>
                                </div>
                            </div>			
						</div>
						
						<table class="table table-striped table-bordered table-advance table-hover" id="glossarioSinonimoTable">
						<thead>
							<th style="text-align: center;" width="5%"></th>
							<th style="text-align: center;" width="25%">Parola</th>
							<th style="text-align: center;">Descrizione</th>
							<th style="text-align: center;" width="10%">Data Inizio</th>
							<th style="text-align: center;" width="10%">Data Fine</th>	
							</thead>
						<tbody>
						</tbody>
						</table>
					</div>
				</form>
			
			<div class="form-actions noborder" style="bottom: 10px;">
				<button type="button" class="btn blue" onclick="">Salva</button>
				<button type="button" class="btn default"
					onclick="slideAsideTassonomiaNewChiudi();">Annulla</button>
			</div>
		</div>
	</div>
</aside>
<!-- END SIDEBAR DX -->
