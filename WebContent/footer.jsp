<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="it.tecnet.crs.web.beans.AppUser;"%>
<%
   AppUser user= (AppUser)request.getSession().getAttribute("AppUser");
   String ruoloAttivo=user.getRuoloAttivo();
%> 

<div class="page-footer">
	<div class="page-footer-inner">
		 
	</div>
	<div class="scroll-to-top">
		<i class="icon-arrow-up"></i>
	</div>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/respond.min.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/Chart.bundle.min.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
<script type="text/javascript" src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-markdown/lib/markdown.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-markdown/js/bootstrap-markdown.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-summernote/summernote.min.js" type="text/javascript"></script>

<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/jquery-notific8/jquery.notific8.min.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/admin/pages/scripts/ui-notific8.js"></script>


<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/morris/morris.min.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/morris/raphael-min.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/select2/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
<script type="text/javascript" src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/clockface/js/clockface.js"></script>
<script type="text/javascript" src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript" src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/admin/layout4/scripts/layout.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/admin/layout4/scripts/demo.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/admin/pages/scripts/components-pickers.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/admin/pages/scripts/index3.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>
<!--<script src="/CruscottoAuditAtpoWebWeb/assets/admin/pages/scripts/table-advanced.js"></script>-->
<script src="/CruscottoAuditAtpoWebWeb/assets/admin/pages/scripts/components-editors.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/admin/pages/scripts/form-fileupload.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/assets/admin/layout4/scripts/custom.js" type="text/javascript"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/cruscottoScript.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/common.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/tassonomie/tassonomie.js" type="text/javascript"></script>

<!-- Script JS tabelle navbar -->


<!-- audit -->
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/delegatiTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/generaleTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/amministrazione/generale/usersTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/amministrazione/generale/sogliecoloriTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/amministrazione/generale/tipologicheTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/amministrazione/generale/caricamentopraticheTable.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/generale.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/campagna/campagnaTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/campagna/regoleTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/campagna/campagnaAccessoTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/campagna/reportSediTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/campagna/verbaliTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/campagna/campagna.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/accesso/accessoTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/accesso/verbaliCampione.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/fasiTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/accesso/accessoAudit.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/accesso/auditorsTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/accesso/praticheSessioneTable.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/fasiDaAggiungereTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/tabRischiENonConformita/rischiTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/tabRischiENonConformita/rischiOp.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/tabRischiENonConformita/espressRischioTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/tabRischiENonConformita/espressRischioOp.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/tabRischiENonConformita/nonConformitaTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/tabRischiENonConformita/nonConformitaOp.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/tabRischiENonConformita/varianteComportamentaleTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/tabRischiENonConformita/varianteComportamentaleOp.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/questionarioAuditTable.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/questionarioAuditOp.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/audit/generale/risposteQuestionarioAuditTable.js"></script>



<!-- auditors -->


<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/accessiTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/reports.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/praticheConRischiTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/indicatoriTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/faseNotificaTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/faseDefinizioneTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/praticheTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/questionario.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/modificaPraticheAccessi.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/accessi.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/rischiPraticheTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/statoEsprRischioTable.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/nonConfAccessiVarComp.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/auditors/ncPraticheTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templatesATPO/cruscotto/js/auditorsATPO/nonConformitaAccessi.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templatesATPO/cruscotto/js/auditorsATPO/mediaTempi.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templatesATPO/cruscotto/js/auditorsATPO/varCNonConfTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templatesATPO/cruscotto/js/auditorsATPO/rischiAccessiTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templatesATPO/cruscotto/js/auditorsATPO/riepilogoFascicoloDocMancanteTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/normativaTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templatesATPO/cruscotto/js/auditorsATPO/esprRischioPianoMiglioramentoTable.js"></script>


 <!-- modellazione -->   
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/areaTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/areaScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/processoTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/processoScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/faseProcessoTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/sottoProcessoScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/attivitaComponenteTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/attivitaComponenteScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/attivitaDettaglioTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/attivitaDettaglioScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/sottoProcessoNormativaTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/attivitaComponenteNormativaTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/attivitaDettaglioNormativaTable.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/processoCircolariInpsTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/processoNoteDecretiTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/processoMessaggiInpsTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/processoLeggiDecretiTable.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/areaProcessoTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaComponente/docAttCompTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaComponente/docAttCompDaAssociaretable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaComponente/docAttCompScript.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaComponente/domAttCompDaAssociareTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaComponente/domAttCompScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaComponente/domAttCompTable.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaComponente/compTecAttCompDaAssociareTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaComponente/compTecAttCompScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaComponente/compTecAttCompTable.js"></script>


