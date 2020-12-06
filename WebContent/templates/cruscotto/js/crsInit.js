/*
 * INIT DELLE TABELLE
 */

function formatCustomDate(data){
	var dateArray=data.split('T');
	var d = new Date(data);
	var month = d.getMonth()+1;
	if(month < 10){
		month = "0"+month;
	}
	var day = d.getDate();
	if(day < 10){
		day = "0"+day;
	}
	return day+"/"+month+"/"+d.getFullYear();
}

function initUserGenerale(){
	
	var generale=UsersTable.init();
	
	$('select').select2({
		 allowClear:true
	});
}
function initTipologicheGenerale(){
	
	var generale=TipologicheTable.init();
	
	$('select').select2({
		 allowClear:true
	});
}
function initIsncGenerale(){
	
	var generale=IsncTable.init();
	
	$('select').select2({
		 allowClear:true
	});
}
function initCaricamentoPraticheGenerale(){
	
	var generale=CaricamentoPraticheTable.init();
	
	$('select').select2({
		 allowClear:true
	});
}


/************
 * 	AUDIT 	*
 ***********/
function initAuditGenerale(){
	
	var generale=GeneraleTable.init();
	
	$('select').select2({
		 allowClear:true
	});
}

function  initAuditGeneraleModificaTables(){
	
	var delegatiTable = DelegatiTable.init();
	
	var fasi= AuditFasiTable.init();

	//inizializzo tabella nell'aside di fasi(button aggiungi)
	var aggiungiFase= FasiDaAggiungere.init();
	
	initDatePicker();
	
	$('select').select2({
		 allowClear:true
	});
}

function initNonConformitaAccessi(){
	var nonConformitaAccessi= NonConformitaAccessi.init();
}
function initRischiAccessi(){
	var r=RischiAccessi.init();
}
function initQuestionario(){
	var questionario= AuditQuestionarioTable.init();
}

function initRisposte(){
	var risposte= AuditRisposteQuestionarioTable.init();
	initDatePicker();
}

//questa funzione viene chgiamata dal menu a tendina dell'aside
//Generale/modfica Audit/fasi-aggiungi
function showFasiDaAggiungereAsideTable(idProcesso){
	
	var aggiungiFase= FasiDaAggiungere.init(idProcesso);
	
}

function initAuditCampagna(){
	document.getElementById("info").style.visibility = "hidden";
	var campagna= CampagnaTable.init();
	initDatePicker();
	$('select').select2({
		 allowClear:true
	});
}

function initTablesGeneraleModifica(){
	var campagnaAccesso= CampagnaAccessoTable.init();
	var campagnaRegole= CampagnaRegoleTable.init();
	var campagnaVerbale= CampagnaVerbaleTable.init();
	var reportSedi= ReportSediTable.init();
	initDatePicker();
	//tabella report sedi dinamica -conta le colonne da creare
//	$.ajax( {
//		type : 'GET',
//		url : '/CruscottoAuditAtpoWebWeb/json/contaColonneReportSedi',
//		dataType:'json',
//		
//		success : function(data) {
//		var numberCol = data.numColonneSediReport;
//		var reportSedi= ReportSediTable.init(numberCol);
//		return; 
//			
//
//		},error: function(data){
//			alert('Tabella report sedi non inizializzata');
//		}
//	});

	$('select').select2({
		 allowClear:true
	});
		
}

function initTablesGeneraleModificaAtpo(){
	var campagnaAccesso= CampagnaAccessoTable.init();
	var campagnaRegole= CampagnaRegoleTable.init();
	var campagnaVerbale= CampagnaVerbaleAtpoTable.init();
	
	//tabella report sedi dinamica -conta le colonne da creare
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/contaColonneReportSedi',
		dataType:'json',
		
		success : function(data) {
		var numberCol = data.numColonneSediReport;
		var reportSedi= ReportSediTable.init(numberCol);
		return; 
			

		},error: function(data){
			alert('Tabella report sedi non inizializzata');
		}
	});

	$('select').select2({
		 allowClear:true
	});
	initDatePicker();
}

