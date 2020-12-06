<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="it.tecnet.crs.session.DatiUtente"%>

<!-- BEGIN CONTENT -->

<!-- BEGIN PAGE HEAD -->
<div class="page-head"><!-- BEGIN PAGE TITLE -->
<div class="page-title"><!-- <h1>ACL Engine <small>gestione autorizzazioni applicative</small></h1> -->
<h1>Gestione componenti <!-- <small>- Processo</small> --></h1>
</div>
<!-- BEGIN PAGE BREADCRUMB -->
<ul class="page-breadcrumb breadcrumb">
	<li>Tassonomie - Ambito</li>
</ul>
<!-- END PAGE BREADCRUMB --> <!-- END PAGE TITLE --></div>
<!-- END PAGE HEAD -->

<!-- BEGIN PAGE CONTENT INNER -->
<div id="tassonomieIndex" class="row margin-top-10">
	<div class="portlet light">
		<div class="portlet-body form">
		<form role="form">
			<div class="form-body">
					<div class="row" style="margin-bottom: 10px;">
						   <input type="hidden" id="ambitoID" />
					   	   <div class="col-md-3">
                           		<div class="form-group form-md-line-input">
                               	   <input type="hidden" id="ambitoEntitaID" />
                                   <input type="text" class="form-control" id="ambitoEntita" value="" readonly="true" style="height: 28px;">
                                   <label for="form_control_1">Entita'<i class="fa fa-asterisk obbligatorio"></i></label>
                               </div>
                           </div>
                           
							<div class="col-md-3">
	                               <div class="form-group form-md-line-input">
	                               	   <input type="hidden" id="ambitoGlossarioID" />
	                                   <input type="text" class="form-control" id="ambitoParola" value="" readonly="true"  style="height: 28px;">
	                                   <label for="form_control_1">Parola<i class="fa fa-asterisk obbligatorio"></i></label>
	                               </div>
	                        </div>
                           
                           <div class="col-md-3">
		                     <div class="form-group">
		                		<span class="help-block">Data Inizio <i class="fa fa-asterisk obbligatorio"></i> </span>
		                        <div class="input-group input-medium date date-picker"
		                           data-date-format="dd-mm-yyyy" data-date-start-date="+0d"><input
		                           type="text" class="form-control" id="dataInizioAmbito" >
		                           <span class="input-group-btn">
		                           <button class="btn default" type="button"
		                              onclick="openDatePicker('dataInizioAmbito')"><i
		                              class="fa fa-calendar"></i></button>
		                           </span>
		                        </div>
		                     </div>
                       	</div>
                           
                           <div class="col-md-3">
		                     <div class="form-group">
		                		<span class="help-block">Data Fine <i class="fa"></i> </span>
		                        <div class="input-group input-medium date date-picker"
		                           data-date-format="dd-mm-yyyy" data-date-start-date="+0d"><input
		                           type="text" class="form-control" id="dataFineAmbito" >
		                           <span class="input-group-btn">
		                           <button class="btn default" type="button"
		                              onclick="openDatePicker('dataFineAmbito')"><i
		                              class="fa fa-calendar"></i></button>
		                           </span>
		                        </div>

		                     </div>
                       	</div>

					</div>
					
					
					<div class="row" style="margin-bottom: 10px;">
						
						<div class="col-md-4">
								<div class="form-group">
                                    <label for="form_control_1" style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%;">Tipo entita <i class="fa fa-asterisk obbligatorio"></i></label>
                                    <select class="form-control input-medium select2me" 
                                    		id="comboTipoAmbito" 
                                    		name="comboTipoAmbito"
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
                                   <input type="text" class="form-control" id="descrizioneAmbito" value=""  style="height: 28px;">
                                   <label for="form_control_1">Descrizione ambito<i class="fa"></i></label>
                               </div>
                        </div>			
					</div>
                     
					<div class="row">
						<div class="col-lg-6 col-xs-12" style="padding-top: 18px;">
							<div class="actions add-action"><a data-toggle="modal" onclick="salvaAmbito('ambito');" class="btn btn-default btn-sm"> <i class="fa fa-plus"></i> Salva </a></div>
							<div class="actions add-action"><a data-toggle="modal" onclick="buttonIndietro('ambito');" class="btn btn-default btn-sm"> <i class="fa fa-pencil"></i>Indietro </a></div>
						</div>
					</div>
			</div>	
		</div>	
	</div>
<div>

<span></span>
<div class="row">
	<div class="col-lg-6 col-xs-12">
		<table class="table table-striped table-bordered table-advance table-hover" id="entitaAmbitoTable">
		<thead>
			<th style="text-align: center;" width="5%"></th>
			<th style="text-align: center;" width="25%">Entità</th>
			</thead>
		<tbody>
		</tbody>
		</table>
	</div>
	<div class="col-lg-6 col-xs-12">
		<table class="table table-striped table-bordered table-advance table-hover" id="glossarioAmbitoTable">
		<thead>
			<th style="text-align: center;" width="5%"></th>
			<th style="text-align: center;" width="25%">Parola</th>	
			</thead>
		<tbody>
		</tbody>
		</table>
	</div>
</div>