/*
 * Gestisce le funzioni degli accessi/AuSessioni
 */

//SALVA LA NOTA DELLA SESSIONE
function  salvaNota(){
	var idSessione= $("#sessionID").val();
	nota=$("#notaSessione").val();
	//scrivo la nota in un input hidden.
	//La uso quando viene premuto 'annulla'
	$("#notaHidden").val(nota);
	
	
	
	Metronic.startPageLoading();
	
	$.ajax( {
		type : 'GET',
		//AuditorsAction.java
		url : '/CruscottoAuditAtpoWebWeb/salvaNotaAccesso',
		data :{ 'idSessione' : idSessione,
				'notaSessione' : nota
			   
					   },
					 
	
		success : function(data) {
			Metronic.stopPageLoading();
			//$('#indicatori').DataTable().ajax.reload();
			
						return; 
			

		},error: function(data){
			Metronic.stopPageLoading();
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
				};
					$.notific8('zindex', 11500);
					$.notific8('Errore durante il salvataggio ', settings);
					return; 
		}
	});
	}

function annullaNota(){
	var nota=$("#notaHidden").val();
	$("#notaSessione").val(nota);
	
	
	
}


function selectReportSubTab(id){
	$('ul.nav-tabs > li').removeClass('active');
	$('div.tab-pane').removeClass('active');
	$('ul.dropdown-report >li').removeClass('active');
	$('#'+id).parent('li').addClass( "active");
	$('li.dropdown-report').addClass("active");
	$('#portlet_'+id).addClass("active");
}

function nonConformitaTable(){
	
	loadDiv('/CruscottoAuditAtpoWebWeb/getTabNonConfAccessi', 'tabNonConfAccess', null, 'initNonConformitaAccessi');
}


function selectTabAccessi(caller){
	
	var tabId;
	
	switch(caller){
	case 'pratiche':
		
		tabId= $("ul.nav li.active a").attr("href");
		$(tabId).html("");
		loadDiv('/CruscottoAuditAtpoWebWeb/getTabAccessiPraticheATPO', 'praticheAccessiTab', null, 'initTableAccessiPraticheATPO');
		break;
		
	case 'nonConformita':
		
		tabId= $("ul.nav li.active a").attr("href");
		$(tabId).html("");
		loadDiv('/CruscottoAuditAtpoWebWeb/getTabNonConfAccessi', 'tabNonConfAccess', null, 'initNonConformitaAccessi');
		break;
		
	case 'rischi':
		
		tabId= $("ul.nav li.active a").attr("href");
		$(tabId).html("");
		loadDiv('/CruscottoAuditAtpoWebWeb/getTabRischiAccessi', 'tabRiskAccess', null, 'initRischiAccessi');
		break;
	
	case 'datiContesto':
		
		tabId= $("ul.nav li.active a").attr("href");
		$(tabId).html("");
		loadDiv('/CruscottoAuditAtpoWebWeb/getTabDatiContesto', 'datiContestoTab', null, null);
		break;
	
	case 'questionario':
		
		tabId= $("ul.nav li.active a").attr("href");
		$(tabId).html("");
		loadDiv('/CruscottoAuditAtpoWebWeb/getTabQuestionarioAccessi', 'tabQuestionario', null, null);
		break;
	case 'nota':
		
		tabId= $("ul.nav li.active a").attr("href");
		$(tabId).html("");
		loadDiv('/CruscottoAuditAtpoWebWeb/getNotaSessione', 'tabNota', null, null);
		break;
	case 'mediaTempi':
		
		tabId= $("ul.nav li.active a").attr("href");
		$(tabId).html("");
		loadDiv('/CruscottoAuditAtpoWebWeb/getMediaTempi', 'tabMediaTempi', null, 'initTableMediaTempi');
		break;
	
	}
	
}

