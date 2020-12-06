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
	<li>Tassonomie - Sinonimo</li>
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
				

					   <div class="col-md-4">
					   		
                               <div class="form-group form-md-line-input">
                               	   <input type="hidden" id="sinonimoID" />
                                   <input type="text" class="form-control" id="sinonimo" value=""  style="height: 28px;">
                                   <label for="form_control_1">Sinonimo<i class="fa fa-asterisk obbligatorio"></i></label>
                               </div>
                           </div>
                           
                           <div class="col-md-4">
		                     <div class="form-group">
		                		<span class="help-block"> Data Inizio <i class="fa fa-asterisk obbligatorio"></i> </span>
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
		                		<span class="help-block"> Data Fine <i class="fa"></i> </span>
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
						<div class="col-md-4">
                               <div class="form-group form-md-line-input">
                               	   <input type="hidden" id="glossarioID" />
                                   <input type="text" class="form-control" id="parola" value="" readonly="true"  style="height: 28px;">
                                   <label for="form_control_1">Parola<i class="fa fa-asterisk obbligatorio"></i></label>
                               </div>
                        </div>
					
						<div class="col-md-8">
                               <div class="form-group form-md-line-input">
                                   <input type="text" class="form-control" id="descrizioneSinonimo" value=""  style="height: 28px;">
                                   <label for="form_control_1">Descrizione sinonimo<i class="fa"></i></label>
                               </div>
                        </div>			
					</div>
                     
                     <div class="row">
						<div class="col-lg-6 col-xs-12" style="padding-top: 18px;">
							<div class="actions add-action"><a data-toggle="modal" onclick="salvaSinonimo('sinonimo');" class="btn btn-default btn-sm"> <i class="fa fa-plus"></i> Salva </a></div>
							<div class="actions add-action"><a data-toggle="modal" onclick="buttonIndietro('sinonimo');" class="btn btn-default btn-sm"> <i class="fa fa-pencil"></i>Indietro </a></div>
						</div>
					</div>
			</div>	
		</div>	
	</div>
<div>
<div id="portlet-sinonimo-body" class="portlet-sinonimo-body" />
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