//questa tabella si trova in organizzazione audit -> generale-> modifica generale-> rischi e non conformita-> rischio
function initRischiTable(){
	var rischio= RischioTable.init();
	initDatePicker();
}
//tab espressione rischio
function initEsprRischioTable(){
	var esprRischio= EspressRischioTable.init();
	initDatePicker();
}
//tab non confomrmita
function initNonConformTable(){
	var nonConform=NonConformTable.init();
	initDatePicker();
}

//tab var comportamentale
function initVarCompTable(){
	
	var varC= varComportamentale.init();
	initDatePicker();
}

// ACCESSO

function initAuditAccesso(){
	$('#selectSede').select2({
		allowClear:true
	});
	//document.getElementById("info").style.visibility = "hidden";
	var accesso= AccessoTable.init();
	initDatePicker();
	$('select').select2({
		 allowClear:true
	});
}

function initModificaAccessoAudit(){
	
	var praticheSessione= PraticheSessioneTable.init();
	
	var auditors=AuditorsTable.init();
	
	/*$('#selectSede').select2({
		allowClear:true
	});*/
	
	/*TAB CAMPIONE IN AUDIT/ACCESSO/MODIFICA ACCESSO
	 * cerca il Campione della sessione:
	 * -se lo trova scrive il nome nell 'input text e rende visibili buttons e tabella;
	 * - se non lo trova mostra soltanto inputText per crearne uno
	*/
	getCampioneSessione();
	
	initDatePicker();
	$('select').select2({
		 allowClear:true
	});
	
	
	
}


function initNonConfAccVarComp(){
	
	var nonCoAccVarComp= NonConfAccVarComp.init();
}

/************
 * AUDITORS *
 ***********/
 
function initAuditors(){
	
	var accessiTable= AccessiTable.init();
	$('select').select2({
		 allowClear:true
	});
	
	
}


 
//MODIFICA AUDITOR
function initTablesModificaAuditor(){
	//var indicatori= Indicatori.init();
	//var praticheConRischi= PraticheConRischi.init();
	var pratiche= Pratiche.init();
	//var faseDefinizione= FaseDefinizione.init();
	//var faseNotifica= FaseNotifica.init();
	
	
	$( ".datepick" ).datepicker();
	
//	$(".page-container").height('auto');

//	$("a[href^='#portlet'").click(function() {
//		var ref=$(this).attr("href");
//		$(".page-container").height($(ref).height()+200);
//		//alert('prova');
//		  
//	});

}



function initDatiGenerali(){
	var datiGeneraliTable= DatiGeneraliTable.init();
}

function initRischiPraticaTable(){
	
	var rischiPratica= RischiPraticheTable.init();
}


function initNonConformitaPratiche(){
	
	var nonConformitaPrt= NcPraticheTable.init();
}

function initStatoEsprRsichio(){
	var statoEsprRsichio= SEsprRischioTable.init();
}

/****************
 * MODELLAZIONE *
 ****************/

function initArea(){
	
	var areaTable=AreaTable.init();
	initDatePicker();
}

function initProcesso(){
	var processoTable=ProcessoTable.init();
	initDatePicker();
}

function initSottoProcesso(){
	var faseProcessoTable=FaseProcessoTable.init();
	initDatePicker();
}

function initAttivitaComponente(){
	var attivitaComponenteTable=AttivitaComponenteTable.init();
	initDatePicker();
}

function initAttivitaDettaglio(){
	var attivitaDettaglioTable=AttivitaDettaglioTable.init();
	initDatePicker();
}

function initAreaProcesso(){
	var areaProcessoTable=AreaProcessoTable.init();
	initDatePicker();
}

function initDocAttCompTable(){
	var docAttCompTable= DocAttCompTable.init();
	var docAttCompDaAssociareTable=DocAttCompDaAssociareTable.init();
	initDatePicker();
}

function initDomAttCompTable(){
	var domAttCompTable= DomAttCompTable.init();
	var domAttCompDaAssociareTable=DomAttCompDaAssociareTable.init();
	initDatePicker();
}

function initCompTecAttCompTable(){
	var compTecAttCompTable=ComponentiTecniciAttCompTable.init();
	var compTecAttCompDaAssociareTable=CompTecAttCompDaAssociareTable.init();
	initDatePicker();
}




function initDominiProcessoTable(){
	var domProcessoTable = DomProcessoTable.init();
	var domProcessoDaAssociareTable = DomProcessoDaAssociareTable.init();
	initDatePicker();
}

