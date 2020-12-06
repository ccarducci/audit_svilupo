/*
 * Gestisce le funzioni CRUD dell'area.
 */


// mostra la modale per inserimento nuovo processo.
function showAsideProcesso(){
	
	// controllo il ruolo dell'utente loggato per la valorizzazione
	// del campo Owner:
	// 1) se è dirigente stampo il suo username
	// 2) se è delegato stampo lo username del dirigente associato.
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/jsonATPO/getUserNameDirigente',
		
		success : function(data) {
			Metronic.stopPageLoading();
			
			$('#owner').val(data.userNameDirigente);
			
			// sbianco i campi della modale
			$('#comboArea').val('');
			$('#descrizione').val('');
			$('#dataInizio').val('');
			$('#dataFine').val('');
			$('#input').val('');
			$('#output').val('');
			$('#ordinamento').val('');
			$('#statoAttivo').attr('checked', 'checked');
			
			
			sizeModalCustom();
			$('.modalProcesso').css("display", "block");
			jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
			 if(contOne){
				 jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
				 contOne = false;
			 }else{
			       
				 $('.modalProcesso').css("display", "none");
			        contOne = true;
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
					$.notific8('Nessuna associazione delegato-dirigente presente in archivio ', settings);
					return; 
		}
	});
	
	
}


