/*
 * Gestisce le funzioni CRUD della normativa.
 */

//mostra la modale per inserimento nuova normativa.
function showAsideNormativa(){

	// valorizzo dinamicamente il titolo della modale
	var idTipo = $('#comboTipoNormativa option:selected').val();

	if(idTipo == 1){
		$('.modalNoteDecreti').css("display", "none");
		$('.modalMessaggiInps').css("display", "none");
		$('.modalLeggiDecreti').css("display", "none");
		
		$("#formCircolariInps").trigger('reset');
		
		$('.modalCircolariInps').css("display", "block");
		jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		if(contOne){
			jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
			contOne = false;
		}else{

			$('.modalCircolariInps').css("display", "none");
			contOne = true;
		}
		
	}
	if(idTipo == 2){
		$('.modalCircolariInps').css("display", "none");
		$('.modalMessaggiInps').css("display", "none");
		$('.modalLeggiDecreti').css("display", "none");
		
		$("#formNoteDecreti").trigger('reset');
		
		$('.modalNoteDecreti').css("display", "block");
		jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		if(contOne){
			jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
			contOne = false;
		}else{

			$('.modalNoteDecreti').css("display", "none");
			contOne = true;
		}
		
	}
	if(idTipo == 3){
		$('.modalCircolariInps').css("display", "none");
		$('.modalNoteDecreti').css("display", "none");
		$('.modalLeggiDecreti').css("display", "none");
		
		$("#formMessaggiInps").trigger('reset');
		
		$('.modalMessaggiInps').css("display", "block");
		jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		if(contOne){
			jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
			contOne = false;
		}else{

			$('.modalMessaggiInps').css("display", "none");
			contOne = true;
		}
		
	}
	if(idTipo == 4){
		$('.modalCircolariInps').css("display", "none");
		$('.modalNoteDecreti').css("display", "none");
		$('.modalMessaggiInps').css("display", "none");
		
		$("#formLeggiDecreti").trigger('reset');
		
		$('.modalLeggiDecreti').css("display", "block");
		jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		if(contOne){
			jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
			contOne = false;
		}else{

			$('.modalLeggiDecreti').css("display", "none");
			contOne = true;
		}
		
	}

}