function initCompTecProcessoTable(){
	var compTecProcessoTable = ComponentiTecniciProcessoTable.init();
	var compTecProcessoDaAssociareTable = ComponentiTecniciProcessoDaAssociareTable.init();
	initDatePicker();
}


function initDominiSottoProcessoTable(){
	var domSottoProcessoTable = DomSottoProcessoTable.init();
	var domSottoProcessoDaAssociareTable = DomSottoProcessoDaAssociareTable.init();
	initDatePicker();
}

function initCompTecSottoProcessoTable(){
	var compTecSottoProcessoTable = ComponentiTecniciSottoProcessoTable.init();
	var compTecSottoProcessoDaAssociareTable = ComponentiTecniciSottoProcessoDaAssociareTable.init();
	initDatePicker();
}

function initDocAttivitaDettaglioTable(){
	var docAttivitaDettaglioTable = DocAttivitaDettaglioTable.init();
	var docAttivitaDettaglioDaAssociareTable = DocAttivitaDettaglioDaAssociareTable.init();
	initDatePicker();
}

function initDominiAttivitaDettaglioTable(){
	var domAttivitaDettaglioTable = DomAttivitaDettaglioTable.init();
	var domAttivitaDettaglioDaAssociareTable = DomAttivitaDettaglioDaAssociareTable.init();
	initDatePicker();
}

function initCompTecAttivitaDettaglioTable(){
	var compTecAttivitaDettaglioTable = ComponentiTecniciAttivitaDettaglioTable.init();
	var compTecAttivitaDettaglioDaAssociareTable = ComponentiTecniciAttivitaDettaglioDaAssociareTable.init();
	initDatePicker();
}


/***********************
 * GESTIONE COMPONENTI *
 ***********************/

function initNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();

	if(idTipo == undefined || idTipo == ''){
		idTipo = 1;
	}
	
	//tabella normativa dinamica - conta le colonne da creare
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/contaColonneNormativa?idTipo='+idTipo,
		dataType:'json',
		
		success : function(data) {
		
			var numColDate = data.numColonneDate;
			var numColText = data.numColonneText;
			var numColNumber = data.numColonneNumber;
			var normativaTable= NormativaTable.init(numColDate, numColText, numColNumber);
	
			return; 
			
		},error: function(data){
			alert('Tabella normativa non inizializzata');
		}
	});
	
}

function initCircolariINps(){
	
	//var idTipo = $('#comboTipoNormativa option:selected').val();
	var circolariInps = CircolartiInpsTable.init();
	
}

function initNoteDecreti(){
	
	var noteDecreti = NoteDecretiTable.init();
	
}

function initMessaggiInps(){
	
	var messaggiInps = MessaggiInpsTable.init();
	
}

function initLeggiDecreti(){
	
	var leggiDecreti = LeggiDecretiTable.init();
	
}

function initDocMedia(){
	var docMedia=DocumentiMediaTable.init();
}
function initDocMediaFiltro(){
	var docMediaF=DocumentiMediaFiltroTable.init();
}

function initDomini(){
	var domini=DominiTable.init();
}
function initDominiFiltro(){
	var dominiFiltro=DominiFiltroTable.init();
}
function initAllegatiTables(){
	var allegati=AllegatiDominioTable.init();
	var addAllegati=AddAllegatiDominioTable.init();
}

function initCompTecnici(){
	var compTec=ComponentiTecniciTable.init();
}
function initStoricoCompTecTable(){
	var storicoCT= ComponentiTecniciStoricoTable.init();
}

function initAllegatiCtTables(){
	var allegCt= AllegatiCompTec.init();
	var addAllegatiCt=AddAllegatiCtTable.init();
}

//cerca il Campione della sessione
function getCampioneSessione(){
	//getCampioneSessione
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/getCampioneSessione',
		dataType:'json',
		
		success : function(data) {
			var campione = data.campioneSessione;
			console.log(campione);
			//se la sessione possiede un campione
			if(campione !== null){ 
				//input text scrive nomeCampione
				$('#nomeCampione').val(campione.descrizione);
				campioneLocal=true;
				
				//mostro componenti
				document.getElementById("addPratica").style.visibility = "visible";
				document.getElementById("removePratica").style.visibility = "visible";
				
					//controllo che abbia dei verbali associati
					document.getElementById("verbaliCampione").style.visibility = "visible";
					
					var verbali= VerbaliCampione.init();
				}else{
					//nascondo tutto
					document.getElementById("addPratica").style.visibility = "hidden";
					document.getElementById("removePratica").style.visibility = "hidden";
					document.getElementById("verbaliCampione").style.visibility = "hidden";
				}
			
			

		},error: function(data){
	
		}
		
	});
}