function salvaSatoEsameSessione(){
	//var statoEsameSess = $("#selectStato").find('option:selected').attr('value');
	// $("#salvaSatoEsameSessione").removeAttr('disabled');
	// $("#salvaSatoEsameSessione").attr("disabled", "disabled");
	// $("#selectStato").removeAttr('disabled');
	// $("#selectStato").attr("disabled", "disabled");

	Metronic.startPageLoading();
	//Metronic.blockUI();
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/calcolaIndicatoriSessione' , 
		data :{ 
			'statoEsameSessione' : 'A'
			},
		success : function(data) {
						   var settings = {
									theme: 'teal',
									sticky: false,
									horizontalEdge: 'top',
									verticalEdge: 'right',
									life: 3000
								};
						   
						   		Metronic.stopPageLoading();
						   		if (data.status > 0 ){
									$.notific8('zindex', 11500);
									
							   		//Metronic.unblockUI();
						   			if (data.status == 2 ){
										$.notific8('Non ci sono pratiche su cui calcolare gli indicatori!', settings);
						   			}else{
						   				$.notific8('Indicatori Calcolati Non Correttamente!', settings);
						   			}

									$("#salvaSatoEsameSessione").removeAttr('disabled');
									$("#selectStato").val("A");
									$("#selectStato").attr("disabled", "disabled");
						   		}else{
							   		//Metronic.unblockUI();
									$.notific8('zindex', 11500);
									$.notific8('Indicatori Calcolati Correttamente!', settings);
									$("#salvaSatoEsameSessione").attr("disabled", "disabled");
									$("#selectStato").val("C");
									$("#selectStato").removeAttr('disabled');
						   		}
								return; 
		},error: function(data){
				var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
				};
				Metronic.stopPageLoading();
				//Metronic.unblockUI();
				$.notific8('zindex', 11500);
				$.notific8('Errore durante il salvataggio ', settings);
				$("#salvaSatoEsameSessione").removeAttr('disabled');
				$("#selectStato").val("A");
				$("#selectStato").attr("disabled", "disabled");
				return; 
		}
	});	
}

function riapriSessione(src){
	bootbox.confirm("Vuoi riaprire la Sessione?", function(result) {
    	if (result){
			//$(src).attr("disabled", "disabled");
    		//Metronic.blockUI();
			Metronic.startPageLoading();
			
			// CHIAMO PER RIAPRIRE
			$.ajax( {
				type : 'POST',
				url : '/CruscottoAuditAtpoWebWeb/json/riapriSessione',
				data :{ 	
				},
				success : function(data) {
					Metronic.stopPageLoading();
					//Metronic.unblockUI();
					if (data.status > 0 ){
						var settings = {
								theme: 'teal',
								sticky: false,
								horizontalEdge: 'top',
								verticalEdge: 'right',
								life: 3000
						};
						$.notific8('zindex', 11500);
						$.notific8(data.message, settings);
						// RIAPERTURA ANDATA MALE
						// IMPOSTO LO STATO A C E LO ABILITO
						$("#selectStato").val("C");
						$("#selectStato").removeAttr('disabled');
						// DISABILITO PULSANTE
						$("#salvaSatoEsameSessione").attr("disabled", "disabled");
					}else{
						var settings = {
								theme: 'teal',
								sticky: false,
								horizontalEdge: 'top',
								verticalEdge: 'right',
								life: 3000
						};
						$.notific8('zindex', 11500);
						$.notific8('Sessione riaperta correttamente!', settings);
						// RIAPERTURA ANDATA BENE
						// IMPOSTO LO STATO AD A E LO DISABILITO
						$("#selectStato").val("A");
						$("#selectStato").attr("disabled", "disabled");
						// ABILITO PULSANTE
						$("#salvaSatoEsameSessione").removeAttr('disabled');
				
					}
					return; 
				},
				error: function(xhr, ajaxOptions, thrownError){
					//Metronic.unblockUI();
					Metronic.stopPageLoading();
					$.notific8('zindex', 11500);
					$.notific8('Errore durante la riapertura della sessione ', settings);
					// RIAPERTURA ANDATA MALE
					// IMPOSTO LO STATO A C E LO ABILITO
					$("#selectStato").val("C");
					$("#selectStato").removeAttr('disabled');
					// DISABILITO PULSANTE
					$("#salvaSatoEsameSessione").attr("disabled", "disabled");
					return; 
				}	
			});

    	}else{
    		
    		$("#selectStato").val("C");
    	}
    }); 
}