<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaProcesso/docProcessoScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaProcesso/docProcessoDaAssociareTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaProcesso/domProcessoScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaProcesso/domProcessoTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaProcesso/domProcessoDaAssociareTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaProcesso/compTecProcessoScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaProcesso/compTecProcessoTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaProcesso/compTecProcessoDaAssociareTable.js"></script>


<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaSottoProcesso/domSottoProcessoScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaSottoProcesso/domSottoProcessoTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaSottoProcesso/domSottoProcessoDaAssociareTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaSottoProcesso/compTecSottoProcessoScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaSottoProcesso/compTecSottoProcessoTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaSottoProcesso/compTecSottoProcessoDaAssociareTable.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaDettaglio/docAttivitaDettaglioScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaDettaglio/docAttivitaDettaglioTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaDettaglio/docAttivitaDettaglioDaAssociareTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaDettaglio/domAttivitaDettaglioScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaDettaglio/domAttivitaDettaglioTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaDettaglio/domAttivitaDettaglioDaAssociareTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaDettaglio/compTecAttivitaDettaglioScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaDettaglio/compTecAttivitaDettaglioTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/modellazioneTables/modificaAttivitaDettaglio/compTecAttivitaDettaglioDaAssociareTable.js"></script>


<!-- Gestione Componenti -->
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/normativaTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/normativaScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/docMediaTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/docMediaScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/dominiTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/dominiFiltroTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/dominiScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/allegatiDominioTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/addAllegatoDominio.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/docMediaFiltroTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/circolariInpsTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/noteDecretiTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/messaggiInpsTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/leggiDecretiTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/componentiTecniciTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/componentiTecniciScript.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/componentiTecniciStoricoTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/componentiTecniciAllegatiTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/addAllegatoCompTecTable.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/allegatiCircolariInpsTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/allegatiNoteDecretiTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/allegatiMessaggiInpsTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/allegatiLeggiDecretiTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/addAllegatoCircolariInps.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/addAllegatoNoteDecreti.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/addAllegatoMessaggiInps.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/addAllegatoLeggiDecreti.js"></script>

<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/componentiTables/dominiValoriTable.js"></script>

<!-- Gestione Profili -->
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/gestioneProfili/profilo/profilo.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/gestioneProfili/profilo/profiloTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/gestioneProfili/associazione/associazioneTable.js"></script>



<!-- ATPO -->
<script src="/CruscottoAuditAtpoWebWeb/templatesATPO/cruscotto/js/initAtpo.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templatesATPO/cruscotto/js/auditorsATPO/praticheAtpoTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templatesATPO/cruscotto/js/auditorsATPO/auditorsATPO.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templatesATPO/cruscotto/js/auditATPO/verbaliTableATPO.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templatesATPO/cruscotto/js/auditorsATPO/rischiPraticheAtpoTable.js"></script>
<script src="/CruscottoAuditAtpoWebWeb/templates/cruscotto/js/crsInit.js" type="text/javascript"></script>
<script src="/CruscottoAuditAtpoWebWeb/templatesATPO/cruscotto/js/auditorsATPO/nonConformitaPraticheAtpoTable.js"></script>

    
<!-- END PAGE LEVEL SCRIPTS -->
<script type="text/javascript">
  $('.date-own').datepicker({
     minViewMode: 2,
     format: 'yyyy'
   });
</script>

<script>
    jQuery(document).ready(function() {   
     
        Metronic.init(); // init metronic core componets
        Layout.init(); // init layout
        Demo.init(); // init demo features 
        Tasks.initDashboardWidget(); // init tash dashboard widget  
        ComponentsEditors.init();
        ComponentsPickers.init();
        UINotific8.init();//init della notifica di errore
        getListaRuoliUtente();
              
        
<%
if(ruoloAttivo.toLowerCase().indexOf("auditors") != -1){
%>
        loadDiv('/CruscottoAuditAtpoWebWeb/accessi', 'appView', null, 'initAuditors' );
        setSelectedTab("auditors");
<%
}else if(ruoloAttivo.toLowerCase().indexOf("amministratore") != -1){
%>
		loadDiv('/CruscottoAuditAtpoWebWeb/usersList', 'appView', null, 'initUserGenerale' );
        setSelectedTab("areaamministrativa");
		
<%


}else{
%>
		loadDiv('/CruscottoAuditAtpoWebWeb/auAudit', 'appView', null, 'initAuditGenerale' );
        setSelectedTab("audit");
		
<%
}
%>
       
    });
</script>
<!-- END JAVASCRIPTS -->