//function salvaNormativa(){
//
//	// recupero i valori dai campi obbligatori (comuni a tutti i tipi normativa)
//	var idDatiTipo = $('#idDatiTipo').val();
//	var idTipo = $('#comboTipoNormativa option:selected').val();
//	var dataEmissione = $('#dataEmissione').val();
//	var descrizione = $('#descrizione').val();
//	var codice = $('#codice').val();
//	var oggetto = $('#oggetto').val();
//
//	var from;
//	var dataInizio;
//	var to;
//	var dataFine;
//
//	if(idTipo == '' || idTipo == undefined){
//		$('#messageNormativaDiv').html("<b>Attenzione!</b><br>Il campo Tipo Normativa è obbligatorio");
//		$('#normativaModal').modal();
//		return;
//	}
//	if(dataEmissione == '' || dataEmissione == undefined){
//		$('#messageNormativaDiv').html("<b>Attenzione!</b><br>Il campo Data Emissione è obbligatorio");
//		$('#normativaModal').modal();
//		return;
//	}
//	if(codice == '' || codice == undefined){
//		$('#messageNormativaDiv').html("<b>Attenzione!</b><br>Il campo Codice è obbligatorio");
//		$('#normativaModal').modal();
//		return;
//	}
//	if(oggetto == '' || oggetto == undefined){
//		$('#messageNormativaDiv').html("<b>Attenzione!</b><br>Il campo Oggetto è obbligatorio");
//		$('#normativaModal').modal();
//		return;
//	}
//	if(descrizione == '' || descrizione == undefined){
//		$('#messageNormativaDiv').html("<b>Attenzione!</b><br>Il campo Descrizione è obbligatorio");
//		$('#normativaModal').modal();
//		return;
//	}
//	if($('#dataInizio').val() == '' || $('#dataInizio').val() == undefined){
//		$('#messageNormativaDiv').html("<b>Attenzione!</b><br>Il campo Data Inizio è obbligatorio");
//		$('#normativaModal').modal();
//		return;
//	}else{
//		from = $('#dataInizio').val().split('/');
//		dataInizio = new Date(from[2], from[1] - 1, from[0]);
//	}
//
//	if($('#dataFine').val() == '' || $('#dataFine').val() == undefined){
//		$('#messageNormativaDiv').html("<b>Attenzione!</b><br>Il campo Data Fine è obbligatorio");
//		$('#normativaModal').modal();
//		return;
//	}else{
//		to = $('#dataFine').val().split('/');
//		dataFine = new Date(to[2], to[1] - 1, to[0]);
//	}
//
//	// controllo dataFine maggiore di dataInizio
//	if(dataFine <= dataInizio){
//		$('#messageNormativaDiv').html("<b>Attenzione!</b><br>La Data Fine deve essere maggiore della Data Inizio");
//		$('#normativaModal').modal();
//		return;
//	}
//
//	var dataForm = '{' +
//	'"datiTipoNormativa.idDatiTipo" : "'+idDatiTipo+'",' +
//	'"idTipo" : "'+idTipo+'",' +
//	'"datiTipoNormativa.date1" : "'+dataInizio.toLocaleDateString()+'",' +
//	'"datiTipoNormativa.date2" : "'+dataFine.toLocaleDateString()+'",' +
//	'"datiTipoNormativa.text1" : "'+dataEmissione+'",' +
//	'"datiTipoNormativa.text2" : "'+codice+'",' +
//	'"datiTipoNormativa.text3" : "'+oggetto+'",' +
//	'"datiTipoNormativa.text4" : "'+descrizione+'"';
//
//	$.ajax( {
//		type : 'GET',
//		url : '/CruscottoAuditAtpoWebWeb/json/salvaNormativa',
//		data : JSON.parse(popolaDataForm(idTipo, dataForm)) ,
//
//		success : function(data) {
//		Metronic.stopPageLoading();
//
//		slideAsideNew('');
//		$('#normativa_table').DataTable().ajax.reload();
//
//		return; 
//	},error: function(data){
//		var settings = {
//				theme: 'teal',
//				sticky: false,
//				horizontalEdge: 'top',
//				verticalEdge: 'right',
//				life: 3000
//		};
//		Metronic.stopPageLoading();
//		$.notific8('zindex', 11500);
//		$.notific8('Errore durante il salvataggio ', settings);
//		return; 
//	}
//	});
//
//
//}