function salvaProcesso(operation){
	
	// attivazione delle modali in base all'operazione
	// inserimento/modifica
	var processoDiv = '#messageProcessoDiv';
	var modalDiv = '#processoModal';
	if(operation != 'I'){
		processoDiv = '#modificaProcessoDiv';
		modalDiv = '#modificaProcessoModal';
	}
	
	var dataFine = $('#dataFine').val();
	
	var idProcesso = $('#idProcesso').val();
	var idArea = $('#comboArea option:selected').val();
	var dataInizio;
	
	var descrizione = $('#descrizione').val();
	var input = $('#input').val();
	var output = $('#output').val();
	var ordinamento = $('#ordinamento').val();
	var owner = $('#owner').val();
	
	if(idArea == '' || idArea == undefined){
		$(''+ processoDiv + '').html("<b>Attenzione!</b><br>Il campo Area è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(descrizione == '' || descrizione == undefined){
		$(''+ processoDiv + '').html("<b>Attenzione!</b><br>Il campo Descrizione è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if(ordinamento == '' || ordinamento == undefined){
		$(''+ processoDiv + '').html("<b>Attenzione!</b><br>Il campo Ordinamento è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}
	if($('#dataInizio').val() == '' || $('#dataInizio').val() == undefined){
		$(''+ processoDiv + '').html("<b>Attenzione!</b><br>Il campo Data Inizio è obbligatorio");
		$(''+modalDiv + '').modal();
		return;
	}else{
		dataInizio = $('#dataInizio').val();
	}
	
	if(!isInteger(ordinamento.replace('.', ','))){
		$(''+ processoDiv + '').html("<b>Attenzione!</b><br>Il campo Ordinamento richiede valori di tipo numerico");
		$(''+modalDiv + '').modal();
		return;
	}

	var stato = 'A';
	
	// controllo dataFine maggiore di dataInizio
	if(dataFine != ''){
		
		if(!checkDate(dataInizio, dataFine)){
			return;
		}
		
	}
	
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/salvaProcesso',
		data : { 	'processo.idProcesso' : idProcesso,
					'processo.area.idArea' : idArea,
					'processo.dataInizio' : dataInizio,
					'processo.dataFine' : dataFine,
					'processo.descrizione' : descrizione,
					'processo.input' : input,
					'processo.output' : output,
					'processo.stato' : stato,
					'processo.ordinamento' : ordinamento,
					'processo.owner' : owner
					
		   },
		
		success : function(data) {
			Metronic.stopPageLoading();
			
				if(data.error){
					
					$(''+ processoDiv + '').html("<b>Attenzione!</b><br>Le date selezionate non sono congruenti con il periodo di validità dell' Area associata");
					$(''+modalDiv + '').modal();
				}else if(data.errorOrdinamento){
					
					$(''+ processoDiv + '').html("<b>Attenzione!</b><br>Campo Ordinamento già presente in archivio");
					$(''+modalDiv + '').modal();
				}else{
					
					$('#processo_table').DataTable().ajax.reload();
					if(operation == 'I'){
						
						slideAsideNew('');
					}else{
						
						$(''+ processoDiv + '').html("Il Processo è stato aggiornato con successo");
						$(''+modalDiv + '').modal();
					}
					
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


function eliminaProcesso(){
	
	var checkedIds = $(".chkProcesso:checked").map(function() {
	    return this.value;
	  
	  }).toArray();
	
	var idProcesso = checkedIds[0];

	checkedIds=[];
	
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/eliminaProcesso',
			data : { 	'idProcesso' : idProcesso
			   },
			
			success : function(data) {
				Metronic.stopPageLoading();
				
				$('#processo_table').DataTable().ajax.reload();
			
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
		
		$('#deleteProcessoModal').modal('toggle');
}


function pubblicaProcessoModal(){
	
	var checkedIds = $(".chkProcesso:checked").map(function() {
	    return this.value;
	  
	  }).toArray();
	
	var idProcesso = checkedIds[0];
	var values = new Array(); 

   $.each($("#checkboxTable_"+idProcesso).closest("td").siblings("td"), 
          function () {
               values.push($(this).text());
          });

   var pubblica =  values[5];
	
   var idProcesso = checkedIds[0];

	checkedIds=[];
	
	$('#pubblicaProcessoModal').modal('toggle');
	
	pubblicaProcesso(idProcesso, pubblica, 'S');

}


// gestisce il cambia stato attiva/disattiva del processo
function pubblicaProcesso(idProcesso, pubblica, value){
	
	var cambiaStato = true;
	
//	switch(pubblica){
//	
//		case 'S' :
//			
//			if(value == 'S'){
//				$('#messageProcessoDiv').html("<b>Attenzione!</b><br>Il Processo selezionato è già stato Pubblicato");
//				$('#processoModal').modal();
//				
//				cambiaStato = false;
//			}
//			
//			break;
//	
//	}
	
	if(cambiaStato){
		
	
		$.ajax( {
			type : 'GET',
			url : '/CruscottoAuditAtpoWebWeb/pubblicaProcesso',
			data : { 	'idProcesso' : idProcesso,
						'pubblicazione' : value
					},
			
			success : function(data) {
				Metronic.stopPageLoading();
	
				$('#processo_table').DataTable().ajax.reload();
				
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
	
}



function aggiornaProcessoTable(){

	$('#processo_table').DataTable().ajax.reload();
	
}

function resetProcessoForm(){
	$("#processoForm").trigger('reset');
	
}

// ricarica la tabella nel Tab Normativa
function ricaricaProcessoNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();
	if(idTipo == undefined){
		
		idTipo=$('#tipoNormativa').val();
	}
	
	if(idTipo == 1){
		$("#buttonPortletTab3").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageCircolariInps', 'processoNormativaDiv', null, 'initCircolariINps');
	}
	
	if(idTipo == 2){
		$("#buttonPortletTab3").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageNoteDecreti', 'processoNormativaDiv', null, 'initNoteDecreti');
	}
	
	if(idTipo == 3){
		$("#buttonPortletTab3").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageMessaggiInps', 'processoNormativaDiv', null, 'initMessaggiInps');
	}
	
	if(idTipo == 4){
		$("#buttonPortletTab3").show();
		loadDiv('/CruscottoAuditAtpoWebWeb/getPageLeggiDecreti', 'processoNormativaDiv', null, 'initLeggiDecreti');
	}
	
}

//mostra la modale per associazione processo/normativa (Tab Normativa).
function showAsideProcessoNormativa(){
	
	var idTipo = $('#comboTipoNormativa option:selected').val();
	var idTipoDescrizione = $('#comboTipoNormativa option:selected').text();
	
	if(idTipo == 1){
		loadDiv('/CruscottoAuditAtpoWebWeb/getCircolariInpsAssociateAsideList', 'processoNormativaAsideDiv', null, 'initProcessoCircolariInps');
	}
	if(idTipo == 2){
		loadDiv('/CruscottoAuditAtpoWebWeb/getNoteDecretiAssociateAsideList', 'processoNormativaAsideDiv', null, 'initProcessoNoteDecreti');
	}
	if(idTipo == 3){
		loadDiv('/CruscottoAuditAtpoWebWeb/getMessaggiInpsAssociateAsideList', 'processoNormativaAsideDiv', null, 'initProcessoMessaggiInps');
	}
	if(idTipo == 4){
		loadDiv('/CruscottoAuditAtpoWebWeb/getLeggiDecretiAssociateAsideList', 'processoNormativaAsideDiv', null, 'initProcessoLeggiDecreti');
	}
	
	
	$('.modalProcessoNormativa').css("display", "block");
	jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
	 if(contOne){
		 jQuery('.menu-sx-levelone-facility.add').animate({marginRight:'0'}, 400);
		 contOne = false;
	 }else{
	       
		 $('.modalProcessoNormativa').css("display", "none");
	        contOne = true;
	    }
	 
	 $('#titoloAside').text('ASSOCIAZIONE PROCESSO - NORMATIVA (' + idTipoDescrizione + ')');
	
}


function associaProcessoNormativa(){
	
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
	
 // controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return
	}

	var idNormativa = checkedIds[0];
	var idProcesso = $('#idProcesso').val();
	
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/associaProcessoNormativa',
		data : { 	'assProcessoClasse.idProcesso' : idProcesso,
					'assProcessoClasse.idNormativa' : idNormativa,
					'assProcessoClasse.idTipo' : idTipo
					
		   },
		
		success : function(data) {
			Metronic.stopPageLoading();

			slideAsideNew('');
			
			if(idTipo == 1){
				$('#circolariInpsTable').DataTable().ajax.reload();
				$(".chkCircolariInps").removeAttr('checked');
			}
			if(idTipo == 2){
				$('#noteDecretiTable').DataTable().ajax.reload();
				$(".chkNoteDecreti").removeAttr('checked');
			}
			if(idTipo == 3){
				$('#messaggiInpsTable').DataTable().ajax.reload();
				$(".chkMessaggiInps").removeAttr('checked');
			}
			if(idTipo == 4){
				$('#leggiDecretiTable').DataTable().ajax.reload();
				$(".chkLeggiDecreti").removeAttr('checked');
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

function rimuoviAssociazioneProcessoNormativa(){
	
	
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
	
 // controlla checkbox selezionati
	if(!checkSelection(checkedIds)){
		return
	}
	
	
	var idNormativa = checkedIds[0];
	var idProcesso = $('#idProcesso').val();
	
	$.ajax( {
		type : 'GET',
		url : '/CruscottoAuditAtpoWebWeb/json/rimuoviAssociazioneProcessoNormativa',
		data : { 	'assProcessoClasse.idProcesso' : idProcesso,
					'assProcessoClasse.idNormativa' : idNormativa,
					'assProcessoClasse.idTipo' : idTipo
					
		   },
		
		success : function(data) {
			Metronic.stopPageLoading();

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

	
}
