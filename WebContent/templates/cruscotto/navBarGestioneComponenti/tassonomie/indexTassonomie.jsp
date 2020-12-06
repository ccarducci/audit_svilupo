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
	<li>Tassonomie</li>
</ul>
<!-- END PAGE BREADCRUMB --> <!-- END PAGE TITLE --></div>
<!-- END PAGE HEAD -->

<!-- BEGIN PAGE CONTENT INNER -->
<div id="tassonomieIndex" class="row margin-top-10">
	<div class="col-md-12"><!-- BEGIN SAMPLE TABLE PORTLET-->
		<div class="portlet">
			<div class="row">
				<div class="col-lg-4 col-xs-12">
					<div class="form-group"><label for="form_control_2"
						style="top: 0; font-size: 13px; color: #888888; opacity: 1; width: 100%; position: absolute;">Tipo Tassonomia <i class="fa fa-asterisk obbligatorio"></i></label><br>
						<select id="tipoTassonomiaCombo" class="form-control select2me"
							onchange="caricaTipoTassonomie(this)">
							<option id="" value=""></option>
							<option id="sinonimo" value="sinonimo">Sinonimo</option>
							<option id="glossario" value="glossario">Glossario</option>
							<option id="ambito" value="ambito">Ambito</option>
							<option id="entita" value="entita">Entità</option>
						</select>
					</div>
				</div>
				<div class="col-lg-6 col-xs-12" style="padding-top: 18px;">
					<div class="actions add-action"><a data-toggle="modal" onclick="getTassonomieCheckBoxID('Inserisci');" class="btn btn-default btn-sm"> <i class="fa fa-plus"></i> Nuovo </a></div>
					<div class="actions add-action"><a data-toggle="modal" onclick="getTassonomieCheckBoxID('Modifica');" class="btn btn-default btn-sm"> <i class="fa fa-pencil"></i>Modifica </a></div>
					<div class="actions add-action"><a data-toggle="modal" onclick="getTassonomieCheckBoxID('Elimina');" class="btn btn-default btn-sm del"> <i class="fa fa-trash"></i>Elimina </a></div>
					<div class="actions add-action"><a data-toggle="modal" onclick="getTassonomieCheckBoxID('Aggiona');" class="btn btn-default btn-sm del"> <i class="fa fa-trash"></i>Aggiorna </a></div>
				</div>
			</div>
		</div>
	</div>
	<div id="tassonomieBody" class="portlet-body" />
	</div>
</div>

<div class="modal-custom"></div>

<!-- GLOSSARIO ASIDE -->
<s:include value="Glossario/Glossario_aside.jsp" />	

<!-- ENTITA ASIDE -->
<s:include value="Entita/Entita_aside.jsp" />	

<script>
	jQuery(document).ready( function() {
		console.log('tassonomie ready');
	});
</script>