function initProcessoCircolariInps(){
	
	var processoCircolariInpsTable= ProcessoCircolariInpsTable.init();
	
	
}
function initProcessoNoteDecreti(){
	
	var processoNoteDecretiTable= ProcessoNoteDecretiTable.init();
	
	
}
function initProcessoMessaggiInps(){
	
	var processoMessaggiInpsTable= ProcessoMessaggiInpsTable.init();
	
	
}
function initProcessoLeggiDecreti(){
	
	var processoLeggiDecretiTable= ProcessoLeggiDecretiTable.init();
	
	
}


function initSottoProcessoNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();

	if(idTipo == undefined || idTipo == ''){
		idTipo = 1;
	}
	
	//tabella normativa dinamica - conta le colonne da creare
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/contaColonneNormativa?idTipo='+idTipo,
		dataType:'json',
		
		success : function(data) {
		
			var numColDate = data.numColonneDate;
			var numColText = data.numColonneText;
			var numColNumber = data.numColonneNumber;
			var sottoProcessoNormativaTable= SottoProcessoNormativaTable.init(numColDate, numColText, numColNumber);
	
			return; 
			
		},error: function(data){
			alert('Tabella normativa non inizializzata');
		}
	});
	
}


function initAttivitaComponenteNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();

	if(idTipo == undefined || idTipo == ''){
		idTipo = 1;
	}
	
	//tabella normativa dinamica - conta le colonne da creare
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/contaColonneNormativa?idTipo='+idTipo,
		dataType:'json',
		
		success : function(data) {
		
			var numColDate = data.numColonneDate;
			var numColText = data.numColonneText;
			var numColNumber = data.numColonneNumber;
			var acNormativaTable= ACNormativaTable.init(numColDate, numColText, numColNumber);
	
			return; 
			
		},error: function(data){
			alert('Tabella normativa non inizializzata');
		}
	});
	
}

function initAttivitaDettaglioNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();

	if(idTipo == undefined || idTipo == ''){
		idTipo = 1;
	}
	
	//tabella normativa dinamica - conta le colonne da creare
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/contaColonneNormativa?idTipo='+idTipo,
		dataType:'json',
		
		success : function(data) {
		
			var numColDate = data.numColonneDate;
			var numColText = data.numColonneText;
			var numColNumber = data.numColonneNumber;
			var adNormativaTable= ADNormativaTable.init(numColDate, numColText, numColNumber);
	
			return; 
			
		},error: function(data){
			alert('Tabella normativa non inizializzata');
		}
	});
	
}


function initAllegatiCircolariInps(){
	var allegatiCI = AllegatiCircolariInpsTable.init();
	var addAllegatiCI = AddAllegatiCircolariInpsTable.init();
}
function initAllegatiNoteDecreti(){
	var allegatiND = AllegatiNoteDecretiTable.init();
	var addAllegatiND = AddAllegatiNoteDecretiTable.init();
}
function initAllegatiMessaggiInps(){
	var allegatiMI = AllegatiMessaggiInpsTable.init();
	var addAllegatiMI = AddAllegatiMessaggiInpsTable.init();
}
function initAllegatiLeggiDecreti(){
	var allegatiLD = AllegatiLeggiDecretiTable.init();
	var addAllegatiLD = AddAllegatiLeggiDecretiTable.init();
}

function initDominiValoriTables(){
	var dominiValori = DominiValoriTable.init();
}

 /*******************
 * GESTIONE PROFILI *
 *******************/

function initProfilo(){
	var profiloTable=ProfiloTable.init();
	
	$('select').select2({
		 allowClear:true
	});
}

function initAssociazione(){
	var associazioneTable=AssociazioneTable.init();
	
	$('select').select2({
		 allowClear:true
	});	
}