function salvaCircolariInps(operation){
	
	// attivazione delle modali in base all'operazione
	// inserimento/modifica
	var normativaDiv = '#messageNormativaDiv';
	var modalDiv = '#normativaModal';
	if(operation != 'I'){
		normativaDiv = '#messageCircolariInpsDiv';
		modalDiv = '#modificaCircolariInpsModal';
	}

	// recupero i valori dai campi della jsp
	var idCircolariInps = $('#idCircolariInps').val();
	var idTipo = $('#comboTipoNormativa option:selected').val();
	var dataEmissione = $('#dataEmissione').val();
	var codice = $('#codice').val();
	var oggetto = $('#oggetto').val();
	var descSintetica = $('#descSintetica').val();
	var descDettaglio = $('#descDettaglio').val();
	var direzioneEmittente1 = $('#direzioneEmittente1').val();
	var direzioneEmittente2 = $('#direzioneEmittente2').val();
	var direzioneEmittente3 = $('#direzioneEmittente3').val();
	var direzioneEmittente4 = $('#direzioneEmittente4').val();
	var direzioneEmittente5 = $('#direzioneEmittente5').val();
	var direzioneEmittente6 = $('#direzioneEmittente6').val();
	var sommario = $('#sommario').val();
	var anno = $('#anno').val();
	var enteEmittente = $('#comboEnteEmittente option:selected').val();

	var dataInizio;
	var dataFine = $('#dataFine').val();
	

	if(dataEmissione == '' || dataEmissione == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Data Emissione è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(codice == '' || codice == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Numero Circolare è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(oggetto == '' || oggetto == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Oggetto è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(descSintetica == '' || descSintetica == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Descrizione Sintetica è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(descDettaglio == '' || descDettaglio == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Descrizione Dettaglio è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	
	if($('#dataInizio').val() == '' || $('#dataInizio').val() == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Data Inizio è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}else{
		dataInizio = $('#dataInizio').val();
	}

	
	// controllo dataFine maggiore di dataInizio
	if(dataFine != '' && dataFine != undefined){
		if(!checkDate(dataInizio, dataFine)){
			return;
		}
	}
	
	
	// controllo che nel campo anno siano stati inseriri solo numeri (4)
	if(anno != '' && anno != undefined){
		if(!isInteger(anno)){
			$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Anno Emissione richiede valori di tipo numerico");
			$(''+modalDiv + '').modal();
			return;
		}
		if(anno.length != 4){
			$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Anno Emission deve essere inserito per intero");
			$(''+modalDiv + '').modal();
			return;
		}
	}else{
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Anno Emissione è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	
	if(direzioneEmittente1 == '' || direzioneEmittente1 == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Direzione Emittente1 è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	
	if(enteEmittente == '' || enteEmittente == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Ente Emittente è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}

	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/salvaCircolariInps',
		data : { 	'circolariInps.idCircolariInps' : idCircolariInps,
					'circolariInps.dataEmissione' : dataEmissione,
					'circolariInps.codice' : codice,
					'circolariInps.oggetto' : oggetto,
					'circolariInps.descSintetica' : descSintetica,
					'circolariInps.descDettaglio' : descDettaglio,
					'circolariInps.dataInizio' : dataInizio,
					'circolariInps.dataFine' : dataFine,
					'circolariInps.direzioneEmittente1' : direzioneEmittente1,
					'circolariInps.direzioneEmittente2' : direzioneEmittente2,
					'circolariInps.direzioneEmittente3' : direzioneEmittente3,
					'circolariInps.direzioneEmittente4' : direzioneEmittente4,
					'circolariInps.direzioneEmittente5' : direzioneEmittente5,
					'circolariInps.direzioneEmittente6' : direzioneEmittente6,
					'circolariInps.sommario' : sommario,
					'circolariInps.anno' : anno,
					'circolariInps.enteEmittente' : enteEmittente
					
		   },

		success : function(data) {
		Metronic.stopPageLoading();

		$('#circolariInpsTable').DataTable().ajax.reload();
		
		if(operation == 'I'){
			slideAsideNew('');
		}else{
			
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			Metronic.stopPageLoading();
			$.notific8('zindex', 11500);
			$.notific8('Circolare Inps aggiornata con successo ', settings);
			return; 
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
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return; 
	}
	});


}


function salvaNoteDecreti(operation){
	
	// attivazione delle modali in base all'operazione
	// inserimento/modifica
	var normativaDiv = '#messageNormativaDiv';
	var modalDiv = '#normativaModal';
	if(operation != 'I'){
		normativaDiv = '#messageNoteDecretiDiv';
		modalDiv = '#modificaNoteDecretiModal';
	}

	// recupero i valori dai campi della jsp
	var idNoteDecreti = $('#idNoteDecreti').val();
	var idTipo = $('#comboTipoNormativa option:selected').val();
	var dataEmissione = $('#dataEmissioneND').val();
	var codice = $('#codiceND').val();
	var oggetto = $('#oggettoND').val();
	var descSintetica = $('#descSinteticaND').val();
	var descDettaglio = $('#descDettaglioND').val();
	var riferimenti = $('#riferimentiND').val();

	var dataInizio;
	var dataFine = $('#dataFineND').val();

	if(dataEmissione == '' || dataEmissione == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Data Emissione è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(codice == '' || codice == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Codice è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(oggetto == '' || oggetto == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Oggetto è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(descSintetica == '' || descSintetica == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Descrizione Sintetica è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(descDettaglio == '' || descDettaglio == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Descrizione Dettaglio è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	
	if($('#dataInizioND').val() == '' || $('#dataInizioND').val() == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Data Inizio è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}else{
		dataInizio = $('#dataInizioND').val();
	}


	// controllo dataFine maggiore di dataInizio
	if(dataFine != '' && dataFine != undefined){
		if(!checkDate(dataInizio, dataFine)){
			return;
		}
	}

	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/salvaNoteDecreti',
		data : { 	'noteDecreti.idNoteDecreti' : idNoteDecreti,
					'noteDecreti.dataEmissione' : dataEmissione,
					'noteDecreti.codice' : codice,
					'noteDecreti.oggetto' : oggetto,
					'noteDecreti.descSintetica' : descSintetica,
					'noteDecreti.descDettaglio' : descDettaglio,
					'noteDecreti.dataInizio' : dataInizio,
					'noteDecreti.dataFine' : dataFine,
					'noteDecreti.riferimenti' : riferimenti
					
		   },

		success : function(data) {
		Metronic.stopPageLoading();

		$('#noteDecretiTable').DataTable().ajax.reload();
		if(operation == 'I'){
			slideAsideNew('');
		}else{
			
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			Metronic.stopPageLoading();
			$.notific8('zindex', 11500);
			$.notific8('Note Decreti aggiornata con successo ', settings);
			return; 
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
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return; 
	}
	});


}


function salvaMessaggiInps(operation){
	
	// attivazione delle modali in base all'operazione
	// inserimento/modifica
	var normativaDiv = '#messageNormativaDiv';
	var modalDiv = '#normativaModal';
	if(operation != 'I'){
		normativaDiv = '#messageMessaggiInpsDiv';
		modalDiv = '#modificaMessaggiInpsModal';
	}

	// recupero i valori dai campi della jsp
	var idMessaggiInps = $('#idMessaggiInps').val();
	var idTipo = $('#comboTipoNormativa option:selected').val();
	var dataEmissione = $('#dataEmissioneMI').val();
	var codice = $('#codiceMI').val();
	var oggetto = $('#oggettoMI').val();
	var descSintetica = $('#descSinteticaMI').val();
	var descDettaglio = $('#descDettaglioMI').val();
	var enteEmittente = $('#comboEnteEmittenteMI option:selected').val();

	var dataInizio;
	var dataFine = $('#dataFineMI').val();

	if(dataEmissione == '' || dataEmissione == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Data Emissione è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(codice == '' || codice == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Codice è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(oggetto == '' || oggetto == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Oggetto è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(descSintetica == '' || descSintetica == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Descrizione Sintetica è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(descDettaglio == '' || descDettaglio == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Descrizione Dettaglio è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	
	if($('#dataInizioMI').val() == '' || $('#dataInizioMI').val() == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Data Inizio è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}else{
		dataInizio = $('#dataInizioMI').val();
	}

	// controllo dataFine maggiore di dataInizio
	if(dataFine != '' && dataFine != undefined){
		if(!checkDate(dataInizio, dataFine)){
			return;
		}
	}
	
	if(enteEmittente == '' || enteEmittente == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Ente Emittente è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}

	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/salvaMessaggiInps',
		data : { 	'messaggiInps.idMessaggiInps' : idMessaggiInps,
					'messaggiInps.dataEmissione' : dataEmissione,
					'messaggiInps.codice' : codice,
					'messaggiInps.oggetto' : oggetto,
					'messaggiInps.descSintetica' : descSintetica,
					'messaggiInps.descDettaglio' : descDettaglio,
					'messaggiInps.dataInizio' : dataInizio,
					'messaggiInps.dataFine' : dataFine,
					'messaggiInps.enteEmittente' : enteEmittente
					
		   },

		success : function(data) {
		Metronic.stopPageLoading();

		$('#messaggiInpsTable').DataTable().ajax.reload();
		if(operation == 'I'){
			slideAsideNew('');
		}else{
			
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			Metronic.stopPageLoading();
			$.notific8('zindex', 11500);
			$.notific8('Messaggi aggiornato con successo ', settings);
			return; 
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
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return; 
	}
	});


}

function salvaLeggiDecreti(operation){
	
	// attivazione delle modali in base all'operazione
	// inserimento/modifica
	var normativaDiv = '#messageNormativaDiv';
	var modalDiv = '#normativaModal';
	if(operation != 'I'){
		normativaDiv = '#messageLeggiDecretiDiv';
		modalDiv = '#modificaLeggiDecretiModal';
	}

	// recupero i valori dai campi della jsp
	var idLeggiDecreti = $('#idLeggiDecreti').val();
	var idTipo = $('#comboTipoNormativa option:selected').val();
	var dataEmissione = $('#dataEmissioneLD').val();
	var codice = $('#codiceLD').val();
	var oggetto = $('#oggettoLD').val();
	var descSintetica = $('#descSinteticaLD').val();
	var descDettaglio = $('#descDettaglioLD').val();
	var articolo = $('#articoloLD').val();
	var annoGui = $('#annoGuiLD').val();
	var numeroGui = $('#numeroGuiLD').val();
	var tipoLegge = $('#comboTipoLeggeLD option:selected').val();

	var dataInizio;
	var dataFine = $('#dataFineLD').val();

	if(dataEmissione == '' || dataEmissione == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Data Emissione è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(codice == '' || codice == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Codice è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(oggetto == '' || oggetto == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Oggetto è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(descSintetica == '' || descSintetica == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Descrizione Sintetica è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(descDettaglio == '' || descDettaglio == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Descrizione Dettaglio è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	
	if($('#dataInizioLD').val() == '' || $('#dataInizioLD').val() == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Data Inizio è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}else{
		dataInizio = $('#dataInizioLD').val();
	}

	// controllo dataFine maggiore di dataInizio
	if(dataFine != '' && dataFine != undefined){
		if(!checkDate(dataInizio, dataFine)){
			return;
		}
	}
	
	// controllo che nel campo anno siano stati inseriri solo numeri (4)
	if(annoGui != '' && annoGui != undefined){
		if(!isInteger(annoGui)){
			$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Anno GUI richiede valori di tipo numerico");
			$(''+modalDiv + '').modal();
			return;
		}
		if(annoGui.length != 4){
			$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Anno GUI deve essere inserito per intero");
			$(''+modalDiv + '').modal();
			return;
		}
	}
	
	if(tipoLegge == '' || tipoLegge == undefined){
		$(''+ normativaDiv + '').html("<b>Attenzione!</b><br>Il campo Tipo Legge è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}

	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/salvaLeggiDecreti',
		data : { 	'leggiDecreti.idLeggiDecreti' : idLeggiDecreti,
					'leggiDecreti.dataEmissione' : dataEmissione,
					'leggiDecreti.codice' : codice,
					'leggiDecreti.oggetto' : oggetto,
					'leggiDecreti.descSintetica' : descSintetica,
					'leggiDecreti.descDettaglio' : descDettaglio,
					'leggiDecreti.dataInizio' : dataInizio,
					'leggiDecreti.dataFine' : dataFine,
					'leggiDecreti.articolo' : articolo,
					'leggiDecreti.annoGui' : annoGui,
					'leggiDecreti.numeroGui' : numeroGui,
					'leggiDecreti.tipoLegge' : tipoLegge
					
		   },

		success : function(data) {
		Metronic.stopPageLoading();

		$('#leggiDecretiTable').DataTable().ajax.reload();
		if(operation == 'I'){
			slideAsideNew('');
		}else{
			
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			Metronic.stopPageLoading();
			$.notific8('zindex', 11500);
			$.notific8('Leggi Decreti aggiornato con successo ', settings);
			return; 
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
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il salvataggio ', settings);
		return; 
	}
	});


}



function eliminaNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();

	var checkedIds;
	
	
	if(idTipo == 1){
		 checkedIds = $(".chkCircolariInps:checked").map(function() {
			    return this.value;
			
			  }).toArray();
	}
	if(idTipo == 2){
		 checkedIds = $(".chkNoteDecreti:checked").map(function() {
			    return this.value;
			
			  }).toArray();
	}
	if(idTipo == 3){
		 checkedIds = $(".chkMessaggiInps:checked").map(function() {
			    return this.value;
			
			  }).toArray();
	}
	if(idTipo == 4){
		 checkedIds = $(".chkLeggiDecreti:checked").map(function() {
			    return this.value;
			
			  }).toArray();
	}
	
	var idNormativa = checkedIds[0];

	

	//se presenta riferimenti con   CRS_ASS_SOTTOPROCESSO_CLASSE o CRS_ASS_PROCESSO_CLASSE non posso eliminare

	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/eliminaNormativaCheckReference',
		data : { 	'idNormativa' : idNormativa,
					'idTipo' : idTipo
		},

	success : function(data) {

		if(data.checkReferenceNormativa == "s"){
			$('#deleteNormativaModal').modal('toggle');
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};

			$.notific8('zindex', 11500);
			$.notific8('Non è possibile eliminare la normativa perché sono presenti dipendenze con altri elementi  ', settings);
			return; 

		}else if(data.checkReferenceNormativa == "n"){
			$.ajax( {
				type : 'GET',
				url : '/CruscottoAuditAtpoWebWeb/eliminaNormativa',
				data : { 	
					'idNormativa' : idNormativa,
					'idTipo' : idTipo
				},

			success : function(data) {
					
					if(idTipo == 1){
						$('#circolariInpsTable').DataTable().ajax.reload();
					}
					if(idTipo == 2){
						$('#noteDecretiTable').DataTable().ajax.reload();
					}
					if(idTipo == 3){
						$('#messaggiInpsTable').DataTable().ajax.reload();
					}
					if(idTipo == 4){
						$('#leggiDecretiTable').DataTable().ajax.reload();
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
				$.notific8('zindex', 11500);
				$.notific8('Errore durante il salvataggio ', settings);
				return; 
			}
			});

			$('#deleteNormativaModal').modal('toggle');

		}else{
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};

			$.notific8('zindex', 11500);
			$.notific8('Normativa non eliminata per malfunzionamento applicativo ', settings);
			return; 
		}

	}

	,error: function(data){
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



function ricaricaNormativa(){

	var idTipo = $('#comboTipoNormativa option:selected').val();
	
	if(idTipo == 1){
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageCircolariInps', 'divNormativaTable', null, 'initCircolariINps');
	}
	
	if(idTipo == 2){
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageNoteDecreti', 'divNormativaTable', null, 'initNoteDecreti');
	}
	
	if(idTipo == 3){
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageMessaggiInps', 'divNormativaTable', null, 'initMessaggiInps');
	}
	
	if(idTipo == 4){
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageLeggiDecreti', 'divNormativaTable', null, 'initLeggiDecreti');
	}
	

}

function modificaNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();
	
	if(idTipo == 1){
		getCheckBoxID('modificaCircolariInps');
	}
	if(idTipo == 2){
		getCheckBoxID('modificaNoteDecreti');
	}
	if(idTipo == 3){
		getCheckBoxID('modificaMessaggiInps');
	}
	if(idTipo == 4){
		getCheckBoxID('modificaLeggiDecreti');
	}
	
	
}


function caricaAllegati(idTipo){

	if(idTipo == 1){
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageAllegatiCircolariInps', 'allegatiCI', null, 'initAllegatiCircolariInps');
	}
	
	if(idTipo == 2){
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageAllegatiNoteDecreti', 'allegatiND', null, 'initAllegatiNoteDecreti');
	}
	
	if(idTipo == 3){
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageAllegatiMessaggiInps', 'allegatiMI', null, 'initAllegatiMessaggiInps');
	}
	
	if(idTipo == 4){
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageAllegatiLeggiDecreti', 'allegatiLD', null, 'initAllegatiLeggiDecreti');
	}

}

//ASIDE TABELLA DOCUMENTI DA ALLEGARE
function aggiungiAllegatoNormativa(idTipo){

	var checkedIds;
	var normativa;
	
	if(idTipo == 1){
		
		normativa = $('#idCircolariInps').val();
		
		checkedIds = $(".chkAllCI:checked").map(function() {
			return this.value;

		}).toArray();
	}
	if(idTipo == 2){
		
		normativa = $('#idNoteDecreti').val();
		
		checkedIds = $(".chkAllND:checked").map(function() {
			return this.value;

		}).toArray();
	}
	if(idTipo == 3){
		
		normativa = $('#idMessaggiInps').val();
		
		checkedIds = $(".chkAllMI:checked").map(function() {
			return this.value;

		}).toArray();
	}
	if(idTipo == 4){
		
		normativa = $('#idLeggiDecreti').val();
		
		checkedIds = $(".chkAllLD:checked").map(function() {
			return this.value;

		}).toArray();
	}
	
	// controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return;
	}else{
		var idDocumento = checkedIds[0];

		$.ajax( {
			type : 'POST',
			dataType:'json',
			//AuAuditAction.java
			url : '/CruscottoAuditAtpoWebWeb/json/addAllegatoNormativa',
			data :{ 
			'idDocumento' : idDocumento,
			'idTipo' : idTipo,
			'idNormativa' : normativa
		},
		success : function(data) {

			if(idTipo == 1){
				$('#addAllegatiCircolariInpsTable').DataTable().ajax.reload();
				$('#allegatiCircolariInpsTable').DataTable().ajax.reload();
			}
			if(idTipo == 2){
				$('#addAllegatiNoteDecretiTable').DataTable().ajax.reload();
				$('#allegatiNoteDecretiTable').DataTable().ajax.reload();
			}
			if(idTipo == 3){
				$('#addAllegatiMessaggiInpsTable').DataTable().ajax.reload();
				$('#allegatiMessaggiInpsTable').DataTable().ajax.reload();
			}
			if(idTipo == 4){
				$('#addAllegatiLeggiDecretiTable').DataTable().ajax.reload();
				$('#allegatiLeggiDecretiTable').DataTable().ajax.reload();
			}
			
			var settings = {
					theme: 'teal',
					sticky: false,
					horizontalEdge: 'top',
					verticalEdge: 'right',
					life: 3000
			};
			$.notific8('zindex', 11500);
			$.notific8('Documento allegato correttamente ', settings);
			return; 

		},error: function(data){

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
	return;

}


//MOSTRA MODALE ELIMINA ALLEGATO__________________________________________________
function showRemoveModalCircolariInps(){
	var checkedIds = $(".chkDocCircolariInps:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return
	}else{
		$('#deleteAllegatiModalCI').modal();
	}

}
function showRemoveModalNoteDecreti(){
	var checkedIds = $(".chkDocNoteDecreti:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return
	}else{
		$('#deleteAllegatiModalND').modal();
	}
}
function showRemoveModalMessaggiInps(){
	var checkedIds = $(".chkDocMessaggiInps:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return
	}else{
		$('#deleteAllegatiModalMI').modal();
	}

}
function showRemoveModalLeggiDecreti(){
	var checkedIds = $(".chkDocLeggiDecreti:checked").map(function() {
		return this.value;

	}).toArray();
	// controlla checkbox selezionati
	if(!checkAtLeastOneSelection(checkedIds)){
		return
	}else{
		$('#deleteAllegatiModalLD').modal();
	}

}


//ELIMINA ALLEGATO__________________________________________________
function eliminaAllegatoNormativa(idTipo){
	
	var checkedIds;
	
	if(idTipo == 1){
		checkedIds = $(".chkDocCircolariInps:checked").map(function() {
			return this.value;

		}).toArray();
	}
	if(idTipo == 2){
		checkedIds = $(".chkDocNoteDecreti:checked").map(function() {
			return this.value;

		}).toArray();
	}
	if(idTipo == 3){
		checkedIds = $(".chkDocMessaggiInps:checked").map(function() {
			return this.value;

		}).toArray();
	}
	if(idTipo == 4){
		checkedIds = $(".chkDocLeggiDecreti:checked").map(function() {
			return this.value;

		}).toArray();
	}
	
	var d=[];
	var arrayLength=checkedIds.length;
	for (var i = 0; i < arrayLength; i++) {
		var idAssDoc= checkedIds[i].split("-");
		var id=idAssDoc[0];//0 = id associazione
		d.push(id);
	}

	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/json/eliminaAllegatoNormativa',
		data :{ 
		'listId' : d.toString(),
		'idTipo' : idTipo
	},


	success : function(data) {

		if(idTipo == 1){
			$('#addAllegatiCircolariInpsTable').DataTable().ajax.reload();
			$('#allegatiCircolariInpsTable').DataTable().ajax.reload();
			$('#deleteAllegatiModalCI').modal('hide');
		}
		if(idTipo == 2){
			$('#addAllegatiNoteDecretiTable').DataTable().ajax.reload();
			$('#allegatiNoteDecretiTable').DataTable().ajax.reload();
			$('#deleteAllegatiModalND').modal('hide');
		}
		if(idTipo == 3){
			$('#addAllegatiMessaggiInpsTable').DataTable().ajax.reload();
			$('#allegatiMessaggiInpsTable').DataTable().ajax.reload();
			$('#deleteAllegatiModalMI').modal('hide');
		}
		if(idTipo == 4){
			$('#addAllegatiLeggiDecretiTable').DataTable().ajax.reload();
			$('#allegatiLeggiDecretiTable').DataTable().ajax.reload();
			$('#deleteAllegatiModalLD').modal('hide');
		}
		
		
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Allegati cancellati correttamente ', settings);
		return; 

	},error: function(data){

		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante la cancellazione ', settings);
		return; 
	}
	});
}


//SCARICA ALLEGATO_______________________________________________________
function scaricaAllegatoNormativa(idTipo){
	
	
	var checkedIds;
	
	if(idTipo == 1){
		checkedIds = $(".chkDocCircolariInps:checked").map(function() {
			return this.value;

		}).toArray();
	}
	if(idTipo == 2){
		checkedIds = $(".chkDocNoteDecreti:checked").map(function() {
			return this.value;

		}).toArray();
	}
	if(idTipo == 3){
		checkedIds = $(".chkDocMessaggiInps:checked").map(function() {
			return this.value;

		}).toArray();
	}
	if(idTipo == 4){
		checkedIds = $(".chkDocLeggiDecreti:checked").map(function() {
			return this.value;

		}).toArray();
	}

	// controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return;
	}
	Metronic.startPageLoading();
	var idAssDoc= checkedIds[0].split("-");
	var idDoc= idAssDoc[1];


	$.ajax( {
		type : 'POST',
		dataType:'json',
		//AuAuditAction.java
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/downloadDocumento',
		data :{ 
		'idDocumento' : idDoc
	},
	success : function(data) {
		Metronic.stopPageLoading();
		$("input[name='ids']:checkbox").removeAttr('checked');

		var prefix=data.prefix.trim();
		var name=data.nomeDocumento.trim();
		var b64=data.base64File.trim();

		var type=base64MimeType(prefix)


		//es: download("data:text/plain;base64,"+data.base64File, "test.txt");
		download(prefix+b64, name, data.toBlob, type);
		return;


	},error: function(data){
		Metronic.stopPageLoading();
		$("input[name='ids']:checkbox").removeAttr('checked');
		Metronic.stopPageLoading();
		var settings = {
				theme: 'teal',
				sticky: false,
				horizontalEdge: 'top',
				verticalEdge: 'right',
				life: 3000
		};
		$.notific8('zindex', 11500);
		$.notific8('Errore durante il download ', settings);
		return; 
	}
	});
	Metronic.stopPageLoading();
}