//Action per il download del report PDF
function generaReportAccessi(){
	
	window.location.href= "/CruscottoAuditAtpoWebWeb/jsonATPO/getReportAccessoPDF";
	
}
//Action per il download del report Erm PDF
function generaReportErm(){
	
	window.location.href= "/CruscottoAuditAtpoWebWeb/jsonATPO/getReportErmPDF";
	
}
//Action per il download dell'allegato report PDF
function generaAllegatoReportAccessi(){
	
	window.location.href= "/CruscottoAuditAtpoWebWeb/jsonATPO/getAllegatoReportAccessoPDF";
	
}

function generateReportPianoMiglioramentoWord(){
	
	window.location.href= "/CruscottoAuditAtpoWebWeb/jsonATPO/generateReportPianoMiglioramentoWord";
	
}

// -------------------------------------------------------------------- OKKIO INIZIO 
function generaReportAccessiAnnualeIDCampagna(){
	window.location.href= "/CruscottoAuditAtpoWebWeb/jsonATPO/getReportAccessoPDFAnnuale";
}

function generaAllegatoReportAccessiAnnualeIDCampagna(){
	window.location.href= "/CruscottoAuditAtpoWebWeb/jsonATPO/getAllegatoReportAccessoPDFAnnuale";
}

function generaReportAccessiAnnuale(){
	
	var dataLen = $(".chkCampagna:checked").length;
    if ( dataLen != 1 ){
    	var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare solo una campagna!', settings);
		return true;
    }else if( dataLen == undefined ){
    	
    	var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare una campagna!', settings);
		return true;

    }
    
    var data = $(".chkCampagna:checked").closest("TR").find("TD:nth-child(5) ").text();
    if ( data == null || data == undefined || data == '' || data.length == 0 ){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare una campagna conclusa', settings);
		return true;
    }
    
    
    var campagna = $(".chkCampagna:checked")[0].value;
	window.location.href= "/CruscottoAuditAtpoWebWeb/jsonATPO/getReportAccessoPDFAnnuale?idCampagna="+campagna;
}


function generaAllegatoReportAccessiAnnuale(){
	
	var dataLen = $(".chkCampagna:checked").length;
    if ( dataLen != 1 ){
    	var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare solo una campagna!', settings);
		return true;
    }else if( dataLen == undefined ){
    	
    	var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare una campagna!', settings);
		return true;

    }
    var data = $(".chkCampagna:checked").closest("TR").find("TD:nth-child(5) ").text();
    if ( data == null || data == undefined || data == '' || data.length == 0 ){
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Selezionare una campagna conclusa', settings);
		return true;
    }
    
    var campagna = $(".chkCampagna:checked")[0].value;
	window.location.href= "/CruscottoAuditAtpoWebWeb/jsonATPO/getAllegatoReportAccessoPDFAnnuale?idCampagna="+campagna;
	
}


function chiudiCampagna(idCampangna){
	console.log(idCampangna);
	window.location.href= "/CruscottoAuditAtpoWebWeb/jsonATPO/v?idCampangna="+idCampangna;
}

function aprinCampagna(idCampangna){
	console.log(idCampangna);
	window.location.href= "/CruscottoAuditAtpoWebWeb/jsonATPO/calcolaIndicatoriApriCampagna?idCampangna="+idCampangna;
}
//-------------------------------------------------------------------- OKKIO FINE

